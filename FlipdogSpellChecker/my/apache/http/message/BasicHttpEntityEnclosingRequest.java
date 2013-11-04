package my.apache.http.message;

import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicHttpEntityEnclosingRequest extends BasicHttpRequest
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;

  public BasicHttpEntityEnclosingRequest(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public BasicHttpEntityEnclosingRequest(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    super(paramString1, paramString2, paramProtocolVersion);
  }

  public BasicHttpEntityEnclosingRequest(RequestLine paramRequestLine)
  {
    super(paramRequestLine);
  }

  public boolean expectContinue()
  {
    Header localHeader = getFirstHeader("Expect");
    return (localHeader != null) && ("100-continue".equalsIgnoreCase(localHeader.getValue()));
  }

  public HttpEntity getEntity()
  {
    return this.entity;
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    this.entity = paramHttpEntity;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHttpEntityEnclosingRequest
 * JD-Core Version:    0.6.2
 */