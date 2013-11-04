package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedConstantBindingBuilder
{
  public abstract ConstantBindingBuilder annotatedWith(Class<? extends Annotation> paramClass);

  public abstract ConstantBindingBuilder annotatedWith(Annotation paramAnnotation);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.AnnotatedConstantBindingBuilder
 * JD-Core Version:    0.6.2
 */