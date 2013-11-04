package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;

public final class TypeListenerBinding
  implements Element
{
  private final TypeListener listener;
  private final Object source;
  private final Matcher<? super TypeLiteral<?>> typeMatcher;

  TypeListenerBinding(Object paramObject, TypeListener paramTypeListener, Matcher<? super TypeLiteral<?>> paramMatcher)
  {
    this.source = paramObject;
    this.listener = paramTypeListener;
    this.typeMatcher = paramMatcher;
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).bindListener(this.typeMatcher, this.listener);
  }

  public TypeListener getListener()
  {
    return this.listener;
  }

  public Object getSource()
  {
    return this.source;
  }

  public Matcher<? super TypeLiteral<?>> getTypeMatcher()
  {
    return this.typeMatcher;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.TypeListenerBinding
 * JD-Core Version:    0.6.2
 */