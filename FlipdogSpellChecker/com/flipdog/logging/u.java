package com.flipdog.logging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import java.util.List;

public class u extends BaseAdapter
{
  private LayoutInflater a;
  private List<m> b;

  public u(Context paramContext, List<m> paramList)
  {
    this.a = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.b = paramList;
  }

  public int getCount()
  {
    return this.b.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = this.a.inflate(com.flipdog.p.logcat_tags_dialog_item, paramViewGroup, false);
    m localm = (m)this.b.get(paramInt);
    CheckBox localCheckBox = (CheckBox)localView.findViewById(com.flipdog.m.checkbox);
    localCheckBox.setOnClickListener(new p(this, localm, localCheckBox));
    localCheckBox.setText(localm.a);
    localCheckBox.setChecked(localm.b);
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.u
 * JD-Core Version:    0.6.2
 */