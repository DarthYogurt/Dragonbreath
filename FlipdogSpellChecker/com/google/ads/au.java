package com.google.ads;

import java.io.IOException;
import java.io.OutputStream;

public final class au
{
  private final byte[] a;
  private final int b;
  private int c;
  private int d = 0;
  private final OutputStream e;

  private au(OutputStream paramOutputStream, byte[] paramArrayOfByte)
  {
    this.e = paramOutputStream;
    this.a = paramArrayOfByte;
    this.c = 0;
    this.b = paramArrayOfByte.length;
  }

  public static au a(OutputStream paramOutputStream)
  {
    return a(paramOutputStream, 4096);
  }

  public static au a(OutputStream paramOutputStream, int paramInt)
  {
    return new au(paramOutputStream, new byte[paramInt]);
  }

  private void b()
    throws IOException
  {
    if (this.e == null)
      throw new a();
    this.e.write(this.a, 0, this.c);
    this.c = 0;
  }

  public void a()
    throws IOException
  {
    if (this.e != null)
      b();
  }

  public void a(byte paramByte)
    throws IOException
  {
    if (this.c == this.b)
      b();
    byte[] arrayOfByte = this.a;
    int i = this.c;
    this.c = (i + 1);
    arrayOfByte[i] = paramByte;
    this.d = (1 + this.d);
  }

  public void a(int paramInt)
    throws IOException
  {
    a((byte)paramInt);
  }

  public void a(int paramInt1, int paramInt2)
    throws IOException
  {
    b(av.a(paramInt1, paramInt2));
  }

  public void a(int paramInt, long paramLong)
    throws IOException
  {
    a(paramInt, 0);
    a(paramLong);
  }

  public void a(int paramInt, String paramString)
    throws IOException
  {
    a(paramInt, 2);
    a(paramString);
  }

  public void a(long paramLong)
    throws IOException
  {
    b(paramLong);
  }

  public void a(String paramString)
    throws IOException
  {
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    b(arrayOfByte.length);
    a(arrayOfByte);
  }

  public void a(byte[] paramArrayOfByte)
    throws IOException
  {
    a(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.b - this.c >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.a, this.c, paramInt2);
      this.c = (paramInt2 + this.c);
      this.d = (paramInt2 + this.d);
      return;
    }
    int i = this.b - this.c;
    System.arraycopy(paramArrayOfByte, paramInt1, this.a, this.c, i);
    int j = paramInt1 + i;
    int k = paramInt2 - i;
    this.c = this.b;
    this.d = (i + this.d);
    b();
    if (k <= this.b)
    {
      System.arraycopy(paramArrayOfByte, j, this.a, 0, k);
      this.c = k;
    }
    while (true)
    {
      this.d = (k + this.d);
      return;
      this.e.write(paramArrayOfByte, j, k);
    }
  }

  public void b(int paramInt)
    throws IOException
  {
    while (true)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        a(paramInt);
        return;
      }
      a(0x80 | paramInt & 0x7F);
      paramInt >>>= 7;
    }
  }

  public void b(long paramLong)
    throws IOException
  {
    while (true)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        a((int)paramLong);
        return;
      }
      a(0x80 | 0x7F & (int)paramLong);
      paramLong >>>= 7;
    }
  }

  public static class a extends IOException
  {
    a()
    {
      super();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.au
 * JD-Core Version:    0.6.2
 */