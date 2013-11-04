package com.google.inject;

abstract interface ConstructionProxyFactory<T>
{
  public abstract ConstructionProxy<T> create();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstructionProxyFactory
 * JD-Core Version:    0.6.2
 */