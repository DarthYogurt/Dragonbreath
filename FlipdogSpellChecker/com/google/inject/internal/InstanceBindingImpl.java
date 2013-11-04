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
import com.google.inject.spi.InstanceBinding;
import com.google.inject.util.Providers;
import java.util.Set;

public class InstanceBindingImpl<T> extends BindingImpl<T>
  implements InstanceBinding<T>
{
  final ImmutableSet<InjectionPoint> injectionPoints;
  final T instance;
  final Provider<T> provider;

  public InstanceBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Set<InjectionPoint> paramSet, T paramT)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, Scoping.UNSCOPED);
    this.injectionPoints = ImmutableSet.copyOf(paramSet);
    this.instance = paramT;
    this.provider = Providers.of(paramT);
  }

  public InstanceBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping, Set<InjectionPoint> paramSet, T paramT)
  {
    super(paramObject, paramKey, paramScoping);
    this.injectionPoints = ImmutableSet.copyOf(paramSet);
    this.instance = paramT;
    this.provider = Providers.of(paramT);
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).bind(getKey()).toInstance(this.instance);
  }

  public Set<Dependency<?>> getDependencies()
  {
    if ((this.instance instanceof HasDependencies))
      return ImmutableSet.copyOf(((HasDependencies)this.instance).getDependencies());
    return Dependency.forInjectionPoints(this.injectionPoints);
  }

  public Set<InjectionPoint> getInjectionPoints()
  {
    return this.injectionPoints;
  }

  public T getInstance()
  {
    return this.instance;
  }

  public Provider<T> getProvider()
  {
    return this.provider;
  }

  public String toString()
  {
    return new ToStringBuilder(InstanceBinding.class).add("key", getKey()).add("source", getSource()).add("instance", this.instance).toString();
  }

  public BindingImpl<T> withKey(Key<T> paramKey)
  {
    return new InstanceBindingImpl(getSource(), paramKey, getScoping(), this.injectionPoints, this.instance);
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new InstanceBindingImpl(getSource(), getKey(), paramScoping, this.injectionPoints, this.instance);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.InstanceBindingImpl
 * JD-Core Version:    0.6.2
 */