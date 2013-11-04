package com.flipdog.a.e.c;

import com.a.a.c.j;
import com.a.a.m;
import com.flipdog.a.c.b.e;
import com.flipdog.a.g.d;
import com.flipdog.a.g.f;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static com.flipdog.a.b.b.a a(com.a.a.c paramc, com.flipdog.a.b.b.b paramb)
  {
    com.flipdog.a.e.e.a locala;
    Iterator localIterator;
    Object localObject;
    if (paramc.d)
    {
      locala = new com.flipdog.a.e.e.a(paramc.g);
      locala.f = paramc.b;
      if (paramc.n != null)
      {
        locala.e = as.b();
        localIterator = paramc.n.iterator();
        if (localIterator.hasNext());
      }
      else
      {
        localObject = locala;
      }
    }
    while (true)
    {
      if (paramc.e != null)
        ((com.flipdog.a.b.b.a)localObject).b = m.a(paramc.e);
      ((com.flipdog.a.b.b.a)localObject).c = paramb;
      Track.me("Dropbox", "Item: %s", new Object[] { localObject });
      return localObject;
      com.flipdog.a.b.b.a locala1 = a((com.a.a.c)localIterator.next(), paramb);
      locala.e.add(locala1);
      break;
      localObject = new com.flipdog.a.b.b.c(paramc.g);
      ((com.flipdog.a.b.b.c)localObject).e = paramc.a;
      ((com.flipdog.a.b.b.c)localObject).f = paramc.j;
    }
  }

  public static void a(Exception paramException)
    throws com.flipdog.a.g.b
  {
    if (((paramException instanceof j)) && (((j)paramException).t == 304))
      return;
    b(paramException);
  }

  public static void b(Exception paramException)
    throws com.flipdog.a.g.b
  {
    Track.it(paramException);
    com.flipdog.a.g.b localb = e.c(paramException);
    if (localb == null)
    {
      if ((paramException instanceof com.a.a.c.g))
        throw new f(paramException);
      if ((paramException instanceof j))
        throw new com.flipdog.a.g.g(paramException);
      throw new d(paramException);
    }
    throw localb;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.c.b
 * JD-Core Version:    0.6.2
 */