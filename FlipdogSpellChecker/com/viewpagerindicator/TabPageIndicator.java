package com.viewpagerindicator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class TabPageIndicator extends HorizontalScrollView
  implements a
{
  private static final CharSequence a = "";
  private Runnable b;
  private int c;
  private final View.OnClickListener d = new c(this);
  private final LinearLayout e;
  private ViewPager f;
  private int g;
  private int h;
  private ViewPager.OnPageChangeListener i;
  private d j;

  public TabPageIndicator(Context paramContext)
  {
    this(paramContext, null);
  }

  public TabPageIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setHorizontalScrollBarEnabled(false);
    this.e = new LinearLayout(getContext());
    addView(this.e, new ViewGroup.LayoutParams(-1, -1));
  }

  private void a(int paramInt)
  {
    View localView = this.e.getChildAt(paramInt);
    if (this.b != null)
      removeCallbacks(this.b);
    this.b = new b(this, localView);
    post(this.b);
  }

  private void a(CharSequence paramCharSequence, int paramInt)
  {
    e locale = new e(this, getContext());
    e.a(locale, paramInt);
    locale.setFocusable(true);
    locale.setOnClickListener(this.d);
    locale.setText(paramCharSequence);
    this.e.addView(locale, new LinearLayout.LayoutParams(-2, -1, 1.0F));
    if (this.c == 0)
      this.c = locale.getTextColors().getDefaultColor();
  }

  private e b(int paramInt)
  {
    return (e)this.e.getChildAt(paramInt);
  }

  public void a()
  {
    this.e.removeAllViews();
    PagerAdapter localPagerAdapter = this.f.getAdapter();
    int k = localPagerAdapter.getCount();
    for (int m = 0; ; m++)
    {
      if (m >= k)
      {
        if (this.h > k)
          this.h = (k - 1);
        setCurrentItem(this.h);
        requestLayout();
        return;
      }
      CharSequence localCharSequence = localPagerAdapter.getPageTitle(m);
      if (localCharSequence == null)
        localCharSequence = a;
      a(localCharSequence, m);
    }
  }

  public int b()
  {
    return this.h;
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.b != null)
      post(this.b);
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.b != null)
      removeCallbacks(this.b);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    int k = View.MeasureSpec.getMode(paramInt1);
    boolean bool;
    if (k == 1073741824)
    {
      bool = true;
      setFillViewport(bool);
      int m = this.e.getChildCount();
      if ((m <= 1) || ((k != 1073741824) && (k != -2147483648)))
        break label116;
      if (m <= 2)
        break label103;
      this.g = 0;
    }
    while (true)
    {
      int n = getMeasuredWidth();
      super.onMeasure(paramInt1, paramInt2);
      int i1 = getMeasuredWidth();
      if ((bool) && (n != i1))
        setCurrentItem(this.h);
      return;
      bool = false;
      break;
      label103: this.g = (View.MeasureSpec.getSize(paramInt1) / 2);
      continue;
      label116: this.g = -1;
    }
  }

  public void onPageScrollStateChanged(int paramInt)
  {
    if (this.i != null)
      this.i.onPageScrollStateChanged(paramInt);
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.i != null)
      this.i.onPageScrolled(paramInt1, paramFloat, paramInt2);
  }

  public void onPageSelected(int paramInt)
  {
    setCurrentItem(paramInt);
    if (this.i != null)
      this.i.onPageSelected(paramInt);
  }

  public void setCurrentItem(int paramInt)
  {
    if (this.f == null)
      throw new IllegalStateException("ViewPager has not been bound.");
    this.h = paramInt;
    this.f.setCurrentItem(paramInt);
    int k = this.e.getChildCount();
    int m = 0;
    if (m >= k)
      return;
    View localView = this.e.getChildAt(m);
    if (m == paramInt);
    for (boolean bool = true; ; bool = false)
    {
      localView.setSelected(bool);
      if (bool)
        a(paramInt);
      m++;
      break;
    }
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.i = paramOnPageChangeListener;
  }

  public void setOnTabReselectedListener(d paramd)
  {
    this.j = paramd;
  }

  public void setTabBackgroundColor(int paramInt1, int paramInt2)
  {
    e locale = b(paramInt1);
    if (paramInt2 == 0)
      paramInt2 = 0;
    locale.setBackgroundColor(paramInt2);
  }

  public void setTabColor(int paramInt1, int paramInt2)
  {
    e locale = b(paramInt1);
    if (paramInt2 == 0)
      paramInt2 = this.c;
    locale.setTextColor(paramInt2);
  }

  public void setTabName(int paramInt, CharSequence paramCharSequence)
  {
    b(paramInt).setText(paramCharSequence);
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    if (this.f == paramViewPager)
      return;
    if (this.f != null)
      this.f.setOnPageChangeListener(null);
    if (paramViewPager.getAdapter() == null)
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    this.f = paramViewPager;
    paramViewPager.setOnPageChangeListener(this);
    a();
  }

  public void setViewPager(ViewPager paramViewPager, int paramInt)
  {
    setViewPager(paramViewPager);
    setCurrentItem(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.viewpagerindicator.TabPageIndicator
 * JD-Core Version:    0.6.2
 */