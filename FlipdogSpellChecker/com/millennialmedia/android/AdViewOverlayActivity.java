package com.millennialmedia.android;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

class AdViewOverlayActivity extends MMBaseActivity
{
  private SensorEventListener accelerometerEventListener = new SensorEventListener()
  {
    private long currentTime = 0L;
    private float force = 0.0F;
    private float lastX = 0.0F;
    private float lastY = 0.0F;
    private float lastZ = 0.0F;
    private long prevShakeTime = 0L;
    private long prevTime = 0L;
    private long timeDifference = 0L;
    private float x = 0.0F;
    private float y = 0.0F;
    private float z = 0.0F;

    public void onAccuracyChanged(Sensor paramAnonymousSensor, int paramAnonymousInt)
    {
    }

    public void onSensorChanged(SensorEvent paramAnonymousSensorEvent)
    {
      this.currentTime = paramAnonymousSensorEvent.timestamp;
      this.x = paramAnonymousSensorEvent.values[0];
      this.y = paramAnonymousSensorEvent.values[1];
      this.z = paramAnonymousSensorEvent.values[2];
      this.timeDifference = (this.currentTime - this.prevTime);
      if (this.timeDifference > 500L)
      {
        AdViewOverlayActivity.this.didAccelerate(this.x, this.y, this.z);
        this.force = (Math.abs(this.x + this.y + this.z - this.lastX - this.lastY - this.lastZ) / (float)this.timeDifference);
        AdViewOverlayActivity.this.didAccelerate(this.x, this.y, this.z);
        if (this.force > 0.2F)
        {
          if (this.currentTime - this.prevShakeTime >= 1000L)
            AdViewOverlayActivity.this.didShake(this.force);
          this.prevShakeTime = this.currentTime;
        }
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;
        this.prevTime = this.currentTime;
      }
    }
  };
  private AdViewOverlayView adViewOverlayView;
  boolean hasFocus;
  private final int interval = 1000;
  boolean isPaused;
  private SensorManager sensorManager;
  private OverlaySettings settings;
  private final float threshold = 0.2F;

  private void didAccelerate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Float.valueOf(paramFloat1);
    arrayOfObject[1] = Float.valueOf(paramFloat2);
    arrayOfObject[2] = Float.valueOf(paramFloat3);
    MMSDK.Log.v("Accelerometer x:%fy:%fz:%f", arrayOfObject);
    this.adViewOverlayView.injectJS("javascript:didAccelerate(" + paramFloat1 + "," + paramFloat2 + "," + paramFloat3 + ")");
  }

  private void didShake(float paramFloat)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Float.valueOf(paramFloat);
    MMSDK.Log.v("Phone shaken: %f", arrayOfObject);
    this.adViewOverlayView.injectJS("javascript:didShake(" + paramFloat + ")");
  }

  private void lockOrientation()
  {
    if (this.activity.getRequestedOrientation() == 0)
    {
      setRequestedOrientation(0);
      return;
    }
    if (this.activity.getRequestedOrientation() == 8)
    {
      setRequestedOrientation(8);
      return;
    }
    if (this.activity.getRequestedOrientation() == 9)
    {
      setRequestedOrientation(9);
      return;
    }
    setRequestedOrientation(1);
  }

  private void setRequestedOrientation(String paramString)
  {
    if ("landscape".equalsIgnoreCase(paramString))
      setRequestedOrientation(0);
    while (!"portrait".equalsIgnoreCase(paramString))
      return;
    setRequestedOrientation(1);
  }

  private void startAccelerating()
  {
    this.sensorManager = ((SensorManager)getSystemService("sensor"));
    if (!Boolean.valueOf(this.sensorManager.registerListener(this.accelerometerEventListener, this.sensorManager.getDefaultSensor(1), 1)).booleanValue())
    {
      MMSDK.Log.w("Accelerometer not supported by this device. Unregistering listener.");
      this.sensorManager.unregisterListener(this.accelerometerEventListener, this.sensorManager.getDefaultSensor(1));
      this.accelerometerEventListener = null;
      this.sensorManager = null;
    }
  }

  private void stopAccelerating()
  {
    try
    {
      if ((this.sensorManager != null) && (this.accelerometerEventListener != null))
        this.sensorManager.unregisterListener(this.accelerometerEventListener, this.sensorManager.getDefaultSensor(1));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void finish()
  {
    if (this.adViewOverlayView != null)
      this.adViewOverlayView.attachWebViewToLink();
    super.finish();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.adViewOverlayView != null)
      this.adViewOverlayView.inlineConfigChange();
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973840);
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setBackgroundDrawable(new ColorDrawable(0));
    getWindow().clearFlags(1024);
    getWindow().addFlags(2048);
    getWindow().addFlags(16777216);
    Intent localIntent = getIntent();
    this.settings = ((OverlaySettings)localIntent.getParcelableExtra("settings"));
    if (this.settings == null)
      this.settings = new OverlaySettings();
    this.settings.log();
    if (this.settings.orientation != null)
      setRequestedOrientation(this.settings.orientation);
    if (this.settings.allowOrientationChange)
    {
      unlockScreenOrientation();
      if (localIntent != null)
      {
        Uri localUri = localIntent.getData();
        if (localUri != null)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localUri.getLastPathSegment();
          MMSDK.Log.v("Path: %s", arrayOfObject);
        }
      }
      RelativeLayout localRelativeLayout = new RelativeLayout(this.activity);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(13);
      localRelativeLayout.setId(885394873);
      localRelativeLayout.setLayoutParams(localLayoutParams);
      this.adViewOverlayView = new AdViewOverlayView(this, this.settings);
      localRelativeLayout.addView(this.adViewOverlayView);
      setContentView(localRelativeLayout);
      if (getLastNonConfigurationInstance() == null)
      {
        if (!this.settings.isExpanded())
          break label328;
        this.adViewOverlayView.adImpl.controller.webView.setMraidExpanded();
        if (this.settings.hasExpandUrl())
          this.adViewOverlayView.getWebContent(this.settings.urlToLoad);
      }
    }
    while (true)
    {
      this.settings.orientation = null;
      return;
      lockOrientation();
      break;
      label328: if (!this.settings.isExpanded())
        this.adViewOverlayView.loadWebContent(this.settings.content, this.settings.adUrl);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    MMSDK.Log.d("Overlay onDestroy");
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
    {
      this.adViewOverlayView.finishOverlayWithAnimation();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    this.isPaused = true;
    MMSDK.Log.d("Overlay onPause");
    if (this.settings.canAccelerate)
      stopAccelerating();
    BridgeMMMedia.Audio.sharedAudio(this.activity).stop();
    this.adViewOverlayView.pauseVideo();
    setResult(0);
    this.adViewOverlayView.adImpl.controller.pauseWebViewVideo();
    super.onPause();
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    this.isPaused = false;
    MMSDK.Log.d("Overlay onResume");
    if (this.settings.canAccelerate)
      startAccelerating();
    if (this.hasFocus)
      this.adViewOverlayView.resumeVideo();
    this.adViewOverlayView.addBlackView();
    this.adViewOverlayView.adImpl.controller.resumeWebViewVideo();
    super.onResume();
  }

  public Object onRetainNonConfigurationInstance()
  {
    return this.adViewOverlayView.getNonConfigurationInstance();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("adViewId", this.adViewOverlayView.getId());
    super.onSaveInstanceState(paramBundle);
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.hasFocus = paramBoolean;
    if ((!this.isPaused) && (paramBoolean))
      this.adViewOverlayView.resumeVideo();
  }

  void setAllowOrientationChange(boolean paramBoolean)
  {
    this.settings.allowOrientationChange = paramBoolean;
    if (paramBoolean)
    {
      unlockScreenOrientation();
      return;
    }
    lockOrientation();
  }

  void setRequestedOrientationLandscape()
  {
    this.settings.orientation = "landscape";
    this.settings.allowOrientationChange = false;
    setRequestedOrientation(0);
  }

  void setRequestedOrientationPortrait()
  {
    this.settings.orientation = "portrait";
    this.settings.allowOrientationChange = false;
    setRequestedOrientation(1);
  }

  void unlockScreenOrientation()
  {
    setRequestedOrientation(-1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdViewOverlayActivity
 * JD-Core Version:    0.6.2
 */