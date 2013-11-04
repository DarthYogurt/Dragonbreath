package my.apache.http.client;

import java.util.Map;
import java.util.Queue;
import my.apache.http.Header;
import my.apache.http.HttpHost;
import my.apache.http.HttpResponse;
import my.apache.http.auth.AuthOption;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.protocol.HttpContext;

public abstract interface AuthenticationStrategy
{
  public abstract void authFailed(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext);

  public abstract void authSucceeded(HttpHost paramHttpHost, AuthScheme paramAuthScheme, HttpContext paramHttpContext);

  public abstract Map<String, Header> getChallenges(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException;

  public abstract boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext);

  public abstract Queue<AuthOption> select(Map<String, Header> paramMap, HttpHost paramHttpHost, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.AuthenticationStrategy
 * JD-Core Version:    0.6.2
 */