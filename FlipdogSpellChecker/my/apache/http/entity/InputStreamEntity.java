package my.apache.http.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class InputStreamEntity extends AbstractHttpEntity
{
  private static final int BUFFER_SIZE = 2048;
  private final InputStream content;
  private final long length;

  public InputStreamEntity(InputStream paramInputStream, long paramLong)
  {
    this(paramInputStream, paramLong, null);
  }

  public InputStreamEntity(InputStream paramInputStream, long paramLong, ContentType paramContentType)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Source input stream may not be null");
    this.content = paramInputStream;
    this.length = paramLong;
    if (paramContentType != null)
      setContentType(paramContentType.toString());
  }

  @Deprecated
  public void consumeContent()
    throws IOException
  {
    this.content.close();
  }

  public InputStream getContent()
    throws IOException
  {
    return this.content;
  }

  public long getContentLength()
  {
    return this.length;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public boolean isStreaming()
  {
    return true;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = this.content;
    label128: 
    while (true)
    {
      byte[] arrayOfByte;
      try
      {
        arrayOfByte = new byte[2048];
        if (this.length < 0L)
        {
          int i = localInputStream.read(arrayOfByte);
          if (i == -1)
            return;
          paramOutputStream.write(arrayOfByte, 0, i);
          continue;
        }
      }
      finally
      {
        localInputStream.close();
      }
      int j;
      for (long l = this.length; ; l -= j)
      {
        if (l <= 0L)
          break label128;
        j = localInputStream.read(arrayOfByte, 0, (int)Math.min(2048L, l));
        if (j == -1)
          break;
        paramOutputStream.write(arrayOfByte, 0, j);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.InputStreamEntity
 * JD-Core Version:    0.6.2
 */