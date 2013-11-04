package com.android.a.a;

import java.lang.reflect.Array;

public class a
{
  private static Object[] a = new Object[0];
  private static final int b = 73;
  private static Object[] c = new Object[73];

  public static int a(int paramInt)
  {
    for (int i = 4; ; i++)
    {
      if (i >= 32)
        return paramInt;
      if (paramInt <= -12 + (1 << i))
        return -12 + (1 << i);
    }
  }

  public static long a(long[] paramArrayOfLong)
  {
    long l = 0L;
    int i = paramArrayOfLong.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return l;
      l += paramArrayOfLong[j];
    }
  }

  public static boolean a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    if (paramArrayOfByte1 == paramArrayOfByte2);
    while (true)
    {
      return true;
      if ((paramArrayOfByte1 == null) || (paramArrayOfByte2 == null) || (paramArrayOfByte1.length < paramInt) || (paramArrayOfByte2.length < paramInt))
        return false;
      for (int i = 0; i < paramInt; i++)
        if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
          return false;
    }
  }

  public static boolean a(int[] paramArrayOfInt, int paramInt)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      if (paramArrayOfInt[j] == paramInt)
        return true;
    }
  }

  public static <T> boolean a(T[] paramArrayOfT, T paramT)
  {
    boolean bool = true;
    int i = paramArrayOfT.length;
    int j = 0;
    if (j >= i)
      bool = false;
    T ?;
    do
    {
      return bool;
      ? = paramArrayOfT[j];
      if (? != null)
        break;
    }
    while (paramT == null);
    while ((paramT == null) || (!?.equals(paramT)))
    {
      j++;
      break;
    }
    return bool;
  }

  public static <T> T[] a(Class<T> paramClass)
  {
    if (paramClass == Object.class)
      return a;
    int i = (0x7FFFFFFF & System.identityHashCode(paramClass) / 8) % 73;
    Object localObject = c[i];
    if ((localObject == null) || (localObject.getClass().getComponentType() != paramClass))
    {
      localObject = Array.newInstance(paramClass, 0);
      c[i] = localObject;
    }
    return (Object[])localObject;
  }

  public static <T> T[] a(Class<T> paramClass, T[] paramArrayOfT, T paramT)
  {
    int j;
    Object[] arrayOfObject1;
    if (paramArrayOfT != null)
    {
      j = paramArrayOfT.length;
      Object[] arrayOfObject2 = (Object[])Array.newInstance(paramClass, j + 1);
      System.arraycopy(paramArrayOfT, 0, arrayOfObject2, 0, j);
      arrayOfObject1 = arrayOfObject2;
    }
    for (int i = j; ; i = 0)
    {
      arrayOfObject1[i] = paramT;
      return arrayOfObject1;
      arrayOfObject1 = (Object[])Array.newInstance(paramClass, 1);
    }
  }

  public static int b(int paramInt)
  {
    return a(paramInt);
  }

  public static int[] b(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null)
    {
      paramArrayOfInt = new int[1];
      paramArrayOfInt[0] = paramInt;
      return paramArrayOfInt;
    }
    int i = paramArrayOfInt.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        int[] arrayOfInt = new int[i + 1];
        System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, i);
        arrayOfInt[i] = paramInt;
        return arrayOfInt;
      }
      if (paramArrayOfInt[j] == paramInt)
        break;
    }
  }

  public static <T> T[] b(Class<T> paramClass, T[] paramArrayOfT, T paramT)
  {
    int i;
    if (paramArrayOfT != null)
      i = paramArrayOfT.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return paramArrayOfT;
      if (paramArrayOfT[j] == paramT)
      {
        if (i == 1)
          return null;
        Object[] arrayOfObject = (Object[])Array.newInstance(paramClass, i - 1);
        System.arraycopy(paramArrayOfT, 0, arrayOfObject, 0, j);
        System.arraycopy(paramArrayOfT, j + 1, arrayOfObject, j, -1 + (i - j));
        return arrayOfObject;
      }
    }
  }

  public static int c(int paramInt)
  {
    return a(paramInt * 2) / 2;
  }

  public static int[] c(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null)
      paramArrayOfInt = null;
    while (true)
    {
      return paramArrayOfInt;
      int i = paramArrayOfInt.length;
      for (int j = 0; j < i; j++)
        if (paramArrayOfInt[j] == paramInt)
        {
          int[] arrayOfInt = new int[i - 1];
          if (j > 0)
            System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, j);
          if (j < i - 1)
            System.arraycopy(paramArrayOfInt, j + 1, arrayOfInt, j, -1 + (i - j));
          return arrayOfInt;
        }
    }
  }

  public static int d(int paramInt)
  {
    return a(paramInt * 2) / 2;
  }

  public static int e(int paramInt)
  {
    return a(paramInt * 4) / 4;
  }

  public static int f(int paramInt)
  {
    return a(paramInt * 4) / 4;
  }

  public static int g(int paramInt)
  {
    return a(paramInt * 4) / 4;
  }

  public static int h(int paramInt)
  {
    return a(paramInt * 8) / 8;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.a.a.a
 * JD-Core Version:    0.6.2
 */