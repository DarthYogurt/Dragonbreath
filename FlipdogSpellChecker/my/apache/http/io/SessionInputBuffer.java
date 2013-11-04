package my.apache.http.io;

import java.io.IOException;
import my.apache.http.util.CharArrayBuffer;

public abstract interface SessionInputBuffer
{
  public abstract HttpTransportMetrics getMetrics();

  public abstract boolean isDataAvailable(int paramInt)
    throws IOException;

  public abstract int read()
    throws IOException;

  public abstract int read(byte[] paramArrayOfByte)
    throws IOException;

  public abstract int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException;

  public abstract String readLine()
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.io.SessionInputBuffer
 * JD-Core Version:    0.6.2
 */