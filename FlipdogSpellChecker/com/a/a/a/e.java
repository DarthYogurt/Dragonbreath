package com.a.a.a;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class e extends c<Map<String, Object>>
  implements Iterable<Map.Entry<String, j>>
{
  public e(Map<String, Object> paramMap)
  {
    super(paramMap);
  }

  public e(Map<String, Object> paramMap, String paramString)
  {
    super(paramMap, paramString);
  }

  private static boolean a(char paramChar)
  {
    return ((paramChar >= 'A') && (paramChar <= 'Z')) || ((paramChar >= 'a') && (paramChar <= 'z'));
  }

  private static String b(String paramString1, String paramString2)
  {
    if (!d(paramString2))
      paramString2 = '"' + paramString2 + '"';
    return j.a(paramString1, paramString2);
  }

  private static boolean b(char paramChar)
  {
    return (paramChar >= '0') && (paramChar <= '9');
  }

  private static boolean d(String paramString)
  {
    int i = 1;
    if (paramString.length() == 0)
      i = 0;
    while (true)
    {
      return i;
      if (!a(paramString.charAt(0)))
        return false;
      for (int j = i; j < paramString.length(); j++)
      {
        char c = paramString.charAt(j);
        if ((!a(c)) && (!b(c)))
          return false;
      }
    }
  }

  public j b(String paramString)
    throws b
  {
    if (!((Map)this.a).containsKey(paramString))
      throw a("expecting object to have field \"" + paramString + "\", but it does not");
    return new j(((Map)this.a).get(paramString), b(this.b, paramString));
  }

  public j c(String paramString)
  {
    if (!((Map)this.a).containsKey(paramString))
      return null;
    return new j(((Map)this.a).get(paramString), b(this.b, paramString));
  }

  public Iterator<Map.Entry<String, j>> iterator()
  {
    return new i(this.b, ((Map)this.a).entrySet().iterator(), null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.e
 * JD-Core Version:    0.6.2
 */