package com.flipdog.logging;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class j
{
  private LinearLayout a;
  private BaseAdapter b;
  private int c;

  public j(LinearLayout paramLinearLayout)
  {
    this.a = paramLinearLayout;
    paramLinearLayout.setOrientation(1);
  }

  private void a(int paramInt, View paramView)
  {
    boolean bool = this.a.getChildAt(paramInt).hasFocus();
    this.a.removeViewAt(paramInt);
    this.a.addView(paramView, paramInt);
    if (bool)
      paramView.requestFocus();
  }

  public View a(int paramInt)
  {
    return this.b.getView(paramInt, null, this.a);
  }

  public void a()
  {
    int i = this.b.getCount();
    int j = 0;
    int m;
    if (j >= Math.min(this.c, i))
    {
      if (this.c <= i)
        break label76;
      m = i;
      label33: if (m < this.c)
        break label64;
    }
    while (true)
    {
      this.c = i;
      return;
      a(j, a(j));
      j++;
      break;
      label64: b(m);
      m++;
      break label33;
      label76: for (int k = this.c; k < i; k++)
        a(a(k));
    }
  }

  public void a(View paramView)
  {
    this.a.addView(paramView);
  }

  public void a(BaseAdapter paramBaseAdapter)
  {
    this.b = paramBaseAdapter;
    this.b.registerDataSetObserver(new d(this));
    a();
  }

  public void b()
  {
    this.a.removeAllViews();
  }

  public void b(int paramInt)
  {
    this.a.removeViewAt(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.j
 * JD-Core Version:    0.6.2
 */