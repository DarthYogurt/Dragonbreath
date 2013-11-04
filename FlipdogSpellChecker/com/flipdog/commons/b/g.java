package com.flipdog.commons.b;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.MyPopupWindow;
import java.util.List;

class g
  implements AdapterView.OnItemClickListener
{
  g(MyPopupWindow paramMyPopupWindow, List paramList, d paramd)
  {
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.a.r();
    c localc = (c)this.b.get(paramInt);
    i.a(this.c, localc.a);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.g
 * JD-Core Version:    0.6.2
 */