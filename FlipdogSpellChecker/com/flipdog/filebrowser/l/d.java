package com.flipdog.filebrowser.l;

import com.flipdog.activity.MyActivity;
import com.flipdog.filebrowser.k.c;
import com.flipdog.s;

public class d extends b
{
  private final com.flipdog.a.b.b.b b;

  public d(com.flipdog.a.b paramb, com.flipdog.a.b.b.b paramb1, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama)
  {
    super(paramb, paramMyActivity, parama);
    this.b = paramb1;
  }

  protected com.flipdog.filebrowser.l.a.b.a b()
    throws Exception
  {
    com.flipdog.filebrowser.l.a.a.b localb = new com.flipdog.filebrowser.l.a.a.b(this);
    try
    {
      localb.a = f().b(this.b);
      return localb;
    }
    catch (com.flipdog.a.g.b localb1)
    {
      localb.c = localb1;
    }
    return localb;
  }

  protected String e()
  {
    return c.a(s.fbrowse_clouds_read_dir);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = f().getClass().getSimpleName();
    arrayOfObject[1] = this.b;
    return String.format("Storage: %s. Folder: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.d
 * JD-Core Version:    0.6.2
 */