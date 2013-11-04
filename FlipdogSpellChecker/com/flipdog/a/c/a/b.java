package com.flipdog.a.c.a;

import com.flipdog.a.c.b.e;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.util.Map;
import my.apache.http.HttpResponse;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.client.methods.HttpRequestBase;
import my.apache.http.entity.mime.MultipartEntity;
import my.apache.http.impl.client.DefaultHttpClient;

public class b
{
  private static Map<com.flipdog.a.b, b> e;
  protected String a;
  protected String b;
  protected final DefaultHttpClient c;
  protected com.flipdog.a.b d;

  protected b(com.flipdog.a.b paramb)
  {
    this.d = paramb;
    this.c = e.a(paramb.b());
  }

  public static b a(com.flipdog.a.b paramb)
  {
    if (e == null)
      e = as.d();
    b localb = (b)e.get(paramb);
    if (localb == null)
    {
      localb = new b(paramb);
      e.put(paramb, localb);
    }
    return localb;
  }

  private void a(HttpResponse paramHttpResponse, com.flipdog.a.b paramb, com.flipdog.a.b.b paramb1)
    throws IOException, com.flipdog.a.g.a
  {
    com.flipdog.a.b.c.a locala = new com.flipdog.a.b.c.a(com.flipdog.a.c.b.b.a(paramHttpResponse));
    Track.me(paramb.k(), "Refresh token: %s", new Object[] { locala });
    com.flipdog.a.b.b localb = paramb1.a();
    localb.c = locala.a;
    if (locala.d != null)
      localb.d = locala.d;
    paramb.a(localb);
    paramb.h().a(localb);
  }

  public HttpResponse a(com.flipdog.a.b paramb, com.flipdog.a.b.b paramb1, HttpRequestBase paramHttpRequestBase, String paramString)
    throws IOException, com.flipdog.a.g.b
  {
    String str = paramb.e();
    HttpPost localHttpPost = new HttpPost(str);
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "refresh_token", paramb1.d);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "client_id", paramb.c());
    com.flipdog.a.c.b.b.a(localMultipartEntity, "client_secret", paramb.d());
    com.flipdog.a.c.b.b.a(localMultipartEntity, "grant_type", "refresh_token");
    localHttpPost.setEntity(localMultipartEntity);
    a(e.a(paramb, localHttpPost, str, true), paramb, paramb1);
    return e.a(paramb, paramHttpRequestBase, paramString);
  }

  public DefaultHttpClient a(HttpRequestBase paramHttpRequestBase, String paramString)
  {
    if (!ax.c(this.a, paramString))
    {
      this.a = paramString;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.a;
      this.b = String.format("Bearer %s", arrayOfObject);
    }
    paramHttpRequestBase.setHeader("Authorization", this.b);
    return this.c;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.a.b
 * JD-Core Version:    0.6.2
 */