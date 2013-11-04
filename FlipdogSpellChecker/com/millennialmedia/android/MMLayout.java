package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

abstract class MMLayout extends RelativeLayout
  implements MMAd, InlineVideoView.TransparentFix
{
  static final String BOTTOM_CENTER = "bottom-center";
  static final String BOTTOM_LEFT = "bottom-left";
  static final String BOTTOM_RIGHT = "bottom-right";
  static final String CENTER = "center";
  private static final int CLOSE_AREA_SIZE = 50;
  public static final String KEY_HEIGHT = "height";
  public static final String KEY_WIDTH = "width";
  static final String TOP_CENTER = "top-center";
  static final String TOP_LEFT = "top-left";
  static final String TOP_RIGHT = "top-right";
  private static boolean appInit;
  MMAdImpl adImpl;
  View blackView;
  View closeAreaView;
  private GestureDetector diagnosticDetector;
  String goalId;
  RelativeLayout inlineVideoLayout;
  InlineVideoView inlineVideoView;
  boolean isResizing;

  protected MMLayout(Context paramContext)
  {
    super(paramContext);
    initLayout(paramContext);
  }

  @Deprecated
  protected MMLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initLayout(paramContext);
  }

  private void initInlineVideoTransparentFix()
  {
    if (this.blackView != null)
    {
      ViewParent localViewParent = this.blackView.getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        ((ViewGroup)localViewParent).removeView(this.blackView);
        this.blackView = null;
      }
    }
    this.blackView = new View(getContext());
    this.blackView.setBackgroundColor(-16777216);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    this.blackView.setLayoutParams(localLayoutParams);
    if ((this.inlineVideoLayout != null) && (this.blackView.getParent() == null))
      this.inlineVideoLayout.addView(this.blackView);
  }

  private void internalSetCloseArea(String paramString)
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (this.closeAreaView == null)
    {
      this.closeAreaView = new View(getContext());
      float f = getContext().getResources().getDisplayMetrics().density;
      localLayoutParams = new RelativeLayout.LayoutParams((int)(50.0F * f), (int)(50.0F * f));
      if (!"top-right".equals(paramString))
        break label94;
      localLayoutParams.addRule(11);
    }
    while (true)
    {
      this.closeAreaView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MMLayout.this.closeAreaTouched();
        }
      });
      addView(this.closeAreaView, localLayoutParams);
      return;
      label94: if ("top-center".equals(paramString))
      {
        localLayoutParams.addRule(14);
      }
      else if ("bottom-left".equals(paramString))
      {
        localLayoutParams.addRule(12);
      }
      else if ("bottom-center".equals(paramString))
      {
        localLayoutParams.addRule(12);
        localLayoutParams.addRule(14);
      }
      else if ("bottom-right".equals(paramString))
      {
        localLayoutParams.addRule(12);
        localLayoutParams.addRule(11);
      }
      else if ("center".equals(paramString))
      {
        localLayoutParams.addRule(13);
      }
    }
  }

  public void addBlackView()
  {
    initInlineVideoTransparentFix();
    if (this.blackView != null)
      this.blackView.setVisibility(0);
  }

  void addInlineVideo()
  {
    if ((this.inlineVideoLayout != null) && (this.inlineVideoLayout.getParent() != null))
      ((ViewGroup)this.inlineVideoLayout.getParent()).removeView(this.inlineVideoLayout);
    this.inlineVideoLayout = new RelativeLayout(getContext());
    this.inlineVideoLayout.setId(892934232);
    if (this.inlineVideoView.getParent() != null)
      ((ViewGroup)this.inlineVideoView.getParent()).removeView(this.inlineVideoView);
    this.inlineVideoLayout.addView(this.inlineVideoView);
    if (this.blackView != null)
    {
      if (this.blackView.getParent() == null)
        this.inlineVideoLayout.addView(this.blackView);
      this.blackView.bringToFront();
    }
    addView(this.inlineVideoLayout, this.inlineVideoView.getCustomLayoutParams());
  }

  boolean adjustVideo(final InlineVideoView.InlineParams paramInlineParams)
  {
    MMSDK.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (MMLayout.this.inlineVideoView != null)
          MMLayout.this.inlineVideoView.adjustVideo(paramInlineParams);
      }
    });
    return false;
  }

  void closeAreaTouched()
  {
  }

  protected void finalize()
    throws Throwable
  {
    if (getId() == -1)
    {
      this.adImpl.isFinishing = true;
      MMSDK.Log.d("finalize() for " + this.adImpl);
      MMAdImplController.removeAdViewController(this.adImpl);
    }
  }

  void fullScreenVideoLayout()
  {
  }

  public String getApid()
  {
    return this.adImpl.getApid();
  }

  public boolean getIgnoresDensityScaling()
  {
    return this.adImpl.getIgnoresDensityScaling();
  }

  public RequestListener getListener()
  {
    return this.adImpl.getListener();
  }

  public MMRequest getMMRequest()
  {
    return this.adImpl.getMMRequest();
  }

  void initInlineVideo(InlineVideoView.InlineParams paramInlineParams)
  {
    if (this.inlineVideoView != null)
    {
      ViewGroup localViewGroup = (ViewGroup)this.inlineVideoView.getParent();
      if (localViewGroup != null)
        localViewGroup.removeView(this.inlineVideoView);
      if (this.inlineVideoView.isPlaying())
        this.inlineVideoView.stopPlayback();
      this.inlineVideoView = null;
    }
    this.inlineVideoView = new InlineVideoView(this);
    this.inlineVideoView.initInlineVideo(paramInlineParams);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13, -1);
    this.inlineVideoView.setLayoutParams(localLayoutParams);
    addInlineVideo();
  }

  protected final void initLayout(Context paramContext)
  {
    try
    {
      MMSDK.Log.i("Initializing MMLayout.");
      MMSDK.checkPermissions(paramContext);
      MMSDK.checkActivity(paramContext);
      this.diagnosticDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener()
      {
        public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          if ((paramAnonymousMotionEvent1 == null) || (paramAnonymousMotionEvent2 == null));
          while ((Math.abs((int)(paramAnonymousMotionEvent2.getX() - paramAnonymousMotionEvent1.getX())) <= 200) || (Math.abs(paramAnonymousFloat1) <= Math.abs(paramAnonymousFloat2)))
            return false;
          if (paramAnonymousFloat1 > 0.0F)
            if (MMSDK.logLevel == 0)
            {
              MMSDK.Log.i("Enabling debug and verbose logging.");
              MMSDK.logLevel = 3;
            }
          while (true)
          {
            return true;
            MMSDK.Log.i("Disabling debug and verbose logging.");
            MMSDK.logLevel = 0;
            continue;
            MMSDK.printDiagnostics(MMLayout.this.adImpl);
          }
        }
      });
      if (!appInit)
      {
        MMSDK.Log.d("********** Millennial Device Id *****************");
        MMSDK.Log.d(MMSDK.getMMdid(paramContext));
        MMSDK.Log.d("Use the above identifier to register this device and receive test ads. Test devices can be registered and administered through your account at http://mmedia.com.");
        MMSDK.Log.d("*************************************************");
        AdCache.cleanCache(paramContext);
        appInit = true;
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localException.getMessage();
        MMSDK.Log.e("There was an exception initializing the MMAdView. %s", arrayOfObject);
        localException.printStackTrace();
      }
    }
  }

  boolean isVideoPlayingStreaming()
  {
    return (this.inlineVideoView != null) && (this.inlineVideoView.isPlayingStreaming());
  }

  void loadUrl(String paramString)
  {
    if (MMSDK.isConnected(getContext()))
    {
      if (this.adImpl.controller != null)
        this.adImpl.controller.loadUrl(paramString);
      return;
    }
    MMSDK.Log.e("No network available, can't load overlay.");
  }

  void loadWebContent(String paramString1, String paramString2)
  {
    if (MMSDK.isConnected(getContext()))
    {
      if (this.adImpl.controller != null)
        this.adImpl.controller.loadWebContent(paramString1, paramString2);
      return;
    }
    MMSDK.Log.e("No network available, can't load overlay.");
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!isInEditMode())
    {
      MMSDK.Log.d("onAttachedToWindow for " + this.adImpl);
      if (getId() == -1)
        MMSDK.Log.w("MMAd missing id from getId(). Performance will be affected for configuration changes.");
      if (!this.isResizing)
        MMAdImplController.assignAdViewController(this.adImpl);
      if (this.inlineVideoLayout != null)
        this.inlineVideoLayout.bringToFront();
    }
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    MMSDK.Log.d("onDetachedFromWindow for" + this.adImpl);
    if (!this.isResizing)
      MMAdImplController.removeAdViewController(this.adImpl);
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    Bundle localBundle = (Bundle)paramParcelable;
    long l = this.adImpl.internalId;
    this.adImpl.internalId = localBundle.getLong("MMAdImplId");
    this.adImpl.linkForExpansionId = localBundle.getLong("MMAdImplLinkedId");
    MMSDK.Log.d("onRestoreInstanceState replacing adImpl-" + l + " with " + this.adImpl + " id=" + getId());
    String str = localBundle.getString("inlineVideoViewGson");
    if (str != null)
      initInlineVideo(InlineVideoView.InlineParams.getInlineParams(str));
    super.onRestoreInstanceState(localBundle.getParcelable("super"));
  }

  protected Parcelable onSaveInstanceState()
  {
    super.onSaveInstanceState();
    MMSDK.Log.d("onSaveInstanceState saving - " + this.adImpl + " id=" + getId());
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("super", super.onSaveInstanceState());
    localBundle.putLong("MMAdImplId", this.adImpl.internalId);
    localBundle.putLong("MMAdImplLinkedId", this.adImpl.linkForExpansionId);
    if (this.inlineVideoView != null)
    {
      if (this.inlineVideoView.isPlaying())
        this.inlineVideoView.inlineParams.currentPosition = this.inlineVideoView.getCurrentPosition();
      localBundle.putString("inlineVideoViewGson", this.inlineVideoView.getGsonState());
    }
    return localBundle;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return (this.diagnosticDetector.onTouchEvent(paramMotionEvent)) || (!isClickable()) || (super.onTouchEvent(paramMotionEvent));
  }

  @Deprecated
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean)
    {
      if (this.inlineVideoView != null)
        this.inlineVideoView.resumeVideo();
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.adImpl;
      arrayOfObject[1] = Boolean.valueOf(paramBoolean);
      arrayOfObject[2] = MMAdImplController.controllersToString();
      MMSDK.Log.d("Window Focus Changed. For %s, Window in focus?: %b Controllers: %s", arrayOfObject);
      if ((this.adImpl != null) && (this.adImpl.controller != null) && (this.adImpl.controller.webView != null))
      {
        if (!paramBoolean)
          break label251;
        this.adImpl.controller.webView.setMraidViewableVisible();
      }
    }
    while (true)
    {
      if ((!paramBoolean) && ((getContext() instanceof Activity)))
      {
        Activity localActivity = (Activity)getContext();
        if ((localActivity == null) || ((localActivity.isFinishing()) && (this.adImpl != null)))
        {
          this.adImpl.isFinishing = true;
          MMSDK.Log.d("Window Focus Changed.removing " + this.adImpl);
          if ((this.adImpl.controller != null) && (this.adImpl.controller.webView != null))
            this.adImpl.controller.webView.setMraidHidden();
          MMAdImplController.removeAdViewController(this.adImpl);
        }
      }
      BridgeMMMedia.Audio.sharedAudio(getContext()).stop();
      return;
      if (this.inlineVideoView == null)
        break;
      this.inlineVideoView.pauseVideo();
      break;
      label251: this.adImpl.controller.webView.setMraidViewableHidden();
    }
  }

  void pauseVideo()
  {
    if (this.inlineVideoView != null)
      this.inlineVideoView.pauseVideo();
  }

  void playVideo()
  {
    if (this.inlineVideoView != null)
      this.inlineVideoView.playVideo();
  }

  public void removeBlackView()
  {
    if (this.blackView != null)
      this.blackView.setVisibility(4);
  }

  void removeCloseTouchDelegate()
  {
    if ((this.closeAreaView != null) && (this.closeAreaView.getParent() != null) && ((this.closeAreaView.getParent() instanceof ViewGroup)))
    {
      ((ViewGroup)this.closeAreaView.getParent()).removeView(this.closeAreaView);
      this.closeAreaView = null;
    }
  }

  void removeVideo()
  {
    if (this.inlineVideoView != null)
    {
      this.inlineVideoView.removeVideo();
      this.inlineVideoView = null;
    }
  }

  void repositionVideoLayout()
  {
  }

  void resumeVideo()
  {
    if (this.inlineVideoView != null)
      this.inlineVideoView.resumeVideo();
  }

  public void setApid(String paramString)
  {
    this.adImpl.setApid(paramString);
  }

  void setCloseArea(final String paramString)
  {
    post(new Runnable()
    {
      public void run()
      {
        MMLayout.this.internalSetCloseArea(paramString);
      }
    });
  }

  public void setIgnoresDensityScaling(boolean paramBoolean)
  {
    this.adImpl.setIgnoresDensityScaling(paramBoolean);
  }

  public void setListener(RequestListener paramRequestListener)
  {
    this.adImpl.setListener(paramRequestListener);
  }

  public void setMMRequest(MMRequest paramMMRequest)
  {
    this.adImpl.setMMRequest(paramMMRequest);
  }

  void setVideoSource(String paramString)
  {
    if (this.inlineVideoView != null)
      this.inlineVideoView.setVideoSource(paramString);
  }

  void stopVideo()
  {
    if (this.inlineVideoView != null)
      this.inlineVideoView.stopVideo();
  }

  class LayoutAdProperties extends AdProperties
  {
    LayoutAdProperties(Context arg2)
    {
      super();
    }

    String getAdDpiIndependentHeight()
    {
      float f = MMSDK.getDensity(getContext());
      return String.valueOf((int)(MMLayout.this.getHeight() / f));
    }

    String getAdDpiIndependentWidth()
    {
      float f = MMSDK.getDensity(getContext());
      return String.valueOf((int)(MMLayout.this.getWidth() / f));
    }
  }

  class MMLayoutMMAdImpl extends MMAdImpl
  {
    public MMLayoutMMAdImpl(Context arg2)
    {
      super();
      this.adProperties = new MMLayout.LayoutAdProperties(MMLayout.this, getContext());
    }

    public void addView(MMWebView paramMMWebView, RelativeLayout.LayoutParams paramLayoutParams)
    {
      MMSDK.Log.w("MMLayout adding view (" + paramMMWebView + ") to " + this);
      MMLayout.this.addView(paramMMWebView, paramLayoutParams);
    }

    MMLayout getCallingAd()
    {
      return MMLayout.this;
    }

    int getId()
    {
      return MMLayout.this.getId();
    }

    public void removeView(MMWebView paramMMWebView)
    {
      MMLayout.this.removeView(paramMMWebView);
    }

    public void setBackgroundColor(int paramInt)
    {
      MMLayout.this.setBackgroundColor(paramInt);
    }

    public void setClickable(boolean paramBoolean)
    {
      MMLayout.this.setClickable(paramBoolean);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMLayout
 * JD-Core Version:    0.6.2
 */