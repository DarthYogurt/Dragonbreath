package com.google.inject.internal;

import com.google.inject.BindingAnnotation;
import com.google.inject.Key;
import com.google.inject.ScopeAnnotation;
import com.google.inject.TypeLiteral;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Member;

public class Annotations
{
  public static void checkForMisplacedScopeAnnotations(Class<?> paramClass, Object paramObject, Errors paramErrors)
  {
    if (Classes.isConcrete(paramClass));
    Class localClass;
    do
    {
      return;
      localClass = findScopeAnnotation(paramErrors, paramClass);
    }
    while (localClass == null);
    paramErrors.withSource(paramClass).scopeAnnotationOnAbstractType(localClass, paramClass, paramObject);
  }

  public static Annotation findBindingAnnotation(Errors paramErrors, Member paramMember, Annotation[] paramArrayOfAnnotation)
  {
    Object localObject = null;
    int i = paramArrayOfAnnotation.length;
    int j = 0;
    if (j < i)
    {
      Annotation localAnnotation = paramArrayOfAnnotation[j];
      if (localAnnotation.annotationType().isAnnotationPresent(BindingAnnotation.class))
      {
        if (localObject == null)
          break label66;
        paramErrors.duplicateBindingAnnotations(paramMember, localObject.annotationType(), localAnnotation.annotationType());
      }
      while (true)
      {
        j++;
        break;
        label66: localObject = localAnnotation;
      }
    }
    return localObject;
  }

  public static Class<? extends Annotation> findScopeAnnotation(Errors paramErrors, Class<?> paramClass)
  {
    return findScopeAnnotation(paramErrors, paramClass.getAnnotations());
  }

  public static Class<? extends Annotation> findScopeAnnotation(Errors paramErrors, Annotation[] paramArrayOfAnnotation)
  {
    Class localClass = null;
    int i = paramArrayOfAnnotation.length;
    int j = 0;
    if (j < i)
    {
      Annotation localAnnotation = paramArrayOfAnnotation[j];
      if (localAnnotation.annotationType().isAnnotationPresent(ScopeAnnotation.class))
      {
        if (localClass == null)
          break label58;
        paramErrors.duplicateScopeAnnotations(localClass, localAnnotation.annotationType());
      }
      while (true)
      {
        j++;
        break;
        label58: localClass = localAnnotation.annotationType();
      }
    }
    return localClass;
  }

  public static Key<?> getKey(TypeLiteral<?> paramTypeLiteral, Member paramMember, Annotation[] paramArrayOfAnnotation, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    Annotation localAnnotation = findBindingAnnotation(paramErrors, paramMember, paramArrayOfAnnotation);
    paramErrors.throwIfNewErrors(i);
    if (localAnnotation == null)
      return Key.get(paramTypeLiteral);
    return Key.get(paramTypeLiteral, localAnnotation);
  }

  public static boolean isRetainedAtRuntime(Class<? extends Annotation> paramClass)
  {
    Retention localRetention = (Retention)paramClass.getAnnotation(Retention.class);
    return (localRetention != null) && (localRetention.value() == RetentionPolicy.RUNTIME);
  }

  public static boolean isScopeAnnotation(Class<? extends Annotation> paramClass)
  {
    return paramClass.isAnnotationPresent(ScopeAnnotation.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Annotations
 * JD-Core Version:    0.6.2
 */