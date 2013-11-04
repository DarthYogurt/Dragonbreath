package my.apache.http.client;

import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;

@Immutable
public class RedirectException extends ProtocolException
{
  private static final long serialVersionUID = 4418824536372559326L;

  public RedirectException()
  {
  }

  public RedirectException(String paramString)
  {
    super(paramString);
  }

  public RedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.RedirectException
 * JD-Core Version:    0.6.2
 */