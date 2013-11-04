package com.b;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class b
{
  private static Timer a = new Timer();

  public static void a(TimerTask paramTimerTask, long paramLong)
  {
    a.schedule(paramTimerTask, paramLong);
  }

  public static void a(TimerTask paramTimerTask, long paramLong1, long paramLong2)
  {
    a.schedule(paramTimerTask, paramLong1, paramLong2);
  }

  public static void a(TimerTask paramTimerTask, Date paramDate)
  {
    a.schedule(paramTimerTask, paramDate);
  }

  public static void a(TimerTask paramTimerTask, Date paramDate, long paramLong)
  {
    a.schedule(paramTimerTask, paramDate, paramLong);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b
 * JD-Core Version:    0.6.2
 */