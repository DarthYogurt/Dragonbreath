package com.flipdog.ads;

import android.app.Activity;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBanner;
import com.google.ads.mediation.customevent.CustomEventBannerListener;

public class CustomEvents
  implements CustomEventBanner
{
  private static void track(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("Ads"))
      return;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = String.format(paramString, paramArrayOfObject);
    Track.me("Ads", "[CustomEvents] %s", arrayOfObject);
  }

  public void destroy()
  {
  }

  public void requestBannerAd(CustomEventBannerListener paramCustomEventBannerListener, Activity paramActivity, String paramString1, String paramString2, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Object paramObject)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = Integer.valueOf(paramAdSize.getWidth());
    arrayOfObject[3] = Integer.valueOf(paramAdSize.getHeight());
    track("requestBannerAd label = %s, serverParameter = %s, %sx%s", arrayOfObject);
    if (ax.b(paramString1, new String[] { "ev" }))
    {
      MMUtils2.handle(paramActivity, g.d.a, paramCustomEventBannerListener);
      return;
    }
    paramCustomEventBannerListener.onFailedToReceiveAd();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.CustomEvents
 * JD-Core Version:    0.6.2
 */