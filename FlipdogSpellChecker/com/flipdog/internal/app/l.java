package com.flipdog.internal.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.flipdog.m;
import java.util.List;

final class l extends BaseAdapter
{
  private final LayoutInflater b;
  private List<g> c;

  public l(Context paramContext, List<g> paramList)
  {
    Object localObject;
    this.c = localObject;
    this.b = ((LayoutInflater)paramList.getSystemService("layout_inflater"));
  }

  private final void a(View paramView, g paramg)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(m.text1);
    TextView localTextView2 = (TextView)paramView.findViewById(m.text2);
    ImageView localImageView = (ImageView)paramView.findViewById(m.icon);
    localTextView1.setText(paramg.b);
    if (paramg.d != null)
    {
      localTextView2.setVisibility(0);
      localTextView2.setText(paramg.d);
    }
    while (true)
    {
      if (paramg.c == null)
        paramg.c = paramg.a.loadIcon(ResolverActivity.a(this.a));
      localImageView.setImageDrawable(paramg.c);
      return;
      localTextView2.setVisibility(8);
    }
  }

  public Intent a(int paramInt)
  {
    g localg = (g)this.c.get(paramInt);
    Intent localIntent = new Intent(localg.e);
    localIntent.addFlags(50331648);
    ActivityInfo localActivityInfo = localg.a.activityInfo;
    localIntent.setComponent(new ComponentName(localActivityInfo.applicationInfo.packageName, localActivityInfo.name));
    localIntent.putExtras(localg.e);
    return localIntent;
  }

  public int getCount()
  {
    if (this.c != null)
      return this.c.size();
    return 0;
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
    if (paramView == null)
      paramView = this.b.inflate(c.c.a, paramViewGroup, false);
    a(paramView, (g)this.c.get(paramInt));
    return paramView;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.l
 * JD-Core Version:    0.6.2
 */