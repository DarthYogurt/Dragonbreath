package com.google.inject.spi;

import com.google.inject.Scope;
import java.lang.annotation.Annotation;

public abstract interface BindingScopingVisitor<V>
{
  public abstract V visitEagerSingleton();

  public abstract V visitNoScoping();

  public abstract V visitScope(Scope paramScope);

  public abstract V visitScopeAnnotation(Class<? extends Annotation> paramClass);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.BindingScopingVisitor
 * JD-Core Version:    0.6.2
 */