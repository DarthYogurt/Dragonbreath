package com.google.inject;

public abstract interface Scope
{
  public abstract <T> Provider<T> scope(Key<T> paramKey, Provider<T> paramProvider);

  public abstract String toString();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Scope
 * JD-Core Version:    0.6.2
 */