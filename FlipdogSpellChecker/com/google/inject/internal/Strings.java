package com.google.inject.internal;

public class Strings
{
  public static String capitalize(String paramString)
  {
    if (paramString.length() == 0);
    char c1;
    char c2;
    do
    {
      return paramString;
      c1 = paramString.charAt(0);
      c2 = Character.toUpperCase(c1);
    }
    while (c1 == c2);
    return c2 + paramString.substring(1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Strings
 * JD-Core Version:    0.6.2
 */