package com.flipdog.a.b.a;

import com.flipdog.a.b;
import com.flipdog.commons.diagnostic.Track;

public abstract class a
{
  public int a;
  public String b;
  protected Class<? extends b> c;

  public b a()
  {
    try
    {
      b localb = (b)this.c.newInstance();
      return localb;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      throw new RuntimeException(localException);
    }
  }

  public Class<? extends b> b()
  {
    return this.c;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.a.a
 * JD-Core Version:    0.6.2
 */