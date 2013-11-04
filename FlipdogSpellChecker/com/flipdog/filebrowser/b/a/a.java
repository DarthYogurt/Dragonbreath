package com.flipdog.filebrowser.b.a;

import android.graphics.drawable.Drawable;
import com.flipdog.filebrowser.d.h;
import com.flipdog.filebrowser.k.c;
import com.flipdog.s;

public class a extends com.flipdog.a.b.a.a
{
  public Drawable d;

  protected a(int paramInt)
  {
    this.d = c.c(paramInt);
    this.a = h.b;
    this.c = null;
    this.b = c.a(s.fbrowse_sdcard);
  }

  public static com.flipdog.a.b.a.a a(int paramInt)
  {
    return new a(paramInt);
  }

  public static com.flipdog.a.b.a.a c()
  {
    return new a(com.flipdog.a.fbrowse_sdcard);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.a.a
 * JD-Core Version:    0.6.2
 */