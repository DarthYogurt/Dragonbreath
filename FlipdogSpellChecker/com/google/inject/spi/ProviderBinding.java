package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.Provider;

public abstract interface ProviderBinding<T extends Provider<?>> extends Binding<T>
{
  public abstract Key<?> getProvidedKey();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ProviderBinding
 * JD-Core Version:    0.6.2
 */