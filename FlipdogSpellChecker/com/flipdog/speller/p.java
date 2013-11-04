package com.flipdog.speller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.flipdog.m;

public class p extends BaseAdapter
{
  private LayoutInflater a;
  private String[] b;

  public p(Activity paramActivity, String[] paramArrayOfString)
  {
    this.a = ((LayoutInflater)paramActivity.getSystemService("layout_inflater"));
    this.b = paramArrayOfString;
  }

  public int getCount()
  {
    return this.b.length;
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
    View localView = this.a.inflate(com.flipdog.p.speller_suggestions_list_item, paramViewGroup, false);
    TextView localTextView = (TextView)localView.findViewById(m.text);
    localTextView.setText(this.b[paramInt]);
    if (paramInt == 0)
      localTextView.setTypeface(null, 1);
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.p
 * JD-Core Version:    0.6.2
 */