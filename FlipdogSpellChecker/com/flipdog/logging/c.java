package com.flipdog.logging;

import java.util.List;

public class c extends com.flipdog.commons.p.c
{
  public static c b()
  {
    return new c();
  }

  private com.flipdog.commons.s.b h()
  {
    return (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  }

  protected String a()
  {
    return "com.flipdog.logging";
  }

  public void a(List<String> paramList)
  {
    a("tags", paramList);
  }

  public void a(boolean paramBoolean)
  {
    a("isEnabled", paramBoolean);
  }

  public void b(boolean paramBoolean)
  {
    a("autoScroll", paramBoolean);
  }

  public void c()
  {
    super.c();
    ((g)h().a(g.class)).a();
  }

  public void d()
  {
    new b(this).execute(new Void[0]);
  }

  public boolean e()
  {
    return b("isEnabled", false);
  }

  public List<String> f()
  {
    return g("tags");
  }

  public boolean g()
  {
    return b("autoScroll", true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.c
 * JD-Core Version:    0.6.2
 */