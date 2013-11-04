package my.apache.http.client.methods;

import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.client.utils.CloneUtils;

@NotThreadSafe
public abstract class HttpEntityEnclosingRequestBase extends HttpRequestBase
  implements HttpEntityEnclosingRequest
{
  private HttpEntity entity;

  public Object clone()
    throws CloneNotSupportedException
  {
    HttpEntityEnclosingRequestBase localHttpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase)super.clone();
    if (this.entity != null)
      localHttpEntityEnclosingRequestBase.entity = ((HttpEntity)CloneUtils.clone(this.entity));
    return localHttpEntityEnclosingRequestBase;
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
 * Qualified Name:     my.apache.http.client.methods.HttpEntityEnclosingRequestBase
 * JD-Core Version:    0.6.2
 */