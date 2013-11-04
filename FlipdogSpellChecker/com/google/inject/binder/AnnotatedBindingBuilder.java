package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedBindingBuilder<T> extends LinkedBindingBuilder<T>
{
  public abstract LinkedBindingBuilder<T> annotatedWith(Class<? extends Annotation> paramClass);

  public abstract LinkedBindingBuilder<T> annotatedWith(Annotation paramAnnotation);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.AnnotatedBindingBuilder
 * JD-Core Version:    0.6.2
 */