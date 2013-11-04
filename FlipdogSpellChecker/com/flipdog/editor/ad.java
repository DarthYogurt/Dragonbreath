package com.flipdog.editor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.flipdog.m;
import com.flipdog.p;
import java.util.ArrayList;
import java.util.List;

public class ad extends BaseAdapter
{
  private LayoutInflater a;
  private List<aa> b;
  private av c;

  public ad(Context paramContext, av paramav)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.c = paramav;
    this.b = new ArrayList();
    this.b.add(a(11));
    this.b.add(a(12));
    this.b.add(a(14));
    this.b.add(a(16));
    this.b.add(a(18));
    this.b.add(a(20));
    this.b.add(a(22));
    this.b.add(a(24));
    this.b.add(a(26));
    this.b.add(a(28));
    this.b.add(a(36));
  }

  private View a(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    return a(paramViewGroup);
  }

  private View a(ViewGroup paramViewGroup)
  {
    return this.a.inflate(p.editor_font_size_item, paramViewGroup, false);
  }

  private aa a(int paramInt)
  {
    return new aa(paramInt, paramInt);
  }

  private ap a(View paramView)
  {
    ap localap = new ap(null);
    localap.a = ((TextView)paramView.findViewById(m.text));
    localap.b = paramView.findViewById(m.border);
    return localap;
  }

  public int getCount()
  {
    return this.b.size();
  }

  public Object getItem(int paramInt)
  {
    return this.b.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = a(paramView, paramViewGroup);
    ap localap = a(localView);
    aa localaa = (aa)this.b.get(paramInt);
    localap.a.setText(localaa.b);
    localap.a.setTextSize(1, localaa.a);
    localap.a.invalidate();
    if (this.c.b(paramInt))
    {
      localap.b.setBackgroundColor(Color.rgb(90, 132, 218));
      return localView;
    }
    localap.b.setBackgroundColor(0);
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ad
 * JD-Core Version:    0.6.2
 */