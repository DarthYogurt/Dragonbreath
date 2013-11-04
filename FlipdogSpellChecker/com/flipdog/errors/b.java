package com.flipdog.errors;

import com.flipdog.commons.a.ay;
import com.flipdog.commons.diagnostic.Track;

public class b
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

  public static void a()
  {
    Thread.setDefaultUncaughtExceptionHandler(new b());
  }

  private static ay b()
  {
    return ay.a();
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      a.a(paramThread, paramThrowable, b().a);
      this.a.uncaughtException(paramThread, paramThrowable);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Track.it(localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.b
 * JD-Core Version:    0.6.2
 */