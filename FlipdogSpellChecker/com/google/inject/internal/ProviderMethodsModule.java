package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.Message;
import com.google.inject.util.Modules;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class ProviderMethodsModule
  implements Module
{
  private final Object delegate;
  private final TypeLiteral<?> typeLiteral;

  private ProviderMethodsModule(Object paramObject)
  {
    this.delegate = Preconditions.checkNotNull(paramObject, "delegate");
    this.typeLiteral = TypeLiteral.get(this.delegate.getClass());
  }

  public static Module forModule(Module paramModule)
  {
    return forObject(paramModule);
  }

  public static Module forObject(Object paramObject)
  {
    if ((paramObject instanceof ProviderMethodsModule))
      return Modules.EMPTY_MODULE;
    return new ProviderMethodsModule(paramObject);
  }

  public void configure(Binder paramBinder)
  {
    try
    {
      Iterator localIterator = getProviderMethods(paramBinder).iterator();
      while (localIterator.hasNext())
        ((ProviderMethod)localIterator.next()).configure(paramBinder);
    }
    finally
    {
    }
  }

  <T> ProviderMethod<T> createProviderMethod(Binder paramBinder, Method paramMethod)
  {
    Binder localBinder = paramBinder.withSource(paramMethod);
    Errors localErrors = new Errors(paramMethod);
    ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = Lists.newArrayList();
    List localList = this.typeLiteral.getParameterTypes(paramMethod);
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    for (int i = 0; i < localList.size(); i++)
    {
      Key localKey2 = getKey(localErrors, (TypeLiteral)localList.get(i), paramMethod, arrayOfAnnotation[i]);
      localArrayList1.add(Dependency.get(localKey2));
      localArrayList2.add(localBinder.getProvider(localKey2));
    }
    Key localKey1 = getKey(localErrors, this.typeLiteral.getReturnType(paramMethod), paramMethod, paramMethod.getAnnotations());
    Class localClass = Annotations.findScopeAnnotation(localErrors, paramMethod.getAnnotations());
    Iterator localIterator = localErrors.getMessages().iterator();
    while (localIterator.hasNext())
      localBinder.addError((Message)localIterator.next());
    return new ProviderMethod(localKey1, paramMethod, this.delegate, ImmutableSet.copyOf(localArrayList1), localArrayList2, localClass);
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof ProviderMethodsModule)) && (((ProviderMethodsModule)paramObject).delegate == this.delegate);
  }

  <T> Key<T> getKey(Errors paramErrors, TypeLiteral<T> paramTypeLiteral, Member paramMember, Annotation[] paramArrayOfAnnotation)
  {
    Annotation localAnnotation = Annotations.findBindingAnnotation(paramErrors, paramMember, paramArrayOfAnnotation);
    if (localAnnotation == null)
      return Key.get(paramTypeLiteral);
    return Key.get(paramTypeLiteral, localAnnotation);
  }

  public List<ProviderMethod<?>> getProviderMethods(Binder paramBinder)
  {
    ArrayList localArrayList = Lists.newArrayList();
    for (Class localClass = this.delegate.getClass(); localClass != Object.class; localClass = localClass.getSuperclass())
      for (Method localMethod : localClass.getDeclaredMethods())
        if (localMethod.isAnnotationPresent(Provides.class))
          localArrayList.add(createProviderMethod(paramBinder, localMethod));
    return localArrayList;
  }

  public int hashCode()
  {
    return this.delegate.hashCode();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ProviderMethodsModule
 * JD-Core Version:    0.6.2
 */