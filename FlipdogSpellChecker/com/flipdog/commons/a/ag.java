package com.flipdog.commons.a;

import android.view.Menu;
import android.view.MenuItem;
import com.flipdog.commons.actionbar.f;

public class ag
{
  public static final int a = 327680;

  public static MenuItem a(Menu paramMenu, int paramInt, String paramString)
  {
    return a(paramMenu, paramInt, paramString, -1);
  }

  public static MenuItem a(Menu paramMenu, int paramInt1, String paramString, int paramInt2)
  {
    if (paramInt2 != -1);
    for (int i = 1; ; i = 0)
      return a(paramMenu, paramInt1, paramString, paramInt2, i);
  }

  public static MenuItem a(Menu paramMenu, int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    MenuItem localMenuItem = paramMenu.add(0, paramInt1, 0, paramString);
    if (paramInt2 != -1)
      localMenuItem.setIcon(paramInt2);
    a(localMenuItem, paramInt3);
    return localMenuItem;
  }

  public static void a(MenuItem paramMenuItem, int paramInt)
  {
    if ((paramMenuItem instanceof f))
      ((f)paramMenuItem).a(paramInt);
    do
    {
      return;
      try
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.TYPE;
        arrayOfObject[1] = Integer.valueOf(paramInt);
        c.a(paramMenuItem, "setShowAsAction", arrayOfObject);
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
      }
    }
    while ((localRuntimeException.getCause() instanceof NoSuchMethodException));
    throw localRuntimeException;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ag
 * JD-Core Version:    0.6.2
 */