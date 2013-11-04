package com.flipdog.spellchecker.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class f
{
  public static void a(Activity paramActivity, int paramInt)
  {
    AdView localAdView = new AdView(paramActivity, AdSize.BANNER, "49ec7cf7371a400f");
    ViewGroup localViewGroup = (ViewGroup)as.a(paramActivity, paramInt);
    localViewGroup.addView(localAdView, new RelativeLayout.LayoutParams(-1, (int)(paramActivity.getResources().getDisplayMetrics().density * 52)));
    localViewGroup.invalidate();
    localAdView.setAdListener(new s());
    localAdView.loadAd(new AdRequest());
  }

  private static void b(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("Ads"))
      return;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = String.format(paramString, paramArrayOfObject);
    Track.me("Ads", "[Ads2] %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.f
 * JD-Core Version:    0.6.2
 */