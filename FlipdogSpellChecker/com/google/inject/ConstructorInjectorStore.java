package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.FailableCache;
import com.google.inject.spi.InjectionPoint;

class ConstructorInjectorStore
{
  private final FailableCache<TypeLiteral<?>, ConstructorInjector<?>> cache = new FailableCache()
  {
    protected ConstructorInjector<?> create(TypeLiteral<?> paramAnonymousTypeLiteral, Errors paramAnonymousErrors)
      throws ErrorsException
    {
      return ConstructorInjectorStore.this.createConstructor(paramAnonymousTypeLiteral, paramAnonymousErrors);
    }
  };
  private final InjectorImpl injector;

  ConstructorInjectorStore(InjectorImpl paramInjectorImpl)
  {
    this.injector = paramInjectorImpl;
  }

  private <T> ConstructorInjector<T> createConstructor(TypeLiteral<T> paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    try
    {
      InjectionPoint localInjectionPoint = InjectionPoint.forConstructorOf(paramTypeLiteral);
      SingleParameterInjector[] arrayOfSingleParameterInjector = this.injector.getParametersInjectors(localInjectionPoint.getDependencies(), paramErrors);
      MembersInjectorImpl localMembersInjectorImpl = this.injector.membersInjectorStore.get(paramTypeLiteral, paramErrors);
      DefaultConstructionProxyFactory localDefaultConstructionProxyFactory = new DefaultConstructionProxyFactory(localInjectionPoint);
      paramErrors.throwIfNewErrors(i);
      return new ConstructorInjector(localMembersInjectorImpl.getInjectionPoints(), localDefaultConstructionProxyFactory.create(), arrayOfSingleParameterInjector, localMembersInjectorImpl);
    }
    catch (ConfigurationException localConfigurationException)
    {
      paramErrors.merge(localConfigurationException.getErrorMessages());
    }
    throw paramErrors.toException();
  }

  public <T> ConstructorInjector<T> get(TypeLiteral<T> paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    return (ConstructorInjector)this.cache.get(paramTypeLiteral, paramErrors);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstructorInjectorStore
 * JD-Core Version:    0.6.2
 */