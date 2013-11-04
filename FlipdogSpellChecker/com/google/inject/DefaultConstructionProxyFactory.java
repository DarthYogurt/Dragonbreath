package com.google.inject;

import com.google.inject.spi.InjectionPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

class DefaultConstructionProxyFactory<T>
  implements ConstructionProxyFactory<T>
{
  private final InjectionPoint injectionPoint;

  DefaultConstructionProxyFactory(InjectionPoint paramInjectionPoint)
  {
    this.injectionPoint = paramInjectionPoint;
  }

  public ConstructionProxy<T> create()
  {
    final Constructor localConstructor = (Constructor)this.injectionPoint.getMember();
    if (Modifier.isPublic(localConstructor.getModifiers()));
    while (true)
    {
      return new ConstructionProxy()
      {
        public Constructor<T> getConstructor()
        {
          return localConstructor;
        }

        public InjectionPoint getInjectionPoint()
        {
          return DefaultConstructionProxyFactory.this.injectionPoint;
        }

        public T newInstance(Object[] paramAnonymousArrayOfObject)
          throws InvocationTargetException
        {
          try
          {
            Object localObject = localConstructor.newInstance(paramAnonymousArrayOfObject);
            return localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new AssertionError(localInstantiationException);
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      localConstructor.setAccessible(true);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.DefaultConstructionProxyFactory
 * JD-Core Version:    0.6.2
 */