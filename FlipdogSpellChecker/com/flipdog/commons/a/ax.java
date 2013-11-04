package com.flipdog.commons.a;

import java.util.Iterator;
import java.util.regex.Pattern;

public class ax
{
  public static final int a = -1;

  public static int a(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null))
      paramInt = -1;
    int i;
    do
    {
      return paramInt;
      if (paramInt < 0)
        paramInt = 0;
      i = 1 + (paramString1.length() - paramString2.length());
      if (paramInt > i)
        return -1;
    }
    while (paramString2.length() == 0);
    for (int j = paramInt; ; j++)
    {
      if (j >= i)
        return -1;
      if (paramString1.regionMatches(true, j, paramString2, 0, paramString2.length()))
        return j;
    }
  }

  public static <T> String a(Iterable<T> paramIterable, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramIterable.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      Object localObject = (Object)localIterator.next();
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(localObject);
    }
  }

  public static String a(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    return paramString.substring(0, Math.min(paramInt, paramString.length()));
  }

  public static String a(String paramString, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    if (paramString == null)
      return null;
    return paramString.replace(paramCharSequence1, paramCharSequence2);
  }

  public static String a(StringBuilder paramStringBuilder, int paramInt)
  {
    if (paramStringBuilder == null)
      return null;
    return paramStringBuilder.substring(0, Math.min(paramInt, paramStringBuilder.length()));
  }

  public static String a(int[] paramArrayOfInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfInt.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuilder.toString();
      int k = paramArrayOfInt[j];
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(k);
    }
  }

  public static String a(long[] paramArrayOfLong, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfLong.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuilder.toString();
      long l = paramArrayOfLong[j];
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(l);
    }
  }

  public static <T> String a(T[] paramArrayOfT, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfT.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuilder.toString();
      T ? = paramArrayOfT[j];
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(?);
    }
  }

  public static boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.length() == 0);
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean a(String paramString, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      if (c(paramString, paramArrayOfString[j]))
        return true;
    }
  }

  public static String[] a(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return new String[0];
    return paramString1.split(Pattern.quote(paramString2));
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.toLowerCase();
  }

  public static <T> String b(T[] paramArrayOfT, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfT.length;
    int j = 0;
    if (j >= i)
      return localStringBuilder.toString();
    T ? = paramArrayOfT[j];
    if (? == null);
    while (true)
    {
      j++;
      break;
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(?);
    }
  }

  public static boolean b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    return paramString1.equalsIgnoreCase(paramString2);
  }

  public static boolean b(String paramString, String[] paramArrayOfString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
        if (paramString.startsWith(paramArrayOfString[j]))
          return true;
    }
  }

  public static String c(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.toUpperCase();
  }

  public static boolean c(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    return paramString1.equals(paramString2);
  }

  public static String d(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.trim();
  }

  public static boolean d(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    return paramString1.endsWith(paramString2);
  }

  public static boolean e(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    return paramString1.contains(paramString2);
  }

  public static char[] e(String paramString)
  {
    if (paramString == null)
      return new char[0];
    return paramString.toCharArray();
  }

  public static String f(String paramString)
  {
    return paramString.replace(' ', ' ');
  }

  public static boolean f(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    while (true)
    {
      return false;
      int i = paramString2.length();
      int j = paramString1.length() - i;
      for (int k = 0; k <= j; k++)
        if (paramString1.regionMatches(true, k, paramString2, 0, i))
          return true;
    }
  }

  public static int g(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, 0);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ax
 * JD-Core Version:    0.6.2
 */