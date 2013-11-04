package com.flipdog.editor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.flipdog.m;
import com.flipdog.p;

public class d extends BaseAdapter
{
  private LayoutInflater a;
  private int[] b;
  private av c;

  public d(Context paramContext, aj paramaj, av paramav)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.b = paramaj.a;
    this.c = paramav;
  }

  private View a(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    return a(paramViewGroup);
  }

  private View a(ViewGroup paramViewGroup)
  {
    return this.a.inflate(p.editor_colors_grid_item, paramViewGroup, false);
  }

  private af a(View paramView)
  {
    af localaf = new af(null);
    localaf.a = paramView.findViewById(m.color);
    localaf.b = paramView.findViewById(m.border);
    return localaf;
  }

  public int getCount()
  {
    return this.b.length;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(this.b[paramInt]);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = a(paramView, paramViewGroup);
    af localaf = a(localView);
    localaf.a.setBackgroundColor(this.b[paramInt]);
    if (this.c.b(paramInt))
    {
      localaf.b.setBackgroundColor(Color.rgb(90, 132, 218));
      return localView;
    }
    localaf.b.setBackgroundColor(0);
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.d
 * JD-Core Version:    0.6.2
 */