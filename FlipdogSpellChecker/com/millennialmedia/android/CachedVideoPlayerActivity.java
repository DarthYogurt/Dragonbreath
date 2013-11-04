package com.millennialmedia.android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

class CachedVideoPlayerActivity extends VideoPlayerActivity
  implements Handler.Callback
{
  private static final int STATIC_HUD_ID = 402;
  private static final int STATIC_HUD_SECONDS_ID = 401;
  private RelativeLayout buttonsLayout;
  private boolean hasCountdownHud = true;
  boolean hasLoadedCompletionUrl;
  boolean hasWebOverlay;
  private TextView hudSeconds;
  private TextView hudStaticText;
  private int lastVideoPosition;
  private Handler logTimeAndEventhandler;
  private MMWebView mWebView;
  private VideoAd videoAd;

  private String calculateHudSecondsText()
  {
    if (this.currentVideoPosition != 0)
      return String.valueOf(this.currentVideoPosition / 1000);
    if (this.videoAd != null)
      return String.valueOf(this.videoAd.duration / 1000L);
    return "";
  }

  private void dismissAfter(long paramLong)
  {
    this.logTimeAndEventhandler.postDelayed(new Runnable()
    {
      public void run()
      {
        CachedVideoPlayerActivity.this.dismiss();
      }
    }
    , paramLong);
  }

  private void fadeButton(VideoImage paramVideoImage)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramVideoImage.fromAlpha, paramVideoImage.toAlpha);
    localAlphaAnimation.setDuration(paramVideoImage.fadeDuration);
    localAlphaAnimation.setFillEnabled(true);
    localAlphaAnimation.setFillBefore(true);
    localAlphaAnimation.setFillAfter(true);
    paramVideoImage.button.startAnimation(localAlphaAnimation);
  }

  private void fadeButtons()
  {
    if ((this.videoAd != null) && (this.videoAd.buttons != null))
      for (int i = 0; i < this.videoAd.buttons.size(); i++)
      {
        VideoImage localVideoImage = (VideoImage)this.videoAd.buttons.get(i);
        if (!localVideoImage.isLeaveBehind)
          setButtonAlpha(localVideoImage.button, localVideoImage.fromAlpha);
        if (localVideoImage.button.getParent() == null)
          this.buttonsLayout.addView(localVideoImage.button, localVideoImage.layoutParams);
        for (int j = 0; j < this.videoAd.buttons.size(); j++)
          this.buttonsLayout.bringChildToFront(((VideoImage)this.videoAd.buttons.get(j)).button);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(i);
        arrayOfObject[1] = Float.valueOf(localVideoImage.fromAlpha);
        MMSDK.Log.v("Button: %d alpha: %f", arrayOfObject);
      }
  }

  private void hideHud()
  {
    if (this.hudStaticText != null)
      this.hudStaticText.setVisibility(4);
    if (this.hudSeconds != null)
      this.hudSeconds.setVisibility(4);
  }

  private void initHudSeconds(ViewGroup paramViewGroup)
  {
    this.hudSeconds = new TextView(this.activity);
    this.hudSeconds.setText(calculateHudSecondsText());
    this.hudSeconds.setTextColor(-1);
    this.hudSeconds.setId(401);
    this.hudSeconds.setShadowLayer(1.0F, 0.0F, 0.0F, -16777216);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(0, 402);
    paramViewGroup.addView(this.hudSeconds, localLayoutParams);
  }

  private void initHudStaticText(ViewGroup paramViewGroup)
  {
    this.hudStaticText = new TextView(this.activity);
    this.hudStaticText.setText(" seconds remaining ...");
    this.hudStaticText.setTextColor(-1);
    this.hudStaticText.setPadding(0, 0, 5, 0);
    this.hudStaticText.setId(402);
    this.hudStaticText.setShadowLayer(1.0F, 0.0F, 0.0F, -16777216);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    paramViewGroup.addView(this.hudStaticText, localLayoutParams);
  }

  private void initWebOverlay()
  {
    this.mWebView = new MMWebView(this.activity, this.activity.creatorAdImplInternalId);
    this.mWebView.setId(413);
    MMWebViewClient.MMWebViewClientListener local3 = new MMWebViewClient.MMWebViewClientListener()
    {
      public void onPageFinished(String paramAnonymousString)
      {
        MMSDK.Log.d("@@ ON PAGE FINISHED" + paramAnonymousString);
        if ((CachedVideoPlayerActivity.this.mVideoView != null) && (CachedVideoPlayerActivity.this.videoAd != null) && (!CachedVideoPlayerActivity.this.mVideoView.isPlaying()) && (paramAnonymousString.equalsIgnoreCase(CachedVideoPlayerActivity.this.videoAd.webOverlayURL)))
          CachedVideoPlayerActivity.this.playVideo(0);
      }
    };
    this.mWebView.setWebViewClient(new InterstitialWebViewClient(local3, this.redirectListenerImpl));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.addRule(13);
    this.mWebView.setLayoutParams(localLayoutParams);
    this.mWebView.setBackgroundColor(0);
    loadUrlForMraidInjection(this.videoAd.webOverlayURL);
  }

  private void showHud()
  {
    if (this.videoAd != null)
      this.hudSeconds.setText(String.valueOf(this.videoAd.duration / 1000L));
    this.hudStaticText.setVisibility(0);
    this.hudSeconds.setVisibility(0);
  }

  protected boolean canFadeButtons()
  {
    return (!this.videoAd.stayInPlayer) || (!super.canFadeButtons());
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.videoAd != null)
    {
      if (this.logTimeAndEventhandler != null)
        this.logTimeAndEventhandler.removeMessages(1);
      if (!this.hasWebOverlay)
      {
        int i = 0;
        if (i < this.videoAd.buttons.size())
        {
          VideoImage localVideoImage = (VideoImage)this.videoAd.buttons.get(i);
          setButtonAlpha(localVideoImage.button, localVideoImage.fromAlpha);
          if (localVideoImage.inactivityTimeout > 0L)
          {
            Message localMessage = Message.obtain(this.logTimeAndEventhandler, 1, localVideoImage);
            this.logTimeAndEventhandler.sendMessageDelayed(localMessage, localVideoImage.inactivityTimeout);
          }
          while (true)
          {
            i++;
            break;
            if (paramMotionEvent.getAction() == 1)
            {
              if (canFadeButtons())
              {
                AlphaAnimation localAlphaAnimation = new AlphaAnimation(localVideoImage.fromAlpha, localVideoImage.toAlpha);
                localAlphaAnimation.setDuration(localVideoImage.fadeDuration);
                localAlphaAnimation.setFillEnabled(true);
                localAlphaAnimation.setFillBefore(true);
                localAlphaAnimation.setFillAfter(true);
                localVideoImage.button.startAnimation(localAlphaAnimation);
              }
            }
            else if (paramMotionEvent.getAction() == 0)
              setButtonAlpha(localVideoImage.button, localVideoImage.fromAlpha);
          }
        }
      }
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void enableButtons()
  {
    super.enableButtons();
    if ((this.hasWebOverlay) || (this.videoAd == null) || (this.videoAd.buttons == null));
    while (true)
    {
      return;
      Iterator localIterator = this.videoAd.buttons.iterator();
      while (localIterator.hasNext())
      {
        VideoImage localVideoImage = (VideoImage)localIterator.next();
        if (localVideoImage.button != null)
          localVideoImage.button.setEnabled(true);
      }
    }
  }

  protected void errorPlayVideo(String paramString)
  {
    if (this.videoAd != null)
      HttpGetRequest.log(this.videoAd.videoError);
    if (this.mWebView != null)
      this.mWebView.loadUrl("javascript:MMJS.cachedVideo.setError(" + paramString + ");");
    super.errorPlayVideo(paramString);
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 3:
      while (true)
      {
        return true;
        if (canFadeButtons())
        {
          fadeButton((VideoImage)paramMessage.obj);
          continue;
          VideoImage localVideoImage = (VideoImage)paramMessage.obj;
          try
          {
            if (this.buttonsLayout.indexOfChild(localVideoImage.button) == -1)
              this.buttonsLayout.addView(localVideoImage.button, localVideoImage.layoutParams);
            if (canFadeButtons())
            {
              fadeButton(localVideoImage);
              Object[] arrayOfObject = new Object[3];
              arrayOfObject[0] = Long.valueOf(localVideoImage.fadeDuration);
              arrayOfObject[1] = Integer.valueOf(localVideoImage.button.getId());
              arrayOfObject[2] = Long.valueOf(System.currentTimeMillis());
              MMSDK.Log.v("Beginning animation to visibility. Fade duration: %d Button: %d Time: %d", arrayOfObject);
            }
          }
          catch (IllegalStateException localIllegalStateException2)
          {
            while (true)
              localIllegalStateException2.printStackTrace();
          }
        }
      }
    case 2:
    }
    while (true)
    {
      try
      {
        if ((this.mVideoView != null) && (this.mVideoView.isPlaying()))
        {
          int i = this.mVideoView.getCurrentPosition();
          if (i > this.lastVideoPosition)
          {
            if (this.videoAd != null)
            {
              if (this.lastVideoPosition != 0)
                break label478;
              this.videoAd.logBeginEvent();
              break label478;
              if (j < this.videoAd.activities.size())
              {
                VideoLogEvent localVideoLogEvent = (VideoLogEvent)this.videoAd.activities.get(j);
                if ((localVideoLogEvent == null) || (localVideoLogEvent.position < this.lastVideoPosition) || (localVideoLogEvent.position >= i))
                  break label484;
                int k = 0;
                if (k >= localVideoLogEvent.activities.length)
                  break label484;
                MMSDK.Event.logEvent(localVideoLogEvent.activities[k]);
                k++;
                continue;
              }
            }
            this.lastVideoPosition = i;
          }
          if ((this.hasWebOverlay) && (this.mWebView != null))
            this.mWebView.loadUrl("javascript:MMJS.cachedVideo.updateVideoSeekTime(" + (float)Math.floor(i / 1000.0F) + ");");
          if (this.hasCountdownHud)
          {
            long l = (this.videoAd.duration - i) / 1000L;
            if (l <= 0L)
              break label471;
            if (this.hudSeconds != null)
              this.hudSeconds.setText(String.valueOf(l));
          }
        }
        this.logTimeAndEventhandler.sendMessageDelayed(Message.obtain(this.logTimeAndEventhandler, 2), 500L);
      }
      catch (IllegalStateException localIllegalStateException1)
      {
        localIllegalStateException1.printStackTrace();
      }
      break;
      label471: hideHud();
      continue;
      label478: int j = 0;
      continue;
      label484: j++;
    }
  }

  protected RelativeLayout initLayout()
  {
    RelativeLayout localRelativeLayout = super.initLayout();
    this.logTimeAndEventhandler = new Handler(this);
    setRequestedOrientation(0);
    if (this.hasCountdownHud)
    {
      initHudStaticText(localRelativeLayout);
      initHudSeconds(localRelativeLayout);
      showHud();
    }
    if ((this.videoAd != null) && (this.videoAd.webOverlayURL != null))
    {
      initWebOverlay();
      localRelativeLayout.addView(this.mWebView);
      this.hasWebOverlay = true;
    }
    label530: label681: 
    do
    {
      return localRelativeLayout;
      this.hasWebOverlay = false;
      this.buttonsLayout = new RelativeLayout(this.activity);
      this.buttonsLayout.setId(1000);
      VideoAd localVideoAd = this.videoAd;
      ArrayList localArrayList = null;
      if (localVideoAd != null)
        localArrayList = this.videoAd.buttons;
      if (localArrayList != null)
      {
        File localFile = AdCache.getCacheDirectory(this.activity);
        int i = 0;
        while (true)
          if (i < localArrayList.size())
          {
            final VideoImage localVideoImage = (VideoImage)localArrayList.get(i);
            final ImageButton localImageButton = new ImageButton(this.activity);
            localVideoImage.button = localImageButton;
            try
            {
              Bitmap localBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath() + File.separator + this.videoAd.getId() + Uri.parse(localVideoImage.imageUrl).getLastPathSegment().replaceFirst("\\.[^\\.]*$", ".dat"));
              if (localBitmap != null)
                localImageButton.setImageBitmap(localBitmap);
              while (true)
              {
                setButtonAlpha(localImageButton, localVideoImage.fromAlpha);
                localImageButton.setId(i + 1);
                localImageButton.setPadding(0, 0, 0, 0);
                if (!localVideoImage.isLeaveBehind)
                  break label530;
                localImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                localImageButton.setBackgroundColor(-16777216);
                localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
                if (!TextUtils.isEmpty(localVideoImage.linkUrl))
                  localImageButton.setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramAnonymousView)
                    {
                      if (localImageButton != null)
                        localImageButton.setEnabled(false);
                      CachedVideoPlayerActivity.this.redirectListenerImpl.orientation = localVideoImage.overlayOrientation;
                      CachedVideoPlayerActivity.this.dispatchButtonClick(localVideoImage.linkUrl);
                      CachedVideoPlayerActivity.this.logButtonEvent(localVideoImage);
                    }
                  });
                if (localVideoImage.appearanceDelay <= 0L)
                  break label681;
                localVideoImage.layoutParams = localLayoutParams;
                Message localMessage2 = Message.obtain(this.logTimeAndEventhandler, 3, localVideoImage);
                this.logTimeAndEventhandler.sendMessageDelayed(localMessage2, localVideoImage.appearanceDelay);
                if (localVideoImage.inactivityTimeout > 0L)
                {
                  Message localMessage1 = Message.obtain(this.logTimeAndEventhandler, 1, localVideoImage);
                  this.logTimeAndEventhandler.sendMessageDelayed(localMessage1, localVideoImage.inactivityTimeout + localVideoImage.appearanceDelay + localVideoImage.fadeDuration);
                }
                i++;
                break;
                localImageButton.setImageURI(Uri.parse(localFile.getAbsolutePath() + File.separator + this.videoAd.getId() + Uri.parse(localVideoImage.imageUrl).getLastPathSegment().replaceFirst("\\.[^\\.]*$", ".dat")));
              }
            }
            catch (Exception localException)
            {
              while (true)
              {
                MMSDK.Log.e(localException);
                continue;
                localImageButton.setBackgroundColor(0);
                RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
                Object[] arrayOfObject = new Object[5];
                arrayOfObject[0] = Integer.valueOf(localImageButton.getId());
                arrayOfObject[1] = Integer.valueOf(localVideoImage.anchor);
                arrayOfObject[2] = Integer.valueOf(localVideoImage.position);
                arrayOfObject[3] = Integer.valueOf(localVideoImage.anchor2);
                arrayOfObject[4] = Integer.valueOf(localVideoImage.position2);
                MMSDK.Log.v("Button: %d Anchor: %d Position: %d Anchor2: %d Position2: %d", arrayOfObject);
                localLayoutParams.addRule(localVideoImage.position, localVideoImage.anchor);
                localLayoutParams.addRule(localVideoImage.position2, localVideoImage.anchor2);
                localLayoutParams.setMargins(localVideoImage.paddingLeft, localVideoImage.paddingTop, localVideoImage.paddingRight, localVideoImage.paddingBottom);
                continue;
                this.buttonsLayout.addView(localImageButton, localLayoutParams);
              }
            }
          }
        localRelativeLayout.addView(this.buttonsLayout, new RelativeLayout.LayoutParams(-1, -1));
      }
      if (this.buttonsLayout != null)
        localRelativeLayout.bringChildToFront(this.buttonsLayout);
    }
    while (this.mWebView == null);
    localRelativeLayout.bringChildToFront(this.mWebView);
    return localRelativeLayout;
  }

  protected void initSavedInstance(Bundle paramBundle)
  {
    super.initSavedInstance(paramBundle);
    if (paramBundle == null)
    {
      String str = getIntent().getStringExtra("videoId");
      this.videoAd = ((VideoAd)AdCache.load(this.activity, str));
      if (this.videoAd != null)
      {
        this.hasBottomBar = this.videoAd.showControls;
        this.hasCountdownHud = this.videoAd.showCountdown;
      }
      return;
    }
    this.videoAd = ((VideoAd)paramBundle.getParcelable("videoAd"));
    this.hasBottomBar = paramBundle.getBoolean("shouldShowBottomBar");
    this.lastVideoPosition = paramBundle.getInt("lastVideoPosition");
    this.currentVideoPosition = paramBundle.getInt("currentVideoPosition");
    this.hasCountdownHud = this.videoAd.showCountdown;
  }

  void loadUrlForMraidInjection(String paramString)
  {
    new FetchWebViewContentTask(paramString).execute(new Void[0]);
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    super.onCompletion(paramMediaPlayer);
    if (this.hasCountdownHud)
      hideHud();
    if (this.videoAd != null)
      videoPlayerOnCompletion(this.videoAd.onCompletionUrl);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adProperties = new AdProperties(this.activity);
    MMSDK.Log.v("Is Cached Ad");
  }

  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    errorPlayVideo(String.format("Error while playing, %d - %d", arrayOfObject));
    return super.onError(paramMediaPlayer, paramInt1, paramInt2);
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    this.videoAd = ((VideoAd)paramBundle.getParcelable("videoAd"));
    super.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mWebView != null)
      this.mWebView.bringToFront();
    if (this.buttonsLayout != null)
      this.buttonsLayout.bringToFront();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putParcelable("videoAd", this.videoAd);
    super.onSaveInstanceState(paramBundle);
  }

  protected void onStart()
  {
    super.onStart();
    if ((!this.hasWebOverlay) && (this.videoAd != null) && (this.videoAd.stayInPlayer == true) && (this.isVideoCompleted == true))
      fadeButtons();
  }

  protected void pauseVideo()
  {
    super.pauseVideo();
    this.logTimeAndEventhandler.removeMessages(1);
    this.logTimeAndEventhandler.removeMessages(2);
    this.logTimeAndEventhandler.removeMessages(3);
  }

  protected void playVideo(int paramInt)
  {
    if (this.videoAd == null)
    {
      Toast.makeText(this.activity, "Sorry. There was a problem playing the video", 1).show();
      return;
    }
    if ((!this.logTimeAndEventhandler.hasMessages(2)) && (this.videoAd != null))
      this.logTimeAndEventhandler.sendMessageDelayed(Message.obtain(this.logTimeAndEventhandler, 2), 1000L);
    super.playVideo(paramInt);
  }

  protected void restartVideo()
  {
    if (this.videoAd != null)
    {
      if (((this.hasLoadedCompletionUrl) || (this.videoAd.reloadNonEndOverlayOnRestart)) && (this.videoAd.webOverlayURL != null) && (this.mWebView != null))
      {
        loadUrlForMraidInjection(this.videoAd.webOverlayURL);
        this.hasLoadedCompletionUrl = false;
      }
      ArrayList localArrayList = this.videoAd.buttons;
      this.logTimeAndEventhandler.removeMessages(1);
      this.logTimeAndEventhandler.removeMessages(2);
      this.logTimeAndEventhandler.removeMessages(3);
      this.lastVideoPosition = 0;
      if ((!this.hasWebOverlay) && (this.buttonsLayout != null) && (localArrayList != null))
        for (int i = 0; i < localArrayList.size(); i++)
        {
          VideoImage localVideoImage = (VideoImage)localArrayList.get(i);
          if (localVideoImage != null)
          {
            if (localVideoImage.appearanceDelay > 0L)
            {
              ImageButton localImageButton = localVideoImage.button;
              this.buttonsLayout.removeView(localImageButton);
              Message localMessage2 = Message.obtain(this.logTimeAndEventhandler, 3, localVideoImage);
              this.logTimeAndEventhandler.sendMessageDelayed(localMessage2, localVideoImage.appearanceDelay);
            }
            if (localVideoImage.inactivityTimeout > 0L)
            {
              Message localMessage1 = Message.obtain(this.logTimeAndEventhandler, 1, localVideoImage);
              this.logTimeAndEventhandler.sendMessageDelayed(localMessage1, localVideoImage.inactivityTimeout + localVideoImage.appearanceDelay + localVideoImage.fadeDuration);
            }
          }
        }
      if (this.logTimeAndEventhandler != null)
        this.logTimeAndEventhandler.sendMessageDelayed(Message.obtain(this.logTimeAndEventhandler, 2), 1000L);
      if (this.hasCountdownHud)
        showHud();
    }
    super.restartVideo();
  }

  protected void resumeVideo()
  {
    if (this.videoAd != null)
    {
      if (!this.logTimeAndEventhandler.hasMessages(2))
        this.logTimeAndEventhandler.sendMessageDelayed(Message.obtain(this.logTimeAndEventhandler, 2), 1000L);
      if (!this.hasWebOverlay)
      {
        if (this.hasCountdownHud)
        {
          long l2 = (this.videoAd.duration - this.currentVideoPosition) / 1000L;
          if (l2 <= 0L)
            break label253;
          if (this.hudSeconds != null)
            this.hudSeconds.setText(String.valueOf(l2));
        }
        while (this.videoAd.buttons != null)
        {
          for (int i = 0; i < this.videoAd.buttons.size(); i++)
          {
            VideoImage localVideoImage = (VideoImage)this.videoAd.buttons.get(i);
            long l1 = 0L;
            if ((localVideoImage.appearanceDelay > 0L) && (this.buttonsLayout.indexOfChild(localVideoImage.button) == -1))
            {
              Message localMessage2 = Message.obtain(this.logTimeAndEventhandler, 3, localVideoImage);
              l1 = localVideoImage.appearanceDelay - this.currentVideoPosition;
              if (l1 < 0L)
                l1 = 500L;
              this.logTimeAndEventhandler.sendMessageDelayed(localMessage2, l1);
            }
            if (localVideoImage.inactivityTimeout > 0L)
            {
              Message localMessage1 = Message.obtain(this.logTimeAndEventhandler, 1, localVideoImage);
              this.logTimeAndEventhandler.sendMessageDelayed(localMessage1, l1 + localVideoImage.inactivityTimeout + localVideoImage.fadeDuration);
            }
          }
          label253: hideHud();
        }
      }
    }
    super.resumeVideo();
  }

  protected void videoPlayerOnCompletion(String paramString)
  {
    if (paramString != null)
      dispatchButtonClick(paramString);
    if (this.videoAd != null)
    {
      this.videoAd.logEndEvent();
      if (!this.videoAd.stayInPlayer)
        dismiss();
    }
    else
    {
      return;
    }
    if ((!this.hasWebOverlay) && (this.videoAd.buttons != null))
    {
      fadeButtons();
      if (!this.videoAd.hasEndCard())
        dismiss();
    }
    if ((this.mWebView != null) && (!TextUtils.isEmpty(this.videoAd.endOverlayURL)))
    {
      loadUrlForMraidInjection(this.videoAd.endOverlayURL);
      this.mWebView.bringToFront();
    }
    while (true)
    {
      if (this.videoAd.closeDelayMillis != 0L)
        dismissAfter(this.videoAd.closeDelayMillis);
      this.logTimeAndEventhandler.removeMessages(1);
      this.logTimeAndEventhandler.removeMessages(2);
      this.logTimeAndEventhandler.removeMessages(3);
      return;
      if (this.hasWebOverlay)
        dismiss();
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
        CachedVideoPlayerActivity.this.dismiss();
      if (paramString != null)
      {
        CachedVideoPlayerActivity.this.mWebView.setWebViewContent(paramString, this.baseUrl, CachedVideoPlayerActivity.this.activity);
        CachedVideoPlayerActivity.this.hasLoadedCompletionUrl = true;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.CachedVideoPlayerActivity
 * JD-Core Version:    0.6.2
 */