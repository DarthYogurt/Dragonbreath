package my.apache.http.auth;

import my.apache.http.annotation.Immutable;

@Immutable
public class InvalidCredentialsException extends AuthenticationException
{
  private static final long serialVersionUID = -4834003835215460648L;

  public InvalidCredentialsException()
  {
  }

  public InvalidCredentialsException(String paramString)
  {
    super(paramString);
  }

  public InvalidCredentialsException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.InvalidCredentialsException
 * JD-Core Version:    0.6.2
 */