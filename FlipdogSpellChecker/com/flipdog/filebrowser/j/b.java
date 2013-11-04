package com.flipdog.filebrowser.j;

import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class b
  implements AdapterView.OnItemClickListener
{
  b(a parama, Dialog paramDialog)
  {
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.b.dismiss();
    this.a.c.a(paramInt, a.c(this.a));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.j.b
 * JD-Core Version:    0.6.2
 */