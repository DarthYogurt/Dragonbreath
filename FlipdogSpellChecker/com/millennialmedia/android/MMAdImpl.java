package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

abstract class MMAdImpl
  implements MMAd
{
  private static long nextAdViewId = 1L;
  AdProperties adProperties;
  String adType;
  String apid = "28911";
  WeakReference<Context> contextRef;
  MMAdImplController controller;
  boolean ignoreDensityScaling = false;
  long internalId;
  boolean isFinishing;
  long lastAdRequest;
  long linkForExpansionId;
  protected MMRequest mmRequest;
  MMWebViewClient mmWebViewClient;
  MMWebViewClient.MMWebViewClientListener mmWebViewClientListener;
  JSONObject obj;
  RequestListener requestListener;
  String userData;
  boolean xmlLayout = false;

  public MMAdImpl(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
    this.mmWebViewClientListener = new BasicMMWebViewClientListener();
    try
    {
      this.internalId = nextAdViewId;
      nextAdViewId = 1L + nextAdViewId;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Long.valueOf(this.internalId);
      MMSDK.Log.v("Assigning MMAdImpl internal id: %d", arrayOfObject);
      return;
    }
    finally
    {
    }
  }

  static String[] getAdTypes()
  {
    return new String[] { "b", "i" };
  }

  void addView(MMWebView paramMMWebView, RelativeLayout.LayoutParams paramLayoutParams)
  {
  }

  void animateTransition()
  {
  }

  JSONObject getAdProperties()
  {
    if (this.adProperties != null)
      return this.adProperties.getAdProperties();
    return null;
  }

  public String getApid()
  {
    return this.apid;
  }

  String getCachedName()
  {
    if ((this.adType != null) && (this.apid != null))
      return this.adType + "_" + this.apid;
    return null;
  }

  abstract MMAd getCallingAd();

  Context getContext()
  {
    if (this.contextRef != null)
      return (Context)this.contextRef.get();
    return null;
  }

  int getId()
  {
    return -1;
  }

  public boolean getIgnoresDensityScaling()
  {
    return this.ignoreDensityScaling;
  }

  public RequestListener getListener()
  {
    return this.requestListener;
  }

  public MMRequest getMMRequest()
  {
    return this.mmRequest;
  }

  MMWebViewClient getMMWebViewClient()
  {
    MMSDK.Log.d("Returning a client for user: DefaultWebViewClient, adimpl=" + this);
    return new BannerWebViewClient(this.mmWebViewClientListener, new MMAdImplRedirectionListenerImpl());
  }

  String getReqType()
  {
    return "fetch";
  }

  String getRequestCompletedAction()
  {
    return "millennialmedia.action.ACTION_FETCH_SUCCEEDED";
  }

  String getRequestFailedAction()
  {
    return "millennialmedia.action.ACTION_FETCH_FAILED";
  }

  public boolean hasCachedVideoSupport()
  {
    return true;
  }

  void insertUrlAdMetaValues(Map<String, String> paramMap)
  {
    Context localContext = getContext();
    paramMap.put("ar", "manual");
    paramMap.put("sdkapid", this.apid);
    paramMap.put("do", MMSDK.getOrientation(localContext));
    paramMap.put("olock", MMSDK.getOrientationLocked(localContext));
    if (!hasCachedVideoSupport())
      paramMap.put("cachedvideo", "false");
    paramMap.put("reqtype", getReqType());
    if (this.mmRequest != null)
      this.mmRequest.getUrlParams(paramMap);
    if (HandShake.sharedHandShake(localContext).canRequestVideo(localContext, this.adType))
      paramMap.put("video", "true");
    while (this.adType != null)
      if ((this.adType.equals("b")) || (this.adType.equals("i")))
      {
        paramMap.put("at", this.adType);
        return;
        paramMap.put("video", "false");
      }
      else
      {
        MMSDK.Log.e("******* ERROR: INCORRECT AD TYPE IN MMADVIEW OBJECT PARAMETERS (" + this.adType + ") **********");
        return;
      }
    MMSDK.Log.e("******* SDK DEFAULTED TO MMBannerAdBottom. THIS MAY AFFECT THE ADS YOU RECIEVE!!! **********");
    paramMap.put("at", "b");
  }

  public boolean isBanner()
  {
    return false;
  }

  boolean isLifecycleObservable()
  {
    return false;
  }

  boolean isRefreshable()
  {
    if (MMSDK.disableAdMinRefresh)
    {
      MMSDK.Log.d("Requesting of ads disabled by server");
      return false;
    }
    long l = System.currentTimeMillis();
    int i = (int)((l - this.lastAdRequest) / 1000L);
    if (i >= HandShake.sharedHandShake(getContext()).adRefreshSecs)
    {
      this.lastAdRequest = l;
      return true;
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = Integer.valueOf(15 - i);
    MMSDK.Log.d("Cannot request ad. Last ad request was %d seconds ago. Next ad can be requested in %d seconds.", arrayOfObject);
    return false;
  }

  boolean isTransitionAnimated()
  {
    return false;
  }

  boolean isUpdatingMraid()
  {
    return (this.controller != null) && (this.controller.webView != null) && (!this.controller.webView.isExpanding);
  }

  void prepareTransition(Bitmap paramBitmap)
  {
  }

  void removeView(MMWebView paramMMWebView)
  {
  }

  void requestAd()
  {
    MMAdImplController.assignAdViewController(this);
    if (this.controller != null)
      this.controller.requestAd();
  }

  public void setApid(String paramString)
  {
    if ((HandShake.apid == null) || (HandShake.apid.equals("28911")))
      HandShake.apid = this.apid;
    this.apid = paramString;
  }

  void setBackgroundColor(int paramInt)
  {
  }

  void setClickable(boolean paramBoolean)
  {
  }

  public void setIgnoresDensityScaling(boolean paramBoolean)
  {
    this.ignoreDensityScaling = paramBoolean;
  }

  public void setListener(RequestListener paramRequestListener)
  {
    this.requestListener = paramRequestListener;
  }

  public void setMMRequest(MMRequest paramMMRequest)
  {
    this.mmRequest = paramMMRequest;
  }

  public String toString()
  {
    return "AdType[(" + this.adType + ") InternalId(" + this.internalId + ") LinkedId(" + this.linkForExpansionId + ") isFinishing(" + this.isFinishing + ")]";
  }

  void unresizeToDefault()
  {
    if (this.controller != null)
      this.controller.unresizeToDefault();
  }

  class BasicMMWebViewClientListener extends MMWebViewClient.MMWebViewClientListener
  {
    BasicMMWebViewClientListener()
    {
    }

    public void onPageFinished(String paramString)
    {
      MMAdImpl.this.setClickable(true);
      if ((MMAdImpl.this.controller != null) && (MMAdImpl.this.controller.webView != null))
        synchronized (MMAdImpl.this.controller.webView)
        {
          if (MMAdImpl.this.controller.webView.hasWindowFocus())
          {
            MMAdImpl.this.controller.webView.setMraidViewableVisible();
            return;
          }
          MMAdImpl.this.controller.webView.setMraidViewableHidden();
        }
    }

    void onPageStarted(String paramString)
    {
      MMAdImpl.this.setClickable(false);
    }
  }

  class MMAdImplRedirectionListenerImpl extends HttpRedirection.RedirectionListenerImpl
  {
    public MMAdImplRedirectionListenerImpl()
    {
      this.creatorAdImplInternalId = MMAdImpl.this.internalId;
    }

    public JSONObject getAdProperties()
    {
      return MMAdImpl.this.getAdProperties();
    }

    public boolean isActivityStartable(Uri paramUri)
    {
      Context localContext = MMAdImpl.this.getContext();
      return (localContext == null) || (!(localContext instanceof Activity)) || (!((Activity)localContext).isFinishing());
    }

    public void startingActivity(Uri paramUri)
    {
      super.startingActivity(paramUri);
      if ((paramUri.getScheme().equalsIgnoreCase("http")) || (paramUri.getScheme().equalsIgnoreCase("https")))
        MMSDK.Event.overlayOpened(MMAdImpl.this);
    }

    public void updateLastVideoViewedTime()
    {
      if (MMAdImpl.this.adType != null)
        HandShake.sharedHandShake(MMAdImpl.this.getContext()).updateLastVideoViewedTime(MMAdImpl.this.getContext(), MMAdImpl.this.adType);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdImpl
 * JD-Core Version:    0.6.2
 */