package com.flipdog.commons.g;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class d
{
  private Timer a = new Timer();
  private boolean b = false;
  private Runnable c;
  private TimerTask d;

  public d(Runnable paramRunnable)
  {
    this.c = paramRunnable;
    this.d = new a(this);
  }

  private void b()
  {
    try
    {
      boolean bool = this.b;
      if (bool);
      while (true)
      {
        return;
        this.c.run();
      }
    }
    finally
    {
    }
  }

  public void a()
  {
    try
    {
      this.a.cancel();
      this.b = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(long paramLong1, long paramLong2)
  {
    this.a.schedule(this.d, paramLong1, paramLong2);
  }

  public void a(Date paramDate)
  {
    this.a.schedule(this.d, paramDate);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.d
 * JD-Core Version:    0.6.2
 */