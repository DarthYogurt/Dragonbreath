package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.binder.AnnotatedElementBuilder;
import java.lang.annotation.Annotation;

public class ExposureBuilder<T>
  implements AnnotatedElementBuilder
{
  private final Binder binder;
  private Key<T> key;
  private final Object source;

  public ExposureBuilder(Binder paramBinder, Object paramObject, Key<T> paramKey)
  {
    this.binder = paramBinder;
    this.source = paramObject;
    this.key = paramKey;
  }

  public void annotatedWith(Class<? extends Annotation> paramClass)
  {
    Preconditions.checkNotNull(paramClass, "annotationType");
    checkNotAnnotated();
    this.key = Key.get(this.key.getTypeLiteral(), paramClass);
  }

  public void annotatedWith(Annotation paramAnnotation)
  {
    Preconditions.checkNotNull(paramAnnotation, "annotation");
    checkNotAnnotated();
    this.key = Key.get(this.key.getTypeLiteral(), paramAnnotation);
  }

  protected void checkNotAnnotated()
  {
    if (this.key.getAnnotationType() != null)
      this.binder.addError("More than one annotation is specified for this binding.", new Object[0]);
  }

  public Key<?> getKey()
  {
    return this.key;
  }

  public Object getSource()
  {
    return this.source;
  }

  public String toString()
  {
    return "AnnotatedElementBuilder";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ExposureBuilder
 * JD-Core Version:    0.6.2
 */