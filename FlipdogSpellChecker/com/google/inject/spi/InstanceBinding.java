package com.google.inject.spi;

import com.google.inject.Binding;
import java.util.Set;

public abstract interface InstanceBinding<T> extends Binding<T>, HasDependencies
{
  public abstract Set<InjectionPoint> getInjectionPoints();

  public abstract T getInstance();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.InstanceBinding
 * JD-Core Version:    0.6.2
 */