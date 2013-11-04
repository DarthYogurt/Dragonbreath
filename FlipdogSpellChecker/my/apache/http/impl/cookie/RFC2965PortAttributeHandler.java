package my.apache.http.impl.cookie;

import java.util.StringTokenizer;
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
public class RFC2965PortAttributeHandler
  implements CookieAttributeHandler
{
  private static int[] parsePortAttribute(String paramString)
    throws MalformedCookieException
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ",");
    int[] arrayOfInt = new int[localStringTokenizer.countTokens()];
    for (int i = 0; ; i++)
      try
      {
        if (!localStringTokenizer.hasMoreTokens())
          return arrayOfInt;
        arrayOfInt[i] = Integer.parseInt(localStringTokenizer.nextToken().trim());
        if (arrayOfInt[i] < 0)
          throw new MalformedCookieException("Invalid Port attribute.");
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new MalformedCookieException("Invalid Port attribute: " + localNumberFormatException.getMessage());
      }
  }

  private static boolean portMatch(int paramInt, int[] paramArrayOfInt)
  {
    int i = 0;
    int j = paramArrayOfInt.length;
    while (true)
    {
      if (i >= j)
        return false;
      if (paramInt == paramArrayOfInt[i])
        return true;
      i++;
    }
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")))
    {
      if (paramCookie.getPorts() == null)
        return false;
      if (!portMatch(i, paramCookie.getPorts()))
        return false;
    }
    return true;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if ((paramSetCookie instanceof SetCookie2))
    {
      SetCookie2 localSetCookie2 = (SetCookie2)paramSetCookie;
      if ((paramString != null) && (paramString.trim().length() > 0))
        localSetCookie2.setPorts(parsePortAttribute(paramString));
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    int i = paramCookieOrigin.getPort();
    if (((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("port")) && (!portMatch(i, paramCookie.getPorts())))
      throw new CookieRestrictionViolationException("Port attribute violates RFC 2965: Request port not found in cookie's port list.");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2965PortAttributeHandler
 * JD-Core Version:    0.6.2
 */