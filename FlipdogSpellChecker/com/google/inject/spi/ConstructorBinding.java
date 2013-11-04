package com.google.inject.spi;

import com.google.inject.Binding;
import java.util.Set;

public abstract interface ConstructorBinding<T> extends Binding<T>, HasDependencies
{
  public abstract InjectionPoint getConstructor();

  public abstract Set<InjectionPoint> getInjectableMembers();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ConstructorBinding
 * JD-Core Version:    0.6.2
 */