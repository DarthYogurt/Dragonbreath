package com.google.inject.internal;

import java.util.logging.Logger;

public class Stopwatch
{
  private static final Logger logger = Logger.getLogger(Stopwatch.class.getName());
  private long start = System.currentTimeMillis();

  public long reset()
  {
    long l1 = System.currentTimeMillis();
    try
    {
      long l2 = this.start;
      long l3 = l1 - l2;
      return l3;
    }
    finally
    {
      this.start = l1;
    }
  }

  public void resetAndLog(String paramString)
  {
    logger.fine(paramString + ": " + reset() + "ms");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Stopwatch
 * JD-Core Version:    0.6.2
 */