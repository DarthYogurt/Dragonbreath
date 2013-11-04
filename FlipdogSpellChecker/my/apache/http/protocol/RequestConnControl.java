package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class RequestConnControl
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"));
    while (paramHttpRequest.containsHeader("Connection"))
      return;
    paramHttpRequest.addHeader("Connection", "Keep-Alive");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.RequestConnControl
 * JD-Core Version:    0.6.2
 */