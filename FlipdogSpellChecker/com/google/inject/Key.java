package com.google.inject;

import com.google.inject.internal.Annotations;
import com.google.inject.internal.MoreTypes;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.ToStringBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class Key<T>
{
  private final AnnotationStrategy annotationStrategy;
  private final int hashCode;
  private final TypeLiteral<T> typeLiteral;

  protected Key()
  {
    this.annotationStrategy = NullAnnotationStrategy.INSTANCE;
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  private Key(TypeLiteral<T> paramTypeLiteral, AnnotationStrategy paramAnnotationStrategy)
  {
    this.annotationStrategy = paramAnnotationStrategy;
    this.typeLiteral = MoreTypes.makeKeySafe(paramTypeLiteral);
    this.hashCode = computeHashCode();
  }

  protected Key(Class<? extends Annotation> paramClass)
  {
    this.annotationStrategy = strategyFor(paramClass);
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  protected Key(Annotation paramAnnotation)
  {
    this.annotationStrategy = strategyFor(paramAnnotation);
    this.typeLiteral = TypeLiteral.fromSuperclassTypeParameter(getClass());
    this.hashCode = computeHashCode();
  }

  private Key(Type paramType, AnnotationStrategy paramAnnotationStrategy)
  {
    this.annotationStrategy = paramAnnotationStrategy;
    this.typeLiteral = MoreTypes.makeKeySafe(TypeLiteral.get(paramType));
    this.hashCode = computeHashCode();
  }

  private int computeHashCode()
  {
    return 31 * this.typeLiteral.hashCode() + this.annotationStrategy.hashCode();
  }

  private static void ensureIsBindingAnnotation(Class<? extends Annotation> paramClass)
  {
    boolean bool = isBindingAnnotation(paramClass);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramClass.getName();
    Preconditions.checkArgument(bool, "%s is not a binding annotation. Please annotate it with @BindingAnnotation.", arrayOfObject);
  }

  private static void ensureRetainedAtRuntime(Class<? extends Annotation> paramClass)
  {
    boolean bool = Annotations.isRetainedAtRuntime(paramClass);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramClass.getName();
    Preconditions.checkArgument(bool, "%s is not retained at runtime. Please annotate it with @Retention(RUNTIME).", arrayOfObject);
  }

  public static <T> Key<T> get(TypeLiteral<T> paramTypeLiteral)
  {
    return new Key(paramTypeLiteral, NullAnnotationStrategy.INSTANCE);
  }

  public static <T> Key<T> get(TypeLiteral<T> paramTypeLiteral, Class<? extends Annotation> paramClass)
  {
    return new Key(paramTypeLiteral, strategyFor(paramClass));
  }

  public static <T> Key<T> get(TypeLiteral<T> paramTypeLiteral, Annotation paramAnnotation)
  {
    return new Key(paramTypeLiteral, strategyFor(paramAnnotation));
  }

  public static <T> Key<T> get(Class<T> paramClass)
  {
    return new Key(paramClass, NullAnnotationStrategy.INSTANCE);
  }

  static <T> Key<T> get(Class<T> paramClass, AnnotationStrategy paramAnnotationStrategy)
  {
    return new Key(paramClass, paramAnnotationStrategy);
  }

  public static <T> Key<T> get(Class<T> paramClass, Class<? extends Annotation> paramClass1)
  {
    return new Key(paramClass, strategyFor(paramClass1));
  }

  public static <T> Key<T> get(Class<T> paramClass, Annotation paramAnnotation)
  {
    return new Key(paramClass, strategyFor(paramAnnotation));
  }

  public static Key<?> get(Type paramType)
  {
    return new Key(paramType, NullAnnotationStrategy.INSTANCE);
  }

  public static Key<?> get(Type paramType, Class<? extends Annotation> paramClass)
  {
    return new Key(paramType, strategyFor(paramClass));
  }

  public static Key<?> get(Type paramType, Annotation paramAnnotation)
  {
    return new Key(paramType, strategyFor(paramAnnotation));
  }

  static boolean isBindingAnnotation(Class<? extends Annotation> paramClass)
  {
    return paramClass.isAnnotationPresent(BindingAnnotation.class);
  }

  static boolean isBindingAnnotation(Annotation paramAnnotation)
  {
    return isBindingAnnotation(paramAnnotation.annotationType());
  }

  static boolean isMarker(Class<? extends Annotation> paramClass)
  {
    return paramClass.getDeclaredMethods().length == 0;
  }

  static AnnotationStrategy strategyFor(Class<? extends Annotation> paramClass)
  {
    Preconditions.checkNotNull(paramClass, "annotation type");
    ensureRetainedAtRuntime(paramClass);
    ensureIsBindingAnnotation(paramClass);
    return new AnnotationTypeStrategy(paramClass, null);
  }

  static AnnotationStrategy strategyFor(Annotation paramAnnotation)
  {
    Preconditions.checkNotNull(paramAnnotation, "annotation");
    Class localClass = paramAnnotation.annotationType();
    ensureRetainedAtRuntime(localClass);
    ensureIsBindingAnnotation(localClass);
    if (localClass.getDeclaredMethods().length == 0)
      return new AnnotationTypeStrategy(localClass, paramAnnotation);
    return new AnnotationInstanceStrategy(paramAnnotation);
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this);
    Key localKey;
    do
    {
      return true;
      if (!(paramObject instanceof Key))
        return false;
      localKey = (Key)paramObject;
    }
    while ((this.annotationStrategy.equals(localKey.annotationStrategy)) && (this.typeLiteral.equals(localKey.typeLiteral)));
    return false;
  }

  public final Annotation getAnnotation()
  {
    return this.annotationStrategy.getAnnotation();
  }

  String getAnnotationName()
  {
    Annotation localAnnotation = this.annotationStrategy.getAnnotation();
    if (localAnnotation != null)
      return localAnnotation.toString();
    return this.annotationStrategy.getAnnotationType().toString();
  }

  public final Class<? extends Annotation> getAnnotationType()
  {
    return this.annotationStrategy.getAnnotationType();
  }

  Class<? super T> getRawType()
  {
    return this.typeLiteral.getRawType();
  }

  public final TypeLiteral<T> getTypeLiteral()
  {
    return this.typeLiteral;
  }

  boolean hasAnnotationType()
  {
    return this.annotationStrategy.getAnnotationType() != null;
  }

  boolean hasAttributes()
  {
    return this.annotationStrategy.hasAttributes();
  }

  public final int hashCode()
  {
    return this.hashCode;
  }

  <T> Key<T> ofType(TypeLiteral<T> paramTypeLiteral)
  {
    return new Key(paramTypeLiteral, this.annotationStrategy);
  }

  <T> Key<T> ofType(Class<T> paramClass)
  {
    return new Key(paramClass, this.annotationStrategy);
  }

  Key<?> ofType(Type paramType)
  {
    return new Key(paramType, this.annotationStrategy);
  }

  Key<Provider<T>> providerKey()
  {
    return ofType(this.typeLiteral.providerType());
  }

  public final String toString()
  {
    return new ToStringBuilder(Key.class).add("type", this.typeLiteral).add("annotation", this.annotationStrategy).toString();
  }

  Key<T> withoutAttributes()
  {
    return new Key(this.typeLiteral, this.annotationStrategy.withoutAttributes());
  }

  static class AnnotationInstanceStrategy
    implements Key.AnnotationStrategy
  {
    final Annotation annotation;

    AnnotationInstanceStrategy(Annotation paramAnnotation)
    {
      this.annotation = ((Annotation)Preconditions.checkNotNull(paramAnnotation, "annotation"));
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AnnotationInstanceStrategy))
        return false;
      AnnotationInstanceStrategy localAnnotationInstanceStrategy = (AnnotationInstanceStrategy)paramObject;
      return this.annotation.equals(localAnnotationInstanceStrategy.annotation);
    }

    public Annotation getAnnotation()
    {
      return this.annotation;
    }

    public Class<? extends Annotation> getAnnotationType()
    {
      return this.annotation.annotationType();
    }

    public boolean hasAttributes()
    {
      return true;
    }

    public int hashCode()
    {
      return this.annotation.hashCode();
    }

    public String toString()
    {
      return this.annotation.toString();
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      return new Key.AnnotationTypeStrategy(getAnnotationType(), this.annotation);
    }
  }

  static abstract interface AnnotationStrategy
  {
    public abstract Annotation getAnnotation();

    public abstract Class<? extends Annotation> getAnnotationType();

    public abstract boolean hasAttributes();

    public abstract AnnotationStrategy withoutAttributes();
  }

  static class AnnotationTypeStrategy
    implements Key.AnnotationStrategy
  {
    final Annotation annotation;
    final Class<? extends Annotation> annotationType;

    AnnotationTypeStrategy(Class<? extends Annotation> paramClass, Annotation paramAnnotation)
    {
      this.annotationType = ((Class)Preconditions.checkNotNull(paramClass, "annotation type"));
      this.annotation = paramAnnotation;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof AnnotationTypeStrategy))
        return false;
      AnnotationTypeStrategy localAnnotationTypeStrategy = (AnnotationTypeStrategy)paramObject;
      return this.annotationType.equals(localAnnotationTypeStrategy.annotationType);
    }

    public Annotation getAnnotation()
    {
      return this.annotation;
    }

    public Class<? extends Annotation> getAnnotationType()
    {
      return this.annotationType;
    }

    public boolean hasAttributes()
    {
      return false;
    }

    public int hashCode()
    {
      return this.annotationType.hashCode();
    }

    public String toString()
    {
      return "@" + this.annotationType.getName();
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      throw new UnsupportedOperationException("Key already has no attributes.");
    }
  }

  static enum NullAnnotationStrategy
    implements Key.AnnotationStrategy
  {
    static
    {
      NullAnnotationStrategy[] arrayOfNullAnnotationStrategy = new NullAnnotationStrategy[1];
      arrayOfNullAnnotationStrategy[0] = INSTANCE;
    }

    public Annotation getAnnotation()
    {
      return null;
    }

    public Class<? extends Annotation> getAnnotationType()
    {
      return null;
    }

    public boolean hasAttributes()
    {
      return false;
    }

    public String toString()
    {
      return "[none]";
    }

    public Key.AnnotationStrategy withoutAttributes()
    {
      throw new UnsupportedOperationException("Key already has no attributes.");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Key
 * JD-Core Version:    0.6.2
 */