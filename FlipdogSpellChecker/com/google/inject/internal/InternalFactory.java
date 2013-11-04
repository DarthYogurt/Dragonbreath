package com.google.inject.internal;

import com.google.inject.spi.Dependency;

public abstract interface InternalFactory<T>
{
  public abstract T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    throws ErrorsException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.InternalFactory
 * JD-Core Version:    0.6.2
 */