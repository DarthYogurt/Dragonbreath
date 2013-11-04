package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.ak;
import com.google.ads.al;
import com.google.ads.l;
import com.google.ads.m;
import com.google.ads.m.a;
import com.google.ads.n;
import com.google.ads.searchads.SearchAdRequest;
import com.google.ads.util.AdUtil;
import com.google.ads.util.AdUtil.a;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;
import com.google.ads.util.i.d;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;

public class c
  implements Runnable
{
  boolean a;
  private String b;
  private String c;
  private String d;
  private String e;
  private boolean f;
  private f g;
  private AdRequest h;
  private WebView i;
  private l j;
  private String k;
  private String l;
  private LinkedList<String> m;
  private String n;
  private AdSize o;
  private boolean p = false;
  private volatile boolean q;
  private boolean r;
  private AdRequest.ErrorCode s;
  private boolean t;
  private int u;
  private Thread v;
  private boolean w;
  private d x = d.b;

  protected c()
  {
  }

  public c(l paraml)
  {
    this.j = paraml;
    this.k = null;
    this.b = null;
    this.c = null;
    this.d = null;
    this.m = new LinkedList();
    this.s = null;
    this.t = false;
    this.u = -1;
    this.f = false;
    this.r = false;
    this.n = null;
    this.o = null;
    if ((Activity)((n)paraml.a.a()).c.a() != null)
    {
      this.i = new AdWebView((n)paraml.a.a(), null);
      this.i.setWebViewClient(i.a((d)((n)paraml.a.a()).b.a(), a.b, false, false));
      this.i.setVisibility(8);
      this.i.setWillNotDraw(true);
      this.g = new f(paraml);
      return;
    }
    this.i = null;
    this.g = null;
    com.google.ads.util.b.e("activity was null while trying to create an AdLoader.");
  }

  static void a(String paramString, com.google.ads.c paramc, com.google.ads.d paramd)
  {
    if (paramString == null);
    while ((paramString.contains("no-store")) || (paramString.contains("no-cache")))
      return;
    Matcher localMatcher = Pattern.compile("max-age\\s*=\\s*(\\d+)").matcher(paramString);
    if (localMatcher.find())
      try
      {
        int i1 = Integer.parseInt(localMatcher.group(1));
        paramd.a(paramc, i1);
        Locale localLocale = Locale.US;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i1);
        com.google.ads.util.b.c(String.format(localLocale, "Caching gWhirl configuration for: %d seconds", arrayOfObject));
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        com.google.ads.util.b.b("Caught exception trying to parse cache control directive. Overflow?", localNumberFormatException);
        return;
      }
    com.google.ads.util.b.c("Unrecognized cacheControlDirective: '" + paramString + "'. Not caching configuration.");
  }

  private void b(String paramString1, String paramString2)
  {
    ((Handler)m.a().c.a()).post(new c(this.i, paramString2, paramString1));
  }

  private String d()
  {
    if ((this.h instanceof SearchAdRequest))
      return "AFMA_buildAdURL";
    return "AFMA_buildAdURL";
  }

  private String e()
  {
    if ((this.h instanceof SearchAdRequest))
      return "AFMA_getSdkConstants();";
    return "AFMA_getSdkConstants();";
  }

  private String f()
  {
    if ((this.h instanceof SearchAdRequest))
      return "http://www.gstatic.com/safa/";
    return "http://media.admob.com/";
  }

  private String g()
  {
    if ((this.h instanceof SearchAdRequest))
      return "<html><head><script src=\"http://www.gstatic.com/safa/sdk-core-v40.js\"></script><script>";
    return "<html><head><script src=\"http://media.admob.com/sdk-core-v40.js\"></script><script>";
  }

  private String h()
  {
    if ((this.h instanceof SearchAdRequest))
      return "</script></head><body></body></html>";
    return "</script></head><body></body></html>";
  }

  private void i()
  {
    AdWebView localAdWebView = ((d)((n)this.j.a.a()).b.a()).l();
    ((d)((n)this.j.a.a()).b.a()).m().c(true);
    ((d)((n)this.j.a.a()).b.a()).n().h();
    ((Handler)m.a().c.a()).post(new c(localAdWebView, this.b, this.c));
  }

  private void j()
  {
    ((Handler)m.a().c.a()).post(new e((d)((n)this.j.a.a()).b.a(), this.i, this.m, this.u, this.r, this.n, this.o));
  }

  public String a(Map<String, Object> paramMap, Activity paramActivity)
    throws c.b
  {
    int i1 = 0;
    Context localContext = paramActivity.getApplicationContext();
    g localg = ((d)((n)this.j.a.a()).b.a()).n();
    long l1 = localg.m();
    if (l1 > 0L)
      paramMap.put("prl", Long.valueOf(l1));
    long l2 = localg.n();
    if (l2 > 0L)
      paramMap.put("prnl", Long.valueOf(l2));
    String str1 = localg.l();
    if (str1 != null)
      paramMap.put("ppcl", str1);
    String str2 = localg.k();
    if (str2 != null)
      paramMap.put("pcl", str2);
    long l3 = localg.j();
    if (l3 > 0L)
      paramMap.put("pcc", Long.valueOf(l3));
    paramMap.put("preqs", Long.valueOf(localg.o()));
    paramMap.put("oar", Long.valueOf(localg.p()));
    paramMap.put("bas_on", Long.valueOf(localg.s()));
    paramMap.put("bas_off", Long.valueOf(localg.v()));
    if (localg.y())
      paramMap.put("aoi_timeout", "true");
    if (localg.A())
      paramMap.put("aoi_nofill", "true");
    String str3 = localg.D();
    if (str3 != null)
      paramMap.put("pit", str3);
    paramMap.put("ptime", Long.valueOf(g.E()));
    localg.a();
    localg.i();
    String str4;
    if (((n)this.j.a.a()).b())
    {
      paramMap.put("format", "interstitial_mb");
      paramMap.put("slotname", ((n)this.j.a.a()).h.a());
      paramMap.put("js", "afma-sdk-a-v6.4.1");
      str4 = localContext.getPackageName();
    }
    while (true)
    {
      StringBuilder localStringBuilder;
      try
      {
        PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(str4, 0);
        int i2 = localPackageInfo.versionCode;
        String str5 = AdUtil.f(localContext);
        if (!TextUtils.isEmpty(str5))
          paramMap.put("mv", str5);
        String str6 = (String)m.a().a.a();
        if (!TextUtils.isEmpty(str6))
          paramMap.put("imbf", str6);
        paramMap.put("msid", localContext.getPackageName());
        paramMap.put("app_name", i2 + ".android." + localContext.getPackageName());
        paramMap.put("isu", AdUtil.a(localContext));
        String str7 = AdUtil.d(localContext);
        if (str7 == null)
          str7 = "null";
        paramMap.put("net", str7);
        String str8 = AdUtil.e(localContext);
        if ((str8 != null) && (str8.length() != 0))
          paramMap.put("cap", str8);
        paramMap.put("u_audio", Integer.valueOf(AdUtil.g(localContext).ordinal()));
        DisplayMetrics localDisplayMetrics1 = AdUtil.a(paramActivity);
        paramMap.put("u_sd", Float.valueOf(localDisplayMetrics1.density));
        paramMap.put("u_h", Integer.valueOf(AdUtil.a(localContext, localDisplayMetrics1)));
        paramMap.put("u_w", Integer.valueOf(AdUtil.b(localContext, localDisplayMetrics1)));
        paramMap.put("hl", Locale.getDefault().getLanguage());
        n localn = (n)this.j.a.a();
        ak localak = (ak)localn.r.a();
        if (localak == null)
        {
          localak = ak.a("afma-sdk-a-v6.4.1", paramActivity);
          localn.r.a(localak);
          localn.s.a(new al(localak));
        }
        paramMap.put("ms", localak.a(localContext));
        if ((((n)this.j.a.a()).j != null) && (((n)this.j.a.a()).j.a() != null))
        {
          AdView localAdView = (AdView)((n)this.j.a.a()).j.a();
          if (localAdView.getParent() != null)
          {
            int[] arrayOfInt = new int[2];
            localAdView.getLocationOnScreen(arrayOfInt);
            int i4 = arrayOfInt[0];
            int i5 = arrayOfInt[1];
            DisplayMetrics localDisplayMetrics2 = ((Context)((n)this.j.a.a()).f.a()).getResources().getDisplayMetrics();
            int i6 = localDisplayMetrics2.widthPixels;
            int i7 = localDisplayMetrics2.heightPixels;
            if ((!localAdView.isShown()) || (i4 + localAdView.getWidth() <= 0) || (i5 + localAdView.getHeight() <= 0) || (i4 > i6) || (i5 > i7))
              break label1817;
            i8 = 1;
            HashMap localHashMap2 = new HashMap();
            localHashMap2.put("x", Integer.valueOf(i4));
            localHashMap2.put("y", Integer.valueOf(i5));
            localHashMap2.put("width", Integer.valueOf(localAdView.getWidth()));
            localHashMap2.put("height", Integer.valueOf(localAdView.getHeight()));
            localHashMap2.put("visible", Integer.valueOf(i8));
            paramMap.put("ad_pos", localHashMap2);
          }
        }
        localStringBuilder = new StringBuilder();
        AdSize[] arrayOfAdSize = (AdSize[])((n)this.j.a.a()).n.a();
        if (arrayOfAdSize == null)
          break label1410;
        int i3 = arrayOfAdSize.length;
        if (i1 < i3)
        {
          AdSize localAdSize2 = arrayOfAdSize[i1];
          if (localStringBuilder.length() != 0)
            localStringBuilder.append("|");
          localStringBuilder.append(localAdSize2.getWidth() + "x" + localAdSize2.getHeight());
          i1++;
          continue;
          AdSize localAdSize1 = ((h)((n)this.j.a.a()).g.a()).c();
          if (localAdSize1.isFullWidth())
            paramMap.put("smart_w", "full");
          if (localAdSize1.isAutoHeight())
            paramMap.put("smart_h", "auto");
          if (!localAdSize1.isCustomAdSize())
          {
            paramMap.put("format", localAdSize1.toString());
            break;
          }
          HashMap localHashMap1 = new HashMap();
          localHashMap1.put("w", Integer.valueOf(localAdSize1.getWidth()));
          localHashMap1.put("h", Integer.valueOf(localAdSize1.getHeight()));
          paramMap.put("ad_frame", localHashMap1);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new b("NameNotFoundException");
      }
      paramMap.put("sz", localStringBuilder.toString());
      label1410: TelephonyManager localTelephonyManager = (TelephonyManager)localContext.getSystemService("phone");
      String str9 = localTelephonyManager.getNetworkOperator();
      if (!TextUtils.isEmpty(str9))
        paramMap.put("carrier", str9);
      paramMap.put("pt", Integer.valueOf(localTelephonyManager.getPhoneType()));
      paramMap.put("gnt", Integer.valueOf(localTelephonyManager.getNetworkType()));
      if (AdUtil.c())
        paramMap.put("simulator", Integer.valueOf(1));
      paramMap.put("session_id", com.google.ads.b.a().b().toString());
      paramMap.put("seq_num", com.google.ads.b.a().c().toString());
      if (((h)((n)this.j.a.a()).g.a()).b())
        paramMap.put("swipeable", Integer.valueOf(1));
      if (((Boolean)((n)this.j.a.a()).t.a()).booleanValue())
        paramMap.put("d_imp_hdr", Integer.valueOf(1));
      String str10 = AdUtil.a(paramMap);
      if (((Boolean)((m.a)((m)((n)this.j.a.a()).d.a()).b.a()).o.a()).booleanValue());
      for (String str11 = g() + d() + "(" + str10 + ");" + h(); ; str11 = g() + e() + d() + "(" + str10 + ");" + h())
      {
        com.google.ads.util.b.c("adRequestUrlHtml: " + str11);
        return str11;
      }
      label1817: int i8 = 0;
    }
  }

  protected void a()
  {
    com.google.ads.util.b.a("AdLoader cancelled.");
    if (this.i != null)
    {
      this.i.stopLoading();
      this.i.destroy();
    }
    if (this.v != null)
    {
      this.v.interrupt();
      this.v = null;
    }
    if (this.g != null)
      this.g.a();
    this.q = true;
  }

  public void a(int paramInt)
  {
    try
    {
      this.u = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(AdRequest.ErrorCode paramErrorCode)
  {
    try
    {
      this.s = paramErrorCode;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void a(AdRequest.ErrorCode paramErrorCode, boolean paramBoolean)
  {
    ((Handler)m.a().c.a()).post(new a((d)((n)this.j.a.a()).b.a(), this.i, this.g, paramErrorCode, paramBoolean));
  }

  protected void a(AdRequest paramAdRequest)
  {
    this.h = paramAdRequest;
    this.q = false;
    this.v = new Thread(this);
    this.v.start();
  }

  public void a(AdSize paramAdSize)
  {
    try
    {
      this.o = paramAdSize;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(d paramd)
  {
    try
    {
      this.x = paramd;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void a(String paramString)
  {
    try
    {
      this.m.add(paramString);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void a(String paramString1, String paramString2)
  {
    try
    {
      this.b = paramString2;
      this.c = paramString1;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(boolean paramBoolean)
  {
    try
    {
      this.p = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void b()
  {
    try
    {
      if (TextUtils.isEmpty(this.e))
      {
        com.google.ads.util.b.b("Got a mediation response with no content type. Aborting mediation.");
        a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
        return;
      }
      if (!this.e.startsWith("application/json"))
      {
        com.google.ads.util.b.b("Got a mediation response with a content type: '" + this.e + "'. Expected something starting with 'application/json'. Aborting mediation.");
        a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      com.google.ads.util.b.b("AdLoader can't parse gWhirl server configuration.", localJSONException);
      a(AdRequest.ErrorCode.INTERNAL_ERROR, false);
      return;
    }
    final com.google.ads.c localc = com.google.ads.c.a(this.c);
    a(this.d, localc, ((d)((n)this.j.a.a()).b.a()).j());
    ((Handler)m.a().c.a()).post(new Runnable()
    {
      public void run()
      {
        if (c.d(c.this) != null)
        {
          c.d(c.this).stopLoading();
          c.d(c.this).destroy();
        }
        ((d)((n)c.b(c.this).a.a()).b.a()).a(c.e(c.this));
        if (c.f(c.this) != null)
          ((h)((n)c.b(c.this).a.a()).g.a()).b(c.f(c.this));
        ((d)((n)c.b(c.this).a.a()).b.a()).a(localc);
      }
    });
  }

  protected void b(String paramString)
  {
    try
    {
      this.e = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void b(boolean paramBoolean)
  {
    try
    {
      this.f = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void c()
  {
    try
    {
      this.t = true;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void c(String paramString)
  {
    try
    {
      this.d = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void c(boolean paramBoolean)
  {
    try
    {
      this.r = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void d(String paramString)
  {
    try
    {
      this.k = paramString;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void d(boolean paramBoolean)
  {
    try
    {
      this.w = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void e(String paramString)
  {
    try
    {
      this.l = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void e(boolean paramBoolean)
  {
    try
    {
      this.a = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void f(String paramString)
  {
    try
    {
      this.n = paramString;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 112	com/google/ads/internal/c:i	Landroid/webkit/WebView;
    //   6: ifnull +10 -> 16
    //   9: aload_0
    //   10: getfield 146	com/google/ads/internal/c:g	Lcom/google/ads/internal/f;
    //   13: ifnonnull +20 -> 33
    //   16: ldc_w 803
    //   19: invokestatic 153	com/google/ads/util/b:e	(Ljava/lang/String;)V
    //   22: aload_0
    //   23: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   26: iconst_0
    //   27: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   30: aload_0
    //   31: monitorexit
    //   32: return
    //   33: aload_0
    //   34: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   37: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   40: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   43: checkcast 97	com/google/ads/n
    //   46: getfield 100	com/google/ads/n:c	Lcom/google/ads/util/i$d;
    //   49: invokevirtual 103	com/google/ads/util/i$d:a	()Ljava/lang/Object;
    //   52: checkcast 105	android/app/Activity
    //   55: astore_3
    //   56: aload_3
    //   57: ifnonnull +25 -> 82
    //   60: ldc_w 805
    //   63: invokestatic 153	com/google/ads/util/b:e	(Ljava/lang/String;)V
    //   66: aload_0
    //   67: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   70: iconst_0
    //   71: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_2
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    //   82: aload_0
    //   83: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   86: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   89: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   92: checkcast 97	com/google/ads/n
    //   95: getfield 114	com/google/ads/n:b	Lcom/google/ads/util/i$b;
    //   98: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   101: checkcast 116	com/google/ads/internal/d
    //   104: invokevirtual 806	com/google/ads/internal/d:p	()J
    //   107: lstore 4
    //   109: invokestatic 811	android/os/SystemClock:elapsedRealtime	()J
    //   112: lstore 6
    //   114: aload_0
    //   115: getfield 264	com/google/ads/internal/c:h	Lcom/google/ads/AdRequest;
    //   118: aload_0
    //   119: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   122: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   125: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   128: checkcast 97	com/google/ads/n
    //   131: getfield 542	com/google/ads/n:f	Lcom/google/ads/util/i$b;
    //   134: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   137: checkcast 399	android/content/Context
    //   140: invokevirtual 817	com/google/ads/AdRequest:getRequestMap	(Landroid/content/Context;)Ljava/util/Map;
    //   143: astore 8
    //   145: aload 8
    //   147: ldc_w 819
    //   150: invokeinterface 823 2 0
    //   155: astore 9
    //   157: aload 9
    //   159: instanceof 322
    //   162: ifeq +189 -> 351
    //   165: aload 9
    //   167: checkcast 322	java/util/Map
    //   170: astore 31
    //   172: aload 31
    //   174: ldc_w 825
    //   177: invokeinterface 823 2 0
    //   182: astore 32
    //   184: aload 32
    //   186: instanceof 160
    //   189: ifeq +12 -> 201
    //   192: aload_0
    //   193: aload 32
    //   195: checkcast 160	java/lang/String
    //   198: putfield 62	com/google/ads/internal/c:b	Ljava/lang/String;
    //   201: aload 31
    //   203: ldc_w 827
    //   206: invokeinterface 823 2 0
    //   211: astore 33
    //   213: aload 33
    //   215: instanceof 160
    //   218: ifeq +12 -> 230
    //   221: aload_0
    //   222: aload 33
    //   224: checkcast 160	java/lang/String
    //   227: putfield 60	com/google/ads/internal/c:k	Ljava/lang/String;
    //   230: aload 31
    //   232: ldc_w 829
    //   235: invokeinterface 823 2 0
    //   240: astore 34
    //   242: aload 34
    //   244: instanceof 160
    //   247: ifeq +12 -> 259
    //   250: aload_0
    //   251: aload 34
    //   253: checkcast 160	java/lang/String
    //   256: putfield 261	com/google/ads/internal/c:l	Ljava/lang/String;
    //   259: aload 31
    //   261: ldc_w 831
    //   264: invokeinterface 823 2 0
    //   269: astore 35
    //   271: aload 35
    //   273: instanceof 160
    //   276: ifeq +19 -> 295
    //   279: aload 35
    //   281: ldc_w 832
    //   284: invokevirtual 835	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   287: ifeq +143 -> 430
    //   290: aload_0
    //   291: iconst_1
    //   292: putfield 77	com/google/ads/internal/c:u	I
    //   295: aload 31
    //   297: ldc_w 837
    //   300: invokeinterface 823 2 0
    //   305: astore 36
    //   307: aload 36
    //   309: instanceof 160
    //   312: ifeq +39 -> 351
    //   315: aload 36
    //   317: ldc_w 838
    //   320: invokevirtual 835	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   323: ifeq +28 -> 351
    //   326: aload_0
    //   327: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   330: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   333: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   336: checkcast 97	com/google/ads/n
    //   339: getfield 114	com/google/ads/n:b	Lcom/google/ads/util/i$b;
    //   342: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   345: checkcast 116	com/google/ads/internal/d
    //   348: invokevirtual 840	com/google/ads/internal/d:e	()V
    //   351: aload_0
    //   352: getfield 62	com/google/ads/internal/c:b	Ljava/lang/String;
    //   355: ifnonnull +1238 -> 1593
    //   358: aload_0
    //   359: getfield 60	com/google/ads/internal/c:k	Ljava/lang/String;
    //   362: astore 14
    //   364: aload 14
    //   366: ifnonnull +290 -> 656
    //   369: aload_0
    //   370: aload 8
    //   372: aload_3
    //   373: invokevirtual 842	com/google/ads/internal/c:a	(Ljava/util/Map;Landroid/app/Activity;)Ljava/lang/String;
    //   376: astore 24
    //   378: aload_0
    //   379: aload 24
    //   381: aload_0
    //   382: invokespecial 844	com/google/ads/internal/c:f	()Ljava/lang/String;
    //   385: invokespecial 846	com/google/ads/internal/c:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   388: invokestatic 811	android/os/SystemClock:elapsedRealtime	()J
    //   391: lstore 25
    //   393: lload 4
    //   395: lload 25
    //   397: lload 6
    //   399: lsub
    //   400: lsub
    //   401: lstore 27
    //   403: lload 27
    //   405: lconst_0
    //   406: lcmp
    //   407: ifle +9 -> 416
    //   410: aload_0
    //   411: lload 27
    //   413: invokevirtual 850	java/lang/Object:wait	(J)V
    //   416: aload_0
    //   417: getfield 727	com/google/ads/internal/c:q	Z
    //   420: istore 29
    //   422: iload 29
    //   424: ifeq +110 -> 534
    //   427: aload_0
    //   428: monitorexit
    //   429: return
    //   430: aload 35
    //   432: ldc_w 851
    //   435: invokevirtual 835	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   438: ifeq -143 -> 295
    //   441: aload_0
    //   442: iconst_0
    //   443: putfield 77	com/google/ads/internal/c:u	I
    //   446: goto -151 -> 295
    //   449: astore_1
    //   450: ldc_w 853
    //   453: aload_1
    //   454: invokestatic 222	com/google/ads/util/b:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   457: aload_0
    //   458: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   461: iconst_1
    //   462: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   465: aload_0
    //   466: monitorexit
    //   467: return
    //   468: astore 23
    //   470: new 224	java/lang/StringBuilder
    //   473: dup
    //   474: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   477: ldc_w 855
    //   480: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: aload 23
    //   485: invokevirtual 858	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   488: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   491: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   494: aload_0
    //   495: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   498: iconst_0
    //   499: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   502: aload_0
    //   503: monitorexit
    //   504: return
    //   505: astore 30
    //   507: new 224	java/lang/StringBuilder
    //   510: dup
    //   511: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   514: ldc_w 860
    //   517: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: aload 30
    //   522: invokevirtual 858	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   525: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   528: invokestatic 711	com/google/ads/util/b:a	(Ljava/lang/String;)V
    //   531: aload_0
    //   532: monitorexit
    //   533: return
    //   534: aload_0
    //   535: getfield 73	com/google/ads/internal/c:s	Lcom/google/ads/AdRequest$ErrorCode;
    //   538: ifnull +15 -> 553
    //   541: aload_0
    //   542: aload_0
    //   543: getfield 73	com/google/ads/internal/c:s	Lcom/google/ads/AdRequest$ErrorCode;
    //   546: iconst_0
    //   547: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   550: aload_0
    //   551: monitorexit
    //   552: return
    //   553: aload_0
    //   554: getfield 60	com/google/ads/internal/c:k	Ljava/lang/String;
    //   557: ifnonnull +44 -> 601
    //   560: new 224	java/lang/StringBuilder
    //   563: dup
    //   564: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   567: ldc_w 862
    //   570: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: lload 4
    //   575: invokevirtual 865	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   578: ldc_w 867
    //   581: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   590: aload_0
    //   591: getstatic 870	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   594: iconst_0
    //   595: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   598: aload_0
    //   599: monitorexit
    //   600: return
    //   601: aload_0
    //   602: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   605: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   608: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   611: checkcast 97	com/google/ads/n
    //   614: getfield 593	com/google/ads/n:g	Lcom/google/ads/util/i$b;
    //   617: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   620: checkcast 595	com/google/ads/internal/h
    //   623: invokevirtual 672	com/google/ads/internal/h:b	()Z
    //   626: ifeq +30 -> 656
    //   629: aload_0
    //   630: getfield 261	com/google/ads/internal/c:l	Ljava/lang/String;
    //   633: invokestatic 427	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   636: ifeq +20 -> 656
    //   639: ldc_w 872
    //   642: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   645: aload_0
    //   646: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   649: iconst_0
    //   650: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   653: aload_0
    //   654: monitorexit
    //   655: return
    //   656: aload_0
    //   657: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   660: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   663: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   666: checkcast 97	com/google/ads/n
    //   669: getfield 114	com/google/ads/n:b	Lcom/google/ads/util/i$b;
    //   672: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   675: checkcast 116	com/google/ads/internal/d
    //   678: invokevirtual 292	com/google/ads/internal/d:n	()Lcom/google/ads/internal/g;
    //   681: astore 15
    //   683: getstatic 877	com/google/ads/internal/c$3:a	[I
    //   686: aload_0
    //   687: getfield 55	com/google/ads/internal/c:x	Lcom/google/ads/internal/c$d;
    //   690: invokevirtual 878	com/google/ads/internal/c$d:ordinal	()I
    //   693: iaload
    //   694: tableswitch	default:+30 -> 724, 1:+231->925, 2:+255->949, 3:+269->963, 4:+288->982
    //   725: getfield 796	com/google/ads/internal/c:a	Z
    //   728: ifne +363 -> 1091
    //   731: ldc_w 880
    //   734: invokestatic 711	com/google/ads/util/b:a	(Ljava/lang/String;)V
    //   737: aload_0
    //   738: getfield 146	com/google/ads/internal/c:g	Lcom/google/ads/internal/f;
    //   741: aload_0
    //   742: getfield 794	com/google/ads/internal/c:w	Z
    //   745: invokevirtual 882	com/google/ads/internal/f:a	(Z)V
    //   748: aload_0
    //   749: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   752: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   755: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   758: checkcast 97	com/google/ads/n
    //   761: getfield 593	com/google/ads/n:g	Lcom/google/ads/util/i$b;
    //   764: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   767: checkcast 595	com/google/ads/internal/h
    //   770: invokevirtual 672	com/google/ads/internal/h:b	()Z
    //   773: ifeq +814 -> 1587
    //   776: aload_0
    //   777: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   780: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   783: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   786: checkcast 97	com/google/ads/n
    //   789: getfield 884	com/google/ads/n:e	Lcom/google/ads/util/i$b;
    //   792: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   795: checkcast 886	com/google/ads/internal/ActivationOverlay
    //   798: invokevirtual 888	com/google/ads/internal/ActivationOverlay:e	()Lcom/google/ads/internal/i;
    //   801: astore 21
    //   803: aload 21
    //   805: iconst_1
    //   806: invokevirtual 289	com/google/ads/internal/i:c	(Z)V
    //   809: invokestatic 245	com/google/ads/m:a	()Lcom/google/ads/m;
    //   812: getfield 247	com/google/ads/m:c	Lcom/google/ads/util/i$b;
    //   815: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   818: checkcast 249	android/os/Handler
    //   821: new 890	com/google/ads/internal/c$1
    //   824: dup
    //   825: aload_0
    //   826: invokespecial 893	com/google/ads/internal/c$1:<init>	(Lcom/google/ads/internal/c;)V
    //   829: invokevirtual 258	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   832: pop
    //   833: aload 21
    //   835: astore 16
    //   837: aload_0
    //   838: getfield 146	com/google/ads/internal/c:g	Lcom/google/ads/internal/f;
    //   841: aload_0
    //   842: getfield 60	com/google/ads/internal/c:k	Ljava/lang/String;
    //   845: invokevirtual 894	com/google/ads/internal/f:a	(Ljava/lang/String;)V
    //   848: aload_0
    //   849: getfield 727	com/google/ads/internal/c:q	Z
    //   852: ifne +158 -> 1010
    //   855: aload_0
    //   856: getfield 73	com/google/ads/internal/c:s	Lcom/google/ads/AdRequest$ErrorCode;
    //   859: ifnonnull +151 -> 1010
    //   862: aload_0
    //   863: getfield 64	com/google/ads/internal/c:c	Ljava/lang/String;
    //   866: ifnonnull +144 -> 1010
    //   869: lload 4
    //   871: invokestatic 811	android/os/SystemClock:elapsedRealtime	()J
    //   874: lload 6
    //   876: lsub
    //   877: lsub
    //   878: lstore 19
    //   880: lload 19
    //   882: lconst_0
    //   883: lcmp
    //   884: ifle +126 -> 1010
    //   887: aload_0
    //   888: lload 19
    //   890: invokevirtual 850	java/lang/Object:wait	(J)V
    //   893: goto -45 -> 848
    //   896: astore 17
    //   898: new 224	java/lang/StringBuilder
    //   901: dup
    //   902: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   905: ldc_w 896
    //   908: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   911: aload 17
    //   913: invokevirtual 858	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   916: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   919: invokestatic 711	com/google/ads/util/b:a	(Ljava/lang/String;)V
    //   922: aload_0
    //   923: monitorexit
    //   924: return
    //   925: aload 15
    //   927: invokevirtual 898	com/google/ads/internal/g:r	()V
    //   930: aload 15
    //   932: invokevirtual 900	com/google/ads/internal/g:u	()V
    //   935: aload 15
    //   937: invokevirtual 902	com/google/ads/internal/g:x	()V
    //   940: ldc_w 904
    //   943: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   946: goto -222 -> 724
    //   949: aload 15
    //   951: invokevirtual 906	com/google/ads/internal/g:t	()V
    //   954: ldc_w 908
    //   957: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   960: goto -236 -> 724
    //   963: aload 15
    //   965: invokevirtual 910	com/google/ads/internal/g:w	()V
    //   968: aload 15
    //   970: invokevirtual 912	com/google/ads/internal/g:q	()V
    //   973: ldc_w 914
    //   976: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   979: goto -255 -> 724
    //   982: aload 15
    //   984: invokevirtual 912	com/google/ads/internal/g:q	()V
    //   987: ldc_w 916
    //   990: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   993: ldc_w 918
    //   996: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   999: aload_0
    //   1000: getstatic 870	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1003: iconst_0
    //   1004: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1007: aload_0
    //   1008: monitorexit
    //   1009: return
    //   1010: aload_0
    //   1011: getfield 727	com/google/ads/internal/c:q	Z
    //   1014: istore 18
    //   1016: iload 18
    //   1018: ifeq +6 -> 1024
    //   1021: aload_0
    //   1022: monitorexit
    //   1023: return
    //   1024: aload_0
    //   1025: getfield 73	com/google/ads/internal/c:s	Lcom/google/ads/AdRequest$ErrorCode;
    //   1028: ifnull +15 -> 1043
    //   1031: aload_0
    //   1032: aload_0
    //   1033: getfield 73	com/google/ads/internal/c:s	Lcom/google/ads/AdRequest$ErrorCode;
    //   1036: iconst_0
    //   1037: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1040: aload_0
    //   1041: monitorexit
    //   1042: return
    //   1043: aload_0
    //   1044: getfield 64	com/google/ads/internal/c:c	Ljava/lang/String;
    //   1047: ifnonnull +533 -> 1580
    //   1050: new 224	java/lang/StringBuilder
    //   1053: dup
    //   1054: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1057: ldc_w 862
    //   1060: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: lload 4
    //   1065: invokevirtual 865	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1068: ldc_w 920
    //   1071: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1077: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   1080: aload_0
    //   1081: getstatic 870	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1084: iconst_0
    //   1085: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1088: aload_0
    //   1089: monitorexit
    //   1090: return
    //   1091: aload_0
    //   1092: aload_0
    //   1093: getfield 60	com/google/ads/internal/c:k	Ljava/lang/String;
    //   1096: putfield 62	com/google/ads/internal/c:b	Ljava/lang/String;
    //   1099: new 224	java/lang/StringBuilder
    //   1102: dup
    //   1103: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1106: ldc_w 922
    //   1109: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1112: aload_0
    //   1113: getfield 62	com/google/ads/internal/c:b	Ljava/lang/String;
    //   1116: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1119: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1122: invokestatic 711	com/google/ads/util/b:a	(Ljava/lang/String;)V
    //   1125: goto +468 -> 1593
    //   1128: aload_0
    //   1129: getfield 796	com/google/ads/internal/c:a	Z
    //   1132: ifne +257 -> 1389
    //   1135: aload_0
    //   1136: getfield 79	com/google/ads/internal/c:f	Z
    //   1139: ifeq +36 -> 1175
    //   1142: aload_0
    //   1143: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   1146: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   1149: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1152: checkcast 97	com/google/ads/n
    //   1155: getfield 114	com/google/ads/n:b	Lcom/google/ads/util/i$b;
    //   1158: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1161: checkcast 116	com/google/ads/internal/d
    //   1164: iconst_1
    //   1165: invokevirtual 924	com/google/ads/internal/d:b	(Z)V
    //   1168: aload_0
    //   1169: invokevirtual 926	com/google/ads/internal/c:b	()V
    //   1172: aload_0
    //   1173: monitorexit
    //   1174: return
    //   1175: aload_0
    //   1176: getfield 754	com/google/ads/internal/c:e	Ljava/lang/String;
    //   1179: ifnull +72 -> 1251
    //   1182: aload_0
    //   1183: getfield 754	com/google/ads/internal/c:e	Ljava/lang/String;
    //   1186: ldc_w 767
    //   1189: invokevirtual 771	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1192: ifne +16 -> 1208
    //   1195: aload_0
    //   1196: getfield 754	com/google/ads/internal/c:e	Ljava/lang/String;
    //   1199: ldc_w 928
    //   1202: invokevirtual 771	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1205: ifeq +46 -> 1251
    //   1208: new 224	java/lang/StringBuilder
    //   1211: dup
    //   1212: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1215: ldc_w 930
    //   1218: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1221: aload_0
    //   1222: getfield 754	com/google/ads/internal/c:e	Ljava/lang/String;
    //   1225: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1228: ldc_w 932
    //   1231: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1234: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1237: invokestatic 758	com/google/ads/util/b:b	(Ljava/lang/String;)V
    //   1240: aload_0
    //   1241: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1244: iconst_0
    //   1245: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1248: aload_0
    //   1249: monitorexit
    //   1250: return
    //   1251: aload_0
    //   1252: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   1255: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   1258: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1261: checkcast 97	com/google/ads/n
    //   1264: getfield 582	com/google/ads/n:n	Lcom/google/ads/util/i$c;
    //   1267: invokevirtual 435	com/google/ads/util/i$c:a	()Ljava/lang/Object;
    //   1270: ifnull +101 -> 1371
    //   1273: aload_0
    //   1274: getfield 85	com/google/ads/internal/c:o	Lcom/google/ads/AdSize;
    //   1277: ifnonnull +20 -> 1297
    //   1280: ldc_w 934
    //   1283: invokestatic 758	com/google/ads/util/b:b	(Ljava/lang/String;)V
    //   1286: aload_0
    //   1287: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1290: iconst_0
    //   1291: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1294: aload_0
    //   1295: monitorexit
    //   1296: return
    //   1297: aload_0
    //   1298: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   1301: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   1304: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1307: checkcast 97	com/google/ads/n
    //   1310: getfield 582	com/google/ads/n:n	Lcom/google/ads/util/i$c;
    //   1313: invokevirtual 435	com/google/ads/util/i$c:a	()Ljava/lang/Object;
    //   1316: checkcast 936	[Ljava/lang/Object;
    //   1319: invokestatic 942	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   1322: aload_0
    //   1323: getfield 85	com/google/ads/internal/c:o	Lcom/google/ads/AdSize;
    //   1326: invokeinterface 946 2 0
    //   1331: ifne +58 -> 1389
    //   1334: new 224	java/lang/StringBuilder
    //   1337: dup
    //   1338: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1341: ldc_w 948
    //   1344: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1347: aload_0
    //   1348: getfield 85	com/google/ads/internal/c:o	Lcom/google/ads/AdSize;
    //   1351: invokevirtual 858	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1354: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1357: invokestatic 758	com/google/ads/util/b:b	(Ljava/lang/String;)V
    //   1360: aload_0
    //   1361: getstatic 763	com/google/ads/AdRequest$ErrorCode:INTERNAL_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1364: iconst_0
    //   1365: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1368: aload_0
    //   1369: monitorexit
    //   1370: return
    //   1371: aload_0
    //   1372: getfield 85	com/google/ads/internal/c:o	Lcom/google/ads/AdSize;
    //   1375: ifnull +14 -> 1389
    //   1378: ldc_w 950
    //   1381: invokestatic 153	com/google/ads/util/b:e	(Ljava/lang/String;)V
    //   1384: aload_0
    //   1385: aconst_null
    //   1386: putfield 85	com/google/ads/internal/c:o	Lcom/google/ads/AdSize;
    //   1389: aload_0
    //   1390: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   1393: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   1396: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1399: checkcast 97	com/google/ads/n
    //   1402: getfield 114	com/google/ads/n:b	Lcom/google/ads/util/i$b;
    //   1405: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1408: checkcast 116	com/google/ads/internal/d
    //   1411: iconst_0
    //   1412: invokevirtual 924	com/google/ads/internal/d:b	(Z)V
    //   1415: aload_0
    //   1416: invokespecial 951	com/google/ads/internal/c:i	()V
    //   1419: aload_0
    //   1420: getfield 727	com/google/ads/internal/c:q	Z
    //   1423: ifne +102 -> 1525
    //   1426: aload_0
    //   1427: getfield 75	com/google/ads/internal/c:t	Z
    //   1430: ifeq +39 -> 1469
    //   1433: aload_0
    //   1434: getfield 58	com/google/ads/internal/c:j	Lcom/google/ads/l;
    //   1437: getfield 90	com/google/ads/l:a	Lcom/google/ads/util/i$b;
    //   1440: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1443: checkcast 97	com/google/ads/n
    //   1446: getfield 593	com/google/ads/n:g	Lcom/google/ads/util/i$b;
    //   1449: invokevirtual 95	com/google/ads/util/i$b:a	()Ljava/lang/Object;
    //   1452: checkcast 595	com/google/ads/internal/h
    //   1455: invokevirtual 672	com/google/ads/internal/h:b	()Z
    //   1458: ifeq +67 -> 1525
    //   1461: aload 10
    //   1463: invokevirtual 953	com/google/ads/internal/i:a	()Z
    //   1466: ifeq +59 -> 1525
    //   1469: lload 4
    //   1471: invokestatic 811	android/os/SystemClock:elapsedRealtime	()J
    //   1474: lload 6
    //   1476: lsub
    //   1477: lsub
    //   1478: lstore 12
    //   1480: lload 12
    //   1482: lconst_0
    //   1483: lcmp
    //   1484: ifle +41 -> 1525
    //   1487: aload_0
    //   1488: lload 12
    //   1490: invokevirtual 850	java/lang/Object:wait	(J)V
    //   1493: goto -74 -> 1419
    //   1496: astore 11
    //   1498: new 224	java/lang/StringBuilder
    //   1501: dup
    //   1502: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1505: ldc_w 955
    //   1508: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1511: aload 11
    //   1513: invokevirtual 858	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1516: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1519: invokestatic 711	com/google/ads/util/b:a	(Ljava/lang/String;)V
    //   1522: aload_0
    //   1523: monitorexit
    //   1524: return
    //   1525: aload_0
    //   1526: getfield 75	com/google/ads/internal/c:t	Z
    //   1529: ifeq +10 -> 1539
    //   1532: aload_0
    //   1533: invokespecial 957	com/google/ads/internal/c:j	()V
    //   1536: goto -1071 -> 465
    //   1539: new 224	java/lang/StringBuilder
    //   1542: dup
    //   1543: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   1546: ldc_w 862
    //   1549: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1552: lload 4
    //   1554: invokevirtual 865	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1557: ldc_w 959
    //   1560: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1563: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1566: invokestatic 217	com/google/ads/util/b:c	(Ljava/lang/String;)V
    //   1569: aload_0
    //   1570: getstatic 870	com/google/ads/AdRequest$ErrorCode:NETWORK_ERROR	Lcom/google/ads/AdRequest$ErrorCode;
    //   1573: iconst_1
    //   1574: invokevirtual 765	com/google/ads/internal/c:a	(Lcom/google/ads/AdRequest$ErrorCode;Z)V
    //   1577: goto -1112 -> 465
    //   1580: aload 16
    //   1582: astore 10
    //   1584: goto -456 -> 1128
    //   1587: aconst_null
    //   1588: astore 16
    //   1590: goto -753 -> 837
    //   1593: aconst_null
    //   1594: astore 10
    //   1596: goto -468 -> 1128
    //
    // Exception table:
    //   from	to	target	type
    //   2	16	77	finally
    //   16	30	77	finally
    //   30	32	77	finally
    //   33	56	77	finally
    //   60	74	77	finally
    //   74	76	77	finally
    //   78	80	77	finally
    //   82	201	77	finally
    //   201	230	77	finally
    //   230	259	77	finally
    //   259	295	77	finally
    //   295	351	77	finally
    //   351	364	77	finally
    //   369	378	77	finally
    //   378	393	77	finally
    //   410	416	77	finally
    //   416	422	77	finally
    //   427	429	77	finally
    //   430	446	77	finally
    //   450	465	77	finally
    //   465	467	77	finally
    //   470	502	77	finally
    //   502	504	77	finally
    //   507	531	77	finally
    //   531	533	77	finally
    //   534	550	77	finally
    //   550	552	77	finally
    //   553	598	77	finally
    //   598	600	77	finally
    //   601	653	77	finally
    //   653	655	77	finally
    //   656	724	77	finally
    //   724	833	77	finally
    //   837	848	77	finally
    //   848	880	77	finally
    //   887	893	77	finally
    //   898	922	77	finally
    //   922	924	77	finally
    //   925	946	77	finally
    //   949	960	77	finally
    //   963	979	77	finally
    //   982	1007	77	finally
    //   1007	1009	77	finally
    //   1010	1016	77	finally
    //   1021	1023	77	finally
    //   1024	1040	77	finally
    //   1040	1042	77	finally
    //   1043	1088	77	finally
    //   1088	1090	77	finally
    //   1091	1125	77	finally
    //   1128	1172	77	finally
    //   1172	1174	77	finally
    //   1175	1208	77	finally
    //   1208	1248	77	finally
    //   1248	1250	77	finally
    //   1251	1294	77	finally
    //   1294	1296	77	finally
    //   1297	1368	77	finally
    //   1368	1370	77	finally
    //   1371	1389	77	finally
    //   1389	1419	77	finally
    //   1419	1469	77	finally
    //   1469	1480	77	finally
    //   1487	1493	77	finally
    //   1498	1522	77	finally
    //   1522	1524	77	finally
    //   1525	1536	77	finally
    //   1539	1577	77	finally
    //   2	16	449	java/lang/Throwable
    //   16	30	449	java/lang/Throwable
    //   33	56	449	java/lang/Throwable
    //   60	74	449	java/lang/Throwable
    //   82	201	449	java/lang/Throwable
    //   201	230	449	java/lang/Throwable
    //   230	259	449	java/lang/Throwable
    //   259	295	449	java/lang/Throwable
    //   295	351	449	java/lang/Throwable
    //   351	364	449	java/lang/Throwable
    //   369	378	449	java/lang/Throwable
    //   378	393	449	java/lang/Throwable
    //   410	416	449	java/lang/Throwable
    //   416	422	449	java/lang/Throwable
    //   430	446	449	java/lang/Throwable
    //   470	502	449	java/lang/Throwable
    //   507	531	449	java/lang/Throwable
    //   534	550	449	java/lang/Throwable
    //   553	598	449	java/lang/Throwable
    //   601	653	449	java/lang/Throwable
    //   656	724	449	java/lang/Throwable
    //   724	833	449	java/lang/Throwable
    //   837	848	449	java/lang/Throwable
    //   848	880	449	java/lang/Throwable
    //   887	893	449	java/lang/Throwable
    //   898	922	449	java/lang/Throwable
    //   925	946	449	java/lang/Throwable
    //   949	960	449	java/lang/Throwable
    //   963	979	449	java/lang/Throwable
    //   982	1007	449	java/lang/Throwable
    //   1010	1016	449	java/lang/Throwable
    //   1024	1040	449	java/lang/Throwable
    //   1043	1088	449	java/lang/Throwable
    //   1091	1125	449	java/lang/Throwable
    //   1128	1172	449	java/lang/Throwable
    //   1175	1208	449	java/lang/Throwable
    //   1208	1248	449	java/lang/Throwable
    //   1251	1294	449	java/lang/Throwable
    //   1297	1368	449	java/lang/Throwable
    //   1371	1389	449	java/lang/Throwable
    //   1389	1419	449	java/lang/Throwable
    //   1419	1469	449	java/lang/Throwable
    //   1469	1480	449	java/lang/Throwable
    //   1487	1493	449	java/lang/Throwable
    //   1498	1522	449	java/lang/Throwable
    //   1525	1536	449	java/lang/Throwable
    //   1539	1577	449	java/lang/Throwable
    //   369	378	468	com/google/ads/internal/c$b
    //   410	416	505	java/lang/InterruptedException
    //   848	880	896	java/lang/InterruptedException
    //   887	893	896	java/lang/InterruptedException
    //   1419	1469	1496	java/lang/InterruptedException
    //   1469	1480	1496	java/lang/InterruptedException
    //   1487	1493	1496	java/lang/InterruptedException
  }

  private static class a
    implements Runnable
  {
    private final d a;
    private final WebView b;
    private final f c;
    private final AdRequest.ErrorCode d;
    private final boolean e;

    public a(d paramd, WebView paramWebView, f paramf, AdRequest.ErrorCode paramErrorCode, boolean paramBoolean)
    {
      this.a = paramd;
      this.b = paramWebView;
      this.c = paramf;
      this.d = paramErrorCode;
      this.e = paramBoolean;
    }

    public void run()
    {
      if (this.b != null)
      {
        this.b.stopLoading();
        this.b.destroy();
      }
      if (this.c != null)
        this.c.a();
      if (this.e)
      {
        this.a.l().stopLoading();
        if (this.a.i().i.a() != null)
          ((ViewGroup)this.a.i().i.a()).setVisibility(8);
      }
      this.a.a(this.d);
    }
  }

  private class b extends Exception
  {
    public b(String arg2)
    {
      super();
    }
  }

  private class c
    implements Runnable
  {
    private final String b;
    private final String c;
    private final WebView d;

    public c(WebView paramString1, String paramString2, String arg4)
    {
      this.d = paramString1;
      this.b = paramString2;
      Object localObject;
      this.c = localObject;
    }

    public void run()
    {
      c.b(c.this).c.a(Boolean.valueOf(c.a(c.this)));
      ((d)((n)c.b(c.this).a.a()).b.a()).l().a(c.a(c.this));
      ActivationOverlay localActivationOverlay;
      if (((n)c.b(c.this).a.a()).e.a() != null)
      {
        localActivationOverlay = (ActivationOverlay)((n)c.b(c.this).a.a()).e.a();
        if (c.a(c.this))
          break label157;
      }
      label157: for (boolean bool = true; ; bool = false)
      {
        localActivationOverlay.setOverlayEnabled(bool);
        if (this.c == null)
          break;
        this.d.loadDataWithBaseURL(this.b, this.c, "text/html", "utf-8", null);
        return;
      }
      this.d.loadUrl(this.b);
    }
  }

  public static enum d
  {
    public String e;

    static
    {
      d[] arrayOfd = new d[4];
      arrayOfd[0] = a;
      arrayOfd[1] = b;
      arrayOfd[2] = c;
      arrayOfd[3] = d;
    }

    private d(String paramString)
    {
      this.e = paramString;
    }
  }

  private class e
    implements Runnable
  {
    private final d b;
    private final WebView c;
    private final LinkedList<String> d;
    private final int e;
    private final boolean f;
    private final String g;
    private final AdSize h;

    public e(WebView paramLinkedList, LinkedList<String> paramInt, int paramBoolean, boolean paramString, String paramAdSize, AdSize arg7)
    {
      this.b = paramLinkedList;
      this.c = paramInt;
      this.d = paramBoolean;
      this.e = paramString;
      this.f = paramAdSize;
      Object localObject1;
      this.g = localObject1;
      Object localObject2;
      this.h = localObject2;
    }

    public void run()
    {
      if (this.c != null)
      {
        this.c.stopLoading();
        this.c.destroy();
      }
      this.b.a(this.d);
      this.b.a(this.e);
      this.b.a(this.f);
      this.b.a(this.g);
      if (this.h != null)
      {
        ((h)((n)c.b(c.this).a.a()).g.a()).b(this.h);
        this.b.l().setAdSize(this.h);
      }
      this.b.E();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.internal.c
 * JD-Core Version:    0.6.2
 */