package com.flipdog.a.d.b;

import com.flipdog.a.b.b.c;
import com.flipdog.a.c.b.e;
import com.flipdog.a.d.a;
import com.flipdog.a.g.g;
import java.io.InputStream;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.methods.HttpGet;

public class d extends com.flipdog.a.f.d
{
  private final a a;

  public d(a parama)
  {
    super("Box.com");
    this.a = parama;
  }

  private String b(c paramc)
  {
    StringBuilder localStringBuilder = new StringBuilder("https://api.box.com/2.0/");
    localStringBuilder.append("files/").append(paramc.d).append("/content");
    return localStringBuilder.toString();
  }

  public InputStream a(c paramc)
    throws com.flipdog.a.g.b
  {
    a("Get filestream: %s", new Object[] { paramc });
    HttpResponse localHttpResponse;
    try
    {
      String str = b(paramc);
      HttpGet localHttpGet = new HttpGet(str);
      localHttpResponse = e.a(this.a, localHttpGet, str);
      if (localHttpResponse.getStatusLine().getStatusCode() != 200)
      {
        com.flipdog.a.c.b.b.b(localHttpResponse);
        throw new g();
      }
    }
    catch (Exception localException)
    {
      e.b(localException);
      return null;
    }
    InputStream localInputStream = localHttpResponse.getEntity().getContent();
    return localInputStream;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.b.d
 * JD-Core Version:    0.6.2
 */