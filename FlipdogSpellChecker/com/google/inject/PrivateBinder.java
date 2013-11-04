package com.google.inject;

import com.google.inject.binder.AnnotatedElementBuilder;

public abstract interface PrivateBinder extends Binder
{
  public abstract AnnotatedElementBuilder expose(TypeLiteral<?> paramTypeLiteral);

  public abstract AnnotatedElementBuilder expose(Class<?> paramClass);

  public abstract void expose(Key<?> paramKey);

  public abstract PrivateBinder skipSources(Class[] paramArrayOfClass);

  public abstract PrivateBinder withSource(Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.PrivateBinder
 * JD-Core Version:    0.6.2
 */