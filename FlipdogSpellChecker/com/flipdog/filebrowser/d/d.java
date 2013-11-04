package com.flipdog.filebrowser.d;

import com.flipdog.a.b.b.a;
import java.util.Comparator;

class d
  implements Comparator<a>
{
  public int a(a parama1, a parama2)
  {
    boolean bool1 = parama2 instanceof com.flipdog.a.b.b.b;
    boolean bool2 = parama1 instanceof com.flipdog.a.b.b.b;
    if ((bool1) && (!bool2))
      return 1;
    if (bool1 == bool2)
      return com.flipdog.filebrowser.i.b.a(parama1.a(), parama2.a());
    return -1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.d.d
 * JD-Core Version:    0.6.2
 */