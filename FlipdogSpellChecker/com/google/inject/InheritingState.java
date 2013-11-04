package com.google.inject;

import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Maps;
import com.google.inject.internal.MatcherAndConverter;
import com.google.inject.internal.Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeListenerBinding;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class InheritingState
  implements State
{
  private final WeakKeySet blacklistedKeys = new WeakKeySet();
  private final List<MatcherAndConverter> converters = Lists.newArrayList();
  private final Map<Key<?>, Binding<?>> explicitBindings = Collections.unmodifiableMap(this.explicitBindingsMutable);
  private final Map<Key<?>, Binding<?>> explicitBindingsMutable = Maps.newLinkedHashMap();
  private final List<TypeListenerBinding> listenerBindings = Lists.newArrayList();
  private final Object lock;
  private final State parent;
  private final Map<Class<? extends Annotation>, Scope> scopes = Maps.newHashMap();

  InheritingState(State paramState)
  {
    this.parent = ((State)Preconditions.checkNotNull(paramState, "parent"));
    if (paramState == State.NONE);
    for (Object localObject = this; ; localObject = paramState.lock())
    {
      this.lock = localObject;
      return;
    }
  }

  public void addConverter(MatcherAndConverter paramMatcherAndConverter)
  {
    this.converters.add(paramMatcherAndConverter);
  }

  public void addTypeListener(TypeListenerBinding paramTypeListenerBinding)
  {
    this.listenerBindings.add(paramTypeListenerBinding);
  }

  public void blacklist(Key<?> paramKey)
  {
    this.parent.blacklist(paramKey);
    this.blacklistedKeys.add(paramKey);
  }

  public MatcherAndConverter getConverter(String paramString, TypeLiteral<?> paramTypeLiteral, Errors paramErrors, Object paramObject)
  {
    Object localObject1 = null;
    for (Object localObject2 = this; localObject2 != State.NONE; localObject2 = ((State)localObject2).parent())
    {
      Iterator localIterator = ((State)localObject2).getConvertersThisLevel().iterator();
      while (localIterator.hasNext())
      {
        MatcherAndConverter localMatcherAndConverter = (MatcherAndConverter)localIterator.next();
        if (localMatcherAndConverter.getTypeMatcher().matches(paramTypeLiteral))
        {
          if (localObject1 != null)
            paramErrors.ambiguousTypeConversion(paramString, paramObject, paramTypeLiteral, (MatcherAndConverter)localObject1, localMatcherAndConverter);
          localObject1 = localMatcherAndConverter;
        }
      }
    }
    return localObject1;
  }

  public Iterable<MatcherAndConverter> getConvertersThisLevel()
  {
    return this.converters;
  }

  public <T> BindingImpl<T> getExplicitBinding(Key<T> paramKey)
  {
    Binding localBinding = (Binding)this.explicitBindings.get(paramKey);
    if (localBinding != null)
      return (BindingImpl)localBinding;
    return this.parent.getExplicitBinding(paramKey);
  }

  public Map<Key<?>, Binding<?>> getExplicitBindingsThisLevel()
  {
    return this.explicitBindings;
  }

  public Scope getScope(Class<? extends Annotation> paramClass)
  {
    Scope localScope = (Scope)this.scopes.get(paramClass);
    if (localScope != null)
      return localScope;
    return this.parent.getScope(paramClass);
  }

  public List<TypeListenerBinding> getTypeListenerBindings()
  {
    List localList = this.parent.getTypeListenerBindings();
    ArrayList localArrayList = new ArrayList(1 + localList.size());
    localArrayList.addAll(localList);
    localArrayList.addAll(this.listenerBindings);
    return localArrayList;
  }

  public boolean isBlacklisted(Key<?> paramKey)
  {
    return this.blacklistedKeys.contains(paramKey);
  }

  public Object lock()
  {
    return this.lock;
  }

  public State parent()
  {
    return this.parent;
  }

  public void putAnnotation(Class<? extends Annotation> paramClass, Scope paramScope)
  {
    this.scopes.put(paramClass, paramScope);
  }

  public void putBinding(Key<?> paramKey, BindingImpl<?> paramBindingImpl)
  {
    this.explicitBindingsMutable.put(paramKey, paramBindingImpl);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InheritingState
 * JD-Core Version:    0.6.2
 */