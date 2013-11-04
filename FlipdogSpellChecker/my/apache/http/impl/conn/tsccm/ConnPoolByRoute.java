package my.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.ConnectionPoolTimeoutException;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.params.ConnManagerParams;
import my.apache.http.conn.params.ConnPerRoute;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class ConnPoolByRoute extends AbstractConnPool
{
  protected final ConnPerRoute connPerRoute;
  private final long connTTL;
  private final TimeUnit connTTLTimeUnit;
  protected final Queue<BasicPoolEntry> freeConnections;
  protected final Set<BasicPoolEntry> leasedConnections;
  private final Log log = LogFactory.getLog(getClass());
  protected volatile int maxTotalConnections;
  protected volatile int numConnections;
  protected final ClientConnectionOperator operator;
  private final Lock poolLock;
  protected final Map<HttpRoute, RouteSpecificPool> routeToPool;
  protected volatile boolean shutdown;
  protected final Queue<WaitingThread> waitingThreads;

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt)
  {
    this(paramClientConnectionOperator, paramConnPerRoute, paramInt, -1L, TimeUnit.MILLISECONDS);
  }

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, ConnPerRoute paramConnPerRoute, int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramClientConnectionOperator == null)
      throw new IllegalArgumentException("Connection operator may not be null");
    if (paramConnPerRoute == null)
      throw new IllegalArgumentException("Connections per route may not be null");
    this.poolLock = this.poolLock;
    this.leasedConnections = this.leasedConnections;
    this.operator = paramClientConnectionOperator;
    this.connPerRoute = paramConnPerRoute;
    this.maxTotalConnections = paramInt;
    this.freeConnections = createFreeConnQueue();
    this.waitingThreads = createWaitingThreadQueue();
    this.routeToPool = createRouteToPoolMap();
    this.connTTL = paramLong;
    this.connTTLTimeUnit = paramTimeUnit;
  }

  public ConnPoolByRoute(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams)
  {
    this(paramClientConnectionOperator, ConnManagerParams.getMaxConnectionsPerRoute(paramHttpParams), ConnManagerParams.getMaxTotalConnections(paramHttpParams));
  }

  private void closeConnection(BasicPoolEntry paramBasicPoolEntry)
  {
    OperatedClientConnection localOperatedClientConnection = paramBasicPoolEntry.getConnection();
    if (localOperatedClientConnection != null);
    try
    {
      localOperatedClientConnection.close();
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    long l = System.currentTimeMillis();
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (localBasicPoolEntry.isExpired(l))
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Closing connection expired @ " + new Date(localBasicPoolEntry.getExpiry()));
          localIterator.remove();
          deleteEntry(localBasicPoolEntry);
        }
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    if (paramLong < 0L)
      paramLong = 0L;
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    long l = System.currentTimeMillis() - paramTimeUnit.toMillis(paramLong);
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (localBasicPoolEntry.getUpdated() <= l)
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Closing connection last used @ " + new Date(localBasicPoolEntry.getUpdated()));
          localIterator.remove();
          deleteEntry(localBasicPoolEntry);
        }
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected BasicPoolEntry createEntry(RouteSpecificPool paramRouteSpecificPool, ClientConnectionOperator paramClientConnectionOperator)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Creating new connection [" + paramRouteSpecificPool.getRoute() + "]");
    BasicPoolEntry localBasicPoolEntry = new BasicPoolEntry(paramClientConnectionOperator, paramRouteSpecificPool.getRoute(), this.connTTL, this.connTTLTimeUnit);
    this.poolLock.lock();
    try
    {
      paramRouteSpecificPool.createdEntry(localBasicPoolEntry);
      this.numConnections = (1 + this.numConnections);
      this.leasedConnections.add(localBasicPoolEntry);
      return localBasicPoolEntry;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected Queue<BasicPoolEntry> createFreeConnQueue()
  {
    return new LinkedList();
  }

  protected Map<HttpRoute, RouteSpecificPool> createRouteToPoolMap()
  {
    return new HashMap();
  }

  protected Queue<WaitingThread> createWaitingThreadQueue()
  {
    return new LinkedList();
  }

  public void deleteClosedConnections()
  {
    this.poolLock.lock();
    try
    {
      Iterator localIterator = this.freeConnections.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        if (!localBasicPoolEntry.getConnection().isOpen())
        {
          localIterator.remove();
          deleteEntry(localBasicPoolEntry);
        }
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected void deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    if (this.log.isDebugEnabled())
      this.log.debug("Deleting connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]");
    this.poolLock.lock();
    try
    {
      closeConnection(paramBasicPoolEntry);
      RouteSpecificPool localRouteSpecificPool = getRoutePool(localHttpRoute, true);
      localRouteSpecificPool.deleteEntry(paramBasicPoolEntry);
      this.numConnections = (-1 + this.numConnections);
      if (localRouteSpecificPool.isUnused())
        this.routeToPool.remove(localHttpRoute);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected void deleteLeastUsedEntry()
  {
    this.poolLock.lock();
    try
    {
      BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)this.freeConnections.remove();
      if (localBasicPoolEntry != null)
        deleteEntry(localBasicPoolEntry);
      while (true)
      {
        return;
        if (this.log.isDebugEnabled())
          this.log.debug("No free connection to delete");
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void freeEntry(BasicPoolEntry paramBasicPoolEntry, boolean paramBoolean, long paramLong, TimeUnit paramTimeUnit)
  {
    HttpRoute localHttpRoute = paramBasicPoolEntry.getPlannedRoute();
    if (this.log.isDebugEnabled())
      this.log.debug("Releasing connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]");
    this.poolLock.lock();
    try
    {
      if (this.shutdown)
      {
        closeConnection(paramBasicPoolEntry);
        return;
      }
      this.leasedConnections.remove(paramBasicPoolEntry);
      RouteSpecificPool localRouteSpecificPool = getRoutePool(localHttpRoute, true);
      String str;
      if ((paramBoolean) && (localRouteSpecificPool.getCapacity() >= 0))
        if (this.log.isDebugEnabled())
        {
          if (paramLong > 0L)
          {
            str = "for " + paramLong + " " + paramTimeUnit;
            this.log.debug("Pooling connection [" + localHttpRoute + "][" + paramBasicPoolEntry.getState() + "]; keep alive " + str);
          }
        }
        else
        {
          localRouteSpecificPool.freeEntry(paramBasicPoolEntry);
          paramBasicPoolEntry.updateExpiry(paramLong, paramTimeUnit);
          this.freeConnections.add(paramBasicPoolEntry);
        }
      while (true)
      {
        notifyWaitingThread(localRouteSpecificPool);
        return;
        str = "indefinitely";
        break;
        closeConnection(paramBasicPoolEntry);
        localRouteSpecificPool.dropEntry();
        this.numConnections = (-1 + this.numConnections);
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public int getConnectionsInPool()
  {
    this.poolLock.lock();
    try
    {
      int i = this.numConnections;
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, false);
      int i = 0;
      if (localRouteSpecificPool != null)
      {
        int j = localRouteSpecificPool.getEntryCount();
        i = j;
      }
      return i;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected BasicPoolEntry getEntryBlocking(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit, WaitingThreadAborter paramWaitingThreadAborter)
    throws ConnectionPoolTimeoutException, InterruptedException
  {
    boolean bool1 = paramLong < 0L;
    Date localDate = null;
    if (bool1)
      localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
    BasicPoolEntry localBasicPoolEntry = null;
    this.poolLock.lock();
    RouteSpecificPool localRouteSpecificPool2;
    WaitingThread localWaitingThread;
    do
    {
      try
      {
        RouteSpecificPool localRouteSpecificPool1 = getRoutePool(paramHttpRoute, true);
        localRouteSpecificPool2 = localRouteSpecificPool1;
        localWaitingThread = null;
        if (localBasicPoolEntry != null)
          return localBasicPoolEntry;
        if (this.shutdown)
          throw new IllegalStateException("Connection pool shut down");
      }
      finally
      {
        this.poolLock.unlock();
      }
      if (this.log.isDebugEnabled())
        this.log.debug("[" + paramHttpRoute + "] total kept alive: " + this.freeConnections.size() + ", total issued: " + this.leasedConnections.size() + ", total allocated: " + this.numConnections + " out of " + this.maxTotalConnections);
      localBasicPoolEntry = getFreeEntry(localRouteSpecificPool2, paramObject);
    }
    while (localBasicPoolEntry != null);
    if (localRouteSpecificPool2.getCapacity() > 0);
    for (int i = 1; ; i = 0)
      while (true)
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Available capacity: " + localRouteSpecificPool2.getCapacity() + " out of " + localRouteSpecificPool2.getMaxEntries() + " [" + paramHttpRoute + "][" + paramObject + "]");
        if ((i != 0) && (this.numConnections < this.maxTotalConnections))
        {
          localBasicPoolEntry = createEntry(localRouteSpecificPool2, this.operator);
          break;
        }
        if ((i != 0) && (!this.freeConnections.isEmpty()))
        {
          deleteLeastUsedEntry();
          localRouteSpecificPool2 = getRoutePool(paramHttpRoute, true);
          localBasicPoolEntry = createEntry(localRouteSpecificPool2, this.operator);
          break;
        }
        if (this.log.isDebugEnabled())
          this.log.debug("Need to wait for connection [" + paramHttpRoute + "][" + paramObject + "]");
        if (localWaitingThread == null)
        {
          localWaitingThread = newWaitingThread(this.poolLock.newCondition(), localRouteSpecificPool2);
          paramWaitingThreadAborter.setWaitingThread(localWaitingThread);
        }
        try
        {
          localRouteSpecificPool2.queueThread(localWaitingThread);
          this.waitingThreads.add(localWaitingThread);
          boolean bool2 = localWaitingThread.await(localDate);
          localRouteSpecificPool2.removeThread(localWaitingThread);
          this.waitingThreads.remove(localWaitingThread);
          if ((bool2) || (localDate == null) || (localDate.getTime() > System.currentTimeMillis()))
            break;
          throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
        finally
        {
          localRouteSpecificPool2.removeThread(localWaitingThread);
          this.waitingThreads.remove(localWaitingThread);
        }
      }
  }

  protected BasicPoolEntry getFreeEntry(RouteSpecificPool paramRouteSpecificPool, Object paramObject)
  {
    BasicPoolEntry localBasicPoolEntry = null;
    this.poolLock.lock();
    int i = 0;
    while (true)
    {
      if (i != 0)
      {
        this.poolLock.unlock();
        return localBasicPoolEntry;
      }
      try
      {
        localBasicPoolEntry = paramRouteSpecificPool.allocEntry(paramObject);
        if (localBasicPoolEntry == null)
          break label226;
        if (this.log.isDebugEnabled())
          this.log.debug("Getting free connection [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
        this.freeConnections.remove(localBasicPoolEntry);
        if (localBasicPoolEntry.isExpired(System.currentTimeMillis()))
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Closing expired free connection [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
          closeConnection(localBasicPoolEntry);
          paramRouteSpecificPool.dropEntry();
          this.numConnections = (-1 + this.numConnections);
          continue;
        }
      }
      finally
      {
        this.poolLock.unlock();
      }
      this.leasedConnections.add(localBasicPoolEntry);
      i = 1;
      continue;
      label226: i = 1;
      if (this.log.isDebugEnabled())
        this.log.debug("No free connections [" + paramRouteSpecificPool.getRoute() + "][" + paramObject + "]");
    }
  }

  protected Lock getLock()
  {
    return this.poolLock;
  }

  public int getMaxTotalConnections()
  {
    return this.maxTotalConnections;
  }

  protected RouteSpecificPool getRoutePool(HttpRoute paramHttpRoute, boolean paramBoolean)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = (RouteSpecificPool)this.routeToPool.get(paramHttpRoute);
      if ((localRouteSpecificPool == null) && (paramBoolean))
      {
        localRouteSpecificPool = newRouteSpecificPool(paramHttpRoute);
        this.routeToPool.put(paramHttpRoute, localRouteSpecificPool);
      }
      return localRouteSpecificPool;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected void handleLostEntry(HttpRoute paramHttpRoute)
  {
    this.poolLock.lock();
    try
    {
      RouteSpecificPool localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
      localRouteSpecificPool.dropEntry();
      if (localRouteSpecificPool.isUnused())
        this.routeToPool.remove(paramHttpRoute);
      this.numConnections = (-1 + this.numConnections);
      notifyWaitingThread(localRouteSpecificPool);
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  protected RouteSpecificPool newRouteSpecificPool(HttpRoute paramHttpRoute)
  {
    return new RouteSpecificPool(paramHttpRoute, this.connPerRoute);
  }

  protected WaitingThread newWaitingThread(Condition paramCondition, RouteSpecificPool paramRouteSpecificPool)
  {
    return new WaitingThread(paramCondition, paramRouteSpecificPool);
  }

  protected void notifyWaitingThread(RouteSpecificPool paramRouteSpecificPool)
  {
    this.poolLock.lock();
    if (paramRouteSpecificPool != null);
    try
    {
      WaitingThread localWaitingThread;
      if (paramRouteSpecificPool.hasThread())
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Notifying thread waiting on pool [" + paramRouteSpecificPool.getRoute() + "]");
        localWaitingThread = paramRouteSpecificPool.nextThread();
      }
      while (true)
      {
        if (localWaitingThread != null)
          localWaitingThread.wakeup();
        return;
        if (!this.waitingThreads.isEmpty())
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Notifying thread waiting on any pool");
          localWaitingThread = (WaitingThread)this.waitingThreads.remove();
        }
        else
        {
          boolean bool = this.log.isDebugEnabled();
          localWaitingThread = null;
          if (bool)
          {
            this.log.debug("Notifying no-one, there are no waiting threads");
            localWaitingThread = null;
          }
        }
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public PoolEntryRequest requestPoolEntry(final HttpRoute paramHttpRoute, final Object paramObject)
  {
    return new PoolEntryRequest()
    {
      public void abortRequest()
      {
        ConnPoolByRoute.this.poolLock.lock();
        try
        {
          this.val$aborter.abort();
          return;
        }
        finally
        {
          ConnPoolByRoute.this.poolLock.unlock();
        }
      }

      public BasicPoolEntry getPoolEntry(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        return ConnPoolByRoute.this.getEntryBlocking(paramHttpRoute, paramObject, paramAnonymousLong, paramAnonymousTimeUnit, this.val$aborter);
      }
    };
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.poolLock.lock();
    try
    {
      this.maxTotalConnections = paramInt;
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public void shutdown()
  {
    this.poolLock.lock();
    while (true)
    {
      Iterator localIterator2;
      Iterator localIterator3;
      try
      {
        boolean bool = this.shutdown;
        if (bool)
          return;
        this.shutdown = true;
        Iterator localIterator1 = this.leasedConnections.iterator();
        if (!localIterator1.hasNext())
        {
          localIterator2 = this.freeConnections.iterator();
          if (!localIterator2.hasNext())
          {
            localIterator3 = this.waitingThreads.iterator();
            if (localIterator3.hasNext())
              break label240;
            this.routeToPool.clear();
          }
        }
        else
        {
          BasicPoolEntry localBasicPoolEntry1 = (BasicPoolEntry)localIterator1.next();
          localIterator1.remove();
          closeConnection(localBasicPoolEntry1);
          continue;
        }
      }
      finally
      {
        this.poolLock.unlock();
      }
      BasicPoolEntry localBasicPoolEntry2 = (BasicPoolEntry)localIterator2.next();
      localIterator2.remove();
      if (this.log.isDebugEnabled())
        this.log.debug("Closing connection [" + localBasicPoolEntry2.getPlannedRoute() + "][" + localBasicPoolEntry2.getState() + "]");
      closeConnection(localBasicPoolEntry2);
      continue;
      label240: WaitingThread localWaitingThread = (WaitingThread)localIterator3.next();
      localIterator3.remove();
      localWaitingThread.wakeup();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.ConnPoolByRoute
 * JD-Core Version:    0.6.2
 */