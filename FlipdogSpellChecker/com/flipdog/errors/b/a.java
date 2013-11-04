package com.flipdog.errors.b;

import com.flipdog.c.b;
import java.util.UUID;

public class a
{
  private static final String a = "com.flipdog.commons";

  public static String a()
  {
    String str = b();
    if (str == null)
    {
      str = c();
      a(str);
    }
    return str;
  }

  private static void a(String paramString)
  {
    b localb = b.b();
    localb.a(paramString);
    localb.c();
  }

  private static String b()
  {
    return b.b().d();
  }

  private static String c()
  {
    return UUID.randomUUID().toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.b.a
 * JD-Core Version:    0.6.2
 */