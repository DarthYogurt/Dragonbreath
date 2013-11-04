package com.google.ads.internal;

import android.os.SystemClock;
import com.google.ads.g.a;
import com.google.ads.util.b;
import java.util.Iterator;
import java.util.LinkedList;

public class g
{
  private static long f = 0L;
  private static long g = 0L;
  private static long h = 0L;
  private static long i = 0L;
  private static long j = -1L;
  private final LinkedList<Long> a = new LinkedList();
  private long b;
  private long c;
  private long d;
  private final LinkedList<Long> e = new LinkedList();
  private boolean k = false;
  private boolean l = false;
  private String m;
  private long n;
  private final LinkedList<Long> o = new LinkedList();
  private final LinkedList<g.a> p = new LinkedList();

  public g()
  {
    a();
  }

  public static long E()
  {
    if (j == -1L)
    {
      j = SystemClock.elapsedRealtime();
      return 0L;
    }
    return SystemClock.elapsedRealtime() - j;
  }

  protected boolean A()
  {
    return this.l;
  }

  protected void B()
  {
    b.d("Interstitial no fill.");
    this.l = true;
  }

  public void C()
  {
    b.d("Landing page dismissed.");
    this.e.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }

  protected String D()
  {
    return this.m;
  }

  protected void a()
  {
    try
    {
      this.a.clear();
      this.b = 0L;
      this.c = 0L;
      this.d = 0L;
      this.e.clear();
      this.n = -1L;
      this.o.clear();
      this.p.clear();
      this.k = false;
      this.l = false;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(g.a parama)
  {
    try
    {
      this.o.add(Long.valueOf(SystemClock.elapsedRealtime() - this.n));
      this.p.add(parama);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(String paramString)
  {
    b.d("Prior impression ticket = " + paramString);
    this.m = paramString;
  }

  public void b()
  {
    try
    {
      this.o.clear();
      this.p.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void c()
  {
    try
    {
      this.n = SystemClock.elapsedRealtime();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String d()
  {
    StringBuilder localStringBuilder;
    try
    {
      localStringBuilder = new StringBuilder();
      Iterator localIterator = this.o.iterator();
      while (localIterator.hasNext())
      {
        long l1 = ((Long)localIterator.next()).longValue();
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(",");
        localStringBuilder.append(l1);
      }
    }
    finally
    {
    }
    String str = localStringBuilder.toString();
    return str;
  }

  public String e()
  {
    StringBuilder localStringBuilder;
    try
    {
      localStringBuilder = new StringBuilder();
      Iterator localIterator = this.p.iterator();
      while (localIterator.hasNext())
      {
        g.a locala = (g.a)localIterator.next();
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(",");
        localStringBuilder.append(locala.ordinal());
      }
    }
    finally
    {
    }
    String str = localStringBuilder.toString();
    return str;
  }

  protected void f()
  {
    b.d("Ad clicked.");
    this.a.add(Long.valueOf(SystemClock.elapsedRealtime()));
  }

  protected void g()
  {
    b.d("Ad request loaded.");
    this.b = SystemClock.elapsedRealtime();
  }

  protected void h()
  {
    try
    {
      b.d("Ad request before rendering.");
      this.c = SystemClock.elapsedRealtime();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void i()
  {
    b.d("Ad request started.");
    this.d = SystemClock.elapsedRealtime();
    f = 1L + f;
  }

  protected long j()
  {
    if (this.a.size() != this.e.size())
      return -1L;
    return this.a.size();
  }

  protected String k()
  {
    if ((this.a.isEmpty()) || (this.a.size() != this.e.size()))
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i1 = 0; i1 < this.a.size(); i1++)
    {
      if (i1 != 0)
        localStringBuilder.append(",");
      localStringBuilder.append(Long.toString(((Long)this.e.get(i1)).longValue() - ((Long)this.a.get(i1)).longValue()));
    }
    return localStringBuilder.toString();
  }

  protected String l()
  {
    if (this.a.isEmpty())
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i1 = 0; i1 < this.a.size(); i1++)
    {
      if (i1 != 0)
        localStringBuilder.append(",");
      localStringBuilder.append(Long.toString(((Long)this.a.get(i1)).longValue() - this.b));
    }
    return localStringBuilder.toString();
  }

  protected long m()
  {
    return this.b - this.d;
  }

  protected long n()
  {
    try
    {
      long l1 = this.c;
      long l2 = this.d;
      long l3 = l1 - l2;
      return l3;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected long o()
  {
    return f;
  }

  protected long p()
  {
    try
    {
      long l1 = g;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void q()
  {
    try
    {
      b.d("Ad request network error");
      g = 1L + g;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void r()
  {
    try
    {
      g = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected long s()
  {
    try
    {
      long l1 = h;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void t()
  {
    try
    {
      h = 1L + h;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void u()
  {
    try
    {
      h = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected long v()
  {
    try
    {
      long l1 = i;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void w()
  {
    try
    {
      i = 1L + i;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void x()
  {
    try
    {
      i = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected boolean y()
  {
    return this.k;
  }

  protected void z()
  {
    b.d("Interstitial network error.");
    this.k = true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.internal.g
 * JD-Core Version:    0.6.2
 */