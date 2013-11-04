package com.millennialmedia.android;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.widget.RelativeLayout.LayoutParams;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

class MMAdImplController
  implements AdCache.AdCacheTaskListener
{
  static final long NO_ID_RETURNED = -4L;
  private static final Map<Long, MMAdImplController> saveableControllers = new ConcurrentHashMap();
  private static final Map<Long, WeakReference<MMAdImplController>> weakUnsaveableAdRef = new ConcurrentHashMap();
  volatile WeakReference<MMAdImpl> adImplRef;
  volatile long linkedAdImplId;
  RequestAdRunnable requestAdRunnable;
  volatile MMWebView webView;

  private MMAdImplController(MMAdImpl paramMMAdImpl)
  {
    MMSDK.Log.d("**************** creating new controller.");
    this.adImplRef = new WeakReference(paramMMAdImpl);
    if (paramMMAdImpl.linkForExpansionId != 0L)
    {
      linkForExpansion(paramMMAdImpl);
      this.webView = getWebViewFromExistingAdImpl(paramMMAdImpl);
      return;
    }
    if (paramMMAdImpl.isBanner())
    {
      this.webView = new MMWebView(paramMMAdImpl.getContext().getApplicationContext(), paramMMAdImpl.internalId);
      this.webView.requiresPreAdSizeFix = true;
      return;
    }
    this.webView = new MMWebView(paramMMAdImpl.getContext(), paramMMAdImpl.internalId);
  }

  static void assignAdViewController(MMAdImpl paramMMAdImpl)
  {
    try
    {
      if (paramMMAdImpl.controller != null)
      {
        if (!saveableControllers.containsValue(paramMMAdImpl.controller))
        {
          if (!paramMMAdImpl.isLifecycleObservable())
            break label112;
          saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), paramMMAdImpl.controller);
          if (weakUnsaveableAdRef.containsKey(Long.valueOf(paramMMAdImpl.internalId)))
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
        }
        while (true)
        {
          MMSDK.Log.d(paramMMAdImpl + " - Has a controller");
          return;
          label112: if (!weakUnsaveableAdRef.containsKey(Long.valueOf(paramMMAdImpl.internalId)))
            weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(paramMMAdImpl.controller));
        }
      }
    }
    finally
    {
    }
    MMSDK.Log.d("*****************************************assignAdViewController for " + paramMMAdImpl);
    MMAdImplController localMMAdImplController = (MMAdImplController)saveableControllers.get(Long.valueOf(paramMMAdImpl.internalId));
    if (localMMAdImplController == null)
    {
      WeakReference localWeakReference = (WeakReference)weakUnsaveableAdRef.get(Long.valueOf(paramMMAdImpl.internalId));
      if (localWeakReference != null)
        localMMAdImplController = (MMAdImplController)localWeakReference.get();
      if (localMMAdImplController == null)
      {
        localMMAdImplController = new MMAdImplController(paramMMAdImpl);
        if (!paramMMAdImpl.isLifecycleObservable())
          break label310;
        saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), localMMAdImplController);
      }
    }
    while (true)
    {
      paramMMAdImpl.controller = localMMAdImplController;
      localMMAdImplController.adImplRef = new WeakReference(paramMMAdImpl);
      if (localMMAdImplController.webView == null)
        break;
      setupWebView(paramMMAdImpl);
      break;
      label310: weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(localMMAdImplController));
    }
  }

  static void attachWebViewFromOverlay(MMAdImpl paramMMAdImpl)
  {
    try
    {
      MMSDK.Log.d("attachWebViewFromOverlay with " + paramMMAdImpl);
      MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
      if ((localMMAdImpl != null) && (localMMAdImpl.controller != null))
      {
        if (localMMAdImpl.controller.webView == null)
        {
          localMMAdImpl.controller.webView = paramMMAdImpl.controller.webView;
          paramMMAdImpl.removeView(paramMMAdImpl.controller.webView);
          paramMMAdImpl.controller.webView = null;
        }
        localMMAdImpl.controller.webView.setMraidDefault();
        localMMAdImpl.controller.webView.setWebViewClient(localMMAdImpl.getMMWebViewClient());
      }
      return;
    }
    finally
    {
    }
  }

  static void clearControllers()
  {
    if (saveableControllers != null)
      saveableControllers.clear();
    if (weakUnsaveableAdRef != null)
      weakUnsaveableAdRef.clear();
  }

  static String controllersToString()
  {
    return weakUnsaveableAdRef.toString() + " SAVED:" + saveableControllers.toString();
  }

  static void destroyOtherInlineVideo(Context paramContext)
  {
    Iterator localIterator = saveableControllers.entrySet().iterator();
    while (localIterator.hasNext())
    {
      MMAdImplController localMMAdImplController = (MMAdImplController)((Map.Entry)localIterator.next()).getValue();
      if (localMMAdImplController != null)
      {
        MMAdImpl localMMAdImpl = (MMAdImpl)localMMAdImplController.adImplRef.get();
        if (localMMAdImpl != null)
        {
          MMAd localMMAd = localMMAdImpl.getCallingAd();
          if ((localMMAd != null) && ((localMMAd instanceof MMLayout)))
            ((MMLayout)localMMAd).removeVideo();
        }
      }
    }
  }

  static MMAdImpl getAdImplWithId(long paramLong)
  {
    boolean bool = paramLong < -4L;
    MMAdImpl localMMAdImpl = null;
    if (!bool);
    while (true)
    {
      return localMMAdImpl;
      try
      {
        MMAdImplController localMMAdImplController = (MMAdImplController)saveableControllers.get(Long.valueOf(paramLong));
        if (localMMAdImplController == null)
        {
          WeakReference localWeakReference = (WeakReference)weakUnsaveableAdRef.get(Long.valueOf(paramLong));
          if (localWeakReference != null)
            localMMAdImplController = (MMAdImplController)localWeakReference.get();
        }
        localMMAdImpl = null;
        if (localMMAdImplController == null)
          continue;
        localMMAdImpl = (MMAdImpl)localMMAdImplController.adImplRef.get();
      }
      finally
      {
      }
    }
  }

  static MMWebView getWebViewFromExistingAdImpl(MMAdImpl paramMMAdImpl)
  {
    try
    {
      MMSDK.Log.i("getWebViewFromExistingLayout(" + paramMMAdImpl.internalId + " taking from " + paramMMAdImpl.linkForExpansionId + ")");
      MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
      MMWebView localMMWebView = null;
      if (localMMAdImpl != null)
      {
        MMAdImplController localMMAdImplController = localMMAdImpl.controller;
        localMMWebView = null;
        if (localMMAdImplController != null)
        {
          localMMWebView = localMMAdImpl.controller.webView;
          localMMAdImpl.controller.webView = null;
        }
      }
      return localMMWebView;
    }
    finally
    {
    }
  }

  private boolean isDownloadingCachedAd(MMAdImpl paramMMAdImpl)
  {
    boolean bool = true;
    while (true)
    {
      try
      {
        Context localContext = paramMMAdImpl.getContext();
        if (HandShake.sharedHandShake(localContext).isAdTypeDownloading(paramMMAdImpl.adType))
        {
          MMSDK.Log.i("There is a download in progress. Defering call for new ad");
          MMSDK.Event.requestFailed(paramMMAdImpl, new MMException(12));
          return bool;
        }
        MMSDK.Log.d("No download in progress.");
        CachedAd localCachedAd = AdCache.loadIncompleteDownload(localContext, paramMMAdImpl.getCachedName());
        if (localCachedAd != null)
        {
          MMSDK.Log.i("Last ad wasn't fully downloaded. Download again.");
          MMSDK.Event.fetchStartedCaching(paramMMAdImpl);
          AdCache.startDownloadTask(localContext, paramMMAdImpl.getCachedName(), localCachedAd, this);
          continue;
        }
      }
      finally
      {
      }
      MMSDK.Log.i("No incomplete downloads.");
      bool = false;
    }
  }

  static void removeAdViewController(MMAdImpl paramMMAdImpl)
  {
    while (true)
    {
      try
      {
        MMAdImplController localMMAdImplController1 = paramMMAdImpl.controller;
        if (localMMAdImplController1 == null)
          return;
        if (paramMMAdImpl.isLifecycleObservable())
        {
          saveableControllers.put(Long.valueOf(paramMMAdImpl.internalId), paramMMAdImpl.controller);
          if (weakUnsaveableAdRef.get(Long.valueOf(paramMMAdImpl.internalId)) != null)
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
          MMSDK.Log.d("****************RemoveAdviewcontroller - " + paramMMAdImpl);
          if (paramMMAdImpl.isFinishing)
          {
            saveableControllers.remove(Long.valueOf(paramMMAdImpl.internalId));
            weakUnsaveableAdRef.remove(Long.valueOf(paramMMAdImpl.internalId));
          }
          MMAdImplController localMMAdImplController2 = paramMMAdImpl.controller;
          paramMMAdImpl.controller = null;
          MMSDK.Log.d("****************RemoveAdviewcontroller - controllers " + controllersToString());
          if (localMMAdImplController2.webView == null)
            continue;
          MMSDK.Log.d("****************RemoveAdviewcontroller - controller!=null, expanding=" + localMMAdImplController2.webView.isExpanding);
          paramMMAdImpl.removeView(localMMAdImplController2.webView);
          localMMAdImplController2.webView.isExpanding = false;
          if ((!paramMMAdImpl.isFinishing) || (paramMMAdImpl.linkForExpansionId != 0L))
            continue;
          localMMAdImplController2.webView = null;
          continue;
        }
      }
      finally
      {
      }
      weakUnsaveableAdRef.put(Long.valueOf(paramMMAdImpl.internalId), new WeakReference(paramMMAdImpl.controller));
    }
  }

  private void requestAdInternal(MMAdImpl paramMMAdImpl)
  {
    if (paramMMAdImpl.apid == null)
    {
      localMMException = new MMException("MMAdView found with a null apid. New ad requests on this MMAdView are disabled until an apid has been assigned.", 1);
      MMSDK.Log.e(localMMException);
      MMSDK.Event.requestFailed(paramMMAdImpl, localMMException);
    }
    while ((!paramMMAdImpl.isBanner()) && (isDownloadingCachedAd(paramMMAdImpl)))
    {
      MMException localMMException;
      return;
    }
    try
    {
      if (this.requestAdRunnable != null)
      {
        MMSDK.Log.i(MMException.getErrorCodeMessage(12));
        MMSDK.Event.requestFailed(paramMMAdImpl, new MMException(12));
        return;
      }
    }
    finally
    {
    }
    this.requestAdRunnable = new RequestAdRunnable(null);
    Utils.ThreadUtils.execute(this.requestAdRunnable);
  }

  private static void setupWebView(MMAdImpl paramMMAdImpl)
  {
    try
    {
      MMAdImplController localMMAdImplController = paramMMAdImpl.controller;
      localMMAdImplController.webView.setWebViewClient(paramMMAdImpl.getMMWebViewClient());
      RelativeLayout.LayoutParams localLayoutParams;
      if (!localMMAdImplController.webView.isCurrentParent(paramMMAdImpl.internalId))
      {
        if (!paramMMAdImpl.isBanner())
          break label90;
        localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (localMMAdImplController.webView.isMraidResized())
          localMMAdImplController.webView.unresizeToDefault(paramMMAdImpl);
      }
      while (true)
      {
        localMMAdImplController.webView.removeFromParent();
        paramMMAdImpl.addView(localMMAdImplController.webView, localLayoutParams);
        return;
        label90: localLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
      }
    }
    finally
    {
    }
  }

  int checkReason(MMAdImpl paramMMAdImpl, CachedAd paramCachedAd)
  {
    if (paramCachedAd.isExpired())
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramCachedAd.getId();
      MMSDK.Log.d("%s is expired.", arrayOfObject3);
      return 21;
    }
    if (!paramCachedAd.isOnDisk(paramMMAdImpl.getContext()))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramCachedAd.getId();
      MMSDK.Log.d("%s is not on disk.", arrayOfObject2);
      return 22;
    }
    if (!HandShake.sharedHandShake(paramMMAdImpl.getContext()).canDisplayCachedAd(paramMMAdImpl.adType, paramCachedAd.deferredViewStart))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramCachedAd.getId();
      MMSDK.Log.d("%s cannot be shown at this time.", arrayOfObject1);
      return 24;
    }
    return 100;
  }

  int display(MMAdImpl paramMMAdImpl)
  {
    CachedAd localCachedAd = AdCache.loadNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName());
    if (localCachedAd != null)
    {
      if (localCachedAd.canShow(paramMMAdImpl.getContext(), paramMMAdImpl, true))
      {
        MMSDK.Event.displayStarted(paramMMAdImpl);
        AdCache.setNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName(), null);
        localCachedAd.show(paramMMAdImpl.getContext(), paramMMAdImpl.internalId);
        HandShake.sharedHandShake(paramMMAdImpl.getContext()).updateLastVideoViewedTime(paramMMAdImpl.getContext(), paramMMAdImpl.adType);
        return 0;
      }
      return checkReason(paramMMAdImpl, localCachedAd);
    }
    return 20;
  }

  public void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean)
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if (localMMAdImpl == null)
    {
      MMSDK.Log.e(MMException.getErrorCodeMessage(25));
      return;
    }
    if (paramBoolean)
      AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), paramCachedAd.getId());
    if (paramBoolean)
    {
      MMSDK.Event.requestCompleted(localMMAdImpl);
      return;
    }
    MMSDK.Event.requestFailed(localMMAdImpl, new MMException(15));
  }

  public void downloadStart(CachedAd paramCachedAd)
  {
  }

  String getUserAgent()
  {
    if (this.webView != null)
      return this.webView.getUserAgent();
    return Build.MODEL;
  }

  int isAdAvailable(MMAdImpl paramMMAdImpl)
  {
    CachedAd localCachedAd = AdCache.loadNextCachedAd(paramMMAdImpl.getContext(), paramMMAdImpl.getCachedName());
    if (localCachedAd != null)
    {
      if (localCachedAd.canShow(paramMMAdImpl.getContext(), paramMMAdImpl, true))
        return 0;
      return checkReason(paramMMAdImpl, localCachedAd);
    }
    MMSDK.Log.i("No next ad.");
    return 20;
  }

  void linkForExpansion(MMAdImpl paramMMAdImpl)
  {
    MMAdImpl localMMAdImpl = getAdImplWithId(paramMMAdImpl.linkForExpansionId);
    if (localMMAdImpl != null)
    {
      this.linkedAdImplId = paramMMAdImpl.linkForExpansionId;
      localMMAdImpl.controller.linkedAdImplId = paramMMAdImpl.internalId;
      localMMAdImpl.linkForExpansionId = paramMMAdImpl.internalId;
    }
  }

  void loadUrl(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (this.webView != null))
      this.webView.loadUrl(paramString);
  }

  void loadWebContent(String paramString1, String paramString2)
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if ((localMMAdImpl != null) && (this.webView != null))
      this.webView.setWebViewContent(paramString1, paramString2, localMMAdImpl);
  }

  void pauseWebViewVideo()
  {
    if (this.webView != null)
      this.webView.onPauseWebViewVideo();
  }

  void requestAd()
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    if (localMMAdImpl == null)
    {
      MMSDK.Log.e(MMException.getErrorCodeMessage(25));
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(25));
      return;
    }
    if (!localMMAdImpl.isRefreshable())
    {
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(16));
      return;
    }
    if (!MMSDK.isUiThread())
    {
      MMSDK.Log.e(MMException.getErrorCodeMessage(3));
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(3));
      return;
    }
    if (HandShake.sharedHandShake(localMMAdImpl.getContext()).kill)
    {
      MMSDK.Log.i("The server is no longer allowing ads.");
      MMSDK.Event.requestFailed(localMMAdImpl, new MMException(16));
      return;
    }
    try
    {
      MMSDK.Log.d("adLayout - requestAd");
      requestAdInternal(localMMAdImpl);
      return;
    }
    catch (Exception localException)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localException.getMessage();
      MMSDK.Log.e("There was an exception with the ad request. %s", arrayOfObject);
      localException.printStackTrace();
    }
  }

  void resumeWebViewVideo()
  {
    if (this.webView != null)
      this.webView.onResumeWebViewVideo();
  }

  void setWebViewContent(String paramString1, String paramString2)
  {
    this.webView.setWebViewContent(paramString1, paramString2, (MMAdImpl)this.adImplRef.get());
  }

  public String toString()
  {
    MMAdImpl localMMAdImpl = (MMAdImpl)this.adImplRef.get();
    StringBuilder localStringBuilder = new StringBuilder();
    if (localMMAdImpl != null)
      localStringBuilder.append(localMMAdImpl + "-LinkInC=" + this.linkedAdImplId);
    return localStringBuilder.toString() + " w/" + this.webView;
  }

  void unresizeToDefault()
  {
    if (this.webView != null)
      this.webView.unresizeToDefault((MMAdImpl)this.adImplRef.get());
  }

  private class RequestAdRunnable
    implements Runnable
  {
    String adUrl;
    HttpMMHeaders mmHeaders;

    private RequestAdRunnable()
    {
    }

    private boolean isAdUrlBuildable()
    {
      this.adUrl = null;
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null)
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      if (localMMAdImpl != null)
      {
        StringBuilder localStringBuilder;
        try
        {
          TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
          localMMAdImpl.insertUrlAdMetaValues(localTreeMap);
          MMSDK.insertUrlCommonValues(localMMAdImpl.getContext(), localTreeMap);
          localTreeMap.put("ua", localMMAdImpl.controller.getUserAgent());
          localStringBuilder = new StringBuilder(HandShake.getAdUrl());
          MMSDK.Log.d(localTreeMap.entrySet().toString());
          Iterator localIterator = localTreeMap.entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = localEntry.getKey();
            arrayOfObject2[1] = URLEncoder.encode((String)localEntry.getValue(), "UTF-8");
            localStringBuilder.append(String.format("%s=%s&", arrayOfObject2));
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          return failWithErrorMessage(new MMException(localUnsupportedEncodingException));
        }
        localStringBuilder.delete(-1 + localStringBuilder.length(), localStringBuilder.length());
        this.adUrl = localStringBuilder.toString();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = this.adUrl;
        MMSDK.Log.d("Calling for an advertisement: %s", arrayOfObject1);
      }
      else
      {
        failWithInfoMessage(new MMException(25));
      }
      return true;
    }

    private boolean isHandledHtmlResponse(HttpEntity paramHttpEntity)
    {
      try
      {
        WeakReference localWeakReference = MMAdImplController.this.adImplRef;
        MMAdImpl localMMAdImpl = null;
        if (localWeakReference != null)
          localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
        if (localMMAdImpl != null)
          if (!localMMAdImpl.isBanner())
          {
            InterstitialAd localInterstitialAd = new InterstitialAd();
            localInterstitialAd.content = HttpGetRequest.convertStreamToString(paramHttpEntity.getContent());
            localInterstitialAd.setId(localMMAdImpl.adType);
            localInterstitialAd.adUrl = this.adUrl;
            localInterstitialAd.mmHeaders = this.mmHeaders;
            if (MMSDK.logLevel >= 5)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localInterstitialAd.adUrl;
              MMSDK.Log.v("Received interstitial ad with url %s.", arrayOfObject);
              MMSDK.Log.v(localInterstitialAd.content);
            }
            AdCache.save(localMMAdImpl.getContext(), localInterstitialAd);
            AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localInterstitialAd.getId());
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
          else
          {
            if (localMMAdImpl.controller != null)
              localMMAdImpl.controller.setWebViewContent(HttpGetRequest.convertStreamToString(paramHttpEntity.getContent()), this.adUrl);
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
      }
      catch (IOException localIOException)
      {
        return failWithErrorMessage(new MMException("Exception raised in HTTP stream: " + localIOException.getMessage(), localIOException));
      }
      return true;
    }

    private boolean isHandledJsonResponse(HttpEntity paramHttpEntity)
    {
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null)
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      if (localMMAdImpl != null)
        if (localMMAdImpl.isBanner())
          return failWithErrorMessage(new MMException("Millennial ad return unsupported format.", 15));
      while (true)
      {
        VideoAd localVideoAd;
        try
        {
          localVideoAd = (VideoAd)CachedAd.parseJSON(HttpGetRequest.convertStreamToString(paramHttpEntity.getContent()));
          if ((localVideoAd != null) && (localVideoAd.isValid()))
          {
            MMSDK.Log.i("Cached video ad JSON received: " + localVideoAd.getId());
            if (localVideoAd.isExpired())
            {
              MMSDK.Log.i("New ad has expiration date in the past. Not downloading ad content.");
              localVideoAd.delete(localMMAdImpl.getContext());
              MMSDK.Event.requestFailed(localMMAdImpl, new MMException(15));
            }
          }
          else
          {
            return true;
          }
        }
        catch (IllegalStateException localIllegalStateException)
        {
          localIllegalStateException.printStackTrace();
          return failWithInfoMessage(new MMException("Millennial ad return failed. Invalid response data.", localIllegalStateException));
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
          return failWithInfoMessage(new MMException("Millennial ad return failed. " + localIOException.getMessage(), localIOException));
        }
        if (AdCache.loadNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName()) != null)
        {
          MMSDK.Log.i("Previously fetched ad exists in the playback queue. Not downloading ad content.");
          localVideoAd.delete(localMMAdImpl.getContext());
          MMSDK.Event.requestFailed(localMMAdImpl, new MMException(17));
        }
        else
        {
          AdCache.save(localMMAdImpl.getContext(), localVideoAd);
          if (!localVideoAd.isOnDisk(localMMAdImpl.getContext()))
          {
            MMSDK.Event.logEvent(localVideoAd.cacheMissURL);
            MMSDK.Log.d("Downloading ad...");
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            localVideoAd.downloadPriority = 3;
            AdCache.startDownloadTask(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localVideoAd, localMMAdImpl.controller);
          }
          else
          {
            MMSDK.Log.d("Cached ad is valid. Moving it to the front of the queue.");
            AdCache.setNextCachedAd(localMMAdImpl.getContext(), localMMAdImpl.getCachedName(), localVideoAd.getId());
            MMSDK.Event.fetchStartedCaching(localMMAdImpl);
            MMSDK.Event.requestCompleted(localMMAdImpl);
          }
        }
      }
    }

    private boolean isHandledResponse(HttpResponse paramHttpResponse)
    {
      HttpEntity localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity == null)
      {
        failWithInfoMessage(new MMException("Null HTTP entity", 14));
        return false;
      }
      if (localHttpEntity.getContentLength() == 0L)
      {
        failWithInfoMessage(new MMException("Millennial ad return failed. Zero content length returned.", 14));
        return false;
      }
      saveMacId(paramHttpResponse);
      Header localHeader1 = localHttpEntity.getContentType();
      if ((localHeader1 != null) && (localHeader1.getValue() != null))
      {
        if (localHeader1.getValue().toLowerCase().startsWith("application/json"))
          isHandledJsonResponse(localHttpEntity);
        while (true)
        {
          return true;
          if (!localHeader1.getValue().toLowerCase().startsWith("text/html"))
            break;
          Header localHeader2 = paramHttpResponse.getFirstHeader("X-MM-Video");
          this.mmHeaders = new HttpMMHeaders(paramHttpResponse.getAllHeaders());
          if ((localHeader2 != null) && (localHeader2.getValue().equalsIgnoreCase("true")))
          {
            WeakReference localWeakReference = MMAdImplController.this.adImplRef;
            MMAdImpl localMMAdImpl = null;
            if (localWeakReference != null)
              localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
            if (localMMAdImpl != null)
            {
              Context localContext = localMMAdImpl.getContext();
              HandShake.sharedHandShake(localContext).updateLastVideoViewedTime(localContext, localMMAdImpl.adType);
            }
          }
          isHandledHtmlResponse(localHttpEntity);
        }
        failWithInfoMessage(new MMException("Millennial ad return failed. Invalid (JSON/HTML expected) mime type returned.", 15));
        return false;
      }
      failWithInfoMessage(new MMException("Millennial ad return failed. HTTP Header value null.", 15));
      return false;
    }

    private void saveMacId(HttpResponse paramHttpResponse)
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Set-Cookie");
      int i = arrayOfHeader.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfHeader[j].getValue();
        int k = str.indexOf("MAC-ID=");
        if (k >= 0)
        {
          int m = str.indexOf(';', k);
          if (m > k)
            MMSDK.macId = str.substring(k + 7, m);
        }
      }
    }

    boolean fail(MMException paramMMException)
    {
      WeakReference localWeakReference = MMAdImplController.this.adImplRef;
      MMAdImpl localMMAdImpl = null;
      if (localWeakReference != null)
        localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
      MMSDK.Event.requestFailed(localMMAdImpl, paramMMException);
      return false;
    }

    boolean failWithErrorMessage(MMException paramMMException)
    {
      MMSDK.Log.e(paramMMException.getMessage());
      return fail(paramMMException);
    }

    boolean failWithInfoMessage(MMException paramMMException)
    {
      MMSDK.Log.i(paramMMException.getMessage());
      return fail(paramMMException);
    }

    public void run()
    {
      try
      {
        if (MMAdImplController.this.adImplRef != null)
        {
          MMAdImpl localMMAdImpl = (MMAdImpl)MMAdImplController.this.adImplRef.get();
          if ((localMMAdImpl != null) && (MMSDK.isConnected(localMMAdImpl.getContext())))
          {
            boolean bool1 = isAdUrlBuildable();
            if (!bool1)
              return;
            HttpResponse localHttpResponse;
            try
            {
              localHttpResponse = new HttpGetRequest().get(this.adUrl);
              if (localHttpResponse == null)
              {
                failWithErrorMessage(new MMException("HTTP response is null.", 14));
                return;
              }
            }
            catch (Exception localException2)
            {
              failWithErrorMessage(new MMException("Ad request HTTP error. " + localException2.getMessage(), 14));
              return;
            }
            boolean bool2 = isHandledResponse(localHttpResponse);
            if (bool2);
          }
          else
          {
            failWithInfoMessage(new MMException("No network available, can't call for ads.", 11));
            return;
          }
        }
        return;
      }
      catch (Exception localException1)
      {
        failWithInfoMessage(new MMException("Request not filled, can't call for ads.", 14));
        return;
      }
      finally
      {
        MMAdImplController.this.requestAdRunnable = null;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAdImplController
 * JD-Core Version:    0.6.2
 */