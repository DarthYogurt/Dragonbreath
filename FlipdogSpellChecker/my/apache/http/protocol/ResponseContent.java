package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolException;
import my.apache.http.ProtocolVersion;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class ResponseContent
  implements HttpResponseInterceptor
{
  private final boolean overwrite;

  public ResponseContent()
  {
    this(false);
  }

  public ResponseContent(boolean paramBoolean)
  {
    this.overwrite = paramBoolean;
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    HttpEntity localHttpEntity;
    long l;
    if (this.overwrite)
    {
      paramHttpResponse.removeHeaders("Transfer-Encoding");
      paramHttpResponse.removeHeaders("Content-Length");
      ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
      localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity == null)
        break label240;
      l = localHttpEntity.getContentLength();
      if ((!localHttpEntity.isChunked()) || (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0)))
        break label212;
      paramHttpResponse.addHeader("Transfer-Encoding", "chunked");
      label101: if ((localHttpEntity.getContentType() != null) && (!paramHttpResponse.containsHeader("Content-Type")))
        paramHttpResponse.addHeader(localHttpEntity.getContentType());
      if ((localHttpEntity.getContentEncoding() != null) && (!paramHttpResponse.containsHeader("Content-Encoding")))
        paramHttpResponse.addHeader(localHttpEntity.getContentEncoding());
    }
    label212: label240: int i;
    do
    {
      return;
      if (paramHttpResponse.containsHeader("Transfer-Encoding"))
        throw new ProtocolException("Transfer-encoding header already present");
      if (!paramHttpResponse.containsHeader("Content-Length"))
        break;
      throw new ProtocolException("Content-Length header already present");
      if (l < 0L)
        break label101;
      paramHttpResponse.addHeader("Content-Length", Long.toString(localHttpEntity.getContentLength()));
      break label101;
      i = paramHttpResponse.getStatusLine().getStatusCode();
    }
    while ((i == 204) || (i == 304) || (i == 205));
    paramHttpResponse.addHeader("Content-Length", "0");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.ResponseContent
 * JD-Core Version:    0.6.2
 */