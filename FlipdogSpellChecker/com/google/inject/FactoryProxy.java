package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.ToStringBuilder;
import com.google.inject.spi.Dependency;

class FactoryProxy<T>
  implements InternalFactory<T>, BindingProcessor.CreationListener
{
  private final InjectorImpl injector;
  private final Key<T> key;
  private final Object source;
  private InternalFactory<? extends T> targetFactory;
  private final Key<? extends T> targetKey;

  FactoryProxy(InjectorImpl paramInjectorImpl, Key<T> paramKey, Key<? extends T> paramKey1, Object paramObject)
  {
    this.injector = paramInjectorImpl;
    this.key = paramKey;
    this.targetKey = paramKey1;
    this.source = paramObject;
  }

  public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    throws ErrorsException
  {
    return this.targetFactory.get(paramErrors.withSource(this.targetKey), paramInternalContext, paramDependency);
  }

  public void notify(Errors paramErrors)
  {
    try
    {
      this.targetFactory = this.injector.getInternalFactory(this.targetKey, paramErrors.withSource(this.source));
      return;
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.merge(localErrorsException.getErrors());
    }
  }

  public String toString()
  {
    return new ToStringBuilder(FactoryProxy.class).add("key", this.key).add("provider", this.targetFactory).toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.FactoryProxy
 * JD-Core Version:    0.6.2
 */