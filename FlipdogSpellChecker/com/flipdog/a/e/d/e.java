package com.flipdog.a.e.d;

import com.a.a.a;
import com.a.a.c.i;
import com.a.a.c.j;

public class e extends com.flipdog.a.f.e
{
  protected final a<com.a.a.b.g> a;

  public e(a<com.a.a.b.g> parama)
  {
    super("Dropbox");
    this.a = parama;
  }

  private com.flipdog.a.b.b.b c(com.flipdog.a.b.b.b paramb, String paramString)
    throws i
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramb.a;
    arrayOfObject[1] = paramString;
    String str = String.format("%s/%s", arrayOfObject);
    return (com.flipdog.a.b.b.b)com.flipdog.a.e.c.b.a(this.a.a(str), paramb);
  }

  public com.flipdog.a.b.b.b a(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
    a("Create folder: %s. Parent: %s", new Object[] { paramString, paramb });
    try
    {
      com.flipdog.a.b.b.b localb2 = c(paramb, paramString);
      localb1 = localb2;
      return localb1;
    }
    catch (i locali)
    {
      com.flipdog.a.b.b.b localb1;
      while ((locali instanceof j))
      {
        j localj = (j)locali;
        if ((localj.t == 403) && (localj.s != null) && (localj.s.a != null) && (localj.s.a.contains("already exists")))
        {
          localb1 = com.flipdog.a.c.b.b(new b(this.a).b(paramb).e, paramString);
          if (localb1 == null)
            throw new com.flipdog.a.g.g();
        }
      }
      com.flipdog.a.e.c.b.b(locali);
    }
    return null;
  }

  protected com.flipdog.a.f.c a()
  {
    return new b(this.a);
  }

  public void a(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    a("Delete folder: %s", new Object[] { paramb });
    try
    {
      this.a.b(paramb.a);
      return;
    }
    catch (i locali)
    {
      com.flipdog.a.e.c.b.b(locali);
    }
  }

  public void b(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
  }

  public void b(com.flipdog.a.b.b.c paramc)
    throws com.flipdog.a.g.b
  {
    try
    {
      this.a.b(paramc.a);
      return;
    }
    catch (i locali)
    {
      com.flipdog.a.e.c.b.b(locali);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.d.e
 * JD-Core Version:    0.6.2
 */