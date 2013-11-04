package com.adfonic.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.adfonic.android.api.Request;
import com.adfonic.android.ormma.ExpandProperties;
import com.adfonic.android.ormma.OrmmaBridge;
import com.adfonic.android.ormma.OrmmaView;
import com.adfonic.android.ormma.js.JsOrmmaBridge;
import com.adfonic.android.utils.HtmlFormatter;
import com.adfonic.android.utils.Log;
import com.adfonic.android.view.BaseAdfonicView;

public class MyAdfonicView extends BaseAdfonicView
  implements ViewTreeObserver.OnGlobalLayoutListener, OrmmaView
{
  private static final String a = "AdfonicOrmmaBridge";
  private OrmmaBridge b;
  private ViewGroup c;
  private ViewGroup.LayoutParams d;
  private ExpandProperties e;
  private int f;
  private int g;
  private int h = -1;
  private int i = -1;
  private int j = -1;
  private int k;
  private int l;
  private boolean m = false;

  public MyAdfonicView(Context paramContext)
  {
    this(paramContext, null);
  }

  public MyAdfonicView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, -1);
  }

  public MyAdfonicView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    m();
  }

  private float a(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }

  private int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int n = View.MeasureSpec.getMode(paramInt1);
    if (n == 0)
      paramInt1 = c(paramInt1, paramInt3);
    do
    {
      do
      {
        return paramInt1;
        if (n != -2147483648)
          break;
      }
      while ((paramInt2 == -1) || (paramInt2 != -2));
      return c(paramInt1, paramInt3);
    }
    while (n == 1073741824);
    return paramInt1;
  }

  private Display a(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
  }

  private LinearLayout a(LinearLayout.LayoutParams paramLayoutParams)
  {
    LinearLayout localLinearLayout = new LinearLayout(getContext());
    localLinearLayout.setOnTouchListener(new c(this));
    localLinearLayout.addView(this, paramLayoutParams);
    return localLinearLayout;
  }

  private void a(int paramInt)
  {
    int n = View.MeasureSpec.getSize(paramInt);
    Rect localRect = new Rect();
    ((Activity)getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    int i1 = localRect.top;
    int i2 = ((Activity)getContext()).getWindowManager().getDefaultDisplay().getHeight();
    if (i2 - i1 - n > i2 / 3);
    for (boolean bool = true; ; bool = false)
    {
      a(bool);
      return;
    }
  }

  private int c(int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0)
      paramInt1 = View.MeasureSpec.makeMeasureSpec((int)(paramInt2 * a((Activity)getContext())), 1073741824);
    return paramInt1;
  }

  private void d(int paramInt1, int paramInt2)
  {
    if (!this.m)
    {
      Log.v("returning as do not have to manage state");
      return;
    }
    Log.v("changeContentArea");
    ((Activity)getContext()).runOnUiThread(new a(this, paramInt1, paramInt2));
  }

  private void e(int paramInt1, int paramInt2)
  {
    this.c.removeView(this);
    LinearLayout.LayoutParams localLayoutParams = f(paramInt1, paramInt2);
    LinearLayout localLinearLayout = a(localLayoutParams);
    ViewGroup localViewGroup = (ViewGroup)this.c.getRootView();
    if (paramInt2 == this.g)
      ((Activity)getContext()).getWindow().setFlags(1024, 1024);
    localLinearLayout.setPadding((this.f - paramInt1) / 2, (this.g - paramInt2) / 2, 0, 0);
    localViewGroup.addView(localLinearLayout, localLayoutParams);
  }

  private LinearLayout.LayoutParams f(int paramInt1, int paramInt2)
  {
    return new LinearLayout.LayoutParams(paramInt1, paramInt2);
  }

  private void m()
  {
    o();
    p();
    q();
    r();
    t();
    n();
  }

  private void n()
  {
    this.b = new JsOrmmaBridge(this);
    addJavascriptInterface(this.b, "AdfonicOrmmaBridge");
    this.b.ready();
  }

  private void o()
  {
    setScrollContainer(false);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    setBackgroundColor(0);
  }

  private void p()
  {
    getSettings().setJavaScriptEnabled(true);
  }

  private void q()
  {
    getViewTreeObserver().addOnGlobalLayoutListener(this);
  }

  private void r()
  {
  }

  private void s()
  {
  }

  private void t()
  {
  }

  private void u()
  {
    if (!this.m)
    {
      Log.v("returning as do not have to manage state");
      return;
    }
    ((Activity)getContext()).runOnUiThread(new b(this));
  }

  private void v()
  {
    ViewGroup localViewGroup = (ViewGroup)getParent();
    localViewGroup.removeView(this);
    ((ViewGroup)this.c.getRootView()).removeView(localViewGroup);
    ((Activity)getContext()).getWindow().clearFlags(1024);
    this.c.addView(this, 1, this.d);
  }

  private void w()
  {
    Display localDisplay = a(getContext());
    this.j = localDisplay.getOrientation();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    this.f = localDisplayMetrics.widthPixels;
    this.g = localDisplayMetrics.heightPixels;
    this.i = getWidth();
    this.h = getHeight();
    this.d = getLayoutParams();
    int[] arrayOfInt = new int[2];
    getLocationOnScreen(arrayOfInt);
    this.l = arrayOfInt[0];
    this.k = arrayOfInt[1];
    x();
    this.c = ((ViewGroup)getParent());
  }

  private void x()
  {
    ExpandProperties localExpandProperties = g();
    localExpandProperties.setHeight(b());
    localExpandProperties.setWidth(a());
    a(localExpandProperties);
  }

  public int a()
  {
    return this.f;
  }

  public void a(int paramInt1, int paramInt2)
  {
    d(paramInt1, paramInt2);
  }

  protected void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.b.onSizeChange(paramInt1, paramInt2);
  }

  public void a(ExpandProperties paramExpandProperties)
  {
    if (paramExpandProperties.isUseCustomClose())
      s();
    while (true)
    {
      this.e = paramExpandProperties;
      return;
      t();
    }
  }

  public void a(OrmmaBridge paramOrmmaBridge)
  {
    this.b = paramOrmmaBridge;
  }

  public void a(String paramString)
  {
    if (paramString == null)
      return;
    try
    {
      super.loadUrl("javascript:" + paramString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void a(boolean paramBoolean)
  {
  }

  public int b()
  {
    return this.g;
  }

  protected void b(int paramInt1, int paramInt2)
  {
    a(paramInt2);
    int i1;
    int n;
    if (getRequest() != null)
    {
      i1 = (int)getRequest().getAdWidth();
      n = (int)getRequest().getAdHeight();
    }
    while (true)
    {
      super.onMeasure(a(paramInt1, getLayoutParams().width, i1), a(paramInt2, getLayoutParams().height, n));
      return;
      n = 0;
      i1 = 0;
    }
  }

  public void b(String paramString)
  {
    Context localContext = getContext();
    localContext.startActivity(AdfonicActivity.getOpenUrlIntent(paramString, localContext));
  }

  public void b(boolean paramBoolean)
  {
    int[] arrayOfInt = new int[2];
    getLocationOnScreen(arrayOfInt);
    this.l = arrayOfInt[0];
    this.k = arrayOfInt[1];
    super.onWindowFocusChanged(paramBoolean);
  }

  public int c()
  {
    return this.l;
  }

  public void c(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!"undefined".equals(paramString)))
      loadUrl(paramString);
    l();
  }

  public void c(boolean paramBoolean)
  {
    Log.setAdLoggingEnabled(paramBoolean);
  }

  public int d()
  {
    return this.k;
  }

  protected void d(String paramString)
  {
    loadDataWithBaseURL("/", new HtmlFormatter().applyHtmlFormatting(paramString), "text/html", "utf-8", null);
    this.b.ready();
    x();
  }

  public int e()
  {
    return this.i;
  }

  public int f()
  {
    return this.h;
  }

  public ExpandProperties g()
  {
    if (this.e == null)
      this.e = new ExpandProperties();
    return this.e;
  }

  public boolean h()
  {
    return getVisibility() == 0;
  }

  public String i()
  {
    return "inline";
  }

  public void j()
  {
    setVisibility(8);
  }

  public void k()
  {
    setVisibility(0);
    u();
  }

  public void l()
  {
    this.m = true;
    ExpandProperties localExpandProperties = g();
    int n = localExpandProperties.getHeight();
    int i1 = localExpandProperties.getWidth();
    if (n > this.g)
      n = this.g;
    if (i1 > this.f)
      i1 = this.f;
    d(i1, n);
  }

  public void onGlobalLayout()
  {
    switch (getResources().getConfiguration().keyboardHidden)
    {
    case 2:
    default:
    case 1:
    }
    while (true)
    {
      if (this.j < 0)
        w();
      int n = a(getContext()).getOrientation();
      if ((this.j >= 0) && (n != this.j))
      {
        u();
        this.b.reset();
        w();
      }
      return;
      this.b.onKeyboardChange(true);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adfonic.android.MyAdfonicView
 * JD-Core Version:    0.6.2
 */