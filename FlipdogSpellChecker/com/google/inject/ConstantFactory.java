package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.ToStringBuilder;
import com.google.inject.spi.Dependency;

class ConstantFactory<T>
  implements InternalFactory<T>
{
  private final Initializable<T> initializable;

  public ConstantFactory(Initializable<T> paramInitializable)
  {
    this.initializable = paramInitializable;
  }

  public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency paramDependency)
    throws ErrorsException
  {
    return this.initializable.get(paramErrors);
  }

  public String toString()
  {
    return new ToStringBuilder(ConstantFactory.class).add("value", this.initializable).toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstantFactory
 * JD-Core Version:    0.6.2
 */