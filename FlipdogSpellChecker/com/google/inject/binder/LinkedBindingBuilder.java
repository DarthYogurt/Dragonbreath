package com.google.inject.binder;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public abstract interface LinkedBindingBuilder<T> extends ScopedBindingBuilder
{
  public abstract ScopedBindingBuilder to(Key<? extends T> paramKey);

  public abstract ScopedBindingBuilder to(TypeLiteral<? extends T> paramTypeLiteral);

  public abstract ScopedBindingBuilder to(Class<? extends T> paramClass);

  public abstract void toInstance(T paramT);

  public abstract ScopedBindingBuilder toProvider(Key<? extends Provider<? extends T>> paramKey);

  public abstract ScopedBindingBuilder toProvider(Provider<? extends T> paramProvider);

  public abstract ScopedBindingBuilder toProvider(Class<? extends Provider<? extends T>> paramClass);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.LinkedBindingBuilder
 * JD-Core Version:    0.6.2
 */