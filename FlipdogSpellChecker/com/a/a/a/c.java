package com.a.a.a;

abstract class c<T>
{
  public final T a;
  public final String b;

  public c(T paramT)
  {
    this(paramT, null);
  }

  public c(T paramT, String paramString)
  {
    this.a = paramT;
    this.b = paramString;
  }

  public b a(String paramString)
  {
    return new b(this.b, paramString, this.a);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.c
 * JD-Core Version:    0.6.2
 */