package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.LinkedKeyBinding;

public final class LinkedBindingImpl<T> extends BindingImpl<T>
  implements LinkedKeyBinding<T>
{
  final Key<? extends T> targetKey;

  public LinkedBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping, Key<? extends T> paramKey1)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, paramScoping);
    this.targetKey = paramKey1;
  }

  public LinkedBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping, Key<? extends T> paramKey1)
  {
    super(paramObject, paramKey, paramScoping);
    this.targetKey = paramKey1;
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()).to(getLinkedKey()));
  }

  public Key<? extends T> getLinkedKey()
  {
    return this.targetKey;
  }

  public String toString()
  {
    return new ToStringBuilder(LinkedKeyBinding.class).add("key", getKey()).add("source", getSource()).add("scope", getScoping()).add("target", this.targetKey).toString();
  }

  public BindingImpl<T> withKey(Key<T> paramKey)
  {
    return new LinkedBindingImpl(getSource(), paramKey, getScoping(), this.targetKey);
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new LinkedBindingImpl(getSource(), getKey(), paramScoping, this.targetKey);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.LinkedBindingImpl
 * JD-Core Version:    0.6.2
 */