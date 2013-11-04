package my.apache.http.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import my.apache.http.HttpClientConnection;
import my.apache.http.HttpHost;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;

public abstract interface ManagedClientConnection extends HttpClientConnection, HttpRoutedConnection, ConnectionReleaseTrigger
{
  public abstract HttpRoute getRoute();

  public abstract SSLSession getSSLSession();

  public abstract Object getState();

  public abstract boolean isMarkedReusable();

  public abstract boolean isSecure();

  public abstract void layerProtocol(HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException;

  public abstract void markReusable();

  public abstract void open(HttpRoute paramHttpRoute, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException;

  public abstract void setIdleDuration(long paramLong, TimeUnit paramTimeUnit);

  public abstract void setState(Object paramObject);

  public abstract void tunnelProxy(HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;

  public abstract void tunnelTarget(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;

  public abstract void unmarkReusable();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ManagedClientConnection
 * JD-Core Version:    0.6.2
 */