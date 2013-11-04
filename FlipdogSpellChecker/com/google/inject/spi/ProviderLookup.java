package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.internal.Preconditions;

public final class ProviderLookup<T>
  implements Element
{
  private Provider<T> delegate;
  private final Key<T> key;
  private final Object source;

  public ProviderLookup(Object paramObject, Key<T> paramKey)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.key = ((Key)Preconditions.checkNotNull(paramKey, "key"));
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    initializeDelegate(paramBinder.withSource(getSource()).getProvider(this.key));
  }

  public Provider<T> getDelegate()
  {
    return this.delegate;
  }

  public Key<T> getKey()
  {
    return this.key;
  }

  public Provider<T> getProvider()
  {
    return new Provider()
    {
      public T get()
      {
        if (ProviderLookup.this.delegate != null);
        for (boolean bool = true; ; bool = false)
        {
          Preconditions.checkState(bool, "This Provider cannot be used until the Injector has been created.");
          return ProviderLookup.this.delegate.get();
        }
      }

      public String toString()
      {
        return "Provider<" + ProviderLookup.this.key.getTypeLiteral() + ">";
      }
    };
  }

  public Object getSource()
  {
    return this.source;
  }

  public void initializeDelegate(Provider<T> paramProvider)
  {
    if (this.delegate == null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "delegate already initialized");
      this.delegate = ((Provider)Preconditions.checkNotNull(paramProvider, "delegate"));
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ProviderLookup
 * JD-Core Version:    0.6.2
 */