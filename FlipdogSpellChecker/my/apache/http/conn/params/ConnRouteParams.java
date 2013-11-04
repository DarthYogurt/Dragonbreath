package my.apache.http.conn.params;

import java.net.InetAddress;
import my.apache.http.HttpHost;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.params.HttpParams;

@Immutable
public class ConnRouteParams
  implements ConnRoutePNames
{
  public static final HttpHost NO_HOST = new HttpHost("127.0.0.255", 0, "no-host");
  public static final HttpRoute NO_ROUTE = new HttpRoute(NO_HOST);

  public static HttpHost getDefaultProxy(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    HttpHost localHttpHost = (HttpHost)paramHttpParams.getParameter("http.route.default-proxy");
    if ((localHttpHost != null) && (NO_HOST.equals(localHttpHost)))
      localHttpHost = null;
    return localHttpHost;
  }

  public static HttpRoute getForcedRoute(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    HttpRoute localHttpRoute = (HttpRoute)paramHttpParams.getParameter("http.route.forced-route");
    if ((localHttpRoute != null) && (NO_ROUTE.equals(localHttpRoute)))
      localHttpRoute = null;
    return localHttpRoute;
  }

  public static InetAddress getLocalAddress(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    return (InetAddress)paramHttpParams.getParameter("http.route.local-address");
  }

  public static void setDefaultProxy(HttpParams paramHttpParams, HttpHost paramHttpHost)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    paramHttpParams.setParameter("http.route.default-proxy", paramHttpHost);
  }

  public static void setForcedRoute(HttpParams paramHttpParams, HttpRoute paramHttpRoute)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    paramHttpParams.setParameter("http.route.forced-route", paramHttpRoute);
  }

  public static void setLocalAddress(HttpParams paramHttpParams, InetAddress paramInetAddress)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    paramHttpParams.setParameter("http.route.local-address", paramInetAddress);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnRouteParams
 * JD-Core Version:    0.6.2
 */