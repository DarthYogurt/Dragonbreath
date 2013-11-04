package com.flipdog.commons.actionbar;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.Window.Callback;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.b.b.h;
import com.flipdog.a;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.i;
import com.flipdog.commons.a.u;
import com.flipdog.commons.diagnostic.Track;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class g extends r
{
  private static final int b = 3;
  private static final int c;
  private k d;
  private Set<Integer> e;
  private b f;
  private d g;
  private k h;
  private Set<Integer> i;
  private boolean j;
  private boolean k;
  private View l;
  private ViewGroup m;
  private ViewGroup n;
  private View o;
  private int p = 0;
  private com.flipdog.commons.s.b q;
  private h r = new h();

  protected g(Activity paramActivity)
  {
    super(paramActivity);
  }

  private int a(Menu paramMenu, int paramInt)
  {
    int i1 = 0;
    int i2 = 0;
    while (true)
    {
      if (i1 >= paramMenu.size())
        return i2;
      if (a(((f)paramMenu.getItem(i1)).a(), paramInt))
        i2++;
      i1++;
    }
  }

  private View a(MenuItem paramMenuItem)
  {
    int i1 = paramMenuItem.getItemId();
    ViewGroup localViewGroup = j();
    if (localViewGroup == null)
      return null;
    int i2 = (int)u.c(k(), 0, com.flipdog.d.actionBarButtonWidth);
    int i3 = (int)u.c(k(), 0, com.flipdog.d.actionBarHeight);
    int i4 = com.flipdog.d.actionBarItemStyle;
    Button localButton = new Button(this.a, null, i4);
    if (i1 == com.flipdog.m.menu_refresh)
    {
      localButton.setId(com.flipdog.m.actionbar_item_refresh);
      Object localObject = (BitmapDrawable)paramMenuItem.getIcon();
      if (localObject != null)
      {
        ((BitmapDrawable)localObject).setBounds(0, 0, i2, i3);
        ((BitmapDrawable)localObject).setGravity(17);
      }
      if ((i()) && (c(paramMenuItem)))
      {
        Drawable[] arrayOfDrawable = new Drawable[2];
        arrayOfDrawable[0] = new ColorDrawable(-1724664347);
        arrayOfDrawable[1] = localObject;
        LayerDrawable localLayerDrawable = new LayerDrawable(arrayOfDrawable);
        localLayerDrawable.setBounds(0, 0, i2, i3);
        localObject = localLayerDrawable;
      }
      if (localObject == null)
        break label355;
      localButton.setCompoundDrawables((Drawable)localObject, null, null, null);
    }
    while (true)
    {
      localButton.setSingleLine(true);
      localButton.setGravity(16);
      i.a(localButton, paramMenuItem.getTitle());
      localButton.setOnClickListener(new n(this, paramMenuItem));
      localViewGroup.addView(localButton, -2, -1);
      if (paramMenuItem.getItemId() == com.flipdog.m.menu_refresh)
      {
        ProgressBar localProgressBar = new ProgressBar(this.a);
        int i5 = i2 / 2;
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(i5, i5);
        localLayoutParams.setMargins((i2 - i5) / 2, (i3 - i5) / 2, (i2 - i5) / 2, 0);
        localProgressBar.setLayoutParams(localLayoutParams);
        localProgressBar.setVisibility(8);
        localProgressBar.setId(com.flipdog.m.actionbar_item_refresh_progress);
        localViewGroup.addView(localProgressBar);
      }
      return localButton;
      localButton.setId(i1);
      break;
      label355: localButton.setText(b(paramMenuItem));
      localButton.setPadding(8, 0, 8, 0);
    }
  }

  private void a(Menu paramMenu, int paramInt1, Collection<Integer> paramCollection, int paramInt2)
  {
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= paramMenu.size());
      while (paramCollection.size() >= paramInt1)
        return;
      f localf = (f)paramMenu.getItem(i1);
      if (a(localf.a(), paramInt2))
        paramCollection.add(Integer.valueOf(localf.getItemId()));
    }
  }

  private void a(View paramView, MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == com.flipdog.m.menu_overflow)
      b(paramView);
    if (paramMenuItem.hasSubMenu())
      b(paramView, paramMenuItem);
    if (i())
    {
      if (c(paramMenuItem))
      {
        a();
        return;
      }
      this.g.a(this.f, paramMenuItem);
      return;
    }
    this.a.onMenuItemSelected(0, paramMenuItem);
  }

  private void a(ViewGroup paramViewGroup, View paramView)
  {
    b(paramViewGroup, paramView);
  }

  private void a(ViewGroup paramViewGroup, CharSequence paramCharSequence)
  {
    TextView localTextView = new TextView(this.a, null, com.flipdog.d.actionBarTitleStyle);
    localTextView.setId(com.flipdog.m.actionbar_title);
    localTextView.setText(paramCharSequence);
    b(paramViewGroup, localTextView);
  }

  private void a(k paramk, Set<Integer> paramSet)
  {
    if (paramSet.size() < paramk.size());
    for (int i1 = 1; ; i1 = 0)
      for (int i2 = 0; ; i2++)
      {
        if (i2 >= paramk.size())
        {
          if (i1 != 0)
          {
            f localf = new f(paramk, com.flipdog.m.menu_overflow, 0, null);
            localf.setIcon(a.ic_menu_moreoverflow);
            a(localf);
          }
          return;
        }
        MenuItem localMenuItem = paramk.getItem(i2);
        if (paramSet.contains(Integer.valueOf(localMenuItem.getItemId())))
          a(localMenuItem);
      }
  }

  private String b(MenuItem paramMenuItem)
  {
    CharSequence localCharSequence = paramMenuItem.getTitle();
    if (localCharSequence == null)
      return null;
    return localCharSequence.toString().toUpperCase();
  }

  private Set<Integer> b(Menu paramMenu)
  {
    int i1 = a(paramMenu, 0);
    int i2 = a(paramMenu, 2);
    int i3 = paramMenu.size() - i1 - i2;
    int i4 = Math.max(0, 3 - i2);
    int i5;
    if (i1 == 0)
    {
      i5 = 0;
      if (i3 > i4)
        i5 = 1;
      if (i5 == 0)
        break label107;
    }
    label107: for (int i6 = Math.max(i2, -1 + (i2 + i4)); ; i6 = i2 + i4)
    {
      HashSet localHashSet = new HashSet();
      a(paramMenu, i6, localHashSet, 2);
      a(paramMenu, i6, localHashSet, 1);
      return localHashSet;
      i5 = 1;
      break;
    }
  }

  private void b(View paramView)
  {
    this.a.onPrepareOptionsMenu(this.d);
    List localList = e.a(m(), n());
    e.a(this.a, paramView, localList);
  }

  private void b(View paramView, MenuItem paramMenuItem)
  {
    List localList = e.a(paramMenuItem.getSubMenu());
    e.a(this.a, paramView, localList);
  }

  private void b(ViewGroup paramViewGroup, View paramView)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, -1);
    localLayoutParams.weight = 1.0F;
    paramView.setLayoutParams(localLayoutParams);
    paramViewGroup.addView(paramView);
  }

  private void b(CharSequence paramCharSequence)
  {
    TextView localTextView = (TextView)this.a.findViewById(com.flipdog.m.actionbar_title);
    if (localTextView != null)
      localTextView.setText(paramCharSequence);
  }

  private boolean c(MenuItem paramMenuItem)
  {
    return paramMenuItem.getItemId() == 16908332;
  }

  private void d()
  {
    q().a(this.r, new m(this));
  }

  private void d(int paramInt)
  {
    f localf = new f(new k(this.a), 16908332, 0, null);
    localf.setIcon(paramInt);
    a(localf);
  }

  private void e()
  {
    this.d = new k(this.a);
    this.a.onCreateOptionsMenu(this.d);
    this.e = b(this.d);
  }

  private void f()
  {
    if (i())
    {
      h();
      return;
    }
    g();
  }

  private void g()
  {
    ViewGroup localViewGroup = j();
    if (localViewGroup == null)
      return;
    int i1 = u.b(k(), 0, com.flipdog.d.actionBarLogo);
    localViewGroup.removeAllViews();
    d(i1);
    if (this.l != null)
      a(localViewGroup, this.l);
    while (true)
    {
      a(this.d, this.e);
      return;
      a(localViewGroup, this.a.getTitle());
    }
  }

  private void h()
  {
    ViewGroup localViewGroup = j();
    if (localViewGroup == null)
      return;
    localViewGroup.removeAllViews();
    d(a.ic_clear_normal);
    a(localViewGroup, null);
    a(this.h, this.i);
  }

  private boolean i()
  {
    return this.f != null;
  }

  private ViewGroup j()
  {
    return (ViewGroup)this.a.findViewById(com.flipdog.m.actionbar);
  }

  private Resources.Theme k()
  {
    return this.a.getTheme();
  }

  private boolean l()
  {
    return !this.k;
  }

  private k m()
  {
    if (this.f != null)
      return this.h;
    return this.d;
  }

  private Set<Integer> n()
  {
    if (this.f != null)
      return this.i;
    return this.e;
  }

  private void o()
  {
    if (this.m == null)
      this.m = ((ViewGroup)this.a.getWindow().getDecorView().findViewById(16908290));
    ArrayList localArrayList;
    int i3;
    Iterator localIterator;
    if (this.n == null)
    {
      int i1 = this.m.getChildCount();
      localArrayList = null;
      if (i1 > 0)
      {
        localArrayList = new ArrayList(1);
        int i2 = this.m.getChildCount();
        i3 = 0;
        if (i3 < i2)
          break label108;
      }
      this.n = p();
      if (localArrayList != null)
        localIterator = localArrayList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        label108: View localView1 = this.m.getChildAt(0);
        this.m.removeView(localView1);
        localArrayList.add(localView1);
        i3++;
        break;
      }
      View localView2 = (View)localIterator.next();
      this.n.addView(localView2);
    }
  }

  private ViewGroup p()
  {
    int i1 = com.flipdog.p.ab_screen;
    View localView = this.a.getLayoutInflater().inflate(i1, null);
    this.m.addView(localView, new ViewGroup.LayoutParams(-1, -1));
    ViewGroup localViewGroup = (ViewGroup)this.m.findViewById(com.flipdog.m.ab_content);
    if (localViewGroup == null)
      throw new RuntimeException("Couldn't find content container view");
    this.m.setId(-1);
    localViewGroup.setId(16908290);
    this.o = this.m.findViewById(com.flipdog.m.ab_top);
    if (b(1))
      this.o.setVisibility(8);
    return localViewGroup;
  }

  private com.b.b.b q()
  {
    return ((MyActivity)this.a).a();
  }

  public MenuInflater a(MenuInflater paramMenuInflater)
  {
    if (this.k)
      return paramMenuInflater;
    return new p(this.a);
  }

  protected void a()
  {
    if (this.f == null)
      return;
    if (b(1))
      this.o.setVisibility(8);
    b localb = this.f;
    d locald = this.g;
    this.f = null;
    this.h = null;
    this.g = null;
    f();
    if (l())
      b(this.a.getTitle());
    locald.a(localb);
  }

  public void a(int paramInt)
  {
    if (this.n == null)
      o();
    while (true)
    {
      this.a.getLayoutInflater().inflate(paramInt, this.n);
      Window.Callback localCallback = this.a.getWindow().getCallback();
      if (localCallback != null)
        localCallback.onContentChanged();
      return;
      this.n.removeAllViews();
    }
  }

  public void a(Bundle paramBundle)
  {
    this.q = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
    try
    {
      this.a.requestWindowFeature(1);
      return;
    }
    catch (AndroidRuntimeException localAndroidRuntimeException)
    {
      Track.it(localAndroidRuntimeException);
    }
  }

  public void a(View paramView)
  {
    this.l = paramView;
  }

  public void a(d paramd)
  {
    if (b(1))
      this.o.setVisibility(0);
    this.f = new b(this);
    this.g = paramd;
    this.h = new k(this.a);
    paramd.a(this.f, this.h);
    this.i = b(this.h);
    f();
  }

  public void a(CharSequence paramCharSequence)
  {
    b(paramCharSequence);
  }

  protected void a(CharSequence paramCharSequence, int paramInt)
  {
    if (this.k)
      return;
    b(paramCharSequence);
  }

  public void a(boolean paramBoolean)
  {
  }

  public boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) != 0;
  }

  public void b()
  {
    if (this.k);
    while (i())
      return;
    e();
    f();
  }

  public void b(Bundle paramBundle)
  {
    e();
    f();
    d();
  }

  public void b(boolean paramBoolean)
  {
    if (this.k);
    View localView2;
    int i2;
    do
    {
      return;
      View localView1 = this.a.findViewById(com.flipdog.m.actionbar_item_refresh);
      localView2 = this.a.findViewById(com.flipdog.m.actionbar_item_refresh_progress);
      if (localView1 != null)
      {
        if (!paramBoolean)
          break;
        i2 = 8;
        localView1.setVisibility(i2);
      }
    }
    while (localView2 == null);
    int i1 = 0;
    if (paramBoolean);
    while (true)
    {
      localView2.setVisibility(i1);
      return;
      i2 = 0;
      break;
      i1 = 8;
    }
  }

  public boolean b(int paramInt)
  {
    return (this.p & 1 << paramInt) != 0;
  }

  public void c()
  {
    View localView = this.a.findViewById(com.flipdog.m.menu_overflow);
    if (localView == null)
      return;
    b(localView);
  }

  public void c(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public boolean c(int paramInt)
  {
    if (this.n != null)
      throw new AndroidRuntimeException("requestFeature() must be called before adding content");
    switch (paramInt)
    {
    case 3:
    case 4:
    case 6:
    case 7:
    default:
      return false;
    case 1:
    case 2:
    case 5:
    case 8:
    case 9:
    case 10:
    }
    this.p |= 1 << paramInt;
    return true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.g
 * JD-Core Version:    0.6.2
 */