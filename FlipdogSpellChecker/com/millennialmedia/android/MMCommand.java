package com.millennialmedia.android;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class MMCommand
  implements Runnable
{
  private Map<String, String> arguments;
  private String callback;
  private Class cls;
  private Method method;
  private WeakReference<MMWebView> webViewRef;

  MMCommand(MMWebView paramMMWebView, String paramString)
  {
    this.webViewRef = new WeakReference(paramMMWebView);
    while (true)
    {
      int j;
      try
      {
        String[] arrayOfString1 = Uri.parse(paramString).getHost().split("\\.");
        if (arrayOfString1.length < 2)
          return;
        String str1 = arrayOfString1[(-2 + arrayOfString1.length)];
        String str2 = arrayOfString1[(-1 + arrayOfString1.length)];
        this.arguments = new HashMap();
        String[] arrayOfString2 = paramString.substring(1 + paramString.indexOf('?')).split("&");
        int i = arrayOfString2.length;
        j = 0;
        if (j < i)
        {
          String[] arrayOfString3 = arrayOfString2[j].split("=");
          if (arrayOfString3.length >= 2)
          {
            this.arguments.put(Uri.decode(arrayOfString3[0]), Uri.decode(arrayOfString3[1]));
            if (arrayOfString3[0].equalsIgnoreCase("callback"))
              this.callback = Uri.decode(arrayOfString3[1]);
          }
        }
        else
        {
          this.cls = Class.forName("com.millennialmedia.android.Bridge" + str1);
          Class localClass = this.cls;
          Class[] arrayOfClass = new Class[1];
          arrayOfClass[0] = this.arguments.getClass();
          this.method = localClass.getMethod(str2, arrayOfClass);
          return;
        }
      }
      catch (Exception localException)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = localException.getMessage();
        MMSDK.Log.e("Exception while executing javascript call %s %s", arrayOfObject);
        localException.printStackTrace();
        return;
      }
      j++;
    }
  }

  private String getBridgeStrippedClassName()
  {
    return this.cls.getSimpleName().replaceFirst("Bridge", "");
  }

  boolean isResizeCommand()
  {
    if (this.method != null)
      return "resize".equals(this.method.getName());
    return false;
  }

  public void run()
  {
    if ((this.cls != null) && (this.method != null))
    {
      try
      {
        MMWebView localMMWebView1 = (MMWebView)this.webViewRef.get();
        if (localMMWebView1 == null)
          return;
        MMJSObject localMMJSObject = (MMJSObject)this.cls.newInstance();
        localMMJSObject.setContext(localMMWebView1.getContext());
        localMMJSObject.setMMWebView(localMMWebView1);
        localMMWebView1.updateArgumentsWithSettings(this.arguments);
        try
        {
          Method localMethod = this.method;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = this.arguments;
          localObject = (MMJSResponse)localMethod.invoke(localMMJSObject, arrayOfObject4);
          if ((this.callback == null) || (this.callback.length() <= 0))
            return;
          final MMWebView localMMWebView2 = (MMWebView)this.webViewRef.get();
          if (localMMWebView2 == null)
            return;
          if (localObject == null)
            localObject = MMJSResponse.responseWithError(this.method.getName());
          if (((MMJSResponse)localObject).methodName == null)
            ((MMJSResponse)localObject).methodName = this.method.getName();
          if (((MMJSResponse)localObject).className == null)
            ((MMJSResponse)localObject).className = getBridgeStrippedClassName();
          Object[] arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = this.callback;
          arrayOfObject3[1] = ((MMJSResponse)localObject).toJSONString();
          final String str2 = String.format("javascript:%s(%s);", arrayOfObject3);
          MMSDK.Log.v("Executing JS bridge callback: " + str2);
          MMSDK.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (MMCommand.this.method.getName().equals("expandWithProperties"))
                localMMWebView2.isExpanding = true;
              localMMWebView2.loadUrl(str2);
            }
          });
          return;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          while (true)
          {
            Object localObject;
            Throwable localThrowable = localInvocationTargetException.getCause();
            if ((localThrowable != null) && (localThrowable.getClass() == ActivityNotFoundException.class))
            {
              localObject = MMJSResponse.responseWithError("Activity not found");
            }
            else
            {
              MMJSResponse localMMJSResponse2 = MMJSResponse.responseWithError();
              localObject = localMMJSResponse2;
            }
          }
        }
      }
      catch (Exception localException)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = this.method.toString();
        arrayOfObject2[1] = localException.getMessage();
        MMSDK.Log.e("Exception while executing javascript call %s %s", arrayOfObject2);
        localException.printStackTrace();
        return;
      }
    }
    else if (!TextUtils.isEmpty(this.callback))
    {
      MMJSResponse localMMJSResponse1 = MMJSResponse.responseWithError("No class or method found");
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = this.callback;
      arrayOfObject1[1] = localMMJSResponse1.toJSONString();
      final String str1 = String.format("javascript:%s(%s);", arrayOfObject1);
      MMSDK.Log.v("Executing JS bridge failed callback: " + str1);
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          this.val$webViewCallback.loadUrl(str1);
        }
      });
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMCommand
 * JD-Core Version:    0.6.2
 */