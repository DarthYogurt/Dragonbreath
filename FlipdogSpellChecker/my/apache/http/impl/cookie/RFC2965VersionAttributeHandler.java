package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.ClientCookie;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieAttributeHandler;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieRestrictionViolationException;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;
import my.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965VersionAttributeHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for version attribute");
    try
    {
      int j = Integer.parseInt(paramString);
      i = j;
      if (i < 0)
        throw new MalformedCookieException("Invalid cookie version.");
    }
    catch (NumberFormatException localNumberFormatException)
    {
      int i;
      while (true)
        i = -1;
      paramSetCookie.setVersion(i);
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (((paramCookie instanceof SetCookie2)) && ((paramCookie instanceof ClientCookie)) && (!((ClientCookie)paramCookie).containsAttribute("version")))
      throw new CookieRestrictionViolationException("Violates RFC 2965. Version attribute is required.");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2965VersionAttributeHandler
 * JD-Core Version:    0.6.2
 */