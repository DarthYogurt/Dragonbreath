package com.flipdog.commons.a;

import java.text.StringCharacterIterator;

public class d
{
  private static final String a = "|\\?*<\":>+[]/'";

  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StringCharacterIterator localStringCharacterIterator = new StringCharacterIterator(paramString);
    if (localStringCharacterIterator.current() == 65535)
      return localStringBuilder.toString();
    char c = localStringCharacterIterator.current();
    if (a(c))
      localStringBuilder.append("%" + Integer.toHexString(c));
    while (true)
    {
      localStringCharacterIterator.next();
      break;
      localStringBuilder.append(c);
    }
  }

  public static String a(String paramString1, String paramString2)
  {
    if (ax.a(paramString1))
      return paramString1;
    StringBuilder localStringBuilder = new StringBuilder();
    StringCharacterIterator localStringCharacterIterator = new StringCharacterIterator(paramString1);
    if (localStringCharacterIterator.current() == 65535)
      return localStringBuilder.toString();
    char c = localStringCharacterIterator.current();
    if (a(c))
      localStringBuilder.append(paramString2);
    while (true)
    {
      localStringCharacterIterator.next();
      break;
      localStringBuilder.append(c);
    }
  }

  private static boolean a(char paramChar)
  {
    return "|\\?*<\":>+[]/'".indexOf(paramChar) != -1;
  }

  public static String b(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return null;
      i = paramString.lastIndexOf(".");
    }
    while (i == -1);
    return paramString.substring(i);
  }

  public static String c(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return null;
      i = paramString.lastIndexOf(".");
    }
    while (i == -1);
    return paramString.substring(i + 1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.d
 * JD-Core Version:    0.6.2
 */