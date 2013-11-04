package my.apache.http.protocol;

import java.io.IOException;
import java.net.InetAddress;
import my.apache.http.HttpConnection;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpInetConnection;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolException;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class RequestTargetHost
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
    if ((paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT")) && (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)));
    HttpHost localHttpHost;
    do
    {
      do
        return;
      while (paramHttpRequest.containsHeader("Host"));
      localHttpHost = (HttpHost)paramHttpContext.getAttribute("http.target_host");
      if (localHttpHost != null)
        break;
      HttpConnection localHttpConnection = (HttpConnection)paramHttpContext.getAttribute("http.connection");
      if ((localHttpConnection instanceof HttpInetConnection))
      {
        InetAddress localInetAddress = ((HttpInetConnection)localHttpConnection).getRemoteAddress();
        int i = ((HttpInetConnection)localHttpConnection).getRemotePort();
        if (localInetAddress != null)
          localHttpHost = new HttpHost(localInetAddress.getHostName(), i);
      }
      if (localHttpHost != null)
        break;
    }
    while (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0));
    throw new ProtocolException("Target host missing");
    paramHttpRequest.addHeader("Host", localHttpHost.toHostString());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.RequestTargetHost
 * JD-Core Version:    0.6.2
 */