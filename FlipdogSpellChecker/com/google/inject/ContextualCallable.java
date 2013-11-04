package com.google.inject;

import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;

abstract interface ContextualCallable<T>
{
  public abstract T call(InternalContext paramInternalContext)
    throws ErrorsException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ContextualCallable
 * JD-Core Version:    0.6.2
 */