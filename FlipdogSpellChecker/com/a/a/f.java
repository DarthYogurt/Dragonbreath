package com.a.a;

import com.a.a.c.h;
import com.a.a.c.i;
import java.util.Map;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import org.json.simple.JSONValue;

public final class f
{
  private String a = null;
  private long b = -1L;
  private String c = null;
  private c d = null;

  private f(HttpResponse paramHttpResponse)
    throws i
  {
    this.d = a(paramHttpResponse);
    if (this.d == null)
      throw new h("Error parsing metadata.");
    this.b = a(paramHttpResponse, this.d);
    if (this.b == -1L)
      throw new h("Error determining file size.");
    Header localHeader = paramHttpResponse.getFirstHeader("Content-Type");
    if (localHeader != null)
    {
      String str = localHeader.getValue();
      if (str != null)
      {
        String[] arrayOfString1 = str.split(";");
        if (arrayOfString1.length > 0)
          this.a = arrayOfString1[0].trim();
        if (arrayOfString1.length > 1)
        {
          String[] arrayOfString2 = arrayOfString1[1].split("=");
          if (arrayOfString2.length > 1)
            this.c = arrayOfString2[1].trim();
        }
      }
    }
  }

  private static long a(HttpResponse paramHttpResponse, c paramc)
  {
    long l = paramHttpResponse.getEntity().getContentLength();
    if (l >= 0L)
      return l;
    if (paramc != null)
      return paramc.a;
    return -1L;
  }

  private static c a(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null)
      return null;
    Header localHeader = paramHttpResponse.getFirstHeader("X-Dropbox-Metadata");
    if (localHeader == null)
      return null;
    Object localObject = JSONValue.parse(localHeader.getValue());
    if (localObject == null)
      return null;
    return new c((Map)localObject);
  }

  public final String a()
  {
    return this.a;
  }

  @Deprecated
  public final long b()
  {
    return c();
  }

  public final long c()
  {
    return this.b;
  }

  public final String d()
  {
    return this.c;
  }

  public final c e()
  {
    return this.d;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.f
 * JD-Core Version:    0.6.2
 */