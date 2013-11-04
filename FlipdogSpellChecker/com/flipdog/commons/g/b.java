package com.flipdog.commons.g;

import com.android.vending.licensing.u;
import com.flipdog.commons.a.ba;
import java.util.Date;

public class b
{
  public String a;
  public String b;

  public u a()
  {
    if (this.a == null)
      return null;
    return n.b(this.a);
  }

  public boolean b()
  {
    return n.b(a());
  }

  public boolean c()
  {
    Date localDate = d();
    return new Date().getTime() > localDate.getTime();
  }

  public Date d()
  {
    return n.a(a());
  }

  public Date e()
  {
    return ba.b(d(), 480);
  }

  public boolean f()
  {
    return n.a(this);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.b
 * JD-Core Version:    0.6.2
 */