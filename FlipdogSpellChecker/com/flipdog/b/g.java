package com.flipdog.b;

import com.flipdog.commons.a.as;
import java.util.Map;

class g
{
  private static Map<String, String> a = as.d();

  static
  {
    a.put("sans", "sans");
    a.put("sans-serif", "sans");
    a.put("serif", "serif");
    a.put("monospace", "monospace");
  }

  public static String a(String paramString)
  {
    if (paramString == null)
      return null;
    return (String)a.get(paramString.trim().toLowerCase());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.b.g
 * JD-Core Version:    0.6.2
 */