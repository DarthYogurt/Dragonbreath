package com.a.a.c;

import java.util.Map;
import my.apache.http.Header;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;

public class j extends i
{
  public static final int a = 200;
  public static final int b = 206;
  public static final int c = 302;
  public static final int d = 304;
  public static final int e = 400;
  public static final int f = 401;
  public static final int g = 403;
  public static final int h = 404;
  public static final int i = 405;
  public static final int j = 406;
  public static final int k = 409;
  public static final int l = 411;
  public static final int m = 415;
  public static final int n = 500;
  public static final int o = 501;
  public static final int p = 502;
  public static final int q = 503;
  public static final int r = 507;
  private static final long y = 1L;
  public c s;
  public int t;
  public String u;
  public String v;
  public String w;
  public Map<String, Object> x;

  public j(HttpResponse paramHttpResponse)
  {
    fillInStackTrace();
    StatusLine localStatusLine = paramHttpResponse.getStatusLine();
    this.t = localStatusLine.getStatusCode();
    this.u = localStatusLine.getReasonPhrase();
    this.v = a(paramHttpResponse, "server");
    this.w = a(paramHttpResponse, "location");
  }

  public j(HttpResponse paramHttpResponse, Object paramObject)
  {
    this(paramHttpResponse);
    if ((paramObject != null) && ((paramObject instanceof Map)))
    {
      this.x = ((Map)paramObject);
      this.s = new c(this.x);
    }
  }

  private static String a(HttpResponse paramHttpResponse, String paramString)
  {
    Header localHeader = paramHttpResponse.getFirstHeader(paramString);
    String str = null;
    if (localHeader != null)
      str = localHeader.getValue();
    return str;
  }

  public static boolean a(HttpResponse paramHttpResponse)
  {
    int i1 = paramHttpResponse.getStatusLine().getStatusCode();
    if (i1 == 302)
    {
      str1 = a(paramHttpResponse, "location");
      if (str1 != null)
      {
        i2 = str1.indexOf("://");
        if (i2 > -1)
        {
          str2 = str1.substring(i2 + 3);
          i3 = str2.indexOf("/");
          if ((i3 <= -1) || (!str2.substring(0, i3).toLowerCase().contains("dropbox.com")));
        }
      }
    }
    else
    {
      while (i1 == 304)
      {
        String str1;
        int i2;
        String str2;
        int i3;
        return true;
      }
    }
    return false;
  }

  public boolean a()
  {
    return (this.t == 400) && (this.s != null) && (this.s.a.contains("taken"));
  }

  public String toString()
  {
    return "DropboxServerException (" + this.v + "): " + this.t + " " + this.u + " (" + this.s.a + ")";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.c.j
 * JD-Core Version:    0.6.2
 */