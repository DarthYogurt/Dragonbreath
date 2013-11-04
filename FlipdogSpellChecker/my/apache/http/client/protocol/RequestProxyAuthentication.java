package my.apache.http.client.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthState;
import my.apache.http.conn.HttpRoutedConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;

@Immutable
public class RequestProxyAuthentication extends RequestAuthenticationBase
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramHttpRequest.containsHeader("Proxy-Authorization"));
    HttpRoutedConnection localHttpRoutedConnection;
    do
    {
      return;
      localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (localHttpRoutedConnection == null)
      {
        this.log.debug("HTTP connection not set in the context");
        return;
      }
    }
    while (localHttpRoutedConnection.getRoute().isTunnelled());
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope");
    if (localAuthState == null)
    {
      this.log.debug("Proxy auth state not set in the context");
      return;
    }
    if (this.log.isDebugEnabled())
      this.log.debug("Proxy auth state: " + localAuthState.getState());
    process(localAuthState, paramHttpRequest, paramHttpContext);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestProxyAuthentication
 * JD-Core Version:    0.6.2
 */