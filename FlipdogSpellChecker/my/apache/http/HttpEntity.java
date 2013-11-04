package my.apache.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface HttpEntity
{
  @Deprecated
  public abstract void consumeContent()
    throws IOException;

  public abstract InputStream getContent()
    throws IOException, IllegalStateException;

  public abstract Header getContentEncoding();

  public abstract long getContentLength();

  public abstract Header getContentType();

  public abstract boolean isChunked();

  public abstract boolean isRepeatable();

  public abstract boolean isStreaming();

  public abstract void writeTo(OutputStream paramOutputStream)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpEntity
 * JD-Core Version:    0.6.2
 */