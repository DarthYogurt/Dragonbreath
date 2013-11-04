package com.google.ads;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.util.a;
import com.google.ads.util.b;
import com.google.ads.util.i.b;
import java.util.HashMap;

public class h
{
  final com.google.ads.internal.h a;
  private final f b;
  private boolean c;
  private boolean d;
  private g.a e;
  private final e f;
  private MediationAdapter<?, ?> g;
  private boolean h;
  private boolean i;
  private View j;
  private final String k;
  private final AdRequest l;
  private final HashMap<String, String> m;

  public h(e parame, com.google.ads.internal.h paramh, f paramf, String paramString, AdRequest paramAdRequest, HashMap<String, String> paramHashMap)
  {
    a.b(TextUtils.isEmpty(paramString));
    this.f = parame;
    this.a = paramh;
    this.b = paramf;
    this.k = paramString;
    this.l = paramAdRequest;
    this.m = paramHashMap;
    this.c = false;
    this.d = false;
    this.e = null;
    this.g = null;
    this.h = false;
    this.i = false;
    this.j = null;
  }

  public f a()
  {
    return this.b;
  }

  public void a(Activity paramActivity)
  {
    try
    {
      a.b(this.h, "startLoadAdTask has already been called.");
      this.h = true;
      ((Handler)m.a().c.a()).post(new i(this, paramActivity, this.k, this.l, this.m));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void a(View paramView)
  {
    try
    {
      this.j = paramView;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void a(MediationAdapter<?, ?> paramMediationAdapter)
  {
    try
    {
      this.g = paramMediationAdapter;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void a(boolean paramBoolean, g.a parama)
  {
    try
    {
      this.d = paramBoolean;
      this.c = true;
      this.e = parama;
      notify();
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
      a.a(this.h, "destroy() called but startLoadAdTask has not been called.");
      ((Handler)m.a().c.a()).post(new Runnable()
      {
        public void run()
        {
          if (h.this.l())
            a.b(h.a(h.this));
          try
          {
            h.a(h.this).destroy();
            b.a("Called destroy() for adapter with class: " + h.a(h.this).getClass().getName());
            return;
          }
          catch (Throwable localThrowable)
          {
            b.b("Error while destroying adapter (" + h.this.h() + "):", localThrowable);
          }
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean c()
  {
    try
    {
      boolean bool = this.c;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean d()
  {
    try
    {
      a.a(this.c, "isLoadAdTaskSuccessful() called when isLoadAdTaskDone() is false.");
      boolean bool = this.d;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public g.a e()
  {
    try
    {
      if (this.e == null);
      for (g.a locala = g.a.d; ; locala = this.e)
        return locala;
    }
    finally
    {
    }
  }

  public View f()
  {
    try
    {
      a.a(this.c, "getAdView() called when isLoadAdTaskDone() is false.");
      View localView = this.j;
      return localView;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void g()
  {
    try
    {
      a.a(this.a.a());
      try
      {
        final MediationInterstitialAdapter localMediationInterstitialAdapter = (MediationInterstitialAdapter)this.g;
        ((Handler)m.a().c.a()).post(new Runnable()
        {
          public void run()
          {
            try
            {
              localMediationInterstitialAdapter.showInterstitial();
              return;
            }
            catch (Throwable localThrowable)
            {
              b.b("Error while telling adapter (" + h.this.h() + ") ad to show interstitial: ", localThrowable);
            }
          }
        });
        return;
      }
      catch (ClassCastException localClassCastException)
      {
        while (true)
          b.b("In Ambassador.show(): ambassador.adapter does not implement the MediationInterstitialAdapter interface.", localClassCastException);
      }
    }
    finally
    {
    }
  }

  public String h()
  {
    try
    {
      if (this.g != null)
      {
        String str2 = this.g.getClass().getName();
        str1 = str2;
        return str1;
      }
      String str1 = "\"adapter was not created.\"";
    }
    finally
    {
    }
  }

  MediationAdapter<?, ?> i()
  {
    try
    {
      MediationAdapter localMediationAdapter = this.g;
      return localMediationAdapter;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  e j()
  {
    return this.f;
  }

  void k()
  {
    try
    {
      this.i = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  boolean l()
  {
    try
    {
      boolean bool = this.i;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.h
 * JD-Core Version:    0.6.2
 */