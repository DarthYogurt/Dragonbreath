package my.apache.http.client;

import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.ProtocolException;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.protocol.HttpContext;

public abstract interface RedirectStrategy
{
  public abstract HttpUriRequest getRedirect(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException;

  public abstract boolean isRedirected(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.RedirectStrategy
 * JD-Core Version:    0.6.2
 */