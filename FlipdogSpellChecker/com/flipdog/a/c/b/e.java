package com.flipdog.a.c.b;

import com.flipdog.a.g.a;
import com.flipdog.a.g.c;
import com.flipdog.a.g.f;
import com.flipdog.a.g.g;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.CookieStore;
import my.apache.http.client.methods.HttpRequestBase;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.impl.client.DefaultHttpClient;
import my.apache.http.params.HttpParams;
import org.json.JSONObject;

public class e
{
  private static com.flipdog.a.g.b a(Throwable paramThrowable)
  {
    if (((paramThrowable instanceof UnknownHostException)) || ((paramThrowable instanceof SocketException)))
      return new c(paramThrowable);
    if (((paramThrowable instanceof ConnectTimeoutException)) || ((paramThrowable instanceof SocketTimeoutException)) || ((paramThrowable instanceof SSLException)))
      return new com.flipdog.a.g.e(paramThrowable);
    if ((paramThrowable instanceof com.flipdog.a.g.b))
      return (com.flipdog.a.g.b)paramThrowable;
    return null;
  }

  // ERROR //
  public static HttpResponse a(com.flipdog.a.b paramb, HttpRequestBase paramHttpRequestBase, String paramString)
    throws com.flipdog.a.g.b
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iconst_0
    //   4: invokestatic 38	com/flipdog/a/c/b/e:a	(Lcom/flipdog/a/b;Lmy/apache/http/client/methods/HttpRequestBase;Ljava/lang/String;Z)Lmy/apache/http/HttpResponse;
    //   7: astore 7
    //   9: aload 7
    //   11: areturn
    //   12: astore_3
    //   13: aload_3
    //   14: instanceof 40
    //   17: ifeq +7 -> 24
    //   20: aload_3
    //   21: invokestatic 44	com/flipdog/a/c/b/e:b	(Ljava/lang/Exception;)V
    //   24: aload_3
    //   25: invokestatic 49	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   28: ldc2_w 50
    //   31: invokestatic 57	java/lang/Thread:sleep	(J)V
    //   34: aload_0
    //   35: aload_1
    //   36: aload_2
    //   37: iconst_0
    //   38: invokestatic 38	com/flipdog/a/c/b/e:a	(Lcom/flipdog/a/b;Lmy/apache/http/client/methods/HttpRequestBase;Ljava/lang/String;Z)Lmy/apache/http/HttpResponse;
    //   41: astore 6
    //   43: aload 6
    //   45: areturn
    //   46: astore 4
    //   48: new 59	java/lang/RuntimeException
    //   51: dup
    //   52: invokespecial 60	java/lang/RuntimeException:<init>	()V
    //   55: athrow
    //   56: astore 5
    //   58: aload 5
    //   60: invokestatic 44	com/flipdog/a/c/b/e:b	(Ljava/lang/Exception;)V
    //   63: aconst_null
    //   64: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	9	12	java/lang/Exception
    //   28	34	46	java/lang/InterruptedException
    //   34	43	56	java/lang/Exception
  }

  public static HttpResponse a(com.flipdog.a.b paramb, HttpRequestBase paramHttpRequestBase, String paramString, boolean paramBoolean)
    throws IOException, com.flipdog.a.g.b
  {
    com.flipdog.a.b.b localb = paramb.j();
    com.flipdog.a.c.a.b localb1 = com.flipdog.a.c.a.b.a(paramb);
    DefaultHttpClient localDefaultHttpClient = localb1.a(paramHttpRequestBase, localb.c);
    Track.me(paramb.k(), "URL: %s", new Object[] { paramString });
    HttpResponse localHttpResponse = localDefaultHttpClient.execute(paramHttpRequestBase);
    Track.me(paramb.k(), "Response: %s", new Object[] { localHttpResponse });
    if (localHttpResponse.getStatusLine().getStatusCode() == 401)
    {
      if (paramBoolean)
        throw new f();
      b.b(localHttpResponse);
      localHttpResponse = localb1.a(paramb, localb, paramHttpRequestBase, paramString);
    }
    return localHttpResponse;
  }

  public static DefaultHttpClient a(String paramString)
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpParams localHttpParams = localDefaultHttpClient.getParams();
    localHttpParams.setParameter("http.connection.timeout", Integer.valueOf(20000));
    localHttpParams.setParameter("http.socket.timeout", Integer.valueOf(20000));
    if (paramString != null)
      localHttpParams.setParameter("http.useragent", paramString);
    return localDefaultHttpClient;
  }

  private static JSONObject a(HttpResponse paramHttpResponse)
    throws com.flipdog.a.g.b
  {
    try
    {
      if (paramHttpResponse.getStatusLine().getStatusCode() != 200)
        throw new g();
    }
    catch (Exception localException)
    {
      b(localException);
      return null;
    }
    JSONObject localJSONObject = com.flipdog.a.c.c.d.a(b.a(paramHttpResponse));
    b(localJSONObject);
    return localJSONObject;
  }

  public static void a(DefaultHttpClient paramDefaultHttpClient)
  {
    paramDefaultHttpClient.getCookieStore().clear();
    paramDefaultHttpClient.getConnectionManager().shutdown();
  }

  public static void a(JSONObject paramJSONObject)
    throws a
  {
    if (!paramJSONObject.has("error"))
      return;
    throw new a(c(paramJSONObject));
  }

  public static boolean a(Exception paramException)
  {
    if ((paramException instanceof com.flipdog.a.g.d))
    {
      Throwable localThrowable = paramException.getCause();
      if ((localThrowable != null) && ((localThrowable instanceof InterruptedIOException)))
        return true;
    }
    return false;
  }

  public static JSONObject b(com.flipdog.a.b paramb, HttpRequestBase paramHttpRequestBase, String paramString)
    throws com.flipdog.a.g.b
  {
    try
    {
      JSONObject localJSONObject = a(a(paramb, paramHttpRequestBase, paramString));
      return localJSONObject;
    }
    catch (Exception localException)
    {
      b(localException);
    }
    return null;
  }

  public static void b(Exception paramException)
    throws com.flipdog.a.g.b
  {
    Track.it(paramException);
    com.flipdog.a.g.b localb = c(paramException);
    if (localb == null)
      throw new com.flipdog.a.g.d(paramException);
    throw localb;
  }

  public static void b(JSONObject paramJSONObject)
    throws g
  {
    if (paramJSONObject.has("error"))
      throw new g(c(paramJSONObject));
  }

  public static com.flipdog.a.g.b c(Exception paramException)
  {
    com.flipdog.a.g.b localb = a(paramException);
    if (localb == null)
      localb = a(paramException.getCause());
    return localb;
  }

  private static String c(JSONObject paramJSONObject)
  {
    String str1 = com.flipdog.a.c.c.d.d(paramJSONObject, "error_description");
    String str2 = com.flipdog.a.c.c.d.c(paramJSONObject, "error");
    if (str1 == null)
      return str2;
    return String.format("%s (%s)", new Object[] { str1, str2 });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.b.e
 * JD-Core Version:    0.6.2
 */