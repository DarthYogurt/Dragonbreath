package my.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import my.apache.http.annotation.GuardedBy;
import my.apache.http.conn.ConnectionPoolTimeoutException;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.impl.conn.IdleConnectionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public abstract class AbstractConnPool
{
  protected IdleConnectionHandler idleConnHandler = new IdleConnectionHandler();
  protected volatile boolean isShutDown;
  protected Set<BasicPoolEntryRef> issuedConnections;

  @GuardedBy("poolLock")
  protected Set<BasicPoolEntry> leasedConnections = new HashSet();
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("poolLock")
  protected int numConnections;
  protected final Lock poolLock = new ReentrantLock();
  protected ReferenceQueue<Object> refQueue;

  protected void closeConnection(OperatedClientConnection paramOperatedClientConnection)
  {
    if (paramOperatedClientConnection != null);
    try
    {
      paramOperatedClientConnection.close();
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  public void closeExpiredConnections()
  {
    this.poolLock.lock();
    try
    {
      this.idleConnHandler.closeExpiredConnections();
      return;
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
    this.poolLock.lock();
    try
    {
      this.idleConnHandler.closeIdleConnections(paramTimeUnit.toMillis(paramLong));
      return;
    }
    finally
    {
      this.poolLock.unlock();
    }
  }

  public abstract void deleteClosedConnections();

  public void enableConnectionGC()
    throws IllegalStateException
  {
  }

  public abstract void freeEntry(BasicPoolEntry paramBasicPoolEntry, boolean paramBoolean, long paramLong, TimeUnit paramTimeUnit);

  public final BasicPoolEntry getEntry(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit)
    throws ConnectionPoolTimeoutException, InterruptedException
  {
    return requestPoolEntry(paramHttpRoute, paramObject).getPoolEntry(paramLong, paramTimeUnit);
  }

  protected abstract void handleLostEntry(HttpRoute paramHttpRoute);

  public void handleReference(Reference<?> paramReference)
  {
  }

  public abstract PoolEntryRequest requestPoolEntry(HttpRoute paramHttpRoute, Object paramObject);

  public void shutdown()
  {
    this.poolLock.lock();
    try
    {
      boolean bool = this.isShutDown;
      if (bool)
        return;
      Iterator localIterator = this.leasedConnections.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this.idleConnHandler.removeAll();
          this.isShutDown = true;
          return;
        }
        BasicPoolEntry localBasicPoolEntry = (BasicPoolEntry)localIterator.next();
        localIterator.remove();
        closeConnection(localBasicPoolEntry.getConnection());
      }
    }
    finally
    {
      this.poolLock.unlock();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.AbstractConnPool
 * JD-Core Version:    0.6.2
 */