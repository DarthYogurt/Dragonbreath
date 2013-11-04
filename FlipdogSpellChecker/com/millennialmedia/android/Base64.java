package com.millennialmedia.android;

import java.util.Arrays;

class Base64
{
  private static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  private static final int[] IA = new int[256];

  static
  {
    Arrays.fill(IA, -1);
    int i = 0;
    int j = CA.length;
    while (i < j)
    {
      IA[CA[i]] = i;
      i++;
    }
    IA[61] = 0;
  }

  public static final byte[] decode(String paramString)
  {
    if (paramString != null);
    for (int i = paramString.length(); i == 0; i = 0)
    {
      arrayOfByte = new byte[0];
      return arrayOfByte;
    }
    int j = 0;
    for (int k = 0; k < i; k++)
      if (IA[paramString.charAt(k)] < 0)
        j++;
    if ((i - j) % 4 != 0)
      return null;
    int m = 0;
    int n = i;
    while (n > 1)
    {
      int[] arrayOfInt2 = IA;
      n--;
      if (arrayOfInt2[paramString.charAt(n)] > 0)
        break;
      if (paramString.charAt(n) == '=')
        m++;
    }
    int i1 = (6 * (i - j) >> 3) - m;
    byte[] arrayOfByte = new byte[i1];
    int i2 = 0;
    int i3 = 0;
    label140: int i6;
    int i7;
    int i8;
    if (i3 < i1)
    {
      int i4 = 0;
      int i5 = 0;
      i6 = i2;
      if (i5 < 4)
      {
        int[] arrayOfInt1 = IA;
        int i9 = i6 + 1;
        int i10 = arrayOfInt1[paramString.charAt(i6)];
        if (i10 >= 0)
          i4 |= i10 << 18 - i5 * 6;
        while (true)
        {
          i5++;
          i6 = i9;
          break;
          i5--;
        }
      }
      i7 = i3 + 1;
      arrayOfByte[i3] = ((byte)(i4 >> 16));
      if (i7 < i1)
      {
        i8 = i7 + 1;
        arrayOfByte[i7] = ((byte)(i4 >> 8));
        if (i8 >= i1)
          break label292;
        i7 = i8 + 1;
        arrayOfByte[i8] = ((byte)i4);
      }
    }
    while (true)
    {
      i3 = i7;
      i2 = i6;
      break label140;
      break;
      label292: i7 = i8;
    }
  }

  public static final byte[] decode(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int j = 0;
    for (int k = 0; k < i; k++)
      if (IA[(0xFF & paramArrayOfByte[k])] < 0)
        j++;
    if ((i - j) % 4 != 0)
    {
      arrayOfByte = null;
      return arrayOfByte;
    }
    int m = 0;
    int n = i;
    while (n > 1)
    {
      int[] arrayOfInt2 = IA;
      n--;
      if (arrayOfInt2[(0xFF & paramArrayOfByte[n])] > 0)
        break;
      if (paramArrayOfByte[n] == 61)
        m++;
    }
    int i1 = (6 * (i - j) >> 3) - m;
    byte[] arrayOfByte = new byte[i1];
    int i2 = 0;
    int i3 = 0;
    label123: int i6;
    int i7;
    int i8;
    if (i3 < i1)
    {
      int i4 = 0;
      int i5 = 0;
      i6 = i2;
      if (i5 < 4)
      {
        int[] arrayOfInt1 = IA;
        int i9 = i6 + 1;
        int i10 = arrayOfInt1[(0xFF & paramArrayOfByte[i6])];
        if (i10 >= 0)
          i4 |= i10 << 18 - i5 * 6;
        while (true)
        {
          i5++;
          i6 = i9;
          break;
          i5--;
        }
      }
      i7 = i3 + 1;
      arrayOfByte[i3] = ((byte)(i4 >> 16));
      if (i7 < i1)
      {
        i8 = i7 + 1;
        arrayOfByte[i7] = ((byte)(i4 >> 8));
        if (i8 >= i1)
          break label280;
        i7 = i8 + 1;
        arrayOfByte[i8] = ((byte)i4);
      }
    }
    while (true)
    {
      i3 = i7;
      i2 = i6;
      break label123;
      break;
      label280: i7 = i8;
    }
  }

  public static final char[] encodeToChar(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    if (paramArrayOfByte != null);
    char[] arrayOfChar;
    for (int i = paramArrayOfByte.length; i == 0; i = 0)
    {
      arrayOfChar = new char[0];
      return arrayOfChar;
    }
    int j = 3 * (i / 3);
    int k = 1 + (i - 1) / 3 << 2;
    int m;
    label55: int n;
    int i2;
    int i3;
    label76: int i14;
    int i19;
    int i22;
    if (paramBoolean)
    {
      m = (k - 1) / 76 << 1;
      n = k + m;
      arrayOfChar = new char[n];
      int i1 = 0;
      i2 = 0;
      i3 = 0;
      if (i3 >= j)
        break label299;
      int i10 = i3 + 1;
      int i11 = (0xFF & paramArrayOfByte[i3]) << 16;
      int i12 = i10 + 1;
      int i13 = i11 | (0xFF & paramArrayOfByte[i10]) << 8;
      i14 = i12 + 1;
      int i15 = i13 | 0xFF & paramArrayOfByte[i12];
      int i16 = i2 + 1;
      arrayOfChar[i2] = CA[(0x3F & i15 >>> 18)];
      int i17 = i16 + 1;
      arrayOfChar[i16] = CA[(0x3F & i15 >>> 12)];
      int i18 = i17 + 1;
      arrayOfChar[i17] = CA[(0x3F & i15 >>> 6)];
      i19 = i18 + 1;
      arrayOfChar[i18] = CA[(i15 & 0x3F)];
      if (!paramBoolean)
        break label434;
      i1++;
      if ((i1 != 19) || (i19 >= n - 2))
        break label434;
      int i21 = i19 + 1;
      arrayOfChar[i19] = '\r';
      i22 = i21 + 1;
      arrayOfChar[i21] = '\n';
      i1 = 0;
    }
    label299: label427: label434: for (int i20 = i22; ; i20 = i19)
    {
      i2 = i20;
      i3 = i14;
      break label76;
      m = 0;
      break label55;
      int i4 = i - j;
      if (i4 <= 0)
        break;
      int i5 = (0xFF & paramArrayOfByte[j]) << 10;
      int i6;
      int i7;
      int i8;
      if (i4 == 2)
      {
        i6 = (0xFF & paramArrayOfByte[(i - 1)]) << 2;
        i7 = i5 | i6;
        arrayOfChar[(n - 4)] = CA[(i7 >> 12)];
        arrayOfChar[(n - 3)] = CA[(0x3F & i7 >>> 6)];
        i8 = n - 2;
        if (i4 != 2)
          break label427;
      }
      for (int i9 = CA[(i7 & 0x3F)]; ; i9 = 61)
      {
        arrayOfChar[i8] = i9;
        arrayOfChar[(n - 1)] = '=';
        return arrayOfChar;
        i6 = 0;
        break;
      }
    }
  }

  public static final String encodeToString(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return new String(encodeToChar(paramArrayOfByte, paramBoolean));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.Base64
 * JD-Core Version:    0.6.2
 */