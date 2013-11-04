package my.apache.http.client.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthProtocolState;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthScope;
import my.apache.http.auth.AuthState;
import my.apache.http.auth.Credentials;
import my.apache.http.client.AuthCache;
import my.apache.http.client.CredentialsProvider;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Immutable
public class RequestAuthCache
  implements HttpRequestInterceptor
{
  private final Log log = LogFactory.getLog(getClass());

  private void doPreemptiveAuth(HttpHost paramHttpHost, AuthScheme paramAuthScheme, AuthState paramAuthState, CredentialsProvider paramCredentialsProvider)
  {
    String str = paramAuthScheme.getSchemeName();
    if (this.log.isDebugEnabled())
      this.log.debug("Re-using cached '" + str + "' auth scheme for " + paramHttpHost);
    Credentials localCredentials = paramCredentialsProvider.getCredentials(new AuthScope(paramHttpHost, AuthScope.ANY_REALM, str));
    if (localCredentials != null)
    {
      if ("BASIC".equalsIgnoreCase(paramAuthScheme.getSchemeName()))
        paramAuthState.setState(AuthProtocolState.CHALLENGED);
      while (true)
      {
        paramAuthState.update(paramAuthScheme, localCredentials);
        return;
        paramAuthState.setState(AuthProtocolState.SUCCESS);
      }
    }
    this.log.debug("No credentials for preemptive authentication");
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache == null)
      this.log.debug("Auth cache not set in the context");
    CredentialsProvider localCredentialsProvider;
    HttpHost localHttpHost2;
    AuthState localAuthState2;
    AuthScheme localAuthScheme1;
    do
    {
      do
      {
        return;
        localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
        if (localCredentialsProvider == null)
        {
          this.log.debug("Credentials provider not set in the context");
          return;
        }
        HttpHost localHttpHost1 = (HttpHost)paramHttpContext.getAttribute("http.target_host");
        if (localHttpHost1.getPort() < 0)
        {
          Scheme localScheme = ((SchemeRegistry)paramHttpContext.getAttribute("http.scheme-registry")).getScheme(localHttpHost1);
          localHttpHost1 = new HttpHost(localHttpHost1.getHostName(), localScheme.resolvePort(localHttpHost1.getPort()), localHttpHost1.getSchemeName());
        }
        AuthState localAuthState1 = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
        if ((localHttpHost1 != null) && (localAuthState1 != null) && (localAuthState1.getState() == AuthProtocolState.UNCHALLENGED))
        {
          AuthScheme localAuthScheme2 = localAuthCache.get(localHttpHost1);
          if (localAuthScheme2 != null)
            doPreemptiveAuth(localHttpHost1, localAuthScheme2, localAuthState1, localCredentialsProvider);
        }
        localHttpHost2 = (HttpHost)paramHttpContext.getAttribute("http.proxy_host");
        localAuthState2 = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
      }
      while ((localHttpHost2 == null) || (localAuthState2 == null) || (localAuthState2.getState() != AuthProtocolState.UNCHALLENGED));
      localAuthScheme1 = localAuthCache.get(localHttpHost2);
    }
    while (localAuthScheme1 == null);
    doPreemptiveAuth(localHttpHost2, localAuthScheme1, localAuthState2, localCredentialsProvider);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestAuthCache
 * JD-Core Version:    0.6.2
 */