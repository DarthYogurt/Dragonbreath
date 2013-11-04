package my.apache.http.impl.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.ProtocolException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.entity.HttpEntityWrapper;

@NotThreadSafe
public class EntityEnclosingRequestWrapper extends RequestWrapper
  implements HttpEntityEnclosingRequest
{
  private boolean consumed;
  private HttpEntity entity;

  public EntityEnclosingRequestWrapper(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws ProtocolException
  {
    super(paramHttpEntityEnclosingRequest);
    setEntity(paramHttpEntityEnclosingRequest.getEntity());
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

  public boolean isRepeatable()
  {
    return (this.entity == null) || (this.entity.isRepeatable()) || (!this.consumed);
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity != null);
    for (EntityWrapper localEntityWrapper = new EntityWrapper(paramHttpEntity); ; localEntityWrapper = null)
    {
      this.entity = localEntityWrapper;
      this.consumed = false;
      return;
    }
  }

  class EntityWrapper extends HttpEntityWrapper
  {
    EntityWrapper(HttpEntity arg2)
    {
      super();
    }

    public void consumeContent()
      throws IOException
    {
      EntityEnclosingRequestWrapper.this.consumed = true;
      super.consumeContent();
    }

    public InputStream getContent()
      throws IOException
    {
      EntityEnclosingRequestWrapper.this.consumed = true;
      return super.getContent();
    }

    public void writeTo(OutputStream paramOutputStream)
      throws IOException
    {
      EntityEnclosingRequestWrapper.this.consumed = true;
      super.writeTo(paramOutputStream);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.EntityEnclosingRequestWrapper
 * JD-Core Version:    0.6.2
 */