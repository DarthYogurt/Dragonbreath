package my.apache.http;

import java.io.IOException;

public class ConnectionClosedException extends IOException
{
  private static final long serialVersionUID = 617550366255636674L;

  public ConnectionClosedException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.ConnectionClosedException
 * JD-Core Version:    0.6.2
 */