package com.flipdog.filebrowser.l;

import com.flipdog.activity.MyActivity;
import com.flipdog.filebrowser.g.g;

public class e extends b
{
  private final com.flipdog.a.b.b.b b;
  private final String c;
  private com.flipdog.a.b.b.b d;

  public e(com.flipdog.a.b.b.b paramb, String paramString, g paramg, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama)
  {
    super(paramg, paramMyActivity, parama);
    this.b = paramb;
    this.c = paramString;
  }

  public com.flipdog.a.b.b.b a()
  {
    return this.d;
  }

  protected com.flipdog.filebrowser.l.a.b.a b()
    throws Exception
  {
    com.flipdog.filebrowser.l.a.b.a locala = new com.flipdog.filebrowser.l.a.b.a(this);
    try
    {
      this.d = f().a(this.b, this.c);
      return locala;
    }
    catch (com.flipdog.a.g.b localb)
    {
      locala.c = localb;
    }
    return locala;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.c;
    arrayOfObject[1] = this.b;
    arrayOfObject[2] = this.d;
    return String.format("Name: %s. Parent: %s. Result: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.e
 * JD-Core Version:    0.6.2
 */