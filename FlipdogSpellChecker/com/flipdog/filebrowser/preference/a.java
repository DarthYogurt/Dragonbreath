package com.flipdog.filebrowser.preference;

import com.flipdog.commons.p.c;

public class a extends c
{
  private static a c;

  public a()
  {
    c = this;
  }

  public static a b()
  {
    if (c == null)
      c = new a();
    return c;
  }

  protected String a()
  {
    return "com.flipdog.filebrowser";
  }

  public void a(String paramString)
  {
    a("LastPath", paramString);
    c();
  }

  public int d()
  {
    return b("NamesFontSize", 15);
  }

  public int e()
  {
    return b("InfoFontSize", 14);
  }

  public int f()
  {
    return b("SubMenuFontSize", 15);
  }

  public boolean g()
  {
    return b("CreateThumbnails", true);
  }

  public boolean h()
  {
    return b("SortList", true);
  }

  public boolean i()
  {
    return b("BackHistory", true);
  }

  public int j()
  {
    return b("StoreSize", 10);
  }

  public String k()
  {
    return c("LastPath");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.preference.a
 * JD-Core Version:    0.6.2
 */