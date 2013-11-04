package com.a.a.b;

import java.io.Serializable;

public abstract class n
  implements Serializable
{
  private static final long c = -42727403799660313L;
  public final String a;
  public final String b;

  public n(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("'key' must be non-null");
    if (paramString1.contains("|"))
      throw new IllegalArgumentException("'key' must not contain a \"|\" character: \"" + paramString1 + "\"");
    if (paramString2 == null)
      throw new IllegalArgumentException("'secret' must be non-null");
    this.a = paramString1;
    this.b = paramString2;
  }

  public boolean a(n paramn)
  {
    return (this.a.equals(paramn.a)) && (this.b.equals(paramn.b));
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof n)) && (a((n)paramObject));
  }

  public int hashCode()
  {
    return this.a.hashCode() ^ this.b.hashCode() << 1;
  }

  public String toString()
  {
    return "{key=\"" + this.a + "\", secret=\"" + this.b + "\"}";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.n
 * JD-Core Version:    0.6.2
 */