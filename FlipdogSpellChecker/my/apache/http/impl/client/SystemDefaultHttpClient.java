package my.apache.http.impl.client;

import java.net.ProxySelector;
import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.routing.HttpRoutePlanner;
import my.apache.http.impl.DefaultConnectionReuseStrategy;
import my.apache.http.impl.NoConnectionReuseStrategy;
import my.apache.http.impl.conn.PoolingClientConnectionManager;
import my.apache.http.impl.conn.ProxySelectorRoutePlanner;
import my.apache.http.impl.conn.SchemeRegistryFactory;
import my.apache.http.params.HttpParams;

@ThreadSafe
public class SystemDefaultHttpClient extends DefaultHttpClient
{
  public SystemDefaultHttpClient()
  {
    super(null, null);
  }

  public SystemDefaultHttpClient(HttpParams paramHttpParams)
  {
    super(null, paramHttpParams);
  }

  protected ClientConnectionManager createClientConnectionManager()
  {
    PoolingClientConnectionManager localPoolingClientConnectionManager = new PoolingClientConnectionManager(SchemeRegistryFactory.createSystemDefault());
    if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true")))
    {
      int i = Integer.parseInt(System.getProperty("http.maxConnections", "5"));
      localPoolingClientConnectionManager.setDefaultMaxPerRoute(i);
      localPoolingClientConnectionManager.setMaxTotal(i * 2);
    }
    return localPoolingClientConnectionManager;
  }

  protected ConnectionReuseStrategy createConnectionReuseStrategy()
  {
    if ("true".equalsIgnoreCase(System.getProperty("http.keepAlive", "true")))
      return new DefaultConnectionReuseStrategy();
    return new NoConnectionReuseStrategy();
  }

  protected HttpRoutePlanner createHttpRoutePlanner()
  {
    return new ProxySelectorRoutePlanner(getConnectionManager().getSchemeRegistry(), ProxySelector.getDefault());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.SystemDefaultHttpClient
 * JD-Core Version:    0.6.2
 */