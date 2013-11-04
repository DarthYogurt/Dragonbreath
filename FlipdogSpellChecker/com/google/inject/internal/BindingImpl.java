package com.google.inject.internal;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.ElementVisitor;
import com.google.inject.spi.InstanceBinding;

public abstract class BindingImpl<T>
  implements Binding<T>
{
  private final Injector injector;
  private final InternalFactory<? extends T> internalFactory;
  private final Key<T> key;
  private volatile Provider<T> provider;
  private final Scoping scoping;
  private final Object source;

  public BindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject, InternalFactory<? extends T> paramInternalFactory, Scoping paramScoping)
  {
    this.injector = paramInjector;
    this.key = paramKey;
    this.source = paramObject;
    this.internalFactory = paramInternalFactory;
    this.scoping = paramScoping;
  }

  protected BindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping)
  {
    this.internalFactory = null;
    this.injector = null;
    this.source = paramObject;
    this.key = paramKey;
    this.scoping = paramScoping;
  }

  public <V> V acceptScopingVisitor(BindingScopingVisitor<V> paramBindingScopingVisitor)
  {
    return this.scoping.acceptVisitor(paramBindingScopingVisitor);
  }

  public <V> V acceptVisitor(ElementVisitor<V> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public Injector getInjector()
  {
    return this.injector;
  }

  public InternalFactory<? extends T> getInternalFactory()
  {
    return this.internalFactory;
  }

  public Key<T> getKey()
  {
    return this.key;
  }

  public Provider<T> getProvider()
  {
    if (this.provider == null)
    {
      if (this.injector == null)
        throw new UnsupportedOperationException("getProvider() not supported for module bindings");
      this.provider = this.injector.getProvider(this.key);
    }
    return this.provider;
  }

  public Scoping getScoping()
  {
    return this.scoping;
  }

  public Object getSource()
  {
    return this.source;
  }

  public boolean isConstant()
  {
    return this instanceof InstanceBinding;
  }

  public String toString()
  {
    return new ToStringBuilder(Binding.class).add("key", this.key).add("scope", this.scoping).add("source", this.source).toString();
  }

  protected BindingImpl<T> withKey(Key<T> paramKey)
  {
    throw new AssertionError();
  }

  protected BindingImpl<T> withScoping(Scoping paramScoping)
  {
    throw new AssertionError();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.BindingImpl
 * JD-Core Version:    0.6.2
 */