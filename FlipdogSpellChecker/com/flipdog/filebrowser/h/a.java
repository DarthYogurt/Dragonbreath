package com.flipdog.filebrowser.h;

import com.flipdog.commons.a.g;
import com.flipdog.filebrowser.h.b.b;
import java.util.Comparator;

public class a
  implements Comparator<com.flipdog.filebrowser.h.b.a>
{
  public int a(com.flipdog.filebrowser.h.b.a parama1, com.flipdog.filebrowser.h.b.a parama2)
  {
    if (parama1.getClass() == parama2.getClass())
      return -1 * g.c(parama1.a, parama2.a);
    if ((parama1 instanceof b))
      return -1;
    return 1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.h.a
 * JD-Core Version:    0.6.2
 */