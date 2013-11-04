package my.apache.http.impl.cookie;

import java.util.Date;
import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;

@Immutable
public class BasicMaxAgeHandler extends AbstractCookieAttributeHandler
{
  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for max-age attribute");
    int i;
    try
    {
      i = Integer.parseInt(paramString);
      if (i < 0)
        throw new MalformedCookieException("Negative max-age attribute: " + paramString);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new MalformedCookieException("Invalid max-age attribute: " + paramString);
    }
    paramSetCookie.setExpiryDate(new Date(System.currentTimeMillis() + 1000L * i));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.BasicMaxAgeHandler
 * JD-Core Version:    0.6.2
 */