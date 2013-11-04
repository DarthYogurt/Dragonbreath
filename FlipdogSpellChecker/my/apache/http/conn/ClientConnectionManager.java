package my.apache.http.conn;

import java.util.concurrent.TimeUnit;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.scheme.SchemeRegistry;

public abstract interface ClientConnectionManager
{
  public abstract void closeExpiredConnections();

  public abstract void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit);

  public abstract SchemeRegistry getSchemeRegistry();

  public abstract void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit);

  public abstract ClientConnectionRequest requestConnection(HttpRoute paramHttpRoute, Object paramObject);

  public abstract void shutdown();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ClientConnectionManager
 * JD-Core Version:    0.6.2
 */