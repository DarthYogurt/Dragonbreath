package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;

@Immutable
class BrowserCompatVersionAttributeHandler extends AbstractCookieAttributeHandler
{
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
      paramSetCookie.setVersion(i);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        int i = 0;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.BrowserCompatVersionAttributeHandler
 * JD-Core Version:    0.6.2
 */