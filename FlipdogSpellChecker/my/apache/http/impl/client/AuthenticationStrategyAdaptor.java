package my.apache.http.impl.client;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import my.apache.http.Header;
import my.apache.http.HttpHost;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthOption;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthScope;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.Credentials;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.client.AuthCache;
import my.apache.http.client.AuthenticationHandler;
import my.apache.http.client.AuthenticationStrategy;
import my.apache.http.client.CredentialsProvider;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
@Immutable
class AuthenticationStrategyAdaptor
  implements AuthenticationStrategy
{
  private final AuthenticationHandler handler;
  private final Log log = LogFactory.getLog(getClass());

  public AuthenticationStrategyAdaptor(AuthenticationHandler paramAuthenticationHandler)
  {
    this.handler = paramAuthenticationHandler;
  }

  private boolean isCachable(AuthScheme paramAuthScheme)
  {
    if ((paramAuthScheme == null) || (!paramAuthScheme.isComplete()));
    String str;
    do
    {
      return false;
      str = paramAuthScheme.getSchemeName();
    }
    while ((!str.equalsIgnoreCase("Basic")) && (!str.equalsIgnoreCase("Digest")));
    return true;
  }

  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache == null)
      return;
    if (this.log.isDebugEnabled())
      this.log.debug("Removing from cache '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
    localAuthCache.remove(paramHttpHost);
  }

  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (isCachable(paramAuthScheme))
    {
      if (localObject == null)
      {
        localObject = new BasicAuthCache();
        paramHttpContext.setAttribute("http.auth.auth-cache", localObject);
      }
      if (this.log.isDebugEnabled())
        this.log.debug("Caching '" + paramAuthScheme.getSchemeName() + "' auth scheme for " + paramHttpHost);
      ((AuthCache)localObject).put(paramHttpHost, paramAuthScheme);
    }
  }

  public Map<String, Header> getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    return this.handler.getChallenges(paramHttpResponse, paramHttpContext);
  }

  public AuthenticationHandler getHandler()
  {
    return this.handler;
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return this.handler.isAuthenticationRequested(paramHttpResponse, paramHttpContext);
  }

  public Queue<AuthOption> select(Map<String, Header> paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    if (paramMap == null)
      throw new IllegalArgumentException("Map of auth challenges may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    LinkedList localLinkedList = new LinkedList();
    CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
    if (localCredentialsProvider == null)
      this.log.debug("Credentials provider not set in the context");
    do
      while (true)
      {
        return localLinkedList;
        try
        {
          AuthScheme localAuthScheme = this.handler.selectScheme(paramMap, paramHttpResponse, paramHttpContext);
          localAuthScheme.processChallenge((Header)paramMap.get(localAuthScheme.getSchemeName().toLowerCase(Locale.US)));
          Credentials localCredentials = localCredentialsProvider.getCredentials(new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName()));
          if (localCredentials != null)
          {
            localLinkedList.add(new AuthOption(localAuthScheme, localCredentials));
            return localLinkedList;
          }
        }
        catch (AuthenticationException localAuthenticationException)
        {
        }
      }
    while (!this.log.isWarnEnabled());
    this.log.warn(localAuthenticationException.getMessage(), localAuthenticationException);
    return localLinkedList;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.AuthenticationStrategyAdaptor
 * JD-Core Version:    0.6.2
 */