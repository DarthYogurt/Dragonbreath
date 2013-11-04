package com.adwhirl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.adwhirl.adapters.AdWhirlAdapter;
import com.adwhirl.obj.Custom;
import com.adwhirl.obj.Extra;
import com.adwhirl.obj.Ration;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class AdWhirlLayout extends RelativeLayout
{
  public static final String ADWHIRL_KEY = "ADWHIRL_KEY";
  public Ration activeRation;
  public WeakReference<Activity> activityReference;
  public AdWhirlInterface adWhirlInterface;
  public AdWhirlManager adWhirlManager;
  private AdWhirlAdapter currentAdapter;
  public Custom custom;
  public Extra extra;
  public final Handler handler = new Handler();
  private boolean hasWindow;
  private boolean isScheduled;
  private String keyAdWhirl;
  private int maxHeight;
  private int maxWidth;
  public Ration nextRation;
  private AdWhirlAdapter previousAdapter;
  public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  public WeakReference<RelativeLayout> superViewReference;

  public AdWhirlLayout(Activity paramActivity, String paramString)
  {
    super(paramActivity);
    init(paramActivity, paramString);
  }

  public AdWhirlLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    String str = getAdWhirlKey(paramContext);
    init((Activity)paramContext, str);
  }

  private void countClick()
  {
    if (this.activeRation != null)
    {
      Object[] arrayOfObject = new Object[6];
      arrayOfObject[0] = this.adWhirlManager.keyAdWhirl;
      arrayOfObject[1] = this.activeRation.nid;
      arrayOfObject[2] = Integer.valueOf(this.activeRation.type);
      arrayOfObject[3] = this.adWhirlManager.deviceIDHash;
      arrayOfObject[4] = this.adWhirlManager.localeString;
      arrayOfObject[5] = Integer.valueOf(311);
      String str = String.format("http://met.adwhirl.com/exclick.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%d&client=2", arrayOfObject);
      this.scheduler.schedule(new PingUrlRunnable(str), 0L, TimeUnit.SECONDS);
    }
  }

  private void countImpression()
  {
    if (this.activeRation != null)
    {
      Object[] arrayOfObject = new Object[6];
      arrayOfObject[0] = this.adWhirlManager.keyAdWhirl;
      arrayOfObject[1] = this.activeRation.nid;
      arrayOfObject[2] = Integer.valueOf(this.activeRation.type);
      arrayOfObject[3] = this.adWhirlManager.deviceIDHash;
      arrayOfObject[4] = this.adWhirlManager.localeString;
      arrayOfObject[5] = Integer.valueOf(311);
      String str = String.format("http://met.adwhirl.com/exmet.php?appid=%s&nid=%s&type=%d&uuid=%s&country_code=%s&appver=%d&client=2", arrayOfObject);
      this.scheduler.schedule(new PingUrlRunnable(str), 0L, TimeUnit.SECONDS);
    }
  }

  private void handleAd()
  {
    if (this.nextRation == null)
    {
      track("nextRation is null!");
      rotateThreadedDelayed();
      return;
    }
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = this.nextRation.nid;
    arrayOfObject[1] = this.nextRation.name;
    arrayOfObject[2] = Integer.valueOf(this.nextRation.type);
    arrayOfObject[3] = this.nextRation.key;
    arrayOfObject[4] = this.nextRation.key2;
    track(String.format("Showing ad:\n\tnid: %s\n\tname: %s\n\ttype: %d\n\tkey: %s\n\tkey2: %s", arrayOfObject));
    try
    {
      if (this.previousAdapter != null)
        this.previousAdapter.willDestroy();
      this.previousAdapter = this.currentAdapter;
      this.currentAdapter = AdWhirlAdapter.handle(this, this.nextRation);
      return;
    }
    catch (Throwable localThrowable)
    {
      track("Caught an exception in adapter:", localThrowable);
      rollover();
    }
  }

  private void rotateAd()
  {
    if (!this.hasWindow)
    {
      this.isScheduled = false;
      return;
    }
    track("Rotating Ad");
    this.nextRation = this.adWhirlManager.getRation();
    this.handler.post(new HandleAdRunnable(this));
  }

  protected static void track(String paramString)
  {
    Track.it(paramString, new String[] { "AdWhirl" });
  }

  protected static void track(String paramString, Throwable paramThrowable)
  {
    Track.it(paramString, new String[] { "AdWhirl" });
    Track.it(paramThrowable, new String[] { "AdWhirl" });
  }

  public void dispatchWindowFocusChanged(boolean paramBoolean)
  {
    try
    {
      super.dispatchWindowFocusChanged(paramBoolean);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Track.it(localNullPointerException);
    }
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    try
    {
      boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
      return bool;
    }
    catch (NullPointerException localNullPointerException)
    {
      Track.it(localNullPointerException);
    }
    return false;
  }

  protected String getAdWhirlKey(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    String str2 = paramContext.getClass().getName();
    PackageManager localPackageManager = paramContext.getPackageManager();
    while (true)
    {
      String str4;
      try
      {
        Bundle localBundle1 = localPackageManager.getActivityInfo(new ComponentName(str1, str2), 128).metaData;
        if (localBundle1 != null)
        {
          String str3 = localBundle1.getString("ADWHIRL_KEY");
          str4 = str3;
          return str4;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        return null;
      }
      try
      {
        Bundle localBundle2 = localPackageManager.getApplicationInfo(str1, 128).metaData;
        str4 = null;
        if (localBundle2 != null)
        {
          String str5 = localBundle2.getString("ADWHIRL_KEY");
          return str5;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
      }
    }
    return null;
  }

  protected void init(Activity paramActivity, String paramString)
  {
    this.activityReference = new WeakReference(paramActivity);
    this.superViewReference = new WeakReference(this);
    this.keyAdWhirl = paramString;
    this.hasWindow = true;
    this.isScheduled = true;
    this.scheduler.schedule(new InitRunnable(this, paramString), 0L, TimeUnit.SECONDS);
    setHorizontalScrollBarEnabled(false);
    setVerticalScrollBarEnabled(false);
    this.maxWidth = 0;
    this.maxHeight = 0;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    }
    while (true)
    {
      return false;
      track("Intercepted ACTION_DOWN event");
      if (this.activeRation != null)
      {
        countClick();
        if (this.activeRation.type == 9)
          if ((this.custom != null) && (this.custom.link != null))
          {
            Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.custom.link));
            localIntent.addFlags(268435456);
            try
            {
              if (this.activityReference != null)
              {
                Activity localActivity = (Activity)this.activityReference.get();
                if (localActivity != null)
                {
                  localActivity.startActivity(localIntent);
                  return false;
                }
              }
            }
            catch (Exception localException)
            {
              track("Could not handle click to " + this.custom.link, localException);
              return false;
            }
          }
      }
    }
    track("In onInterceptTouchEvent(), but custom or custom.link is null");
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Track.it(localNullPointerException);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    if ((this.maxWidth > 0) && (i > this.maxWidth))
      paramInt1 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, -2147483648);
    if ((this.maxHeight > 0) && (j > this.maxHeight))
      paramInt2 = View.MeasureSpec.makeMeasureSpec(this.maxHeight, -2147483648);
    super.onMeasure(paramInt1, paramInt2);
  }

  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    try
    {
      boolean bool = super.onRequestFocusInDescendants(paramInt, paramRect);
      return bool;
    }
    catch (NullPointerException localNullPointerException)
    {
      Track.it(localNullPointerException);
    }
    return false;
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    if (paramInt == 0)
    {
      this.hasWindow = true;
      if (!this.isScheduled)
      {
        this.isScheduled = true;
        if (this.extra != null)
          rotateThreadedNow();
      }
      else
      {
        return;
      }
      this.scheduler.schedule(new InitRunnable(this, this.keyAdWhirl), 0L, TimeUnit.SECONDS);
      return;
    }
    this.hasWindow = false;
  }

  public void pushSubView(ViewGroup paramViewGroup)
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)this.superViewReference.get();
    if (localRelativeLayout == null)
      return;
    localRelativeLayout.removeAllViews();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13);
    try
    {
      localRelativeLayout.addView(paramViewGroup, localLayoutParams);
      track("Added subview");
      this.activeRation = this.nextRation;
      countImpression();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
      {
        Track.it(localNullPointerException);
        try
        {
          localRelativeLayout.removeAllViews();
        }
        catch (Exception localException)
        {
          Track.it(localException);
        }
      }
    }
  }

  public void rollover()
  {
    this.nextRation = this.adWhirlManager.getRollover();
    this.handler.post(new HandleAdRunnable(this));
  }

  public void rotateThreadedDelayed()
  {
    track("Will call rotateAd() in " + this.extra.cycleTime + " seconds");
    this.scheduler.schedule(new RotateAdRunnable(this), this.extra.cycleTime, TimeUnit.SECONDS);
  }

  public void rotateThreadedNow()
  {
    this.scheduler.schedule(new RotateAdRunnable(this), 0L, TimeUnit.SECONDS);
  }

  public void setAdWhirlInterface(AdWhirlInterface paramAdWhirlInterface)
  {
    this.adWhirlInterface = paramAdWhirlInterface;
  }

  public void setMaxHeight(int paramInt)
  {
    this.maxHeight = paramInt;
  }

  public void setMaxWidth(int paramInt)
  {
    this.maxWidth = paramInt;
  }

  public static abstract interface AdWhirlInterface
  {
    public abstract void adWhirlGeneric();
  }

  private static class HandleAdRunnable
    implements Runnable
  {
    private WeakReference<AdWhirlLayout> adWhirlLayoutReference;

    public HandleAdRunnable(AdWhirlLayout paramAdWhirlLayout)
    {
      this.adWhirlLayoutReference = new WeakReference(paramAdWhirlLayout);
    }

    public void run()
    {
      AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
      if (localAdWhirlLayout != null)
        localAdWhirlLayout.handleAd();
    }
  }

  private static class InitRunnable
    implements Runnable
  {
    private WeakReference<AdWhirlLayout> adWhirlLayoutReference;
    private String keyAdWhirl;

    public InitRunnable(AdWhirlLayout paramAdWhirlLayout, String paramString)
    {
      this.adWhirlLayoutReference = new WeakReference(paramAdWhirlLayout);
      this.keyAdWhirl = paramString;
    }

    public void run()
    {
      AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
      Activity localActivity;
      if (localAdWhirlLayout != null)
      {
        localActivity = (Activity)localAdWhirlLayout.activityReference.get();
        if (localActivity != null);
      }
      else
      {
        return;
      }
      if (localAdWhirlLayout.adWhirlManager == null)
        localAdWhirlLayout.adWhirlManager = new AdWhirlManager(new WeakReference(localActivity.getApplicationContext()), this.keyAdWhirl);
      if (!localAdWhirlLayout.hasWindow)
      {
        localAdWhirlLayout.isScheduled = false;
        return;
      }
      localAdWhirlLayout.adWhirlManager.fetchConfig();
      localAdWhirlLayout.extra = localAdWhirlLayout.adWhirlManager.getExtra();
      if (localAdWhirlLayout.extra == null)
      {
        localAdWhirlLayout.scheduler.schedule(this, 30L, TimeUnit.SECONDS);
        return;
      }
      localAdWhirlLayout.rotateAd();
    }
  }

  private static class PingUrlRunnable
    implements Runnable
  {
    private String url;

    public PingUrlRunnable(String paramString)
    {
      this.url = paramString;
    }

    public void run()
    {
      AdWhirlLayout.track("Pinging URL: " + this.url);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      HttpGet localHttpGet = new HttpGet(this.url);
      try
      {
        localDefaultHttpClient.execute(localHttpGet);
        return;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        AdWhirlLayout.track("Caught ClientProtocolException in PingUrlRunnable", localClientProtocolException);
        return;
      }
      catch (IOException localIOException)
      {
        AdWhirlLayout.track("Caught IOException in PingUrlRunnable", localIOException);
      }
    }
  }

  private static class RotateAdRunnable
    implements Runnable
  {
    private WeakReference<AdWhirlLayout> adWhirlLayoutReference;

    public RotateAdRunnable(AdWhirlLayout paramAdWhirlLayout)
    {
      this.adWhirlLayoutReference = new WeakReference(paramAdWhirlLayout);
    }

    public void run()
    {
      AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
      if (localAdWhirlLayout != null)
        localAdWhirlLayout.rotateAd();
    }
  }

  public static class ViewAdRunnable
    implements Runnable
  {
    private WeakReference<AdWhirlLayout> adWhirlLayoutReference;
    private ViewGroup nextView;

    public ViewAdRunnable(AdWhirlLayout paramAdWhirlLayout, ViewGroup paramViewGroup)
    {
      this.adWhirlLayoutReference = new WeakReference(paramAdWhirlLayout);
      this.nextView = paramViewGroup;
    }

    public void run()
    {
      AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
      if (localAdWhirlLayout != null)
        localAdWhirlLayout.pushSubView(this.nextView);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.AdWhirlLayout
 * JD-Core Version:    0.6.2
 */