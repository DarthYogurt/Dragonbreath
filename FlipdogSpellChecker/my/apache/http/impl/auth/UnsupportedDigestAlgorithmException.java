package my.apache.http.impl.auth;

import my.apache.http.annotation.Immutable;

@Immutable
public class UnsupportedDigestAlgorithmException extends RuntimeException
{
  private static final long serialVersionUID = 319558534317118022L;

  public UnsupportedDigestAlgorithmException()
  {
  }

  public UnsupportedDigestAlgorithmException(String paramString)
  {
    super(paramString);
  }

  public UnsupportedDigestAlgorithmException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.UnsupportedDigestAlgorithmException
 * JD-Core Version:    0.6.2
 */