package com.flipdog.commons.a;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class c
{
  private static bb a(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length % 2 != 0)
      throw new RuntimeException("The 'types' and 'values' should match.");
    bb localbb = new bb(null);
    localbb.a = (paramArrayOfObject.length / 2);
    localbb.b = new Class[localbb.a];
    localbb.c = new Object[localbb.a];
    for (int i = 0; ; i++)
    {
      if (i >= localbb.a)
        return localbb;
      localbb.b[i] = ((Class)paramArrayOfObject[(i * 2)]);
      localbb.c[i] = paramArrayOfObject[(1 + i * 2)];
    }
  }

  public static <T> T a(Class<?> paramClass, Object paramObject, String paramString)
  {
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      localField.setAccessible(true);
      Object localObject = localField.get(paramObject);
      return localObject;
    }
    catch (SecurityException localSecurityException)
    {
      throw new RuntimeException(localSecurityException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }

  public static <T> T a(Class<?> paramClass, Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      bb localbb = a(paramArrayOfObject);
      Method localMethod = paramClass.getDeclaredMethod(paramString, localbb.b);
      localMethod.setAccessible(true);
      Object localObject = localMethod.invoke(paramObject, localbb.c);
      return localObject;
    }
    catch (SecurityException localSecurityException)
    {
      throw new RuntimeException(localSecurityException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new RuntimeException(localInvocationTargetException);
    }
  }

  public static <T> T a(Class<T> paramClass, Object[] paramArrayOfObject)
    throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
  {
    bb localbb = a(paramArrayOfObject);
    return paramClass.getConstructor(localbb.b).newInstance(localbb.c);
  }

  public static <T> T a(Object paramObject, String paramString)
  {
    try
    {
      if ((paramObject instanceof Class));
      Class localClass;
      for (Object localObject = (Class)paramObject; ; localObject = localClass)
      {
        return ((Class)localObject).getField(paramString).get(paramObject);
        localClass = paramObject.getClass();
      }
    }
    catch (SecurityException localSecurityException)
    {
      throw new RuntimeException(localSecurityException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }

  public static <T> T a(Object paramObject, String paramString, Object[] paramArrayOfObject)
  {
    try
    {
      if ((paramObject instanceof Class));
      Class localClass;
      for (Object localObject = (Class)paramObject; ; localObject = localClass)
      {
        bb localbb = a(paramArrayOfObject);
        return ((Class)localObject).getMethod(paramString, localbb.b).invoke(paramObject, localbb.c);
        localClass = paramObject.getClass();
      }
    }
    catch (SecurityException localSecurityException)
    {
      throw new RuntimeException(localSecurityException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new RuntimeException(localNoSuchMethodException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new RuntimeException(localInvocationTargetException);
    }
  }

  public static void a(Class<?> paramClass, Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      localField.setAccessible(true);
      localField.set(paramObject1, paramObject2);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      throw new RuntimeException(localSecurityException);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new RuntimeException(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }

  public static boolean a(Class<?> paramClass1, Class<?> paramClass2)
  {
    return paramClass2.isAssignableFrom(paramClass1);
  }

  public static boolean a(Object paramObject, Class<?> paramClass)
  {
    return paramClass.isInstance(paramObject);
  }

  public static boolean a(Object paramObject1, Object paramObject2)
    throws IllegalArgumentException, IllegalAccessException
  {
    if ((paramObject1 == null) && (paramObject2 == null))
      return true;
    if ((paramObject1 == null) || (paramObject2 == null))
      return false;
    Field[] arrayOfField = paramObject1.getClass().getFields();
    int i = arrayOfField.length;
    int j = 0;
    label34: Field localField;
    if (j < i)
    {
      localField = arrayOfField[j];
      if (b(localField))
        break label60;
    }
    label60: 
    while (b(localField.get(paramObject1), localField.get(paramObject2)))
    {
      j++;
      break label34;
      break;
    }
    return false;
  }

  public static boolean a(Field paramField)
  {
    return Modifier.isFinal(paramField.getModifiers());
  }

  public static boolean a(Method paramMethod)
  {
    return paramMethod.getReturnType().getName().equals("void");
  }

  public static <T> T[] a(Class<T> paramClass, int paramInt)
  {
    return (Object[])Array.newInstance(paramClass, paramInt);
  }

  private static boolean b(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) && (paramObject2 == null))
      return true;
    if ((paramObject1 == null) || (paramObject2 == null))
      return false;
    return paramObject1.equals(paramObject2);
  }

  public static boolean b(Field paramField)
  {
    return Modifier.isPublic(paramField.getModifiers());
  }

  public static boolean b(Method paramMethod)
  {
    return paramMethod.getParameterTypes().length != 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.c
 * JD-Core Version:    0.6.2
 */