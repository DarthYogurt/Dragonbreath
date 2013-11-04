package com.google.inject.spi;

import com.google.inject.ConfigurationException;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.Annotations;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Lists;
import com.google.inject.internal.MoreTypes;
import com.google.inject.internal.Nullability;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class InjectionPoint
{
  private final ImmutableList<Dependency<?>> dependencies;
  private final Member member;
  private final boolean optional;

  InjectionPoint(TypeLiteral<?> paramTypeLiteral, Constructor<?> paramConstructor)
  {
    this.member = paramConstructor;
    this.optional = false;
    this.dependencies = forMember(paramConstructor, paramTypeLiteral, paramConstructor.getParameterAnnotations());
  }

  InjectionPoint(TypeLiteral<?> paramTypeLiteral, Field paramField)
  {
    this.member = paramField;
    this.optional = ((Inject)paramField.getAnnotation(Inject.class)).optional();
    Annotation[] arrayOfAnnotation = paramField.getAnnotations();
    Errors localErrors = new Errors(paramField);
    try
    {
      Key localKey2 = Annotations.getKey(paramTypeLiteral.getFieldType(paramField), paramField, arrayOfAnnotation, localErrors);
      localKey1 = localKey2;
      localErrors.throwConfigurationExceptionIfErrorsExist();
      this.dependencies = ImmutableList.of(newDependency(localKey1, Nullability.allowsNull(arrayOfAnnotation), -1));
      return;
    }
    catch (ErrorsException localErrorsException)
    {
      while (true)
      {
        localErrors.merge(localErrorsException.getErrors());
        Key localKey1 = null;
      }
    }
  }

  InjectionPoint(TypeLiteral<?> paramTypeLiteral, Method paramMethod)
  {
    this.member = paramMethod;
    this.optional = ((Inject)paramMethod.getAnnotation(Inject.class)).optional();
    this.dependencies = forMember(paramMethod, paramTypeLiteral, paramMethod.getParameterAnnotations());
  }

  private InjectionPoint(Member paramMember, ImmutableList<Dependency<?>> paramImmutableList, boolean paramBoolean)
  {
    this.member = paramMember;
    this.dependencies = paramImmutableList;
    this.optional = paramBoolean;
  }

  private static <M extends Member,  extends AnnotatedElement> void addInjectionPoints(TypeLiteral<?> paramTypeLiteral, Factory<M> paramFactory, boolean paramBoolean, Collection<InjectionPoint> paramCollection, Errors paramErrors)
  {
    if (paramTypeLiteral.getType() == Object.class)
      return;
    addInjectionPoints(paramTypeLiteral.getSupertype(paramTypeLiteral.getRawType().getSuperclass()), paramFactory, paramBoolean, paramCollection, paramErrors);
    addInjectorsForMembers(paramTypeLiteral, paramFactory, paramBoolean, paramCollection, paramErrors);
  }

  private static <M extends Member,  extends AnnotatedElement> void addInjectorsForMembers(TypeLiteral<?> paramTypeLiteral, Factory<M> paramFactory, boolean paramBoolean, Collection<InjectionPoint> paramCollection, Errors paramErrors)
  {
    Member[] arrayOfMember = paramFactory.getMembers(MoreTypes.getRawType(paramTypeLiteral.getType()));
    int i = arrayOfMember.length;
    int j = 0;
    if (j < i)
    {
      Member localMember = arrayOfMember[j];
      if (isStatic(localMember) != paramBoolean);
      while (true)
      {
        j++;
        break;
        Inject localInject = (Inject)((AnnotatedElement)localMember).getAnnotation(Inject.class);
        if (localInject != null)
        {
          try
          {
            paramCollection.add(paramFactory.create(paramTypeLiteral, localMember, paramErrors));
          }
          catch (ConfigurationException localConfigurationException)
          {
          }
          if (!localInject.optional())
            paramErrors.merge(localConfigurationException.getErrorMessages());
        }
      }
    }
  }

  private static void checkForMisplacedBindingAnnotations(Member paramMember, Errors paramErrors)
  {
    Annotation localAnnotation = Annotations.findBindingAnnotation(paramErrors, paramMember, ((AnnotatedElement)paramMember).getAnnotations());
    if (localAnnotation == null);
    while (true)
    {
      return;
      if ((paramMember instanceof Method));
      try
      {
        Field localField = paramMember.getDeclaringClass().getDeclaredField(paramMember.getName());
        if (localField != null)
          continue;
        label49: paramErrors.misplacedBindingAnnotation(paramMember, localAnnotation);
        return;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        break label49;
      }
    }
  }

  public static InjectionPoint forConstructorOf(TypeLiteral<?> paramTypeLiteral)
  {
    Class localClass = MoreTypes.getRawType(paramTypeLiteral.getType());
    Errors localErrors = new Errors(localClass);
    Object localObject = null;
    for (Constructor localConstructor2 : localClass.getDeclaredConstructors())
    {
      Inject localInject = (Inject)localConstructor2.getAnnotation(Inject.class);
      if (localInject != null)
      {
        if (localInject.optional())
          localErrors.optionalConstructor(localConstructor2);
        if (localObject != null)
          localErrors.tooManyConstructors(localClass);
        localObject = localConstructor2;
        checkForMisplacedBindingAnnotations(localObject, localErrors);
      }
    }
    localErrors.throwConfigurationExceptionIfErrorsExist();
    if (localObject != null)
      return new InjectionPoint(paramTypeLiteral, localObject);
    Constructor localConstructor1;
    try
    {
      localConstructor1 = localClass.getDeclaredConstructor(new Class[0]);
      if ((Modifier.isPrivate(localConstructor1.getModifiers())) && (!Modifier.isPrivate(localClass.getModifiers())))
      {
        localErrors.missingConstructor(localClass);
        throw new ConfigurationException(localErrors.getMessages());
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localErrors.missingConstructor(localClass);
      throw new ConfigurationException(localErrors.getMessages());
    }
    checkForMisplacedBindingAnnotations(localConstructor1, localErrors);
    InjectionPoint localInjectionPoint = new InjectionPoint(paramTypeLiteral, localConstructor1);
    return localInjectionPoint;
  }

  public static InjectionPoint forConstructorOf(Class<?> paramClass)
  {
    return forConstructorOf(TypeLiteral.get(paramClass));
  }

  public static Set<InjectionPoint> forInstanceMethodsAndFields(TypeLiteral<?> paramTypeLiteral)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Errors localErrors = new Errors();
    addInjectionPoints(paramTypeLiteral, Factory.FIELDS, false, localArrayList, localErrors);
    addInjectionPoints(paramTypeLiteral, Factory.METHODS, false, localArrayList, localErrors);
    ImmutableSet localImmutableSet = ImmutableSet.copyOf(localArrayList);
    if (localErrors.hasErrors())
      throw new ConfigurationException(localErrors.getMessages()).withPartialValue(localImmutableSet);
    return localImmutableSet;
  }

  public static Set<InjectionPoint> forInstanceMethodsAndFields(Class<?> paramClass)
  {
    return forInstanceMethodsAndFields(TypeLiteral.get(paramClass));
  }

  private ImmutableList<Dependency<?>> forMember(Member paramMember, TypeLiteral<?> paramTypeLiteral, Annotation[][] paramArrayOfAnnotation)
  {
    Errors localErrors = new Errors(paramMember);
    Iterator localIterator1 = Arrays.asList(paramArrayOfAnnotation).iterator();
    ArrayList localArrayList = Lists.newArrayList();
    int i = 0;
    Iterator localIterator2 = paramTypeLiteral.getParameterTypes(paramMember).iterator();
    while (localIterator2.hasNext())
    {
      TypeLiteral localTypeLiteral = (TypeLiteral)localIterator2.next();
      try
      {
        Annotation[] arrayOfAnnotation = (Annotation[])localIterator1.next();
        localArrayList.add(newDependency(Annotations.getKey(localTypeLiteral, paramMember, arrayOfAnnotation, localErrors), Nullability.allowsNull(arrayOfAnnotation), i));
        i++;
      }
      catch (ErrorsException localErrorsException)
      {
        localErrors.merge(localErrorsException.getErrors());
      }
    }
    localErrors.throwConfigurationExceptionIfErrorsExist();
    return ImmutableList.copyOf(localArrayList);
  }

  public static Set<InjectionPoint> forStaticMethodsAndFields(TypeLiteral paramTypeLiteral)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Errors localErrors = new Errors();
    addInjectionPoints(paramTypeLiteral, Factory.FIELDS, true, localArrayList, localErrors);
    addInjectionPoints(paramTypeLiteral, Factory.METHODS, true, localArrayList, localErrors);
    ImmutableSet localImmutableSet = ImmutableSet.copyOf(localArrayList);
    if (localErrors.hasErrors())
      throw new ConfigurationException(localErrors.getMessages()).withPartialValue(localImmutableSet);
    return localImmutableSet;
  }

  public static Set<InjectionPoint> forStaticMethodsAndFields(Class<?> paramClass)
  {
    return forStaticMethodsAndFields(TypeLiteral.get(paramClass));
  }

  private static boolean isStatic(Member paramMember)
  {
    return Modifier.isStatic(paramMember.getModifiers());
  }

  private <T> Dependency<T> newDependency(Key<T> paramKey, boolean paramBoolean, int paramInt)
  {
    return new Dependency(this, paramKey, paramBoolean, paramInt);
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof InjectionPoint)) && (this.member.equals(((InjectionPoint)paramObject).member));
  }

  public List<Dependency<?>> getDependencies()
  {
    return this.dependencies;
  }

  public Member getMember()
  {
    return this.member;
  }

  public int hashCode()
  {
    return this.member.hashCode();
  }

  public boolean isOptional()
  {
    return this.optional;
  }

  public String toString()
  {
    return MoreTypes.toString(this.member);
  }

  private static abstract interface Factory<M extends Member,  extends AnnotatedElement>
  {
    public static final Factory<Field> FIELDS = new Factory()
    {
      public InjectionPoint create(TypeLiteral<?> paramAnonymousTypeLiteral, Field paramAnonymousField, Errors paramAnonymousErrors)
      {
        return new InjectionPoint(paramAnonymousTypeLiteral, paramAnonymousField);
      }

      public Field[] getMembers(Class<?> paramAnonymousClass)
      {
        return paramAnonymousClass.getDeclaredFields();
      }
    };
    public static final Factory<Method> METHODS = new Factory()
    {
      public InjectionPoint create(TypeLiteral<?> paramAnonymousTypeLiteral, Method paramAnonymousMethod, Errors paramAnonymousErrors)
      {
        InjectionPoint.checkForMisplacedBindingAnnotations(paramAnonymousMethod, paramAnonymousErrors);
        return new InjectionPoint(paramAnonymousTypeLiteral, paramAnonymousMethod);
      }

      public Method[] getMembers(Class<?> paramAnonymousClass)
      {
        return paramAnonymousClass.getDeclaredMethods();
      }
    };

    public abstract InjectionPoint create(TypeLiteral<?> paramTypeLiteral, M paramM, Errors paramErrors);

    public abstract M[] getMembers(Class<?> paramClass);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.InjectionPoint
 * JD-Core Version:    0.6.2
 */