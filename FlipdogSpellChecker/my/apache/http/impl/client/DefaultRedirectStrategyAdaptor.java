package my.apache.http.impl.client;

import java.net.URI;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.ProtocolException;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.RedirectHandler;
import my.apache.http.client.RedirectStrategy;
import my.apache.http.client.methods.HttpGet;
import my.apache.http.client.methods.HttpHead;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.protocol.HttpContext;

@Deprecated
@Immutable
class DefaultRedirectStrategyAdaptor
  implements RedirectStrategy
{
  private final RedirectHandler handler;

  public DefaultRedirectStrategyAdaptor(RedirectHandler paramRedirectHandler)
  {
    this.handler = paramRedirectHandler;
  }

  public RedirectHandler getHandler()
  {
    return this.handler;
  }

  public HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    URI localURI = this.handler.getLocationURI(paramHttpResponse, paramHttpContext);
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("HEAD"))
      return new HttpHead(localURI);
    return new HttpGet(localURI);
  }

  public boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    return this.handler.isRedirectRequested(paramHttpResponse, paramHttpContext);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultRedirectStrategyAdaptor
 * JD-Core Version:    0.6.2
 */