package com.flipdog.commons.actionbar;

import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;

class j
  implements ActionMode.Callback
{
  j(l paraml, d paramd)
  {
  }

  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return this.b.a(this.a.a(paramActionMode), paramMenuItem);
  }

  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return this.b.a(this.a.a(paramActionMode), paramMenu);
  }

  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    this.b.a(this.a.a(paramActionMode));
  }

  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return this.b.b(this.a.a(paramActionMode), paramMenu);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.j
 * JD-Core Version:    0.6.2
 */