package com.flipdog.commons.n;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class b extends BaseAdapter
{
  public c a = new c();
  private DataSetObserver b = new a(this);

  public void a(View paramView)
  {
    this.a.a(paramView);
  }

  public void a(BaseAdapter paramBaseAdapter)
  {
    this.a.a(paramBaseAdapter);
    paramBaseAdapter.registerDataSetObserver(this.b);
  }

  public int getCount()
  {
    return this.a.a();
  }

  public Object getItem(int paramInt)
  {
    return this.a.a(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return this.a.b(paramInt);
  }

  public int getItemViewType(int paramInt)
  {
    return this.a.c(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return this.a.a(paramInt, paramView, paramViewGroup);
  }

  public int getViewTypeCount()
  {
    return this.a.b();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.n.b
 * JD-Core Version:    0.6.2
 */