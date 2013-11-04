package com.google.inject;

abstract interface Lookups
{
  public abstract <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> Provider<T> getProvider(Key<T> paramKey);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Lookups
 * JD-Core Version:    0.6.2
 */