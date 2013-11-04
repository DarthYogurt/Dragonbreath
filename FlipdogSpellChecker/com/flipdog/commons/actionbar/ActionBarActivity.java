package com.flipdog.commons.actionbar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.b.h;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a;

public abstract class ActionBarActivity extends MyActivity
{
  final r t = r.a(this);

  public void a(d paramd)
  {
    this.t.a(paramd);
  }

  public void a(boolean paramBoolean)
  {
    this.t.c(paramBoolean);
  }

  public r e()
  {
    return this.t;
  }

  public void f()
  {
    this.t.b();
  }

  protected boolean g()
  {
    return false;
  }

  public MenuInflater getMenuInflater()
  {
    return this.t.a(super.getMenuInflater());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.t.a(paramBundle);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false | this.t.a(paramMenu) | super.onCreateOptionsMenu(paramMenu);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 82) && ((this.t instanceof g)))
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 82)
    {
      if ((this.t instanceof g))
      {
        ((a)a().a(a.class)).a();
        return true;
      }
    }
    else if ((paramInt == 4) && (g()))
      return true;
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
    {
      if (g())
        return true;
      finish();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    this.t.b(paramBundle);
  }

  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt)
  {
    this.t.a(paramCharSequence, paramInt);
    super.onTitleChanged(paramCharSequence, paramInt);
  }

  public void setContentView(int paramInt)
  {
    e().a(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.ActionBarActivity
 * JD-Core Version:    0.6.2
 */