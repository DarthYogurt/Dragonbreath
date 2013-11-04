package com.flipdog.commons.a;

import java.io.File;
import java.util.Map;

public class aj
{
  private static String a = "application/octet-stream";
  private static Map<String, String> b = as.d();

  static
  {
    a("text/html", new String[] { "html", "htm" });
    a("text/plain", new String[] { "txt", "text" });
    a("image/gif", new String[] { "gif" });
    a("image/ief", new String[] { "ief" });
    a("image/jpeg", new String[] { "jpeg", "jpg", "jpe" });
    a("image/tiff", new String[] { "tiff", "tif" });
    a("image/png", new String[] { "png" });
    a("image/x-xwindowdump", new String[] { "xwd" });
    a("application/postscript", new String[] { "ai", "eps", "ps" });
    a("application/rtf", new String[] { "rtf" });
    a("application/x-tex", new String[] { "tex" });
    a("application/x-texinfo", new String[] { "texinfo", "texi" });
    a("application/x-troff", new String[] { "t", "tr", "roff" });
    a("audio/basic", new String[] { "au" });
    a("audio/midi", new String[] { "midi", "mid" });
    a("audio/x-aifc", new String[] { "aifc" });
    a("audio/x-aiff", new String[] { "aif", "aiff" });
    a("audio/x-mpeg", new String[] { "mpeg", "mpg" });
    a("audio/x-wav", new String[] { "wav" });
    a("video/mpeg", new String[] { "mpeg", "mpg", "mpe" });
    a("video/quicktime", new String[] { "qt", "mov" });
    a("video/x-msvideo", new String[] { "avi" });
    a("message/rfc822", new String[] { "eml" });
  }

  public static String a(File paramFile)
  {
    return a(paramFile.getName());
  }

  public static String a(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    String str2;
    if (i < 0)
      str2 = a;
    do
    {
      return str2;
      String str1 = paramString.substring(i + 1);
      if (str1.length() == 0)
        return a;
      str2 = (String)b.get(str1.toLowerCase());
    }
    while (str2 != null);
    return a;
  }

  private static void a(String paramString, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      String str = paramArrayOfString[j];
      b.put(str, paramString);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.aj
 * JD-Core Version:    0.6.2
 */