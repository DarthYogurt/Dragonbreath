package my.apache.http.impl.cookie;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import my.apache.http.client.utils.Punycode;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieAttributeHandler;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;

public class PublicSuffixFilter
  implements CookieAttributeHandler
{
  private Set<String> exceptions;
  private Set<String> suffixes;
  private final CookieAttributeHandler wrapped;

  public PublicSuffixFilter(CookieAttributeHandler paramCookieAttributeHandler)
  {
    this.wrapped = paramCookieAttributeHandler;
  }

  private boolean isForPublicSuffix(Cookie paramCookie)
  {
    String str1 = paramCookie.getDomain();
    if (str1.startsWith("."))
      str1 = str1.substring(1);
    String str2 = Punycode.toUnicode(str1);
    if ((this.exceptions != null) && (this.exceptions.contains(str2)));
    while (this.suffixes == null)
      return false;
    do
    {
      if (this.suffixes.contains(str2))
        return true;
      if (str2.startsWith("*."))
        str2 = str2.substring(2);
      int i = str2.indexOf('.');
      if (i == -1)
        break;
      str2 = "*" + str2.substring(i);
    }
    while (str2.length() > 0);
    return false;
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (isForPublicSuffix(paramCookie))
      return false;
    return this.wrapped.match(paramCookie, paramCookieOrigin);
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    this.wrapped.parse(paramSetCookie, paramString);
  }

  public void setExceptions(Collection<String> paramCollection)
  {
    this.exceptions = new HashSet(paramCollection);
  }

  public void setPublicSuffixes(Collection<String> paramCollection)
  {
    this.suffixes = new HashSet(paramCollection);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    this.wrapped.validate(paramCookie, paramCookieOrigin);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.PublicSuffixFilter
 * JD-Core Version:    0.6.2
 */