package com.flipdog.a.e.d;

import com.a.a.b.g;
import com.a.a.c.i;
import com.flipdog.a.f.c;
import java.util.Iterator;
import java.util.List;

public class b extends c
{
  protected final com.a.a.a<g> a;

  public b(com.a.a.a<g> parama)
  {
    super("Dropbox");
    this.a = parama;
  }

  private void a(com.flipdog.a.e.e.a parama1, com.flipdog.a.e.e.a parama2)
  {
    parama2.e = parama1.e;
    parama2.f = parama1.f;
    parama2.b = parama1.b;
    Iterator localIterator = parama2.e.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((com.flipdog.a.b.b.a)localIterator.next()).c = parama2;
    }
  }

  public com.flipdog.a.b.b.b b(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    a("Get content folder: %s", new Object[] { paramb });
    if (paramb == null);
    for (Object localObject = new com.flipdog.a.e.e.a("/"); ; localObject = paramb)
    {
      com.flipdog.a.e.e.a locala = (com.flipdog.a.e.e.a)localObject;
      try
      {
        a((com.flipdog.a.e.e.a)com.flipdog.a.e.c.b.a(this.a.a(((com.flipdog.a.b.b.b)localObject).a, 0, locala.f, true, null), ((com.flipdog.a.b.b.b)localObject).c), locala);
        locala.c = ((com.flipdog.a.b.b.b)localObject).c;
        return locala;
      }
      catch (i locali)
      {
        com.flipdog.a.e.c.b.a(locali);
        return locala;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.d.b
 * JD-Core Version:    0.6.2
 */