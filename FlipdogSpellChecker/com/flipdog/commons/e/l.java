package com.flipdog.commons.e;

import com.flipdog.activity.f;
import com.flipdog.commons.a.a;
import com.flipdog.commons.diagnostic.Track;

class l
  implements Runnable
{
  l(Exception paramException, f paramf)
  {
  }

  public void run()
  {
    Track.it(this.a);
    a.a(this.b.c(), this.a.getMessage());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.l
 * JD-Core Version:    0.6.2
 */