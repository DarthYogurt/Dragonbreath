package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpProtocolParams;

@Immutable
public class RequestUserAgent
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpRequest.containsHeader("User-Agent"))
    {
      String str = HttpProtocolParams.getUserAgent(paramHttpRequest.getParams());
      if (str != null)
        paramHttpRequest.addHeader("User-Agent", str);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.RequestUserAgent
 * JD-Core Version:    0.6.2
 */