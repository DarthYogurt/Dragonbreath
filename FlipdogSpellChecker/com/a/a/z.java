package com.a.a;

import com.a.a.c.e;
import com.a.a.c.i;
import com.a.a.c.j;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class z
{
  private static final int d = 4194304;
  private String b;
  private long c = 0L;
  private byte[] e = null;
  private InputStream f;
  private long g;
  private boolean h = true;
  private x i = null;
  private int j;

  z(a parama, InputStream paramInputStream, long paramLong)
  {
    this(parama, paramInputStream, paramLong, 4194304);
  }

  z(a parama, InputStream paramInputStream, long paramLong, int paramInt)
  {
    this.f = paramInputStream;
    this.g = paramLong;
    this.j = paramInt;
  }

  public long a()
  {
    return this.c;
  }

  public c a(String paramString1, String paramString2)
    throws i
  {
    return a.a(this.a, paramString1, this.b, false, paramString2);
  }

  public void a(h paramh)
    throws i, IOException
  {
    if (this.c >= this.g)
      return;
    int k = (int)Math.min(this.j, this.g - this.c);
    if (paramh != null);
    for (q localq = new q(paramh, this.c, this.g); ; localq = null)
      while (true)
      {
        if (this.e == null)
        {
          this.e = new byte[k];
          int m = this.f.read(this.e);
          if (m < this.e.length)
            throw new IllegalStateException("InputStream ended after " + (this.c + m) + " bytes, expecting " + this.g + " bytes.");
        }
        try
        {
          try
          {
            if (!this.h)
              throw new e(0L);
          }
          finally
          {
          }
        }
        catch (j localj)
        {
          if (localj.s.c.containsKey("offset"))
          {
            long l = ((Long)localj.s.c.get("offset")).longValue();
            if (l > this.c)
            {
              this.e = null;
              this.c = l;
              break;
              this.i = this.a.a(new ByteArrayInputStream(this.e), this.e.length, localq, this.c, this.b);
              w localw = this.i.b();
              this.c = localw.b();
              this.b = localw.a();
              this.e = null;
              break;
            }
            throw localj;
          }
          throw localj;
        }
      }
  }

  public boolean b()
  {
    return this.c == this.g;
  }

  public boolean c()
  {
    return this.h;
  }

  public void d()
  {
    try
    {
      if (this.i != null)
        this.i.a();
      this.h = false;
      return;
    }
    finally
    {
    }
  }

  public void e()
    throws i, IOException
  {
    a(null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.z
 * JD-Core Version:    0.6.2
 */