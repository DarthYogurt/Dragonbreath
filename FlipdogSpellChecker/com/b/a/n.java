package com.b.a;

import java.io.File;
import java.util.regex.Pattern;

public class n
{
  public static int a(File paramFile, String paramString)
  {
    String str = paramFile.getName();
    if (!str.matches(Pattern.quote(paramString) + "_v(.*)\\.db"))
      return -1;
    return a(str);
  }

  private static int a(String paramString)
  {
    return Integer.parseInt(paramString.replaceAll(".*_v(.*)\\.db", "$1"));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.n
 * JD-Core Version:    0.6.2
 */