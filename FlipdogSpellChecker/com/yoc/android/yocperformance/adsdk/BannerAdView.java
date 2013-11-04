package com.yoc.android.yocperformance.adsdk;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

public class BannerAdView extends AdView
{
  private static final float a = 6.0F;
  private static final float b = 0.2F;

  public BannerAdView(Context paramContext)
  {
    super(paramContext);
  }

  public BannerAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected int a(int paramInt1, int paramInt2)
  {
    return paramInt1;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
      b();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    double d1 = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getMode(paramInt1);
    double d2 = View.MeasureSpec.getSize(paramInt2);
    int j = View.MeasureSpec.getMode(paramInt2);
    double d3;
    int k;
    if ((i != 0) && (j != 1073741824))
    {
      d3 = d1 / 6.0D;
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      k = Math.round(0.2F * localDisplayMetrics.heightPixels);
      if (d3 <= k)
        break label163;
    }
    label163: for (double d4 = k; ; d4 = d3)
    {
      if ((j == 0) || ((j == -2147483648) && (d4 < d2)));
      while (true)
      {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)Math.round(d1), 1073741824), View.MeasureSpec.makeMeasureSpec((int)Math.round(d4), 1073741824));
        return;
        super.onMeasure(paramInt1, paramInt2);
        return;
        d4 = d2;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.BannerAdView
 * JD-Core Version:    0.6.2
 */