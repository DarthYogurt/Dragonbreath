package my.apache.http.auth;

import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;

@Immutable
public class AuthenticationException extends ProtocolException
{
  private static final long serialVersionUID = -6794031905674764776L;

  public AuthenticationException()
  {
  }

  public AuthenticationException(String paramString)
  {
    super(paramString);
  }

  public AuthenticationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.AuthenticationException
 * JD-Core Version:    0.6.2
 */