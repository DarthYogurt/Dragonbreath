package com.flipdog.commons.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.flipdog.m;
import com.flipdog.p;
import java.util.Iterator;
import java.util.List;

public class k extends BaseAdapter
{
  private LayoutInflater a;
  private List<c> b;
  private boolean c;

  public k(Context paramContext, List<c> paramList)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.b = paramList;
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      if (((c)localIterator.next()).c > 0)
        this.c = true;
    }
  }

  private View a(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    return this.a.inflate(p.toolbar_overflow_item, paramViewGroup, false);
  }

  private a a(View paramView)
  {
    a locala = new a(null);
    locala.a = ((ImageView)paramView.findViewById(m.image));
    locala.b = ((TextView)paramView.findViewById(m.title));
    return locala;
  }

  public int getCount()
  {
    return this.b.size();
  }

  public Object getItem(int paramInt)
  {
    return null;
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = a(paramView, paramViewGroup);
    a locala = a(localView);
    c localc = (c)this.b.get(paramInt);
    if (this.c)
      if (localc.c > 0)
      {
        locala.a.setVisibility(0);
        locala.a.setImageResource(localc.c);
      }
    while (true)
    {
      locala.b.setText(localc.d);
      return localView;
      locala.a.setVisibility(4);
      continue;
      locala.a.setVisibility(8);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.k
 * JD-Core Version:    0.6.2
 */