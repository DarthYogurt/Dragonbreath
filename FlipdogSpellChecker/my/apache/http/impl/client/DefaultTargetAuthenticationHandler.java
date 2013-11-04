package my.apache.http.impl.client;

import java.util.List;
import java.util.Map;
import my.apache.http.Header;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;

@Deprecated
@Immutable
public class DefaultTargetAuthenticationHandler extends AbstractAuthenticationHandler
{
  protected List<String> getAuthPreferences(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    List localList = (List)paramHttpResponse.getParams().getParameter("http.auth.target-scheme-pref");
    if (localList != null)
      return localList;
    return super.getAuthPreferences(paramHttpResponse, paramHttpContext);
  }

  public Map<String, Header> getChallenges(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws MalformedChallengeException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    return parseChallenges(paramHttpResponse.getHeaders("WWW-Authenticate"));
  }

  public boolean isAuthenticationRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    return paramHttpResponse.getStatusLine().getStatusCode() == 401;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultTargetAuthenticationHandler
 * JD-Core Version:    0.6.2
 */