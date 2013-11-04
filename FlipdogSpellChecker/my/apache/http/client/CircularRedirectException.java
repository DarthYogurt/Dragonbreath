package my.apache.http.client;

import my.apache.http.annotation.Immutable;

@Immutable
public class CircularRedirectException extends RedirectException
{
  private static final long serialVersionUID = 6830063487001091803L;

  public CircularRedirectException()
  {
  }

  public CircularRedirectException(String paramString)
  {
    super(paramString);
  }

  public CircularRedirectException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.CircularRedirectException
 * JD-Core Version:    0.6.2
 */