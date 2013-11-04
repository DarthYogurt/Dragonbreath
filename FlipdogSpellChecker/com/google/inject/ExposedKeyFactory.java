package com.google.inject;

import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.PrivateElements;

class ExposedKeyFactory<T>
  implements InternalFactory<T>, BindingProcessor.CreationListener
{
  private BindingImpl<T> delegate;
  private final Key<T> key;
  private final PrivateElements privateElements;

  public ExposedKeyFactory(Key<T> paramKey, PrivateElements paramPrivateElements)
  {
    this.key = paramKey;
    this.privateElements = paramPrivateElements;
  }

  public T get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    throws ErrorsException
  {
    return this.delegate.getInternalFactory().get(paramErrors, paramInternalContext, paramDependency);
  }

  public void notify(Errors paramErrors)
  {
    BindingImpl localBindingImpl = ((InjectorImpl)this.privateElements.getInjector()).state.getExplicitBinding(this.key);
    if (localBindingImpl.getInternalFactory() == this)
    {
      paramErrors.withSource(localBindingImpl.getSource()).exposedButNotBound(this.key);
      return;
    }
    this.delegate = localBindingImpl;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ExposedKeyFactory
 * JD-Core Version:    0.6.2
 */