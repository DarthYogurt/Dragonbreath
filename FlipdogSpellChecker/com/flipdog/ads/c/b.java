package com.flipdog.ads.c;

import android.location.Location;
import com.flipdog.commons.diagnostic.Track;

class b
  implements Runnable
{
  b(d paramd)
  {
  }

  public void run()
  {
    try
    {
      d.a(this.a, true);
      Location localLocation = d.a(this.a);
      this.a.a(localLocation);
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.c.b
 * JD-Core Version:    0.6.2
 */