package my.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.HttpEntity;
import my.apache.http.entity.HttpEntityWrapper;

abstract class DecompressingEntity extends HttpEntityWrapper
{
  private static final int BUFFER_SIZE = 2048;
  private InputStream content;

  public DecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  private InputStream getDecompressingStream()
    throws IOException
  {
    InputStream localInputStream1 = this.wrappedEntity.getContent();
    try
    {
      InputStream localInputStream2 = decorate(localInputStream1);
      return localInputStream2;
    }
    catch (IOException localIOException)
    {
      localInputStream1.close();
      throw localIOException;
    }
  }

  abstract InputStream decorate(InputStream paramInputStream)
    throws IOException;

  public InputStream getContent()
    throws IOException
  {
    if (this.wrappedEntity.isStreaming())
    {
      if (this.content == null)
        this.content = getDecompressingStream();
      return this.content;
    }
    return getDecompressingStream();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = getContent();
    try
    {
      byte[] arrayOfByte = new byte[2048];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1)
          return;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      localInputStream.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.entity.DecompressingEntity
 * JD-Core Version:    0.6.2
 */