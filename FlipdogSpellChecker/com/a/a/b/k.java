package com.a.a.b;

import com.a.a.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import my.apache.http.HttpRequest;
import my.apache.http.client.HttpClient;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.conn.params.ConnManagerParams;
import my.apache.http.conn.scheme.PlainSocketFactory;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.conn.ssl.SSLSocketFactory;
import my.apache.http.impl.client.DefaultHttpClient;
import my.apache.http.params.BasicHttpParams;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;

public abstract class k
  implements j
{
  private static final String a = "api.dropbox.com";
  private static final String b = "api-content.dropbox.com";
  private static final String c = "www.dropbox.com";
  private static final int d = 20;
  private static final int e = 5;
  private static final int f = 30000;
  private final f g;
  private final i h;
  private c i = null;
  private HttpClient j = null;

  public k(i parami, f paramf)
  {
    this(parami, paramf, null);
  }

  public k(i parami, f paramf, c paramc)
  {
    if (parami == null)
      throw new IllegalArgumentException("'appKeyPair' must be non-null");
    if (paramf == null)
      throw new IllegalArgumentException("'type' must be non-null");
    this.h = parami;
    this.g = paramf;
    this.i = paramc;
  }

  private static String a(i parami, c paramc)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OAuth oauth_version=\"1.0\"");
    localStringBuilder.append(", oauth_signature_method=\"PLAINTEXT\"");
    localStringBuilder.append(", oauth_consumer_key=\"").append(a(parami.a)).append("\"");
    if (paramc != null)
      localStringBuilder.append(", oauth_token=\"").append(a(paramc.a)).append("\"");
    for (String str = a(parami.b) + "&" + a(paramc.b); ; str = a(parami.b) + "&")
    {
      localStringBuilder.append(", oauth_signature=\"").append(str).append("\"");
      return localStringBuilder.toString();
    }
  }

  private static String a(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      AssertionError localAssertionError = new AssertionError("UTF-8 isn't available");
      localAssertionError.initCause(localUnsupportedEncodingException);
      throw localAssertionError;
    }
  }

  public void a(c paramc)
  {
    if (paramc == null)
      throw new IllegalArgumentException("'accessTokenPair' must be non-null");
    this.i = paramc;
  }

  public void a(HttpRequest paramHttpRequest)
  {
    paramHttpRequest.addHeader("Authorization", a(this.h, this.i));
  }

  public void a(HttpUriRequest paramHttpUriRequest)
  {
    HttpParams localHttpParams = paramHttpUriRequest.getParams();
    HttpConnectionParams.setSoTimeout(localHttpParams, 30000);
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 30000);
  }

  public i b()
  {
    return this.h;
  }

  public c c()
  {
    return this.i;
  }

  public f d()
  {
    return this.g;
  }

  public Locale e()
  {
    return Locale.ENGLISH;
  }

  public boolean f()
  {
    return this.i != null;
  }

  public void g()
  {
    this.i = null;
  }

  public r h()
  {
    return null;
  }

  public HttpClient i()
  {
    try
    {
      if (this.j == null)
      {
        BasicHttpParams localBasicHttpParams1 = new BasicHttpParams();
        ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams1, new m(this));
        ConnManagerParams.setMaxTotalConnections(localBasicHttpParams1, 20);
        SchemeRegistry localSchemeRegistry = new SchemeRegistry();
        localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        b localb = new b(localBasicHttpParams1, localSchemeRegistry);
        BasicHttpParams localBasicHttpParams2 = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams2, 30000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams2, 30000);
        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams2, 8192);
        HttpProtocolParams.setUserAgent(localBasicHttpParams2, "OfficialDropboxJavaSDK/" + a.b);
        o localo = new o(this, localb, localBasicHttpParams2);
        localo.addRequestInterceptor(new p(this));
        localo.addResponseInterceptor(new q(this));
        this.j = localo;
      }
      HttpClient localHttpClient = this.j;
      return localHttpClient;
    }
    finally
    {
    }
  }

  public String j()
  {
    return "api.dropbox.com";
  }

  public String k()
  {
    return "api-content.dropbox.com";
  }

  public String l()
  {
    return "www.dropbox.com";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.k
 * JD-Core Version:    0.6.2
 */