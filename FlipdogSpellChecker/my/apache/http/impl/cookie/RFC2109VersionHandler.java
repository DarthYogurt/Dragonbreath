package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieRestrictionViolationException;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;

@Immutable
public class RFC2109VersionHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    if (paramString.trim().length() == 0)
      throw new MalformedCookieException("Blank value for version attribute");
    try
    {
      paramSetCookie.setVersion(Integer.parseInt(paramString));
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new MalformedCookieException("Invalid version: " + localNumberFormatException.getMessage());
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookie.getVersion() < 0)
      throw new CookieRestrictionViolationException("Cookie version may not be negative");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2109VersionHandler
 * JD-Core Version:    0.6.2
 */