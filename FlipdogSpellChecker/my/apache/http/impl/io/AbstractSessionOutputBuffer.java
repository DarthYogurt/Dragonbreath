package my.apache.http.impl.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.BufferInfo;
import my.apache.http.io.HttpTransportMetrics;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;
import my.apache.http.util.ByteArrayBuffer;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractSessionOutputBuffer
  implements SessionOutputBuffer, BufferInfo
{
  private static final Charset ASCII = Charset.forName("US-ASCII");
  private static final byte[] CRLF = { 13, 10 };
  private boolean ascii = true;
  private ByteBuffer bbuf;
  private ByteArrayBuffer buffer;
  private Charset charset;
  private CharsetEncoder encoder;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit = 512;
  private CodingErrorAction onMalformedInputAction;
  private CodingErrorAction onUnMappableInputAction;
  private OutputStream outstream;

  private void handleEncodingResult(CoderResult paramCoderResult)
    throws IOException
  {
    if (paramCoderResult.isError())
      paramCoderResult.throwException();
    this.bbuf.flip();
    while (true)
    {
      if (!this.bbuf.hasRemaining())
      {
        this.bbuf.compact();
        return;
      }
      write(this.bbuf.get());
    }
  }

  private void writeEncoded(CharBuffer paramCharBuffer)
    throws IOException
  {
    if (!paramCharBuffer.hasRemaining())
      return;
    if (this.encoder == null)
    {
      this.encoder = this.charset.newEncoder();
      this.encoder.onMalformedInput(this.onMalformedInputAction);
      this.encoder.onUnmappableCharacter(this.onUnMappableInputAction);
    }
    if (this.bbuf == null)
      this.bbuf = ByteBuffer.allocate(1024);
    this.encoder.reset();
    while (true)
    {
      if (!paramCharBuffer.hasRemaining())
      {
        handleEncodingResult(this.encoder.flush(this.bbuf));
        this.bbuf.clear();
        return;
      }
      handleEncodingResult(this.encoder.encode(paramCharBuffer, this.bbuf, true));
    }
  }

  public int available()
  {
    return capacity() - length();
  }

  public int capacity()
  {
    return this.buffer.capacity();
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  public void flush()
    throws IOException
  {
    flushBuffer();
    this.outstream.flush();
  }

  protected void flushBuffer()
    throws IOException
  {
    int i = this.buffer.length();
    if (i > 0)
    {
      this.outstream.write(this.buffer.buffer(), 0, i);
      this.buffer.clear();
      this.metrics.incrementBytesTransferred(i);
    }
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }

  protected void init(OutputStream paramOutputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.outstream = paramOutputStream;
    this.buffer = new ByteArrayBuffer(paramInt);
    this.charset = Charset.forName(HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    this.ascii = this.charset.equals(ASCII);
    this.encoder = null;
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
    this.onMalformedInputAction = HttpProtocolParams.getMalformedInputAction(paramHttpParams);
    this.onUnMappableInputAction = HttpProtocolParams.getUnmappableInputAction(paramHttpParams);
  }

  public int length()
  {
    return this.buffer.length();
  }

  public void write(int paramInt)
    throws IOException
  {
    if (this.buffer.isFull())
      flushBuffer();
    this.buffer.append(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt2 > this.minChunkLimit) || (paramInt2 > this.buffer.capacity()))
    {
      flushBuffer();
      this.outstream.write(paramArrayOfByte, paramInt1, paramInt2);
      this.metrics.incrementBytesTransferred(paramInt2);
      return;
    }
    if (paramInt2 > this.buffer.capacity() - this.buffer.length())
      flushBuffer();
    this.buffer.append(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeLine(String paramString)
    throws IOException
  {
    if (paramString == null)
      return;
    int i;
    if (paramString.length() > 0)
    {
      if (!this.ascii)
        break label52;
      i = 0;
      if (i < paramString.length())
        break label37;
    }
    while (true)
    {
      write(CRLF);
      return;
      label37: write(paramString.charAt(i));
      i++;
      break;
      label52: writeEncoded(CharBuffer.wrap(paramString));
    }
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    if (paramCharArrayBuffer == null)
      return;
    int i;
    int j;
    if (this.ascii)
    {
      i = 0;
      j = paramCharArrayBuffer.length();
      if (j > 0);
    }
    while (true)
    {
      write(CRLF);
      return;
      int k = Math.min(this.buffer.capacity() - this.buffer.length(), j);
      if (k > 0)
        this.buffer.append(paramCharArrayBuffer, i, k);
      if (this.buffer.isFull())
        flushBuffer();
      i += k;
      j -= k;
      break;
      writeEncoded(CharBuffer.wrap(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length()));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.AbstractSessionOutputBuffer
 * JD-Core Version:    0.6.2
 */