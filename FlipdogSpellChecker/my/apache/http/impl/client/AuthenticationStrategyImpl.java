package my.apache.http.impl.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HttpHost;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthOption;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthSchemeRegistry;
import my.apache.http.auth.AuthScope;
import my.apache.http.auth.Credentials;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.client.AuthCache;
import my.apache.http.client.AuthenticationStrategy;
import my.apache.http.client.CredentialsProvider;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HTTP;
import my.apache.http.protocol.HttpContext;
import my.apache.http.util.CharArrayBuffer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Immutable
class AuthenticationStrategyImpl
  implements AuthenticationStrategy
{
  private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[] { "negotiate", "Kerberos", "NTLM", "Digest", "Basic" }));
  private final int challengeCode;
  private final String headerName;
  private final Log log = LogFactory.getLog(getClass());
  private final String prefParamName;

  AuthenticationStrategyImpl(int paramInt, String paramString1, String paramString2)
  {
    this.challengeCode = paramInt;
    this.headerName = paramString1;
    this.prefParamName = paramString2;
  }

  public void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    AuthCache localAuthCache = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
    if (localAuthCache != null)
    {
      if (this.log.isDebugEnabled())
        this.log.debug("Clearing cached auth scheme for " + paramHttpHost);
      localAuthCache.remove(paramHttpHost);
    }
  }

  public void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host may not be null");
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (isCachable(paramAuthScheme))
    {
      Object localObject = (AuthCache)paramHttpContext.getAttribute("http.auth.auth-cache");
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
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    Header[] arrayOfHeader = paramHttpResponse.getHeaders(this.headerName);
    HashMap localHashMap = new HashMap(arrayOfHeader.length);
    int i = arrayOfHeader.length;
    int j = 0;
    if (j >= i)
      return localHashMap;
    Header localHeader = arrayOfHeader[j];
    CharArrayBuffer localCharArrayBuffer;
    int k;
    label95: int m;
    if ((localHeader instanceof FormattedHeader))
    {
      localCharArrayBuffer = ((FormattedHeader)localHeader).getBuffer();
      k = ((FormattedHeader)localHeader).getValuePos();
      if ((k < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(k))))
        break label227;
      m = k;
    }
    while (true)
    {
      if ((k >= localCharArrayBuffer.length()) || (HTTP.isWhitespace(localCharArrayBuffer.charAt(k))))
      {
        localHashMap.put(localCharArrayBuffer.substring(m, k).toLowerCase(Locale.US), localHeader);
        j++;
        break;
        String str = localHeader.getValue();
        if (str == null)
          throw new MalformedChallengeException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(str.length());
        localCharArrayBuffer.append(str);
        k = 0;
        break label95;
        label227: k++;
        break label95;
      }
      k++;
    }
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    return paramHttpResponse.getStatusLine().getStatusCode() == this.challengeCode;
  }

  protected boolean isCachable(AuthScheme paramAuthScheme)
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
    AuthSchemeRegistry localAuthSchemeRegistry = (AuthSchemeRegistry)paramHttpContext.getAttribute("http.authscheme-registry");
    if (localAuthSchemeRegistry == null)
      this.log.debug("Auth scheme registry not set in the context");
    while (true)
    {
      return localLinkedList;
      CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramHttpContext.getAttribute("http.auth.credentials-provider");
      if (localCredentialsProvider == null)
      {
        this.log.debug("Credentials provider not set in the context");
        return localLinkedList;
      }
      List localList = (List)paramHttpResponse.getParams().getParameter(this.prefParamName);
      if (localList == null)
        localList = DEFAULT_SCHEME_PRIORITY;
      if (this.log.isDebugEnabled())
        this.log.debug("Authentication schemes in the order of preference: " + localList);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Header localHeader = (Header)paramMap.get(str.toLowerCase(Locale.US));
        if (localHeader != null)
        {
          try
          {
            AuthScheme localAuthScheme = localAuthSchemeRegistry.getAuthScheme(str, paramHttpResponse.getParams());
            localAuthScheme.processChallenge(localHeader);
            Credentials localCredentials = localCredentialsProvider.getCredentials(new AuthScope(paramHttpHost.getHostName(), paramHttpHost.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName()));
            if (localCredentials == null)
              continue;
            localLinkedList.add(new AuthOption(localAuthScheme, localCredentials));
          }
          catch (IllegalStateException localIllegalStateException)
          {
          }
          if (this.log.isWarnEnabled())
            this.log.warn("Authentication scheme " + str + " not supported");
        }
        else if (this.log.isDebugEnabled())
        {
          this.log.debug("Challenge for " + str + " authentication scheme not available");
        }
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.AuthenticationStrategyImpl
 * JD-Core Version:    0.6.2
 */