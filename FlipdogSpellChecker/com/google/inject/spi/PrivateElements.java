package com.google.inject.spi;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.util.List;
import java.util.Set;

public abstract interface PrivateElements extends Element
{
  public abstract List<Element> getElements();

  public abstract Set<Key<?>> getExposedKeys();

  public abstract Object getExposedSource(Key<?> paramKey);

  public abstract Injector getInjector();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.PrivateElements
 * JD-Core Version:    0.6.2
 */