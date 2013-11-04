package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Provider;
import java.util.Set;

public abstract interface ProviderInstanceBinding<T> extends Binding<T>, HasDependencies
{
  public abstract Set<InjectionPoint> getInjectionPoints();

  public abstract Provider<? extends T> getProviderInstance();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ProviderInstanceBinding
 * JD-Core Version:    0.6.2
 */