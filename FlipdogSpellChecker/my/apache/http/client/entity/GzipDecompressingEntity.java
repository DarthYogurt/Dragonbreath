package my.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import my.apache.http.Header;
import my.apache.http.HttpEntity;

public class GzipDecompressingEntity extends DecompressingEntity
{
  public GzipDecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  InputStream decorate(InputStream paramInputStream)
    throws IOException
  {
    return new GZIPInputStream(paramInputStream);
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    return -1L;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.entity.GzipDecompressingEntity
 * JD-Core Version:    0.6.2
 */