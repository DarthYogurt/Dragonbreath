package my.apache.http.impl.conn;

import java.io.IOException;
import my.apache.http.HttpHost;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.RouteTracker;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;

@Deprecated
public abstract class AbstractPooledConnAdapter extends AbstractClientConnAdapter
{
  protected volatile AbstractPoolEntry poolEntry;

  protected AbstractPooledConnAdapter(ClientConnectionManager paramClientConnectionManager, AbstractPoolEntry paramAbstractPoolEntry)
  {
    super(paramClientConnectionManager, paramAbstractPoolEntry.connection);
    this.poolEntry = paramAbstractPoolEntry;
  }

  protected final void assertAttached()
  {
    if (this.poolEntry == null)
      throw new ConnectionShutdownException();
  }

  protected void assertValid(AbstractPoolEntry paramAbstractPoolEntry)
  {
    if ((isReleased()) || (paramAbstractPoolEntry == null))
      throw new ConnectionShutdownException();
  }

  public void close()
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    if (localAbstractPoolEntry != null)
      localAbstractPoolEntry.shutdownEntry();
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection != null)
      localOperatedClientConnection.close();
  }

  protected void detach()
  {
    try
    {
      this.poolEntry = null;
      super.detach();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected AbstractPoolEntry getPoolEntry()
  {
    return this.poolEntry;
  }

  public HttpRoute getRoute()
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    if (localAbstractPoolEntry.tracker == null)
      return null;
    return localAbstractPoolEntry.tracker.toRoute();
  }

  public Object getState()
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    return localAbstractPoolEntry.getState();
  }

  public void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.layerProtocol(paramHttpContext, paramHttpParams);
  }

  public void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.open(paramHttpRoute, paramHttpContext, paramHttpParams);
  }

  public void setState(Object paramObject)
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.setState(paramObject);
  }

  public void shutdown()
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    if (localAbstractPoolEntry != null)
      localAbstractPoolEntry.shutdownEntry();
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection != null)
      localOperatedClientConnection.shutdown();
  }

  public void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.tunnelProxy(paramHttpHost, paramBoolean, paramHttpParams);
  }

  public void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    AbstractPoolEntry localAbstractPoolEntry = getPoolEntry();
    assertValid(localAbstractPoolEntry);
    localAbstractPoolEntry.tunnelTarget(paramBoolean, paramHttpParams);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.AbstractPooledConnAdapter
 * JD-Core Version:    0.6.2
 */