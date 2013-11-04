package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolException;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class RequestContent
  implements HttpRequestInterceptor
{
  private final boolean overwrite;

  public RequestContent()
  {
    this(false);
  }

  public RequestContent(boolean paramBoolean)
  {
    this.overwrite = paramBoolean;
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      if (!this.overwrite)
        break label83;
      paramHttpRequest.removeHeaders("Transfer-Encoding");
      paramHttpRequest.removeHeaders("Content-Length");
    }
    ProtocolVersion localProtocolVersion;
    HttpEntity localHttpEntity;
    label83: 
    do
    {
      localProtocolVersion = paramHttpRequest.getRequestLine().getProtocolVersion();
      localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (localHttpEntity != null)
        break;
      paramHttpRequest.addHeader("Content-Length", "0");
      return;
      if (paramHttpRequest.containsHeader("Transfer-Encoding"))
        throw new ProtocolException("Transfer-encoding header already present");
    }
    while (!paramHttpRequest.containsHeader("Content-Length"));
    throw new ProtocolException("Content-Length header already present");
    if ((localHttpEntity.isChunked()) || (localHttpEntity.getContentLength() < 0L))
    {
      if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0))
        throw new ProtocolException("Chunked transfer encoding not allowed for " + localProtocolVersion);
      paramHttpRequest.addHeader("Transfer-Encoding", "chunked");
    }
    while (true)
    {
      if ((localHttpEntity.getContentType() != null) && (!paramHttpRequest.containsHeader("Content-Type")))
        paramHttpRequest.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() == null) || (paramHttpRequest.containsHeader("Content-Encoding")))
        break;
      paramHttpRequest.addHeader(localHttpEntity.getContentEncoding());
      return;
      paramHttpRequest.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.RequestContent
 * JD-Core Version:    0.6.2
 */