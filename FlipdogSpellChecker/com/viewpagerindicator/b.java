package com.viewpagerindicator;

import android.view.View;

class b
  implements Runnable
{
  b(TabPageIndicator paramTabPageIndicator, View paramView)
  {
  }

  public void run()
  {
    int i = this.b.getLeft() - (this.a.getWidth() - this.b.getWidth()) / 2;
    this.a.smoothScrollTo(i, 0);
    TabPageIndicator.a(this.a, null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.viewpagerindicator.b
 * JD-Core Version:    0.6.2
 */