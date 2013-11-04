package my.apache.http.client;

import java.util.Date;
import java.util.List;
import my.apache.http.cookie.Cookie;

public abstract interface CookieStore
{
  public abstract void addCookie(Cookie paramCookie);

  public abstract void clear();

  public abstract boolean clearExpired(Date paramDate);

  public abstract List<Cookie> getCookies();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.CookieStore
 * JD-Core Version:    0.6.2
 */