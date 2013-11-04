package com.flipdog.commons.k;

import com.flipdog.commons.diagnostic.Track;

public class e
{
  private com.flipdog.commons.s.b a = (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  private b b = new b();

  private void b(b paramb)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(paramb.a);
    arrayOfObject[1] = Boolean.valueOf(paramb.b);
    Track.it(String.format("onStateChanged, mounted = %s, writable = %s", arrayOfObject), new String[] { "Sdcard" });
    this.b = paramb;
    ((c)this.a.b(c.class)).a();
  }

  private b c(b paramb)
  {
    return (b)this.b.clone();
  }

  public b a()
  {
    return c(this.b);
  }

  public void a(b paramb)
  {
    if (this.b.equals(paramb))
      return;
    b(paramb);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.k.e
 * JD-Core Version:    0.6.2
 */