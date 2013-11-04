package com.b.b;

import com.flipdog.commons.diagnostic.Track;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Iterator;

public class c
  implements InvocationHandler
{
  public Iterable<?> a;

  private c(Iterable<?> paramIterable)
  {
    this.a = paramIterable;
  }

  public static Object a(Class<?> paramClass, Iterable<?> paramIterable)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, new c(paramIterable));
  }

  private boolean a(Method paramMethod)
  {
    return paramMethod.getReturnType().getName().equals("void");
  }

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    if (Track.isEnabled("EventBus"))
      g.a(paramMethod, paramArrayOfObject, this.a);
    Object localObject1 = null;
    int i = 0;
    Iterator localIterator = this.a.iterator();
    while (true)
      if (!localIterator.hasNext())
      {
        if ((i == 0) && (!a(paramMethod)))
          throw new i();
      }
      else
      {
        Object localObject2 = localIterator.next();
        try
        {
          Object localObject3 = paramMethod.invoke(localObject2, paramArrayOfObject);
          localObject1 = localObject3;
          i = 1;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          throw localInvocationTargetException.getCause();
        }
        catch (UndeclaredThrowableException localUndeclaredThrowableException)
        {
          throw localUndeclaredThrowableException.getCause();
        }
      }
    return localObject1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b.c
 * JD-Core Version:    0.6.2
 */