package com.flipdog.a.i;

import com.flipdog.a.c.b.e;
import com.flipdog.a.f.f;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.methods.HttpGet;
import my.apache.http.client.methods.HttpRequestBase;
import my.apache.http.impl.client.DefaultHttpClient;

public abstract class a extends f
{
  public static int a;
  protected DefaultHttpClient b = null;

  public a(String paramString)
  {
    super(paramString);
  }

  public com.flipdog.a.b.b a(com.flipdog.a.b.a parama)
    throws com.flipdog.a.g.b
  {
    b(parama);
    return null;
  }

  protected abstract String a();

  protected String a(String paramString)
    throws ClientProtocolException, IOException
  {
    return a(new HttpGet(paramString), paramString);
  }

  protected String a(HttpRequestBase paramHttpRequestBase, String paramString)
    throws ClientProtocolException, IOException
  {
    a("HTTP request: %s", new Object[] { paramString });
    HttpResponse localHttpResponse = this.b.execute(paramHttpRequestBase);
    if (localHttpResponse.getStatusLine().getStatusCode() == 302)
    {
      String str = localHttpResponse.getHeaders("Location")[0].getValue();
      a("Find redirect URL: %s", new Object[] { str });
      if (Track.isEnabled("Dev"))
      {
        com.flipdog.a.c.b.b.a(localHttpResponse);
        return str;
      }
      com.flipdog.a.c.b.b.b(localHttpResponse);
      return str;
    }
    return com.flipdog.a.c.b.b.a(localHttpResponse);
  }

  protected void b(com.flipdog.a.b.a parama)
  {
    this.b = e.a(a());
    a("Try login: %s", new Object[] { parama });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.i.a
 * JD-Core Version:    0.6.2
 */