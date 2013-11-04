package com.flipdog.errors.a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import my.apache.http.HttpResponse;
import my.apache.http.NameValuePair;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.HttpClient;
import my.apache.http.client.entity.UrlEncodedFormEntity;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.impl.client.DefaultHttpClient;
import my.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import my.apache.http.message.BasicNameValuePair;
import my.apache.http.params.HttpParams;

public class a
{
  private static final String a = "utf-8";
  private static int b = 1000;
  private static int c = 30 * b;

  private String a(String paramString)
  {
    return "http://chat-history.com" + paramString;
  }

  private HttpResponse a(String paramString, List<NameValuePair> paramList)
    throws ClientProtocolException, IOException
  {
    DefaultHttpClient localDefaultHttpClient = a();
    HttpPost localHttpPost = new HttpPost(paramString);
    localHttpPost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
    localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(c));
    return localDefaultHttpClient.execute(localHttpPost);
  }

  public static DefaultHttpClient a()
  {
    ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager();
    localThreadSafeClientConnManager.setMaxTotal(1);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localThreadSafeClientConnManager);
    localThreadSafeClientConnManager.getSchemeRegistry().register(new Scheme("https", new com.flipdog.commons.j.a(), 443));
    return localDefaultHttpClient;
  }

  public void a(String paramString1, String paramString2)
    throws ClientProtocolException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("version", paramString1));
    localArrayList.add(new BasicNameValuePair("report", paramString2));
    a(a("/bugs/submit"), localArrayList);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.a.a
 * JD-Core Version:    0.6.2
 */