package com.google.inject.matcher;

import com.google.inject.internal.Preconditions;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

public class Matchers
{
  private static final Matcher<Object> ANY = new Any(null);

  public static Matcher<AnnotatedElement> annotatedWith(Class<? extends Annotation> paramClass)
  {
    return new AnnotatedWithType(paramClass);
  }

  public static Matcher<AnnotatedElement> annotatedWith(Annotation paramAnnotation)
  {
    return new AnnotatedWith(paramAnnotation);
  }

  public static Matcher<Object> any()
  {
    return ANY;
  }

  private static void checkForRuntimeRetention(Class<? extends Annotation> paramClass)
  {
    Retention localRetention = (Retention)paramClass.getAnnotation(Retention.class);
    if ((localRetention != null) && (localRetention.value() == RetentionPolicy.RUNTIME));
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "Annotation " + paramClass.getSimpleName() + " is missing RUNTIME retention");
      return;
    }
  }

  public static Matcher<Object> identicalTo(Object paramObject)
  {
    return new IdenticalTo(paramObject);
  }

  public static Matcher<Class> inPackage(Package paramPackage)
  {
    return new InPackage(paramPackage);
  }

  public static Matcher<Class> inSubpackage(String paramString)
  {
    return new InSubpackage(paramString);
  }

  public static <T> Matcher<T> not(Matcher<? super T> paramMatcher)
  {
    return new Not(paramMatcher, null);
  }

  public static Matcher<Object> only(Object paramObject)
  {
    return new Only(paramObject);
  }

  public static Matcher<Method> returns(Matcher<? super Class<?>> paramMatcher)
  {
    return new Returns(paramMatcher);
  }

  public static Matcher<Class> subclassesOf(Class<?> paramClass)
  {
    return new SubclassesOf(paramClass);
  }

  private static class AnnotatedWith extends AbstractMatcher<AnnotatedElement>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Annotation annotation;

    public AnnotatedWith(Annotation paramAnnotation)
    {
      this.annotation = ((Annotation)Preconditions.checkNotNull(paramAnnotation, "annotation"));
      Matchers.checkForRuntimeRetention(paramAnnotation.annotationType());
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AnnotatedWith)) && (((AnnotatedWith)paramObject).annotation.equals(this.annotation));
    }

    public int hashCode()
    {
      return 37 * this.annotation.hashCode();
    }

    public boolean matches(AnnotatedElement paramAnnotatedElement)
    {
      Annotation localAnnotation = paramAnnotatedElement.getAnnotation(this.annotation.annotationType());
      return (localAnnotation != null) && (this.annotation.equals(localAnnotation));
    }

    public String toString()
    {
      return "annotatedWith(" + this.annotation + ")";
    }
  }

  private static class AnnotatedWithType extends AbstractMatcher<AnnotatedElement>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Class<? extends Annotation> annotationType;

    public AnnotatedWithType(Class<? extends Annotation> paramClass)
    {
      this.annotationType = ((Class)Preconditions.checkNotNull(paramClass, "annotation type"));
      Matchers.checkForRuntimeRetention(paramClass);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AnnotatedWithType)) && (((AnnotatedWithType)paramObject).annotationType.equals(this.annotationType));
    }

    public int hashCode()
    {
      return 37 * this.annotationType.hashCode();
    }

    public boolean matches(AnnotatedElement paramAnnotatedElement)
    {
      return paramAnnotatedElement.getAnnotation(this.annotationType) != null;
    }

    public String toString()
    {
      return "annotatedWith(" + this.annotationType.getSimpleName() + ".class)";
    }
  }

  private static class Any extends AbstractMatcher<Object>
    implements Serializable
  {
    private static final long serialVersionUID;

    public boolean matches(Object paramObject)
    {
      return true;
    }

    public Object readResolve()
    {
      return Matchers.any();
    }

    public String toString()
    {
      return "any()";
    }
  }

  private static class IdenticalTo extends AbstractMatcher<Object>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Object value;

    public IdenticalTo(Object paramObject)
    {
      this.value = Preconditions.checkNotNull(paramObject, "value");
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof IdenticalTo)) && (((IdenticalTo)paramObject).value == this.value);
    }

    public int hashCode()
    {
      return 37 * System.identityHashCode(this.value);
    }

    public boolean matches(Object paramObject)
    {
      return this.value == paramObject;
    }

    public String toString()
    {
      return "identicalTo(" + this.value + ")";
    }
  }

  private static class InPackage extends AbstractMatcher<Class>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final String packageName;
    private final transient Package targetPackage;

    public InPackage(Package paramPackage)
    {
      this.targetPackage = ((Package)Preconditions.checkNotNull(paramPackage, "package"));
      this.packageName = paramPackage.getName();
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof InPackage)) && (((InPackage)paramObject).targetPackage.equals(this.targetPackage));
    }

    public int hashCode()
    {
      return 37 * this.targetPackage.hashCode();
    }

    public boolean matches(Class paramClass)
    {
      return paramClass.getPackage().equals(this.targetPackage);
    }

    public Object readResolve()
    {
      return Matchers.inPackage(Package.getPackage(this.packageName));
    }

    public String toString()
    {
      return "inPackage(" + this.targetPackage.getName() + ")";
    }
  }

  private static class InSubpackage extends AbstractMatcher<Class>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final String targetPackageName;

    public InSubpackage(String paramString)
    {
      this.targetPackageName = paramString;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof InSubpackage)) && (((InSubpackage)paramObject).targetPackageName.equals(this.targetPackageName));
    }

    public int hashCode()
    {
      return 37 * this.targetPackageName.hashCode();
    }

    public boolean matches(Class paramClass)
    {
      String str = paramClass.getPackage().getName();
      return (str.equals(this.targetPackageName)) || (str.startsWith(this.targetPackageName + "."));
    }

    public String toString()
    {
      return "inSubpackage(" + this.targetPackageName + ")";
    }
  }

  private static class Not<T> extends AbstractMatcher<T>
    implements Serializable
  {
    private static final long serialVersionUID;
    final Matcher<? super T> delegate;

    private Not(Matcher<? super T> paramMatcher)
    {
      this.delegate = ((Matcher)Preconditions.checkNotNull(paramMatcher, "delegate"));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Not)) && (((Not)paramObject).delegate.equals(this.delegate));
    }

    public int hashCode()
    {
      return -this.delegate.hashCode();
    }

    public boolean matches(T paramT)
    {
      return !this.delegate.matches(paramT);
    }

    public String toString()
    {
      return "not(" + this.delegate + ")";
    }
  }

  private static class Only extends AbstractMatcher<Object>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Object value;

    public Only(Object paramObject)
    {
      this.value = Preconditions.checkNotNull(paramObject, "value");
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Only)) && (((Only)paramObject).value.equals(this.value));
    }

    public int hashCode()
    {
      return 37 * this.value.hashCode();
    }

    public boolean matches(Object paramObject)
    {
      return this.value.equals(paramObject);
    }

    public String toString()
    {
      return "only(" + this.value + ")";
    }
  }

  private static class Returns extends AbstractMatcher<Method>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Matcher<? super Class<?>> returnType;

    public Returns(Matcher<? super Class<?>> paramMatcher)
    {
      this.returnType = ((Matcher)Preconditions.checkNotNull(paramMatcher, "return type matcher"));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Returns)) && (((Returns)paramObject).returnType.equals(this.returnType));
    }

    public int hashCode()
    {
      return 37 * this.returnType.hashCode();
    }

    public boolean matches(Method paramMethod)
    {
      return this.returnType.matches(paramMethod.getReturnType());
    }

    public String toString()
    {
      return "returns(" + this.returnType + ")";
    }
  }

  private static class SubclassesOf extends AbstractMatcher<Class>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Class<?> superclass;

    public SubclassesOf(Class<?> paramClass)
    {
      this.superclass = ((Class)Preconditions.checkNotNull(paramClass, "superclass"));
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof SubclassesOf)) && (((SubclassesOf)paramObject).superclass.equals(this.superclass));
    }

    public int hashCode()
    {
      return 37 * this.superclass.hashCode();
    }

    public boolean matches(Class paramClass)
    {
      return this.superclass.isAssignableFrom(paramClass);
    }

    public String toString()
    {
      return "subclassesOf(" + this.superclass.getSimpleName() + ".class)";
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.matcher.Matchers
 * JD-Core Version:    0.6.2
 */