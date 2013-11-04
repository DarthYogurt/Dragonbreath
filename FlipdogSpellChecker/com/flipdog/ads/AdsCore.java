package com.flipdog.ads;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.adwhirl.AdWhirlLayout;
import com.flipdog.ads.c.d;
import com.flipdog.commons.i.b;
import com.millennialmedia.android.MMSDK;

public class AdsCore
{
  private static boolean configured = false;

  public static void append(Activity paramActivity, ViewGroup paramViewGroup)
  {
    if (!configured)
      configure();
    if (!g.a)
      return;
    o.a();
    appendAdWhirl(paramActivity, paramViewGroup);
  }

  private static void appendAdWhirl(Activity paramActivity, ViewGroup paramViewGroup)
  {
    AdWhirlLayout localAdWhirlLayout = new AdWhirlLayout(paramActivity, g.c.a);
    localAdWhirlLayout.setAdWhirlInterface(new t(paramActivity, localAdWhirlLayout));
    paramViewGroup.addView(localAdWhirlLayout, new RelativeLayout.LayoutParams(-1, (int)(paramActivity.getResources().getDisplayMetrics().density * 52)));
    paramViewGroup.invalidate();
  }

  private static void configure()
  {
    if (!g.b)
      ((d)b.a(d.class)).a();
    configured = true;
  }

  public static void onCreateApplication(Application paramApplication)
  {
    MMSDK.initialize(paramApplication);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.AdsCore
 * JD-Core Version:    0.6.2
 */