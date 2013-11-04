package com.flipdog.commons.actionbar;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public abstract class r
{
  protected Activity a;

  protected r(Activity paramActivity)
  {
    this.a = paramActivity;
  }

  public static r a(Activity paramActivity)
  {
    int i = Integer.parseInt(Build.VERSION.SDK);
    if (i >= 14)
      return new c(paramActivity);
    if (i >= 11)
      return new l(paramActivity);
    return new g(paramActivity);
  }

  public MenuInflater a(MenuInflater paramMenuInflater)
  {
    return paramMenuInflater;
  }

  public abstract void a(int paramInt);

  public void a(Bundle paramBundle)
  {
  }

  public abstract void a(View paramView);

  public abstract void a(d paramd);

  protected void a(CharSequence paramCharSequence, int paramInt)
  {
  }

  public abstract void a(boolean paramBoolean);

  public boolean a(Menu paramMenu)
  {
    return true;
  }

  public abstract void b();

  public void b(Bundle paramBundle)
  {
  }

  public abstract void b(boolean paramBoolean);

  public abstract boolean b(int paramInt);

  public abstract void c(boolean paramBoolean);

  public abstract boolean c(int paramInt);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.r
 * JD-Core Version:    0.6.2
 */