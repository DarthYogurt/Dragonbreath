package com.google.inject.spi;

import com.google.inject.Provider;

public abstract interface ProviderWithDependencies<T> extends Provider<T>, HasDependencies
{
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ProviderWithDependencies
 * JD-Core Version:    0.6.2
 */