package com.millennialmedia.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.millennialmedia.google.gson.Gson;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONObject;

class MMWebView extends WebView
{
  static final String JS_INTERFACE_NAME = "interface";
  static final String PROPERTY_BANNER_TYPE = "PROPERTY_BANNER_TYPE";
  static final String PROPERTY_EXPANDING = "PROPERTY_EXPANDING";
  static final String PROPERTY_STATE = "PROPERTY_STATE";
  long creatorAdImplId;
  String currentUrl;
  volatile boolean isExpanding;
  volatile boolean isUserClosedResize;
  private MyWebChromeClient mWebChromeClient = new MyWebChromeClient(null);
  volatile String mraidState;
  int oldHeight = -50;
  int oldWidth = -50;
  volatile boolean requiresPreAdSizeFix;
  final GestureDetector tapDetector;
  final String userAgent;

  public MMWebView(final Context paramContext, long paramLong)
  {
    super(paramContext);
    MMSDK.Log.d("Setting isExpanding in constructor to " + this.isExpanding);
    setWillNotDraw(false);
    setHorizontalScrollBarEnabled(false);
    setVerticalScrollBarEnabled(false);
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return (paramAnonymousMotionEvent.getAction() == 2) && (MMWebView.this.canScroll());
      }
    });
    this.mraidState = "loading";
    this.creatorAdImplId = paramLong;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(this.creatorAdImplId);
    MMSDK.Log.v("Assigning WebView internal id: %d", arrayOfObject);
    setId((int)(15063L + this.creatorAdImplId));
    if (HandShake.sharedHandShake(getContext()).hardwareAccelerationEnabled)
      enableHardwareAcceleration();
    while (true)
    {
      setMediaPlaybackRequiresUserGesture(false);
      setWebChromeClient(this.mWebChromeClient);
      WebSettings localWebSettings = getSettings();
      this.userAgent = (localWebSettings.getUserAgentString() + Build.MODEL);
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setCacheMode(-1);
      localWebSettings.setDefaultTextEncodingName("UTF-8");
      localWebSettings.setLoadWithOverviewMode(true);
      localWebSettings.setGeolocationEnabled(true);
      localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
      this.tapDetector = new GestureDetector(paramContext, new GestureDetector.SimpleOnGestureListener()
      {
        public boolean onSingleTapConfirmed(MotionEvent paramAnonymousMotionEvent)
        {
          MMSDK.Event.overlayTap(paramContext, MMWebView.this.creatorAdImplId);
          return false;
        }
      });
      return;
      disableAllAcceleration();
    }
  }

  private boolean canScroll()
  {
    return getParent() instanceof MMAdView;
  }

  private boolean hasDefaultResizeParams()
  {
    return (this.oldWidth == -50) && (this.oldHeight == -50);
  }

  void animateTransition(final MMAdImpl paramMMAdImpl)
  {
    try
    {
      synchronized (new Runnable()
      {
        public void run()
        {
          try
          {
            MMWebView.this.buildDrawingCache();
            Bitmap localBitmap1 = MMWebView.this.getDrawingCache();
            if (localBitmap1 != null)
            {
              Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1);
              paramMMAdImpl.prepareTransition(localBitmap2);
            }
            MMWebView.this.destroyDrawingCache();
            notify();
            return;
          }
          catch (Exception localException)
          {
            while (true)
            {
              MMSDK.Log.d(localException);
              notify();
            }
          }
          finally
          {
          }
        }
      })
      {
        MMSDK.runOnUiThread(???);
        ???.wait();
        return;
      }
    }
    catch (Exception localException)
    {
      ???.notify();
    }
  }

  void disableAllAcceleration()
  {
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = WebView.class.getMethod("setLayerType", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(0);
      arrayOfObject[1] = null;
      localMethod.invoke(this, arrayOfObject);
      MMSDK.Log.d("Remove allAcceleration");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  void enableHardwareAcceleration()
  {
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = WebView.class.getMethod("setLayerType", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(2);
      arrayOfObject[1] = null;
      localMethod.invoke(this, arrayOfObject);
      MMSDK.Log.d("Enabled hardwareAcceleration");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  void enableSoftwareAcceleration()
  {
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = Paint.class;
      Method localMethod = WebView.class.getMethod("setLayerType", arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(1);
      arrayOfObject[1] = null;
      localMethod.invoke(this, arrayOfObject);
      MMSDK.Log.d("Remove allAcceleration");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  Activity getActivity()
  {
    try
    {
      ViewParent localViewParent = getParent();
      Context localContext;
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        localContext = ((ViewGroup)localViewParent).getContext();
        if ((localContext == null) || (!(localContext instanceof MMActivity)));
      }
      for (MMActivity localMMActivity = (MMActivity)localContext; ; localMMActivity = null)
        return localMMActivity;
    }
    finally
    {
    }
  }

  AdViewOverlayView getAdViewOverlayView()
  {
    try
    {
      ViewParent localViewParent = getParent();
      if ((localViewParent != null) && ((localViewParent instanceof AdViewOverlayView)));
      for (AdViewOverlayView localAdViewOverlayView = (AdViewOverlayView)localViewParent; ; localAdViewOverlayView = null)
        return localAdViewOverlayView;
    }
    finally
    {
    }
  }

  MMAdView getBanner()
  {
    try
    {
      ViewParent localViewParent = getParent();
      if ((localViewParent != null) && ((localViewParent instanceof MMAdView)));
      for (MMAdView localMMAdView = (MMAdView)localViewParent; ; localMMAdView = null)
        return localMMAdView;
    }
    finally
    {
    }
  }

  MMAdView getMMAdView()
  {
    if ((getParent() instanceof MMAdView))
      return (MMAdView)getParent();
    return null;
  }

  MMLayout getMMLayout()
  {
    if ((getParent() instanceof MMLayout))
      return (MMLayout)getParent();
    return null;
  }

  String getUserAgent()
  {
    return this.userAgent;
  }

  boolean isCurrentParent(long paramLong)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent == null)
      return false;
    MMSDK.Log.w("Id check for parent: " + paramLong + " versus " + ((MMLayout)localViewParent).adImpl.internalId);
    if (paramLong == ((MMLayout)localViewParent).adImpl.internalId);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  boolean isMraidResized()
  {
    return "resized".equals(this.mraidState);
  }

  boolean isOriginalUrl(String paramString)
  {
    return ((!TextUtils.isEmpty(this.currentUrl)) && (paramString.equals(this.currentUrl + "?"))) || (paramString.equals(this.currentUrl + "#"));
  }

  boolean isParentBannerAd()
  {
    if (getParent() != null)
      return (ViewGroup)getParent() instanceof MMAdView;
    return false;
  }

  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.currentUrl = paramString1;
    super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
  }

  public void loadUrl(String paramString)
  {
    if (paramString.startsWith("http"))
      this.currentUrl = paramString;
    MMSDK.Log.v("loadUrl @@" + paramString);
    super.loadUrl(paramString);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = getMeasuredHeight();
    if (k == 0)
      k = i;
    if (this.requiresPreAdSizeFix)
    {
      setMeasuredDimension(1, 1);
      return;
    }
    setMeasuredDimension(j, k);
  }

  public void onPauseWebViewVideo()
  {
    try
    {
      WebView.class.getMethod("onPause", new Class[0]).invoke(this, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onResumeWebViewVideo()
  {
    try
    {
      WebView.class.getMethod("onResume", new Class[0]).invoke(this, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[] arrayOfInt = new int[2];
    getLocationInWindow(arrayOfInt);
    DTOAdViewLayout localDTOAdViewLayout = new DTOAdViewLayout(arrayOfInt[0], arrayOfInt[1], getWidth(), getHeight());
    loadUrl("javascript:MMJS.sdk.setAdSize(" + new Gson().toJson(localDTOAdViewLayout) + ");");
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    requestFocus();
    this.tapDetector.onTouchEvent(paramMotionEvent);
    if (paramMotionEvent.getAction() == 1)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramMotionEvent.getAction());
      arrayOfObject[1] = Float.valueOf(paramMotionEvent.getX());
      arrayOfObject[2] = Float.valueOf(paramMotionEvent.getY());
      MMSDK.Log.v("Ad clicked: action=%d x=%f y=%f", arrayOfObject);
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  void removeFromParent()
  {
    ViewParent localViewParent = getParent();
    if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      ((ViewGroup)localViewParent).removeView(this);
  }

  void setAdProperties(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      loadUrl("javascript:MMJS.sdk.setAdProperties(" + paramJSONObject + ");");
  }

  public void setBackgroundColor(int paramInt)
  {
    if (paramInt == 0)
      enableSoftwareAcceleration();
    super.setBackgroundColor(paramInt);
  }

  void setMediaPlaybackRequiresUserGesture(boolean paramBoolean)
  {
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Method localMethod = WebView.class.getMethod("setMediaPlaybackRequiresUserGesture", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      localMethod.invoke(this, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  void setMraidDefault()
  {
    loadUrl("javascript:MMJS.sdk.setState('default')");
    this.mraidState = "default";
  }

  void setMraidExpanded()
  {
    MMSDK.Log.d("Changing state to EXPANDED for ---- " + toString() + "---- of creatorId ---" + this.creatorAdImplId + " ----");
    loadUrl("javascript:MMJS.sdk.setState('expanded');");
    this.mraidState = "expanded";
  }

  void setMraidHidden()
  {
    loadUrl("javascript:MMJS.sdk.setState('hidden')");
    this.mraidState = "hidden";
  }

  void setMraidPlacementTypeInline()
  {
    loadUrl("javascript:MMJS.sdk.setPlacementType('inline');");
  }

  void setMraidPlacementTypeInterstitial()
  {
    loadUrl("javascript:MMJS.sdk.setPlacementType('interstitial');");
  }

  void setMraidReady()
  {
    loadUrl("javascript:MMJS.sdk.ready();");
  }

  void setMraidResize(final DTOResizeParameters paramDTOResizeParameters)
  {
    try
    {
      if (MMSDK.hasSetTranslationMethod())
      {
        final MMAdView localMMAdView = getMMAdView();
        this.isUserClosedResize = false;
        MMSDK.Log.d("New DTOResizeParameters = " + paramDTOResizeParameters);
        if (localMMAdView != null)
          MMSDK.runOnUiThread(new Runnable()
          {
            private void handleMraidResize(DTOResizeParameters paramAnonymousDTOResizeParameters)
            {
              MMAdView localMMAdView = localMMAdView;
              localMMAdView.getClass();
              MMAdView.BannerBounds localBannerBounds = new MMAdView.BannerBounds(localMMAdView, paramAnonymousDTOResizeParameters);
              setUnresizeParameters();
              localBannerBounds.modifyLayoutParams(MMWebView.this.getLayoutParams());
            }

            private void setUnresizeParameters()
            {
              if (MMWebView.this.hasDefaultResizeParams())
              {
                ViewGroup.LayoutParams localLayoutParams = MMWebView.this.getLayoutParams();
                MMWebView.this.oldWidth = localLayoutParams.width;
                MMWebView.this.oldHeight = localLayoutParams.height;
                if (MMWebView.this.oldWidth <= 0)
                  MMWebView.this.oldWidth = MMWebView.this.getWidth();
                if (MMWebView.this.oldHeight <= 0)
                  MMWebView.this.oldHeight = MMWebView.this.getHeight();
              }
            }

            public void run()
            {
              synchronized (MMWebView.this)
              {
                localMMAdView.handleMraidResize(paramDTOResizeParameters);
                handleMraidResize(paramDTOResizeParameters);
                MMWebView.this.loadUrl("javascript:MMJS.sdk.setState('resized');");
                MMWebView.this.mraidState = "resized";
                return;
              }
            }
          });
      }
      return;
    }
    finally
    {
    }
  }

  void setMraidViewableHidden()
  {
    loadUrl("javascript:MMJS.sdk.setViewable(false)");
  }

  void setMraidViewableVisible()
  {
    loadUrl("javascript:MMJS.sdk.setViewable(true)");
  }

  void setWebViewContent(String paramString1, String paramString2, Context paramContext)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    final String str1 = paramString2.substring(0, 1 + paramString2.lastIndexOf("/"));
    if (MMSDK.logLevel >= 5)
    {
      MMSDK.Log.v("Received ad with base url %s.", new Object[] { paramString2 });
      MMSDK.Log.v(paramString1);
    }
    final String str2 = paramString1;
    if (MRaid.hasMraidLocally(paramContext))
      str2 = MRaid.injectMraidJs(paramContext, str2);
    while (true)
    {
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled)
            MMWebView.this.enableHardwareAcceleration();
          while (true)
          {
            MMWebView.this.loadDataWithBaseURL(str1, str2, "text/html", "UTF-8", null);
            return;
            MMWebView.this.disableAllAcceleration();
          }
        }
      });
      return;
      MMSDK.Log.e("MMJS is not downloaded");
    }
  }

  void setWebViewContent(String paramString1, String paramString2, final MMAdImpl paramMMAdImpl)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramMMAdImpl == null))
      return;
    unresizeToDefault(paramMMAdImpl);
    final String str1 = paramString2.substring(0, 1 + paramString2.lastIndexOf("/"));
    if (MMSDK.logLevel >= 5)
    {
      MMSDK.Log.v("Received ad with base url %s.", new Object[] { str1 });
      MMSDK.Log.v(paramString1);
    }
    if (paramMMAdImpl.isTransitionAnimated())
      animateTransition(paramMMAdImpl);
    final String str2;
    if (paramMMAdImpl.ignoreDensityScaling)
    {
      str2 = "<head><meta name=\"viewport\" content=\"target-densitydpi=device-dpi\" /></head>" + paramString1;
      if (!MRaid.hasMraidLocally(paramMMAdImpl.getContext()))
        break label145;
      str2 = MRaid.injectMraidJs(paramMMAdImpl.getContext(), str2);
    }
    while (true)
    {
      MMSDK.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (HandShake.sharedHandShake(MMWebView.this.getContext()).hardwareAccelerationEnabled)
            MMWebView.this.enableHardwareAcceleration();
          while (true)
          {
            MMAd localMMAd = paramMMAdImpl.getCallingAd();
            if ((localMMAd != null) && ((localMMAd instanceof MMLayout)))
              ((MMLayout)localMMAd).removeVideo();
            MMWebView.this.loadDataWithBaseURL(str1, str2, "text/html", "UTF-8", null);
            return;
            MMWebView.this.disableAllAcceleration();
          }
        }
      });
      return;
      str2 = paramString1;
      break;
      label145: MMSDK.Log.e("MMJS is not downloaded");
    }
  }

  public String toString()
  {
    return "MMWebView originally from(" + this.creatorAdImplId + ") MRaidState(" + this.mraidState + ").";
  }

  void unresizeToDefault(MMAdImpl paramMMAdImpl)
  {
    try
    {
      if ((MMSDK.hasSetTranslationMethod()) && (isMraidResized()) && (paramMMAdImpl != null))
      {
        MMAd localMMAd = paramMMAdImpl.getCallingAd();
        if ((localMMAd instanceof MMAdView))
        {
          final MMAdView localMMAdView = (MMAdView)localMMAd;
          this.isUserClosedResize = true;
          MMSDK.runOnUiThread(new Runnable()
          {
            void handleUnresize()
            {
              if ((MMSDK.hasSetTranslationMethod()) && (!MMWebView.this.hasDefaultResizeParams()))
              {
                ViewGroup.LayoutParams localLayoutParams = MMWebView.this.getLayoutParams();
                localLayoutParams.width = MMWebView.this.oldWidth;
                localLayoutParams.height = MMWebView.this.oldHeight;
                MMWebView.this.oldWidth = -50;
                MMWebView.this.oldHeight = -50;
                MMWebView.this.requestLayout();
              }
            }

            public void run()
            {
              synchronized (MMWebView.this)
              {
                localMMAdView.handleUnresize();
                handleUnresize();
                MMWebView.this.loadUrl("javascript:MMJS.sdk.setState('default');");
                MMWebView.this.mraidState = "default";
                MMWebView.this.invalidate();
                return;
              }
            }
          });
        }
      }
      return;
    }
    finally
    {
    }
  }

  void updateArgumentsWithSettings(Map<String, String> paramMap)
  {
    if (isParentBannerAd());
    for (String str = "true"; ; str = "false")
    {
      paramMap.put("PROPERTY_BANNER_TYPE", str);
      paramMap.put("PROPERTY_STATE", this.mraidState);
      paramMap.put("PROPERTY_EXPANDING", String.valueOf(this.creatorAdImplId));
      return;
    }
  }

  private class MyWebChromeClient extends WebChromeClient
  {
    private static final String KEY_USE_GEO = "mm_use_geo_location";

    private MyWebChromeClient()
    {
    }

    private String getApplicationName(Context paramContext)
    {
      PackageManager localPackageManager = paramContext.getApplicationContext().getPackageManager();
      try
      {
        ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
        localApplicationInfo1 = localApplicationInfo2;
        if (localApplicationInfo1 != null)
        {
          localObject = localPackageManager.getApplicationLabel(localApplicationInfo1);
          return (String)localObject;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          ApplicationInfo localApplicationInfo1 = null;
          continue;
          Object localObject = "This app";
        }
      }
    }

    private boolean isFirstGeoRequest()
    {
      boolean bool1 = MMWebView.this.getContext().getSharedPreferences("MillennialMediaSettings", 0).contains("mm_use_geo_location");
      boolean bool2 = false;
      if (!bool1)
        bool2 = true;
      return bool2;
    }

    private boolean retrieveUseGeo()
    {
      return MMWebView.this.getContext().getSharedPreferences("MillennialMediaSettings", 0).getBoolean("mm_use_geo_location", false);
    }

    private void saveUseGeo(boolean paramBoolean)
    {
      SharedPreferences.Editor localEditor = MMWebView.this.getContext().getSharedPreferences("MillennialMediaSettings", 0).edit();
      localEditor.putBoolean("mm_use_geo_location", paramBoolean);
      localEditor.commit();
    }

    public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
    {
      MMSDK.Log.d(paramString1 + " at " + paramString2 + " -- line " + paramInt);
      super.onConsoleMessage(paramString1, paramInt, paramString2);
    }

    public void onGeolocationPermissionsShowPrompt(final String paramString, final GeolocationPermissions.Callback paramCallback)
    {
      if (isFirstGeoRequest())
      {
        if (retrieveUseGeo())
          paramCallback.invoke(paramString, true, true);
        Activity localActivity;
        do
        {
          return;
          localActivity = MMWebView.this.getActivity();
        }
        while (localActivity == null);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(localActivity);
        localBuilder.setTitle(getApplicationName(localActivity));
        localBuilder.setMessage("Would like to use your Current Location.").setPositiveButton("Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MMWebView.MyWebChromeClient.this.saveUseGeo(true);
            paramCallback.invoke(paramString, true, true);
          }
        }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            MMWebView.MyWebChromeClient.this.saveUseGeo(false);
            paramCallback.invoke(paramString, false, false);
          }
        });
        localBuilder.create().show();
        return;
      }
      paramCallback.invoke(paramString, false, false);
    }

    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      if (MMWebView.this.getContext() != MMWebView.this.getContext().getApplicationContext())
        return super.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
      Toast.makeText(MMWebView.this.getContext(), paramString2, 0).show();
      return true;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMWebView
 * JD-Core Version:    0.6.2
 */