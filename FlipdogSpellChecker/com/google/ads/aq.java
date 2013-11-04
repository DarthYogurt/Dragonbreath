package com.google.ads;

public final class aq
{
  private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  private static final char[] b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
  private static final byte[] c = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9 };
  private static final byte[] d = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9 };

  private static int a(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte1, int paramInt2, byte[] paramArrayOfByte2)
  {
    if (paramArrayOfChar[(paramInt1 + 2)] == '=')
    {
      paramArrayOfByte1[paramInt2] = ((byte)((paramArrayOfByte2[paramArrayOfChar[paramInt1]] << 24 >>> 6 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 1)]] << 24 >>> 12) >>> 16));
      return 1;
    }
    if (paramArrayOfChar[(paramInt1 + 3)] == '=')
    {
      int j = paramArrayOfByte2[paramArrayOfChar[paramInt1]] << 24 >>> 6 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 1)]] << 24 >>> 12 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 2)]] << 24 >>> 18;
      paramArrayOfByte1[paramInt2] = ((byte)(j >>> 16));
      paramArrayOfByte1[(paramInt2 + 1)] = ((byte)(j >>> 8));
      return 2;
    }
    int i = paramArrayOfByte2[paramArrayOfChar[paramInt1]] << 24 >>> 6 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 1)]] << 24 >>> 12 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 2)]] << 24 >>> 18 | paramArrayOfByte2[paramArrayOfChar[(paramInt1 + 3)]] << 24 >>> 24;
    paramArrayOfByte1[paramInt2] = ((byte)(i >> 16));
    paramArrayOfByte1[(paramInt2 + 1)] = ((byte)(i >> 8));
    paramArrayOfByte1[(paramInt2 + 2)] = ((byte)i);
    return 3;
  }

  public static String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar, boolean paramBoolean)
  {
    char[] arrayOfChar = a(paramArrayOfByte, paramInt1, paramInt2, paramArrayOfChar, 2147483647);
    for (int i = arrayOfChar.length; ; i--)
      if ((paramBoolean) || (i <= 0) || (arrayOfChar[(i - 1)] != '='))
        return new String(arrayOfChar, 0, i);
  }

  public static String a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, b, paramBoolean);
  }

  public static byte[] a(String paramString)
    throws ap
  {
    char[] arrayOfChar = paramString.toCharArray();
    return a(arrayOfChar, 0, arrayOfChar.length);
  }

  public static byte[] a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws ap
  {
    return a(paramArrayOfChar, paramInt1, paramInt2, c);
  }

  public static byte[] a(char[] paramArrayOfChar, int paramInt1, int paramInt2, byte[] paramArrayOfByte)
    throws ap
  {
    byte[] arrayOfByte1 = new byte[2 + paramInt2 * 3 / 4];
    int i = 0;
    char[] arrayOfChar = new char[4];
    int j = 0;
    int k = 0;
    int m = 0;
    int i1;
    int i3;
    if (k < paramInt2)
    {
      int n = paramArrayOfChar[(k + paramInt1)];
      i1 = (char)(n & 0x7F);
      int i2 = paramArrayOfByte[i1];
      if ((i1 == n) && (i2 < -5))
        throw new ap("Bad Base64 input character at " + k + ": " + paramArrayOfChar[(k + paramInt1)] + "(decimal)");
      if (i2 < -1)
        break label398;
      if (i1 == 61)
        if (j != 0)
          i3 = i;
    }
    while (true)
    {
      k++;
      i = i3;
      break;
      if (k < 2)
        throw new ap("Invalid padding char found in position " + k);
      j = 1;
      int i5 = (char)(0x7F & paramArrayOfChar[(paramInt1 + (paramInt2 - 1))]);
      if ((i5 != 61) && (i5 != 10))
        throw new ap("encoded value has invalid trailing char");
      i3 = i;
      continue;
      if (j != 0)
        throw new ap("Data found after trailing padding char at index " + k);
      int i4 = m + 1;
      arrayOfChar[m] = i1;
      if (i4 == 4)
      {
        i3 = i + a(arrayOfChar, 0, arrayOfByte1, i, paramArrayOfByte);
        m = 0;
        continue;
        if (m != 0)
        {
          if (m == 1)
            throw new ap("single trailing character at offset " + (paramInt2 - 1));
          arrayOfChar[m] = '=';
          i += a(arrayOfChar, 0, arrayOfByte1, i, paramArrayOfByte);
        }
        byte[] arrayOfByte2 = new byte[i];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
        return arrayOfByte2;
      }
      else
      {
        m = i4;
        i3 = i;
        continue;
        label398: i3 = i;
      }
    }
  }

  public static char[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    int i = 4 * ((paramInt2 + 2) / 3);
    char[] arrayOfChar = new char[i + i / paramInt3];
    int j = paramInt2 - 2;
    int k = 0;
    int m = 0;
    int n = 0;
    while (n < j)
    {
      int i1 = paramArrayOfByte[(n + paramInt1)] << 24 >>> 8 | paramArrayOfByte[(paramInt1 + (n + 1))] << 24 >>> 16 | paramArrayOfByte[(paramInt1 + (n + 2))] << 24 >>> 24;
      arrayOfChar[m] = paramArrayOfChar[(i1 >>> 18)];
      arrayOfChar[(m + 1)] = paramArrayOfChar[(0x3F & i1 >>> 12)];
      arrayOfChar[(m + 2)] = paramArrayOfChar[(0x3F & i1 >>> 6)];
      arrayOfChar[(m + 3)] = paramArrayOfChar[(i1 & 0x3F)];
      int i2 = k + 4;
      if (i2 == paramInt3)
      {
        arrayOfChar[(m + 4)] = '\n';
        m++;
        i2 = 0;
      }
      n += 3;
      m += 4;
      k = i2;
    }
    if (n < paramInt2)
    {
      a(paramArrayOfByte, n + paramInt1, paramInt2 - n, arrayOfChar, m, paramArrayOfChar);
      if (k + 4 == paramInt3)
      {
        arrayOfChar[(m + 4)] = '\n';
        m++;
      }
      (m + 4);
    }
    return arrayOfChar;
  }

  private static char[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, char[] paramArrayOfChar1, int paramInt3, char[] paramArrayOfChar2)
  {
    int i;
    if (paramInt2 > 0)
    {
      i = paramArrayOfByte[paramInt1] << 24 >>> 8;
      label15: if (paramInt2 <= 1)
        break label104;
    }
    int n;
    label104: for (int j = paramArrayOfByte[(paramInt1 + 1)] << 24 >>> 16; ; j = 0)
    {
      int k = j | i;
      int m = 0;
      if (paramInt2 > 2)
        m = paramArrayOfByte[(paramInt1 + 2)] << 24 >>> 24;
      n = m | k;
      switch (paramInt2)
      {
      default:
        return paramArrayOfChar1;
        i = 0;
        break label15;
      case 3:
      case 2:
      case 1:
      }
    }
    paramArrayOfChar1[paramInt3] = paramArrayOfChar2[(n >>> 18)];
    paramArrayOfChar1[(paramInt3 + 1)] = paramArrayOfChar2[(0x3F & n >>> 12)];
    paramArrayOfChar1[(paramInt3 + 2)] = paramArrayOfChar2[(0x3F & n >>> 6)];
    paramArrayOfChar1[(paramInt3 + 3)] = paramArrayOfChar2[(n & 0x3F)];
    return paramArrayOfChar1;
    paramArrayOfChar1[paramInt3] = paramArrayOfChar2[(n >>> 18)];
    paramArrayOfChar1[(paramInt3 + 1)] = paramArrayOfChar2[(0x3F & n >>> 12)];
    paramArrayOfChar1[(paramInt3 + 2)] = paramArrayOfChar2[(0x3F & n >>> 6)];
    paramArrayOfChar1[(paramInt3 + 3)] = '=';
    return paramArrayOfChar1;
    paramArrayOfChar1[paramInt3] = paramArrayOfChar2[(n >>> 18)];
    paramArrayOfChar1[(paramInt3 + 1)] = paramArrayOfChar2[(0x3F & n >>> 12)];
    paramArrayOfChar1[(paramInt3 + 2)] = '=';
    paramArrayOfChar1[(paramInt3 + 3)] = '=';
    return paramArrayOfChar1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.aq
 * JD-Core Version:    0.6.2
 */