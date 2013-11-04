package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;

@Immutable
public class BasicExpiresHandler extends AbstractCookieAttributeHandler
{
  private final String[] datepatterns;

  public BasicExpiresHandler(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null)
      throw new IllegalArgumentException("Array of date patterns may not be null");
    this.datepatterns = paramArrayOfString;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if (paramSetCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramString == null)
      throw new MalformedCookieException("Missing value for expires attribute");
    try
    {
      paramSetCookie.setExpiryDate(DateUtils.parseDate(paramString, this.datepatterns));
      return;
    }
    catch (DateParseException localDateParseException)
    {
    }
    throw new MalformedCookieException("Unable to parse expires attribute: " + paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.BasicExpiresHandler
 * JD-Core Version:    0.6.2
 */