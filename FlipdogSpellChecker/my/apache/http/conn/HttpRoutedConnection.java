package my.apache.http.conn;

import javax.net.ssl.SSLSession;
import my.apache.http.HttpInetConnection;
import my.apache.http.conn.routing.HttpRoute;

public abstract interface HttpRoutedConnection extends HttpInetConnection
{
  public abstract HttpRoute getRoute();

  public abstract SSLSession getSSLSession();

  public abstract boolean isSecure();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.HttpRoutedConnection
 * JD-Core Version:    0.6.2
 */