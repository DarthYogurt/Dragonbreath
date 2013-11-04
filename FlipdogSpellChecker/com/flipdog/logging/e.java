package com.flipdog.logging;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.flipdog.m;
import com.flipdog.p;
import java.util.List;

public class e extends BaseAdapter
{
  private LayoutInflater a;
  private List<String> b;

  public e(Activity paramActivity, List<String> paramList)
  {
    this.b = paramList;
    this.a = ((LayoutInflater)paramActivity.getSystemService("layout_inflater"));
  }

  private View a(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    return this.a.inflate(p.logcat_line, paramViewGroup, false);
  }

  private l a(View paramView)
  {
    l locall = new l(null);
    locall.a = ((TextView)paramView.findViewById(m.text));
    return locall;
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
    View localView = a(paramView, paramViewGroup);
    a(localView).a.setText((CharSequence)this.b.get(paramInt));
    return localView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.e
 * JD-Core Version:    0.6.2
 */