package my.apache.http.cookie;

import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;

@Immutable
public class MalformedCookieException extends ProtocolException
{
  private static final long serialVersionUID = -6695462944287282185L;

  public MalformedCookieException()
  {
  }

  public MalformedCookieException(String paramString)
  {
    super(paramString);
  }

  public MalformedCookieException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.cookie.MalformedCookieException
 * JD-Core Version:    0.6.2
 */