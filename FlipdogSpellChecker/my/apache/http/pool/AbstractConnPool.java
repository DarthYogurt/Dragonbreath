package my.apache.http.pool;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.concurrent.FutureCallback;

@ThreadSafe
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>>
  implements ConnPool<T, E>, ConnPoolControl<T>
{
  private final LinkedList<E> available;
  private final ConnFactory<T, C> connFactory;
  private volatile int defaultMaxPerRoute;
  private volatile boolean isShutDown;
  private final Set<E> leased;
  private final Lock lock;
  private final Map<T, Integer> maxPerRoute;
  private volatile int maxTotal;
  private final LinkedList<PoolEntryFuture<E>> pending;
  private final Map<T, RouteSpecificPool<T, C, E>> routeToPool;

  public AbstractConnPool(ConnFactory<T, C> paramConnFactory, int paramInt1, int paramInt2)
  {
    if (paramConnFactory == null)
      throw new IllegalArgumentException("Connection factory may not null");
    if (paramInt1 <= 0)
      throw new IllegalArgumentException("Max per route value may not be negative or zero");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("Max total value may not be negative or zero");
    this.lock = new ReentrantLock();
    this.connFactory = paramConnFactory;
    this.routeToPool = new HashMap();
    this.leased = new HashSet();
    this.available = new LinkedList();
    this.pending = new LinkedList();
    this.maxPerRoute = new HashMap();
    this.defaultMaxPerRoute = paramInt1;
    this.maxTotal = paramInt2;
  }

  private int getMax(T paramT)
  {
    Integer localInteger = (Integer)this.maxPerRoute.get(paramT);
    if (localInteger != null)
      return localInteger.intValue();
    return this.defaultMaxPerRoute;
  }

  private RouteSpecificPool<T, C, E> getPool(final T paramT)
  {
    Object localObject = (RouteSpecificPool)this.routeToPool.get(paramT);
    if (localObject == null)
    {
      localObject = new RouteSpecificPool(paramT)
      {
        protected E createEntry(C paramAnonymousC)
        {
          return AbstractConnPool.this.createEntry(paramT, paramAnonymousC);
        }
      };
      this.routeToPool.put(paramT, localObject);
    }
    return localObject;
  }

  private E getPoolEntryBlocking(T paramT, Object paramObject, long paramLong, TimeUnit paramTimeUnit, PoolEntryFuture<E> paramPoolEntryFuture)
    throws IOException, InterruptedException, TimeoutException
  {
    boolean bool1 = paramLong < 0L;
    Date localDate = null;
    if (bool1)
      localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
    this.lock.lock();
    RouteSpecificPool localRouteSpecificPool;
    Object localObject2;
    try
    {
      localRouteSpecificPool = getPool(paramT);
      localObject2 = null;
      break label490;
      throw new TimeoutException("Timeout waiting for connection");
    }
    finally
    {
      this.lock.unlock();
    }
    if (this.isShutDown)
      throw new IllegalStateException("Connection pool shut down");
    do
    {
      if ((!((PoolEntry)localObject2).isClosed()) && (!((PoolEntry)localObject2).isExpired(System.currentTimeMillis())))
        break;
      ((PoolEntry)localObject2).close();
      this.available.remove(localObject2);
      localRouteSpecificPool.free((PoolEntry)localObject2, false);
      localObject2 = localRouteSpecificPool.getFree(paramObject);
    }
    while (localObject2 != null);
    if (localObject2 != null)
    {
      this.available.remove(localObject2);
      this.leased.add(localObject2);
      this.lock.unlock();
      return localObject2;
    }
    int i = getMax(paramT);
    int j = Math.max(0, 1 + localRouteSpecificPool.getAllocatedCount() - i);
    int k;
    if (j > 0)
      k = 0;
    while (true)
    {
      if (localRouteSpecificPool.getAllocatedCount() < i)
      {
        int m = this.leased.size();
        int n = Math.max(this.maxTotal - m, 0);
        if (n > 0)
        {
          if ((this.available.size() > n - 1) && (!this.available.isEmpty()))
          {
            PoolEntry localPoolEntry2 = (PoolEntry)this.available.removeLast();
            localPoolEntry2.close();
            getPool(localPoolEntry2.getRoute()).remove(localPoolEntry2);
          }
          PoolEntry localPoolEntry1 = localRouteSpecificPool.add(this.connFactory.create(paramT));
          this.leased.add(localPoolEntry1);
          this.lock.unlock();
          return localPoolEntry1;
        }
      }
      label490: label498: 
      do
        while (true)
        {
          PoolEntry localPoolEntry3 = localRouteSpecificPool.getLastUsed();
          if (localPoolEntry3 == null)
            break;
          localPoolEntry3.close();
          this.available.remove(localPoolEntry3);
          localRouteSpecificPool.remove(localPoolEntry3);
          k++;
          break label498;
          try
          {
            localRouteSpecificPool.queue(paramPoolEntryFuture);
            this.pending.add(paramPoolEntryFuture);
            boolean bool2 = paramPoolEntryFuture.await(localDate);
            localRouteSpecificPool.unqueue(paramPoolEntryFuture);
            this.pending.remove(paramPoolEntryFuture);
            if ((!bool2) && (localDate != null))
              if (localDate.getTime() > System.currentTimeMillis())
                break label490;
          }
          finally
          {
            localRouteSpecificPool.unqueue(paramPoolEntryFuture);
            this.pending.remove(paramPoolEntryFuture);
          }
        }
      while (k < j);
    }
  }

  private void notifyPending(RouteSpecificPool<T, C, E> paramRouteSpecificPool)
  {
    PoolEntryFuture localPoolEntryFuture = paramRouteSpecificPool.nextPending();
    if (localPoolEntryFuture != null)
      this.pending.remove(localPoolEntryFuture);
    while (true)
    {
      if (localPoolEntryFuture != null)
        localPoolEntryFuture.wakeup();
      return;
      localPoolEntryFuture = (PoolEntryFuture)this.pending.poll();
    }
  }

  public void closeExpired()
  {
    long l = System.currentTimeMillis();
    this.lock.lock();
    try
    {
      Iterator localIterator = this.available.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        PoolEntry localPoolEntry = (PoolEntry)localIterator.next();
        if (localPoolEntry.isExpired(l))
        {
          localPoolEntry.close();
          RouteSpecificPool localRouteSpecificPool = getPool(localPoolEntry.getRoute());
          localRouteSpecificPool.remove(localPoolEntry);
          localIterator.remove();
          notifyPending(localRouteSpecificPool);
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void closeIdle(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    long l1 = paramTimeUnit.toMillis(paramLong);
    if (l1 < 0L)
      l1 = 0L;
    long l2 = System.currentTimeMillis() - l1;
    this.lock.lock();
    try
    {
      Iterator localIterator = this.available.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        PoolEntry localPoolEntry = (PoolEntry)localIterator.next();
        if (localPoolEntry.getUpdated() <= l2)
        {
          localPoolEntry.close();
          RouteSpecificPool localRouteSpecificPool = getPool(localPoolEntry.getRoute());
          localRouteSpecificPool.remove(localPoolEntry);
          localIterator.remove();
          notifyPending(localRouteSpecificPool);
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
  }

  protected abstract E createEntry(T paramT, C paramC);

  public int getDefaultMaxPerRoute()
  {
    this.lock.lock();
    try
    {
      int i = this.defaultMaxPerRoute;
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public int getMaxPerRoute(T paramT)
  {
    if (paramT == null)
      throw new IllegalArgumentException("Route may not be null");
    this.lock.lock();
    try
    {
      int i = getMax(paramT);
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public int getMaxTotal()
  {
    this.lock.lock();
    try
    {
      int i = this.maxTotal;
      return i;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public PoolStats getStats(T paramT)
  {
    if (paramT == null)
      throw new IllegalArgumentException("Route may not be null");
    this.lock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getPool(paramT);
      PoolStats localPoolStats = new PoolStats(localRouteSpecificPool.getLeasedCount(), localRouteSpecificPool.getPendingCount(), localRouteSpecificPool.getAvailableCount(), getMax(paramT));
      return localPoolStats;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public PoolStats getTotalStats()
  {
    this.lock.lock();
    try
    {
      PoolStats localPoolStats = new PoolStats(this.leased.size(), this.pending.size(), this.available.size(), this.maxTotal);
      return localPoolStats;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public boolean isShutdown()
  {
    return this.isShutDown;
  }

  public Future<E> lease(T paramT, Object paramObject)
  {
    return lease(paramT, paramObject, null);
  }

  public Future<E> lease(final T paramT, final Object paramObject, FutureCallback<E> paramFutureCallback)
  {
    if (paramT == null)
      throw new IllegalArgumentException("Route may not be null");
    if (this.isShutDown)
      throw new IllegalStateException("Connection pool shut down");
    return new PoolEntryFuture(this.lock, paramFutureCallback)
    {
      public E getPoolEntry(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, TimeoutException, IOException
      {
        return AbstractConnPool.this.getPoolEntryBlocking(paramT, paramObject, paramAnonymousLong, paramAnonymousTimeUnit, this);
      }
    };
  }

  public void release(E paramE, boolean paramBoolean)
  {
    this.lock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool;
      if (this.leased.remove(paramE))
      {
        localRouteSpecificPool = getPool(paramE.getRoute());
        localRouteSpecificPool.free(paramE, paramBoolean);
        if ((!paramBoolean) || (this.isShutDown))
          break label74;
        this.available.addFirst(paramE);
      }
      while (true)
      {
        notifyPending(localRouteSpecificPool);
        return;
        label74: paramE.close();
      }
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("Max value may not be negative or zero");
    this.lock.lock();
    try
    {
      this.defaultMaxPerRoute = paramInt;
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void setMaxPerRoute(T paramT, int paramInt)
  {
    if (paramT == null)
      throw new IllegalArgumentException("Route may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Max value may not be negative or zero");
    this.lock.lock();
    try
    {
      this.maxPerRoute.put(paramT, Integer.valueOf(paramInt));
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void setMaxTotal(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("Max value may not be negative or zero");
    this.lock.lock();
    try
    {
      this.maxTotal = paramInt;
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public void shutdown()
    throws IOException
  {
    if (this.isShutDown)
      return;
    this.isShutDown = true;
    this.lock.lock();
    while (true)
    {
      Iterator localIterator2;
      Iterator localIterator3;
      try
      {
        Iterator localIterator1 = this.available.iterator();
        if (!localIterator1.hasNext())
        {
          localIterator2 = this.leased.iterator();
          if (!localIterator2.hasNext())
          {
            localIterator3 = this.routeToPool.values().iterator();
            if (localIterator3.hasNext())
              break label161;
            this.routeToPool.clear();
            this.leased.clear();
            this.available.clear();
          }
        }
        else
        {
          ((PoolEntry)localIterator1.next()).close();
          continue;
        }
      }
      finally
      {
        this.lock.unlock();
      }
      ((PoolEntry)localIterator2.next()).close();
      continue;
      label161: ((RouteSpecificPool)localIterator3.next()).shutdown();
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[leased: ");
    localStringBuilder.append(this.leased);
    localStringBuilder.append("][available: ");
    localStringBuilder.append(this.available);
    localStringBuilder.append("][pending: ");
    localStringBuilder.append(this.pending);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.AbstractConnPool
 * JD-Core Version:    0.6.2
 */