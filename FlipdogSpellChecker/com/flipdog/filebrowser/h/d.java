package com.flipdog.filebrowser.h;

import com.flipdog.commons.diagnostic.Track;
import java.util.concurrent.Semaphore;

public class d
{
  private Semaphore a = new Semaphore(1);

  protected void f()
  {
    try
    {
      this.a.acquire();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      Track.it(localInterruptedException);
    }
  }

  protected void g()
  {
    this.a.release();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.h.d
 * JD-Core Version:    0.6.2
 */