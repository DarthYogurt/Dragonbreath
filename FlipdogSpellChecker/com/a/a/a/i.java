package com.a.a.a;

import java.util.Iterator;
import java.util.Map.Entry;

final class i
  implements Iterator<Map.Entry<String, j>>
{
  private final String a;
  private final Iterator<Map.Entry<String, Object>> b;

  private i(String paramString, Iterator<Map.Entry<String, Object>> paramIterator)
  {
    this.a = paramString;
    this.b = paramIterator;
  }

  public Map.Entry<String, j> a()
  {
    return new a(this.a, (Map.Entry)this.b.next(), null);
  }

  public boolean hasNext()
  {
    return this.b.hasNext();
  }

  public void remove()
  {
    throw new UnsupportedOperationException("can't remove");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.i
 * JD-Core Version:    0.6.2
 */