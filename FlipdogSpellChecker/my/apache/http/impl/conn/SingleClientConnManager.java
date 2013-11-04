package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
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
import my.apache.http.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@ThreadSafe
public class SingleClientConnManager
  implements ClientConnectionManager
{
  public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
  protected final boolean alwaysShutDown;
  protected final ClientConnectionOperator connOperator;

  @GuardedBy("this")
  protected volatile long connectionExpiresTime;
  protected volatile boolean isShutDown;

  @GuardedBy("this")
  protected volatile long lastReleaseTime;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  protected volatile ConnAdapter managedConn;
  protected final SchemeRegistry schemeRegistry;

  @GuardedBy("this")
  protected volatile PoolEntry uniquePoolEntry;

  public SingleClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public SingleClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.uniquePoolEntry = new PoolEntry();
    this.managedConn = null;
    this.lastReleaseTime = -1L;
    this.alwaysShutDown = false;
    this.isShutDown = false;
  }

  public SingleClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry);
  }

  protected final void assertStillUp()
    throws IllegalStateException
  {
    if (this.isShutDown)
      throw new IllegalStateException("Manager is shut down.");
  }

  public void closeExpiredConnections()
  {
    long l = this.connectionExpiresTime;
    if (System.currentTimeMillis() >= l)
      closeIdleConnections(0L, TimeUnit.MILLISECONDS);
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    assertStillUp();
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit must not be null.");
    try
    {
      if ((this.managedConn == null) && (this.uniquePoolEntry.connection.isOpen()))
      {
        long l1 = System.currentTimeMillis() - paramTimeUnit.toMillis(paramLong);
        long l2 = this.lastReleaseTime;
        if (l2 > l1);
      }
      try
      {
        this.uniquePoolEntry.close();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.log.debug("Problem closing idle connection.", localIOException);
      }
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

  public ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("Route may not be null.");
    assertStillUp();
    if (this.log.isDebugEnabled())
      this.log.debug("Get connection for route " + paramHttpRoute);
    try
    {
      if (this.managedConn != null)
        throw new IllegalStateException("Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
    }
    finally
    {
    }
    int i = 0;
    closeExpiredConnections();
    int j;
    if (this.uniquePoolEntry.connection.isOpen())
    {
      RouteTracker localRouteTracker = this.uniquePoolEntry.tracker;
      if (localRouteTracker != null)
      {
        boolean bool = localRouteTracker.toRoute().equals(paramHttpRoute);
        if (bool)
          j = 0;
      }
    }
    while (true)
    {
      if (j != 0)
        i = 1;
      try
      {
        this.uniquePoolEntry.shutdown();
        if (i != 0)
          this.uniquePoolEntry = new PoolEntry();
        this.managedConn = new ConnAdapter(this.uniquePoolEntry, paramHttpRoute);
        ConnAdapter localConnAdapter = this.managedConn;
        return localConnAdapter;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.log.debug("Problem shutting down connection.", localIOException);
      }
      j = 1;
      i = 0;
      continue;
      i = 1;
      j = 0;
    }
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 118	my/apache/http/impl/conn/SingleClientConnManager:assertStillUp	()V
    //   4: aload_1
    //   5: instanceof 200
    //   8: ifne +13 -> 21
    //   11: new 58	java/lang/IllegalArgumentException
    //   14: dup
    //   15: ldc 210
    //   17: invokespecial 63	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   20: athrow
    //   21: aload_0
    //   22: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   25: invokeinterface 163 1 0
    //   30: ifeq +28 -> 58
    //   33: aload_0
    //   34: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   37: new 165	java/lang/StringBuilder
    //   40: dup
    //   41: ldc 212
    //   43: invokespecial 168	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   46: aload_1
    //   47: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 176	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokeinterface 179 2 0
    //   58: aload_1
    //   59: checkcast 200	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter
    //   62: astore 5
    //   64: aload 5
    //   66: monitorenter
    //   67: aload 5
    //   69: getfield 216	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:poolEntry	Lmy/apache/http/impl/conn/AbstractPoolEntry;
    //   72: ifnonnull +7 -> 79
    //   75: aload 5
    //   77: monitorexit
    //   78: return
    //   79: aload 5
    //   81: invokevirtual 220	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:getManager	()Lmy/apache/http/conn/ClientConnectionManager;
    //   84: astore 7
    //   86: aload 7
    //   88: ifnull +27 -> 115
    //   91: aload 7
    //   93: aload_0
    //   94: if_acmpeq +21 -> 115
    //   97: new 58	java/lang/IllegalArgumentException
    //   100: dup
    //   101: ldc 222
    //   103: invokespecial 63	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   106: athrow
    //   107: astore 6
    //   109: aload 5
    //   111: monitorexit
    //   112: aload 6
    //   114: athrow
    //   115: aload 5
    //   117: invokevirtual 223	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:isOpen	()Z
    //   120: ifeq +46 -> 166
    //   123: aload_0
    //   124: getfield 86	my/apache/http/impl/conn/SingleClientConnManager:alwaysShutDown	Z
    //   127: ifne +11 -> 138
    //   130: aload 5
    //   132: invokevirtual 226	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:isMarkedReusable	()Z
    //   135: ifne +31 -> 166
    //   138: aload_0
    //   139: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   142: invokeinterface 163 1 0
    //   147: ifeq +14 -> 161
    //   150: aload_0
    //   151: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   154: ldc 228
    //   156: invokeinterface 179 2 0
    //   161: aload 5
    //   163: invokevirtual 229	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:shutdown	()V
    //   166: aload 5
    //   168: invokevirtual 232	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   171: aload_0
    //   172: monitorenter
    //   173: aload_0
    //   174: aconst_null
    //   175: putfield 80	my/apache/http/impl/conn/SingleClientConnManager:managedConn	Lmy/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   178: aload_0
    //   179: invokestatic 104	java/lang/System:currentTimeMillis	()J
    //   182: putfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   185: lload_2
    //   186: lconst_0
    //   187: lcmp
    //   188: ifle +177 -> 365
    //   191: aload_0
    //   192: aload 4
    //   194: lload_2
    //   195: invokevirtual 134	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   198: aload_0
    //   199: getfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   202: ladd
    //   203: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   206: aload_0
    //   207: monitorexit
    //   208: aload 5
    //   210: monitorexit
    //   211: return
    //   212: astore 10
    //   214: aload_0
    //   215: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   218: invokeinterface 163 1 0
    //   223: ifeq +16 -> 239
    //   226: aload_0
    //   227: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   230: ldc 234
    //   232: aload 10
    //   234: invokeinterface 145 3 0
    //   239: aload 5
    //   241: invokevirtual 232	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   244: aload_0
    //   245: monitorenter
    //   246: aload_0
    //   247: aconst_null
    //   248: putfield 80	my/apache/http/impl/conn/SingleClientConnManager:managedConn	Lmy/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   251: aload_0
    //   252: invokestatic 104	java/lang/System:currentTimeMillis	()J
    //   255: putfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   258: lload_2
    //   259: lconst_0
    //   260: lcmp
    //   261: ifle +30 -> 291
    //   264: aload_0
    //   265: aload 4
    //   267: lload_2
    //   268: invokevirtual 134	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   271: aload_0
    //   272: getfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   275: ladd
    //   276: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   279: aload_0
    //   280: monitorexit
    //   281: goto -73 -> 208
    //   284: astore 11
    //   286: aload_0
    //   287: monitorexit
    //   288: aload 11
    //   290: athrow
    //   291: aload_0
    //   292: ldc2_w 235
    //   295: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   298: goto -19 -> 279
    //   301: astore 8
    //   303: aload 5
    //   305: invokevirtual 232	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   308: aload_0
    //   309: monitorenter
    //   310: aload_0
    //   311: aconst_null
    //   312: putfield 80	my/apache/http/impl/conn/SingleClientConnManager:managedConn	Lmy/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   315: aload_0
    //   316: invokestatic 104	java/lang/System:currentTimeMillis	()J
    //   319: putfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   322: lload_2
    //   323: lconst_0
    //   324: lcmp
    //   325: ifle +23 -> 348
    //   328: aload_0
    //   329: aload 4
    //   331: lload_2
    //   332: invokevirtual 134	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   335: aload_0
    //   336: getfield 84	my/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   339: ladd
    //   340: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   343: aload_0
    //   344: monitorexit
    //   345: aload 8
    //   347: athrow
    //   348: aload_0
    //   349: ldc2_w 235
    //   352: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   355: goto -12 -> 343
    //   358: astore 9
    //   360: aload_0
    //   361: monitorexit
    //   362: aload 9
    //   364: athrow
    //   365: aload_0
    //   366: ldc2_w 235
    //   369: putfield 98	my/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   372: goto -166 -> 206
    //   375: astore 12
    //   377: aload_0
    //   378: monitorexit
    //   379: aload 12
    //   381: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   67	78	107	finally
    //   79	86	107	finally
    //   97	107	107	finally
    //   109	112	107	finally
    //   166	173	107	finally
    //   208	211	107	finally
    //   239	246	107	finally
    //   288	291	107	finally
    //   303	310	107	finally
    //   345	348	107	finally
    //   362	365	107	finally
    //   379	382	107	finally
    //   115	138	212	java/io/IOException
    //   138	161	212	java/io/IOException
    //   161	166	212	java/io/IOException
    //   246	258	284	finally
    //   264	279	284	finally
    //   279	281	284	finally
    //   286	288	284	finally
    //   291	298	284	finally
    //   115	138	301	finally
    //   138	161	301	finally
    //   161	166	301	finally
    //   214	239	301	finally
    //   310	322	358	finally
    //   328	343	358	finally
    //   343	345	358	finally
    //   348	355	358	finally
    //   360	362	358	finally
    //   173	185	375	finally
    //   191	206	375	finally
    //   206	208	375	finally
    //   365	372	375	finally
    //   377	379	375	finally
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
        return SingleClientConnManager.this.getConnection(paramHttpRoute, paramObject);
      }
    };
  }

  // ERROR //
  protected void revokeConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	my/apache/http/impl/conn/SingleClientConnManager:managedConn	Lmy/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +4 -> 10
    //   9: return
    //   10: aload_1
    //   11: invokevirtual 232	my/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   14: aload_0
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield 78	my/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lmy/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   20: invokevirtual 198	my/apache/http/impl/conn/SingleClientConnManager$PoolEntry:shutdown	()V
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_3
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_3
    //   30: athrow
    //   31: astore_2
    //   32: aload_0
    //   33: getfield 56	my/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   36: ldc 246
    //   38: aload_2
    //   39: invokeinterface 145 3 0
    //   44: goto -21 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   16	23	26	finally
    //   23	25	26	finally
    //   27	29	26	finally
    //   32	44	26	finally
    //   16	23	31	java/io/IOException
  }

  public void shutdown()
  {
    this.isShutDown = true;
    try
    {
      if (this.uniquePoolEntry != null)
        this.uniquePoolEntry.shutdown();
    }
    catch (IOException localIOException)
    {
    }
    finally
    {
      this.uniquePoolEntry = null;
      this.managedConn = null;
    }
  }

  protected class ConnAdapter extends AbstractPooledConnAdapter
  {
    protected ConnAdapter(SingleClientConnManager.PoolEntry paramHttpRoute, HttpRoute arg3)
    {
      super(paramHttpRoute);
      markReusable();
      Object localObject;
      paramHttpRoute.route = localObject;
    }
  }

  protected class PoolEntry extends AbstractPoolEntry
  {
    protected PoolEntry()
    {
      super(null);
    }

    protected void close()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.close();
    }

    protected void shutdown()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.shutdown();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.SingleClientConnManager
 * JD-Core Version:    0.6.2
 */