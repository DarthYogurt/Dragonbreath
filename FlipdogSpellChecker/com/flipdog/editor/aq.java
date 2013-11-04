package com.flipdog.editor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.flipdog.m;
import com.flipdog.p;

public class aq extends BaseAdapter
{
  private LayoutInflater a;
  private int[] b;

  public aq(Context paramContext, aj paramaj)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.b = paramaj.a;
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

  private k a(View paramView)
  {
    k localk = new k(null);
    localk.a = ((TextView)paramView.findViewById(m.color));
    return localk;
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
    k localk = a(localView);
    localk.a.setBackgroundColor(this.b[paramInt]);
    localk.a.setText("a");
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.aq
 * JD-Core Version:    0.6.2
 */