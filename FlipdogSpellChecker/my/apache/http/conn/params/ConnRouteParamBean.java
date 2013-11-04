package my.apache.http.conn.params;

import java.net.InetAddress;
import my.apache.http.HttpHost;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.params.HttpAbstractParamBean;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class ConnRouteParamBean extends HttpAbstractParamBean
{
  public ConnRouteParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setDefaultProxy(HttpHost paramHttpHost)
  {
    this.params.setParameter("http.route.default-proxy", paramHttpHost);
  }

  public void setForcedRoute(HttpRoute paramHttpRoute)
  {
    this.params.setParameter("http.route.forced-route", paramHttpRoute);
  }

  public void setLocalAddress(InetAddress paramInetAddress)
  {
    this.params.setParameter("http.route.local-address", paramInetAddress);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnRouteParamBean
 * JD-Core Version:    0.6.2
 */