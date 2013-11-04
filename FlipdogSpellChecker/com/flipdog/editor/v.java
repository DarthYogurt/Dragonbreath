package com.flipdog.editor;

import android.content.Context;
import com.flipdog.commons.a.a;
import com.flipdog.p;

public class v
{
  public static void a(Context paramContext, aj paramaj, av paramav, i parami)
  {
    d locald = new d(paramContext, paramaj, paramav);
    y localy = new y(paramContext, p.editor_colors_grid, locald);
    localy.a(paramav);
    localy.setOnDismissListener(new j(localy, parami));
    a.a(localy);
    localy.show();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.v
 * JD-Core Version:    0.6.2
 */