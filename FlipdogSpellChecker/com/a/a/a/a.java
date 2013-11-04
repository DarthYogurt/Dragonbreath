package com.a.a.a;

import java.util.Map.Entry;

final class a
  implements Map.Entry<String, j>
{
  private final String a;
  private final j b;

  private a(String paramString, Map.Entry<String, Object> paramEntry)
  {
    this.a = ((String)paramEntry.getKey());
    this.b = new j(paramEntry.getValue(), e.a(paramString, this.a));
  }

  public j a(j paramj)
  {
    throw new UnsupportedOperationException();
  }

  public String a()
  {
    return this.a;
  }

  public j b()
  {
    return this.b;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.a
 * JD-Core Version:    0.6.2
 */