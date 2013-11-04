package my.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.ClientConnectionRequest;
import my.apache.http.conn.ConnectionPoolTimeoutException;
import my.apache.http.conn.ManagedClientConnection;
import my.apache.http.conn.params.ConnPerRouteBean;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.impl.conn.DefaultClientConnectionOperator;
import my.apache.http.impl.conn.SchemeRegistryFactory;
import my.apache.http.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@ThreadSafe
public class ThreadSafeClientConnManager
  implements ClientConnectionManager
{
  protected final ClientConnectionOperator connOperator;
  protected final ConnPerRouteBean connPerRoute;
  protected final AbstractConnPool connectionPool;
  private final Log log;
  protected final ConnPoolByRoute pool;
  protected final SchemeRegistry schemeRegistry;

  public ThreadSafeClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS);
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit)
  {
    this(paramSchemeRegistry, paramLong, paramTimeUnit, new ConnPerRouteBean());
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit, ConnPerRouteBean paramConnPerRouteBean)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = paramConnPerRouteBean;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = createConnectionPool(paramLong, paramTimeUnit);
    this.connectionPool = this.pool;
  }

  public ThreadSafeClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = new ConnPerRouteBean();
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = ((ConnPoolByRoute)createConnectionPool(paramHttpParams));
    this.connectionPool = this.pool;
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    this.pool.closeExpiredConnections();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Closing connections idle longer than " + paramLong + " " + paramTimeUnit);
    this.pool.closeIdleConnections(paramLong, paramTimeUnit);
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  protected AbstractConnPool createConnectionPool(HttpParams paramHttpParams)
  {
    return new ConnPoolByRoute(this.connOperator, paramHttpParams);
  }

  protected ConnPoolByRoute createConnectionPool(long paramLong, TimeUnit paramTimeUnit)
  {
    return new ConnPoolByRoute(this.connOperator, this.connPerRoute, 20, paramLong, paramTimeUnit);
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

  public int getConnectionsInPool()
  {
    return this.pool.getConnectionsInPool();
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    return this.pool.getConnectionsInPool(paramHttpRoute);
  }

  public int getDefaultMaxPerRoute()
  {
    return this.connPerRoute.getDefaultMaxPerRoute();
  }

  public int getMaxForRoute(HttpRoute paramHttpRoute)
  {
    return this.connPerRoute.getMaxForRoute(paramHttpRoute);
  }

  public int getMaxTotal()
  {
    return this.pool.getMaxTotalConnections();
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 176
    //   4: ifne +13 -> 17
    //   7: new 52	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 178
    //   13: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_1
    //   18: checkcast 176	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter
    //   21: astore 5
    //   23: aload 5
    //   25: invokevirtual 182	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lmy/apache/http/impl/conn/AbstractPoolEntry;
    //   28: ifnull +22 -> 50
    //   31: aload 5
    //   33: invokevirtual 186	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getManager	()Lmy/apache/http/conn/ClientConnectionManager;
    //   36: aload_0
    //   37: if_acmpeq +13 -> 50
    //   40: new 52	java/lang/IllegalArgumentException
    //   43: dup
    //   44: ldc 188
    //   46: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: aload 5
    //   52: monitorenter
    //   53: aload 5
    //   55: invokevirtual 182	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lmy/apache/http/impl/conn/AbstractPoolEntry;
    //   58: checkcast 190	my/apache/http/impl/conn/tsccm/BasicPoolEntry
    //   61: astore 7
    //   63: aload 7
    //   65: ifnonnull +7 -> 72
    //   68: aload 5
    //   70: monitorexit
    //   71: return
    //   72: aload 5
    //   74: invokevirtual 193	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isOpen	()Z
    //   77: ifeq +16 -> 93
    //   80: aload 5
    //   82: invokevirtual 196	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   85: ifne +8 -> 93
    //   88: aload 5
    //   90: invokevirtual 197	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:shutdown	()V
    //   93: aload 5
    //   95: invokevirtual 196	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   98: istore 12
    //   100: aload_0
    //   101: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   104: invokeinterface 112 1 0
    //   109: ifeq +19 -> 128
    //   112: iload 12
    //   114: ifeq +216 -> 330
    //   117: aload_0
    //   118: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   121: ldc 199
    //   123: invokeinterface 104 2 0
    //   128: aload 5
    //   130: invokevirtual 202	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   133: aload_0
    //   134: getfield 85	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lmy/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   137: aload 7
    //   139: iload 12
    //   141: lload_2
    //   142: aload 4
    //   144: invokevirtual 206	my/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lmy/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   147: aload 5
    //   149: monitorexit
    //   150: return
    //   151: astore 6
    //   153: aload 5
    //   155: monitorexit
    //   156: aload 6
    //   158: athrow
    //   159: astore 10
    //   161: aload_0
    //   162: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   165: invokeinterface 112 1 0
    //   170: ifeq +16 -> 186
    //   173: aload_0
    //   174: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   177: ldc 208
    //   179: aload 10
    //   181: invokeinterface 211 3 0
    //   186: aload 5
    //   188: invokevirtual 196	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   191: istore 11
    //   193: aload_0
    //   194: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   197: invokeinterface 112 1 0
    //   202: ifeq +19 -> 221
    //   205: iload 11
    //   207: ifeq +36 -> 243
    //   210: aload_0
    //   211: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   214: ldc 199
    //   216: invokeinterface 104 2 0
    //   221: aload 5
    //   223: invokevirtual 202	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   226: aload_0
    //   227: getfield 85	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lmy/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   230: aload 7
    //   232: iload 11
    //   234: lload_2
    //   235: aload 4
    //   237: invokevirtual 206	my/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lmy/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   240: goto -93 -> 147
    //   243: aload_0
    //   244: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   247: ldc 213
    //   249: invokeinterface 104 2 0
    //   254: goto -33 -> 221
    //   257: astore 8
    //   259: aload 5
    //   261: invokevirtual 196	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   264: istore 9
    //   266: aload_0
    //   267: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   270: invokeinterface 112 1 0
    //   275: ifeq +19 -> 294
    //   278: iload 9
    //   280: ifeq +36 -> 316
    //   283: aload_0
    //   284: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   287: ldc 199
    //   289: invokeinterface 104 2 0
    //   294: aload 5
    //   296: invokevirtual 202	my/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   299: aload_0
    //   300: getfield 85	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lmy/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   303: aload 7
    //   305: iload 9
    //   307: lload_2
    //   308: aload 4
    //   310: invokevirtual 206	my/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lmy/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   313: aload 8
    //   315: athrow
    //   316: aload_0
    //   317: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   320: ldc 213
    //   322: invokeinterface 104 2 0
    //   327: goto -33 -> 294
    //   330: aload_0
    //   331: getfield 69	my/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   334: ldc 213
    //   336: invokeinterface 104 2 0
    //   341: goto -213 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   53	63	151	finally
    //   68	71	151	finally
    //   93	112	151	finally
    //   117	128	151	finally
    //   128	147	151	finally
    //   147	150	151	finally
    //   153	156	151	finally
    //   186	205	151	finally
    //   210	221	151	finally
    //   221	240	151	finally
    //   243	254	151	finally
    //   259	278	151	finally
    //   283	294	151	finally
    //   294	316	151	finally
    //   316	327	151	finally
    //   330	341	151	finally
    //   72	93	159	java/io/IOException
    //   72	93	257	finally
    //   161	186	257	finally
  }

  public ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
        this.val$poolRequest.abortRequest();
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        if (paramHttpRoute == null)
          throw new IllegalArgumentException("Route may not be null.");
        if (ThreadSafeClientConnManager.this.log.isDebugEnabled())
          ThreadSafeClientConnManager.this.log.debug("Get connection: " + paramHttpRoute + ", timeout = " + paramAnonymousLong);
        BasicPoolEntry localBasicPoolEntry = this.val$poolRequest.getPoolEntry(paramAnonymousLong, paramAnonymousTimeUnit);
        return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, localBasicPoolEntry);
      }
    };
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    this.connPerRoute.setDefaultMaxPerRoute(paramInt);
  }

  public void setMaxForRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    this.connPerRoute.setMaxForRoute(paramHttpRoute, paramInt);
  }

  public void setMaxTotal(int paramInt)
  {
    this.pool.setMaxTotalConnections(paramInt);
  }

  public void shutdown()
  {
    this.log.debug("Shutting down");
    this.pool.shutdown();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
 * JD-Core Version:    0.6.2
 */