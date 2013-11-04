package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import my.apache.http.HttpClientConnection;
import my.apache.http.annotation.GuardedBy;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.ClientConnectionRequest;
import my.apache.http.conn.ManagedClientConnection;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.RouteTracker;
import my.apache.http.conn.scheme.SchemeRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ThreadSafe
public class BasicClientConnectionManager
  implements ClientConnectionManager
{
  private static final AtomicLong COUNTER = new AtomicLong();
  public static final String MISUSE_MESSAGE = "Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";

  @GuardedBy("this")
  private ManagedClientConnectionImpl conn;
  private final ClientConnectionOperator connOperator;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  private HttpPoolEntry poolEntry;
  private final SchemeRegistry schemeRegistry;

  @GuardedBy("this")
  private volatile boolean shutdown;

  public BasicClientConnectionManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public BasicClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
  }

  private void assertNotShutdown()
  {
    if (this.shutdown)
      throw new IllegalStateException("Connection manager has been shut down");
  }

  private void shutdownConnection(HttpClientConnection paramHttpClientConnection)
  {
    try
    {
      paramHttpClientConnection.shutdown();
      return;
    }
    catch (IOException localIOException)
    {
      while (!this.log.isDebugEnabled());
      this.log.debug("I/O exception shutting down connection", localIOException);
    }
  }

  public void closeExpiredConnections()
  {
    try
    {
      assertNotShutdown();
      long l = System.currentTimeMillis();
      if ((this.poolEntry != null) && (this.poolEntry.isExpired(l)))
      {
        this.poolEntry.close();
        this.poolEntry.getTracker().reset();
      }
      return;
    }
    finally
    {
    }
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    try
    {
      assertNotShutdown();
      long l1 = paramTimeUnit.toMillis(paramLong);
      if (l1 < 0L)
        l1 = 0L;
      long l2 = System.currentTimeMillis() - l1;
      if ((this.poolEntry != null) && (this.poolEntry.getUpdated() <= l2))
      {
        this.poolEntry.close();
        this.poolEntry.getTracker().reset();
      }
      return;
    }
    finally
    {
    }
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      shutdown();
      return;
    }
    finally
    {
      super.finalize();
    }
  }

  ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null.");
    try
    {
      assertNotShutdown();
      if (this.log.isDebugEnabled())
        this.log.debug("Get connection for route " + paramHttpRoute);
      if (this.conn != null)
        throw new IllegalStateException("Invalid use of BasicClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
    }
    finally
    {
    }
    if ((this.poolEntry != null) && (!this.poolEntry.getPlannedRoute().equals(paramHttpRoute)))
    {
      this.poolEntry.close();
      this.poolEntry = null;
    }
    if (this.poolEntry == null)
    {
      String str = Long.toString(COUNTER.getAndIncrement());
      OperatedClientConnection localOperatedClientConnection = this.connOperator.createConnection();
      this.poolEntry = new HttpPoolEntry(this.log, str, paramHttpRoute, localOperatedClientConnection, 0L, TimeUnit.MILLISECONDS);
    }
    long l = System.currentTimeMillis();
    if (this.poolEntry.isExpired(l))
    {
      this.poolEntry.close();
      this.poolEntry.getTracker().reset();
    }
    this.conn = new ManagedClientConnectionImpl(this, this.connOperator, this.poolEntry);
    ManagedClientConnectionImpl localManagedClientConnectionImpl = this.conn;
    return localManagedClientConnectionImpl;
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    if (!(paramManagedClientConnection instanceof ManagedClientConnectionImpl))
      throw new IllegalArgumentException("Connection class mismatch, connection not obtained from this manager");
    synchronized ((ManagedClientConnectionImpl)paramManagedClientConnection)
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Releasing connection " + paramManagedClientConnection);
      if (???.getPoolEntry() == null)
        return;
      ClientConnectionManager localClientConnectionManager = ???.getManager();
      if ((localClientConnectionManager != null) && (localClientConnectionManager != this))
        throw new IllegalStateException("Connection not obtained from this manager");
    }
    try
    {
      if (this.shutdown)
      {
        shutdownConnection(???);
        return;
      }
      try
      {
        if ((???.isOpen()) && (!???.isMarkedReusable()))
          shutdownConnection(???);
        TimeUnit localTimeUnit;
        if (???.isMarkedReusable())
        {
          HttpPoolEntry localHttpPoolEntry = this.poolEntry;
          if (paramTimeUnit == null)
            break label289;
          localTimeUnit = paramTimeUnit;
          localHttpPoolEntry.updateExpiry(paramLong, localTimeUnit);
          if (this.log.isDebugEnabled())
            if (paramLong <= 0L)
              break label297;
        }
        label289: label297: for (String str = "for " + paramLong + " " + paramTimeUnit; ; str = "indefinitely")
        {
          this.log.debug("Connection can be kept alive " + str);
          ???.detach();
          this.conn = null;
          if (this.poolEntry.isClosed())
            this.poolEntry = null;
          return;
          localTimeUnit = TimeUnit.MILLISECONDS;
          break;
        }
      }
      finally
      {
        ???.detach();
        this.conn = null;
        if (this.poolEntry.isClosed())
          this.poolEntry = null;
      }
    }
    finally
    {
    }
  }

  public final ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, final Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
      {
        return BasicClientConnectionManager.this.getConnection(paramHttpRoute, paramObject);
      }
    };
  }

  public void shutdown()
  {
    try
    {
      this.shutdown = true;
      try
      {
        if (this.poolEntry != null)
          this.poolEntry.close();
        this.poolEntry = null;
        this.conn = null;
        return;
      }
      finally
      {
        localObject2 = finally;
        this.poolEntry = null;
        this.conn = null;
        throw localObject2;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.BasicClientConnectionManager
 * JD-Core Version:    0.6.2
 */