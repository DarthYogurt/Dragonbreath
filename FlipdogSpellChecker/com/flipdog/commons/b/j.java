package com.flipdog.commons.b;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.flipdog.a;
import com.flipdog.commons.a.s;
import com.flipdog.commons.a.u;
import java.util.ArrayList;
import java.util.List;

public class j
{
  private Activity a;
  private LayoutInflater b;
  private LinearLayout c;
  private List<c> d = new ArrayList();
  private d e;
  private int f;

  public j(Activity paramActivity, int paramInt)
  {
    this(paramActivity, paramInt, a.toolbar_overflow);
  }

  public j(Activity paramActivity, int paramInt1, int paramInt2)
  {
    this.a = paramActivity;
    this.b = ((LayoutInflater)paramActivity.getSystemService("layout_inflater"));
    this.c = ((LinearLayout)paramActivity.findViewById(paramInt1));
    this.f = paramInt2;
    Drawable localDrawable = u.a(c(), com.flipdog.d.toolbarStyle, 16842964);
    this.c.setBackgroundDrawable(localDrawable);
  }

  private Button a(Context paramContext, Drawable paramDrawable, CharSequence paramCharSequence)
  {
    Button localButton = new Button(paramContext);
    localButton.setBackgroundDrawable(u.a(c(), com.flipdog.d.toolbarStyle, com.flipdog.d.toolbarItemBackground));
    int i = (int)u.c(c(), com.flipdog.d.toolbarStyle, com.flipdog.d.toolbarHeight);
    int j = (int)u.c(c(), com.flipdog.d.toolbarStyle, com.flipdog.d.toolbarItemIconWidth);
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)paramDrawable;
    localBitmapDrawable.setBounds(0, 0, j, i);
    localBitmapDrawable.setGravity(17);
    Drawable localDrawable = s.c(localBitmapDrawable);
    localDrawable.setBounds(0, 0, j, i);
    localButton.setCompoundDrawables(localDrawable, null, null, null);
    localButton.setSingleLine(true);
    localButton.setGravity(16);
    com.flipdog.commons.a.i.a(localButton, paramCharSequence);
    localButton.setPadding(0, 0, 0, 0);
    localButton.setMinWidth(0);
    localButton.setMinimumWidth(0);
    return localButton;
  }

  private void b()
  {
    c localc = new c();
    localc.a = -1;
    localc.c = this.f;
    View localView = b(localc);
    localView.setOnClickListener(new e(this, localView));
  }

  private Resources.Theme c()
  {
    return this.a.getTheme();
  }

  private View c(c paramc)
  {
    Drawable localDrawable = this.a.getResources().getDrawable(paramc.c);
    Button localButton = a(this.a, localDrawable, paramc.d);
    localButton.setId(paramc.a);
    localButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    this.c.addView(localButton);
    if (paramc.a != -1)
      localButton.setOnClickListener(new h(this, paramc, localButton));
    return localButton;
  }

  public void a()
  {
    this.c.removeAllViews();
  }

  protected void a(int paramInt)
  {
    if (this.e == null)
      return;
    this.e.a(paramInt);
  }

  protected void a(View paramView)
  {
    a(paramView, this.d);
  }

  public void a(View paramView, List<c> paramList)
  {
    i.a(this.a, this.b, paramView, paramList, this.e);
  }

  public void a(c paramc)
  {
    if (this.d.size() == 0)
      b();
    this.d.add(paramc);
  }

  public void a(d paramd)
  {
    this.e = paramd;
  }

  public View b(int paramInt)
  {
    return this.c.findViewById(paramInt);
  }

  public View b(c paramc)
  {
    return c(paramc);
  }

  protected void b(View paramView, List<c> paramList)
  {
    a(paramView, paramList);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.j
 * JD-Core Version:    0.6.2
 */