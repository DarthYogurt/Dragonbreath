package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.HasDependencies;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.ProviderInstanceBinding;
import java.util.Set;

public final class ProviderInstanceBindingImpl<T> extends BindingImpl<T>
  implements ProviderInstanceBinding<T>
{
  final ImmutableSet<InjectionPoint> injectionPoints;
  final Provider<? extends T> providerInstance;

  public ProviderInstanceBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping, Provider<? extends T> paramProvider, Set<InjectionPoint> paramSet)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, paramScoping);
    this.providerInstance = paramProvider;
    this.injectionPoints = ImmutableSet.copyOf(paramSet);
  }

  public ProviderInstanceBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping, Set<InjectionPoint> paramSet, Provider<? extends T> paramProvider)
  {
    super(paramObject, paramKey, paramScoping);
    this.injectionPoints = ImmutableSet.copyOf(paramSet);
    this.providerInstance = paramProvider;
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()).toProvider(getProviderInstance()));
  }

  public Set<Dependency<?>> getDependencies()
  {
    if ((this.providerInstance instanceof HasDependencies))
      return ImmutableSet.copyOf(((HasDependencies)this.providerInstance).getDependencies());
    return Dependency.forInjectionPoints(this.injectionPoints);
  }

  public Set<InjectionPoint> getInjectionPoints()
  {
    return this.injectionPoints;
  }

  public Provider<? extends T> getProviderInstance()
  {
    return this.providerInstance;
  }

  public String toString()
  {
    return new ToStringBuilder(ProviderInstanceBinding.class).add("key", getKey()).add("source", getSource()).add("scope", getScoping()).add("provider", this.providerInstance).toString();
  }

  public BindingImpl<T> withKey(Key<T> paramKey)
  {
    return new ProviderInstanceBindingImpl(getSource(), paramKey, getScoping(), this.injectionPoints, this.providerInstance);
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new ProviderInstanceBindingImpl(getSource(), getKey(), paramScoping, this.injectionPoints, this.providerInstance);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ProviderInstanceBindingImpl
 * JD-Core Version:    0.6.2
 */