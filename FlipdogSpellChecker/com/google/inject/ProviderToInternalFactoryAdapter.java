package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.spi.Dependency;

class ProviderToInternalFactoryAdapter<T>
  implements Provider<T>
{
  private final InjectorImpl injector;
  private final InternalFactory<? extends T> internalFactory;

  public ProviderToInternalFactoryAdapter(InjectorImpl paramInjectorImpl, InternalFactory<? extends T> paramInternalFactory)
  {
    this.injector = paramInjectorImpl;
    this.internalFactory = paramInternalFactory;
  }

  public T get()
  {
    final Errors localErrors = new Errors();
    try
    {
      Object localObject = this.injector.callInContext(new ContextualCallable()
      {
        public T call(InternalContext paramAnonymousInternalContext)
          throws ErrorsException
        {
          Dependency localDependency = paramAnonymousInternalContext.getDependency();
          return ProviderToInternalFactoryAdapter.this.internalFactory.get(localErrors, paramAnonymousInternalContext, localDependency);
        }
      });
      localErrors.throwIfNewErrors(0);
      return localObject;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ProvisionException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public String toString()
  {
    return this.internalFactory.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ProviderToInternalFactoryAdapter
 * JD-Core Version:    0.6.2
 */