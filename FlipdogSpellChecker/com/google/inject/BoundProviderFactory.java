package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.spi.Dependency;

class BoundProviderFactory<T>
  implements InternalFactory<T>, BindingProcessor.CreationListener
{
  private final InjectorImpl injector;
  private InternalFactory<? extends Provider<? extends T>> providerFactory;
  final Key<? extends Provider<? extends T>> providerKey;
  final Object source;

  BoundProviderFactory(InjectorImpl paramInjectorImpl, Key<? extends Provider<? extends T>> paramKey, Object paramObject)
  {
    this.injector = paramInjectorImpl;
    this.providerKey = paramKey;
    this.source = paramObject;
  }

  public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    throws ErrorsException
  {
    Errors localErrors = paramErrors.withSource(this.providerKey);
    Provider localProvider = (Provider)this.providerFactory.get(localErrors, paramInternalContext, paramDependency);
    try
    {
      Object localObject = localErrors.checkForNull(localProvider.get(), this.source, paramDependency);
      return localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localErrors.errorInProvider(localRuntimeException).toException();
    }
  }

  public void notify(Errors paramErrors)
  {
    try
    {
      this.providerFactory = this.injector.getInternalFactory(this.providerKey, paramErrors.withSource(this.source));
      return;
    }
    catch (ErrorsException localErrorsException)
    {
      paramErrors.merge(localErrorsException.getErrors());
    }
  }

  public String toString()
  {
    return this.providerKey.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.BoundProviderFactory
 * JD-Core Version:    0.6.2
 */