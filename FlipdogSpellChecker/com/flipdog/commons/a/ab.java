package com.flipdog.commons.a;

import my.apache.commons.codec.binary.Base64;

public class ab
{
  public static String a(String paramString)
  {
    if (paramString == null)
      return null;
    return new String(Base64.encodeBase64(paramString.getBytes()));
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    return new String(Base64.decodeBase64(paramString.getBytes()));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ab
 * JD-Core Version:    0.6.2
 */