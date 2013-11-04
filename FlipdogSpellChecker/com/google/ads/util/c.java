package com.google.ads.util;

public class c
{
  static
  {
    if (!c.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public static byte[] a(String paramString)
  {
    return a(paramString.getBytes(), 0);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    b localb = new b(paramInt3, new byte[paramInt2 * 3 / 4]);
    if (!localb.a(paramArrayOfByte, paramInt1, paramInt2, true))
      throw new IllegalArgumentException("bad base-64");
    if (localb.b == localb.a.length)
      return localb.a;
    byte[] arrayOfByte = new byte[localb.b];
    System.arraycopy(localb.a, 0, arrayOfByte, 0, localb.b);
    return arrayOfByte;
  }

  public static abstract class a
  {
    public byte[] a;
    public int b;
  }

  public static class b extends c.a
  {
    private static final int[] c = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    private static final int[] d = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    private int e;
    private int f;
    private final int[] g;

    public b(int paramInt, byte[] paramArrayOfByte)
    {
      this.a = paramArrayOfByte;
      if ((paramInt & 0x8) == 0);
      for (int[] arrayOfInt = c; ; arrayOfInt = d)
      {
        this.g = arrayOfInt;
        this.e = 0;
        this.f = 0;
        return;
      }
    }

    public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (this.e == 6)
        return false;
      int i = paramInt2 + paramInt1;
      int j = this.e;
      int k = this.f;
      int m = 0;
      byte[] arrayOfByte = this.a;
      int[] arrayOfInt = this.g;
      int n = paramInt1;
      if (n < i)
        if (j == 0)
        {
          while (n + 4 <= i)
          {
            k = arrayOfInt[(0xFF & paramArrayOfByte[n])] << 18 | arrayOfInt[(0xFF & paramArrayOfByte[(n + 1)])] << 12 | arrayOfInt[(0xFF & paramArrayOfByte[(n + 2)])] << 6 | arrayOfInt[(0xFF & paramArrayOfByte[(n + 3)])];
            if (k < 0)
              break;
            arrayOfByte[(m + 2)] = ((byte)k);
            arrayOfByte[(m + 1)] = ((byte)(k >> 8));
            arrayOfByte[m] = ((byte)(k >> 16));
            m += 3;
            n += 4;
          }
          if (n < i);
        }
      for (int i1 = k; ; i1 = k)
      {
        if (!paramBoolean)
        {
          this.e = j;
          this.f = i1;
          this.b = m;
          return true;
          int i4 = n + 1;
          int i5 = arrayOfInt[(0xFF & paramArrayOfByte[n])];
          switch (j)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          }
          label276: label559: 
          do
          {
            do
            {
              int i6 = j;
              while (true)
              {
                j = i6;
                n = i4;
                break;
                if (i5 >= 0)
                {
                  i6 = j + 1;
                  k = i5;
                }
                else
                {
                  if (i5 == -1)
                    break label276;
                  this.e = 6;
                  return false;
                  if (i5 >= 0)
                  {
                    k = i5 | k << 6;
                    i6 = j + 1;
                  }
                  else
                  {
                    if (i5 == -1)
                      break label276;
                    this.e = 6;
                    return false;
                    if (i5 >= 0)
                    {
                      k = i5 | k << 6;
                      i6 = j + 1;
                    }
                    else if (i5 == -2)
                    {
                      int i7 = m + 1;
                      arrayOfByte[m] = ((byte)(k >> 4));
                      i6 = 4;
                      m = i7;
                    }
                    else
                    {
                      if (i5 == -1)
                        break label276;
                      this.e = 6;
                      return false;
                      if (i5 >= 0)
                      {
                        k = i5 | k << 6;
                        arrayOfByte[(m + 2)] = ((byte)k);
                        arrayOfByte[(m + 1)] = ((byte)(k >> 8));
                        arrayOfByte[m] = ((byte)(k >> 16));
                        m += 3;
                        i6 = 0;
                      }
                      else if (i5 == -2)
                      {
                        arrayOfByte[(m + 1)] = ((byte)(k >> 2));
                        arrayOfByte[m] = ((byte)(k >> 10));
                        m += 2;
                        i6 = 5;
                      }
                      else
                      {
                        if (i5 == -1)
                          break label276;
                        this.e = 6;
                        return false;
                        if (i5 != -2)
                          break label559;
                        i6 = j + 1;
                      }
                    }
                  }
                }
              }
            }
            while (i5 == -1);
            this.e = 6;
            return false;
          }
          while (i5 == -1);
          this.e = 6;
          return false;
        }
        switch (j)
        {
        case 0:
        default:
        case 1:
        case 2:
        case 3:
          while (true)
          {
            this.e = j;
            this.b = m;
            return true;
            this.e = 6;
            return false;
            int i3 = m + 1;
            arrayOfByte[m] = ((byte)(i1 >> 4));
            m = i3;
            continue;
            int i2 = m + 1;
            arrayOfByte[m] = ((byte)(i1 >> 10));
            m = i2 + 1;
            arrayOfByte[i2] = ((byte)(i1 >> 2));
          }
        case 4:
        }
        this.e = 6;
        return false;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.util.c
 * JD-Core Version:    0.6.2
 */