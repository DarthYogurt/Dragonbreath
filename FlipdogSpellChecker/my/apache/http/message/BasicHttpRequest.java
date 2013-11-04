package my.apache.http.message;

import my.apache.http.HttpRequest;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public class BasicHttpRequest extends AbstractHttpMessage
  implements HttpRequest
{
  private final String method;
  private RequestLine requestline;
  private final String uri;

  public BasicHttpRequest(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Method name may not be null");
    if (paramString2 == null)
      throw new IllegalArgumentException("Request URI may not be null");
    this.method = paramString1;
    this.uri = paramString2;
    this.requestline = null;
  }

  public BasicHttpRequest(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    this(new BasicRequestLine(paramString1, paramString2, paramProtocolVersion));
  }

  public BasicHttpRequest(RequestLine paramRequestLine)
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    this.requestline = paramRequestLine;
    this.method = paramRequestLine.getMethod();
    this.uri = paramRequestLine.getUri();
  }

  public ProtocolVersion getProtocolVersion()
  {
    return getRequestLine().getProtocolVersion();
  }

  public RequestLine getRequestLine()
  {
    if (this.requestline == null)
    {
      ProtocolVersion localProtocolVersion = HttpProtocolParams.getVersion(getParams());
      this.requestline = new BasicRequestLine(this.method, this.uri, localProtocolVersion);
    }
    return this.requestline;
  }

  public String toString()
  {
    return this.method + " " + this.uri + " " + this.headergroup;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHttpRequest
 * JD-Core Version:    0.6.2
 */