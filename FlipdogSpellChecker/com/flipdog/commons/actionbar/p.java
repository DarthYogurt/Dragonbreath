package com.flipdog.commons.actionbar;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import com.flipdog.commons.b.b;
import com.flipdog.commons.b.c;
import java.util.Iterator;
import java.util.List;

class p extends MenuInflater
{
  private Context a;

  public p(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext;
  }

  public void inflate(int paramInt, Menu paramMenu)
  {
    Iterator localIterator = b.a(this.a, paramInt).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      c localc = (c)localIterator.next();
      f localf = (f)paramMenu.add(0, localc.a, 0, localc.d);
      localf.setIcon(localc.c);
      localf.a(localc.b);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.p
 * JD-Core Version:    0.6.2
 */