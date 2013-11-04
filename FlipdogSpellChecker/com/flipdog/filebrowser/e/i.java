package com.flipdog.filebrowser.e;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class i
  implements AdapterView.OnItemClickListener
{
  i(b paramb)
  {
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    b localb = (b)paramAdapterView.getTag();
    localb.a(localb.getItem(paramInt));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.i
 * JD-Core Version:    0.6.2
 */