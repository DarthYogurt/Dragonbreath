package com.yoc.android.yocperformance.adsdk;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.flipdog.commons.diagnostic.Track;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public final class t
{
  public static final String a = "adsnipplet";
  public static final String b = "adfitml20";
  private static final Uri c = Uri.parse("http://adserver.ubiyoo.com/");
  private static t d;
  private static Object e = new Object();
  private static String j = null;
  private final Handler f = new Handler(Looper.getMainLooper());
  private final Executor g = Executors.newFixedThreadPool(3, new p(this));
  private final HttpClient h;
  private final Context i;

  private t(Context paramContext)
  {
    this.i = paramContext;
    if (j == null)
    {
      WebView localWebView = new WebView(paramContext);
      localWebView.getSettings().setJavaScriptEnabled(true);
      j = localWebView.getSettings().getUserAgentString();
    }
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 2000);
    this.h = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
  }

  public static t a(Context paramContext)
  {
    if (d == null);
    synchronized (e)
    {
      if (d == null)
        d = new t(paramContext);
      return d;
    }
  }

  public static String a()
  {
    return j;
  }

  private static void b(String paramString)
  {
    Track.it(paramString, new String[] { "Ads" });
  }

  private String d()
  {
    return "app";
  }

  public void a(int paramInt, j paramj)
  {
    a(paramInt, null, d(), null, null, paramj);
  }

  public void a(int paramInt, Integer paramInteger, u paramu, j paramj)
  {
    a(paramInt, null, d(), paramInteger, paramu, paramj);
  }

  public void a(int paramInt, String paramString1, String paramString2, Integer paramInteger, u paramu, j paramj)
  {
    if (paramj == null)
      throw new IllegalArgumentException("Callback is null");
    this.g.execute(new r(this, paramInt, paramString1, paramString2, paramInteger, paramu, paramj));
  }

  public void a(Uri paramUri, n paramn)
  {
    if (paramUri == null)
      throw new IllegalArgumentException("Uri is null");
    if (paramn == null)
      throw new IllegalArgumentException("Callback is null");
    this.g.execute(new q(this, paramUri, paramn));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.t
 * JD-Core Version:    0.6.2
 */