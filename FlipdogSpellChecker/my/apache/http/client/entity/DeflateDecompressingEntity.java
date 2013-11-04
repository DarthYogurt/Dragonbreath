package my.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import my.apache.http.Header;
import my.apache.http.HttpEntity;

public class DeflateDecompressingEntity extends DecompressingEntity
{
  public DeflateDecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  InputStream decorate(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte1 = new byte[6];
    PushbackInputStream localPushbackInputStream = new PushbackInputStream(paramInputStream, arrayOfByte1.length);
    int i = localPushbackInputStream.read(arrayOfByte1);
    if (i == -1)
      throw new IOException("Unable to read the response");
    byte[] arrayOfByte2 = new byte[1];
    Inflater localInflater = new Inflater();
    while (true)
    {
      try
      {
        int j = localInflater.inflate(arrayOfByte2);
        if (j != 0)
        {
          if (j != -1)
            break;
          throw new IOException("Unable to read the response");
        }
      }
      catch (DataFormatException localDataFormatException)
      {
        localPushbackInputStream.unread(arrayOfByte1, 0, i);
        DeflateStream localDeflateStream1 = new DeflateStream(localPushbackInputStream, new Inflater(true));
        return localDeflateStream1;
        if (localInflater.finished())
          throw new IOException("Unable to read the response");
      }
      finally
      {
        localInflater.end();
      }
      if (!localInflater.needsDictionary())
        if (localInflater.needsInput())
          localInflater.setInput(arrayOfByte1);
    }
    localPushbackInputStream.unread(arrayOfByte1, 0, i);
    DeflateStream localDeflateStream2 = new DeflateStream(localPushbackInputStream, new Inflater());
    localInflater.end();
    return localDeflateStream2;
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    return -1L;
  }

  static class DeflateStream extends InflaterInputStream
  {
    private boolean closed = false;

    public DeflateStream(InputStream paramInputStream, Inflater paramInflater)
    {
      super(paramInflater);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      this.closed = true;
      this.inf.end();
      super.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.entity.DeflateDecompressingEntity
 * JD-Core Version:    0.6.2
 */