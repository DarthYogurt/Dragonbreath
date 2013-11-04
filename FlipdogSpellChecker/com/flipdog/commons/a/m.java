package com.flipdog.commons.a;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class m
{
  public static String a(Date paramDate)
  {
    if (paramDate == null)
      return null;
    return paramDate.getTime();
  }

  public static String a(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return null;
    String[] arrayOfString = new String[paramList.size()];
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString.length)
        return ax.a(arrayOfString, ",");
      arrayOfString[i] = ab.a((String)paramList.get(i));
    }
  }

  public static Date a(String paramString)
  {
    if (paramString == null)
      return null;
    return new Date(Long.parseLong(paramString));
  }

  public static List<String> b(String paramString)
  {
    if (paramString == null)
      return Collections.emptyList();
    String[] arrayOfString = ax.a(paramString, ",");
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString.length)
        return Arrays.asList(arrayOfString);
      arrayOfString[i] = ab.b(arrayOfString[i]);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.m
 * JD-Core Version:    0.6.2
 */