package com.b.a;

import com.b.a.a.a;

public class d
{
  public static int a(h paramh)
  {
    if (new j(paramh).b("sqlite_master").a("type", "table").a("name", "version").k() == 0)
      return -1;
    return ((Integer)new j(paramh).a("version").b("version").c(com.b.a.b.b.a)).intValue();
  }

  private static a a(Class<? extends a> paramClass)
  {
    try
    {
      a locala = (a)paramClass.newInstance();
      return locala;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }

  public static h a(String paramString)
  {
    return ((c)com.flipdog.commons.i.b.a(c.class)).a(paramString);
  }

  public static void a(String paramString, Class<? extends a> paramClass, int paramInt)
  {
    h localh = a(paramString);
    try
    {
      int i = a(localh);
      a(paramClass).b(localh, i, paramInt);
      return;
    }
    finally
    {
      localh.d();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.d
 * JD-Core Version:    0.6.2
 */