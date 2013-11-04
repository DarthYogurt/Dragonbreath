package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Binding;

public abstract interface ExposedBinding<T> extends Binding<T>, HasDependencies
{
  public abstract void applyTo(Binder paramBinder);

  public abstract PrivateElements getPrivateElements();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ExposedBinding
 * JD-Core Version:    0.6.2
 */