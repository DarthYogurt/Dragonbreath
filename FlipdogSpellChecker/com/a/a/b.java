package com.a.a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class b extends FilterOutputStream
{
  private long b = 0L;
  private long c = 0L;
  private long d = 0L;

  public b(s params, OutputStream paramOutputStream)
  {
    super(paramOutputStream);
    this.c = s.a(params).a();
  }

  public void write(int paramInt)
    throws IOException
  {
    super.write(paramInt);
    this.d = (1L + this.d);
    long l = System.currentTimeMillis();
    if (l - this.b > this.c)
    {
      this.b = l;
      s.a(this.a).a(this.d, s.b(this.a));
    }
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    this.d += paramInt2;
    long l = System.currentTimeMillis();
    if (l - this.b > this.c)
    {
      this.b = l;
      s.a(this.a).a(this.d, s.b(this.a));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b
 * JD-Core Version:    0.6.2
 */