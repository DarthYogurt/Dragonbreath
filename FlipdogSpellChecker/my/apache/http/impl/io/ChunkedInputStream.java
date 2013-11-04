package my.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.MalformedChunkCodingException;
import my.apache.http.TruncatedChunkException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.BufferInfo;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class ChunkedInputStream extends InputStream
{
  private static final int BUFFER_SIZE = 2048;
  private static final int CHUNK_CRLF = 3;
  private static final int CHUNK_DATA = 2;
  private static final int CHUNK_LEN = 1;
  private final CharArrayBuffer buffer;
  private int chunkSize;
  private boolean closed = false;
  private boolean eof = false;
  private Header[] footers = new Header[0];
  private final SessionInputBuffer in;
  private int pos;
  private int state;

  public ChunkedInputStream(SessionInputBuffer paramSessionInputBuffer)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.in = paramSessionInputBuffer;
    this.pos = 0;
    this.buffer = new CharArrayBuffer(16);
    this.state = 1;
  }

  private int getChunkSize()
    throws IOException
  {
    switch (this.state)
    {
    case 2:
    default:
      throw new IllegalStateException("Inconsistent codec state");
    case 3:
      this.buffer.clear();
      if (this.in.readLine(this.buffer) != -1)
        break;
    case 1:
    }
    do
    {
      return 0;
      if (!this.buffer.isEmpty())
        throw new MalformedChunkCodingException("Unexpected content at the end of chunk");
      this.state = 1;
      this.buffer.clear();
    }
    while (this.in.readLine(this.buffer) == -1);
    int i = this.buffer.indexOf(59);
    if (i < 0)
      i = this.buffer.length();
    try
    {
      int j = Integer.parseInt(this.buffer.substringTrimmed(0, i), 16);
      return j;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new MalformedChunkCodingException("Bad chunk header");
  }

  private void nextChunk()
    throws IOException
  {
    this.chunkSize = getChunkSize();
    if (this.chunkSize < 0)
      throw new MalformedChunkCodingException("Negative chunk size");
    this.state = 2;
    this.pos = 0;
    if (this.chunkSize == 0)
    {
      this.eof = true;
      parseTrailerHeaders();
    }
  }

  private void parseTrailerHeaders()
    throws IOException
  {
    try
    {
      this.footers = AbstractMessageParser.parseHeaders(this.in, -1, -1, null);
      return;
    }
    catch (HttpException localHttpException)
    {
      MalformedChunkCodingException localMalformedChunkCodingException = new MalformedChunkCodingException("Invalid footer: " + localHttpException.getMessage());
      localMalformedChunkCodingException.initCause(localHttpException);
      throw localMalformedChunkCodingException;
    }
  }

  public int available()
    throws IOException
  {
    if ((this.in instanceof BufferInfo))
      return Math.min(((BufferInfo)this.in).length(), this.chunkSize - this.pos);
    return 0;
  }

  public void close()
    throws IOException
  {
    if (!this.closed);
    try
    {
      if (!this.eof)
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
      this.eof = true;
      this.closed = true;
    }
  }

  public Header[] getFooters()
  {
    return (Header[])this.footers.clone();
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    int i;
    if (this.eof)
      i = -1;
    do
    {
      do
      {
        return i;
        if (this.state != 2)
        {
          nextChunk();
          if (this.eof)
            return -1;
        }
        i = this.in.read();
      }
      while (i == -1);
      this.pos = (1 + this.pos);
    }
    while (this.pos < this.chunkSize);
    this.state = 3;
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
    int j;
    if (this.eof)
      j = -1;
    do
    {
      return j;
      if (this.state != 2)
      {
        nextChunk();
        if (this.eof)
          return -1;
      }
      int i = Math.min(paramInt2, this.chunkSize - this.pos);
      j = this.in.read(paramArrayOfByte, paramInt1, i);
      if (j == -1)
        break;
      this.pos = (j + this.pos);
    }
    while (this.pos < this.chunkSize);
    this.state = 3;
    return j;
    this.eof = true;
    throw new TruncatedChunkException("Truncated chunk ( expected size: " + this.chunkSize + "; actual size: " + this.pos + ")");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.ChunkedInputStream
 * JD-Core Version:    0.6.2
 */