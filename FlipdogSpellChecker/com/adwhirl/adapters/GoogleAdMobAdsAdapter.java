package com.adwhirl.adapters;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.ViewAdRunnable;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.adwhirl.obj.Extra;
import com.adwhirl.obj.Ration;
import com.adwhirl.util.AdWhirlUtil;
import com.flipdog.commons.diagnostic.Track;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class GoogleAdMobAdsAdapter extends AdWhirlAdapter
  implements AdListener
{
  private static int sdk = Integer.parseInt(Build.VERSION.SDK);
  private AdView adView;

  public GoogleAdMobAdsAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    super(paramAdWhirlLayout, paramRation);
  }

  protected String birthdayForAdWhirlTargeting()
  {
    if (AdWhirlTargeting.getBirthDate() != null)
      return new SimpleDateFormat("yyyyMMdd").format(AdWhirlTargeting.getBirthDate().getTime());
    return null;
  }

  protected AdRequest.Gender genderForAdWhirlTargeting()
  {
    switch ($SWITCH_TABLE$com$adwhirl$AdWhirlTargeting$TargetingGender()[AdWhirlTargeting.getGender().ordinal()])
    {
    default:
      return null;
    case 2:
      return AdRequest.Gender.MALE;
    case 3:
    }
    return AdRequest.Gender.FEMALE;
  }

  public void handle()
  {
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null);
    Activity localActivity;
    do
    {
      return;
      if (sdk <= 4)
      {
        localAdWhirlLayout.rollover();
        return;
      }
      localActivity = (Activity)localAdWhirlLayout.activityReference.get();
    }
    while (localActivity == null);
    this.adView = new AdView(localActivity, AdSize.BANNER, this.ration.key);
    this.adView.setAdListener(this);
    this.adView.loadAd(requestForAdWhirlLayout(localAdWhirlLayout));
  }

  protected void log(String paramString)
  {
    Log.d("AdWhirl SDK", "GoogleAdapter " + paramString);
  }

  public void onDismissScreen(Ad paramAd)
  {
  }

  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode)
  {
    log("failure (" + paramErrorCode + ")");
    paramAd.setAdListener(null);
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    localAdWhirlLayout.rollover();
  }

  public void onLeaveApplication(Ad paramAd)
  {
  }

  public void onPresentScreen(Ad paramAd)
  {
  }

  public void onReceiveAd(Ad paramAd)
  {
    log("success");
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    if (!(paramAd instanceof AdView))
    {
      log("invalid AdView");
      return;
    }
    AdView localAdView = (AdView)paramAd;
    localAdWhirlLayout.adWhirlManager.resetRollover();
    localAdWhirlLayout.handler.post(new AdWhirlLayout.ViewAdRunnable(localAdWhirlLayout, localAdView));
    localAdWhirlLayout.rotateThreadedDelayed();
  }

  protected AdRequest requestForAdWhirlLayout(AdWhirlLayout paramAdWhirlLayout)
  {
    AdRequest localAdRequest = new AdRequest();
    if (AdWhirlTargeting.getTestMode())
    {
      Activity localActivity = (Activity)paramAdWhirlLayout.activityReference.get();
      if (localActivity != null)
        localAdRequest.addTestDevice(AdWhirlUtil.getEncodedDeviceId(localActivity.getApplicationContext()));
    }
    localAdRequest.setGender(genderForAdWhirlTargeting());
    localAdRequest.setBirthday(birthdayForAdWhirlTargeting());
    if (paramAdWhirlLayout.extra.locationOn == 1)
      localAdRequest.setLocation(paramAdWhirlLayout.adWhirlManager.location);
    localAdRequest.setKeywords(AdWhirlTargeting.getKeywordSet());
    return localAdRequest;
  }

  public void willDestroy()
  {
    log("AdView will get destroyed");
    if (this.adView != null);
    try
    {
      this.adView.destroy();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Track.it(localNullPointerException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.GoogleAdMobAdsAdapter
 * JD-Core Version:    0.6.2
 */