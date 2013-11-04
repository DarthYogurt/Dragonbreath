package com.flipdog.commons.diagnostic;

import java.util.HashMap;

public class g
{
  private static HashMap<String, Object> a = new HashMap();

  public static Object a(String paramString)
  {
    try
    {
      Object localObject2 = a.get(paramString);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public static <T> T a(String paramString, T paramT)
  {
    try
    {
      Object localObject2 = a.get(paramString);
      if (localObject2 == null)
        return paramT;
      paramT = localObject2;
    }
    finally
    {
    }
  }

  public static void b(String paramString, Object paramObject)
  {
    try
    {
      a.put(paramString, paramObject);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.g
 * JD-Core Version:    0.6.2
 */