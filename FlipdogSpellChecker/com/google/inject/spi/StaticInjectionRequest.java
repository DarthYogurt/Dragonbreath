package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.internal.Preconditions;
import java.util.Set;

public final class StaticInjectionRequest
  implements Element
{
  private final Object source;
  private final Class<?> type;

  StaticInjectionRequest(Object paramObject, Class<?> paramClass)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.type = ((Class)Preconditions.checkNotNull(paramClass, "type"));
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    Binder localBinder = paramBinder.withSource(getSource());
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = this.type;
    localBinder.requestStaticInjection(arrayOfClass);
  }

  public Set<InjectionPoint> getInjectionPoints()
    throws ConfigurationException
  {
    return InjectionPoint.forStaticMethodsAndFields(this.type);
  }

  public Object getSource()
  {
    return this.source;
  }

  public Class<?> getType()
  {
    return this.type;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.StaticInjectionRequest
 * JD-Core Version:    0.6.2
 */