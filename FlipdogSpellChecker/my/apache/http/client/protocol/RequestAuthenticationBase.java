package my.apache.http.client.protocol;

import java.io.IOException;
import java.util.Queue;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.auth.AuthOption;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthState;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.ContextAwareAuthScheme;
import my.apache.http.auth.Credentials;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

abstract class RequestAuthenticationBase
  implements HttpRequestInterceptor
{
  final Log log = LogFactory.getLog(getClass());

  private Header authenticate(AuthScheme paramAuthScheme, Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    if (paramAuthScheme == null)
      throw new IllegalStateException("Auth state object is null");
    if ((paramAuthScheme instanceof ContextAwareAuthScheme))
      return ((ContextAwareAuthScheme)paramAuthScheme).authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
    return paramAuthScheme.authenticate(paramCredentials, paramHttpRequest);
  }

  private void ensureAuthScheme(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme == null)
      throw new IllegalStateException("Auth scheme is not set");
  }

  void process(AuthState paramAuthState, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    AuthScheme localAuthScheme1 = paramAuthState.getAuthScheme();
    Credentials localCredentials1 = paramAuthState.getCredentials();
    switch ($SWITCH_TABLE$my$apache$http$auth$AuthProtocolState()[paramAuthState.getState().ordinal()])
    {
    case 3:
    default:
    case 4:
    case 5:
    case 2:
    }
    while (true)
    {
      if (localAuthScheme1 != null);
      try
      {
        paramHttpRequest.addHeader(authenticate(localAuthScheme1, localCredentials1, paramHttpRequest, paramHttpContext));
        while (true)
        {
          return;
          ensureAuthScheme(localAuthScheme1);
          if (!localAuthScheme1.isConnectionBased())
            break;
          return;
          Queue localQueue = paramAuthState.getAuthOptions();
          if (localQueue == null)
            break label265;
          while (!localQueue.isEmpty())
          {
            AuthOption localAuthOption = (AuthOption)localQueue.remove();
            AuthScheme localAuthScheme2 = localAuthOption.getAuthScheme();
            Credentials localCredentials2 = localAuthOption.getCredentials();
            paramAuthState.update(localAuthScheme2, localCredentials2);
            if (this.log.isDebugEnabled())
              this.log.debug("Generating response to an authentication challenge using " + localAuthScheme2.getSchemeName() + " scheme");
            try
            {
              paramHttpRequest.addHeader(authenticate(localAuthScheme2, localCredentials2, paramHttpRequest, paramHttpContext));
              return;
            }
            catch (AuthenticationException localAuthenticationException2)
            {
            }
            if (this.log.isWarnEnabled())
              this.log.warn(localAuthScheme2 + " authentication error: " + localAuthenticationException2.getMessage());
          }
        }
        label265: ensureAuthScheme(localAuthScheme1);
      }
      catch (AuthenticationException localAuthenticationException1)
      {
        while (!this.log.isErrorEnabled());
        this.log.error(localAuthScheme1 + " authentication error: " + localAuthenticationException1.getMessage());
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestAuthenticationBase
 * JD-Core Version:    0.6.2
 */