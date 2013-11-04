package com.flipdog.filebrowser.l.a;

import com.flipdog.filebrowser.l.b;

public class a extends com.flipdog.filebrowser.l.a.b.a
{
  public int a;
  public int e;
  public long f;
  public String g;
  public String h;

  public a(b paramb)
  {
    super(paramb);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = super.toString();
    arrayOfObject[1] = Integer.valueOf(this.a);
    arrayOfObject[2] = Integer.valueOf(this.e);
    arrayOfObject[3] = Long.valueOf(this.f);
    arrayOfObject[4] = this.h;
    arrayOfObject[5] = this.g;
    return String.format("%s. Files: %d. Dirs: %d. Size: %d. Name: %s. Date: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.a.a
 * JD-Core Version:    0.6.2
 */