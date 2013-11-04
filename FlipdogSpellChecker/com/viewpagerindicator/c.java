package com.viewpagerindicator;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

class c
  implements View.OnClickListener
{
  c(TabPageIndicator paramTabPageIndicator)
  {
  }

  public void onClick(View paramView)
  {
    e locale = (e)paramView;
    int i = TabPageIndicator.a(this.a).getCurrentItem();
    int j = locale.a();
    TabPageIndicator.a(this.a).setCurrentItem(j);
    if ((i == j) && (TabPageIndicator.b(this.a) != null))
      TabPageIndicator.b(this.a).a(j);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.viewpagerindicator.c
 * JD-Core Version:    0.6.2
 */