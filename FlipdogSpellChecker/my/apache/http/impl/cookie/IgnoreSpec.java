package my.apache.http.impl.cookie;

import java.util.Collections;
import java.util.List;
import my.apache.http.Header;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;

@NotThreadSafe
public class IgnoreSpec extends CookieSpecBase
{
  public List<Header> formatCookies(List<Cookie> paramList)
  {
    return Collections.emptyList();
  }

  public int getVersion()
  {
    return 0;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    return Collections.emptyList();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.IgnoreSpec
 * JD-Core Version:    0.6.2
 */