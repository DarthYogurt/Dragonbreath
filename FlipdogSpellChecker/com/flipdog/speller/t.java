package com.flipdog.speller;

import com.flipdog.commons.a.n;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.io.InputStream;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.HttpClient;
import my.apache.http.client.methods.HttpGet;
import my.apache.http.impl.client.DefaultHttpClient;
import my.apache.http.params.HttpParams;

class t
{
  private static final String a = "Web";

  public static String a(HttpResponse paramHttpResponse)
    throws IOException
  {
    InputStream localInputStream = paramHttpResponse.getEntity().getContent();
    try
    {
      String str = n.a(localInputStream, "utf-8");
      return str;
    }
    finally
    {
      localInputStream.close();
    }
  }

  public static String a(HttpClient paramHttpClient, HttpGet paramHttpGet)
    throws IOException, ClientProtocolException
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramHttpGet.getURI();
    Track.me("Web", "Request URL: %s", arrayOfObject1);
    HttpResponse localHttpResponse = paramHttpClient.execute(paramHttpGet);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = localHttpResponse.getStatusLine();
    Track.me("Web", "Status: %s", arrayOfObject2);
    String str = a(localHttpResponse);
    Track.me("Web", "%s", new Object[] { str });
    return str;
  }

  public static DefaultHttpClient a()
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpParams localHttpParams = localDefaultHttpClient.getParams();
    localHttpParams.setParameter("http.connection.timeout", Integer.valueOf(30000));
    localHttpParams.setParameter("http.socket.timeout", Integer.valueOf(30000));
    return localDefaultHttpClient;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.t
 * JD-Core Version:    0.6.2
 */