package my.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthState;
import my.apache.http.auth.Credentials;
import my.apache.http.client.UserTokenHandler;
import my.apache.http.conn.HttpRoutedConnection;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultUserTokenHandler
  implements UserTokenHandler
{
  private static Principal getAuthPrincipal(AuthState paramAuthState)
  {
    AuthScheme localAuthScheme = paramAuthState.getAuthScheme();
    if ((localAuthScheme != null) && (localAuthScheme.isComplete()) && (localAuthScheme.isConnectionBased()))
    {
      Credentials localCredentials = paramAuthState.getCredentials();
      if (localCredentials != null)
        return localCredentials.getUserPrincipal();
    }
    return null;
  }

  public Object getUserToken(HttpContext paramHttpContext)
  {
    AuthState localAuthState = (AuthState)paramHttpContext.getAttribute("http.auth.target-scope");
    Principal localPrincipal = null;
    if (localAuthState != null)
    {
      localPrincipal = getAuthPrincipal(localAuthState);
      if (localPrincipal == null)
        localPrincipal = getAuthPrincipal((AuthState)paramHttpContext.getAttribute("http.auth.proxy-scope"));
    }
    if (localPrincipal == null)
    {
      HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (localHttpRoutedConnection.isOpen())
      {
        SSLSession localSSLSession = localHttpRoutedConnection.getSSLSession();
        if (localSSLSession != null)
          localPrincipal = localSSLSession.getLocalPrincipal();
      }
    }
    return localPrincipal;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultUserTokenHandler
 * JD-Core Version:    0.6.2
 */