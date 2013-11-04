package my.apache.http.client.protocol;

import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.auth.AuthSchemeRegistry;
import my.apache.http.client.CookieStore;
import my.apache.http.client.CredentialsProvider;
import my.apache.http.cookie.CookieSpecRegistry;
import my.apache.http.protocol.HttpContext;

@NotThreadSafe
public class ClientContextConfigurer
  implements ClientContext
{
  private final HttpContext context;

  public ClientContextConfigurer(HttpContext paramHttpContext)
  {
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    this.context = paramHttpContext;
  }

  public void setAuthSchemeRegistry(AuthSchemeRegistry paramAuthSchemeRegistry)
  {
    this.context.setAttribute("http.authscheme-registry", paramAuthSchemeRegistry);
  }

  public void setCookieSpecRegistry(CookieSpecRegistry paramCookieSpecRegistry)
  {
    this.context.setAttribute("http.cookiespec-registry", paramCookieSpecRegistry);
  }

  public void setCookieStore(CookieStore paramCookieStore)
  {
    this.context.setAttribute("http.cookie-store", paramCookieStore);
  }

  public void setCredentialsProvider(CredentialsProvider paramCredentialsProvider)
  {
    this.context.setAttribute("http.auth.credentials-provider", paramCredentialsProvider);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.ClientContextConfigurer
 * JD-Core Version:    0.6.2
 */