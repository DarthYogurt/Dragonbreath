package my.apache.http.client.protocol;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;

@Immutable
public class RequestDefaultHeaders
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (paramHttpRequest.getRequestLine().getMethod().equalsIgnoreCase("CONNECT"));
    while (true)
    {
      return;
      Collection localCollection = (Collection)paramHttpRequest.getParams().getParameter("http.default-headers");
      if (localCollection != null)
      {
        Iterator localIterator = localCollection.iterator();
        while (localIterator.hasNext())
          paramHttpRequest.addHeader((Header)localIterator.next());
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.RequestDefaultHeaders
 * JD-Core Version:    0.6.2
 */