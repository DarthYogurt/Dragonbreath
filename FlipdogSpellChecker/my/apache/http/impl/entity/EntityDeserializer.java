package my.apache.http.impl.entity;

import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.BasicHttpEntity;
import my.apache.http.entity.ContentLengthStrategy;
import my.apache.http.impl.io.ChunkedInputStream;
import my.apache.http.impl.io.ContentLengthInputStream;
import my.apache.http.impl.io.IdentityInputStream;
import my.apache.http.io.SessionInputBuffer;

@Immutable
public class EntityDeserializer
{
  private final ContentLengthStrategy lenStrategy;

  public EntityDeserializer(ContentLengthStrategy paramContentLengthStrategy)
  {
    if (paramContentLengthStrategy == null)
      throw new IllegalArgumentException("Content length strategy may not be null");
    this.lenStrategy = paramContentLengthStrategy;
  }

  public HttpEntity deserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    return doDeserialize(paramSessionInputBuffer, paramHttpMessage);
  }

  protected BasicHttpEntity doDeserialize(SessionInputBuffer paramSessionInputBuffer, HttpMessage paramHttpMessage)
    throws HttpException, IOException
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    long l = this.lenStrategy.determineLength(paramHttpMessage);
    if (l == -2L)
    {
      localBasicHttpEntity.setChunked(true);
      localBasicHttpEntity.setContentLength(-1L);
      localBasicHttpEntity.setContent(new ChunkedInputStream(paramSessionInputBuffer));
    }
    while (true)
    {
      Header localHeader1 = paramHttpMessage.getFirstHeader("Content-Type");
      if (localHeader1 != null)
        localBasicHttpEntity.setContentType(localHeader1);
      Header localHeader2 = paramHttpMessage.getFirstHeader("Content-Encoding");
      if (localHeader2 != null)
        localBasicHttpEntity.setContentEncoding(localHeader2);
      return localBasicHttpEntity;
      if (l == -1L)
      {
        localBasicHttpEntity.setChunked(false);
        localBasicHttpEntity.setContentLength(-1L);
        localBasicHttpEntity.setContent(new IdentityInputStream(paramSessionInputBuffer));
      }
      else
      {
        localBasicHttpEntity.setChunked(false);
        localBasicHttpEntity.setContentLength(l);
        localBasicHttpEntity.setContent(new ContentLengthInputStream(paramSessionInputBuffer, l));
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.entity.EntityDeserializer
 * JD-Core Version:    0.6.2
 */