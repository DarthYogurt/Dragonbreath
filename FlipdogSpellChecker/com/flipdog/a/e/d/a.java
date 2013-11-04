package com.flipdog.a.e.d;

import com.flipdog.a.b.b.c;
import com.flipdog.a.c.b.e;
import com.flipdog.a.f.d;
import com.flipdog.a.g.b;
import java.io.InputStream;

public class a extends d
{
  protected final com.a.a.a<com.a.a.b.g> a;

  public a(com.a.a.a<com.a.a.b.g> parama)
  {
    super("Dropbox");
    this.a = parama;
  }

  public InputStream a(c paramc)
    throws b
  {
    a("Get filestream: %s", new Object[] { paramc });
    try
    {
      com.a.a.g localg = this.a.a(paramc.a, null);
      return localg;
    }
    catch (Exception localException)
    {
      e.b(localException);
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.d.a
 * JD-Core Version:    0.6.2
 */