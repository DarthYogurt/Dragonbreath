package my.apache.http.impl.client;

import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import my.apache.http.Header;
import my.apache.http.HttpHost;
import my.apache.http.HttpResponse;
import my.apache.http.auth.AuthProtocolState;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthState;
import my.apache.http.auth.MalformedChallengeException;
import my.apache.http.client.AuthenticationStrategy;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpAuthenticator
{
  private final Log log;

  public HttpAuthenticator()
  {
    this(null);
  }

  public HttpAuthenticator(Log paramLog)
  {
    if (paramLog != null);
    while (true)
    {
      this.log = paramLog;
      return;
      paramLog = LogFactory.getLog(getClass());
    }
  }

  public boolean authenticate(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    while (true)
    {
      Map localMap;
      AuthScheme localAuthScheme;
      try
      {
        if (this.log.isDebugEnabled())
          this.log.debug(paramHttpHost.toHostString() + " requested authentication");
        localMap = paramAuthenticationStrategy.getChallenges(paramHttpHost, paramHttpResponse, paramHttpContext);
        if (localMap.isEmpty())
        {
          this.log.debug("Response contains no authentication challenges");
          return false;
        }
        localAuthScheme = paramAuthState.getAuthScheme();
        switch ($SWITCH_TABLE$my$apache$http$auth$AuthProtocolState()[paramAuthState.getState().ordinal()])
        {
        default:
          Queue localQueue = paramAuthenticationStrategy.select(localMap, paramHttpHost, paramHttpResponse, paramHttpContext);
          if ((localQueue == null) || (localQueue.isEmpty()))
            break;
          if (this.log.isDebugEnabled())
            this.log.debug("Selected authentication options: " + localQueue);
          paramAuthState.setState(AuthProtocolState.CHALLENGED);
          paramAuthState.update(localQueue);
          return true;
        case 5:
          paramAuthState.reset();
          continue;
        case 2:
        case 3:
        case 1:
        case 4:
        }
      }
      catch (MalformedChallengeException localMalformedChallengeException)
      {
        if (this.log.isWarnEnabled())
          this.log.warn("Malformed challenge: " + localMalformedChallengeException.getMessage());
        paramAuthState.reset();
        return false;
      }
      if (localAuthScheme == null)
      {
        this.log.debug("Auth scheme is null");
        paramAuthenticationStrategy.authFailed(paramHttpHost, null, paramHttpContext);
        paramAuthState.reset();
        paramAuthState.setState(AuthProtocolState.FAILURE);
        return false;
      }
      if (localAuthScheme != null)
      {
        Header localHeader = (Header)localMap.get(localAuthScheme.getSchemeName().toLowerCase(Locale.US));
        if (localHeader != null)
        {
          this.log.debug("Authorization challenge processed");
          localAuthScheme.processChallenge(localHeader);
          if (localAuthScheme.isComplete())
          {
            this.log.debug("Authentication failed");
            paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
            paramAuthState.reset();
            paramAuthState.setState(AuthProtocolState.FAILURE);
            return false;
          }
          paramAuthState.setState(AuthProtocolState.HANDSHAKE);
          return true;
        }
        paramAuthState.reset();
      }
    }
    return false;
    return false;
  }

  public boolean isAuthenticationRequested(HttpHost paramHttpHost, HttpResponse paramHttpResponse, AuthenticationStrategy paramAuthenticationStrategy, AuthState paramAuthState, HttpContext paramHttpContext)
  {
    if (paramAuthenticationStrategy.isAuthenticationRequested(paramHttpHost, paramHttpResponse, paramHttpContext))
    {
      if (paramAuthState.getState() == AuthProtocolState.SUCCESS)
        paramAuthenticationStrategy.authFailed(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
      this.log.debug("Authentication required");
      return true;
    }
    switch ($SWITCH_TABLE$my$apache$http$auth$AuthProtocolState()[paramAuthState.getState().ordinal()])
    {
    case 4:
    default:
      paramAuthState.setState(AuthProtocolState.UNCHALLENGED);
    case 5:
    case 2:
    case 3:
    }
    while (true)
    {
      return false;
      this.log.debug("Authentication succeeded");
      paramAuthState.setState(AuthProtocolState.SUCCESS);
      paramAuthenticationStrategy.authSucceeded(paramHttpHost, paramAuthState.getAuthScheme(), paramHttpContext);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.HttpAuthenticator
 * JD-Core Version:    0.6.2
 */