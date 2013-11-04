package com.millennialmedia.google.gson.internal;

import com.millennialmedia.google.gson.InstanceCreator;
import com.millennialmedia.google.gson.JsonIOException;
import com.millennialmedia.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class ConstructorConstructor
{
  private final Map<Type, InstanceCreator<?>> instanceCreators;

  public ConstructorConstructor(Map<Type, InstanceCreator<?>> paramMap)
  {
    this.instanceCreators = paramMap;
  }

  private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> paramClass)
  {
    try
    {
      final Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible())
        localConstructor.setAccessible(true);
      ObjectConstructor local3 = new ObjectConstructor()
      {
        public T construct()
        {
          try
          {
            Object localObject = localConstructor.newInstance(null);
            return localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException("Failed to invoke " + localConstructor + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      return local3;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return null;
  }

  private <T> ObjectConstructor<T> newDefaultImplementationConstructor(final Type paramType, Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new TreeSet();
          }
        };
      if (EnumSet.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            if ((paramType instanceof ParameterizedType))
            {
              Type localType = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localType instanceof Class))
                return EnumSet.noneOf((Class)localType);
              throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
            }
            throw new JsonIOException("Invalid EnumSet type: " + paramType.toString());
          }
        };
      if (Set.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedHashSet();
          }
        };
      if (Queue.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedList();
          }
        };
      return new ObjectConstructor()
      {
        public T construct()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (SortedMap.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new TreeMap();
          }
        };
      if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)paramType).getActualTypeArguments()[0]).getRawType())))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedHashMap();
          }
        };
      return new ObjectConstructor()
      {
        public T construct()
        {
          return new LinkedHashTreeMap();
        }
      };
    }
    return null;
  }

  private <T> ObjectConstructor<T> newUnsafeAllocator(final Type paramType, final Class<? super T> paramClass)
  {
    return new ObjectConstructor()
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

      public T construct()
      {
        try
        {
          Object localObject = this.unsafeAllocator.newInstance(paramClass);
          return localObject;
        }
        catch (Exception localException)
        {
          throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }

  public <T> ObjectConstructor<T> get(TypeToken<T> paramTypeToken)
  {
    final Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    final InstanceCreator localInstanceCreator1 = (InstanceCreator)this.instanceCreators.get(localType);
    Object localObject;
    if (localInstanceCreator1 != null)
      localObject = new ObjectConstructor()
      {
        public T construct()
        {
          return localInstanceCreator1.createInstance(localType);
        }
      };
    do
    {
      return localObject;
      final InstanceCreator localInstanceCreator2 = (InstanceCreator)this.instanceCreators.get(localClass);
      if (localInstanceCreator2 != null)
        return new ObjectConstructor()
        {
          public T construct()
          {
            return localInstanceCreator2.createInstance(localType);
          }
        };
      localObject = newDefaultConstructor(localClass);
    }
    while (localObject != null);
    ObjectConstructor localObjectConstructor = newDefaultImplementationConstructor(localType, localClass);
    if (localObjectConstructor != null)
      return localObjectConstructor;
    return newUnsafeAllocator(localType, localClass);
  }

  public String toString()
  {
    return this.instanceCreators.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal.ConstructorConstructor
 * JD-Core Version:    0.6.2
 */