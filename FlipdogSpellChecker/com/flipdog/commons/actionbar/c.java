package com.flipdog.commons.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

public class c extends l
{
  protected c(Activity paramActivity)
  {
    super(paramActivity);
  }

  private ActionBar c()
  {
    return this.a.getActionBar();
  }

  protected Context a()
  {
    return c().getThemedContext();
  }

  public void a(boolean paramBoolean)
  {
    ActionBar localActionBar = c();
    if (localActionBar != null)
      localActionBar.setHomeButtonEnabled(paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.c
 * JD-Core Version:    0.6.2
 */