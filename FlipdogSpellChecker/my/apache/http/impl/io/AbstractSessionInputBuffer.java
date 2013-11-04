package my.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.BufferInfo;
import my.apache.http.io.HttpTransportMetrics;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;
import my.apache.http.util.ByteArrayBuffer;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractSessionInputBuffer
  implements SessionInputBuffer, BufferInfo
{
  private static final Charset ASCII = Charset.forName("US-ASCII");
  private boolean ascii = true;
  private byte[] buffer;
  private int bufferlen;
  private int bufferpos;
  private CharBuffer cbuf;
  private Charset charset;
  private CharsetDecoder decoder;
  private InputStream instream;
  private ByteArrayBuffer linebuffer = null;
  private int maxLineLen = -1;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit = 512;
  private CodingErrorAction onMalformedInputAction;
  private CodingErrorAction onUnMappableInputAction;

  private int appendDecoded(CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (!paramByteBuffer.hasRemaining())
      return 0;
    if (this.decoder == null)
    {
      this.decoder = this.charset.newDecoder();
      this.decoder.onMalformedInput(this.onMalformedInputAction);
      this.decoder.onUnmappableCharacter(this.onUnMappableInputAction);
    }
    if (this.cbuf == null)
      this.cbuf = CharBuffer.allocate(1024);
    this.decoder.reset();
    int i = 0;
    while (true)
    {
      if (!paramByteBuffer.hasRemaining())
      {
        int j = i + handleDecodingResult(this.decoder.flush(this.cbuf), paramCharArrayBuffer, paramByteBuffer);
        this.cbuf.clear();
        return j;
      }
      i += handleDecodingResult(this.decoder.decode(paramByteBuffer, this.cbuf, true), paramCharArrayBuffer, paramByteBuffer);
    }
  }

  private int handleDecodingResult(CoderResult paramCoderResult, CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (paramCoderResult.isError())
      paramCoderResult.throwException();
    this.cbuf.flip();
    int i = this.cbuf.remaining();
    while (true)
    {
      if (!this.cbuf.hasRemaining())
      {
        this.cbuf.compact();
        return i;
      }
      paramCharArrayBuffer.append(this.cbuf.get());
    }
  }

  private int lineFromLineBuffer(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = this.linebuffer.length();
    if (i > 0)
    {
      if (this.linebuffer.byteAt(i - 1) == 10)
        i--;
      if ((i > 0) && (this.linebuffer.byteAt(i - 1) == 13))
        i--;
    }
    if (this.ascii)
      paramCharArrayBuffer.append(this.linebuffer, 0, i);
    while (true)
    {
      this.linebuffer.clear();
      return i;
      i = appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(this.linebuffer.buffer(), 0, i));
    }
  }

  private int lineFromReadBuffer(CharArrayBuffer paramCharArrayBuffer, int paramInt)
    throws IOException
  {
    int i = this.bufferpos;
    this.bufferpos = (paramInt + 1);
    if ((paramInt > i) && (this.buffer[(paramInt - 1)] == 13))
      paramInt--;
    int j = paramInt - i;
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.buffer, i, j);
      return j;
    }
    return appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(this.buffer, i, j));
  }

  private int locateLF()
  {
    for (int i = this.bufferpos; ; i++)
    {
      if (i >= this.bufferlen)
        i = -1;
      while (this.buffer[i] == 10)
        return i;
    }
  }

  public int available()
  {
    return capacity() - length();
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  protected int fillBuffer()
    throws IOException
  {
    if (this.bufferpos > 0)
    {
      int m = this.bufferlen - this.bufferpos;
      if (m > 0)
        System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, m);
      this.bufferpos = 0;
      this.bufferlen = m;
    }
    int i = this.bufferlen;
    int j = this.buffer.length - i;
    int k = this.instream.read(this.buffer, i, j);
    if (k == -1)
      return -1;
    this.bufferlen = (i + k);
    this.metrics.incrementBytesTransferred(k);
    return k;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }

  protected boolean hasBufferedData()
  {
    return this.bufferpos < this.bufferlen;
  }

  protected void init(InputStream paramInputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.instream = paramInputStream;
    this.buffer = new byte[paramInt];
    this.bufferpos = 0;
    this.bufferlen = 0;
    this.linebuffer = new ByteArrayBuffer(paramInt);
    this.charset = Charset.forName(HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    this.ascii = this.charset.equals(ASCII);
    this.decoder = null;
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
    this.onMalformedInputAction = HttpProtocolParams.getMalformedInputAction(paramHttpParams);
    this.onUnMappableInputAction = HttpProtocolParams.getUnmappableInputAction(paramHttpParams);
  }

  public int length()
  {
    return this.bufferlen - this.bufferpos;
  }

  public int read()
    throws IOException
  {
    do
      if (hasBufferedData())
      {
        byte[] arrayOfByte = this.buffer;
        int i = this.bufferpos;
        this.bufferpos = (i + 1);
        return 0xFF & arrayOfByte[i];
      }
    while (fillBuffer() != -1);
    return -1;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = -1;
    if (paramArrayOfByte == null)
      i = 0;
    do
      while (fillBuffer() == i)
      {
        return i;
        if (hasBufferedData())
        {
          int m = Math.min(paramInt2, this.bufferlen - this.bufferpos);
          System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, m);
          this.bufferpos = (m + this.bufferpos);
          return m;
        }
        if (paramInt2 <= this.minChunkLimit)
          break;
        int k = this.instream.read(paramArrayOfByte, paramInt1, paramInt2);
        if (k > 0)
          this.metrics.incrementBytesTransferred(k);
        return k;
      }
    while (!hasBufferedData());
    int j = Math.min(paramInt2, this.bufferlen - this.bufferpos);
    System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, j);
    this.bufferpos = (j + this.bufferpos);
    return j;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int i = 0;
    int j = 1;
    while (true)
      if (j == 0)
      {
        if ((i == -1) && (this.linebuffer.isEmpty()))
          return -1;
      }
      else
      {
        int k = locateLF();
        if (k != -1)
        {
          if (this.linebuffer.isEmpty())
            return lineFromReadBuffer(paramCharArrayBuffer, k);
          j = 0;
          int n = k + 1 - this.bufferpos;
          this.linebuffer.append(this.buffer, this.bufferpos, n);
          this.bufferpos = (k + 1);
        }
        while ((this.maxLineLen > 0) && (this.linebuffer.length() >= this.maxLineLen))
        {
          throw new IOException("Maximum line length limit exceeded");
          if (hasBufferedData())
          {
            int m = this.bufferlen - this.bufferpos;
            this.linebuffer.append(this.buffer, this.bufferpos, m);
            this.bufferpos = this.bufferlen;
          }
          i = fillBuffer();
          if (i == -1)
            j = 0;
        }
      }
    return lineFromLineBuffer(paramCharArrayBuffer);
  }

  public String readLine()
    throws IOException
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(64);
    if (readLine(localCharArrayBuffer) != -1)
      return localCharArrayBuffer.toString();
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.AbstractSessionInputBuffer
 * JD-Core Version:    0.6.2
 */