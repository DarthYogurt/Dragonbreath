package com.google.inject.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConstructionContext<T>
{
  boolean constructing;
  T currentReference;
  List<DelegatingInvocationHandler<T>> invocationHandlers;

  public Object createProxy(Errors paramErrors, Class<?> paramClass)
    throws ErrorsException
  {
    if (!paramClass.isInterface())
      throw paramErrors.cannotSatisfyCircularDependency(paramClass).toException();
    if (this.invocationHandlers == null)
      this.invocationHandlers = new ArrayList();
    DelegatingInvocationHandler localDelegatingInvocationHandler = new DelegatingInvocationHandler();
    this.invocationHandlers.add(localDelegatingInvocationHandler);
    return paramClass.cast(Proxy.newProxyInstance(BytecodeGen.getClassLoader(paramClass), new Class[] { paramClass }, localDelegatingInvocationHandler));
  }

  public void finishConstruction()
  {
    this.constructing = false;
    this.invocationHandlers = null;
  }

  public T getCurrentReference()
  {
    return this.currentReference;
  }

  public boolean isConstructing()
  {
    return this.constructing;
  }

  public void removeCurrentReference()
  {
    this.currentReference = null;
  }

  public void setCurrentReference(T paramT)
  {
    this.currentReference = paramT;
  }

  public void setProxyDelegates(T paramT)
  {
    if (this.invocationHandlers != null)
    {
      Iterator localIterator = this.invocationHandlers.iterator();
      while (localIterator.hasNext())
        ((DelegatingInvocationHandler)localIterator.next()).setDelegate(paramT);
    }
  }

  public void startConstruction()
  {
    this.constructing = true;
  }

  static class DelegatingInvocationHandler<T>
    implements InvocationHandler
  {
    T delegate;

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      if (this.delegate == null)
        throw new IllegalStateException("This is a proxy used to support circular references involving constructors. The object we're proxying is not constructed yet. Please wait until after injection has completed to use this object.");
      try
      {
        Object localObject = paramMethod.invoke(this.delegate, paramArrayOfObject);
        return localObject;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new RuntimeException(localIllegalAccessException);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new RuntimeException(localIllegalArgumentException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw localInvocationTargetException.getTargetException();
      }
    }

    void setDelegate(T paramT)
    {
      this.delegate = paramT;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ConstructionContext
 * JD-Core Version:    0.6.2
 */