package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MMJSObject
{
  protected WeakReference<Context> contextRef;
  protected WeakReference<MMWebView> mmWebViewRef;

  long getAdImplId(String paramString)
  {
    if (paramString != null)
      return ()Float.parseFloat(paramString);
    return -4L;
  }

  AdViewOverlayActivity getBaseActivity()
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if (localMMWebView != null)
    {
      Activity localActivity = localMMWebView.getActivity();
      if ((localActivity instanceof MMActivity))
      {
        MMBaseActivity localMMBaseActivity = ((MMActivity)localActivity).getWrappedActivity();
        if ((localMMBaseActivity instanceof AdViewOverlayActivity))
          return (AdViewOverlayActivity)localMMBaseActivity;
      }
    }
    return null;
  }

  MMJSResponse runOnUiThreadFuture(Callable<MMJSResponse> paramCallable)
  {
    FutureTask localFutureTask = new FutureTask(paramCallable);
    MMSDK.runOnUiThread(localFutureTask);
    try
    {
      MMJSResponse localMMJSResponse = (MMJSResponse)localFutureTask.get();
      return localMMJSResponse;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      while (true)
        localExecutionException.printStackTrace();
    }
  }

  void setContext(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
  }

  void setMMWebView(MMWebView paramMMWebView)
  {
    this.mmWebViewRef = new WeakReference(paramMMWebView);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMJSObject
 * JD-Core Version:    0.6.2
 */