package my.apache.http.conn;

import java.io.InterruptedIOException;
import my.apache.http.annotation.Immutable;

@Immutable
public class ConnectTimeoutException extends InterruptedIOException
{
  private static final long serialVersionUID = -4816682903149535989L;

  public ConnectTimeoutException()
  {
  }

  public ConnectTimeoutException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ConnectTimeoutException
 * JD-Core Version:    0.6.2
 */