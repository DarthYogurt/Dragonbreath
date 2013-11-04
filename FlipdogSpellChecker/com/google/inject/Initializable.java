package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;

abstract interface Initializable<T>
{
  public abstract T get(Errors paramErrors)
    throws ErrorsException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Initializable
 * JD-Core Version:    0.6.2
 */