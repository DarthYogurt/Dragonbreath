package my.apache.http.auth;

import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;

@Immutable
public class MalformedChallengeException extends ProtocolException
{
  private static final long serialVersionUID = 814586927989932284L;

  public MalformedChallengeException()
  {
  }

  public MalformedChallengeException(String paramString)
  {
    super(paramString);
  }

  public MalformedChallengeException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.MalformedChallengeException
 * JD-Core Version:    0.6.2
 */