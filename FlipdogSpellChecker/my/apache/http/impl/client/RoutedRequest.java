package my.apache.http.impl.client;

import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.routing.HttpRoute;

@NotThreadSafe
public class RoutedRequest
{
  protected final RequestWrapper request;
  protected final HttpRoute route;

  public RoutedRequest(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
  {
    this.request = paramRequestWrapper;
    this.route = paramHttpRoute;
  }

  public final RequestWrapper getRequest()
  {
    return this.request;
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.RoutedRequest
 * JD-Core Version:    0.6.2
 */