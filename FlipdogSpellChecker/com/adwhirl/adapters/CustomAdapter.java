package com.adwhirl.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Handler;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.obj.Custom;
import com.adwhirl.obj.Extra;
import com.adwhirl.obj.Ration;
import com.adwhirl.util.AdWhirlUtil;
import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CustomAdapter extends AdWhirlAdapter
{
  public CustomAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    super(paramAdWhirlLayout, paramRation);
  }

  public void displayCustom()
  {
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null);
    Activity localActivity;
    do
    {
      return;
      localActivity = (Activity)localAdWhirlLayout.activityReference.get();
    }
    while (localActivity == null);
    switch (localAdWhirlLayout.custom.type)
    {
    default:
      track("Unknown custom type!");
      localAdWhirlLayout.rotateThreadedNow();
      return;
    case 1:
      track("Serving custom type: banner");
      RelativeLayout localRelativeLayout2 = new RelativeLayout(localActivity);
      if (localAdWhirlLayout.custom.image == null)
      {
        localAdWhirlLayout.rotateThreadedNow();
        return;
      }
      ImageView localImageView4 = new ImageView(localActivity);
      localImageView4.setImageDrawable(localAdWhirlLayout.custom.image);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams4.addRule(13);
      localRelativeLayout2.addView(localImageView4, localLayoutParams4);
      localAdWhirlLayout.pushSubView(localRelativeLayout2);
    case 2:
    }
    while (true)
    {
      localAdWhirlLayout.adWhirlManager.resetRollover();
      localAdWhirlLayout.rotateThreadedDelayed();
      return;
      track("Serving custom type: icon");
      RelativeLayout localRelativeLayout1 = new RelativeLayout(localActivity);
      if (localAdWhirlLayout.custom.image == null)
      {
        localAdWhirlLayout.rotateThreadedNow();
        return;
      }
      double d1 = AdWhirlUtil.getDensity(localActivity);
      double d2 = AdWhirlUtil.convertToScreenPixels(320, d1);
      double d3 = AdWhirlUtil.convertToScreenPixels(50, d1);
      double d4 = AdWhirlUtil.convertToScreenPixels(4, d1);
      double d5 = AdWhirlUtil.convertToScreenPixels(6, d1);
      localRelativeLayout1.setLayoutParams(new FrameLayout.LayoutParams((int)d2, (int)d3));
      ImageView localImageView1 = new ImageView(localActivity);
      int i = Color.rgb(localAdWhirlLayout.extra.bgRed, localAdWhirlLayout.extra.bgGreen, localAdWhirlLayout.extra.bgBlue);
      localImageView1.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] { -1, i, i, i }));
      localRelativeLayout1.addView(localImageView1, new RelativeLayout.LayoutParams(-1, -1));
      ImageView localImageView2 = new ImageView(localActivity);
      localImageView2.setImageDrawable(localAdWhirlLayout.custom.image);
      localImageView2.setId(10);
      localImageView2.setPadding((int)d4, 0, (int)d5, 0);
      localImageView2.setScaleType(ImageView.ScaleType.CENTER);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -1);
      localRelativeLayout1.addView(localImageView2, localLayoutParams1);
      ImageView localImageView3 = new ImageView(localActivity);
      localImageView3.setImageDrawable(new BitmapDrawable(getClass().getResourceAsStream("/com/adwhirl/assets/ad_frame.gif")));
      localImageView3.setPadding((int)d4, 0, (int)d5, 0);
      localImageView3.setScaleType(ImageView.ScaleType.CENTER);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
      localRelativeLayout1.addView(localImageView3, localLayoutParams2);
      TextView localTextView = new TextView(localActivity);
      localTextView.setText(localAdWhirlLayout.custom.description);
      localTextView.setTypeface(Typeface.DEFAULT_BOLD, 1);
      localTextView.setTextColor(Color.rgb(localAdWhirlLayout.extra.fgRed, localAdWhirlLayout.extra.fgGreen, localAdWhirlLayout.extra.fgBlue));
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
      localLayoutParams3.addRule(1, localImageView2.getId());
      localLayoutParams3.addRule(10);
      localLayoutParams3.addRule(12);
      localLayoutParams3.addRule(15);
      localLayoutParams3.addRule(13);
      localTextView.setGravity(16);
      localRelativeLayout1.addView(localTextView, localLayoutParams3);
      localAdWhirlLayout.pushSubView(localRelativeLayout1);
    }
  }

  public void handle()
  {
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    localAdWhirlLayout.scheduler.schedule(new FetchCustomRunnable(this), 0L, TimeUnit.SECONDS);
  }

  private static class DisplayCustomRunnable
    implements Runnable
  {
    private CustomAdapter customAdapter;

    public DisplayCustomRunnable(CustomAdapter paramCustomAdapter)
    {
      this.customAdapter = paramCustomAdapter;
    }

    public void run()
    {
      this.customAdapter.displayCustom();
    }
  }

  private static class FetchCustomRunnable
    implements Runnable
  {
    private CustomAdapter customAdapter;

    public FetchCustomRunnable(CustomAdapter paramCustomAdapter)
    {
      this.customAdapter = paramCustomAdapter;
    }

    public void run()
    {
      AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.customAdapter.adWhirlLayoutReference.get();
      if (localAdWhirlLayout == null)
        return;
      localAdWhirlLayout.custom = localAdWhirlLayout.adWhirlManager.getCustom(this.customAdapter.ration.nid);
      if (localAdWhirlLayout.custom == null)
      {
        localAdWhirlLayout.rotateThreadedNow();
        return;
      }
      localAdWhirlLayout.handler.post(new CustomAdapter.DisplayCustomRunnable(this.customAdapter));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.CustomAdapter
 * JD-Core Version:    0.6.2
 */