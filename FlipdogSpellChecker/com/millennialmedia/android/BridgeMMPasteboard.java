package com.millennialmedia.android;

import android.content.Context;
import android.text.ClipboardManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.Callable;

class BridgeMMPasteboard extends MMJSObject
{
  public MMJSResponse getPasteboardContents(HashMap<String, String> paramHashMap)
  {
    final Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          try
          {
            CharSequence localCharSequence = ((ClipboardManager)localContext.getSystemService("clipboard")).getText();
            String str = null;
            if (localCharSequence != null)
              str = localCharSequence.toString();
            if (str != null)
            {
              MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess(str);
              return localMMJSResponse;
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          return null;
        }
      });
    return null;
  }

  public MMJSResponse writeToPasteboard(final HashMap<String, String> paramHashMap)
  {
    final Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          try
          {
            ((ClipboardManager)localContext.getSystemService("clipboard")).setText((String)paramHashMap.get("data"));
            MMJSResponse localMMJSResponse = MMJSResponse.responseWithSuccess();
            return localMMJSResponse;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          return null;
        }
      });
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMPasteboard
 * JD-Core Version:    0.6.2
 */