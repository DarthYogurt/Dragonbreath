package com.google.inject;

import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableSet.Builder;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.Scoping;
import com.google.inject.internal.ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ConstructorBinding;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.InjectionPoint;
import java.util.Set;

class ConstructorBindingImpl<T> extends BindingImpl<T>
  implements ConstructorBinding<T>
{
  private final Factory<T> factory;

  private ConstructorBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping, Factory<T> paramFactory)
  {
    super(paramInjector, paramKey, paramObject, paramInternalFactory, paramScoping);
    this.factory = paramFactory;
  }

  static <T> ConstructorBindingImpl<T> create(InjectorImpl paramInjectorImpl, Key<T> paramKey, Object paramObject, Scoping paramScoping)
  {
    Factory localFactory = new Factory(null);
    return new ConstructorBindingImpl(paramInjectorImpl, paramKey, paramObject, Scopes.scope(paramKey, paramInjectorImpl, localFactory, paramScoping), paramScoping, localFactory);
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    if (this.factory.constructorInjector != null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "not initialized");
      return paramBindingTargetVisitor.visit(this);
    }
  }

  public void applyTo(Binder paramBinder)
  {
    throw new UnsupportedOperationException("This element represents a synthetic binding.");
  }

  public InjectionPoint getConstructor()
  {
    if (this.factory.constructorInjector != null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "Binding is not ready");
      return this.factory.constructorInjector.getConstructionProxy().getInjectionPoint();
    }
  }

  public Set<Dependency<?>> getDependencies()
  {
    return Dependency.forInjectionPoints(new ImmutableSet.Builder().add(getConstructor()).addAll(getInjectableMembers()).build());
  }

  public Set<InjectionPoint> getInjectableMembers()
  {
    if (this.factory.constructorInjector != null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "Binding is not ready");
      return this.factory.constructorInjector.getInjectableMembers();
    }
  }

  public void initialize(InjectorImpl paramInjectorImpl, Errors paramErrors)
    throws ErrorsException
  {
    Factory.access$102(this.factory, paramInjectorImpl.constructors.get(getKey().getTypeLiteral(), paramErrors));
  }

  public String toString()
  {
    return new ToStringBuilder(ConstructorBinding.class).add("key", getKey()).add("source", getSource()).add("scope", getScoping()).toString();
  }

  private static class Factory<T>
    implements InternalFactory<T>
  {
    private ConstructorInjector<T> constructorInjector;

    public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
      throws ErrorsException
    {
      if (this.constructorInjector != null);
      for (boolean bool = true; ; bool = false)
      {
        Preconditions.checkState(bool, "Constructor not ready");
        return this.constructorInjector.construct(paramErrors, paramInternalContext, paramDependency.getKey().getRawType());
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstructorBindingImpl
 * JD-Core Version:    0.6.2
 */