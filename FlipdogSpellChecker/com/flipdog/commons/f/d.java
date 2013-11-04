package com.flipdog.commons.f;

import java.util.Map;

public class d
{
  protected static ThreadLocal<Map<Object, Object>> a = new b();

  public static <T> T a(Object paramObject)
  {
    return ((Map)a.get()).get(paramObject);
  }

  public static void a(Object paramObject1, Object paramObject2)
  {
    ((Map)a.get()).put(paramObject1, paramObject2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.f.d
 * JD-Core Version:    0.6.2
 */