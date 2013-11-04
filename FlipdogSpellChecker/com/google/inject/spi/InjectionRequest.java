package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.Preconditions;
import java.util.Set;

public final class InjectionRequest<T>
  implements Element
{
  private final T instance;
  private final Object source;
  private final TypeLiteral<T> type;

  public InjectionRequest(Object paramObject, TypeLiteral<T> paramTypeLiteral, T paramT)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.type = ((TypeLiteral)Preconditions.checkNotNull(paramTypeLiteral, "type"));
    this.instance = Preconditions.checkNotNull(paramT, "instance");
  }

  public <R> R acceptVisitor(ElementVisitor<R> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).requestInjection(this.type, this.instance);
  }

  public Set<InjectionPoint> getInjectionPoints()
    throws ConfigurationException
  {
    return InjectionPoint.forInstanceMethodsAndFields(this.instance.getClass());
  }

  public T getInstance()
  {
    return this.instance;
  }

  public Object getSource()
  {
    return this.source;
  }

  public TypeLiteral<T> getType()
  {
    return this.type;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.InjectionRequest
 * JD-Core Version:    0.6.2
 */