package my.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieAttributeHandler;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;

@NotThreadSafe
public abstract class CookieSpecBase extends AbstractCookieSpec
{
  protected static String getDefaultDomain(CookieOrigin paramCookieOrigin)
  {
    return paramCookieOrigin.getHost();
  }

  protected static String getDefaultPath(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getPath();
    int i = str.lastIndexOf('/');
    if (i >= 0)
    {
      if (i == 0)
        i = 1;
      str = str.substring(0, i);
    }
    return str;
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    Iterator localIterator = getAttribHandlers().iterator();
    do
      if (!localIterator.hasNext())
        return true;
    while (((CookieAttributeHandler)localIterator.next()).match(paramCookie, paramCookieOrigin));
    return false;
  }

  protected List<Cookie> parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfHeaderElement.length);
    int i = paramArrayOfHeaderElement.length;
    int j = 0;
    if (j >= i)
      return localArrayList;
    HeaderElement localHeaderElement = paramArrayOfHeaderElement[j];
    String str1 = localHeaderElement.getName();
    String str2 = localHeaderElement.getValue();
    if ((str1 == null) || (str1.length() == 0))
      throw new MalformedCookieException("Cookie name may not be empty");
    BasicClientCookie localBasicClientCookie = new BasicClientCookie(str1, str2);
    localBasicClientCookie.setPath(getDefaultPath(paramCookieOrigin));
    localBasicClientCookie.setDomain(getDefaultDomain(paramCookieOrigin));
    NameValuePair[] arrayOfNameValuePair = localHeaderElement.getParameters();
    for (int k = -1 + arrayOfNameValuePair.length; ; k--)
    {
      if (k < 0)
      {
        localArrayList.add(localBasicClientCookie);
        j++;
        break;
      }
      NameValuePair localNameValuePair = arrayOfNameValuePair[k];
      String str3 = localNameValuePair.getName().toLowerCase(Locale.ENGLISH);
      localBasicClientCookie.setAttribute(str3, localNameValuePair.getValue());
      CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(str3);
      if (localCookieAttributeHandler != null)
        localCookieAttributeHandler.parse(localBasicClientCookie, localNameValuePair.getValue());
    }
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    Iterator localIterator = getAttribHandlers().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((CookieAttributeHandler)localIterator.next()).validate(paramCookie, paramCookieOrigin);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.CookieSpecBase
 * JD-Core Version:    0.6.2
 */