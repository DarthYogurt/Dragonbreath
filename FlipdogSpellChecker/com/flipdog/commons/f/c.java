package com.flipdog.commons.f;

import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.diagnostic.i;
import com.flipdog.commons.diagnostic.k;
import com.flipdog.commons.i.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class c
{
  private static WeakHashMap<Thread, Object> a = new WeakHashMap();

  public static Thread a(Runnable paramRunnable)
  {
    Thread.currentThread().setContextClassLoader(c.class.getClassLoader());
    if (Track.isEnabled("Async"))
    {
      List localList = k.a(15, new Class[] { k.class, c.class });
      Track.it("ThreadUtils.start", new String[] { "Async" });
      Track.it(i.a(localList), new String[] { "Async" });
    }
    Thread localThread = new Thread(new a(b.a(), paramRunnable));
    a.put(localThread, null);
    localThread.start();
    return localThread;
  }

  public static ArrayList<Thread> a()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = a.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Thread localThread = (Thread)localIterator.next();
      if (localThread != null)
        localArrayList.add(localThread);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.f.c
 * JD-Core Version:    0.6.2
 */