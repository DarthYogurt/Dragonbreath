package com.flipdog.commons.actionbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class f
  implements MenuItem
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 4;
  public static final int e = 8;
  private k f;
  private final int g;
  private final int h;
  private CharSequence i;
  private CharSequence j;
  private Drawable k;
  private int l = 0;
  private boolean m = true;
  private boolean n = true;
  private int o;
  private k p;

  public f(int paramInt)
  {
    this.g = paramInt;
    this.h = 0;
  }

  public f(k paramk, int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    this.f = paramk;
    this.g = paramInt1;
    this.h = paramInt2;
    this.i = paramCharSequence;
  }

  public int a()
  {
    return this.o;
  }

  public void a(int paramInt)
  {
    this.o = paramInt;
  }

  public void a(k paramk)
  {
    this.p = paramk;
  }

  public int b()
  {
    return this.l;
  }

  public boolean collapseActionView()
  {
    return false;
  }

  public boolean expandActionView()
  {
    return false;
  }

  public ActionProvider getActionProvider()
  {
    return null;
  }

  public View getActionView()
  {
    return null;
  }

  public char getAlphabeticShortcut()
  {
    return '\000';
  }

  public int getGroupId()
  {
    return 0;
  }

  public Drawable getIcon()
  {
    if (this.k != null)
      return this.k;
    if (this.l != 0)
      return this.f.b().getDrawable(this.l);
    return null;
  }

  public Intent getIntent()
  {
    return null;
  }

  public int getItemId()
  {
    return this.g;
  }

  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    return null;
  }

  public char getNumericShortcut()
  {
    return '\000';
  }

  public int getOrder()
  {
    return this.h;
  }

  public SubMenu getSubMenu()
  {
    return this.p;
  }

  public CharSequence getTitle()
  {
    return this.i;
  }

  public CharSequence getTitleCondensed()
  {
    if (this.j != null)
      return this.j;
    return this.i;
  }

  public boolean hasSubMenu()
  {
    return this.p != null;
  }

  public boolean isActionViewExpanded()
  {
    return false;
  }

  public boolean isCheckable()
  {
    return false;
  }

  public boolean isChecked()
  {
    return false;
  }

  public boolean isEnabled()
  {
    return this.m;
  }

  public boolean isVisible()
  {
    return this.n;
  }

  public MenuItem setActionProvider(ActionProvider paramActionProvider)
  {
    return this;
  }

  public MenuItem setActionView(int paramInt)
  {
    return this;
  }

  public MenuItem setActionView(View paramView)
  {
    return this;
  }

  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    return this;
  }

  public MenuItem setCheckable(boolean paramBoolean)
  {
    return this;
  }

  public MenuItem setChecked(boolean paramBoolean)
  {
    return this;
  }

  public MenuItem setEnabled(boolean paramBoolean)
  {
    this.m = paramBoolean;
    return this;
  }

  public MenuItem setIcon(int paramInt)
  {
    this.k = null;
    this.l = paramInt;
    return this;
  }

  public MenuItem setIcon(Drawable paramDrawable)
  {
    this.l = 0;
    this.k = paramDrawable;
    return this;
  }

  public MenuItem setIntent(Intent paramIntent)
  {
    return this;
  }

  public MenuItem setNumericShortcut(char paramChar)
  {
    return this;
  }

  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    return this;
  }

  public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    return this;
  }

  public MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    return this;
  }

  public void setShowAsAction(int paramInt)
  {
    throw new RuntimeException("Use setShowAsActionCustom instead");
  }

  public MenuItem setShowAsActionFlags(int paramInt)
  {
    return null;
  }

  public MenuItem setTitle(int paramInt)
  {
    return setTitle(this.f.a().getString(paramInt));
  }

  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    this.i = paramCharSequence;
    return this;
  }

  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    this.j = paramCharSequence;
    return this;
  }

  public MenuItem setVisible(boolean paramBoolean)
  {
    this.n = paramBoolean;
    return this;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.f
 * JD-Core Version:    0.6.2
 */