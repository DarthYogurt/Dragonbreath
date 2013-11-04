package com.flipdog.commons.diagnostic;

public class j
{
  public static h a;
  private static e b;
  private static boolean c;

  private static e a()
  {
    if (c)
      return b;
    try
    {
      if (c)
      {
        e locale2 = b;
        return locale2;
      }
    }
    finally
    {
    }
    if (a != null)
      b = a.a();
    c = true;
    e locale1 = b;
    return locale1;
  }

  public static void a(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    e locale = a();
    if (locale == null)
      return;
    locale.a(paramString1, String.format(paramString2, paramArrayOfObject));
  }

  public static void a(Throwable paramThrowable)
  {
    e locale = a();
    if (locale == null)
      return;
    locale.a(paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.j
 * JD-Core Version:    0.6.2
 */