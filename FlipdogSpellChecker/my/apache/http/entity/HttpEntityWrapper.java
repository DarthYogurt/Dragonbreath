package my.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpEntityWrapper
  implements HttpEntity
{
  protected HttpEntity wrappedEntity;

  public HttpEntityWrapper(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("wrapped entity must not be null");
    this.wrappedEntity = paramHttpEntity;
  }

  @Deprecated
  public void consumeContent()
    throws IOException
  {
    this.wrappedEntity.consumeContent();
  }

  public InputStream getContent()
    throws IOException
  {
    return this.wrappedEntity.getContent();
  }

  public Header getContentEncoding()
  {
    return this.wrappedEntity.getContentEncoding();
  }

  public long getContentLength()
  {
    return this.wrappedEntity.getContentLength();
  }

  public Header getContentType()
  {
    return this.wrappedEntity.getContentType();
  }

  public boolean isChunked()
  {
    return this.wrappedEntity.isChunked();
  }

  public boolean isRepeatable()
  {
    return this.wrappedEntity.isRepeatable();
  }

  public boolean isStreaming()
  {
    return this.wrappedEntity.isStreaming();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.wrappedEntity.writeTo(paramOutputStream);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.HttpEntityWrapper
 * JD-Core Version:    0.6.2
 */