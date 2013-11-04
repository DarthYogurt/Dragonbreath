package my.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.params.ConnRouteParams;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.HttpRoutePlanner;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ProxySelectorRoutePlanner
  implements HttpRoutePlanner
{
  protected ProxySelector proxySelector;
  protected final SchemeRegistry schemeRegistry;

  public ProxySelectorRoutePlanner(SchemeRegistry paramSchemeRegistry, ProxySelector paramProxySelector)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("SchemeRegistry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.proxySelector = paramProxySelector;
  }

  protected Proxy chooseProxy(List<Proxy> paramList, HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      throw new IllegalArgumentException("Proxy list must not be empty.");
    Object localObject = null;
    int i = 0;
    if ((localObject != null) || (i >= paramList.size()))
    {
      if (localObject == null)
        localObject = Proxy.NO_PROXY;
      return localObject;
    }
    Proxy localProxy = (Proxy)paramList.get(i);
    switch ($SWITCH_TABLE$java$net$Proxy$Type()[localProxy.type().ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      i++;
      break;
      localObject = localProxy;
    }
  }

  protected HttpHost determineProxy(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    ProxySelector localProxySelector = this.proxySelector;
    if (localProxySelector == null)
      localProxySelector = ProxySelector.getDefault();
    if (localProxySelector == null);
    Proxy localProxy;
    while (true)
    {
      return null;
      try
      {
        URI localURI = new URI(paramHttpHost.toURI());
        localProxy = chooseProxy(localProxySelector.select(localURI), paramHttpHost, paramHttpRequest, paramHttpContext);
        if (localProxy.type() == Proxy.Type.HTTP)
          if (!(localProxy.address() instanceof InetSocketAddress))
            throw new HttpException("Unable to handle non-Inet proxy address: " + localProxy.address());
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new HttpException("Cannot convert host to URI: " + paramHttpHost, localURISyntaxException);
      }
    }
    InetSocketAddress localInetSocketAddress = (InetSocketAddress)localProxy.address();
    return new HttpHost(getHost(localInetSocketAddress), localInetSocketAddress.getPort());
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
    HttpHost localHttpHost = determineProxy(paramHttpHost, paramHttpRequest, paramHttpContext);
    boolean bool = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName()).isLayered();
    if (localHttpHost == null);
    for (HttpRoute localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, bool); ; localHttpRoute2 = new HttpRoute(paramHttpHost, localInetAddress, localHttpHost, bool))
      return localHttpRoute2;
  }

  protected String getHost(InetSocketAddress paramInetSocketAddress)
  {
    if (paramInetSocketAddress.isUnresolved())
      return paramInetSocketAddress.getHostName();
    return paramInetSocketAddress.getAddress().getHostAddress();
  }

  public ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }

  public void setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.ProxySelectorRoutePlanner
 * JD-Core Version:    0.6.2
 */