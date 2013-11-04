package com.flipdog.commons.a;

import java.util.Map;
import java.util.WeakHashMap;

public class ap
{
  private static WeakHashMap<Object, Map<Object, Object>> a = as.e();

  public static Object a()
  {
    try
    {
      Integer localInteger = Integer.valueOf(a.size());
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static <T> T a(Object paramObject1, Object paramObject2)
  {
    try
    {
      Map localMap = (Map)a.get(paramObject1);
      if (localMap == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = localMap.get(paramObject2);
      }
    }
    finally
    {
    }
  }

  public static void a(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    try
    {
      if (!a.containsKey(paramObject2))
      {
        Map localMap = as.d();
        a.put(paramObject1, localMap);
      }
      ((Map)a.get(paramObject1)).put(paramObject2, paramObject3);
      return;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ap
 * JD-Core Version:    0.6.2
 */