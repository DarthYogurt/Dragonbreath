package com.google.inject.internal;

public abstract interface Function<F, T>
{
  public abstract T apply(@Nullable F paramF);

  public abstract boolean equals(@Nullable Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Function
 * JD-Core Version:    0.6.2
 */