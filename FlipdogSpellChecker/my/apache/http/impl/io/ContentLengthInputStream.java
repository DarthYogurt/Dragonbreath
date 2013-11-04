package my.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import my.apache.http.ConnectionClosedException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.BufferInfo;
import my.apache.http.io.SessionInputBuffer;

@NotThreadSafe
public class ContentLengthInputStream extends InputStream
{
  private static final int BUFFER_SIZE = 2048;
  private boolean closed = false;
  private long contentLength;
  private SessionInputBuffer in = null;
  private long pos = 0L;

  public ContentLengthInputStream(SessionInputBuffer paramSessionInputBuffer, long paramLong)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramLong < 0L)
      throw new IllegalArgumentException("Content length may not be negative");
    this.in = paramSessionInputBuffer;
    this.contentLength = paramLong;
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
      return Math.min(((BufferInfo)this.in).length(), (int)(this.contentLength - this.pos));
    return 0;
  }

  public void close()
    throws IOException
  {
    if (!this.closed);
    try
    {
      if (this.pos < this.contentLength)
      {
        byte[] arrayOfByte = new byte[2048];
        int i;
        do
          i = read(arrayOfByte);
        while (i >= 0);
      }
      return;
    }
    finally
    {
      this.closed = true;
    }
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    int i;
    if (this.pos >= this.contentLength)
      i = -1;
    do
    {
      return i;
      i = this.in.read();
      if (i != -1)
        break;
    }
    while (this.pos >= this.contentLength);
    throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
    this.pos = (1L + this.pos);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    int i;
    if (this.pos >= this.contentLength)
      i = -1;
    do
    {
      return i;
      if (this.pos + paramInt2 > this.contentLength)
        paramInt2 = (int)(this.contentLength - this.pos);
      i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
      if ((i == -1) && (this.pos < this.contentLength))
        throw new ConnectionClosedException("Premature end of Content-Length delimited message body (expected: " + this.contentLength + "; received: " + this.pos);
    }
    while (i <= 0);
    this.pos += i;
    return i;
  }

  public long skip(long paramLong)
    throws IOException
  {
    long l2;
    if (paramLong <= 0L)
      l2 = 0L;
    label83: 
    while (true)
    {
      return l2;
      byte[] arrayOfByte = new byte[2048];
      long l1 = Math.min(paramLong, this.contentLength - this.pos);
      l2 = 0L;
      while (true)
      {
        if (l1 <= 0L)
          break label83;
        int i = read(arrayOfByte, 0, (int)Math.min(2048L, l1));
        if (i == -1)
          break;
        l2 += i;
        l1 -= i;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.ContentLengthInputStream
 * JD-Core Version:    0.6.2
 */