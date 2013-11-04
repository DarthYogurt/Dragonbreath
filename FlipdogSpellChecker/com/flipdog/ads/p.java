package com.flipdog.ads;

import android.app.Activity;
import android.os.Handler;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.ViewAdRunnable;
import com.flipdog.commons.diagnostic.Track;
import com.yoc.android.yocperformance.adsdk.BannerAdView;

public class p
{
  public static void a(Activity paramActivity, AdWhirlLayout paramAdWhirlLayout, String paramString)
  {
    a("YocCustomEvent", new Object[0]);
    if (paramString == "top");
    for (int i = g.e.a; ; i = g.e.b)
    {
      j localj = new j(paramAdWhirlLayout.getContext(), paramAdWhirlLayout);
      localj.setAdSpaceIdPortrait(Integer.valueOf(i));
      localj.setAdSpaceIdLandscape(Integer.valueOf(i));
      paramAdWhirlLayout.handler.post(new AdWhirlLayout.ViewAdRunnable(paramAdWhirlLayout, localj));
      return;
      if (paramString != "bottom")
        break;
    }
    throw new RuntimeException("Unexpected: " + paramString);
  }

  private static void a(String paramString, Object[] paramArrayOfObject)
  {
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Ads" });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.p
 * JD-Core Version:    0.6.2
 */