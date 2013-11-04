package my.apache.http.conn.params;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.routing.HttpRoute;

@Deprecated
@ThreadSafe
public final class ConnPerRouteBean
  implements ConnPerRoute
{
  public static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 2;
  private volatile int defaultMax;
  private final ConcurrentHashMap<HttpRoute, Integer> maxPerHostMap = new ConcurrentHashMap();

  public ConnPerRouteBean()
  {
    this(2);
  }

  public ConnPerRouteBean(int paramInt)
  {
    setDefaultMaxPerRoute(paramInt);
  }

  public int getDefaultMax()
  {
    return this.defaultMax;
  }

  public int getDefaultMaxPerRoute()
  {
    return this.defaultMax;
  }

  public int getMaxForRoute(HttpRoute paramHttpRoute)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null.");
    Integer localInteger = (Integer)this.maxPerHostMap.get(paramHttpRoute);
    if (localInteger != null)
      return localInteger.intValue();
    return this.defaultMax;
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("The maximum must be greater than 0.");
    this.defaultMax = paramInt;
  }

  public void setMaxForRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    if (paramHttpRoute == null)
      throw new IllegalArgumentException("HTTP route may not be null.");
    if (paramInt < 1)
      throw new IllegalArgumentException("The maximum must be greater than 0.");
    this.maxPerHostMap.put(paramHttpRoute, Integer.valueOf(paramInt));
  }

  public void setMaxForRoutes(Map<HttpRoute, Integer> paramMap)
  {
    if (paramMap == null)
      return;
    this.maxPerHostMap.clear();
    this.maxPerHostMap.putAll(paramMap);
  }

  public String toString()
  {
    return this.maxPerHostMap.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnPerRouteBean
 * JD-Core Version:    0.6.2
 */