package my.apache.http.cookie;

import my.apache.http.annotation.Immutable;

@Immutable
public class CookieRestrictionViolationException extends MalformedCookieException
{
  private static final long serialVersionUID = 7371235577078589013L;

  public CookieRestrictionViolationException()
  {
  }

  public CookieRestrictionViolationException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.cookie.CookieRestrictionViolationException
 * JD-Core Version:    0.6.2
 */