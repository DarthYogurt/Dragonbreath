package my.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.protocol.HTTP;

@NotThreadSafe
public class StringEntity extends AbstractHttpEntity
  implements Cloneable
{
  protected final byte[] content;

  public StringEntity(String paramString)
    throws UnsupportedEncodingException
  {
    this(paramString, ContentType.DEFAULT_TEXT);
  }

  public StringEntity(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    this(paramString1, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), paramString2));
  }

  @Deprecated
  public StringEntity(String paramString1, String paramString2, String paramString3)
    throws UnsupportedEncodingException
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Source string may not be null");
    if (paramString2 == null)
      paramString2 = "text/plain";
    if (paramString3 == null)
      paramString3 = "ISO-8859-1";
    this.content = paramString1.getBytes(paramString3);
    setContentType(paramString2 + "; charset=" + paramString3);
  }

  public StringEntity(String paramString, Charset paramCharset)
  {
    this(paramString, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), paramCharset));
  }

  public StringEntity(String paramString, ContentType paramContentType)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Source string may not be null");
    Charset localCharset;
    if (paramContentType != null)
      localCharset = paramContentType.getCharset();
    while (true)
    {
      if (localCharset == null)
        localCharset = HTTP.DEF_CONTENT_CHARSET;
      try
      {
        this.content = paramString.getBytes(localCharset.name());
        if (paramContentType != null)
          setContentType(paramContentType.toString());
        return;
        localCharset = null;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
    }
    throw new UnsupportedCharsetException(localCharset.name());
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public InputStream getContent()
    throws IOException
  {
    return new ByteArrayInputStream(this.content);
  }

  public long getContentLength()
  {
    return this.content.length;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    paramOutputStream.write(this.content);
    paramOutputStream.flush();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.StringEntity
 * JD-Core Version:    0.6.2
 */