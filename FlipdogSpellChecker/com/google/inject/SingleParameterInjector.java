package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.spi.Dependency;

class SingleParameterInjector<T>
{
  private static final Object[] NO_ARGUMENTS = new Object[0];
  private final Dependency<T> dependency;
  private final InternalFactory<? extends T> factory;

  SingleParameterInjector(Dependency<T> paramDependency, InternalFactory<? extends T> paramInternalFactory)
  {
    this.dependency = paramDependency;
    this.factory = paramInternalFactory;
  }

  static Object[] getAll(Errors paramErrors, InternalContext paramInternalContext, SingleParameterInjector<?>[] paramArrayOfSingleParameterInjector)
    throws ErrorsException
  {
    if (paramArrayOfSingleParameterInjector == null)
      return NO_ARGUMENTS;
    int i = paramErrors.size();
    int j = paramArrayOfSingleParameterInjector.length;
    Object[] arrayOfObject = new Object[j];
    int k = 0;
    while (true)
      if (k < j)
      {
        SingleParameterInjector<?> localSingleParameterInjector = paramArrayOfSingleParameterInjector[k];
        try
        {
          arrayOfObject[k] = localSingleParameterInjector.inject(paramErrors, paramInternalContext);
          k++;
        }
        catch (ErrorsException localErrorsException)
        {
          while (true)
            paramErrors.merge(localErrorsException.getErrors());
        }
      }
    paramErrors.throwIfNewErrors(i);
    return arrayOfObject;
  }

  private T inject(Errors paramErrors, InternalContext paramInternalContext)
    throws ErrorsException
  {
    paramInternalContext.setDependency(this.dependency);
    try
    {
      Object localObject2 = this.factory.get(paramErrors.withSource(this.dependency), paramInternalContext, this.dependency);
      return localObject2;
    }
    finally
    {
      paramInternalContext.setDependency(null);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.SingleParameterInjector
 * JD-Core Version:    0.6.2
 */