package com.flipdog.activity;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyListActivity extends MyActivity
{
  private ListView c;
  private ListAdapter d;

  public void a(ListAdapter paramListAdapter)
  {
    try
    {
      this.d = paramListAdapter;
      this.c.setAdapter(paramListAdapter);
      return;
    }
    finally
    {
    }
  }

  protected void a(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
  }

  public ListView d()
  {
    return this.c;
  }

  public ListAdapter e()
  {
    return this.d;
  }

  public void onContentChanged()
  {
    super.onContentChanged();
    View localView = findViewById(16908292);
    this.c = ((ListView)findViewById(16908298));
    if (this.c == null)
      throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
    if (localView != null)
      this.c.setEmptyView(localView);
    this.c.setOnItemClickListener(new d(this));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.activity.MyListActivity
 * JD-Core Version:    0.6.2
 */