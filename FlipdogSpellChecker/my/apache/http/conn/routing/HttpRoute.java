package my.apache.http.conn.routing;

import java.net.InetAddress;
import my.apache.http.HttpHost;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public final class HttpRoute
  implements RouteInfo, Cloneable
{
  private static final HttpHost[] EMPTY_HTTP_HOST_ARRAY = new HttpHost[0];
  private final RouteInfo.LayerType layered;
  private final InetAddress localAddress;
  private final HttpHost[] proxyChain;
  private final boolean secure;
  private final HttpHost targetHost;
  private final RouteInfo.TunnelType tunnelled;

  private HttpRoute(InetAddress paramInetAddress, HttpHost paramHttpHost, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramArrayOfHttpHost == null)
      throw new IllegalArgumentException("Proxies may not be null.");
    if ((paramTunnelType == RouteInfo.TunnelType.TUNNELLED) && (paramArrayOfHttpHost.length == 0))
      throw new IllegalArgumentException("Proxy required if tunnelled.");
    if (paramTunnelType == null)
      paramTunnelType = RouteInfo.TunnelType.PLAIN;
    if (paramLayerType == null)
      paramLayerType = RouteInfo.LayerType.PLAIN;
    this.targetHost = paramHttpHost;
    this.localAddress = paramInetAddress;
    this.proxyChain = paramArrayOfHttpHost;
    this.secure = paramBoolean;
    this.tunnelled = paramTunnelType;
    this.layered = paramLayerType;
  }

  public HttpRoute(HttpHost paramHttpHost)
  {
    this(null, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, false, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean)
  {
  }

  public HttpRoute(HttpHost paramHttpHost1, InetAddress paramInetAddress, HttpHost paramHttpHost2, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramInetAddress, paramHttpHost1, toChain(paramHttpHost2), paramBoolean, paramTunnelType, paramLayerType);
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, boolean paramBoolean)
  {
    this(paramInetAddress, paramHttpHost, EMPTY_HTTP_HOST_ARRAY, paramBoolean, RouteInfo.TunnelType.PLAIN, RouteInfo.LayerType.PLAIN);
  }

  public HttpRoute(HttpHost paramHttpHost, InetAddress paramInetAddress, HttpHost[] paramArrayOfHttpHost, boolean paramBoolean, RouteInfo.TunnelType paramTunnelType, RouteInfo.LayerType paramLayerType)
  {
    this(paramInetAddress, paramHttpHost, toChain(paramArrayOfHttpHost), paramBoolean, paramTunnelType, paramLayerType);
  }

  private static HttpHost[] toChain(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      return EMPTY_HTTP_HOST_ARRAY;
    return new HttpHost[] { paramHttpHost };
  }

  private static HttpHost[] toChain(HttpHost[] paramArrayOfHttpHost)
  {
    if ((paramArrayOfHttpHost == null) || (paramArrayOfHttpHost.length < 1))
      return EMPTY_HTTP_HOST_ARRAY;
    int i = paramArrayOfHttpHost.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        HttpHost[] arrayOfHttpHost = new HttpHost[paramArrayOfHttpHost.length];
        System.arraycopy(paramArrayOfHttpHost, 0, arrayOfHttpHost, 0, paramArrayOfHttpHost.length);
        return arrayOfHttpHost;
      }
      if (paramArrayOfHttpHost[j] == null)
        throw new IllegalArgumentException("Proxy chain may not contain null elements.");
    }
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject);
    HttpRoute localHttpRoute;
    do
    {
      return true;
      if (!(paramObject instanceof HttpRoute))
        break;
      localHttpRoute = (HttpRoute)paramObject;
    }
    while ((this.secure == localHttpRoute.secure) && (this.tunnelled == localHttpRoute.tunnelled) && (this.layered == localHttpRoute.layered) && (LangUtils.equals(this.targetHost, localHttpRoute.targetHost)) && (LangUtils.equals(this.localAddress, localHttpRoute.localAddress)) && (LangUtils.equals(this.proxyChain, localHttpRoute.proxyChain)));
    return false;
    return false;
  }

  public final int getHopCount()
  {
    return 1 + this.proxyChain.length;
  }

  public final HttpHost getHopTarget(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Hop index must not be negative: " + paramInt);
    int i = getHopCount();
    if (paramInt >= i)
      throw new IllegalArgumentException("Hop index " + paramInt + " exceeds route length " + i);
    if (paramInt < i - 1)
      return this.proxyChain[paramInt];
    return this.targetHost;
  }

  public final RouteInfo.LayerType getLayerType()
  {
    return this.layered;
  }

  public final InetAddress getLocalAddress()
  {
    return this.localAddress;
  }

  public final HttpHost getProxyHost()
  {
    if (this.proxyChain.length == 0)
      return null;
    return this.proxyChain[0];
  }

  public final HttpHost getTargetHost()
  {
    return this.targetHost;
  }

  public final RouteInfo.TunnelType getTunnelType()
  {
    return this.tunnelled;
  }

  public final int hashCode()
  {
    int i = LangUtils.hashCode(LangUtils.hashCode(17, this.targetHost), this.localAddress);
    for (int j = 0; ; j++)
    {
      if (j >= this.proxyChain.length)
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(i, this.secure), this.tunnelled), this.layered);
      i = LangUtils.hashCode(i, this.proxyChain[j]);
    }
  }

  public final boolean isLayered()
  {
    return this.layered == RouteInfo.LayerType.LAYERED;
  }

  public final boolean isSecure()
  {
    return this.secure;
  }

  public final boolean isTunnelled()
  {
    return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(50 + 30 * getHopCount());
    if (this.localAddress != null)
    {
      localStringBuilder.append(this.localAddress);
      localStringBuilder.append("->");
    }
    localStringBuilder.append('{');
    if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED)
      localStringBuilder.append('t');
    if (this.layered == RouteInfo.LayerType.LAYERED)
      localStringBuilder.append('l');
    if (this.secure)
      localStringBuilder.append('s');
    localStringBuilder.append("}->");
    HttpHost[] arrayOfHttpHost = this.proxyChain;
    int i = arrayOfHttpHost.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.append(this.targetHost);
        return localStringBuilder.toString();
      }
      localStringBuilder.append(arrayOfHttpHost[j]);
      localStringBuilder.append("->");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.routing.HttpRoute
 * JD-Core Version:    0.6.2
 */