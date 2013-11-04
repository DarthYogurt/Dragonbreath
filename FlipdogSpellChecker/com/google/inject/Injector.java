package com.google.inject;

import java.util.List;
import java.util.Map;

public abstract interface Injector
{
  public abstract Injector createChildInjector(Iterable<? extends Module> paramIterable);

  public abstract Injector createChildInjector(Module[] paramArrayOfModule);

  public abstract <T> List<Binding<T>> findBindingsByType(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> Binding<T> getBinding(Key<T> paramKey);

  public abstract <T> Binding<T> getBinding(Class<T> paramClass);

  public abstract Map<Key<?>, Binding<?>> getBindings();

  public abstract <T> T getInstance(Key<T> paramKey);

  public abstract <T> T getInstance(Class<T> paramClass);

  public abstract <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> MembersInjector<T> getMembersInjector(Class<T> paramClass);

  public abstract Injector getParent();

  public abstract <T> Provider<T> getProvider(Key<T> paramKey);

  public abstract <T> Provider<T> getProvider(Class<T> paramClass);

  public abstract void injectMembers(Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Injector
 * JD-Core Version:    0.6.2
 */