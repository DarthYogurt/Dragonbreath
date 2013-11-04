package my.apache.http.client;

import java.util.Map;
import my.apache.http.Header;
import my.apache.http.HttpResponse;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.protocol.HttpContext;

@Deprecated
public abstract interface AuthenticationHandler
{
  public abstract Map<String, Header> getChallenges(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException;

  public abstract boolean isAuthenticationRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext);

  public abstract AuthScheme selectScheme(Map<String, Header> paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws AuthenticationException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.AuthenticationHandler
 * JD-Core Version:    0.6.2
 */