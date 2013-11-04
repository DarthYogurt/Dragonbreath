package com.flipdog.ads;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;

class a
  implements AdListener
{
  public void onDismissScreen(Ad paramAd)
  {
    AdsCore2.access$0("onDismissScreen", new Object[0]);
  }

  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode)
  {
    AdsCore2.access$0("onFailedToReceiveAd: %s", new Object[] { paramErrorCode });
  }

  public void onLeaveApplication(Ad paramAd)
  {
    AdsCore2.access$0("onLeaveApplication", new Object[0]);
  }

  public void onPresentScreen(Ad paramAd)
  {
    AdsCore2.access$0("onPresentScreen", new Object[0]);
  }

  public void onReceiveAd(Ad paramAd)
  {
    AdsCore2.access$0("onReceivedAd", new Object[0]);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.a
 * JD-Core Version:    0.6.2
 */