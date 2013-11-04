package com.google.inject.spi;

import java.util.Set;

public abstract interface HasDependencies
{
  public abstract Set<Dependency<?>> getDependencies();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.HasDependencies
 * JD-Core Version:    0.6.2
 */