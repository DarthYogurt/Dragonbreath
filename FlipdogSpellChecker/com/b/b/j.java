package com.b.b;

import com.flipdog.commons.diagnostic.Track;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;

public class j
  implements InvocationHandler
{
  public Iterable<?> a;
  public f b;

  private j(Iterable<?> paramIterable, f paramf)
  {
    this.a = paramIterable;
    this.b = paramf;
  }

  public static Object a(Class<?> paramClass, Iterable<?> paramIterable, f paramf)
  {
    return Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, new j(paramIterable, paramf));
  }

  private boolean a(Method paramMethod)
  {
    return paramMethod.getReturnType().getName().equals("void");
  }

  protected void a(Method paramMethod, Object[] paramArrayOfObject)
  {
    Iterator localIterator = this.a.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Object localObject = localIterator.next();
      try
      {
        paramMethod.invoke(localObject, paramArrayOfObject);
      }
      catch (Exception localException)
      {
        Track.it(localException);
      }
    }
  }

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    if (Track.isEnabled("EventBus"))
      g.a(paramMethod, paramArrayOfObject, this.a);
    if (!a(paramMethod))
      throw new RuntimeException("Only 'void' events supported.");
    this.b.a(new a(this, paramMethod, paramArrayOfObject));
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b.j
 * JD-Core Version:    0.6.2
 */