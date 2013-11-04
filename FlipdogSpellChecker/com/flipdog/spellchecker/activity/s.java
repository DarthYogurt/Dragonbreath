package com.flipdog.spellchecker.activity;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;

class s
  implements AdListener
{
  public void onDismissScreen(Ad paramAd)
  {
    f.a("onDismissScreen", new Object[0]);
  }

  public void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode)
  {
    f.a("onFailedToReceiveAd: %s", new Object[] { paramErrorCode });
  }

  public void onLeaveApplication(Ad paramAd)
  {
    f.a("onLeaveApplication", new Object[0]);
  }

  public void onPresentScreen(Ad paramAd)
  {
    f.a("onPresentScreen", new Object[0]);
  }

  public void onReceiveAd(Ad paramAd)
  {
    f.a("onReceivedAd", new Object[0]);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.s
 * JD-Core Version:    0.6.2
 */