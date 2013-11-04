package com.millennialmedia.android;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.Callable;

class BridgeMMInlineVideo extends MMJSObject
{
  public MMJSResponse adjustVideo(final HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if ((localMMWebView != null) && (localMMWebView != null) && (localMMWebView.getMMLayout().adjustVideo(new InlineVideoView.InlineParams(paramHashMap, localMMWebView.getContext()))))
          return MMJSResponse.responseWithSuccess();
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse insertVideo(final HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          localMMLayout.initInlineVideo(new InlineVideoView.InlineParams(paramHashMap, localMMWebView.getContext()));
          return MMJSResponse.responseWithSuccess("usingStreaming=" + localMMLayout.isVideoPlayingStreaming());
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse pauseVideo(HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.pauseVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse playVideo(HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.playVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse removeVideo(HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.removeVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse resumeVideo(HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.resumeVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse setStreamVideoSource(final HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          String str = (String)paramHashMap.get("streamVideoURI");
          if ((localMMLayout != null) && (str != null))
          {
            localMMLayout.setVideoSource(str);
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }

  public MMJSResponse stopVideo(HashMap<String, String> paramHashMap)
  {
    return runOnUiThreadFuture(new Callable()
    {
      public MMJSResponse call()
      {
        MMWebView localMMWebView = (MMWebView)BridgeMMInlineVideo.this.mmWebViewRef.get();
        if (localMMWebView != null)
        {
          MMLayout localMMLayout = localMMWebView.getMMLayout();
          if (localMMLayout != null)
          {
            localMMLayout.stopVideo();
            return MMJSResponse.responseWithSuccess();
          }
        }
        return MMJSResponse.responseWithError();
      }
    });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMInlineVideo
 * JD-Core Version:    0.6.2
 */