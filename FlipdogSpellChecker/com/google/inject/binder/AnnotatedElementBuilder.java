package com.google.inject.binder;

import java.lang.annotation.Annotation;

public abstract interface AnnotatedElementBuilder
{
  public abstract void annotatedWith(Class<? extends Annotation> paramClass);

  public abstract void annotatedWith(Annotation paramAnnotation);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.AnnotatedElementBuilder
 * JD-Core Version:    0.6.2
 */