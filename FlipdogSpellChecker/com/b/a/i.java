package com.b.a;

import java.io.File;

public class i
{
  private String a;

  public i(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    this.a = String.format("database_v%s.db", arrayOfObject);
  }

  public i(String paramString)
  {
    this.a = paramString;
  }

  private static int a(String paramString)
  {
    return Integer.parseInt(paramString.replace("database_v", "").replace(".db", ""));
  }

  public static i a(File paramFile)
  {
    String str = paramFile.getName();
    if (!str.matches("database_v(.*)\\.db"))
      return null;
    return new i(a(str));
  }

  public int a()
  {
    return a(this.a);
  }

  public String toString()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.i
 * JD-Core Version:    0.6.2
 */