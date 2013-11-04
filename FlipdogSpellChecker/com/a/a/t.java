package com.a.a;

import com.a.a.a.b;
import com.a.a.a.e;
import com.a.a.a.f;
import com.a.a.a.g;
import com.a.a.a.j;
import java.util.List;

public final class t<MD>
{
  public final boolean a;
  public final String b;
  public final List<l<MD>> c;
  public final boolean d;

  public t(boolean paramBoolean1, List<l<MD>> paramList, String paramString, boolean paramBoolean2)
  {
    this.a = paramBoolean1;
    this.c = paramList;
    this.b = paramString;
    this.d = paramBoolean2;
  }

  public static <MD> t<MD> a(j paramj, f<MD> paramf)
    throws b
  {
    e locale = paramj.c();
    boolean bool1 = locale.b("reset").n();
    String str = locale.b("cursor").k();
    boolean bool2 = locale.b("has_more").n();
    return new t(bool1, locale.b("entries").e().a(new n(paramf)), str, bool2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.t
 * JD-Core Version:    0.6.2
 */