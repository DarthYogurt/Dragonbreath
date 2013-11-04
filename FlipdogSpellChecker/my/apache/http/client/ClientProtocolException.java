package my.apache.http.client;

import java.io.IOException;
import my.apache.http.annotation.Immutable;

@Immutable
public class ClientProtocolException extends IOException
{
  private static final long serialVersionUID = -5596590843227115865L;

  public ClientProtocolException()
  {
  }

  public ClientProtocolException(String paramString)
  {
    super(paramString);
  }

  public ClientProtocolException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }

  public ClientProtocolException(Throwable paramThrowable)
  {
    initCause(paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.ClientProtocolException
 * JD-Core Version:    0.6.2
 */