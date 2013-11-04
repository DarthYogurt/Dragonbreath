package com.flipdog.spellchecker;

import android.content.Context;
import java.io.File;

public class w
{
  private static String a;
  private static String b;
  private static String c;

  public static String a()
  {
    if (a == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = b();
      a = String.format("content://%s/", arrayOfObject);
    }
    return a;
  }

  public static String a(String paramString)
  {
    return d().b() + "/" + paramString;
  }

  public static File b(String paramString)
  {
    return new File(a(paramString));
  }

  public static String b()
  {
    if (b == null)
      b = ((Context)com.flipdog.commons.i.b.a(Context.class)).getPackageName();
    return b;
  }

  public static String c()
  {
    if (c == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = b();
      c = String.format("file:///data/data/%s/files/", arrayOfObject);
    }
    return c;
  }

  public static String c(String paramString)
  {
    return a() + paramString;
  }

  private static com.flipdog.commons.r.b d()
  {
    return (com.flipdog.commons.r.b)com.flipdog.commons.i.b.a(com.flipdog.commons.r.b.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.w
 * JD-Core Version:    0.6.2
 */