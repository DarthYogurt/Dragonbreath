package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Key;
import java.util.Set;

public abstract interface ConvertedConstantBinding<T> extends Binding<T>, HasDependencies
{
  public abstract Set<Dependency<?>> getDependencies();

  public abstract Key<String> getSourceKey();

  public abstract T getValue();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ConvertedConstantBinding
 * JD-Core Version:    0.6.2
 */