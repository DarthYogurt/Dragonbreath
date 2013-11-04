package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.Scoping;

public class Scopes
{
  public static final Scope NO_SCOPE = new Scope()
  {
    public <T> Provider<T> scope(Key<T> paramAnonymousKey, Provider<T> paramAnonymousProvider)
    {
      return paramAnonymousProvider;
    }

    public String toString()
    {
      return "Scopes.NO_SCOPE";
    }
  };
  public static final Scope SINGLETON = new Scope()
  {
    public <T> Provider<T> scope(Key<T> paramAnonymousKey, final Provider<T> paramAnonymousProvider)
    {
      return new Provider()
      {
        private volatile T instance;

        public T get()
        {
          if (this.instance == null);
          try
          {
            if (this.instance == null)
              this.instance = paramAnonymousProvider.get();
            return this.instance;
          }
          finally
          {
          }
        }

        public String toString()
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramAnonymousProvider;
          arrayOfObject[1] = Scopes.SINGLETON;
          return String.format("%s[%s]", arrayOfObject);
        }
      };
    }

    public String toString()
    {
      return "Scopes.SINGLETON";
    }
  };

  static Scoping makeInjectable(Scoping paramScoping, InjectorImpl paramInjectorImpl, Errors paramErrors)
  {
    Class localClass = paramScoping.getScopeAnnotation();
    if (localClass == null)
      return paramScoping;
    Scope localScope = paramInjectorImpl.state.getScope(localClass);
    if (localScope != null)
      return Scoping.forInstance(localScope);
    paramErrors.scopeNotFound(localClass);
    return Scoping.UNSCOPED;
  }

  static <T> InternalFactory<? extends T> scope(Key<T> paramKey, InjectorImpl paramInjectorImpl, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping)
  {
    if (paramScoping.isNoScope())
      return paramInternalFactory;
    return new InternalFactoryToProviderAdapter(Initializables.of(paramScoping.getScopeInstance().scope(paramKey, new ProviderToInternalFactoryAdapter(paramInjectorImpl, paramInternalFactory))));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Scopes
 * JD-Core Version:    0.6.2
 */