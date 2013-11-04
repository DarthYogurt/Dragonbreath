package my.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class EofSensorInputStream extends InputStream
  implements ConnectionReleaseTrigger
{
  private final EofSensorWatcher eofWatcher;
  private boolean selfClosed;
  protected InputStream wrappedStream;

  public EofSensorInputStream(InputStream paramInputStream, EofSensorWatcher paramEofSensorWatcher)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Wrapped stream may not be null.");
    this.wrappedStream = paramInputStream;
    this.selfClosed = false;
    this.eofWatcher = paramEofSensorWatcher;
  }

  public void abortConnection()
    throws IOException
  {
    this.selfClosed = true;
    checkAbort();
  }

  public int available()
    throws IOException
  {
    boolean bool = isReadAllowed();
    int i = 0;
    if (bool);
    try
    {
      int j = this.wrappedStream.available();
      i = j;
      return i;
    }
    catch (IOException localIOException)
    {
      checkAbort();
      throw localIOException;
    }
  }

  protected void checkAbort()
    throws IOException
  {
    boolean bool;
    if (this.wrappedStream != null)
      bool = true;
    try
    {
      if (this.eofWatcher != null)
        bool = this.eofWatcher.streamAbort(this.wrappedStream);
      if (bool)
        this.wrappedStream.close();
      return;
    }
    finally
    {
      this.wrappedStream = null;
    }
  }

  protected void checkClose()
    throws IOException
  {
    boolean bool;
    if (this.wrappedStream != null)
      bool = true;
    try
    {
      if (this.eofWatcher != null)
        bool = this.eofWatcher.streamClosed(this.wrappedStream);
      if (bool)
        this.wrappedStream.close();
      return;
    }
    finally
    {
      this.wrappedStream = null;
    }
  }

  protected void checkEOF(int paramInt)
    throws IOException
  {
    boolean bool;
    if ((this.wrappedStream != null) && (paramInt < 0))
      bool = true;
    try
    {
      if (this.eofWatcher != null)
        bool = this.eofWatcher.eofDetected(this.wrappedStream);
      if (bool)
        this.wrappedStream.close();
      return;
    }
    finally
    {
      this.wrappedStream = null;
    }
  }

  public void close()
    throws IOException
  {
    this.selfClosed = true;
    checkClose();
  }

  protected boolean isReadAllowed()
    throws IOException
  {
    if (this.selfClosed)
      throw new IOException("Attempted read on closed stream.");
    return this.wrappedStream != null;
  }

  public int read()
    throws IOException
  {
    int i = -1;
    if (isReadAllowed());
    try
    {
      i = this.wrappedStream.read();
      checkEOF(i);
      return i;
    }
    catch (IOException localIOException)
    {
      checkAbort();
      throw localIOException;
    }
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed());
    try
    {
      i = this.wrappedStream.read(paramArrayOfByte);
      checkEOF(i);
      return i;
    }
    catch (IOException localIOException)
    {
      checkAbort();
      throw localIOException;
    }
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed());
    try
    {
      i = this.wrappedStream.read(paramArrayOfByte, paramInt1, paramInt2);
      checkEOF(i);
      return i;
    }
    catch (IOException localIOException)
    {
      checkAbort();
      throw localIOException;
    }
  }

  public void releaseConnection()
    throws IOException
  {
    close();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.EofSensorInputStream
 * JD-Core Version:    0.6.2
 */