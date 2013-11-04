package com.google.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.google.ads.Ad;
import com.google.ads.AdActivity;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;
import com.google.ads.InterstitialAd;
import com.google.ads.SwipeableAdListener;
import com.google.ads.ae;
import com.google.ads.af;
import com.google.ads.at;
import com.google.ads.doubleclick.SwipeableDfpAdView;
import com.google.ads.f;
import com.google.ads.l;
import com.google.ads.m;
import com.google.ads.m.a;
import com.google.ads.n;
import com.google.ads.util.AdUtil;
import com.google.ads.util.IcsUtil.IcsAdWebView;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;
import com.google.ads.util.i.d;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class d
{
  private static final Object a = new Object();
  private String A = null;
  private String B = null;
  private final n b;
  private c c;
  private AdRequest d;
  private g e;
  private AdWebView f;
  private i g;
  private boolean h = false;
  private long i;
  private boolean j;
  private boolean k;
  private boolean l;
  private boolean m;
  private boolean n;
  private SharedPreferences o;
  private long p;
  private af q;
  private boolean r;
  private LinkedList<String> s;
  private LinkedList<String> t;
  private LinkedList<String> u;
  private int v = -1;
  private Boolean w;
  private com.google.ads.d x;
  private com.google.ads.e y;
  private f z;

  public d(Ad paramAd, Activity paramActivity, AdSize paramAdSize, String paramString, ViewGroup paramViewGroup, boolean paramBoolean)
  {
    this.r = paramBoolean;
    this.e = new g();
    this.c = null;
    this.d = null;
    this.k = false;
    this.p = 60000L;
    this.l = false;
    this.n = false;
    this.m = true;
    h localh;
    m localm2;
    AdView localAdView2;
    if (paramAdSize == null)
    {
      localh = h.a;
      if ((paramAd instanceof SwipeableDfpAdView))
        localh.a(true);
      if (paramActivity != null)
        break label188;
      localm2 = m.a();
      if (!(paramAd instanceof AdView))
        break label176;
      localAdView2 = (AdView)paramAd;
      label122: if (!(paramAd instanceof InterstitialAd))
        break label182;
    }
    label176: label182: for (InterstitialAd localInterstitialAd2 = (InterstitialAd)paramAd; ; localInterstitialAd2 = null)
    {
      this.b = new n(localm2, paramAd, localAdView2, localInterstitialAd2, paramString, null, null, paramViewGroup, localh, this);
      return;
      localh = h.a(paramAdSize, paramActivity.getApplicationContext());
      break;
      localAdView2 = null;
      break label122;
    }
    while (true)
    {
      synchronized (a)
      {
        label188: this.o = paramActivity.getApplicationContext().getSharedPreferences("GoogleAdMobAdsPrefs", 0);
        if (paramBoolean)
        {
          long l1 = this.o.getLong("Timeout" + paramString, -1L);
          if (l1 < 0L)
          {
            this.i = 5000L;
            m localm1 = m.a();
            if (!(paramAd instanceof AdView))
              break label453;
            localAdView1 = (AdView)paramAd;
            if (!(paramAd instanceof InterstitialAd))
              break label459;
            localInterstitialAd1 = (InterstitialAd)paramAd;
            this.b = new n(localm1, paramAd, localAdView1, localInterstitialAd1, paramString, paramActivity, paramActivity.getApplicationContext(), paramViewGroup, localh, this);
            this.q = new af(this);
            this.s = new LinkedList();
            this.t = new LinkedList();
            this.u = new LinkedList();
            a();
            AdUtil.h((Context)this.b.f.a());
            this.x = new com.google.ads.d();
            this.y = new com.google.ads.e(this);
            this.w = null;
            this.z = null;
            return;
          }
          this.i = l1;
        }
      }
      this.i = 60000L;
      continue;
      label453: AdView localAdView1 = null;
      continue;
      label459: InterstitialAd localInterstitialAd1 = null;
    }
  }

  private void a(f paramf, Boolean paramBoolean)
  {
    Object localObject = paramf.d();
    if (localObject == null)
    {
      localObject = new ArrayList();
      ((List)localObject).add("http://e.admob.com/imp?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@&adt=@gw_adt@&aec=@gw_aec@");
    }
    String str = paramf.b();
    a((List)localObject, paramf.a(), str, paramf.c(), paramBoolean, this.e.d(), this.e.e());
  }

  private void a(List<String> paramList, String paramString)
  {
    Object localObject;
    if (paramList == null)
    {
      localObject = new ArrayList();
      ((List)localObject).add("http://e.admob.com/nofill?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&adt=@gw_adt@&aec=@gw_aec@");
    }
    while (true)
    {
      a((List)localObject, null, null, paramString, null, this.e.d(), this.e.e());
      return;
      localObject = paramList;
    }
  }

  private void a(List<String> paramList, String paramString1, String paramString2, String paramString3, Boolean paramBoolean, String paramString4, String paramString5)
  {
    String str1 = AdUtil.a((Context)this.b.f.a());
    com.google.ads.b localb = com.google.ads.b.a();
    String str2 = localb.b().toString();
    String str3 = localb.c().toString();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      new Thread(new ae(com.google.ads.g.a((String)localIterator.next(), (String)this.b.h.a(), paramBoolean, str1, paramString1, paramString2, paramString3, str2, str3, paramString4, paramString5), (Context)this.b.f.a())).start();
    this.e.b();
  }

  private void b(f paramf, Boolean paramBoolean)
  {
    Object localObject = paramf.e();
    if (localObject == null)
    {
      localObject = new ArrayList();
      ((List)localObject).add("http://e.admob.com/clk?ad_loc=@gw_adlocid@&qdata=@gw_qdata@&ad_network_id=@gw_adnetid@&js=@gw_sdkver@&session_id=@gw_sessid@&seq_num=@gw_seqnum@&nr=@gw_adnetrefresh@");
    }
    String str = paramf.b();
    a((List)localObject, paramf.a(), str, paramf.c(), paramBoolean, null, null);
  }

  public void A()
  {
    while (true)
    {
      try
      {
        boolean bool = this.h;
        if (bool)
          return;
        if (this.d == null)
          break label134;
        if (!this.b.a())
          break label125;
        if ((((AdView)this.b.j.a()).isShown()) && (AdUtil.d()))
        {
          com.google.ads.util.b.c("Refreshing ad.");
          a(this.d);
          if (!this.n)
            break label98;
          f();
          continue;
        }
      }
      finally
      {
      }
      com.google.ads.util.b.a("Not refreshing because the ad is not visible.");
      continue;
      label98: ((Handler)m.a().c.a()).postDelayed(this.q, this.p);
      continue;
      label125: com.google.ads.util.b.a("Tried to refresh an ad that wasn't an AdView.");
      continue;
      label134: com.google.ads.util.b.a("Tried to refresh before calling loadAd().");
    }
  }

  public void B()
  {
    while (true)
    {
      try
      {
        com.google.ads.util.a.a(this.b.b());
        if (!this.k)
          break label101;
        this.k = false;
        if (this.w == null)
        {
          com.google.ads.util.b.b("isMediationFlag is null in show() with isReady() true. we should have an ad and know whether this is a mediation request or not. ");
          return;
        }
        if (this.w.booleanValue())
        {
          if (!this.y.c())
            continue;
          a(this.z, Boolean.valueOf(false));
          continue;
        }
      }
      finally
      {
      }
      AdActivity.launchAdActivity(this, new e("interstitial"));
      y();
      continue;
      label101: com.google.ads.util.b.c("Cannot show interstitial because it is not loaded and ready.");
    }
  }

  public void C()
  {
    try
    {
      if (this.c != null)
      {
        this.c.a();
        this.c = null;
      }
      if (this.f != null)
        this.f.stopLoading();
      return;
    }
    finally
    {
    }
  }

  protected void D()
  {
    try
    {
      Activity localActivity = (Activity)this.b.c.a();
      if (localActivity == null)
        com.google.ads.util.b.e("activity was null while trying to ping click tracking URLs.");
      while (true)
      {
        return;
        Iterator localIterator = this.u.iterator();
        while (localIterator.hasNext())
          new Thread(new ae((String)localIterator.next(), localActivity.getApplicationContext())).start();
      }
    }
    finally
    {
    }
  }

  protected void E()
  {
    try
    {
      this.c = null;
      this.k = true;
      this.f.setVisibility(0);
      if (this.b.a())
        a(this.f);
      this.e.g();
      if (this.b.a())
        y();
      com.google.ads.util.b.c("onReceiveAd()");
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onReceiveAd((Ad)this.b.a.a());
      this.b.l.a(this.b.m.a());
      this.b.m.a(null);
      return;
    }
    finally
    {
    }
  }

  public LinkedList<String> F()
  {
    return this.t;
  }

  public void a()
  {
    try
    {
      AdSize localAdSize = ((h)this.b.g.a()).c();
      if (AdUtil.a >= 14);
      for (Object localObject2 = new IcsUtil.IcsAdWebView(this.b, localAdSize); ; localObject2 = new AdWebView(this.b, localAdSize))
      {
        this.f = ((AdWebView)localObject2);
        this.f.setVisibility(8);
        this.g = i.a(this, a.d, true, this.b.b());
        this.f.setWebViewClient(this.g);
        m.a locala = (m.a)((m)this.b.d.a()).b.a();
        if ((AdUtil.a < ((Integer)locala.b.a()).intValue()) && (!((h)this.b.g.a()).a()))
        {
          com.google.ads.util.b.a("Disabling hardware acceleration for a banner.");
          this.f.g();
        }
        return;
      }
    }
    finally
    {
    }
  }

  public void a(float paramFloat)
  {
    try
    {
      long l1 = this.p;
      this.p = (()(1000.0F * paramFloat));
      if ((t()) && (this.p != l1))
      {
        f();
        g();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(int paramInt)
  {
    try
    {
      this.v = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ActivationOverlay localActivationOverlay = (ActivationOverlay)this.b.e.a();
    Context localContext1 = (Context)this.b.f.a();
    int i1;
    FrameLayout.LayoutParams localLayoutParams;
    int i3;
    if (paramInt3 < 0)
    {
      i1 = ((h)this.b.g.a()).c().getWidth();
      int i2 = AdUtil.a(localContext1, i1);
      Context localContext2 = (Context)this.b.f.a();
      if (paramInt4 < 0)
        paramInt4 = ((h)this.b.g.a()).c().getHeight();
      localLayoutParams = new FrameLayout.LayoutParams(i2, AdUtil.a(localContext2, paramInt4));
      if (paramInt3 >= 0)
        break label279;
      i3 = 0;
    }
    for (int i4 = 0; ; i4 = paramInt1)
    {
      if (i4 < 0);
      for (int i5 = ((ActivationOverlay)this.b.e.a()).d(); ; i5 = i4)
      {
        if (i3 < 0)
          i3 = ((ActivationOverlay)this.b.e.a()).c();
        ((ActivationOverlay)this.b.e.a()).setXPosition(i5);
        ((ActivationOverlay)this.b.e.a()).setYPosition(i3);
        localLayoutParams.setMargins(AdUtil.a((Context)this.b.f.a(), i5), AdUtil.a((Context)this.b.f.a(), i3), 0, 0);
        localActivationOverlay.setLayoutParams(localLayoutParams);
        return;
        i1 = paramInt3;
        break;
      }
      label279: i3 = paramInt2;
    }
  }

  public void a(long paramLong)
  {
    synchronized (a)
    {
      SharedPreferences.Editor localEditor = this.o.edit();
      localEditor.putLong("Timeout" + this.b.h, paramLong);
      localEditor.commit();
      if (this.r)
        this.i = paramLong;
      return;
    }
  }

  public void a(View paramView)
  {
    ((ViewGroup)this.b.i.a()).setVisibility(0);
    ((ViewGroup)this.b.i.a()).removeAllViews();
    ((ViewGroup)this.b.i.a()).addView(paramView);
    if (((h)this.b.g.a()).b())
    {
      ((d)this.b.b.a()).a((l)this.b.l.a(), false, -1, -1, -1, -1);
      if (((ActivationOverlay)this.b.e.a()).a())
        ((ViewGroup)this.b.i.a()).addView((View)this.b.e.a(), AdUtil.a((Context)this.b.f.a(), ((h)this.b.g.a()).c().getWidth()), AdUtil.a((Context)this.b.f.a(), ((h)this.b.g.a()).c().getHeight()));
    }
  }

  public void a(View paramView, com.google.ads.h paramh, f paramf, boolean paramBoolean)
  {
    try
    {
      com.google.ads.util.b.a("AdManager.onReceiveGWhirlAd() called.");
      this.k = true;
      this.z = paramf;
      if (this.b.a())
      {
        a(paramView);
        a(paramf, Boolean.valueOf(paramBoolean));
      }
      this.y.d(paramh);
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onReceiveAd((Ad)this.b.a.a());
      return;
    }
    finally
    {
    }
  }

  public void a(AdRequest.ErrorCode paramErrorCode)
  {
    try
    {
      this.c = null;
      if (paramErrorCode == AdRequest.ErrorCode.NETWORK_ERROR)
      {
        a(60.0F);
        if (!t())
          h();
      }
      if (this.b.b())
      {
        if (paramErrorCode != AdRequest.ErrorCode.NO_FILL)
          break label126;
        this.e.B();
      }
      while (true)
      {
        com.google.ads.util.b.c("onFailedToReceiveAd(" + paramErrorCode + ")");
        AdListener localAdListener = (AdListener)this.b.o.a();
        if (localAdListener != null)
          localAdListener.onFailedToReceiveAd((Ad)this.b.a.a(), paramErrorCode);
        return;
        label126: if (paramErrorCode == AdRequest.ErrorCode.NETWORK_ERROR)
          this.e.z();
      }
    }
    finally
    {
    }
  }

  public void a(AdRequest paramAdRequest)
  {
    while (true)
    {
      try
      {
        com.google.ads.util.b.d("v6.4.1 RC00");
        if (this.h)
        {
          com.google.ads.util.b.e("loadAd called after ad was destroyed.");
          return;
        }
        if (q())
        {
          com.google.ads.util.b.e("loadAd called while the ad is already loading, so aborting.");
          continue;
        }
      }
      finally
      {
      }
      if (AdActivity.isShowing())
      {
        com.google.ads.util.b.e("loadAd called while an interstitial or landing page is displayed, so aborting");
      }
      else if ((AdUtil.c((Context)this.b.f.a())) && (AdUtil.b((Context)this.b.f.a())))
      {
        long l1 = this.o.getLong("GoogleAdMobDoritosLife", 60000L);
        if (at.a((Context)this.b.f.a(), l1))
          at.a((Activity)this.b.c.a());
        this.k = false;
        this.s.clear();
        this.t.clear();
        this.d = paramAdRequest;
        if (this.x.a())
        {
          this.y.a(this.x.b(), paramAdRequest);
        }
        else
        {
          l locall = new l(this.b);
          this.b.m.a(locall);
          this.c = ((c)locall.b.a());
          this.c.a(paramAdRequest);
        }
      }
    }
  }

  public void a(com.google.ads.c paramc)
  {
    try
    {
      this.c = null;
      this.y.a(paramc, this.d);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(f paramf, boolean paramBoolean)
  {
    try
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      com.google.ads.util.b.a(String.format(localLocale, "AdManager.onGWhirlAdClicked(%b) called.", arrayOfObject));
      b(paramf, Boolean.valueOf(paramBoolean));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(l paraml, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ActivationOverlay localActivationOverlay = (ActivationOverlay)this.b.e.a();
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      localActivationOverlay.setOverlayActivated(bool);
      a(paramInt1, paramInt2, paramInt3, paramInt4);
      if (this.b.q.a() != null)
      {
        if (!paramBoolean)
          break;
        ((SwipeableAdListener)this.b.q.a()).onAdActivated((Ad)this.b.a.a());
      }
      return;
    }
    ((SwipeableAdListener)this.b.q.a()).onAdDeactivated((Ad)this.b.a.a());
  }

  public void a(String paramString)
  {
    this.B = paramString;
    Uri localUri = new Uri.Builder().encodedQuery(paramString).build();
    StringBuilder localStringBuilder = new StringBuilder();
    HashMap localHashMap = AdUtil.b(localUri);
    Iterator localIterator = localHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuilder.append(str).append(" = ").append((String)localHashMap.get(str)).append("\n");
    }
    this.A = localStringBuilder.toString().trim();
    if (TextUtils.isEmpty(this.A))
      this.A = null;
  }

  public void a(String paramString1, String paramString2)
  {
    try
    {
      AppEventListener localAppEventListener = (AppEventListener)this.b.p.a();
      if (localAppEventListener != null)
        localAppEventListener.onAppEvent((Ad)this.b.a.a(), paramString1, paramString2);
      return;
    }
    finally
    {
    }
  }

  protected void a(LinkedList<String> paramLinkedList)
  {
    try
    {
      Iterator localIterator = paramLinkedList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        com.google.ads.util.b.a("Adding a click tracking URL: " + str);
      }
    }
    finally
    {
    }
    this.u = paramLinkedList;
  }

  public void a(boolean paramBoolean)
  {
    try
    {
      this.j = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b()
  {
    try
    {
      if (this.y != null)
        this.y.b();
      this.b.o.a(null);
      this.b.p.a(null);
      C();
      f();
      if (this.f != null)
        this.f.destroy();
      this.h = true;
      return;
    }
    finally
    {
    }
  }

  public void b(long paramLong)
  {
    if (paramLong > 0L);
    try
    {
      this.o.edit().putLong("GoogleAdMobDoritosLife", paramLong).commit();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b(com.google.ads.c paramc)
  {
    try
    {
      com.google.ads.util.b.a("AdManager.onGWhirlNoFill() called.");
      a(paramc.i(), paramc.c());
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onFailedToReceiveAd((Ad)this.b.a.a(), AdRequest.ErrorCode.NO_FILL);
      return;
    }
    finally
    {
    }
  }

  protected void b(String paramString)
  {
    try
    {
      com.google.ads.util.b.a("Adding a tracking URL: " + paramString);
      this.s.add(paramString);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b(boolean paramBoolean)
  {
    this.w = Boolean.valueOf(paramBoolean);
  }

  public String c()
  {
    return this.A;
  }

  protected void c(String paramString)
  {
    try
    {
      com.google.ads.util.b.a("Adding a manual tracking URL: " + paramString);
      F().add(paramString);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String d()
  {
    return this.B;
  }

  public void e()
  {
    try
    {
      this.m = false;
      com.google.ads.util.b.a("Refreshing is no longer allowed on this AdView.");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void f()
  {
    try
    {
      if (this.l)
      {
        com.google.ads.util.b.a("Disabling refreshing.");
        ((Handler)m.a().c.a()).removeCallbacks(this.q);
        this.l = false;
      }
      while (true)
      {
        return;
        com.google.ads.util.b.a("Refreshing is already disabled.");
      }
    }
    finally
    {
    }
  }

  public void g()
  {
    while (true)
    {
      try
      {
        this.n = false;
        if (!this.b.a())
          break label118;
        if (this.m)
        {
          if (!this.l)
          {
            com.google.ads.util.b.a("Enabling refreshing every " + this.p + " milliseconds.");
            ((Handler)m.a().c.a()).postDelayed(this.q, this.p);
            this.l = true;
            return;
          }
          com.google.ads.util.b.a("Refreshing is already enabled.");
          continue;
        }
      }
      finally
      {
      }
      com.google.ads.util.b.a("Refreshing disabled on this AdView");
      continue;
      label118: com.google.ads.util.b.a("Tried to enable refreshing on something other than an AdView.");
    }
  }

  public void h()
  {
    g();
    this.n = true;
  }

  public n i()
  {
    return this.b;
  }

  public com.google.ads.d j()
  {
    try
    {
      com.google.ads.d locald = this.x;
      return locald;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public c k()
  {
    try
    {
      c localc = this.c;
      return localc;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public AdWebView l()
  {
    try
    {
      AdWebView localAdWebView = this.f;
      return localAdWebView;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public i m()
  {
    try
    {
      i locali = this.g;
      return locali;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public g n()
  {
    return this.e;
  }

  public int o()
  {
    try
    {
      int i1 = this.v;
      return i1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long p()
  {
    return this.i;
  }

  public boolean q()
  {
    try
    {
      c localc = this.c;
      if (localc != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean r()
  {
    try
    {
      boolean bool = this.j;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean s()
  {
    try
    {
      boolean bool = this.k;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean t()
  {
    try
    {
      boolean bool = this.l;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void u()
  {
    try
    {
      this.e.C();
      com.google.ads.util.b.c("onDismissScreen()");
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onDismissScreen((Ad)this.b.a.a());
      return;
    }
    finally
    {
    }
  }

  public void v()
  {
    try
    {
      com.google.ads.util.b.c("onPresentScreen()");
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onPresentScreen((Ad)this.b.a.a());
      return;
    }
    finally
    {
    }
  }

  public void w()
  {
    try
    {
      com.google.ads.util.b.c("onLeaveApplication()");
      AdListener localAdListener = (AdListener)this.b.o.a();
      if (localAdListener != null)
        localAdListener.onLeaveApplication((Ad)this.b.a.a());
      return;
    }
    finally
    {
    }
  }

  public void x()
  {
    this.e.f();
    D();
  }

  public void y()
  {
    try
    {
      Activity localActivity = (Activity)this.b.c.a();
      if (localActivity == null)
        com.google.ads.util.b.e("activity was null while trying to ping tracking URLs.");
      while (true)
      {
        return;
        Iterator localIterator = this.s.iterator();
        while (localIterator.hasNext())
          new Thread(new ae((String)localIterator.next(), localActivity.getApplicationContext())).start();
      }
    }
    finally
    {
    }
  }

  public void z()
  {
    try
    {
      Activity localActivity = (Activity)this.b.c.a();
      if (localActivity == null)
        com.google.ads.util.b.e("activity was null while trying to ping manual tracking URLs.");
      while (true)
      {
        return;
        Iterator localIterator = this.t.iterator();
        while (localIterator.hasNext())
          new Thread(new ae((String)localIterator.next(), localActivity.getApplicationContext())).start();
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.internal.d
 * JD-Core Version:    0.6.2
 */