package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.ExposedBinding;
import com.google.inject.spi.PrivateElements;
import java.util.Set;

public class ExposedBindingImpl<T> extends BindingImpl<T>
  implements ExposedBinding<T>
{
  private final PrivateElements privateElements;

  public ExposedBindingImpl(Injector paramInjector, Object paramObject, Key<T> paramKey, InternalFactory<T> paramInternalFactory, PrivateElements paramPrivateElements)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, Scoping.UNSCOPED);
    this.privateElements = paramPrivateElements;
  }

  public ExposedBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping, PrivateElements paramPrivateElements)
  {
    super(paramObject, paramKey, paramScoping);
    this.privateElements = paramPrivateElements;
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    throw new UnsupportedOperationException("This element represents a synthetic binding.");
  }

  public Set<Dependency<?>> getDependencies()
  {
    return ImmutableSet.of(Dependency.get(Key.get(Injector.class)));
  }

  public PrivateElements getPrivateElements()
  {
    return this.privateElements;
  }

  public String toString()
  {
    return new ToStringBuilder(ExposedBinding.class).add("key", getKey()).add("source", getSource()).add("privateElements", this.privateElements).toString();
  }

  public ExposedBindingImpl<T> withKey(Key<T> paramKey)
  {
    return new ExposedBindingImpl(getSource(), paramKey, getScoping(), this.privateElements);
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new ExposedBindingImpl(getSource(), getKey(), paramScoping, this.privateElements);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ExposedBindingImpl
 * JD-Core Version:    0.6.2
 */