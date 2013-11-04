package com.flipdog.ads;

import android.content.Context;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;

public class MMHandler2
{
  private void track(String paramString, Object[] paramArrayOfObject)
  {
    MMUtils2.track(paramString, paramArrayOfObject);
  }

  public void handle(Context paramContext, String paramString, CustomEventBannerListener paramCustomEventBannerListener)
  {
    track("adView = new MMAdView(context)", new Object[0]);
    MMAdView localMMAdView = new MMAdView(paramContext);
    localMMAdView.setApid(paramString);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(MMSDK.getDefaultAdId());
    track("adView.setId(%s)", arrayOfObject);
    localMMAdView.setId(MMSDK.getDefaultAdId());
    track("adView.setHorizontalScrollBarEnabled(false)", new Object[0]);
    localMMAdView.setHorizontalScrollBarEnabled(false);
    track("adView.setVerticalScrollBarEnabled(false)", new Object[0]);
    localMMAdView.setVerticalScrollBarEnabled(false);
    MMUtils2.setLocation();
    MMRequest localMMRequest = new MMRequest();
    localMMRequest.setMetaValues(MMUtils2.createMetaData());
    localMMAdView.setMMRequest(localMMRequest);
    track("adView.setListener(this)", new Object[0]);
    localMMAdView.setListener(MMUtils2.createListener(localMMAdView, paramCustomEventBannerListener));
    track("adView.getAd()", new Object[0]);
    localMMAdView.getAd();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.MMHandler2
 * JD-Core Version:    0.6.2
 */