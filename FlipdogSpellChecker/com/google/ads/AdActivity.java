package com.google.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.internal.i;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.f;
import com.google.ads.util.g;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;
import com.google.ads.util.i.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdActivity extends Activity
  implements View.OnClickListener
{
  public static final String BASE_URL_PARAM = "baseurl";
  public static final String COMPONENT_NAME_PARAM = "c";
  public static final String CUSTOM_CLOSE_PARAM = "custom_close";
  public static final String HTML_PARAM = "html";
  public static final String INTENT_ACTION_PARAM = "i";
  public static final String INTENT_EXTRAS_PARAM = "e";
  public static final String INTENT_FLAGS_PARAM = "f";
  public static final String ORIENTATION_PARAM = "o";
  public static final String PACKAGE_NAME_PARAM = "p";
  public static final String TYPE_PARAM = "m";
  public static final String URL_PARAM = "u";
  private static final a a = (a)a.a.b();
  private static final Object b = new Object();
  private static AdActivity c = null;
  private static d d = null;
  private static AdActivity e = null;
  private static AdActivity f = null;
  private static final StaticMethodWrapper g = new StaticMethodWrapper();
  private AdWebView h;
  private FrameLayout i;
  private int j;
  private ViewGroup k = null;
  private boolean l;
  private long m;
  private RelativeLayout n;
  private AdActivity o = null;
  private boolean p;
  private boolean q;
  private boolean r;
  private boolean s;
  private AdVideoView t;

  private RelativeLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, 0);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    return localLayoutParams;
  }

  private void a(String paramString)
  {
    b.b(paramString);
    finish();
  }

  private void a(String paramString, Throwable paramThrowable)
  {
    b.b(paramString, paramThrowable);
    finish();
  }

  private void e()
  {
    if (!this.l)
      if (this.h != null)
      {
        a.b(this.h);
        this.h.setAdActivity(null);
        this.h.setIsExpandedMraid(false);
        if ((!this.q) && (this.n != null) && (this.k != null))
        {
          if ((!this.r) || (this.s))
            break label238;
          b.a("Disabling hardware acceleration on collapsing MRAID WebView.");
          this.h.g();
        }
      }
    while (true)
    {
      this.n.removeView(this.h);
      this.k.addView(this.h);
      if (this.t != null)
      {
        this.t.e();
        this.t = null;
      }
      if (this == c)
        c = null;
      f = this.o;
      synchronized (b)
      {
        if ((d != null) && (this.q) && (this.h != null))
        {
          if (this.h == d.l())
            d.a();
          this.h.stopLoading();
        }
        if (this == e)
        {
          e = null;
          if (d != null)
          {
            d.u();
            d = null;
          }
        }
        else
        {
          this.l = true;
          b.a("AdActivity is closing.");
          return;
          label238: if ((this.r) || (!this.s))
            continue;
          b.a("Re-enabling hardware acceleration on collapsing MRAID WebView.");
          this.h.h();
          continue;
        }
        b.e("currentAdManager is null while trying to destroy AdActivity.");
      }
    }
  }

  public static boolean isShowing()
  {
    return g.isShowing();
  }

  public static void launchAdActivity(d paramd, com.google.ads.internal.e parame)
  {
    g.launchAdActivity(paramd, parame);
  }

  public static boolean leftApplication()
  {
    return g.leftApplication();
  }

  protected View a(int paramInt, boolean paramBoolean)
  {
    this.j = ((int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics()));
    this.i = new FrameLayout(getApplicationContext());
    this.i.setMinimumWidth(this.j);
    this.i.setMinimumHeight(this.j);
    this.i.setOnClickListener(this);
    setCustomClose(paramBoolean);
    return this.i;
  }

  protected AdVideoView a(Activity paramActivity)
  {
    return new AdVideoView(paramActivity, this.h);
  }

  protected void a(AdWebView paramAdWebView, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3)
  {
    requestWindowFeature(1);
    Window localWindow = getWindow();
    localWindow.setFlags(1024, 1024);
    if (AdUtil.a >= 11)
    {
      if (!this.r)
        break label105;
      b.a("Enabling hardware acceleration on the AdActivity window.");
      g.a(localWindow);
    }
    while (true)
    {
      ViewParent localViewParent = paramAdWebView.getParent();
      if (localViewParent != null)
      {
        if (!paramBoolean2)
          break label126;
        if (!(localViewParent instanceof ViewGroup))
          break;
        this.k = ((ViewGroup)localViewParent);
        this.k.removeView(paramAdWebView);
      }
      if (paramAdWebView.i() == null)
        break label134;
      a("Interstitial created with an AdWebView that is already in use by another AdActivity.");
      return;
      label105: b.a("Disabling hardware acceleration on the AdActivity WebView.");
      paramAdWebView.g();
    }
    a("MRAID banner was not a child of a ViewGroup.");
    return;
    label126: a("Interstitial created with an AdWebView that has a parent.");
    return;
    label134: setRequestedOrientation(paramInt);
    paramAdWebView.setAdActivity(this);
    int i1;
    label153: View localView;
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramBoolean2)
    {
      i1 = 50;
      localView = a(i1, paramBoolean3);
      this.n.addView(paramAdWebView, -1, -1);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      if (!paramBoolean2)
        break label264;
      localLayoutParams.addRule(10);
      localLayoutParams.addRule(11);
    }
    while (true)
    {
      this.n.addView(localView, localLayoutParams);
      this.n.setKeepScreenOn(true);
      setContentView(this.n);
      this.n.getRootView().setBackgroundColor(-16777216);
      if (!paramBoolean1)
        break;
      a.a(paramAdWebView);
      return;
      i1 = 32;
      break label153;
      label264: localLayoutParams.addRule(10);
      localLayoutParams.addRule(9);
    }
  }

  protected void a(d paramd)
  {
    this.h = null;
    this.m = SystemClock.elapsedRealtime();
    this.p = true;
    synchronized (b)
    {
      if (c == null)
      {
        c = this;
        paramd.w();
      }
      return;
    }
  }

  protected void a(HashMap<String, String> paramHashMap, d paramd)
  {
    int i1 = 0;
    if (paramHashMap == null)
    {
      a("Could not get the paramMap in launchIntent()");
      return;
    }
    Intent localIntent = new Intent();
    String str1 = (String)paramHashMap.get("u");
    String str2 = (String)paramHashMap.get("m");
    String str3 = (String)paramHashMap.get("i");
    String str4 = (String)paramHashMap.get("p");
    String str5 = (String)paramHashMap.get("c");
    String str6 = (String)paramHashMap.get("f");
    String str7 = (String)paramHashMap.get("e");
    int i2;
    if (!TextUtils.isEmpty(str1))
      i2 = 1;
    while (true)
    {
      int i3;
      if (!TextUtils.isEmpty(str2))
      {
        i3 = 1;
        label122: if ((i2 == 0) || (i3 == 0))
          break label431;
        localIntent.setDataAndType(Uri.parse(str1), str2);
        label145: if (TextUtils.isEmpty(str3))
          break label466;
        localIntent.setAction(str3);
        label161: if ((!TextUtils.isEmpty(str4)) && (AdUtil.a >= 4))
          com.google.ads.util.e.a(localIntent, str4);
        if (!TextUtils.isEmpty(str5))
        {
          String[] arrayOfString = str5.split("/");
          if (arrayOfString.length < 2)
            b.e("Warning: Could not parse component name from open GMSG: " + str5);
          localIntent.setClassName(arrayOfString[0], arrayOfString[1]);
        }
        if (TextUtils.isEmpty(str6));
      }
      try
      {
        int i6 = Integer.parseInt(str6);
        i5 = i6;
        localIntent.addFlags(i5);
        if (TextUtils.isEmpty(str7));
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
        {
          String str8;
          JSONObject localJSONObject2;
          try
          {
            int i5;
            JSONObject localJSONObject1 = new JSONObject(str7);
            JSONArray localJSONArray = localJSONObject1.names();
            if (i1 < localJSONArray.length())
            {
              str8 = localJSONArray.getString(i1);
              localJSONObject2 = localJSONObject1.getJSONObject(str8);
              int i4 = localJSONObject2.getInt("t");
              switch (i4)
              {
              default:
                b.e("Warning: Unknown type in extras from open GMSG: " + str8 + " (type: " + i4 + ")");
                i1++;
                continue;
                i2 = 0;
                break;
                i3 = 0;
                break label122;
                if (i2 != 0)
                {
                  localIntent.setData(Uri.parse(str1));
                  break label145;
                }
                if (i3 == 0)
                  break label145;
                localIntent.setType(str2);
                break label145;
                if (i2 == 0)
                  break label161;
                localIntent.setAction("android.intent.action.VIEW");
                break label161;
                localNumberFormatException = localNumberFormatException;
                b.e("Warning: Could not parse flags from open GMSG: " + str6);
                i5 = 0;
                break;
              case 1:
                label431: label466: localIntent.putExtra(str8, localJSONObject2.getBoolean("v"));
                continue;
              case 2:
              case 3:
              case 4:
              case 5:
              }
            }
          }
          catch (JSONException localJSONException)
          {
            b.e("Warning: Could not parse extras from open GMSG: " + str7);
          }
          if (!localIntent.filterEquals(new Intent()))
            break label659;
          a("Tried to launch empty intent.");
          return;
          localIntent.putExtra(str8, localJSONObject2.getDouble("v"));
          continue;
          localIntent.putExtra(str8, localJSONObject2.getInt("v"));
          continue;
          localIntent.putExtra(str8, localJSONObject2.getLong("v"));
          continue;
          localIntent.putExtra(str8, localJSONObject2.getString("v"));
        }
        try
        {
          label659: b.a("Launching an intent from AdActivity: " + localIntent);
          startActivity(localIntent);
          a(paramd);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          a(localActivityNotFoundException.getMessage(), localActivityNotFoundException);
        }
      }
    }
  }

  public AdVideoView getAdVideoView()
  {
    return this.t;
  }

  public AdWebView getOpeningAdWebView()
  {
    if (this.o != null)
      return this.o.h;
    synchronized (b)
    {
      if (d == null)
      {
        b.e("currentAdManager was null while trying to get the opening AdWebView.");
        return null;
      }
    }
    AdWebView localAdWebView = d.l();
    if (localAdWebView != this.h)
      return localAdWebView;
    return null;
  }

  public void moveAdVideoView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.t != null)
    {
      this.t.setLayoutParams(a(paramInt1, paramInt2, paramInt3, paramInt4));
      this.t.requestLayout();
    }
  }

  public void newAdVideoView(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.t == null)
    {
      this.t = a(this);
      this.n.addView(this.t, 0, a(paramInt1, paramInt2, paramInt3, paramInt4));
      synchronized (b)
      {
        if (d == null)
        {
          b.e("currentAdManager was null while trying to get the opening AdWebView.");
          return;
        }
        d.m().b(false);
        return;
      }
    }
  }

  public void onClick(View paramView)
  {
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.l = false;
    d locald;
    boolean bool1;
    Bundle localBundle;
    while (true)
    {
      synchronized (b)
      {
        if (d != null)
        {
          locald = d;
          if (e == null)
          {
            e = this;
            locald.v();
          }
          if ((this.o == null) && (f != null))
            this.o = f;
          f = this;
          if (((locald.i().a()) && (e == this)) || ((locald.i().b()) && (this.o == e)))
            locald.x();
          bool1 = locald.r();
          m.a locala = (m.a)((m)locald.i().d.a()).b.a();
          if (AdUtil.a >= ((Integer)locala.b.a()).intValue())
          {
            bool2 = true;
            this.s = bool2;
            if (AdUtil.a < ((Integer)locala.d.a()).intValue())
              break label268;
            bool3 = true;
            this.r = bool3;
            this.n = null;
            this.p = false;
            this.q = true;
            this.t = null;
            localBundle = getIntent().getBundleExtra("com.google.ads.AdOpener");
            if (localBundle != null)
              break;
            a("Could not get the Bundle used to create AdActivity.");
          }
        }
        else
        {
          a("Could not get currentAdManager.");
          return;
        }
      }
      boolean bool2 = false;
      continue;
      label268: boolean bool3 = false;
    }
    com.google.ads.internal.e locale = new com.google.ads.internal.e(localBundle);
    String str1 = locale.b();
    HashMap localHashMap = locale.c();
    if (str1.equals("intent"))
    {
      a(localHashMap, locald);
      return;
    }
    this.n = new RelativeLayout(getApplicationContext());
    if (str1.equals("webapp"))
    {
      this.h = new AdWebView(locald.i(), null);
      Map localMap = a.d;
      boolean bool7;
      String str3;
      String str4;
      label463: String str5;
      int i2;
      label490: AdWebView localAdWebView;
      if (!bool1)
      {
        bool7 = true;
        i locali = i.a(locald, localMap, true, bool7);
        locali.d(true);
        if (bool1)
          locali.a(true);
        this.h.setWebViewClient(locali);
        String str2 = (String)localHashMap.get("u");
        str3 = (String)localHashMap.get("baseurl");
        str4 = (String)localHashMap.get("html");
        if (str2 == null)
          break label540;
        this.h.loadUrl(str2);
        str5 = (String)localHashMap.get("o");
        if (!"p".equals(str5))
          break label574;
        i2 = AdUtil.b();
        localAdWebView = this.h;
        if ((localHashMap == null) || (!"1".equals(localHashMap.get("custom_close"))))
          break label616;
      }
      label540: label574: label616: for (boolean bool8 = true; ; bool8 = false)
      {
        a(localAdWebView, false, i2, bool1, bool8);
        return;
        bool7 = false;
        break;
        if (str4 != null)
        {
          this.h.loadDataWithBaseURL(str3, str4, "text/html", "utf-8", null);
          break label463;
        }
        a("Could not get the URL or HTML parameter to show a web app.");
        return;
        if ("l".equals(str5))
        {
          i2 = AdUtil.a();
          break label490;
        }
        if (this == e)
        {
          i2 = locald.o();
          break label490;
        }
        i2 = -1;
        break label490;
      }
    }
    int i1;
    boolean bool5;
    boolean bool4;
    if ((str1.equals("interstitial")) || (str1.equals("expand")))
    {
      this.h = locald.l();
      i1 = locald.o();
      if (str1.equals("expand"))
      {
        this.h.setIsExpandedMraid(true);
        this.q = false;
        bool5 = false;
        if (localHashMap != null)
        {
          boolean bool6 = "1".equals(localHashMap.get("custom_close"));
          bool5 = false;
          if (bool6)
            bool5 = true;
        }
        if ((!this.r) || (this.s))
          break label809;
        b.a("Re-enabling hardware acceleration on expanding MRAID WebView.");
        this.h.h();
        bool4 = bool5;
      }
    }
    while (true)
    {
      a(this.h, true, i1, bool1, bool4);
      return;
      bool4 = this.h.j();
      continue;
      a("Unknown AdOpener, <action: " + str1 + ">");
      return;
      label809: bool4 = bool5;
    }
  }

  public void onDestroy()
  {
    if (this.n != null)
      this.n.removeAllViews();
    if (isFinishing())
    {
      e();
      if ((this.q) && (this.h != null))
      {
        this.h.stopLoading();
        this.h.destroy();
        this.h = null;
      }
    }
    super.onDestroy();
  }

  public void onPause()
  {
    if (isFinishing())
      e();
    super.onPause();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    if ((this.p) && (paramBoolean) && (SystemClock.elapsedRealtime() - this.m > 250L))
    {
      b.d("Launcher AdActivity got focus and is closing.");
      finish();
    }
    super.onWindowFocusChanged(paramBoolean);
  }

  public void setCustomClose(boolean paramBoolean)
  {
    if (this.i != null)
    {
      this.i.removeAllViews();
      if (!paramBoolean)
      {
        ImageButton localImageButton = new ImageButton(this);
        localImageButton.setImageResource(17301527);
        localImageButton.setBackgroundColor(0);
        localImageButton.setOnClickListener(this);
        localImageButton.setPadding(0, 0, 0, 0);
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(this.j, this.j, 17);
        this.i.addView(localImageButton, localLayoutParams);
      }
    }
  }

  public static class StaticMethodWrapper
  {
    public boolean isShowing()
    {
      while (true)
      {
        synchronized (AdActivity.a())
        {
          if (AdActivity.b() != null)
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
    }

    public void launchAdActivity(d paramd, com.google.ads.internal.e parame)
    {
      Activity localActivity;
      synchronized (AdActivity.a())
      {
        if (AdActivity.d() == null)
          AdActivity.b(paramd);
        while (AdActivity.d() == paramd)
        {
          localActivity = (Activity)paramd.i().c.a();
          if (localActivity != null)
            break;
          b.e("activity was null while launching an AdActivity.");
          return;
        }
        b.b("Tried to launch a new AdActivity with a different AdManager.");
        return;
      }
      Intent localIntent = new Intent(localActivity.getApplicationContext(), AdActivity.class);
      localIntent.putExtra("com.google.ads.AdOpener", parame.a());
      try
      {
        b.a("Launching AdActivity.");
        localActivity.startActivity(localIntent);
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        b.b("Activity not found.", localActivityNotFoundException);
      }
    }

    public boolean leftApplication()
    {
      while (true)
      {
        synchronized (AdActivity.a())
        {
          if (AdActivity.c() != null)
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.AdActivity
 * JD-Core Version:    0.6.2
 */