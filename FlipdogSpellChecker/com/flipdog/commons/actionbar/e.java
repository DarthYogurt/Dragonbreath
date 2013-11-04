package com.flipdog.commons.actionbar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.flipdog.commons.b.c;
import com.flipdog.commons.b.i;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class e
{
  private static c a(MenuItem paramMenuItem)
  {
    f localf = (f)paramMenuItem;
    c localc = new c();
    localc.c = localf.b();
    localc.a = localf.getItemId();
    localc.b = localf.a();
    localc.d = localf.getTitle();
    return localc;
  }

  public static List<c> a(Menu paramMenu, Set<Integer> paramSet)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= paramMenu.size())
        return localArrayList;
      MenuItem localMenuItem = paramMenu.getItem(i);
      if (!paramSet.contains(Integer.valueOf(localMenuItem.getItemId())))
        localArrayList.add(a(localMenuItem));
    }
  }

  public static List<c> a(SubMenu paramSubMenu)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= paramSubMenu.size())
        return localArrayList;
      localArrayList.add(a(paramSubMenu.getItem(i)));
    }
  }

  public static void a(Activity paramActivity, View paramView, List<c> paramList)
  {
    i.a(paramActivity, (LayoutInflater)paramActivity.getSystemService("layout_inflater"), paramView, paramList, new q(paramActivity));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.e
 * JD-Core Version:    0.6.2
 */