package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpParams;

@Immutable
public class ResponseServer
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if (!paramHttpResponse.containsHeader("Server"))
    {
      String str = (String)paramHttpResponse.getParams().getParameter("http.origin-server");
      if (str != null)
        paramHttpResponse.addHeader("Server", str);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.ResponseServer
 * JD-Core Version:    0.6.2
 */