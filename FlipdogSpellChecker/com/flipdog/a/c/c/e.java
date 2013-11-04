package com.flipdog.a.c.c;

import android.webkit.MimeTypeMap;

public class e
{
  public static final String a(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i == -1)
      return null;
    return paramString.substring(i + 1);
  }

  public static final String b(String paramString)
  {
    return c(a(paramString));
  }

  public static final String c(String paramString)
  {
    String str;
    if (paramString == null)
      str = null;
    do
    {
      return str;
      MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
      str = localMimeTypeMap.getMimeTypeFromExtension(paramString);
      if (str == null)
        str = localMimeTypeMap.getMimeTypeFromExtension(paramString.toLowerCase());
      if (str == null)
        str = localMimeTypeMap.getMimeTypeFromExtension(paramString.toUpperCase());
    }
    while (str != null);
    return b.a().b(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.c.e
 * JD-Core Version:    0.6.2
 */