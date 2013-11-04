package com.flipdog.ads;

import android.app.Activity;
import com.adwhirl.AdWhirlLayout;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;
import java.lang.ref.WeakReference;

public class MMHandler
{
  private void track(String paramString, Object[] paramArrayOfObject)
  {
    MMUtils.track(paramString, paramArrayOfObject);
  }

  public void handle(WeakReference<AdWhirlLayout> paramWeakReference, String paramString)
  {
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)paramWeakReference.get();
    if (localAdWhirlLayout == null)
      return;
    track("adView = new MMAdView(context)", new Object[0]);
    MMAdView localMMAdView = new MMAdView((Activity)localAdWhirlLayout.getContext());
    localMMAdView.setApid(paramString);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(MMSDK.getDefaultAdId());
    track("adView.setId(%s)", arrayOfObject);
    localMMAdView.setId(MMSDK.getDefaultAdId());
    track("adView.setHorizontalScrollBarEnabled(false)", new Object[0]);
    localMMAdView.setHorizontalScrollBarEnabled(false);
    track("adView.setVerticalScrollBarEnabled(false)", new Object[0]);
    localMMAdView.setVerticalScrollBarEnabled(false);
    MMUtils.setLocation();
    MMRequest localMMRequest = new MMRequest();
    localMMRequest.setMetaValues(MMUtils.createMetaData());
    localMMAdView.setMMRequest(localMMRequest);
    track("adView.setListener(this)", new Object[0]);
    localMMAdView.setListener(MMUtils.createListener(localAdWhirlLayout, localMMAdView));
    track("adView.getAd()", new Object[0]);
    localMMAdView.getAd();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.MMHandler
 * JD-Core Version:    0.6.2
 */