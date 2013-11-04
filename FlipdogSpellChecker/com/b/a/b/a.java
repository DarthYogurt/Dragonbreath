package com.b.a.b;

import android.database.Cursor;

class a
  implements e<Integer>
{
  public Integer a(Cursor paramCursor)
  {
    if (paramCursor.isNull(0))
      return null;
    return Integer.valueOf(paramCursor.getInt(0));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.b.a
 * JD-Core Version:    0.6.2
 */