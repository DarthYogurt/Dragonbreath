package com.google.inject;

import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

abstract interface ConstructionProxy<T>
{
  public abstract Constructor<T> getConstructor();

  public abstract InjectionPoint getInjectionPoint();

  public abstract T newInstance(Object[] paramArrayOfObject)
    throws InvocationTargetException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ConstructionProxy
 * JD-Core Version:    0.6.2
 */