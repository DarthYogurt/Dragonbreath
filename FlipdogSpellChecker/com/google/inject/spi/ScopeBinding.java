package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.Scope;
import com.google.inject.internal.Preconditions;
import java.lang.annotation.Annotation;

public final class ScopeBinding
  implements Element
{
  private final Class<? extends Annotation> annotationType;
  private final Scope scope;
  private final Object source;

  ScopeBinding(Object paramObject, Class<? extends Annotation> paramClass, Scope paramScope)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.annotationType = ((Class)Preconditions.checkNotNull(paramClass, "annotationType"));
    this.scope = ((Scope)Preconditions.checkNotNull(paramScope, "scope"));
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).bindScope(this.annotationType, this.scope);
  }

  public Class<? extends Annotation> getAnnotationType()
  {
    return this.annotationType;
  }

  public Scope getScope()
  {
    return this.scope;
  }

  public Object getSource()
  {
    return this.source;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ScopeBinding
 * JD-Core Version:    0.6.2
 */