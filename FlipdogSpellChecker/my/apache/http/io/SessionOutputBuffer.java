package my.apache.http.io;

import java.io.IOException;
import my.apache.http.util.CharArrayBuffer;

public abstract interface SessionOutputBuffer
{
  public abstract void flush()
    throws IOException;

  public abstract HttpTransportMetrics getMetrics();

  public abstract void write(int paramInt)
    throws IOException;

  public abstract void write(byte[] paramArrayOfByte)
    throws IOException;

  public abstract void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract void writeLine(String paramString)
    throws IOException;

  public abstract void writeLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.io.SessionOutputBuffer
 * JD-Core Version:    0.6.2
 */