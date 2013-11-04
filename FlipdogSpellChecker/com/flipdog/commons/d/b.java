package com.flipdog.commons.d;

import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class b
{
  private static WeakHashMap<Object, Object> a = new WeakHashMap();
  private static boolean b = false;

  public static void a()
  {
    Map localMap = as.d();
    Iterator localIterator1 = a.keySet().iterator();
    Iterator localIterator2;
    label38: Class localClass1;
    if (!localIterator1.hasNext())
    {
      localIterator2 = localMap.keySet().iterator();
      if (localIterator2.hasNext());
    }
    else
    {
      localClass1 = localIterator1.next().getClass();
      if (!localMap.containsKey(localClass1))
        break label148;
    }
    label148: for (int i = ((Integer)localMap.get(localClass1)).intValue(); ; i = 0)
    {
      localMap.put(localClass1, Integer.valueOf(i + 1));
      break;
      Class localClass2 = (Class)localIterator2.next();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localClass2;
      arrayOfObject[1] = localMap.get(localClass2);
      Track.me("Memory", "%s: %s", arrayOfObject);
      break label38;
    }
  }

  public static void a(Object paramObject)
  {
    if (!b)
      return;
    a.put(paramObject, null);
  }

  public static void a(boolean paramBoolean)
  {
    b = paramBoolean;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.d.b
 * JD-Core Version:    0.6.2
 */