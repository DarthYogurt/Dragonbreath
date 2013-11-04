package my.apache.http.impl.cookie;

import java.util.Locale;
import java.util.StringTokenizer;
import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieRestrictionViolationException;
import my.apache.http.cookie.MalformedCookieException;

@Immutable
public class NetscapeDomainHandler extends BasicDomainHandler
{
  private static boolean isSpecialDomain(String paramString)
  {
    String str = paramString.toUpperCase(Locale.ENGLISH);
    return (str.endsWith(".COM")) || (str.endsWith(".EDU")) || (str.endsWith(".NET")) || (str.endsWith(".GOV")) || (str.endsWith(".MIL")) || (str.endsWith(".ORG")) || (str.endsWith(".INT"));
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    String str1 = paramCookieOrigin.getHost();
    String str2 = paramCookie.getDomain();
    if (str2 == null)
      return false;
    return str1.endsWith(str2);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    super.validate(paramCookie, paramCookieOrigin);
    String str1 = paramCookieOrigin.getHost();
    String str2 = paramCookie.getDomain();
    if (str1.contains("."))
    {
      int i = new StringTokenizer(str2, ".").countTokens();
      if (isSpecialDomain(str2))
      {
        if (i < 2)
          throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" violates the Netscape cookie specification for " + "special domains");
      }
      else if (i < 3)
        throw new CookieRestrictionViolationException("Domain attribute \"" + str2 + "\" violates the Netscape cookie specification");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.NetscapeDomainHandler
 * JD-Core Version:    0.6.2
 */