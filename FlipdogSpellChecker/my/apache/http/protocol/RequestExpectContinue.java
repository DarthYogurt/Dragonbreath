package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpProtocolParams;

@Immutable
public class RequestExpectContinue
  implements HttpRequestInterceptor
{
  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if ((localHttpEntity != null) && (localHttpEntity.getContentLength() != 0L))
      {
        ProtocolVersion localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
        if ((HttpProtocolParams.useExpectContinue(paramHttpRequest.getParams())) && (!localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
          paramHttpRequest.addHeader("Expect", "100-continue");
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.RequestExpectContinue
 * JD-Core Version:    0.6.2
 */