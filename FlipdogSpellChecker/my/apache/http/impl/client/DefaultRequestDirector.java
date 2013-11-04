package my.apache.http.impl.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.ProtocolException;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthState;
import my.apache.http.client.AuthenticationHandler;
import my.apache.http.client.AuthenticationStrategy;
import my.apache.http.client.HttpRequestRetryHandler;
import my.apache.http.client.NonRepeatableRequestException;
import my.apache.http.client.RedirectException;
import my.apache.http.client.RedirectHandler;
import my.apache.http.client.RedirectStrategy;
import my.apache.http.client.RequestDirector;
import my.apache.http.client.UserTokenHandler;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.client.params.HttpClientParams;
import my.apache.http.client.utils.URIUtils;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ConnectionKeepAliveStrategy;
import my.apache.http.conn.ManagedClientConnection;
import my.apache.http.conn.routing.BasicRouteDirector;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.conn.routing.HttpRouteDirector;
import my.apache.http.conn.routing.HttpRoutePlanner;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.entity.BufferedHttpEntity;
import my.apache.http.message.BasicHttpRequest;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;
import my.apache.http.protocol.HttpContext;
import my.apache.http.protocol.HttpProcessor;
import my.apache.http.protocol.HttpRequestExecutor;
import my.apache.http.util.EntityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@NotThreadSafe
public class DefaultRequestDirector
  implements RequestDirector
{
  private final HttpAuthenticator authenticator;
  protected final ClientConnectionManager connManager;
  private int execCount;
  protected final HttpProcessor httpProcessor;
  protected final ConnectionKeepAliveStrategy keepAliveStrategy;
  private final Log log;
  protected ManagedClientConnection managedConn;
  private int maxRedirects;
  protected final HttpParams params;

  @Deprecated
  protected final AuthenticationHandler proxyAuthHandler;
  protected final AuthState proxyAuthState;
  protected final AuthenticationStrategy proxyAuthStrategy;
  private int redirectCount;

  @Deprecated
  protected final RedirectHandler redirectHandler;
  protected final RedirectStrategy redirectStrategy;
  protected final HttpRequestExecutor requestExec;
  protected final HttpRequestRetryHandler retryHandler;
  protected final ConnectionReuseStrategy reuseStrategy;
  protected final HttpRoutePlanner routePlanner;

  @Deprecated
  protected final AuthenticationHandler targetAuthHandler;
  protected final AuthState targetAuthState;
  protected final AuthenticationStrategy targetAuthStrategy;
  protected final UserTokenHandler userTokenHandler;
  private HttpHost virtualHost;

  @Deprecated
  public DefaultRequestDirector(HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectHandler paramRedirectHandler, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    this(LogFactory.getLog(DefaultRequestDirector.class), paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, new DefaultRedirectStrategyAdaptor(paramRedirectHandler), new AuthenticationStrategyAdaptor(paramAuthenticationHandler1), new AuthenticationStrategyAdaptor(paramAuthenticationHandler2), paramUserTokenHandler, paramHttpParams);
  }

  @Deprecated
  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationHandler paramAuthenticationHandler1, AuthenticationHandler paramAuthenticationHandler2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    this(LogFactory.getLog(DefaultRequestDirector.class), paramHttpRequestExecutor, paramClientConnectionManager, paramConnectionReuseStrategy, paramConnectionKeepAliveStrategy, paramHttpRoutePlanner, paramHttpProcessor, paramHttpRequestRetryHandler, paramRedirectStrategy, new AuthenticationStrategyAdaptor(paramAuthenticationHandler1), new AuthenticationStrategyAdaptor(paramAuthenticationHandler2), paramUserTokenHandler, paramHttpParams);
  }

  public DefaultRequestDirector(Log paramLog, HttpRequestExecutor paramHttpRequestExecutor, ClientConnectionManager paramClientConnectionManager, ConnectionReuseStrategy paramConnectionReuseStrategy, ConnectionKeepAliveStrategy paramConnectionKeepAliveStrategy, HttpRoutePlanner paramHttpRoutePlanner, HttpProcessor paramHttpProcessor, HttpRequestRetryHandler paramHttpRequestRetryHandler, RedirectStrategy paramRedirectStrategy, AuthenticationStrategy paramAuthenticationStrategy1, AuthenticationStrategy paramAuthenticationStrategy2, UserTokenHandler paramUserTokenHandler, HttpParams paramHttpParams)
  {
    if (paramLog == null)
      throw new IllegalArgumentException("Log may not be null.");
    if (paramHttpRequestExecutor == null)
      throw new IllegalArgumentException("Request executor may not be null.");
    if (paramClientConnectionManager == null)
      throw new IllegalArgumentException("Client connection manager may not be null.");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null.");
    if (paramConnectionKeepAliveStrategy == null)
      throw new IllegalArgumentException("Connection keep alive strategy may not be null.");
    if (paramHttpRoutePlanner == null)
      throw new IllegalArgumentException("Route planner may not be null.");
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP protocol processor may not be null.");
    if (paramHttpRequestRetryHandler == null)
      throw new IllegalArgumentException("HTTP request retry handler may not be null.");
    if (paramRedirectStrategy == null)
      throw new IllegalArgumentException("Redirect strategy may not be null.");
    if (paramAuthenticationStrategy1 == null)
      throw new IllegalArgumentException("Target authentication strategy may not be null.");
    if (paramAuthenticationStrategy2 == null)
      throw new IllegalArgumentException("Proxy authentication strategy may not be null.");
    if (paramUserTokenHandler == null)
      throw new IllegalArgumentException("User token handler may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.log = paramLog;
    this.authenticator = new HttpAuthenticator(paramLog);
    this.requestExec = paramHttpRequestExecutor;
    this.connManager = paramClientConnectionManager;
    this.reuseStrategy = paramConnectionReuseStrategy;
    this.keepAliveStrategy = paramConnectionKeepAliveStrategy;
    this.routePlanner = paramHttpRoutePlanner;
    this.httpProcessor = paramHttpProcessor;
    this.retryHandler = paramHttpRequestRetryHandler;
    this.redirectStrategy = paramRedirectStrategy;
    this.targetAuthStrategy = paramAuthenticationStrategy1;
    this.proxyAuthStrategy = paramAuthenticationStrategy2;
    this.userTokenHandler = paramUserTokenHandler;
    this.params = paramHttpParams;
    if ((paramRedirectStrategy instanceof DefaultRedirectStrategyAdaptor))
    {
      this.redirectHandler = ((DefaultRedirectStrategyAdaptor)paramRedirectStrategy).getHandler();
      if (!(paramAuthenticationStrategy1 instanceof AuthenticationStrategyAdaptor))
        break label406;
      this.targetAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy1).getHandler();
      label323: if (!(paramAuthenticationStrategy2 instanceof AuthenticationStrategyAdaptor))
        break label414;
    }
    label406: label414: for (this.proxyAuthHandler = ((AuthenticationStrategyAdaptor)paramAuthenticationStrategy2).getHandler(); ; this.proxyAuthHandler = null)
    {
      this.managedConn = null;
      this.execCount = 0;
      this.redirectCount = 0;
      this.targetAuthState = new AuthState();
      this.proxyAuthState = new AuthState();
      this.maxRedirects = this.params.getIntParameter("http.protocol.max-redirects", 100);
      return;
      this.redirectHandler = null;
      break;
      this.targetAuthHandler = null;
      break label323;
    }
  }

  private void abortConnection()
  {
    ManagedClientConnection localManagedClientConnection = this.managedConn;
    if (localManagedClientConnection != null)
      this.managedConn = null;
    try
    {
      localManagedClientConnection.abortConnection();
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          localManagedClientConnection.releaseConnection();
          return;
          localIOException1 = localIOException1;
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
        }
      }
      catch (IOException localIOException2)
      {
        this.log.debug("Error releasing connection", localIOException2);
      }
    }
  }

  private void tryConnect(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    int i = 0;
    while (true)
    {
      paramHttpContext.setAttribute("http.request", localRequestWrapper);
      i++;
      try
      {
        if (!this.managedConn.isOpen())
          this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
        while (true)
        {
          establishRoute(localHttpRoute, paramHttpContext);
          return;
          this.managedConn.setSocketTimeout(HttpConnectionParams.getSoTimeout(this.params));
        }
      }
      catch (IOException localIOException1)
      {
      }
      try
      {
        this.managedConn.close();
        label91: if (this.retryHandler.retryRequest(localIOException1, i, paramHttpContext))
        {
          if (!this.log.isInfoEnabled())
            continue;
          this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when connecting to the target host: " + localIOException1.getMessage());
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying connect");
          continue;
        }
        throw localIOException1;
      }
      catch (IOException localIOException2)
      {
        break label91;
      }
    }
  }

  private HttpResponse tryExecute(RoutedRequest paramRoutedRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    RequestWrapper localRequestWrapper = paramRoutedRequest.getRequest();
    HttpRoute localHttpRoute = paramRoutedRequest.getRoute();
    Object localObject = null;
    while (true)
    {
      this.execCount = (1 + this.execCount);
      localRequestWrapper.incrementExecCount();
      if (!localRequestWrapper.isRepeatable())
      {
        this.log.debug("Cannot retry non-repeatable request");
        if (localObject != null)
          throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.  The cause lists the reason the original request failed.", (Throwable)localObject);
        throw new NonRepeatableRequestException("Cannot retry request with a non-repeatable request entity.");
      }
      try
      {
        if (!this.managedConn.isOpen())
        {
          if (!localHttpRoute.isTunnelled())
          {
            this.log.debug("Reopening the direct connection.");
            this.managedConn.open(localHttpRoute, paramHttpContext, this.params);
          }
        }
        else
        {
          if (this.log.isDebugEnabled())
            this.log.debug("Attempt " + this.execCount + " to execute request");
          return this.requestExec.execute(localRequestWrapper, this.managedConn, paramHttpContext);
        }
        this.log.debug("Proxied connection. Need to start over.");
        return null;
      }
      catch (IOException localIOException1)
      {
        this.log.debug("Closing the connection.");
      }
      try
      {
        this.managedConn.close();
        label222: if (this.retryHandler.retryRequest(localIOException1, localRequestWrapper.getExecCount(), paramHttpContext))
        {
          if (this.log.isInfoEnabled())
            this.log.info("I/O exception (" + localIOException1.getClass().getName() + ") caught when processing request: " + localIOException1.getMessage());
          if (this.log.isDebugEnabled())
            this.log.debug(localIOException1.getMessage(), localIOException1);
          this.log.info("Retrying request");
          localObject = localIOException1;
          continue;
        }
        throw localIOException1;
      }
      catch (IOException localIOException2)
      {
        break label222;
      }
    }
  }

  private RequestWrapper wrapRequest(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
      return new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpRequest);
    return new RequestWrapper(paramHttpRequest);
  }

  protected HttpRequest createConnectRequest(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
  {
    HttpHost localHttpHost = paramHttpRoute.getTargetHost();
    String str = localHttpHost.getHostName();
    int i = localHttpHost.getPort();
    if (i < 0)
      i = this.connManager.getSchemeRegistry().getScheme(localHttpHost.getSchemeName()).getDefaultPort();
    StringBuilder localStringBuilder = new StringBuilder(6 + str.length());
    localStringBuilder.append(str);
    localStringBuilder.append(':');
    localStringBuilder.append(Integer.toString(i));
    return new BasicHttpRequest("CONNECT", localStringBuilder.toString(), HttpProtocolParams.getVersion(this.params));
  }

  protected boolean createTunnelToProxy(HttpRoute paramHttpRoute, int paramInt, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    throw new HttpException("Proxy chains are not supported.");
  }

  protected boolean createTunnelToTarget(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpHost localHttpHost1 = paramHttpRoute.getProxyHost();
    HttpHost localHttpHost2 = paramHttpRoute.getTargetHost();
    HttpResponse localHttpResponse;
    while (true)
    {
      if (!this.managedConn.isOpen())
        this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
      HttpRequest localHttpRequest = createConnectRequest(paramHttpRoute, paramHttpContext);
      localHttpRequest.setParams(this.params);
      paramHttpContext.setAttribute("http.target_host", localHttpHost2);
      paramHttpContext.setAttribute("http.proxy_host", localHttpHost1);
      paramHttpContext.setAttribute("http.connection", this.managedConn);
      paramHttpContext.setAttribute("http.request", localHttpRequest);
      this.requestExec.preProcess(localHttpRequest, this.httpProcessor, paramHttpContext);
      localHttpResponse = this.requestExec.execute(localHttpRequest, this.managedConn, paramHttpContext);
      localHttpResponse.setParams(this.params);
      this.requestExec.postProcess(localHttpResponse, this.httpProcessor, paramHttpContext);
      if (localHttpResponse.getStatusLine().getStatusCode() < 200)
        throw new HttpException("Unexpected response to CONNECT request: " + localHttpResponse.getStatusLine());
      if (HttpClientParams.isAuthenticating(this.params))
      {
        if ((!this.authenticator.isAuthenticationRequested(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)) || (!this.authenticator.authenticate(localHttpHost1, localHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext)))
          break;
        if (this.reuseStrategy.keepAlive(localHttpResponse, paramHttpContext))
        {
          this.log.debug("Connection kept alive");
          EntityUtils.consume(localHttpResponse.getEntity());
        }
        else
        {
          this.managedConn.close();
        }
      }
    }
    if (localHttpResponse.getStatusLine().getStatusCode() > 299)
    {
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      if (localHttpEntity != null)
        localHttpResponse.setEntity(new BufferedHttpEntity(localHttpEntity));
      this.managedConn.close();
      throw new TunnelRefusedException("CONNECT refused by proxy: " + localHttpResponse.getStatusLine(), localHttpResponse);
    }
    this.managedConn.markReusable();
    return false;
  }

  protected HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException
  {
    if (paramHttpHost == null)
      paramHttpHost = (HttpHost)paramHttpRequest.getParams().getParameter("http.default-host");
    if (paramHttpHost == null)
      throw new IllegalStateException("Target host must not be null, or set in parameters.");
    return this.routePlanner.determineRoute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }

  protected void establishRoute(HttpRoute paramHttpRoute, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    BasicRouteDirector localBasicRouteDirector = new BasicRouteDirector();
    HttpRoute localHttpRoute = this.managedConn.getRoute();
    int i = localBasicRouteDirector.nextStep(paramHttpRoute, localHttpRoute);
    switch (i)
    {
    default:
      throw new IllegalStateException("Unknown step indicator " + i + " from RouteDirector.");
    case 1:
    case 2:
      this.managedConn.open(paramHttpRoute, paramHttpContext, this.params);
    case 0:
    case 3:
    case 4:
    case 5:
      while (i <= 0)
      {
        return;
        boolean bool2 = createTunnelToTarget(paramHttpRoute, paramHttpContext);
        this.log.debug("Tunnel to target created.");
        this.managedConn.tunnelTarget(bool2, this.params);
        continue;
        int j = -1 + localHttpRoute.getHopCount();
        boolean bool1 = createTunnelToProxy(paramHttpRoute, j, paramHttpContext);
        this.log.debug("Tunnel to proxy created.");
        this.managedConn.tunnelProxy(paramHttpRoute.getHopTarget(j), bool1, this.params);
        continue;
        this.managedConn.layerProtocol(paramHttpContext, this.params);
      }
    case -1:
    }
    throw new HttpException("Unable to establish route: planned = " + paramHttpRoute + "; current = " + localHttpRoute);
  }

  // ERROR //
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    // Byte code:
    //   0: aload_3
    //   1: ldc_w 590
    //   4: aload_0
    //   5: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   8: invokeinterface 224 3 0
    //   13: aload_3
    //   14: ldc_w 592
    //   17: aload_0
    //   18: getfield 166	my/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lmy/apache/http/auth/AuthState;
    //   21: invokeinterface 224 3 0
    //   26: aload_0
    //   27: aload_2
    //   28: invokespecial 594	my/apache/http/impl/client/DefaultRequestDirector:wrapRequest	(Lmy/apache/http/HttpRequest;)Lmy/apache/http/impl/client/RequestWrapper;
    //   31: astore 4
    //   33: aload 4
    //   35: aload_0
    //   36: getfield 140	my/apache/http/impl/client/DefaultRequestDirector:params	Lmy/apache/http/params/HttpParams;
    //   39: invokevirtual 595	my/apache/http/impl/client/RequestWrapper:setParams	(Lmy/apache/http/params/HttpParams;)V
    //   42: aload_0
    //   43: aload_1
    //   44: aload 4
    //   46: aload_3
    //   47: invokevirtual 596	my/apache/http/impl/client/DefaultRequestDirector:determineRoute	(Lmy/apache/http/HttpHost;Lmy/apache/http/HttpRequest;Lmy/apache/http/protocol/HttpContext;)Lmy/apache/http/conn/routing/HttpRoute;
    //   50: astore 5
    //   52: aload_0
    //   53: aload 4
    //   55: invokevirtual 597	my/apache/http/impl/client/RequestWrapper:getParams	()Lmy/apache/http/params/HttpParams;
    //   58: ldc_w 599
    //   61: invokeinterface 527 2 0
    //   66: checkcast 364	my/apache/http/HttpHost
    //   69: putfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   72: aload_0
    //   73: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   76: ifnull +65 -> 141
    //   79: aload_0
    //   80: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   83: invokevirtual 370	my/apache/http/HttpHost:getPort	()I
    //   86: iconst_m1
    //   87: if_icmpne +54 -> 141
    //   90: aload_1
    //   91: ifnull +128 -> 219
    //   94: aload_1
    //   95: astore 38
    //   97: aload 38
    //   99: invokevirtual 370	my/apache/http/HttpHost:getPort	()I
    //   102: istore 39
    //   104: iload 39
    //   106: iconst_m1
    //   107: if_icmpeq +34 -> 141
    //   110: new 364	my/apache/http/HttpHost
    //   113: dup
    //   114: aload_0
    //   115: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   118: invokevirtual 367	my/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   121: iload 39
    //   123: aload_0
    //   124: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   127: invokevirtual 379	my/apache/http/HttpHost:getSchemeName	()Ljava/lang/String;
    //   130: invokespecial 604	my/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   133: astore 40
    //   135: aload_0
    //   136: aload 40
    //   138: putfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   141: new 208	my/apache/http/impl/client/RoutedRequest
    //   144: dup
    //   145: aload 4
    //   147: aload 5
    //   149: invokespecial 607	my/apache/http/impl/client/RoutedRequest:<init>	(Lmy/apache/http/impl/client/RequestWrapper;Lmy/apache/http/conn/routing/HttpRoute;)V
    //   152: astore 6
    //   154: iconst_0
    //   155: istore 7
    //   157: iconst_0
    //   158: istore 8
    //   160: aconst_null
    //   161: astore 9
    //   163: iload 8
    //   165: ifeq +64 -> 229
    //   168: aload 9
    //   170: ifnull +28 -> 198
    //   173: aload 9
    //   175: invokeinterface 492 1 0
    //   180: ifnull +18 -> 198
    //   183: aload 9
    //   185: invokeinterface 492 1 0
    //   190: invokeinterface 612 1 0
    //   195: ifne +907 -> 1102
    //   198: iload 7
    //   200: ifeq +12 -> 212
    //   203: aload_0
    //   204: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   207: invokeinterface 515 1 0
    //   212: aload_0
    //   213: invokevirtual 613	my/apache/http/impl/client/DefaultRequestDirector:releaseConnection	()V
    //   216: aload 9
    //   218: areturn
    //   219: aload 5
    //   221: invokevirtual 362	my/apache/http/conn/routing/HttpRoute:getTargetHost	()Lmy/apache/http/HttpHost;
    //   224: astore 38
    //   226: goto -129 -> 97
    //   229: aload 6
    //   231: invokevirtual 216	my/apache/http/impl/client/RoutedRequest:getRequest	()Lmy/apache/http/impl/client/RequestWrapper;
    //   234: astore 16
    //   236: aload 6
    //   238: invokevirtual 212	my/apache/http/impl/client/RoutedRequest:getRoute	()Lmy/apache/http/conn/routing/HttpRoute;
    //   241: astore 17
    //   243: aload_3
    //   244: ldc_w 615
    //   247: invokeinterface 618 2 0
    //   252: astore 18
    //   254: aload_0
    //   255: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   258: ifnonnull +128 -> 386
    //   261: aload_0
    //   262: getfield 120	my/apache/http/impl/client/DefaultRequestDirector:connManager	Lmy/apache/http/conn/ClientConnectionManager;
    //   265: aload 17
    //   267: aload 18
    //   269: invokeinterface 622 3 0
    //   274: astore 34
    //   276: aload_2
    //   277: instanceof 624
    //   280: ifeq +14 -> 294
    //   283: aload_2
    //   284: checkcast 624	my/apache/http/client/methods/AbortableHttpRequest
    //   287: aload 34
    //   289: invokeinterface 628 2 0
    //   294: aload_0
    //   295: getfield 140	my/apache/http/impl/client/DefaultRequestDirector:params	Lmy/apache/http/params/HttpParams;
    //   298: invokestatic 632	my/apache/http/client/params/HttpClientParams:getConnectionManagerTimeout	(Lmy/apache/http/params/HttpParams;)J
    //   301: lstore 35
    //   303: aload_0
    //   304: aload 34
    //   306: lload 35
    //   308: getstatic 638	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   311: invokeinterface 644 4 0
    //   316: putfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   319: aload_0
    //   320: getfield 140	my/apache/http/impl/client/DefaultRequestDirector:params	Lmy/apache/http/params/HttpParams;
    //   323: invokestatic 647	my/apache/http/params/HttpConnectionParams:isStaleCheckingEnabled	(Lmy/apache/http/params/HttpParams;)Z
    //   326: ifeq +60 -> 386
    //   329: aload_0
    //   330: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   333: invokeinterface 227 1 0
    //   338: ifeq +48 -> 386
    //   341: aload_0
    //   342: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   345: ldc_w 649
    //   348: invokeinterface 300 2 0
    //   353: aload_0
    //   354: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   357: invokeinterface 652 1 0
    //   362: ifeq +24 -> 386
    //   365: aload_0
    //   366: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   369: ldc_w 654
    //   372: invokeinterface 300 2 0
    //   377: aload_0
    //   378: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   381: invokeinterface 248 1 0
    //   386: aload_2
    //   387: instanceof 624
    //   390: ifeq +16 -> 406
    //   393: aload_2
    //   394: checkcast 624	my/apache/http/client/methods/AbortableHttpRequest
    //   397: aload_0
    //   398: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   401: invokeinterface 658 2 0
    //   406: aload_0
    //   407: aload 6
    //   409: aload_3
    //   410: invokespecial 660	my/apache/http/impl/client/DefaultRequestDirector:tryConnect	(Lmy/apache/http/impl/client/RoutedRequest;Lmy/apache/http/protocol/HttpContext;)V
    //   413: aload 16
    //   415: invokevirtual 664	my/apache/http/impl/client/RequestWrapper:getURI	()Ljava/net/URI;
    //   418: invokevirtual 669	java/net/URI:getUserInfo	()Ljava/lang/String;
    //   421: astore 21
    //   423: aload 21
    //   425: ifnull +38 -> 463
    //   428: aload_0
    //   429: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   432: astore 22
    //   434: new 671	my/apache/http/impl/auth/BasicScheme
    //   437: dup
    //   438: invokespecial 672	my/apache/http/impl/auth/BasicScheme:<init>	()V
    //   441: astore 23
    //   443: new 674	my/apache/http/auth/UsernamePasswordCredentials
    //   446: dup
    //   447: aload 21
    //   449: invokespecial 675	my/apache/http/auth/UsernamePasswordCredentials:<init>	(Ljava/lang/String;)V
    //   452: astore 24
    //   454: aload 22
    //   456: aload 23
    //   458: aload 24
    //   460: invokevirtual 679	my/apache/http/auth/AuthState:update	(Lmy/apache/http/auth/AuthScheme;Lmy/apache/http/auth/Credentials;)V
    //   463: aload 17
    //   465: invokevirtual 428	my/apache/http/conn/routing/HttpRoute:getProxyHost	()Lmy/apache/http/HttpHost;
    //   468: astore 25
    //   470: aload_0
    //   471: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   474: ifnull +416 -> 890
    //   477: aload_0
    //   478: getfield 601	my/apache/http/impl/client/DefaultRequestDirector:virtualHost	Lmy/apache/http/HttpHost;
    //   481: astore_1
    //   482: aload_1
    //   483: ifnonnull +9 -> 492
    //   486: aload 17
    //   488: invokevirtual 362	my/apache/http/conn/routing/HttpRoute:getTargetHost	()Lmy/apache/http/HttpHost;
    //   491: astore_1
    //   492: aload 16
    //   494: invokevirtual 682	my/apache/http/impl/client/RequestWrapper:resetHeaders	()V
    //   497: aload_0
    //   498: aload 16
    //   500: aload 17
    //   502: invokevirtual 685	my/apache/http/impl/client/DefaultRequestDirector:rewriteRequestURI	(Lmy/apache/http/impl/client/RequestWrapper;Lmy/apache/http/conn/routing/HttpRoute;)V
    //   505: aload_3
    //   506: ldc_w 438
    //   509: aload_1
    //   510: invokeinterface 224 3 0
    //   515: aload_3
    //   516: ldc_w 440
    //   519: aload 25
    //   521: invokeinterface 224 3 0
    //   526: aload_3
    //   527: ldc_w 442
    //   530: aload_0
    //   531: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   534: invokeinterface 224 3 0
    //   539: aload_0
    //   540: getfield 118	my/apache/http/impl/client/DefaultRequestDirector:requestExec	Lmy/apache/http/protocol/HttpRequestExecutor;
    //   543: aload 16
    //   545: aload_0
    //   546: getfield 128	my/apache/http/impl/client/DefaultRequestDirector:httpProcessor	Lmy/apache/http/protocol/HttpProcessor;
    //   549: aload_3
    //   550: invokevirtual 446	my/apache/http/protocol/HttpRequestExecutor:preProcess	(Lmy/apache/http/HttpRequest;Lmy/apache/http/protocol/HttpProcessor;Lmy/apache/http/protocol/HttpContext;)V
    //   553: aload_0
    //   554: aload 6
    //   556: aload_3
    //   557: invokespecial 687	my/apache/http/impl/client/DefaultRequestDirector:tryExecute	(Lmy/apache/http/impl/client/RoutedRequest;Lmy/apache/http/protocol/HttpContext;)Lmy/apache/http/HttpResponse;
    //   560: astore 9
    //   562: aload 9
    //   564: ifnull -401 -> 163
    //   567: aload_0
    //   568: getfield 140	my/apache/http/impl/client/DefaultRequestDirector:params	Lmy/apache/http/params/HttpParams;
    //   571: astore 27
    //   573: aload 9
    //   575: aload 27
    //   577: invokeinterface 449 2 0
    //   582: aload_0
    //   583: getfield 118	my/apache/http/impl/client/DefaultRequestDirector:requestExec	Lmy/apache/http/protocol/HttpRequestExecutor;
    //   586: astore 28
    //   588: aload_0
    //   589: getfield 128	my/apache/http/impl/client/DefaultRequestDirector:httpProcessor	Lmy/apache/http/protocol/HttpProcessor;
    //   592: astore 29
    //   594: aload 28
    //   596: aload 9
    //   598: aload 29
    //   600: aload_3
    //   601: invokevirtual 453	my/apache/http/protocol/HttpRequestExecutor:postProcess	(Lmy/apache/http/HttpResponse;Lmy/apache/http/protocol/HttpProcessor;Lmy/apache/http/protocol/HttpContext;)V
    //   604: aload_0
    //   605: getfield 122	my/apache/http/impl/client/DefaultRequestDirector:reuseStrategy	Lmy/apache/http/ConnectionReuseStrategy;
    //   608: aload 9
    //   610: aload_3
    //   611: invokeinterface 486 3 0
    //   616: istore 7
    //   618: iload 7
    //   620: ifeq +109 -> 729
    //   623: aload_0
    //   624: getfield 124	my/apache/http/impl/client/DefaultRequestDirector:keepAliveStrategy	Lmy/apache/http/conn/ConnectionKeepAliveStrategy;
    //   627: aload 9
    //   629: aload_3
    //   630: invokeinterface 693 3 0
    //   635: lstore 30
    //   637: aload_0
    //   638: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   641: invokeinterface 192 1 0
    //   646: ifeq +69 -> 715
    //   649: lload 30
    //   651: lconst_0
    //   652: lcmp
    //   653: ifle +492 -> 1145
    //   656: new 259	java/lang/StringBuilder
    //   659: dup
    //   660: ldc_w 695
    //   663: invokespecial 262	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   666: lload 30
    //   668: invokevirtual 698	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   671: ldc_w 700
    //   674: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: getstatic 638	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   680: invokevirtual 467	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   683: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   686: astore 32
    //   688: aload_0
    //   689: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   692: new 259	java/lang/StringBuilder
    //   695: dup
    //   696: ldc_w 702
    //   699: invokespecial 262	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   702: aload 32
    //   704: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   707: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   710: invokeinterface 300 2 0
    //   715: aload_0
    //   716: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   719: lload 30
    //   721: getstatic 638	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   724: invokeinterface 706 4 0
    //   729: aload_0
    //   730: aload 6
    //   732: aload 9
    //   734: aload_3
    //   735: invokevirtual 710	my/apache/http/impl/client/DefaultRequestDirector:handleResponse	(Lmy/apache/http/impl/client/RoutedRequest;Lmy/apache/http/HttpResponse;Lmy/apache/http/protocol/HttpContext;)Lmy/apache/http/impl/client/RoutedRequest;
    //   738: astore 33
    //   740: aload 33
    //   742: ifnonnull +172 -> 914
    //   745: iconst_1
    //   746: istore 8
    //   748: aload_0
    //   749: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   752: ifnull -589 -> 163
    //   755: aload 18
    //   757: ifnonnull +26 -> 783
    //   760: aload_0
    //   761: getfield 138	my/apache/http/impl/client/DefaultRequestDirector:userTokenHandler	Lmy/apache/http/client/UserTokenHandler;
    //   764: aload_3
    //   765: invokeinterface 716 2 0
    //   770: astore 18
    //   772: aload_3
    //   773: ldc_w 615
    //   776: aload 18
    //   778: invokeinterface 224 3 0
    //   783: aload 18
    //   785: ifnull -622 -> 163
    //   788: aload_0
    //   789: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   792: aload 18
    //   794: invokeinterface 719 2 0
    //   799: goto -636 -> 163
    //   802: astore 13
    //   804: new 721	java/io/InterruptedIOException
    //   807: dup
    //   808: ldc_w 723
    //   811: invokespecial 724	java/io/InterruptedIOException:<init>	(Ljava/lang/String;)V
    //   814: astore 14
    //   816: aload 14
    //   818: aload 13
    //   820: invokevirtual 728	java/io/InterruptedIOException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   823: pop
    //   824: aload 14
    //   826: athrow
    //   827: astore 37
    //   829: invokestatic 734	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   832: invokevirtual 737	java/lang/Thread:interrupt	()V
    //   835: new 721	java/io/InterruptedIOException
    //   838: dup
    //   839: invokespecial 738	java/io/InterruptedIOException:<init>	()V
    //   842: athrow
    //   843: astore 12
    //   845: aload_0
    //   846: invokespecial 739	my/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   849: aload 12
    //   851: athrow
    //   852: astore 19
    //   854: aload_0
    //   855: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   858: invokeinterface 192 1 0
    //   863: ifeq +17 -> 880
    //   866: aload_0
    //   867: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   870: aload 19
    //   872: invokevirtual 740	my/apache/http/impl/client/TunnelRefusedException:getMessage	()Ljava/lang/String;
    //   875: invokeinterface 300 2 0
    //   880: aload 19
    //   882: invokevirtual 744	my/apache/http/impl/client/TunnelRefusedException:getResponse	()Lmy/apache/http/HttpResponse;
    //   885: astore 9
    //   887: goto -719 -> 168
    //   890: aload 16
    //   892: invokevirtual 664	my/apache/http/impl/client/RequestWrapper:getURI	()Ljava/net/URI;
    //   895: astore 26
    //   897: aload 26
    //   899: invokevirtual 747	java/net/URI:isAbsolute	()Z
    //   902: ifeq -420 -> 482
    //   905: aload 26
    //   907: invokestatic 753	my/apache/http/client/utils/URIUtils:extractHost	(Ljava/net/URI;)Lmy/apache/http/HttpHost;
    //   910: astore_1
    //   911: goto -429 -> 482
    //   914: iload 7
    //   916: ifeq +45 -> 961
    //   919: aload 9
    //   921: invokeinterface 492 1 0
    //   926: invokestatic 498	my/apache/http/util/EntityUtils:consume	(Lmy/apache/http/HttpEntity;)V
    //   929: aload_0
    //   930: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   933: invokeinterface 515 1 0
    //   938: aload 33
    //   940: invokevirtual 212	my/apache/http/impl/client/RoutedRequest:getRoute	()Lmy/apache/http/conn/routing/HttpRoute;
    //   943: aload 6
    //   945: invokevirtual 212	my/apache/http/impl/client/RoutedRequest:getRoute	()Lmy/apache/http/conn/routing/HttpRoute;
    //   948: invokevirtual 757	my/apache/http/conn/routing/HttpRoute:equals	(Ljava/lang/Object;)Z
    //   951: ifne +202 -> 1153
    //   954: aload_0
    //   955: invokevirtual 613	my/apache/http/impl/client/DefaultRequestDirector:releaseConnection	()V
    //   958: goto +195 -> 1153
    //   961: aload_0
    //   962: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   965: invokeinterface 248 1 0
    //   970: aload_0
    //   971: getfield 166	my/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lmy/apache/http/auth/AuthState;
    //   974: invokevirtual 761	my/apache/http/auth/AuthState:getState	()Lmy/apache/http/auth/AuthProtocolState;
    //   977: getstatic 767	my/apache/http/auth/AuthProtocolState:CHALLENGED	Lmy/apache/http/auth/AuthProtocolState;
    //   980: invokevirtual 771	my/apache/http/auth/AuthProtocolState:compareTo	(Ljava/lang/Enum;)I
    //   983: ifle +47 -> 1030
    //   986: aload_0
    //   987: getfield 166	my/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lmy/apache/http/auth/AuthState;
    //   990: invokevirtual 775	my/apache/http/auth/AuthState:getAuthScheme	()Lmy/apache/http/auth/AuthScheme;
    //   993: ifnull +37 -> 1030
    //   996: aload_0
    //   997: getfield 166	my/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lmy/apache/http/auth/AuthState;
    //   1000: invokevirtual 775	my/apache/http/auth/AuthState:getAuthScheme	()Lmy/apache/http/auth/AuthScheme;
    //   1003: invokeinterface 780 1 0
    //   1008: ifeq +22 -> 1030
    //   1011: aload_0
    //   1012: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   1015: ldc_w 782
    //   1018: invokeinterface 300 2 0
    //   1023: aload_0
    //   1024: getfield 166	my/apache/http/impl/client/DefaultRequestDirector:proxyAuthState	Lmy/apache/http/auth/AuthState;
    //   1027: invokevirtual 785	my/apache/http/auth/AuthState:reset	()V
    //   1030: aload_0
    //   1031: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   1034: invokevirtual 761	my/apache/http/auth/AuthState:getState	()Lmy/apache/http/auth/AuthProtocolState;
    //   1037: getstatic 767	my/apache/http/auth/AuthProtocolState:CHALLENGED	Lmy/apache/http/auth/AuthProtocolState;
    //   1040: invokevirtual 771	my/apache/http/auth/AuthProtocolState:compareTo	(Ljava/lang/Enum;)I
    //   1043: ifle -105 -> 938
    //   1046: aload_0
    //   1047: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   1050: invokevirtual 775	my/apache/http/auth/AuthState:getAuthScheme	()Lmy/apache/http/auth/AuthScheme;
    //   1053: ifnull -115 -> 938
    //   1056: aload_0
    //   1057: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   1060: invokevirtual 775	my/apache/http/auth/AuthState:getAuthScheme	()Lmy/apache/http/auth/AuthScheme;
    //   1063: invokeinterface 780 1 0
    //   1068: ifeq -130 -> 938
    //   1071: aload_0
    //   1072: getfield 109	my/apache/http/impl/client/DefaultRequestDirector:log	Lorg/apache/commons/logging/Log;
    //   1075: ldc_w 787
    //   1078: invokeinterface 300 2 0
    //   1083: aload_0
    //   1084: getfield 164	my/apache/http/impl/client/DefaultRequestDirector:targetAuthState	Lmy/apache/http/auth/AuthState;
    //   1087: invokevirtual 785	my/apache/http/auth/AuthState:reset	()V
    //   1090: goto -152 -> 938
    //   1093: astore 11
    //   1095: aload_0
    //   1096: invokespecial 739	my/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   1099: aload 11
    //   1101: athrow
    //   1102: new 789	my/apache/http/conn/BasicManagedEntity
    //   1105: dup
    //   1106: aload 9
    //   1108: invokeinterface 492 1 0
    //   1113: aload_0
    //   1114: getfield 155	my/apache/http/impl/client/DefaultRequestDirector:managedConn	Lmy/apache/http/conn/ManagedClientConnection;
    //   1117: iload 7
    //   1119: invokespecial 792	my/apache/http/conn/BasicManagedEntity:<init>	(Lmy/apache/http/HttpEntity;Lmy/apache/http/conn/ManagedClientConnection;Z)V
    //   1122: astore 20
    //   1124: aload 9
    //   1126: aload 20
    //   1128: invokeinterface 505 2 0
    //   1133: aload 9
    //   1135: areturn
    //   1136: astore 10
    //   1138: aload_0
    //   1139: invokespecial 739	my/apache/http/impl/client/DefaultRequestDirector:abortConnection	()V
    //   1142: aload 10
    //   1144: athrow
    //   1145: ldc_w 794
    //   1148: astore 32
    //   1150: goto -462 -> 688
    //   1153: aload 33
    //   1155: astore 6
    //   1157: goto -409 -> 748
    //
    // Exception table:
    //   from	to	target	type
    //   173	198	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   203	212	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   212	216	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   229	294	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   294	303	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   303	319	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   319	386	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   386	406	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   406	413	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   413	423	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   428	463	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   463	482	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   486	492	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   492	562	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   567	618	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   623	649	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   656	688	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   688	715	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   715	729	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   729	740	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   748	755	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   760	783	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   788	799	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   829	843	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   854	880	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   880	887	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   890	911	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   919	938	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   938	958	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   961	1030	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   1030	1090	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   1102	1133	802	my/apache/http/impl/conn/ConnectionShutdownException
    //   303	319	827	java/lang/InterruptedException
    //   173	198	843	my/apache/http/HttpException
    //   203	212	843	my/apache/http/HttpException
    //   212	216	843	my/apache/http/HttpException
    //   229	294	843	my/apache/http/HttpException
    //   294	303	843	my/apache/http/HttpException
    //   303	319	843	my/apache/http/HttpException
    //   319	386	843	my/apache/http/HttpException
    //   386	406	843	my/apache/http/HttpException
    //   406	413	843	my/apache/http/HttpException
    //   413	423	843	my/apache/http/HttpException
    //   428	463	843	my/apache/http/HttpException
    //   463	482	843	my/apache/http/HttpException
    //   486	492	843	my/apache/http/HttpException
    //   492	562	843	my/apache/http/HttpException
    //   567	618	843	my/apache/http/HttpException
    //   623	649	843	my/apache/http/HttpException
    //   656	688	843	my/apache/http/HttpException
    //   688	715	843	my/apache/http/HttpException
    //   715	729	843	my/apache/http/HttpException
    //   729	740	843	my/apache/http/HttpException
    //   748	755	843	my/apache/http/HttpException
    //   760	783	843	my/apache/http/HttpException
    //   788	799	843	my/apache/http/HttpException
    //   829	843	843	my/apache/http/HttpException
    //   854	880	843	my/apache/http/HttpException
    //   880	887	843	my/apache/http/HttpException
    //   890	911	843	my/apache/http/HttpException
    //   919	938	843	my/apache/http/HttpException
    //   938	958	843	my/apache/http/HttpException
    //   961	1030	843	my/apache/http/HttpException
    //   1030	1090	843	my/apache/http/HttpException
    //   1102	1133	843	my/apache/http/HttpException
    //   406	413	852	my/apache/http/impl/client/TunnelRefusedException
    //   173	198	1093	java/io/IOException
    //   203	212	1093	java/io/IOException
    //   212	216	1093	java/io/IOException
    //   229	294	1093	java/io/IOException
    //   294	303	1093	java/io/IOException
    //   303	319	1093	java/io/IOException
    //   319	386	1093	java/io/IOException
    //   386	406	1093	java/io/IOException
    //   406	413	1093	java/io/IOException
    //   413	423	1093	java/io/IOException
    //   428	463	1093	java/io/IOException
    //   463	482	1093	java/io/IOException
    //   486	492	1093	java/io/IOException
    //   492	562	1093	java/io/IOException
    //   567	618	1093	java/io/IOException
    //   623	649	1093	java/io/IOException
    //   656	688	1093	java/io/IOException
    //   688	715	1093	java/io/IOException
    //   715	729	1093	java/io/IOException
    //   729	740	1093	java/io/IOException
    //   748	755	1093	java/io/IOException
    //   760	783	1093	java/io/IOException
    //   788	799	1093	java/io/IOException
    //   829	843	1093	java/io/IOException
    //   854	880	1093	java/io/IOException
    //   880	887	1093	java/io/IOException
    //   890	911	1093	java/io/IOException
    //   919	938	1093	java/io/IOException
    //   938	958	1093	java/io/IOException
    //   961	1030	1093	java/io/IOException
    //   1030	1090	1093	java/io/IOException
    //   1102	1133	1093	java/io/IOException
    //   173	198	1136	java/lang/RuntimeException
    //   203	212	1136	java/lang/RuntimeException
    //   212	216	1136	java/lang/RuntimeException
    //   229	294	1136	java/lang/RuntimeException
    //   294	303	1136	java/lang/RuntimeException
    //   303	319	1136	java/lang/RuntimeException
    //   319	386	1136	java/lang/RuntimeException
    //   386	406	1136	java/lang/RuntimeException
    //   406	413	1136	java/lang/RuntimeException
    //   413	423	1136	java/lang/RuntimeException
    //   428	463	1136	java/lang/RuntimeException
    //   463	482	1136	java/lang/RuntimeException
    //   486	492	1136	java/lang/RuntimeException
    //   492	562	1136	java/lang/RuntimeException
    //   567	618	1136	java/lang/RuntimeException
    //   623	649	1136	java/lang/RuntimeException
    //   656	688	1136	java/lang/RuntimeException
    //   688	715	1136	java/lang/RuntimeException
    //   715	729	1136	java/lang/RuntimeException
    //   729	740	1136	java/lang/RuntimeException
    //   748	755	1136	java/lang/RuntimeException
    //   760	783	1136	java/lang/RuntimeException
    //   788	799	1136	java/lang/RuntimeException
    //   829	843	1136	java/lang/RuntimeException
    //   854	880	1136	java/lang/RuntimeException
    //   880	887	1136	java/lang/RuntimeException
    //   890	911	1136	java/lang/RuntimeException
    //   919	938	1136	java/lang/RuntimeException
    //   938	958	1136	java/lang/RuntimeException
    //   961	1030	1136	java/lang/RuntimeException
    //   1030	1090	1136	java/lang/RuntimeException
    //   1102	1133	1136	java/lang/RuntimeException
  }

  protected RoutedRequest handleResponse(RoutedRequest paramRoutedRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRoute localHttpRoute1 = paramRoutedRequest.getRoute();
    RequestWrapper localRequestWrapper1 = paramRoutedRequest.getRequest();
    HttpParams localHttpParams = localRequestWrapper1.getParams();
    if (HttpClientParams.isAuthenticating(localHttpParams))
    {
      Object localObject = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localObject == null)
        localObject = localHttpRoute1.getTargetHost();
      if (((HttpHost)localObject).getPort() < 0)
      {
        Scheme localScheme = this.connManager.getSchemeRegistry().getScheme((HttpHost)localObject);
        HttpHost localHttpHost3 = new HttpHost(((HttpHost)localObject).getHostName(), localScheme.getDefaultPort(), ((HttpHost)localObject).getSchemeName());
        localObject = localHttpHost3;
      }
      if ((this.authenticator.isAuthenticationRequested((HttpHost)localObject, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext)) && (this.authenticator.authenticate((HttpHost)localObject, paramHttpResponse, this.targetAuthStrategy, this.targetAuthState, paramHttpContext)));
      HttpHost localHttpHost2;
      do
      {
        return paramRoutedRequest;
        localHttpHost2 = localHttpRoute1.getProxyHost();
        if (!this.authenticator.isAuthenticationRequested(localHttpHost2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext))
          break;
        if (localHttpHost2 == null)
          localHttpHost2 = localHttpRoute1.getTargetHost();
      }
      while (this.authenticator.authenticate(localHttpHost2, paramHttpResponse, this.proxyAuthStrategy, this.proxyAuthState, paramHttpContext));
    }
    if ((HttpClientParams.isRedirecting(localHttpParams)) && (this.redirectStrategy.isRedirected(localRequestWrapper1, paramHttpResponse, paramHttpContext)))
    {
      if (this.redirectCount >= this.maxRedirects)
        throw new RedirectException("Maximum redirects (" + this.maxRedirects + ") exceeded");
      this.redirectCount = (1 + this.redirectCount);
      this.virtualHost = null;
      HttpUriRequest localHttpUriRequest = this.redirectStrategy.getRedirect(localRequestWrapper1, paramHttpResponse, paramHttpContext);
      localHttpUriRequest.setHeaders(localRequestWrapper1.getOriginal().getAllHeaders());
      URI localURI = localHttpUriRequest.getURI();
      HttpHost localHttpHost1 = URIUtils.extractHost(localURI);
      if (localHttpHost1 == null)
        throw new ProtocolException("Redirect URI does not specify a valid host name: " + localURI);
      if (!localHttpRoute1.getTargetHost().equals(localHttpHost1))
      {
        this.log.debug("Resetting target auth state");
        this.targetAuthState.reset();
        AuthScheme localAuthScheme = this.proxyAuthState.getAuthScheme();
        if ((localAuthScheme != null) && (localAuthScheme.isConnectionBased()))
        {
          this.log.debug("Resetting proxy auth state");
          this.proxyAuthState.reset();
        }
      }
      RequestWrapper localRequestWrapper2 = wrapRequest(localHttpUriRequest);
      localRequestWrapper2.setParams(localHttpParams);
      HttpRoute localHttpRoute2 = determineRoute(localHttpHost1, localRequestWrapper2, paramHttpContext);
      RoutedRequest localRoutedRequest = new RoutedRequest(localRequestWrapper2, localHttpRoute2);
      if (this.log.isDebugEnabled())
        this.log.debug("Redirecting to '" + localURI + "' via " + localHttpRoute2);
      return localRoutedRequest;
    }
    return null;
  }

  protected void releaseConnection()
  {
    try
    {
      this.managedConn.releaseConnection();
      this.managedConn = null;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        this.log.debug("IOException releasing connection", localIOException);
    }
  }

  protected void rewriteRequestURI(RequestWrapper paramRequestWrapper, HttpRoute paramHttpRoute)
    throws ProtocolException
  {
    try
    {
      URI localURI1 = paramRequestWrapper.getURI();
      Object localObject;
      if ((paramHttpRoute.getProxyHost() != null) && (!paramHttpRoute.isTunnelled()))
        if (!localURI1.isAbsolute())
          localObject = URIUtils.rewriteURI(localURI1, paramHttpRoute.getTargetHost(), true);
      while (true)
      {
        paramRequestWrapper.setURI((URI)localObject);
        return;
        localObject = URIUtils.rewriteURI(localURI1);
        continue;
        if (localURI1.isAbsolute())
        {
          localObject = URIUtils.rewriteURI(localURI1, null, true);
        }
        else
        {
          URI localURI2 = URIUtils.rewriteURI(localURI1);
          localObject = localURI2;
        }
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new ProtocolException("Invalid URI: " + paramRequestWrapper.getRequestLine().getUri(), localURISyntaxException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultRequestDirector
 * JD-Core Version:    0.6.2
 */