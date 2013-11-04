package my.apache.http.conn.params;

import my.apache.http.annotation.Immutable;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.params.HttpParams;

@Deprecated
@Immutable
public final class ConnManagerParams
  implements ConnManagerPNames
{
  private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute()
  {
    public int getMaxForRoute(HttpRoute paramAnonymousHttpRoute)
    {
      return 2;
    }
  };
  public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

  public static ConnPerRoute getMaxConnectionsPerRoute(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    ConnPerRoute localConnPerRoute = (ConnPerRoute)paramHttpParams.getParameter("http.conn-manager.max-per-route");
    if (localConnPerRoute == null)
      localConnPerRoute = DEFAULT_CONN_PER_ROUTE;
    return localConnPerRoute;
  }

  public static int getMaxTotalConnections(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    return paramHttpParams.getIntParameter("http.conn-manager.max-total", 20);
  }

  public static long getTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getLongParameter("http.conn-manager.timeout", 0L);
  }

  public static void setMaxConnectionsPerRoute(HttpParams paramHttpParams, ConnPerRoute paramConnPerRoute)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    paramHttpParams.setParameter("http.conn-manager.max-per-route", paramConnPerRoute);
  }

  public static void setMaxTotalConnections(HttpParams paramHttpParams, int paramInt)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters must not be null.");
    paramHttpParams.setIntParameter("http.conn-manager.max-total", paramInt);
  }

  public static void setTimeout(HttpParams paramHttpParams, long paramLong)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnManagerParams
 * JD-Core Version:    0.6.2
 */