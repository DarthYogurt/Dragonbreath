package com.flipdog.a.c.b;

import com.flipdog.a.c.a.a;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.entity.mime.MultipartEntity;
import my.apache.http.entity.mime.content.StringBody;
import my.apache.http.util.EntityUtils;

public class b
{
  private static Charset a;

  public static String a(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      String str = URLDecoder.decode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
  }

  public static String a(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, 0, -1 + paramString1.length());
  }

  public static String a(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if ((ax.a(paramString1)) || (ax.a(paramString2)) || (paramInt1 >= paramInt2) || (paramInt1 < 0));
    int j;
    int k;
    do
    {
      String str;
      int i;
      do
      {
        return null;
        str = paramString2 + "=\"";
        i = paramString1.indexOf(str, paramInt1);
      }
      while (i == -1);
      j = i + str.length();
      k = paramString1.indexOf("\"", j);
    }
    while ((k == -1) || (k > paramInt2));
    return paramString1.substring(j, k);
  }

  public static String a(HttpResponse paramHttpResponse)
    throws IOException
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    String str = null;
    if (localHttpEntity != null)
    {
      str = EntityUtils.toString(localHttpEntity, "UTF-8");
      EntityUtils.consume(localHttpEntity);
      if (Track.isEnabled("Dev"))
        a.a().a(str);
    }
    return str;
  }

  public static void a(MultipartEntity paramMultipartEntity, String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    a(paramMultipartEntity, paramString1, paramString2, null);
  }

  public static void a(MultipartEntity paramMultipartEntity, String paramString1, String paramString2, Charset paramCharset)
    throws UnsupportedEncodingException
  {
    Track.me("Dev", "POST '%s' = '%s'", new Object[] { paramString1, paramString2 });
    if (paramCharset == null)
    {
      paramMultipartEntity.addPart(paramString1, new StringBody(paramString2));
      return;
    }
    paramMultipartEntity.addPart(paramString1, new StringBody(paramString2, paramCharset));
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
  }

  public static String b(String paramString1, String paramString2)
  {
    String str = String.format("\"%s\" value=\"", new Object[] { paramString2 });
    int i = paramString1.indexOf(str);
    if (i == -1)
      return null;
    int j = i + str.length();
    return paramString1.substring(j, paramString1.indexOf("\"", j));
  }

  public static void b(HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity != null);
    try
    {
      EntityUtils.consume(localHttpEntity);
      return;
    }
    catch (IOException localIOException)
    {
      Track.it(localIOException);
    }
  }

  public static void b(MultipartEntity paramMultipartEntity, String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    if (a == null)
      a = Charset.forName("UTF-8");
    a(paramMultipartEntity, paramString1, paramString2, a);
  }

  public static String c(String paramString1, String paramString2)
  {
    int i = paramString1.indexOf(paramString2);
    if (i == -1)
      return null;
    int j = paramString2.length();
    int k = paramString1.indexOf("&", i + j);
    if (k == -1)
      k = paramString1.length();
    return paramString1.substring(1 + (i + j), k);
  }

  public static String d(String paramString1, String paramString2)
  {
    String str = String.format("\"%s\">", new Object[] { paramString2 });
    int i = paramString1.indexOf(str);
    if (i == -1);
    int j;
    int k;
    do
    {
      return null;
      j = i + str.length();
      k = paramString1.indexOf("</", j);
    }
    while (k == -1);
    return paramString1.substring(j, k);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.b.b
 * JD-Core Version:    0.6.2
 */