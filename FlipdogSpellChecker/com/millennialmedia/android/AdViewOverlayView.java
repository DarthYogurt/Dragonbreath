package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.ref.WeakReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

class AdViewOverlayView extends MMLayout
{
  private Button mraidCloseButton;
  CloseTopDrawable mraidCloseDrawable;
  WeakReference<AdViewOverlayActivity> overlayActivityRef;
  private ProgressBar progressBar;
  private boolean progressDone;
  OverlaySettings settings;

  AdViewOverlayView(AdViewOverlayActivity paramAdViewOverlayActivity, OverlaySettings paramOverlaySettings)
  {
    super(paramAdViewOverlayActivity.activity);
    this.overlayActivityRef = new WeakReference(paramAdViewOverlayActivity);
    this.adImpl = new AdViewOverlayViewMMAdImpl(paramAdViewOverlayActivity.activity);
    setId(15062);
    this.adImpl.adType = "i";
    this.settings = paramOverlaySettings;
    boolean bool = paramAdViewOverlayActivity.activity instanceof Activity;
    NonConfigurationInstance localNonConfigurationInstance = null;
    RelativeLayout.LayoutParams localLayoutParams;
    if (bool)
    {
      localNonConfigurationInstance = (NonConfigurationInstance)paramAdViewOverlayActivity.activity.getLastNonConfigurationInstance();
      if (localNonConfigurationInstance != null)
      {
        this.progressDone = localNonConfigurationInstance.progressDone;
        this.adImpl.controller = localNonConfigurationInstance.controller;
        this.settings = localNonConfigurationInstance.settings;
        if ((this.adImpl != null) && (this.adImpl.controller != null) && (this.adImpl.controller.webView != null))
          addView(this.adImpl.controller.webView);
        MMSDK.Log.d("Restoring configurationinstance w/ controller= " + localNonConfigurationInstance.controller);
      }
    }
    else
    {
      float f = paramAdViewOverlayActivity.activity.getResources().getDisplayMetrics().density;
      if ((this.settings.height == 0) || (this.settings.width == 0))
        break label499;
      localLayoutParams = new RelativeLayout.LayoutParams((int)(f * this.settings.width), (int)(f * this.settings.height));
      label257: localLayoutParams.addRule(13);
      setLayoutParams(localLayoutParams);
      Integer localInteger = Integer.valueOf((int)(0.0625F * f * this.settings.shouldResizeOverlay));
      setPadding(localInteger.intValue(), localInteger.intValue(), localInteger.intValue(), localInteger.intValue());
      this.mraidCloseButton = initMRaidCloseButton(paramAdViewOverlayActivity.activity, f);
      if ((this.settings.isExpanded()) && (!this.settings.hasExpandUrl()))
        this.adImpl.linkForExpansionId = this.settings.creatorAdImplId;
      MMAdImplController.assignAdViewController(this.adImpl);
      if (this.mraidCloseButton != null)
        addView(this.mraidCloseButton);
      if ((!this.progressDone) && (!this.settings.isExpanded()) && (!this.settings.isFromInterstitial()))
        initProgressBar();
      if (!this.settings.getIsTransparent())
        break label513;
      this.adImpl.controller.webView.setBackgroundColor(0);
      this.adImpl.setBackgroundColor(0);
    }
    while (true)
    {
      if (this.settings.enableHardwareAccel())
        this.adImpl.controller.webView.enableHardwareAcceleration();
      if (localNonConfigurationInstance == null)
        animateView();
      setUseCustomClose(this.settings.getUseCustomClose());
      return;
      MMSDK.Log.d("Null configurationinstance ");
      break;
      label499: localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
      break label257;
      label513: this.adImpl.controller.webView.setBackgroundColor(-1);
      this.adImpl.setBackgroundColor(-1);
    }
  }

  private void animateView()
  {
    Object localObject;
    if (this.settings.getTransition().equals("slideup"))
    {
      localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
      MMSDK.Log.v("Translate up");
    }
    while (true)
    {
      ((Animation)localObject).setDuration(this.settings.getTransitionDurationInMillis());
      startAnimation((Animation)localObject);
      do
      {
        return;
        if (this.settings.getTransition().equals("slidedown"))
        {
          localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
          MMSDK.Log.v("Translate down");
          break;
        }
      }
      while (!this.settings.getTransition().equals("explode"));
      localObject = new ScaleAnimation(1.1F, 0.9F, 0.1F, 0.9F, 1, 0.5F, 1, 0.5F);
      MMSDK.Log.v("Explode");
    }
  }

  private RelativeLayout.LayoutParams getCloseAreaParams(float paramFloat)
  {
    int i = (int)(0.5F + 50.0F * paramFloat);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, i);
    localLayoutParams.addRule(11);
    localLayoutParams.addRule(10);
    return localLayoutParams;
  }

  private Button initMRaidCloseButton(Context paramContext, float paramFloat)
  {
    Button localButton = new Button(paramContext);
    localButton.setId(301);
    this.mraidCloseDrawable = new CloseTopDrawable(true, paramFloat);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MMSDK.Log.v("Close button clicked.");
        AdViewOverlayView.this.finishOverlayWithAnimation();
      }
    });
    RelativeLayout.LayoutParams localLayoutParams = getCloseAreaParams(paramFloat);
    localButton.setLayoutParams(localLayoutParams);
    localButton.post(new SetCloseButtonTouchDelegateRunnable(localButton, localLayoutParams.topMargin, localLayoutParams.leftMargin, localLayoutParams.bottomMargin, localLayoutParams.rightMargin));
    return localButton;
  }

  private void initProgressBar()
  {
    AdViewOverlayActivity localAdViewOverlayActivity = (AdViewOverlayActivity)this.overlayActivityRef.get();
    if (localAdViewOverlayActivity != null)
    {
      this.progressBar = new ProgressBar(localAdViewOverlayActivity.activity);
      this.progressBar.setIndeterminate(true);
      this.progressBar.setVisibility(0);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(13);
      addView(this.progressBar, localLayoutParams);
    }
  }

  private void removeProgressBar()
  {
    if ((!this.progressDone) && (this.progressBar != null))
    {
      this.progressDone = true;
      this.progressBar.setVisibility(8);
      removeView(this.progressBar);
      this.progressBar = null;
    }
  }

  void addInlineVideo()
  {
    super.addInlineVideo();
    bringMraidCloseToFront();
  }

  void attachWebViewToLink()
  {
    if (this.adImpl.linkForExpansionId != 0L)
      MMAdImplController.attachWebViewFromOverlay(this.adImpl);
  }

  void bringMraidCloseToFront()
  {
    if (this.mraidCloseButton != null)
      this.mraidCloseButton.bringToFront();
  }

  void closeAreaTouched()
  {
    post(new Runnable()
    {
      public void run()
      {
        AdViewOverlayView.this.finishOverlayWithAnimation();
      }
    });
  }

  void finishOverlayWithAnimation()
  {
    MMSDK.Log.d("Ad overlay closed");
    if ((Activity)getContext() == null)
      return;
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        Activity localActivity = (Activity)AdViewOverlayView.this.getContext();
        MMSDK.Log.d("Finishing overlay this is in w/ anim finishOverLayWithAnim()");
        AdViewOverlayView.this.attachWebViewToLink();
        localActivity.finish();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        if (AdViewOverlayView.this.mraidCloseButton != null)
          AdViewOverlayView.this.mraidCloseButton.setVisibility(8);
      }
    });
    localAlphaAnimation.setFillEnabled(true);
    localAlphaAnimation.setFillBefore(true);
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setDuration(400L);
    startAnimation(localAlphaAnimation);
  }

  void fullScreenVideoLayout()
  {
    removeView(this.inlineVideoLayout);
    addView(this.inlineVideoLayout, new RelativeLayout.LayoutParams(-1, -1));
    bringMraidCloseToFront();
  }

  Object getNonConfigurationInstance()
  {
    MMSDK.Log.d("Saving getNonConfigurationInstance for " + this.adImpl);
    NonConfigurationInstance localNonConfigurationInstance = new NonConfigurationInstance(null);
    if (this.adImpl.controller != null)
      this.adImpl.controller.webView.removeFromParent();
    localNonConfigurationInstance.progressDone = this.progressDone;
    localNonConfigurationInstance.controller = this.adImpl.controller;
    localNonConfigurationInstance.settings = this.settings;
    return localNonConfigurationInstance;
  }

  void getWebContent(String paramString)
  {
    new FetchWebViewContentTask(paramString).execute(new Void[0]);
  }

  void injectJS(String paramString)
  {
    if (this.adImpl.controller != null)
      this.adImpl.controller.loadUrl(paramString);
  }

  void inlineConfigChange()
  {
    if ((this.inlineVideoView != null) && (this.inlineVideoLayout != null))
    {
      this.inlineVideoLayout.setLayoutParams(this.inlineVideoView.getCustomLayoutParams());
      bringMraidCloseToFront();
    }
  }

  void repositionVideoLayout()
  {
    removeView(this.inlineVideoLayout);
    addView(this.inlineVideoLayout, this.inlineVideoView.getCustomLayoutParams());
    bringMraidCloseToFront();
  }

  void setUseCustomClose(boolean paramBoolean)
  {
    this.settings.setUseCustomClose(paramBoolean);
    Button localButton = this.mraidCloseButton;
    if (paramBoolean);
    for (Object localObject = null; ; localObject = this.mraidCloseDrawable)
    {
      localButton.setBackgroundDrawable((Drawable)localObject);
      return;
    }
  }

  static abstract interface ActivityContainer
  {
    public abstract Activity getActivity();

    public abstract AdViewOverlayView getAdViewOverlayView();
  }

  class AdViewOverlayViewMMAdImpl extends MMLayout.MMLayoutMMAdImpl
  {
    public AdViewOverlayViewMMAdImpl(Context arg2)
    {
      super(localContext);
      this.mmWebViewClientListener = new MMAdImpl.BasicMMWebViewClientListener(AdViewOverlayView.this)
      {
        public void onPageFinished(String paramAnonymousString)
        {
          super.onPageFinished(paramAnonymousString);
          AdViewOverlayView.this.removeProgressBar();
        }
      };
    }

    MMWebViewClient getMMWebViewClient()
    {
      MMSDK.Log.d("Returning a client for user: OverlayWebViewClient, adimpl=" + AdViewOverlayView.this.adImpl);
      if ((AdViewOverlayView.this.adImpl.linkForExpansionId != 0L) || (AdViewOverlayView.this.settings.hasExpandUrl()))
      {
        BannerExpandedWebViewClient localBannerExpandedWebViewClient = new BannerExpandedWebViewClient(this.mmWebViewClientListener, new OverlayRedirectionListenerImpl());
        this.mmWebViewClient = localBannerExpandedWebViewClient;
        return localBannerExpandedWebViewClient;
      }
      InterstitialWebViewClient localInterstitialWebViewClient = new InterstitialWebViewClient(this.mmWebViewClientListener, new OverlayRedirectionListenerImpl());
      this.mmWebViewClient = localInterstitialWebViewClient;
      return localInterstitialWebViewClient;
    }

    class OverlayRedirectionListenerImpl extends MMAdImpl.MMAdImplRedirectionListenerImpl
    {
      OverlayRedirectionListenerImpl()
      {
        super();
      }

      public boolean isExpandingToUrl()
      {
        return (AdViewOverlayView.this.settings.hasExpandUrl()) && (!AdViewOverlayView.this.settings.hasLoadedExpandUrl());
      }
    }
  }

  private static class CloseDrawable extends Drawable
  {
    protected boolean enabled = true;
    protected final Paint paint;

    CloseDrawable(boolean paramBoolean)
    {
      this.enabled = paramBoolean;
      this.paint = new Paint();
      this.paint.setAntiAlias(true);
      this.paint.setStyle(Paint.Style.STROKE);
    }

    public void draw(Canvas paramCanvas)
    {
      Rect localRect = copyBounds();
      int i = localRect.right - localRect.left;
      int j = localRect.bottom - localRect.top;
      float f = i / 6.0F;
      this.paint.setStrokeWidth(f);
      if (this.enabled);
      for (int k = 255; ; k = 80)
      {
        this.paint.setARGB(255, k, k, k);
        paramCanvas.drawLine(f / 2.0F, f / 2.0F, i - f / 2.0F, j - f / 2.0F, this.paint);
        paramCanvas.drawLine(i - f / 2.0F, f / 2.0F, f / 2.0F, j - f / 2.0F, this.paint);
        return;
      }
    }

    public int getOpacity()
    {
      return -3;
    }

    public void setAlpha(int paramInt)
    {
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
    }
  }

  private static class CloseTopDrawable extends AdViewOverlayView.CloseDrawable
  {
    final float dist;
    final float scale;

    CloseTopDrawable(boolean paramBoolean, float paramFloat)
    {
      super();
      this.scale = paramFloat;
      this.dist = (4.0F * paramFloat);
      this.paint.setColor(-16777216);
    }

    public void draw(Canvas paramCanvas)
    {
      Rect localRect = copyBounds();
      float f1 = (localRect.right - localRect.left) / 10.0F;
      float f2 = localRect.right - 20.0F * this.scale;
      float f3 = localRect.top + 20.0F * this.scale;
      this.paint.setStrokeWidth(f1);
      this.paint.setColor(-16777216);
      this.paint.setStyle(Paint.Style.STROKE);
      paramCanvas.drawCircle(f2, f3, 12.0F * this.scale, this.paint);
      this.paint.setColor(-1);
      this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
      paramCanvas.drawCircle(f2, f3, 10.0F * this.scale, this.paint);
      this.paint.setColor(-16777216);
      paramCanvas.drawCircle(f2, f3, 7.0F * this.scale, this.paint);
      this.paint.setColor(-1);
      this.paint.setStrokeWidth(f1 / 2.0F);
      this.paint.setStyle(Paint.Style.STROKE);
      paramCanvas.drawLine(f2 - this.dist, f3 - this.dist, f2 + this.dist, f3 + this.dist, this.paint);
      paramCanvas.drawLine(f2 + this.dist, f3 - this.dist, f2 - this.dist, f3 + this.dist, this.paint);
    }
  }

  class FetchWebViewContentTask extends AsyncTask<Void, Void, String>
  {
    private String baseUrl;
    private boolean cancelVideo;

    public FetchWebViewContentTask(String arg2)
    {
      Object localObject;
      this.baseUrl = localObject;
    }

    protected String doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        HttpResponse localHttpResponse = new HttpGetRequest().get(this.baseUrl);
        StatusLine localStatusLine = localHttpResponse.getStatusLine();
        if ((localHttpResponse != null) && (localStatusLine != null) && (localStatusLine.getStatusCode() != 404))
        {
          HttpEntity localHttpEntity = localHttpResponse.getEntity();
          if (localHttpEntity != null)
            return HttpGetRequest.convertStreamToString(localHttpEntity.getContent());
        }
        else
        {
          this.cancelVideo = true;
        }
        return null;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }

    protected void onPostExecute(String paramString)
    {
      if (this.cancelVideo)
      {
        AdViewOverlayActivity localAdViewOverlayActivity = (AdViewOverlayActivity)AdViewOverlayView.this.overlayActivityRef.get();
        if (localAdViewOverlayActivity == null)
          break label52;
        localAdViewOverlayActivity.finish();
      }
      while (true)
      {
        if (paramString != null)
          AdViewOverlayView.this.adImpl.controller.setWebViewContent(paramString, this.baseUrl);
        return;
        label52: AdViewOverlayView.this.removeProgressBar();
      }
    }

    protected void onPreExecute()
    {
      if (AdViewOverlayView.this.progressBar == null)
        AdViewOverlayView.this.initProgressBar();
      super.onPreExecute();
    }
  }

  private static final class NonConfigurationInstance
  {
    MMAdImplController controller;
    boolean progressDone;
    OverlaySettings settings;
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public AdViewOverlayView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new AdViewOverlayView.SavedState(paramAnonymousParcel, null);
      }

      public AdViewOverlayView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new AdViewOverlayView.SavedState[paramAnonymousInt];
      }
    };
    public Object customInlineLayoutParams;
    String gson;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.gson = paramParcel.readString();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.gson);
    }
  }

  private static class SetCloseButtonTouchDelegateRunnable
    implements Runnable
  {
    int bottom;
    private final Button closeButton;
    int left;
    int right;
    int top;

    SetCloseButtonTouchDelegateRunnable(Button paramButton, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.closeButton = paramButton;
      this.top = paramInt1;
      this.left = paramInt2;
      this.bottom = paramInt3;
      this.right = paramInt4;
    }

    public void run()
    {
      Rect localRect = new Rect();
      this.closeButton.getHitRect(localRect);
      localRect.top += this.top;
      localRect.right += this.right;
      localRect.bottom += this.bottom;
      localRect.left += this.left;
      TouchDelegate localTouchDelegate = new TouchDelegate(localRect, this.closeButton);
      if (View.class.isInstance(this.closeButton.getParent()))
        ((View)this.closeButton.getParent()).setTouchDelegate(localTouchDelegate);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdViewOverlayView
 * JD-Core Version:    0.6.2
 */