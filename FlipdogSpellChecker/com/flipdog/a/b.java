package com.flipdog.a;

import com.flipdog.a.f.e;
import com.flipdog.a.f.g;
import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.io.InputStream;

public abstract class b
  implements com.flipdog.a.a.a.a, com.flipdog.a.a.a.b, com.flipdog.a.a.a.c, com.flipdog.a.a.a.d
{
  protected com.flipdog.a.b.b a = null;
  protected com.flipdog.a.a.a b;
  protected e c;
  protected g d;
  protected com.flipdog.a.f.c e;
  protected com.flipdog.a.f.d f;
  private final String g;
  private com.flipdog.a.f.b h;

  protected b(com.flipdog.a.a.a parama, String paramString)
  {
    this.b = parama;
    this.g = paramString;
  }

  private com.flipdog.a.f.b f()
  {
    if (this.h == null)
      this.h = a_();
    return this.h;
  }

  public com.flipdog.a.b.b.b a()
    throws com.flipdog.a.g.b
  {
    return n().a();
  }

  public com.flipdog.a.b.b.b a(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
    return l().a(paramb, paramString);
  }

  public com.flipdog.a.b.b.c a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb, com.flipdog.a.a.b paramb1, com.flipdog.commons.l.b paramb2)
    throws com.flipdog.a.g.b
  {
    return m().a(paramFile, paramString, paramb, paramb1, paramb2);
  }

  public com.flipdog.a.b.b a(com.flipdog.a.b.a parama)
    throws com.flipdog.a.g.b
  {
    com.flipdog.a.b.b localb = ((com.flipdog.a.i.a)f().a(com.flipdog.a.f.a.e)).a(parama);
    if (localb != null)
      a(localb);
    return localb;
  }

  public InputStream a(com.flipdog.a.b.b.c paramc)
    throws com.flipdog.a.g.b
  {
    return o().a(paramc);
  }

  public void a(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    l().a(paramb);
  }

  public void a(com.flipdog.a.b.b paramb)
  {
    this.a = paramb;
  }

  public void a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    l().a(paramFile, paramString, paramb);
  }

  protected void a(String paramString, Object[] paramArrayOfObject)
  {
    Track.me(this.g, paramString, paramArrayOfObject);
  }

  protected abstract com.flipdog.a.f.b a_();

  public com.flipdog.a.b.b.b b(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    return n().b(paramb);
  }

  public String b()
  {
    throw new RuntimeException();
  }

  public void b(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
    l().b(paramb, paramString);
  }

  public void b(com.flipdog.a.b.b.c paramc)
    throws com.flipdog.a.g.b
  {
    l().b(paramc);
  }

  public String c()
  {
    throw new RuntimeException();
  }

  public String d()
  {
    throw new RuntimeException();
  }

  public String e()
  {
    throw new RuntimeException();
  }

  public boolean g()
  {
    return false;
  }

  public com.flipdog.a.a.a h()
  {
    return this.b;
  }

  public void i()
  {
    this.b.b(this.a);
    a(null);
  }

  public com.flipdog.a.b.b j()
  {
    return this.a;
  }

  public String k()
  {
    return this.g;
  }

  protected e l()
  {
    if (this.c == null)
      this.c = ((e)f().a(com.flipdog.a.f.a.c));
    return this.c;
  }

  protected g m()
  {
    if (this.d == null)
      this.d = ((g)f().a(com.flipdog.a.f.a.b));
    return this.d;
  }

  protected com.flipdog.a.f.c n()
  {
    if (this.e == null)
      this.e = ((com.flipdog.a.f.c)f().a(com.flipdog.a.f.a.a));
    return this.e;
  }

  protected com.flipdog.a.f.d o()
  {
    if (this.f == null)
      this.f = ((com.flipdog.a.f.d)f().a(com.flipdog.a.f.a.d));
    return this.f;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b
 * JD-Core Version:    0.6.2
 */