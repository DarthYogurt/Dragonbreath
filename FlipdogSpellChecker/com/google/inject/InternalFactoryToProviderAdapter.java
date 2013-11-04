package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.SourceProvider;
import com.google.inject.spi.Dependency;

class InternalFactoryToProviderAdapter<T>
  implements InternalFactory<T>
{
  private final Initializable<Provider<? extends T>> initializable;
  private final Object source;

  public InternalFactoryToProviderAdapter(Initializable<Provider<? extends T>> paramInitializable)
  {
    this(paramInitializable, SourceProvider.UNKNOWN_SOURCE);
  }

  public InternalFactoryToProviderAdapter(Initializable<Provider<? extends T>> paramInitializable, Object paramObject)
  {
    this.initializable = ((Initializable)Preconditions.checkNotNull(paramInitializable, "provider"));
    this.source = Preconditions.checkNotNull(paramObject, "source");
  }

  public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    throws ErrorsException
  {
    try
    {
      Object localObject = paramErrors.checkForNull(((Provider)this.initializable.get(paramErrors)).get(), this.source, paramDependency);
      return localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw paramErrors.withSource(this.source).errorInProvider(localRuntimeException).toException();
    }
  }

  public String toString()
  {
    return this.initializable.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InternalFactoryToProviderAdapter
 * JD-Core Version:    0.6.2
 */