package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ProviderKeyBinding;

public final class LinkedProviderBindingImpl<T> extends BindingImpl<T>
  implements ProviderKeyBinding<T>
{
  final Key<? extends Provider<? extends T>> providerKey;

  public LinkedProviderBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping, Key<? extends Provider<? extends T>> paramKey1)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, paramScoping);
    this.providerKey = paramKey1;
  }

  LinkedProviderBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping, Key<? extends Provider<? extends T>> paramKey1)
  {
    super(paramObject, paramKey, paramScoping);
    this.providerKey = paramKey1;
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()).toProvider(getProviderKey()));
  }

  public Key<? extends Provider<? extends T>> getProviderKey()
  {
    return this.providerKey;
  }

  public String toString()
  {
    return new ToStringBuilder(ProviderKeyBinding.class).add("key", getKey()).add("source", getSource()).add("scope", getScoping()).add("provider", this.providerKey).toString();
  }

  public BindingImpl<T> withKey(Key<T> paramKey)
  {
    return new LinkedProviderBindingImpl(getSource(), paramKey, getScoping(), this.providerKey);
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new LinkedProviderBindingImpl(getSource(), getKey(), paramScoping, this.providerKey);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.LinkedProviderBindingImpl
 * JD-Core Version:    0.6.2
 */