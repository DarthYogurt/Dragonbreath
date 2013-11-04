package com.flipdog.filebrowser.a;

import android.view.Menu;
import android.view.MenuItem;
import com.flipdog.a;
import com.flipdog.commons.a.ag;
import com.flipdog.filebrowser.e.b;
import com.flipdog.filebrowser.g.g;
import com.flipdog.m;
import com.flipdog.s;

public class f
{
  private final com.flipdog.filebrowser.d.h a;
  private final com.flipdog.filebrowser.login.b.c b;

  public f(com.flipdog.filebrowser.d.h paramh, com.flipdog.filebrowser.login.b.c paramc)
  {
    this.a = paramh;
    this.b = paramc;
  }

  private b a()
  {
    return this.a.b();
  }

  private void a(b paramb, int paramInt, Object paramObject)
  {
    com.flipdog.filebrowser.g.h.a(paramInt, paramb).a(paramObject, this.a.f());
  }

  private void c(Menu paramMenu)
  {
    ag.a(paramMenu, m.fbrowse_oper_create_folder, com.flipdog.filebrowser.k.c.a(s.fbrowse_oper_create_folder), -1, 0);
    ag.a(paramMenu, m.fbrowse_oper_refresh, com.flipdog.filebrowser.k.c.a(s.fbrowse_oper_refresh), -1, 0);
  }

  private void d(Menu paramMenu)
  {
    ag.a(paramMenu, m.fbrowse_action_done, com.flipdog.filebrowser.k.c.a(s.fbrowse_action_done), -1, 2);
    ag.a(paramMenu, m.fbrowse_action_discard, com.flipdog.filebrowser.k.c.a(17039360), -1, 2);
  }

  private void e(Menu paramMenu)
  {
    ag.a(paramMenu, m.fbrowse_select_all, com.flipdog.filebrowser.k.c.a(s.fbrowse_show_file_select_all_in_folder), a.fbrowse_select_all_folder, 0);
    ag.a(paramMenu, m.fbrowse_select_clear_in_folder, com.flipdog.filebrowser.k.c.a(s.fbrowse_show_file_select_clear_all_in_folder), a.fbrowse_clear_in_folder, 0);
    ag.a(paramMenu, m.fbrowse_select_clear_all, com.flipdog.filebrowser.k.c.a(s.fbrowse_show_file_select_clear_all), a.fbrowse_clear_all, 0);
  }

  public void a(Menu paramMenu)
  {
    b localb = this.a.b();
    if (!(localb instanceof com.flipdog.filebrowser.e.c));
    while (true)
    {
      return;
      boolean bool = ((com.flipdog.filebrowser.e.c)localb).a();
      int[] arrayOfInt = new int[3];
      arrayOfInt[0] = m.fbrowse_logging;
      arrayOfInt[1] = m.fbrowse_oper_refresh;
      arrayOfInt[2] = m.fbrowse_oper_create_folder;
      int i = arrayOfInt.length;
      for (int j = 0; j < i; j++)
        paramMenu.findItem(arrayOfInt[j]).setVisible(bool);
    }
  }

  public void a(MenuItem paramMenuItem)
  {
    int i = paramMenuItem.getItemId();
    b localb = a();
    if (i == m.fbrowse_logging)
    {
      ((com.flipdog.filebrowser.e.c)localb).a(false);
      return;
    }
    if (i == m.fbrowse_action_done)
    {
      this.b.a(Integer.valueOf(1));
      return;
    }
    if (i == m.fbrowse_action_discard)
    {
      this.b.a(Integer.valueOf(0));
      return;
    }
    if (i == m.fbrowse_oper_create_folder)
    {
      a(localb, 3, localb.e());
      return;
    }
    if (i == m.fbrowse_oper_refresh)
    {
      a(localb, 4, null);
      return;
    }
    int j;
    if (i == m.fbrowse_select_all)
      j = 1;
    while (true)
    {
      this.a.b().a(j);
      return;
      if (i == m.fbrowse_select_clear_in_folder)
      {
        j = 2;
      }
      else
      {
        if (i != m.fbrowse_select_clear_all)
          break;
        j = 3;
      }
    }
  }

  public void b(Menu paramMenu)
  {
    Class localClass = this.a.b().getClass();
    if (localClass.equals(com.flipdog.filebrowser.e.f.class));
    do
    {
      return;
      d(paramMenu);
      e(paramMenu);
      c(paramMenu);
    }
    while (!localClass.equals(com.flipdog.filebrowser.e.c.class));
    ag.a(paramMenu, m.fbrowse_logging, com.flipdog.filebrowser.k.c.a(s.fbrowse_button_logout), a.fbrowse_logging, 0);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.f
 * JD-Core Version:    0.6.2
 */