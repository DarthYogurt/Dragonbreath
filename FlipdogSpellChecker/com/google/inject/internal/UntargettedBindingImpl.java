package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.UntargettedBinding;

public class UntargettedBindingImpl<T> extends BindingImpl<T>
  implements UntargettedBinding<T>
{
  public UntargettedBindingImpl(Injector paramInjector, Key<T> paramKey, Object paramObject)
  {
    super(paramInjector, paramKey, paramObject, new InternalFactory()
    {
      public T get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency<?> paramAnonymousDependency)
      {
        throw new AssertionError();
      }
    }
    , Scoping.UNSCOPED);
  }

  public UntargettedBindingImpl(Object paramObject, Key<T> paramKey, Scoping paramScoping)
  {
    super(paramObject, paramKey, paramScoping);
  }

  public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
  {
    return paramBindingTargetVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    getScoping().applyTo(paramBinder.withSource(getSource()).bind(getKey()));
  }

  public String toString()
  {
    return new ToStringBuilder(UntargettedBinding.class).add("key", getKey()).add("source", getSource()).toString();
  }

  public BindingImpl<T> withKey(Key<T> paramKey)
  {
    return new UntargettedBindingImpl(getSource(), paramKey, getScoping());
  }

  public BindingImpl<T> withScoping(Scoping paramScoping)
  {
    return new UntargettedBindingImpl(getSource(), getKey(), paramScoping);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.UntargettedBindingImpl
 * JD-Core Version:    0.6.2
 */