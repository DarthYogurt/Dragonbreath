package com.flipdog.editor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.flipdog.m;
import com.flipdog.p;
import java.util.ArrayList;
import java.util.List;

public class x extends BaseAdapter
{
  private LayoutInflater a;
  private List<ae> b = new ArrayList();
  private av c;

  public x(Context paramContext)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  private View a(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    return a(paramViewGroup);
  }

  private View a(ViewGroup paramViewGroup)
  {
    return this.a.inflate(p.editor_smiles_grid_item, paramViewGroup, false);
  }

  private a a(View paramView)
  {
    a locala = new a(null);
    locala.a = ((ImageView)paramView.findViewById(m.image));
    locala.b = paramView.findViewById(m.border);
    return locala;
  }

  public void a(ac paramac)
  {
    this.b = paramac.a;
    this.c = paramac.b;
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
    a locala = a(localView);
    ae localae = (ae)this.b.get(paramInt);
    if (localae.b != 0);
    for (int i = localae.b; ; i = localae.c)
    {
      locala.a.setImageResource(i);
      if (!this.c.b(paramInt))
        break;
      locala.b.setBackgroundColor(Color.rgb(90, 132, 218));
      return localView;
    }
    locala.b.setBackgroundColor(0);
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.x
 * JD-Core Version:    0.6.2
 */