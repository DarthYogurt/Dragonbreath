package com.flipdog.commons.g;

import com.flipdog.commons.a.ba;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class h
{
  private com.flipdog.commons.s.b a = (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  private com.b.b.h b = new com.b.b.h();
  private d c;
  private c d = (c)com.flipdog.commons.i.b.a(c.class);
  private o e = (o)com.flipdog.commons.i.b.a(o.class);
  private j f = null;

  public h()
  {
    g();
  }

  private void a(Date paramDate)
  {
    if (this.c != null)
      this.c.a();
    this.c = new d(new z(this));
    this.c.a(paramDate);
  }

  private boolean a(b paramb)
  {
    if (paramb == null);
    while (!paramb.e().after(ba.a()))
      return true;
    return false;
  }

  private j b(b paramb)
  {
    if (paramb == null)
      return j.c;
    if (!paramb.f())
      return j.a;
    if (paramb.c())
      return j.c;
    if (paramb.b())
      return j.b;
    return j.a;
  }

  private void b(String paramString1, String paramString2)
  {
    b localb = new b();
    localb.b = paramString2;
    localb.a = paramString1;
    try
    {
      this.d.a(localb);
      this.f = null;
      ((r)this.a.a(r.class)).a();
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }

  private j f()
  {
    j localj = b(j());
    if (localj == j.a);
    return localj;
  }

  private void g()
  {
    this.a.a(this.b, new w(this));
    this.a.a(this.b, new x(this));
    this.a.a(this.b, new y(this));
  }

  private void h()
  {
    try
    {
      b localb = this.d.a();
      if (localb.b())
        a(localb.e());
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  private void i()
  {
    a(null);
  }

  private b j()
  {
    try
    {
      b localb = this.d.a();
      return localb;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public void a()
  {
    h();
  }

  public void a(af paramaf)
  {
    this.e.a(paramaf);
  }

  protected void a(String paramString1, String paramString2)
  {
    b(paramString1, paramString2);
    h();
  }

  protected void a(boolean paramBoolean)
  {
    if (!paramBoolean);
    while (!a(j()))
      return;
    i();
  }

  public void b()
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    a(new v(this, localCountDownLatch));
    try
    {
      localCountDownLatch.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException(localInterruptedException);
    }
  }

  protected void c()
  {
    j localj = e();
    if (localj == j.c)
      i();
    while (localj == j.b)
      return;
  }

  protected void d()
  {
    if (a(j()))
      i();
  }

  public j e()
  {
    if (this.f == null)
      this.f = f();
    return this.f;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.h
 * JD-Core Version:    0.6.2
 */