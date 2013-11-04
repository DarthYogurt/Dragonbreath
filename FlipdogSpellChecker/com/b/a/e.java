package com.b.a;

import android.database.Cursor;
import java.util.Iterator;

public class e<T>
  implements Iterator<T>
{
  private Cursor a;
  private com.b.a.b.e<T> b;
  private boolean c;

  public e(Cursor paramCursor, com.b.a.b.e<T> parame)
  {
    this.a = paramCursor;
    this.b = parame;
    this.c = this.a.moveToNext();
  }

  public void a()
  {
    this.a.close();
  }

  public boolean hasNext()
  {
    return this.c;
  }

  public T next()
  {
    Object localObject = this.b.b(this.a);
    this.c = this.a.moveToNext();
    return localObject;
  }

  public void remove()
  {
    throw new UnsupportedOperationException("remove");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.e
 * JD-Core Version:    0.6.2
 */