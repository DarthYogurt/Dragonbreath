package my.apache.http.conn.params;

import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.params.HttpAbstractParamBean;
import my.apache.http.params.HttpParams;

@Deprecated
@NotThreadSafe
public class ConnManagerParamBean extends HttpAbstractParamBean
{
  public ConnManagerParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setConnectionsPerRoute(ConnPerRouteBean paramConnPerRouteBean)
  {
    this.params.setParameter("http.conn-manager.max-per-route", paramConnPerRouteBean);
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.params.setIntParameter("http.conn-manager.max-total", paramInt);
  }

  public void setTimeout(long paramLong)
  {
    this.params.setLongParameter("http.conn-manager.timeout", paramLong);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnManagerParamBean
 * JD-Core Version:    0.6.2
 */