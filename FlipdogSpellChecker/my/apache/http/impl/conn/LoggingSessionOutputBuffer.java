package my.apache.http.impl.conn;

import java.io.IOException;
import java.nio.charset.Charset;
import my.apache.http.Consts;
import my.apache.http.annotation.Immutable;
import my.apache.http.io.HttpTransportMetrics;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionOutputBuffer
  implements SessionOutputBuffer
{
  private final String charset;
  private final SessionOutputBuffer out;
  private final Wire wire;

  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire)
  {
    this(paramSessionOutputBuffer, paramWire, null);
  }

  public LoggingSessionOutputBuffer(SessionOutputBuffer paramSessionOutputBuffer, Wire paramWire, String paramString)
  {
    this.out = paramSessionOutputBuffer;
    this.wire = paramWire;
    if (paramString != null);
    while (true)
    {
      this.charset = paramString;
      return;
      paramString = Consts.ASCII.name();
    }
  }

  public void flush()
    throws IOException
  {
    this.out.flush();
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.out.getMetrics();
  }

  public void write(int paramInt)
    throws IOException
  {
    this.out.write(paramInt);
    if (this.wire.enabled())
      this.wire.output(paramInt);
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    this.out.write(paramArrayOfByte);
    if (this.wire.enabled())
      this.wire.output(paramArrayOfByte);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    if (this.wire.enabled())
      this.wire.output(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeLine(String paramString)
    throws IOException
  {
    this.out.writeLine(paramString);
    if (this.wire.enabled())
    {
      String str = paramString + "\r\n";
      this.wire.output(str.getBytes(this.charset));
    }
  }

  public void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    this.out.writeLine(paramCharArrayBuffer);
    if (this.wire.enabled())
    {
      String str = new String(paramCharArrayBuffer.buffer(), 0, paramCharArrayBuffer.length()) + "\r\n";
      this.wire.output(str.getBytes(this.charset));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.LoggingSessionOutputBuffer
 * JD-Core Version:    0.6.2
 */