package my.apache.http.conn;

import my.apache.http.annotation.Immutable;

@Immutable
public class ConnectionPoolTimeoutException extends ConnectTimeoutException
{
  private static final long serialVersionUID = -7898874842020245128L;

  public ConnectionPoolTimeoutException()
  {
  }

  public ConnectionPoolTimeoutException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ConnectionPoolTimeoutException
 * JD-Core Version:    0.6.2
 */