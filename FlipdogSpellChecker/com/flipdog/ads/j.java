package com.flipdog.ads;

import android.content.Context;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;
import com.yoc.android.yocperformance.adsdk.BannerAdView;

class j extends BannerAdView
{
  j(Context paramContext, AdWhirlLayout paramAdWhirlLayout)
  {
    super(paramContext);
  }

  public void a(Exception paramException)
  {
    super.a(paramException);
    this.a.rollover();
  }

  public void a(String paramString)
  {
    super.a(paramString);
    this.a.adWhirlManager.resetRollover();
    this.a.rotateThreadedDelayed();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.j
 * JD-Core Version:    0.6.2
 */