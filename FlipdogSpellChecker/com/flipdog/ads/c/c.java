package com.flipdog.ads.c;

import com.flipdog.commons.diagnostic.Track;

class c
  implements Runnable
{
  c(d paramd)
  {
  }

  public void run()
  {
    try
    {
      this.a.b();
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.c.c
 * JD-Core Version:    0.6.2
 */