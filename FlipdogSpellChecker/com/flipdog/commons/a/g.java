package com.flipdog.commons.a;

import java.util.Date;

public class g
{
  public static int a(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 == paramFloat2)
      return 0;
    if (paramFloat1 > paramFloat2)
      return 1;
    return -1;
  }

  public static int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2)
      return 0;
    if (paramInt1 > paramInt2)
      return 1;
    return -1;
  }

  public static int a(long paramLong1, long paramLong2)
  {
    if (paramLong1 == paramLong2)
      return 0;
    if (paramLong1 > paramLong2)
      return 1;
    return -1;
  }

  public static int a(String paramString1, String paramString2)
  {
    return paramString1.compareTo(paramString2);
  }

  public static int a(Date paramDate1, Date paramDate2)
  {
    return a(paramDate1.getTime(), paramDate2.getTime());
  }

  public static int b(float paramFloat1, float paramFloat2)
  {
    return -1 * a(paramFloat1, paramFloat2);
  }

  public static int b(int paramInt1, int paramInt2)
  {
    return -1 * a(paramInt1, paramInt2);
  }

  public static int b(long paramLong1, long paramLong2)
  {
    return -1 * a(paramLong1, paramLong2);
  }

  public static int b(String paramString1, String paramString2)
  {
    return -1 * a(paramString1, paramString2);
  }

  public static int b(Date paramDate1, Date paramDate2)
  {
    return b(paramDate1.getTime(), paramDate2.getTime());
  }

  public static int c(float paramFloat1, float paramFloat2)
  {
    return b(paramFloat1, paramFloat2);
  }

  public static int c(long paramLong1, long paramLong2)
  {
    return a(paramLong1, paramLong2);
  }

  public static int c(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2);
  }

  public static int d(float paramFloat1, float paramFloat2)
  {
    return a(paramFloat1, paramFloat2);
  }

  public static int d(long paramLong1, long paramLong2)
  {
    return b(paramLong1, paramLong2);
  }

  public static int d(String paramString1, String paramString2)
  {
    return b(paramString1, paramString2);
  }

  public static int e(long paramLong1, long paramLong2)
  {
    return a(paramLong1, paramLong2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.g
 * JD-Core Version:    0.6.2
 */