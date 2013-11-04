package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.ClientConnectionRequest;
import my.apache.http.conn.ConnectionPoolTimeoutException;
import my.apache.http.conn.DnsResolver;
import my.apache.http.conn.ManagedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.pool.ConnPoolControl;
import my.apache.http.pool.PoolStats;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ThreadSafe
public class PoolingClientConnectionManager
  implements ClientConnectionManager, ConnPoolControl<HttpRoute>
{
  private final DnsResolver dnsResolver;
  private final Log log = LogFactory.getLog(getClass());
  private final ClientConnectionOperator operator;
  private final HttpConnPool pool;
  private final SchemeRegistry schemeRegistry;

  public PoolingClientConnectionManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS);
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit)
  {
    this(paramSchemeRegistry, paramLong, paramTimeUnit, new SystemDefaultDnsResolver());
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit, DnsResolver paramDnsResolver)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    if (paramDnsResolver == null)
      throw new IllegalArgumentException("DNS resolver may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.dnsResolver = paramDnsResolver;
    this.operator = createConnectionOperator(paramSchemeRegistry);
    this.pool = new HttpConnPool(this.log, this.operator, 2, 20, paramLong, paramTimeUnit);
  }

  public PoolingClientConnectionManager(SchemeRegistry paramSchemeRegistry, DnsResolver paramDnsResolver)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS, paramDnsResolver);
  }

  private String format(HttpRoute paramHttpRoute, Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[route: ").append(paramHttpRoute).append("]");
    if (paramObject != null)
      localStringBuilder.append("[state: ").append(paramObject).append("]");
    return localStringBuilder.toString();
  }

  private String format(HttpPoolEntry paramHttpPoolEntry)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id: ").append(paramHttpPoolEntry.getId()).append("]");
    localStringBuilder.append("[route: ").append(paramHttpPoolEntry.getRoute()).append("]");
    Object localObject = paramHttpPoolEntry.getState();
    if (localObject != null)
      localStringBuilder.append("[state: ").append(localObject).append("]");
    return localStringBuilder.toString();
  }

  private String formatStats(HttpRoute paramHttpRoute)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    PoolStats localPoolStats1 = this.pool.getTotalStats();
    PoolStats localPoolStats2 = this.pool.getStats(paramHttpRoute);
    localStringBuilder.append("[total kept alive: ").append(localPoolStats1.getAvailable()).append("; ");
    localStringBuilder.append("route allocated: ").append(localPoolStats2.getLeased() + localPoolStats2.getAvailable());
    localStringBuilder.append(" of ").append(localPoolStats2.getMax()).append("; ");
    localStringBuilder.append("total allocated: ").append(localPoolStats1.getLeased() + localPoolStats1.getAvailable());
    localStringBuilder.append(" of ").append(localPoolStats1.getMax()).append("]");
    return localStringBuilder.toString();
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    this.pool.closeExpired();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    this.pool.closeIdle(paramLong, paramTimeUnit);
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry, this.dnsResolver);
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

  public int getDefaultMaxPerRoute()
  {
    return this.pool.getDefaultMaxPerRoute();
  }

  public int getMaxPerRoute(HttpRoute paramHttpRoute)
  {
    return this.pool.getMaxPerRoute(paramHttpRoute);
  }

  public int getMaxTotal()
  {
    return this.pool.getMaxTotal();
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  public PoolStats getStats(HttpRoute paramHttpRoute)
  {
    return this.pool.getStats(paramHttpRoute);
  }

  public PoolStats getTotalStats()
  {
    return this.pool.getTotalStats();
  }

  ManagedClientConnection leaseConnection(Future<HttpPoolEntry> paramFuture, long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ConnectionPoolTimeoutException
  {
    HttpPoolEntry localHttpPoolEntry;
    try
    {
      localHttpPoolEntry = (HttpPoolEntry)paramFuture.get(paramLong, paramTimeUnit);
      if ((localHttpPoolEntry == null) || (paramFuture.isCancelled()))
        throw new InterruptedException();
    }
    catch (ExecutionException localExecutionException)
    {
      Object localObject = localExecutionException.getCause();
      if (localObject == null)
        localObject = localExecutionException;
      this.log.error("Unexpected exception leasing connection from pool", (Throwable)localObject);
      throw new InterruptedException();
      if (localHttpPoolEntry.getConnection() == null)
        throw new IllegalStateException("Pool entry with no connection");
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
    }
    if (this.log.isDebugEnabled())
      this.log.debug("Connection leased: " + format(localHttpPoolEntry) + formatStats((HttpRoute)localHttpPoolEntry.getRoute()));
    ManagedClientConnectionImpl localManagedClientConnectionImpl = new ManagedClientConnectionImpl(this, this.operator, localHttpPoolEntry);
    return localManagedClientConnectionImpl;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 270
    //   4: ifne +14 -> 18
    //   7: new 64	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc_w 279
    //   14: invokespecial 69	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   17: athrow
    //   18: aload_1
    //   19: checkcast 270	my/apache/http/impl/conn/ManagedClientConnectionImpl
    //   22: astore 5
    //   24: aload 5
    //   26: invokevirtual 283	my/apache/http/impl/conn/ManagedClientConnectionImpl:getManager	()Lmy/apache/http/conn/ClientConnectionManager;
    //   29: aload_0
    //   30: if_acmpeq +14 -> 44
    //   33: new 256	java/lang/IllegalStateException
    //   36: dup
    //   37: ldc_w 285
    //   40: invokespecial 259	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   43: athrow
    //   44: aload 5
    //   46: monitorenter
    //   47: aload 5
    //   49: invokevirtual 289	my/apache/http/impl/conn/ManagedClientConnectionImpl:detach	()Lmy/apache/http/impl/conn/HttpPoolEntry;
    //   52: astore 7
    //   54: aload 7
    //   56: ifnonnull +7 -> 63
    //   59: aload 5
    //   61: monitorexit
    //   62: return
    //   63: aload 5
    //   65: invokevirtual 292	my/apache/http/impl/conn/ManagedClientConnectionImpl:isOpen	()Z
    //   68: ifeq +20 -> 88
    //   71: aload 5
    //   73: invokevirtual 295	my/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   76: istore 11
    //   78: iload 11
    //   80: ifne +8 -> 88
    //   83: aload 5
    //   85: invokevirtual 296	my/apache/http/impl/conn/ManagedClientConnectionImpl:shutdown	()V
    //   88: aload 5
    //   90: invokevirtual 295	my/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   93: ifeq +109 -> 202
    //   96: aload 4
    //   98: ifnull +238 -> 336
    //   101: aload 4
    //   103: astore 9
    //   105: aload 7
    //   107: lload_2
    //   108: aload 9
    //   110: invokevirtual 299	my/apache/http/impl/conn/HttpPoolEntry:updateExpiry	(JLjava/util/concurrent/TimeUnit;)V
    //   113: aload_0
    //   114: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   117: invokeinterface 179 1 0
    //   122: ifeq +80 -> 202
    //   125: lload_2
    //   126: lconst_0
    //   127: lcmp
    //   128: ifle +216 -> 344
    //   131: new 93	java/lang/StringBuilder
    //   134: dup
    //   135: ldc_w 301
    //   138: invokespecial 182	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   141: lload_2
    //   142: invokevirtual 185	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   145: ldc 187
    //   147: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 4
    //   152: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   155: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: astore 10
    //   160: aload_0
    //   161: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   164: new 93	java/lang/StringBuilder
    //   167: dup
    //   168: ldc_w 303
    //   171: invokespecial 182	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   174: aload_0
    //   175: aload 7
    //   177: invokespecial 266	my/apache/http/impl/conn/PoolingClientConnectionManager:format	(Lmy/apache/http/impl/conn/HttpPoolEntry;)Ljava/lang/String;
    //   180: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: ldc_w 305
    //   186: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload 10
    //   191: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokeinterface 170 2 0
    //   202: aload_0
    //   203: getfield 88	my/apache/http/impl/conn/PoolingClientConnectionManager:pool	Lmy/apache/http/impl/conn/HttpConnPool;
    //   206: aload 7
    //   208: aload 5
    //   210: invokevirtual 295	my/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   213: invokevirtual 309	my/apache/http/impl/conn/HttpConnPool:release	(Lmy/apache/http/pool/PoolEntry;Z)V
    //   216: aload_0
    //   217: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   220: invokeinterface 179 1 0
    //   225: ifeq +49 -> 274
    //   228: aload_0
    //   229: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   232: new 93	java/lang/StringBuilder
    //   235: dup
    //   236: ldc_w 311
    //   239: invokespecial 182	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   242: aload_0
    //   243: aload 7
    //   245: invokespecial 266	my/apache/http/impl/conn/PoolingClientConnectionManager:format	(Lmy/apache/http/impl/conn/HttpPoolEntry;)Ljava/lang/String;
    //   248: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload_0
    //   252: aload 7
    //   254: invokevirtual 123	my/apache/http/impl/conn/HttpPoolEntry:getRoute	()Ljava/lang/Object;
    //   257: checkcast 209	my/apache/http/conn/routing/HttpRoute
    //   260: invokespecial 268	my/apache/http/impl/conn/PoolingClientConnectionManager:formatStats	(Lmy/apache/http/conn/routing/HttpRoute;)Ljava/lang/String;
    //   263: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   269: invokeinterface 170 2 0
    //   274: aload 5
    //   276: monitorexit
    //   277: return
    //   278: astore 6
    //   280: aload 5
    //   282: monitorexit
    //   283: aload 6
    //   285: athrow
    //   286: astore 12
    //   288: aload_0
    //   289: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   292: invokeinterface 179 1 0
    //   297: ifeq -209 -> 88
    //   300: aload_0
    //   301: getfield 62	my/apache/http/impl/conn/PoolingClientConnectionManager:log	Lorg/apache/commons/logging/Log;
    //   304: ldc_w 313
    //   307: aload 12
    //   309: invokeinterface 315 3 0
    //   314: goto -226 -> 88
    //   317: astore 8
    //   319: aload_0
    //   320: getfield 88	my/apache/http/impl/conn/PoolingClientConnectionManager:pool	Lmy/apache/http/impl/conn/HttpConnPool;
    //   323: aload 7
    //   325: aload 5
    //   327: invokevirtual 295	my/apache/http/impl/conn/ManagedClientConnectionImpl:isMarkedReusable	()Z
    //   330: invokevirtual 309	my/apache/http/impl/conn/HttpConnPool:release	(Lmy/apache/http/pool/PoolEntry;Z)V
    //   333: aload 8
    //   335: athrow
    //   336: getstatic 39	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   339: astore 9
    //   341: goto -236 -> 105
    //   344: ldc_w 317
    //   347: astore 10
    //   349: goto -189 -> 160
    //
    // Exception table:
    //   from	to	target	type
    //   47	54	278	finally
    //   59	62	278	finally
    //   202	274	278	finally
    //   274	277	278	finally
    //   280	283	278	finally
    //   319	336	278	finally
    //   83	88	286	java/io/IOException
    //   63	78	317	finally
    //   83	88	317	finally
    //   88	96	317	finally
    //   105	125	317	finally
    //   131	160	317	finally
    //   160	202	317	finally
    //   288	314	317	finally
    //   336	341	317	finally
  }

  public ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null");
    if (this.log.isDebugEnabled())
      this.log.debug("Connection request: " + format(paramHttpRoute, paramObject) + formatStats(paramHttpRoute));
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
        this.val$future.cancel(true);
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        return PoolingClientConnectionManager.this.leaseConnection(this.val$future, paramAnonymousLong, paramAnonymousTimeUnit);
      }
    };
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    this.pool.setDefaultMaxPerRoute(paramInt);
  }

  public void setMaxPerRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    this.pool.setMaxPerRoute(paramHttpRoute, paramInt);
  }

  public void setMaxTotal(int paramInt)
  {
    this.pool.setMaxTotal(paramInt);
  }

  public void shutdown()
  {
    this.log.debug("Connection manager is shutting down");
    try
    {
      this.pool.shutdown();
      this.log.debug("Connection manager shut down");
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        this.log.debug("I/O exception shutting down connection manager", localIOException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.PoolingClientConnectionManager
 * JD-Core Version:    0.6.2
 */