package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieAttributeHandler;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie;
import my.apache.http.cookie.SetCookie2;

@Immutable
public class RFC2965CommentUrlAttributeHandler
  implements CookieAttributeHandler
{
  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    return true;
  }

  public void parse(SetCookie paramSetCookie, String paramString)
    throws MalformedCookieException
  {
    if ((paramSetCookie instanceof SetCookie2))
      ((SetCookie2)paramSetCookie).setCommentURL(paramString);
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2965CommentUrlAttributeHandler
 * JD-Core Version:    0.6.2
 */