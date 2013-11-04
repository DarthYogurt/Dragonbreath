package com.flipdog.commons.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.flipdog.m;
import com.flipdog.p;

public class l extends r
{
  private Menu b;
  private View c = null;

  protected l(Activity paramActivity)
  {
    super(paramActivity);
  }

  protected Context a()
  {
    return this.a;
  }

  protected b a(ActionMode paramActionMode)
  {
    return new i(this, paramActionMode);
  }

  public void a(int paramInt)
  {
    this.a.getWindow().setContentView(paramInt);
  }

  public void a(View paramView)
  {
    ActionBar localActionBar = this.a.getActionBar();
    if (localActionBar == null)
      return;
    localActionBar.setDisplayOptions(0x10 | localActionBar.getDisplayOptions());
    localActionBar.setCustomView(paramView);
  }

  public void a(d paramd)
  {
    this.a.startActionMode(new j(this, paramd));
  }

  public void a(boolean paramBoolean)
  {
  }

  public boolean a(Menu paramMenu)
  {
    this.b = paramMenu;
    return super.a(paramMenu);
  }

  public void b()
  {
    this.a.invalidateOptionsMenu();
  }

  public void b(boolean paramBoolean)
  {
    if (this.b == null);
    MenuItem localMenuItem;
    do
    {
      return;
      localMenuItem = this.b.findItem(m.menu_refresh);
    }
    while (localMenuItem == null);
    if (paramBoolean)
    {
      if (this.c == null)
        this.c = ((LayoutInflater)a().getSystemService("layout_inflater")).inflate(p.actionbar_indeterminate_progress, null);
      localMenuItem.setActionView(this.c);
      return;
    }
    localMenuItem.setActionView(null);
  }

  public boolean b(int paramInt)
  {
    return this.a.getWindow().hasFeature(paramInt);
  }

  public void c(boolean paramBoolean)
  {
    ActionBar localActionBar = this.a.getActionBar();
    if (localActionBar == null)
      return;
    localActionBar.setDisplayHomeAsUpEnabled(paramBoolean);
  }

  public boolean c(int paramInt)
  {
    return this.a.getWindow().requestFeature(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.l
 * JD-Core Version:    0.6.2
 */