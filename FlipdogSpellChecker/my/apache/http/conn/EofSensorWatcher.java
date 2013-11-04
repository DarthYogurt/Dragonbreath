package my.apache.http.conn;

import java.io.IOException;
import java.io.InputStream;

public abstract interface EofSensorWatcher
{
  public abstract boolean eofDetected(InputStream paramInputStream)
    throws IOException;

  public abstract boolean streamAbort(InputStream paramInputStream)
    throws IOException;

  public abstract boolean streamClosed(InputStream paramInputStream)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.EofSensorWatcher
 * JD-Core Version:    0.6.2
 */