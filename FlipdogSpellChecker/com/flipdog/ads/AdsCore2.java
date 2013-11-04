package com.flipdog.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.AdWhirlTargeting.TargetingGender;
import com.flipdog.ads.c.d;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.i.b;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.mediation.millennial.MillennialAdapterExtras;
import com.millennialmedia.android.MMSDK;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdsCore2
{
  private static boolean configured = false;

  public static void append(Activity paramActivity, ViewGroup paramViewGroup)
  {
    if (!configured)
      configure();
    if (!g.a)
      return;
    o.a();
    appendAdView(paramActivity, paramViewGroup);
  }

  private static void appendAdView(Activity paramActivity, ViewGroup paramViewGroup)
  {
    AdView localAdView = new AdView(paramActivity, AdSize.BANNER, g.h.a);
    paramViewGroup.addView(localAdView, new RelativeLayout.LayoutParams(-1, (int)(52.0F * getDensity(paramActivity))));
    paramViewGroup.invalidate();
    setAdListener(localAdView);
    AdRequest localAdRequest = createRequest(paramActivity);
    localAdView.loadAd(localAdRequest);
    localAdRequest.setTesting(false);
  }

  private static void configure()
  {
    if (!g.b)
      ((d)b.a(d.class)).a();
    configured = true;
  }

  private static AdRequest createRequest(Context paramContext)
  {
    AdRequest localAdRequest = new AdRequest();
    setTargeting(localAdRequest, paramContext);
    return localAdRequest;
  }

  private static float getDensity(Activity paramActivity)
  {
    return paramActivity.getResources().getDisplayMetrics().density;
  }

  public static void onCreateApplication(Application paramApplication)
  {
    MMSDK.initialize(paramApplication);
  }

  private static Location queryLocation(Context paramContext)
  {
    LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
    Location localLocation = localLocationManager.getLastKnownLocation("gps");
    Iterator localIterator;
    if (localLocation == null)
      localIterator = localLocationManager.getAllProviders().iterator();
    do
    {
      if (!localIterator.hasNext())
        return localLocation;
      localLocation = localLocationManager.getLastKnownLocation((String)localIterator.next());
    }
    while (localLocation == null);
    return localLocation;
  }

  private static void setAdListener(AdView paramAdView)
  {
    paramAdView.setAdListener(new a());
  }

  private static void setLocation(AdRequest paramAdRequest, Context paramContext)
  {
    Location localLocation = AdWhirlTargeting.getLocation();
    if (localLocation != null)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Double.valueOf(localLocation.getLatitude());
      arrayOfObject[1] = Double.valueOf(localLocation.getLongitude());
      track("adView.updateUserLocation(location), latitude = %s, longitude = %s", arrayOfObject);
    }
    while (true)
    {
      paramAdRequest.setLocation(localLocation);
      return;
      track("adView.updateUserLocation(null)", new Object[0]);
    }
  }

  private static void setTargeting(AdRequest paramAdRequest, Context paramContext)
  {
    setLocation(paramAdRequest, paramContext);
    AdWhirlTargeting.TargetingGender localTargetingGender = AdWhirlTargeting.getGender();
    if (localTargetingGender == AdWhirlTargeting.TargetingGender.MALE)
      paramAdRequest.setGender(AdRequest.Gender.MALE);
    while (true)
    {
      GregorianCalendar localGregorianCalendar = AdWhirlTargeting.getBirthDate();
      if (localGregorianCalendar != null)
        paramAdRequest.setBirthday(localGregorianCalendar);
      Set localSet = AdWhirlTargeting.getKeywordSet();
      if (!as.c(localSet))
        paramAdRequest.setKeywords(localSet);
      MillennialAdapterExtras localMillennialAdapterExtras = new MillennialAdapterExtras();
      String str = AdWhirlTargeting.getPostalCode();
      if (!TextUtils.isEmpty(str))
        localMillennialAdapterExtras.setPostalCode(str);
      paramAdRequest.setNetworkExtras(localMillennialAdapterExtras);
      return;
      if (localTargetingGender == AdWhirlTargeting.TargetingGender.FEMALE)
        paramAdRequest.setGender(AdRequest.Gender.FEMALE);
    }
  }

  private static void track(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("Ads"))
      return;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = String.format(paramString, paramArrayOfObject);
    Track.me("Ads", "[AdsCore2] %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.AdsCore2
 * JD-Core Version:    0.6.2
 */