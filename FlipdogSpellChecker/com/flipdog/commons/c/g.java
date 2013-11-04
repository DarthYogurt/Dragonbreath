package com.flipdog.commons.c;

import com.flipdog.commons.a.as;
import java.util.List;

public class g
{
  public int a;
  public int b;
  public String c;
  public String d;

  public static List<g> a(String paramString1, String paramString2, String paramString3)
  {
    List localList = as.b();
    if (paramString1 == null)
      return localList;
    g localg;
    for (int i = 0; ; i = localg.b)
    {
      int j = paramString1.indexOf(paramString2, i);
      if (j == -1);
      int k;
      do
      {
        return localList;
        k = paramString1.indexOf(paramString3, j + paramString2.length());
      }
      while (k == -1);
      while (true)
      {
        int m = paramString1.indexOf(paramString2, j + 1);
        if ((m <= j) || (m >= k))
          break;
        j = m;
      }
      localg = new g();
      localg.c = paramString2;
      localg.d = paramString1.substring(j + paramString2.length(), k);
      localg.a = j;
      localg.b = (k + paramString3.length());
      localList.add(localg);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.c.g
 * JD-Core Version:    0.6.2
 */