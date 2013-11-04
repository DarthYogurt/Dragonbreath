package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.RouteTracker;
import my.apache.http.pool.PoolEntry;
import org.apache.commons.logging.Log;

class HttpPoolEntry extends PoolEntry<HttpRoute, OperatedClientConnection>
{
  private final Log log;
  private final RouteTracker tracker;

  public HttpPoolEntry(Log paramLog, String paramString, HttpRoute paramHttpRoute, OperatedClientConnection paramOperatedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    super(paramString, paramHttpRoute, paramOperatedClientConnection, paramLong, paramTimeUnit);
    this.log = paramLog;
    this.tracker = new RouteTracker(paramHttpRoute);
  }

  public void close()
  {
    OperatedClientConnection localOperatedClientConnection = (OperatedClientConnection)getConnection();
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

  HttpRoute getEffectiveRoute()
  {
    return this.tracker.toRoute();
  }

  HttpRoute getPlannedRoute()
  {
    return (HttpRoute)getRoute();
  }

  RouteTracker getTracker()
  {
    return this.tracker;
  }

  public boolean isClosed()
  {
    return !((OperatedClientConnection)getConnection()).isOpen();
  }

  public boolean isExpired(long paramLong)
  {
    boolean bool = super.isExpired(paramLong);
    if ((bool) && (this.log.isDebugEnabled()))
      this.log.debug("Connection " + this + " expired @ " + new Date(getExpiry()));
    return bool;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.HttpPoolEntry
 * JD-Core Version:    0.6.2
 */