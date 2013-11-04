package com.flipdog.ads;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout.LayoutParams;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlTargeting.TargetingGender;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdLayout.AdSize;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.AdTargetingOptions.Gender;
import com.flipdog.commons.diagnostic.Track;
import java.lang.ref.WeakReference;

public class r
{
  private static AdListener a(AdWhirlLayout paramAdWhirlLayout)
  {
    return new b(new WeakReference(paramAdWhirlLayout));
  }

  private static AdTargetingOptions a()
  {
    AdTargetingOptions localAdTargetingOptions = new AdTargetingOptions();
    AdWhirlTargeting.TargetingGender localTargetingGender = AdWhirlTargeting.getGender();
    if (localTargetingGender == AdWhirlTargeting.TargetingGender.MALE)
      localAdTargetingOptions.setGender(AdTargetingOptions.Gender.MALE);
    while (true)
    {
      int i = AdWhirlTargeting.getAge();
      if (i != -1)
        localAdTargetingOptions.setAge(i);
      localAdTargetingOptions.enableGeoLocation(true);
      return localAdTargetingOptions;
      if (localTargetingGender == AdWhirlTargeting.TargetingGender.FEMALE)
        localAdTargetingOptions.setGender(AdTargetingOptions.Gender.FEMALE);
    }
  }

  public static void a(Activity paramActivity, AdWhirlLayout paramAdWhirlLayout)
  {
    b("AmazonCustomEvent", new Object[0]);
    AdRegistration.setAppKey(b(), g.g.a);
    AdLayout localAdLayout = new AdLayout(paramActivity, AdLayout.AdSize.AD_SIZE_320x50);
    paramAdWhirlLayout.addView(localAdLayout, new LinearLayout.LayoutParams(-1, -1));
    AdTargetingOptions localAdTargetingOptions = a();
    localAdLayout.setListener(a(paramAdWhirlLayout));
    localAdLayout.loadAd(localAdTargetingOptions);
  }

  private static Context b()
  {
    return (Context)com.flipdog.commons.i.b.a(Context.class);
  }

  private static void b(String paramString, Object[] paramArrayOfObject)
  {
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Ads" });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.r
 * JD-Core Version:    0.6.2
 */