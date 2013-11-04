package com.flipdog.commons.actionbar;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.util.ArrayList;

public class k
  implements SubMenu
{
  private Context a;
  private Resources b;
  private ArrayList<f> c;
  private f d;

  public k(Context paramContext)
  {
    this.a = paramContext;
    this.b = paramContext.getResources();
    this.c = new ArrayList();
  }

  private static int a(ArrayList<? extends MenuItem> paramArrayList, int paramInt)
  {
    for (int i = -1 + paramArrayList.size(); ; i--)
    {
      if (i < 0)
        return 0;
      if (((MenuItem)paramArrayList.get(i)).getOrder() <= paramInt)
        return i + 1;
    }
  }

  private MenuItem a(int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    f localf = new f(this, paramInt1, paramInt2, paramCharSequence);
    this.c.add(a(this.c, paramInt2), localf);
    return localf;
  }

  private void a(f paramf)
  {
    this.d = paramf;
  }

  private void b(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.c.size()))
      return;
    this.c.remove(paramInt);
  }

  public int a(int paramInt)
  {
    int i = size();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return -1;
      if (((f)this.c.get(j)).getItemId() == paramInt)
        return j;
    }
  }

  public Context a()
  {
    return this.a;
  }

  public MenuItem add(int paramInt)
  {
    return a(0, 0, this.b.getString(paramInt));
  }

  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return a(paramInt2, paramInt3, this.b.getString(paramInt4));
  }

  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    return a(paramInt2, paramInt3, paramCharSequence);
  }

  public MenuItem add(CharSequence paramCharSequence)
  {
    return a(0, 0, paramCharSequence);
  }

  public int addIntentOptions(int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt4, MenuItem[] paramArrayOfMenuItem)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu addSubMenu(int paramInt)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    f localf = (f)add(paramInt1, paramInt2, paramInt3, paramCharSequence);
    k localk = new k(this.a);
    localk.a(localf);
    localf.a(localk);
    return localk;
  }

  public SubMenu addSubMenu(CharSequence paramCharSequence)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public Resources b()
  {
    return this.b;
  }

  public void clear()
  {
    this.c.clear();
  }

  public void clearHeader()
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public void close()
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public MenuItem findItem(int paramInt)
  {
    int i = size();
    for (int j = 0; ; j++)
    {
      Object localObject;
      if (j >= i)
        localObject = null;
      do
      {
        return localObject;
        localObject = (f)this.c.get(j);
      }
      while (((f)localObject).getItemId() == paramInt);
    }
  }

  public MenuItem getItem()
  {
    return this.d;
  }

  public MenuItem getItem(int paramInt)
  {
    return (MenuItem)this.c.get(paramInt);
  }

  public boolean hasVisibleItems()
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public boolean isShortcutKey(int paramInt, KeyEvent paramKeyEvent)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public boolean performIdentifierAction(int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public boolean performShortcut(int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public void removeGroup(int paramInt)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public void removeItem(int paramInt)
  {
    b(a(paramInt));
  }

  public void setGroupCheckable(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public void setGroupEnabled(int paramInt, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public void setGroupVisible(int paramInt, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setHeaderIcon(int paramInt)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setHeaderIcon(Drawable paramDrawable)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setHeaderTitle(int paramInt)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setHeaderTitle(CharSequence paramCharSequence)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setHeaderView(View paramView)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public SubMenu setIcon(int paramInt)
  {
    this.d.setIcon(paramInt);
    return this;
  }

  public SubMenu setIcon(Drawable paramDrawable)
  {
    this.d.setIcon(paramDrawable);
    return this;
  }

  public void setQwertyMode(boolean paramBoolean)
  {
    throw new UnsupportedOperationException("This operation is not supported for SimpleMenu");
  }

  public int size()
  {
    return this.c.size();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.k
 * JD-Core Version:    0.6.2
 */