package com.b.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.WeakHashMap;

public class b
{
  private HashMap<Class<?>, WeakHashMap<Object, Object>> a = new HashMap();
  private f b;
  private boolean c = false;

  private <T> T a(T paramT)
  {
    try
    {
      Class[] arrayOfClass;
      int j;
      if (paramT.getClass().getInterfaces().length != 0)
      {
        arrayOfClass = paramT.getClass().getInterfaces();
        int i = arrayOfClass.length;
        j = 0;
        if (j < i);
      }
      while (true)
      {
        return paramT;
        a(arrayOfClass[j], paramT);
        j++;
        break;
        a(paramT.getClass().getSuperclass(), paramT);
      }
    }
    finally
    {
    }
  }

  private <T> void a(Class<?> paramClass, T paramT)
  {
    if (!this.a.containsKey(paramClass))
      this.a.put(paramClass, new WeakHashMap());
    ((WeakHashMap)this.a.get(paramClass)).put(paramT, null);
  }

  private <T> ArrayList<T> c(Class<T> paramClass)
  {
    WeakHashMap localWeakHashMap = (WeakHashMap)this.a.get(paramClass);
    Set localSet = null;
    if (localWeakHashMap != null)
      localSet = localWeakHashMap.keySet();
    if (localSet == null)
      return new ArrayList();
    return new ArrayList(localSet);
  }

  public <T> T a(Class<T> paramClass)
  {
    try
    {
      ArrayList localArrayList = c(paramClass);
      return c.a(paramClass, localArrayList);
    }
    finally
    {
    }
  }

  public void a()
  {
    this.c = true;
  }

  public <T> void a(h paramh, T paramT)
  {
    try
    {
      a(paramT);
      paramh.a(paramT);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public <T> T b(Class<T> paramClass)
  {
    if (this.c)
      return a(paramClass);
    try
    {
      ArrayList localArrayList = c(paramClass);
      if (this.b == null)
      {
        this.b = new f();
        this.b.b();
      }
      return j.a(paramClass, localArrayList, this.b);
    }
    finally
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b.b
 * JD-Core Version:    0.6.2
 */