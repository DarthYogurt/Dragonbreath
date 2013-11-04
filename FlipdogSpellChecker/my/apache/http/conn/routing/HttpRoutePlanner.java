package my.apache.http.conn.routing;

import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.protocol.HttpContext;

public abstract interface HttpRoutePlanner
{
  public abstract HttpRoute determineRoute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.routing.HttpRoutePlanner
 * JD-Core Version:    0.6.2
 */