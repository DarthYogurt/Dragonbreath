package my.apache.http.client.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthState;
import my.apache.http.client.AuthCache;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.impl.client.BasicAuthCache;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@Immutable
public class ResponseAuthCache
  implements HttpResponseInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void cache(AuthCache paramAuthCache, HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Caching '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
    paramAuthCache.put(paramHttpHost, paramAuthScheme);
  }

  private boolean isCachable(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme == null) || (!localAuthScheme.isComplete()));
    String str;
    do
    {
      return false;
      str = localAuthScheme.getSchemeName();
    }
    while ((!str.equalsIgnoreCase("Basic")) && (!str.equalsIgnoreCase("Digest")));
    return true;
  }

  private void uncache(AuthCache paramAuthCache, HttpHost paramHttpHost, AuthScheme paramAuthScheme)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Removing from cache '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
    paramAuthCache.remove(paramHttpHost);
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    HttpHost localHttpHost1 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
    AuthState localAuthState1 = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    if ((localHttpHost1 != null) && (localAuthState1 != null))
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Target auth state: " + localAuthState1.getState());
      if (isCachable(localAuthState1))
      {
        SchemeRegistry localSchemeRegistry = (SchemeRegistry)paramHttpContext.getAttribute("http.scheme-registry");
        if (localHttpHost1.getPort() < 0)
        {
          Scheme localScheme = localSchemeRegistry.getScheme(localHttpHost1);
          localHttpHost1 = new HttpHost(localHttpHost1.getHostName(), localScheme.resolvePort(localHttpHost1.getPort()), localHttpHost1.getSchemeName());
        }
        if (localObject == null)
        {
          localObject = new BasicAuthCache();
          paramHttpContext.setAttribute("http.auth.auth-cache", localObject);
        }
        switch ($SWITCH_TABLE$my$apache$http$auth$AuthProtocolState()[localAuthState1.getState().ordinal()])
        {
        case 3:
        default:
        case 2:
        case 4:
        }
      }
    }
    HttpHost localHttpHost2;
    AuthState localAuthState2;
    while (true)
    {
      localHttpHost2 = (HttpHost)paramHttpContext.getAttribute("http.proxy_host");
      localAuthState2 = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
      if ((localHttpHost2 != null) && (localAuthState2 != null))
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Proxy auth state: " + localAuthState2.getState());
        if (isCachable(localAuthState2))
          if (localObject == null)
          {
            localObject = new BasicAuthCache();
            paramHttpContext.setAttribute("http.auth.auth-cache", localObject);
          }
      }
      switch ($SWITCH_TABLE$my$apache$http$auth$AuthProtocolState()[localAuthState2.getState().ordinal()])
      {
      case 3:
      default:
        return;
        cache((AuthCache)localObject, localHttpHost1, localAuthState1.getAuthScheme());
        continue;
        uncache((AuthCache)localObject, localHttpHost1, localAuthState1.getAuthScheme());
      case 2:
      case 4:
      }
    }
    cache((AuthCache)localObject, localHttpHost2, localAuthState2.getAuthScheme());
    return;
    uncache((AuthCache)localObject, localHttpHost2, localAuthState2.getAuthScheme());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.ResponseAuthCache
 * JD-Core Version:    0.6.2
 */