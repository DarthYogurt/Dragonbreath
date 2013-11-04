package com.flipdog.commons.a;

import android.database.Cursor;

class ao
{
  private String a;
  private Cursor b;
  private int c;

  public ao(Cursor paramCursor, int paramInt)
  {
    this.b = paramCursor;
    this.c = paramInt;
    this.a = paramCursor.getColumnName(paramInt);
  }

  public String a()
  {
    return this.b.getString(this.c);
  }

  public boolean a(String paramString)
  {
    return ax.c(this.a, paramString);
  }

  public long b()
  {
    return this.b.getLong(this.c);
  }

  public String c()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ao
 * JD-Core Version:    0.6.2
 */