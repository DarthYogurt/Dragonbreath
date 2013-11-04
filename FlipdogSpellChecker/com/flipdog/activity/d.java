package com.flipdog.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class d
  implements AdapterView.OnItemClickListener
{
  d(MyListActivity paramMyListActivity)
  {
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.a.a(MyListActivity.a(this.a), paramView, paramInt, paramLong);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.activity.d
 * JD-Core Version:    0.6.2
 */