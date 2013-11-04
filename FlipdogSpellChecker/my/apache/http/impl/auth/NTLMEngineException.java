package my.apache.http.impl.auth;

import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthenticationException;

@Immutable
public class NTLMEngineException extends AuthenticationException
{
  private static final long serialVersionUID = 6027981323731768824L;

  public NTLMEngineException()
  {
  }

  public NTLMEngineException(String paramString)
  {
    super(paramString);
  }

  public NTLMEngineException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.NTLMEngineException
 * JD-Core Version:    0.6.2
 */