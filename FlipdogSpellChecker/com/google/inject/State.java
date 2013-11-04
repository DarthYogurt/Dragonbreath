package com.google.inject;

import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.MatcherAndConverter;
import com.google.inject.spi.TypeListenerBinding;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

abstract interface State
{
  public static final State NONE = new State()
  {
    public void addConverter(MatcherAndConverter paramAnonymousMatcherAndConverter)
    {
      throw new UnsupportedOperationException();
    }

    public void addTypeListener(TypeListenerBinding paramAnonymousTypeListenerBinding)
    {
      throw new UnsupportedOperationException();
    }

    public void blacklist(Key<?> paramAnonymousKey)
    {
    }

    public MatcherAndConverter getConverter(String paramAnonymousString, TypeLiteral<?> paramAnonymousTypeLiteral, Errors paramAnonymousErrors, Object paramAnonymousObject)
    {
      throw new UnsupportedOperationException();
    }

    public Iterable<MatcherAndConverter> getConvertersThisLevel()
    {
      return ImmutableSet.of();
    }

    public <T> BindingImpl<T> getExplicitBinding(Key<T> paramAnonymousKey)
    {
      return null;
    }

    public Map<Key<?>, Binding<?>> getExplicitBindingsThisLevel()
    {
      throw new UnsupportedOperationException();
    }

    public Scope getScope(Class<? extends Annotation> paramAnonymousClass)
    {
      return null;
    }

    public List<TypeListenerBinding> getTypeListenerBindings()
    {
      return ImmutableList.of();
    }

    public boolean isBlacklisted(Key<?> paramAnonymousKey)
    {
      return true;
    }

    public Object lock()
    {
      throw new UnsupportedOperationException();
    }

    public State parent()
    {
      throw new UnsupportedOperationException();
    }

    public void putAnnotation(Class<? extends Annotation> paramAnonymousClass, Scope paramAnonymousScope)
    {
      throw new UnsupportedOperationException();
    }

    public void putBinding(Key<?> paramAnonymousKey, BindingImpl<?> paramAnonymousBindingImpl)
    {
      throw new UnsupportedOperationException();
    }
  };

  public abstract void addConverter(MatcherAndConverter paramMatcherAndConverter);

  public abstract void addTypeListener(TypeListenerBinding paramTypeListenerBinding);

  public abstract void blacklist(Key<?> paramKey);

  public abstract MatcherAndConverter getConverter(String paramString, TypeLiteral<?> paramTypeLiteral, Errors paramErrors, Object paramObject);

  public abstract Iterable<MatcherAndConverter> getConvertersThisLevel();

  public abstract <T> BindingImpl<T> getExplicitBinding(Key<T> paramKey);

  public abstract Map<Key<?>, Binding<?>> getExplicitBindingsThisLevel();

  public abstract Scope getScope(Class<? extends Annotation> paramClass);

  public abstract List<TypeListenerBinding> getTypeListenerBindings();

  public abstract boolean isBlacklisted(Key<?> paramKey);

  public abstract Object lock();

  public abstract State parent();

  public abstract void putAnnotation(Class<? extends Annotation> paramClass, Scope paramScope);

  public abstract void putBinding(Key<?> paramKey, BindingImpl<?> paramBindingImpl);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.State
 * JD-Core Version:    0.6.2
 */