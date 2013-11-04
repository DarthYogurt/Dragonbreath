package com.google.inject.binder;

import com.google.inject.Scope;
import java.lang.annotation.Annotation;

public abstract interface ScopedBindingBuilder
{
  public abstract void asEagerSingleton();

  public abstract void in(Scope paramScope);

  public abstract void in(Class<? extends Annotation> paramClass);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.ScopedBindingBuilder
 * JD-Core Version:    0.6.2
 */