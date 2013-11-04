package com.yoc.android.yocperformance.adsdk;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.flipdog.commons.diagnostic.Track;

public abstract class AdView extends RelativeLayout
  implements j, n
{
  private Integer a;
  private Integer b;
  private u c;
  private Ad d;
  private View e;

  public AdView(Context paramContext)
  {
    super(paramContext);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    String str1 = a();
    int i = paramAttributeSet.getAttributeIntValue(str1, "adSpaceId", -1);
    if (i != -1)
    {
      this.a = Integer.valueOf(i);
      this.b = Integer.valueOf(i);
    }
    int j = paramAttributeSet.getAttributeIntValue(str1, "adSpaceIdPortrait", -1);
    if (j != -1)
      this.a = Integer.valueOf(j);
    int k = paramAttributeSet.getAttributeIntValue(str1, "adSpaceIdLandscape", -1);
    if (k != -1)
      this.b = Integer.valueOf(k);
    String str2 = paramAttributeSet.getAttributeValue(str1, "keywords");
    if (str2 != null)
      this.c = new u(str2);
  }

  private static void a(String paramString, Throwable paramThrowable)
  {
    Track.it(paramString, new String[] { "Ads" });
    Track.it(paramThrowable, new String[] { "Ads" });
  }

  protected abstract int a(int paramInt1, int paramInt2);

  protected View.OnClickListener a(Ad paramAd)
  {
    return new g(this, paramAd);
  }

  protected View a(Ad paramAd, Drawable paramDrawable)
  {
    ImageView localImageView = new ImageView(getContext());
    localImageView.setScaleType(ImageView.ScaleType.CENTER);
    localImageView.setImageDrawable(paramDrawable);
    localImageView.setBackgroundColor(0);
    localImageView.setOnClickListener(a(paramAd));
    return localImageView;
  }

  final String a()
  {
    return "http://schemas.android.com/apk/res/" + getContext().getPackageName();
  }

  public void a(Drawable paramDrawable)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13);
    View localView = a(this.d, paramDrawable);
    this.e = localView;
    addView(localView, localLayoutParams);
    if ((paramDrawable instanceof Animatable))
      post(new c(this, paramDrawable));
  }

  public void a(Exception paramException)
  {
    a("Exception while loading ad", paramException);
    setVisibility(8);
  }

  public void a(String paramString)
  {
    try
    {
      this.d = Ad.a(paramString);
      t.a(getContext()).a(this.d.b(), this);
      return;
    }
    catch (Exception localException)
    {
      a(localException);
    }
  }

  protected final void b()
  {
    if ((getWidth() == 0) && (getHeight() == 0))
      throw new IllegalStateException("AdView does not have any dimensions. Layout phase not completed yet?");
    if (this.d == null)
    {
      i = a(getWidth(), getHeight());
      if (getResources().getConfiguration().orientation == 2)
        t.a(getContext()).a(this.b.intValue(), Integer.valueOf(i), this.c, this);
    }
    while (this.e != null)
    {
      int i;
      return;
      t.a(getContext()).a(this.a.intValue(), Integer.valueOf(i), this.c, this);
      return;
    }
    t.a(getContext()).a(this.d.b(), this);
  }

  public void b(Exception paramException)
  {
    a("Exception while loading ad image", paramException);
    setVisibility(8);
  }

  public final void c()
  {
    removeView(this.e);
    this.d = null;
    this.e = null;
  }

  public void d()
  {
  }

  public void e()
  {
  }

  public boolean f()
  {
    return this.d == null;
  }

  public Integer g()
  {
    return this.a;
  }

  public Integer h()
  {
    return this.b;
  }

  public final u i()
  {
    return this.c;
  }

  public void setAdSpaceIdLandscape(Integer paramInteger)
  {
    this.b = paramInteger;
  }

  public void setAdSpaceIdPortrait(Integer paramInteger)
  {
    this.a = paramInteger;
  }

  public final void setKeywords(u paramu)
  {
    this.c = paramu;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.AdView
 * JD-Core Version:    0.6.2
 */