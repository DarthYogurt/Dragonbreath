package com.flipdog.filebrowser.l;

import com.flipdog.a.b.b.c;
import com.flipdog.activity.MyActivity;
import com.flipdog.filebrowser.g.g;

public class a extends b
{
  private final com.flipdog.a.b.b.a b;

  public a(com.flipdog.a.b.b.a parama, g paramg, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama1)
  {
    super(paramg, paramMyActivity, parama1);
    this.b = parama;
  }

  public com.flipdog.a.b.b.a a()
  {
    return this.b;
  }

  protected com.flipdog.filebrowser.l.a.b.a b()
    throws Exception
  {
    com.flipdog.filebrowser.l.a.b.a locala = new com.flipdog.filebrowser.l.a.b.a(this);
    try
    {
      com.flipdog.a.b localb1 = f();
      if ((this.b instanceof c))
      {
        localb1.b((c)this.b);
        return locala;
      }
      if ((this.b instanceof com.flipdog.a.b.b.b))
      {
        localb1.a((com.flipdog.a.b.b.b)this.b);
        return locala;
      }
    }
    catch (com.flipdog.a.g.b localb)
    {
      locala.c = localb;
      return locala;
    }
    if (this.b == null);
    String str;
    for (Object localObject = ""; ; localObject = str)
    {
      throw new RuntimeException((String)localObject);
      str = this.b.getClass().getSimpleName();
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.b;
    return String.format("Entry: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.a
 * JD-Core Version:    0.6.2
 */