package com.flipdog.ads;

import android.app.Activity;
import android.os.Handler;
import com.adfonic.android.AdfonicView;
import com.adfonic.android.api.Request;
import com.adfonic.android.api.Request.RequestBuilder;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.ViewAdRunnable;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlTargeting.TargetingGender;
import com.flipdog.commons.diagnostic.Track;

public class q
{
  private static Request a(String paramString)
  {
    Request.RequestBuilder localRequestBuilder = new Request.RequestBuilder();
    localRequestBuilder.withSlotId(paramString);
    localRequestBuilder.withAllowLocation(true);
    int i = AdWhirlTargeting.getAge();
    if (i != -1)
      localRequestBuilder.withAge(i);
    AdWhirlTargeting.TargetingGender localTargetingGender = AdWhirlTargeting.getGender();
    if (localTargetingGender == AdWhirlTargeting.TargetingGender.MALE)
      localRequestBuilder.withIsMale(true);
    while (true)
    {
      localRequestBuilder.withRefreshAd(false);
      return localRequestBuilder.build();
      if (localTargetingGender == AdWhirlTargeting.TargetingGender.FEMALE)
        localRequestBuilder.withIsMale(false);
    }
  }

  public static void a(Activity paramActivity, AdWhirlLayout paramAdWhirlLayout, String paramString)
  {
    b("AdFonicCustomEvent", new Object[0]);
    AdfonicView localAdfonicView = new AdfonicView(paramActivity);
    localAdfonicView.setAdListener(new n(paramAdWhirlLayout));
    localAdfonicView.setRequest(a(g.f.a));
    paramAdWhirlLayout.handler.post(new AdWhirlLayout.ViewAdRunnable(paramAdWhirlLayout, localAdfonicView));
  }

  private static void b(String paramString, Object[] paramArrayOfObject)
  {
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Ads" });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.q
 * JD-Core Version:    0.6.2
 */