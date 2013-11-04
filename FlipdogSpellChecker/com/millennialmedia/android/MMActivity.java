package com.millennialmedia.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MMActivity extends Activity
{
  long creatorAdImplInternalId;
  private MMBaseActivity mmBaseActivity;

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mmBaseActivity != null)
      return this.mmBaseActivity.dispatchTouchEvent(paramMotionEvent);
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  boolean dispatchTouchEventSuper(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void finish()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.finish();
      return;
    }
    MMSDK.Event.overlayClosed(this, this.creatorAdImplInternalId);
    super.finish();
  }

  public void finishSuper()
  {
    MMSDK.Event.overlayClosed(this, this.creatorAdImplInternalId);
    super.finish();
  }

  protected MMBaseActivity getWrappedActivity()
  {
    return this.mmBaseActivity;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  void onActivityResultSuper(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onConfigurationChanged(paramConfiguration);
      return;
    }
    super.onConfigurationChanged(paramConfiguration);
  }

  void onConfigurationChangedSuper(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    String str = null;
    this.creatorAdImplInternalId = getIntent().getLongExtra("internalId", -4L);
    try
    {
      str = getIntent().getStringExtra("class");
      this.mmBaseActivity = ((MMBaseActivity)Class.forName(str).newInstance());
      this.mmBaseActivity.activity = this;
      this.mmBaseActivity.onCreate(paramBundle);
      return;
    }
    catch (Exception localException)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str;
      arrayOfObject[1] = localException.getMessage();
      MMSDK.Log.e("Could not start activity for %s. %s", arrayOfObject);
      localException.printStackTrace();
      finish();
    }
  }

  void onCreateSuper(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onDestroy();
      return;
    }
    super.onDestroy();
  }

  void onDestroySuper()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.mmBaseActivity != null)
      return this.mmBaseActivity.onKeyDown(paramInt, paramKeyEvent);
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  boolean onKeyDownSuper(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onPause();
      return;
    }
    super.onPause();
  }

  void onPauseSuper()
  {
    super.onPause();
  }

  protected void onRestart()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onRestart();
      return;
    }
    super.onRestart();
  }

  void onRestartSuper()
  {
    super.onRestart();
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onRestoreInstanceState(paramBundle);
      return;
    }
    super.onRestoreInstanceState(paramBundle);
  }

  void onRestoreInstanceStateSuper(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onResume();
      return;
    }
    super.onResume();
  }

  void onResumeSuper()
  {
    super.onResume();
  }

  public Object onRetainNonConfigurationInstance()
  {
    if (this.mmBaseActivity != null)
      return this.mmBaseActivity.onRetainNonConfigurationInstance();
    return super.onRetainNonConfigurationInstance();
  }

  Object onRetainNonConfigurationInstanceSuper()
  {
    return super.onRetainNonConfigurationInstance();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onSaveInstanceState(paramBundle);
      return;
    }
    super.onSaveInstanceState(paramBundle);
  }

  void onSaveInstanceStateSuper(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }

  protected void onStart()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onStart();
      return;
    }
    super.onStart();
  }

  void onStartSuper()
  {
    super.onStart();
  }

  protected void onStop()
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onStop();
      return;
    }
    super.onStop();
  }

  void onStopSuper()
  {
    super.onStop();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if (this.mmBaseActivity != null)
    {
      this.mmBaseActivity.onWindowFocusChanged(paramBoolean);
      return;
    }
    super.onWindowFocusChanged(paramBoolean);
  }

  void onWindowFocusChangedSuper(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMActivity
 * JD-Core Version:    0.6.2
 */