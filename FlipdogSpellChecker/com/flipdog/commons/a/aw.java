package com.flipdog.commons.a;

import java.text.DecimalFormat;

public class aw
{
  private static final int a = 1024;
  private static final int b = 1048576;

  public static String a(long paramLong)
  {
    if (paramLong < 1024L)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Long.valueOf(paramLong);
      return String.format("%s bytes", arrayOfObject3);
    }
    if (paramLong < 1048576L)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Long.valueOf(paramLong / 1024L);
      return String.format("%s KB", arrayOfObject2);
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Long.valueOf(paramLong / 1048576L);
    return String.format("%s MB", arrayOfObject1);
  }

  public static String b(long paramLong)
  {
    double d = paramLong;
    if (paramLong / 1048576L != 0L)
      return new DecimalFormat("#.## MB").format(d / 1048576.0D);
    if (paramLong / 1024L != 0L)
      return new DecimalFormat("#.## KB").format(d / 1024.0D);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(paramLong);
    return String.format("%s Bytes", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.aw
 * JD-Core Version:    0.6.2
 */