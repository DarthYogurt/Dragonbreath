package com.yoc.android.yocperformance.adsdk;

import java.util.concurrent.ThreadFactory;

class p
  implements ThreadFactory
{
  p(t paramt)
  {
  }

  public Thread newThread(Runnable paramRunnable)
  {
    return new Thread(paramRunnable, "AdService Thread");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.p
 * JD-Core Version:    0.6.2
 */