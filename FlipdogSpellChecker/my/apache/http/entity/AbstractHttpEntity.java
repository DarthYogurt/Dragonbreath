package my.apache.http.entity;

import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HttpEntity;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.message.BasicHeader;

@NotThreadSafe
public abstract class AbstractHttpEntity
  implements HttpEntity
{
  protected boolean chunked;
  protected Header contentEncoding;
  protected Header contentType;

  @Deprecated
  public void consumeContent()
    throws IOException
  {
  }

  public Header getContentEncoding()
  {
    return this.contentEncoding;
  }

  public Header getContentType()
  {
    return this.contentType;
  }

  public boolean isChunked()
  {
    return this.chunked;
  }

  public void setChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }

  public void setContentEncoding(String paramString)
  {
    BasicHeader localBasicHeader = null;
    if (paramString != null)
      localBasicHeader = new BasicHeader("Content-Encoding", paramString);
    setContentEncoding(localBasicHeader);
  }

  public void setContentEncoding(Header paramHeader)
  {
    this.contentEncoding = paramHeader;
  }

  public void setContentType(String paramString)
  {
    BasicHeader localBasicHeader = null;
    if (paramString != null)
      localBasicHeader = new BasicHeader("Content-Type", paramString);
    setContentType(localBasicHeader);
  }

  public void setContentType(Header paramHeader)
  {
    this.contentType = paramHeader;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.AbstractHttpEntity
 * JD-Core Version:    0.6.2
 */