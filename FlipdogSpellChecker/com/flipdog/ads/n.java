package com.flipdog.ads;

import com.adfonic.android.AdListener;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlManager;

class n
  implements AdListener
{
  n(AdWhirlLayout paramAdWhirlLayout)
  {
  }

  public void a()
  {
    q.a("onReceivedAd", new Object[0]);
    this.a.adWhirlManager.resetRollover();
    this.a.rotateThreadedDelayed();
  }

  public void b()
  {
    q.a("onPresentScreen", new Object[0]);
  }

  public void c()
  {
    q.a("onNoFill", new Object[0]);
    this.a.rollover();
  }

  public void d()
  {
    q.a("onNetworkError", new Object[0]);
    this.a.rollover();
  }

  public void e()
  {
    q.a("onLeaveApplication", new Object[0]);
  }

  public void f()
  {
    q.a("onInvalidRequest", new Object[0]);
    this.a.rollover();
  }

  public void g()
  {
    q.a("onInternalError", new Object[0]);
    this.a.rollover();
  }

  public void h()
  {
    q.a("onDismissScreen", new Object[0]);
  }

  public void i()
  {
    q.a("onClick", new Object[0]);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.n
 * JD-Core Version:    0.6.2
 */