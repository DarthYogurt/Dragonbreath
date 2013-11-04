package my.apache.http.impl.client;

import java.net.URI;
import java.net.URISyntaxException;
import my.apache.http.HttpRequest;
import my.apache.http.ProtocolException;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.message.AbstractHttpMessage;
import my.apache.http.message.BasicRequestLine;
import my.apache.http.message.HeaderGroup;
import my.apache.http.params.HttpProtocolParams;

@NotThreadSafe
public class RequestWrapper extends AbstractHttpMessage
  implements HttpUriRequest
{
  private int execCount;
  private String method;
  private final HttpRequest original;
  private URI uri;
  private ProtocolVersion version;

  public RequestWrapper(HttpRequest paramHttpRequest)
    throws ProtocolException
  {
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    this.original = paramHttpRequest;
    setParams(paramHttpRequest.getParams());
    setHeaders(paramHttpRequest.getAllHeaders());
    if ((paramHttpRequest instanceof HttpUriRequest))
    {
      this.uri = ((HttpUriRequest)paramHttpRequest).getURI();
      this.method = ((HttpUriRequest)paramHttpRequest).getMethod();
      this.version = null;
    }
    while (true)
    {
      this.execCount = 0;
      return;
      RequestLine localRequestLine = paramHttpRequest.getRequestLine();
      try
      {
        this.uri = new URI(localRequestLine.getUri());
        this.method = localRequestLine.getMethod();
        this.version = paramHttpRequest.getProtocolVersion();
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new ProtocolException("Invalid request URI: " + localRequestLine.getUri(), localURISyntaxException);
      }
    }
  }

  public void abort()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException();
  }

  public int getExecCount()
  {
    return this.execCount;
  }

  public String getMethod()
  {
    return this.method;
  }

  public HttpRequest getOriginal()
  {
    return this.original;
  }

  public ProtocolVersion getProtocolVersion()
  {
    if (this.version == null)
      this.version = HttpProtocolParams.getVersion(getParams());
    return this.version;
  }

  public RequestLine getRequestLine()
  {
    String str1 = getMethod();
    ProtocolVersion localProtocolVersion = getProtocolVersion();
    URI localURI = this.uri;
    String str2 = null;
    if (localURI != null)
      str2 = this.uri.toASCIIString();
    if ((str2 == null) || (str2.length() == 0))
      str2 = "/";
    return new BasicRequestLine(str1, str2, localProtocolVersion);
  }

  public URI getURI()
  {
    return this.uri;
  }

  public void incrementExecCount()
  {
    this.execCount = (1 + this.execCount);
  }

  public boolean isAborted()
  {
    return false;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public void resetHeaders()
  {
    this.headergroup.clear();
    setHeaders(this.original.getAllHeaders());
  }

  public void setMethod(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Method name may not be null");
    this.method = paramString;
  }

  public void setProtocolVersion(ProtocolVersion paramProtocolVersion)
  {
    this.version = paramProtocolVersion;
  }

  public void setURI(URI paramURI)
  {
    this.uri = paramURI;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.RequestWrapper
 * JD-Core Version:    0.6.2
 */