package my.apache.http.impl.conn;

import java.net.InetAddress;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.params.ConnRouteParams;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.HttpRoutePlanner;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.protocol.HttpContext;

@ThreadSafe
public class DefaultHttpRoutePlanner
  implements HttpRoutePlanner
{
  protected final SchemeRegistry schemeRegistry;

  public DefaultHttpRoutePlanner(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
  }

  public HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpRequest == null)
      throw new IllegalStateException("Request must not be null.");
    HttpRoute localHttpRoute1 = ConnRouteParams.getForcedRoute(paramHttpRequest.getParams());
    if (localHttpRoute1 != null)
      return localHttpRoute1;
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null.");
    InetAddress localInetAddress = ConnRouteParams.getLocalAddress(paramHttpRequest.getParams());
    HttpHost localHttpHost = ConnRouteParams.getDefaultProxy(paramHttpRequest.getParams());
    while (true)
    {
      boolean bool;
      try
      {
        Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
        bool = localScheme.isLayered();
        if (localHttpHost == null)
        {
          localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, bool);
          return localHttpRoute2;
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        throw new HttpException(localIllegalStateException.getMessage());
      }
      HttpRoute localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, localHttpHost, bool);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.DefaultHttpRoutePlanner
 * JD-Core Version:    0.6.2
 */