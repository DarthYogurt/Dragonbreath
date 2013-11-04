package com.flipdog.commons.n;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

class d
{
  public View a;
  public Adapter b;
  public int c;

  public d(View paramView)
  {
    this.a = paramView;
  }

  public d(BaseAdapter paramBaseAdapter)
  {
    this.b = paramBaseAdapter;
  }

  public int a()
  {
    if (this.a != null)
      return 1;
    return this.b.getViewTypeCount();
  }

  public int a(int paramInt)
  {
    if (this.a != null)
      return 0;
    return this.b.getItemViewType(paramInt);
  }

  public View a(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (this.a != null)
      return this.a;
    return this.b.getView(paramInt - this.c, paramView, paramViewGroup);
  }

  public int b()
  {
    if (this.a != null)
      return 1;
    return this.b.getCount();
  }

  public Object b(int paramInt)
  {
    if (this.a != null)
      return null;
    return this.b.getItem(paramInt - this.c);
  }

  public long c(int paramInt)
  {
    if (this.a != null)
      return -1L;
    return this.b.getItemId(paramInt - this.c);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.n.d
 * JD-Core Version:    0.6.2
 */