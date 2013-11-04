package com.b.a.b;

import android.database.Cursor;
import com.flipdog.commons.a.r;

public class c
  implements e<Boolean>
{
  private Boolean a;

  public c()
  {
    this.a = null;
  }

  public c(Boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public Boolean a(Cursor paramCursor)
  {
    return r.a(paramCursor.getString(0), this.a);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.b.c
 * JD-Core Version:    0.6.2
 */