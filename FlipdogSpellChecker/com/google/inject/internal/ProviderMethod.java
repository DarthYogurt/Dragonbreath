package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Exposed;
import com.google.inject.Key;
import com.google.inject.PrivateBinder;
import com.google.inject.Provider;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.ProviderWithDependencies;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class ProviderMethod<T>
  implements ProviderWithDependencies<T>
{
  private final ImmutableSet<Dependency<?>> dependencies;
  private final boolean exposed;
  private final Object instance;
  private final Key<T> key;
  private final Method method;
  private final List<Provider<?>> parameterProviders;
  private final Class<? extends Annotation> scopeAnnotation;

  ProviderMethod(Key<T> paramKey, Method paramMethod, Object paramObject, ImmutableSet<Dependency<?>> paramImmutableSet, List<Provider<?>> paramList, Class<? extends Annotation> paramClass)
  {
    this.key = paramKey;
    this.scopeAnnotation = paramClass;
    this.instance = paramObject;
    this.dependencies = paramImmutableSet;
    this.method = paramMethod;
    this.parameterProviders = paramList;
    this.exposed = paramMethod.isAnnotationPresent(Exposed.class);
    paramMethod.setAccessible(true);
  }

  public void configure(Binder paramBinder)
  {
    Binder localBinder = paramBinder.withSource(this.method);
    if (this.scopeAnnotation != null)
      localBinder.bind(this.key).toProvider(this).in(this.scopeAnnotation);
    while (true)
    {
      if (this.exposed)
        ((PrivateBinder)localBinder).expose(this.key);
      return;
      localBinder.bind(this.key).toProvider(this);
    }
  }

  public T get()
  {
    Object[] arrayOfObject = new Object[this.parameterProviders.size()];
    for (int i = 0; i < arrayOfObject.length; i++)
      arrayOfObject[i] = ((Provider)this.parameterProviders.get(i)).get();
    try
    {
      Object localObject = this.method.invoke(this.instance, arrayOfObject);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new RuntimeException(localInvocationTargetException);
    }
  }

  public Set<Dependency<?>> getDependencies()
  {
    return this.dependencies;
  }

  public Object getInstance()
  {
    return this.instance;
  }

  public Key<T> getKey()
  {
    return this.key;
  }

  public Method getMethod()
  {
    return this.method;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ProviderMethod
 * JD-Core Version:    0.6.2
 */