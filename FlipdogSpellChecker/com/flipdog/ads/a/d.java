package com.flipdog.ads.a;

import java.util.Iterator;

public class d
{
  public static b a(b paramb, int paramInt)
  {
    b localb = new b();
    Iterator localIterator = paramb.iterator();
    int j;
    for (int i = 0; ; i = j)
    {
      if (!localIterator.hasNext());
      do
      {
        return localb;
        localb.add((String)localIterator.next());
        j = i + 1;
      }
      while (j == paramInt);
    }
  }

  public static b a(String paramString1, int paramInt, String paramString2)
  {
    int i = 0;
    b localb = new b();
    if (paramString1 == null);
    String[] arrayOfString;
    String str2;
    do
    {
      return null;
      String str1 = paramString1.substring(0, Math.min(300, paramString1.length()));
      arrayOfString = str1.split("\\W");
      str2 = str1.toLowerCase();
    }
    while ((str2.indexOf("password") != -1) || (str2.indexOf("psw") != -1));
    int j = arrayOfString.length;
    int k = 0;
    if (i >= j);
    while (true)
    {
      return localb;
      String str3 = arrayOfString[i];
      if (str3.trim().length() == 0);
      do
      {
        do
        {
          i++;
          break;
        }
        while ((a(paramString2, str3)) || (a(str3)));
        localb.add(str3);
        k++;
      }
      while (k < paramInt);
    }
  }

  private static boolean a(String paramString)
  {
    return paramString.matches("\\d.*");
  }

  private static boolean a(String paramString1, String paramString2)
  {
    boolean bool = true;
    if ((paramString1 == null) || (paramString2 == null))
      bool = false;
    Object[] arrayOfObject;
    do
    {
      return bool;
      arrayOfObject = new Object[bool];
      arrayOfObject[0] = paramString2.toLowerCase();
    }
    while (paramString1.indexOf(String.format("\r\n%s\r\n", arrayOfObject)) != -1);
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.a.d
 * JD-Core Version:    0.6.2
 */