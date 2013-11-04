package my.apache.http.client.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.HttpRoutedConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Immutable
public class RequestClientConnControl
  implements HttpRequestInterceptor
{
  private static final String PROXY_CONN_DIRECTIVE = "Proxy-Connection";
  private final Log log = LogFactory.getLog(getClass());

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"))
      paramHttpRequest.setHeader("Proxy-Connection", "Keep-Alive");
    HttpRoute localHttpRoute;
    do
    {
      return;
      HttpRoutedConnection localHttpRoutedConnection = (HttpRoutedConnection)paramHttpContext.getAttribute("http.connection");
      if (localHttpRoutedConnection == null)
      {
        this.log.debug("HTTP connection not set in the context");
        return;
      }
      localHttpRoute = localHttpRoutedConnection.getRoute();
      if (((localHttpRoute.getHopCount() == 1) || (localHttpRoute.isTunnelled())) && (!paramHttpRequest.containsHeader("Connection")))
        paramHttpRequest.addHeader("Connection", "Keep-Alive");
    }
    while ((localHttpRoute.getHopCount() != 2) || (localHttpRoute.isTunnelled()) || (paramHttpRequest.containsHeader("Proxy-Connection")));
    paramHttpRequest.addHeader("Proxy-Connection", "Keep-Alive");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestClientConnControl
 * JD-Core Version:    0.6.2
 */