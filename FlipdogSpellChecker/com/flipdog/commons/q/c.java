package com.flipdog.commons.q;

import com.flipdog.commons.l.b;
import com.flipdog.commons.u.a;
import java.io.IOException;
import java.io.InputStream;

public class c extends InputStream
{
  private static ThreadLocal<b> b = new ThreadLocal();
  private InputStream a;

  public c(InputStream paramInputStream)
  {
    this.a = paramInputStream;
  }

  public static void a(b paramb)
  {
    b.set(paramb);
  }

  private boolean a()
  {
    b localb = (b)b.get();
    if (localb == null)
      return false;
    return localb.a();
  }

  public int read()
    throws IOException
  {
    if (a())
      throw new a();
    return this.a.read();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.q.c
 * JD-Core Version:    0.6.2
 */