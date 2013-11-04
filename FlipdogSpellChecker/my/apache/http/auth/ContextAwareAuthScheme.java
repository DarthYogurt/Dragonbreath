package my.apache.http.auth;

import my.apache.http.Header;
import my.apache.http.HttpRequest;
import my.apache.http.protocol.HttpContext;

public abstract interface ContextAwareAuthScheme extends AuthScheme
{
  public abstract Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.ContextAwareAuthScheme
 * JD-Core Version:    0.6.2
 */