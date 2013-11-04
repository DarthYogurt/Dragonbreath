package com.a.a.a;

import java.util.Iterator;

final class h
  implements Iterator<j>
{
  private int a = 0;
  private final String b;
  private final Iterator<Object> c;

  private h(String paramString, Iterator<Object> paramIterator)
  {
    this.b = paramString;
    this.c = paramIterator;
  }

  public j a()
  {
    int i = this.a;
    this.a = (i + 1);
    return new j(this.c.next(), g.a(this.b, i));
  }

  public boolean hasNext()
  {
    return this.c.hasNext();
  }

  public void remove()
  {
    throw new UnsupportedOperationException("can't remove");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.h
 * JD-Core Version:    0.6.2
 */