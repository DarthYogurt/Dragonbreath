package com.flipdog.filebrowser.j;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.flipdog.commons.a.as;
import com.flipdog.filebrowser.j.b.b;
import java.util.List;

class c extends BaseAdapter
{
  c(a parama)
  {
  }

  public int getCount()
  {
    return this.a.b.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = as.a(paramView, paramViewGroup, a.a(this.a), a.b(this.a));
    b localb;
    if (paramView == null)
    {
      localb = this.a.a(localView);
      localView.setTag(localb);
    }
    while (true)
    {
      this.a.a(localb, this.a.b.get(paramInt));
      return localView;
      localb = (b)localView.getTag();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.j.c
 * JD-Core Version:    0.6.2
 */