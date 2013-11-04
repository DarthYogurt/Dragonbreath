package com.google.inject;

import com.google.inject.internal.ConstructionContext;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.InternalContext;
import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.InvocationTargetException;

class ConstructorInjector<T>
{
  private final ConstructionProxy<T> constructionProxy;
  private final ImmutableSet<InjectionPoint> injectableMembers;
  private final MembersInjectorImpl<T> membersInjector;
  private final SingleParameterInjector<?>[] parameterInjectors;

  ConstructorInjector(ImmutableSet<InjectionPoint> paramImmutableSet, ConstructionProxy<T> paramConstructionProxy, SingleParameterInjector<?>[] paramArrayOfSingleParameterInjector, MembersInjectorImpl<T> paramMembersInjectorImpl)
    throws ErrorsException
  {
    this.injectableMembers = paramImmutableSet;
    this.constructionProxy = paramConstructionProxy;
    this.parameterInjectors = paramArrayOfSingleParameterInjector;
    this.membersInjector = paramMembersInjectorImpl;
  }

  Object construct(Errors paramErrors, InternalContext paramInternalContext, Class<?> paramClass)
    throws ErrorsException
  {
    ConstructionContext localConstructionContext = paramInternalContext.getConstructionContext(this);
    Object localObject1;
    if (localConstructionContext.isConstructing())
      localObject1 = localConstructionContext.createProxy(paramErrors, paramClass);
    do
    {
      return localObject1;
      localObject1 = localConstructionContext.getCurrentReference();
    }
    while (localObject1 != null);
    while (true)
    {
      try
      {
        localConstructionContext.startConstruction();
        try
        {
          Object[] arrayOfObject = SingleParameterInjector.getAll(paramErrors, paramInternalContext, this.parameterInjectors);
          Object localObject5 = this.constructionProxy.newInstance(arrayOfObject);
          localConstructionContext.setProxyDelegates(localObject5);
          localConstructionContext.finishConstruction();
          localConstructionContext.setCurrentReference(localObject5);
          this.membersInjector.injectMembers(localObject5, paramErrors, paramInternalContext);
          this.membersInjector.notifyListeners(localObject5, paramErrors);
          return localObject5;
        }
        finally
        {
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        if (localInvocationTargetException.getCause() != null)
        {
          localObject3 = localInvocationTargetException.getCause();
          throw paramErrors.withSource(this.constructionProxy.getInjectionPoint()).errorInjectingConstructor((Throwable)localObject3).toException();
        }
      }
      finally
      {
        localConstructionContext.removeCurrentReference();
      }
      Object localObject3 = localInvocationTargetException;
    }
  }

  ConstructionProxy<T> getConstructionProxy()
  {
    return this.constructionProxy;
  }

  public ImmutableSet<InjectionPoint> getInjectableMembers()
  {
    return this.injectableMembers;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstructorInjector
 * JD-Core Version:    0.6.2
 */