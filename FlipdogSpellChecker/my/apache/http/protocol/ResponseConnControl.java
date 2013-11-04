package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolVersion;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class ResponseConnControl
  implements HttpResponseInterceptor
{
  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if ((i == 400) || (i == 408) || (i == 411) || (i == 413) || (i == 414) || (i == 503) || (i == 501))
      paramHttpResponse.setHeader("Connection", "Close");
    HttpRequest localHttpRequest;
    do
    {
      do
      {
        Header localHeader1;
        do
        {
          return;
          localHeader1 = paramHttpResponse.getFirstHeader("Connection");
        }
        while ((localHeader1 != null) && ("Close".equalsIgnoreCase(localHeader1.getValue())));
        HttpEntity localHttpEntity = paramHttpResponse.getEntity();
        if (localHttpEntity != null)
        {
          ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
          if ((localHttpEntity.getContentLength() < 0L) && ((!localHttpEntity.isChunked()) || (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))))
          {
            paramHttpResponse.setHeader("Connection", "Close");
            return;
          }
        }
        localHttpRequest = (HttpRequest)paramHttpContext.getAttribute("http.request");
      }
      while (localHttpRequest == null);
      Header localHeader2 = localHttpRequest.getFirstHeader("Connection");
      if (localHeader2 != null)
      {
        paramHttpResponse.setHeader("Connection", localHeader2.getValue());
        return;
      }
    }
    while (!localHttpRequest.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0));
    paramHttpResponse.setHeader("Connection", "Close");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.ResponseConnControl
 * JD-Core Version:    0.6.2
 */