package my.apache.http.impl.cookie;

import java.util.Iterator;
import java.util.List;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookieSpec;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.cookie.SetCookie2;
import my.apache.http.message.ParserCursor;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BestMatchSpec
  implements CookieSpec
{
  private BrowserCompatSpec compat;
  private final String[] datepatterns;
  private RFC2109Spec obsoleteStrict;
  private final boolean oneHeader;
  private RFC2965Spec strict;

  public BestMatchSpec()
  {
    this(null, false);
  }

  public BestMatchSpec(String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString == null);
    for (String[] arrayOfString = null; ; arrayOfString = (String[])paramArrayOfString.clone())
    {
      this.datepatterns = arrayOfString;
      this.oneHeader = paramBoolean;
      return;
    }
  }

  private BrowserCompatSpec getCompat()
  {
    if (this.compat == null)
      this.compat = new BrowserCompatSpec(this.datepatterns);
    return this.compat;
  }

  private RFC2109Spec getObsoleteStrict()
  {
    if (this.obsoleteStrict == null)
      this.obsoleteStrict = new RFC2109Spec(this.datepatterns, this.oneHeader);
    return this.obsoleteStrict;
  }

  private RFC2965Spec getStrict()
  {
    if (this.strict == null)
      this.strict = new RFC2965Spec(this.datepatterns, this.oneHeader);
    return this.strict;
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    int i = 2147483647;
    int j = 1;
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (i <= 0)
          break label107;
        if (j == 0)
          break;
        return getStrict().formatCookies(paramList);
      }
      Cookie localCookie = (Cookie)localIterator.next();
      if (!(localCookie instanceof SetCookie2))
        j = 0;
      if (localCookie.getVersion() < i)
        i = localCookie.getVersion();
    }
    return getObsoleteStrict().formatCookies(paramList);
    label107: return getCompat().formatCookies(paramList);
  }

  public int getVersion()
  {
    return getStrict().getVersion();
  }

  public Header getVersionHeader()
  {
    return getStrict().getVersionHeader();
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
        return getStrict().match(paramCookie, paramCookieOrigin);
      return getObsoleteStrict().match(paramCookie, paramCookieOrigin);
    }
    return getCompat().match(paramCookie, paramCookieOrigin);
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    HeaderElement[] arrayOfHeaderElement1 = paramHeader.getElements();
    int i = 0;
    int j = 0;
    int k = arrayOfHeaderElement1.length;
    int m = 0;
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser;
    CharArrayBuffer localCharArrayBuffer;
    if (m >= k)
    {
      if ((j == 0) && (i != 0))
        break label245;
      localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
      if (!(paramHeader instanceof FormattedHeader))
        break label183;
      localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
    }
    for (ParserCursor localParserCursor = new ParserCursor(((FormattedHeader)paramHeader).getValuePos(), localCharArrayBuffer.length()); ; localParserCursor = new ParserCursor(0, localCharArrayBuffer.length()))
    {
      HeaderElement[] arrayOfHeaderElement2 = new HeaderElement[1];
      arrayOfHeaderElement2[0] = localNetscapeDraftHeaderParser.parseHeader(localCharArrayBuffer, localParserCursor);
      return getCompat().parse(arrayOfHeaderElement2, paramCookieOrigin);
      HeaderElement localHeaderElement = arrayOfHeaderElement1[m];
      if (localHeaderElement.getParameterByName("version") != null)
        i = 1;
      if (localHeaderElement.getParameterByName("expires") != null)
        j = 1;
      m++;
      break;
      label183: String str = paramHeader.getValue();
      if (str == null)
        throw new MalformedCookieException("Header value is null");
      localCharArrayBuffer = new CharArrayBuffer(str.length());
      localCharArrayBuffer.append(str);
    }
    label245: if ("Set-Cookie2".equals(paramHeader.getName()))
      return getStrict().parse(arrayOfHeaderElement1, paramCookieOrigin);
    return getObsoleteStrict().parse(arrayOfHeaderElement1, paramCookieOrigin);
  }

  public String toString()
  {
    return "best-match";
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (paramCookie.getVersion() > 0)
    {
      if ((paramCookie instanceof SetCookie2))
      {
        getStrict().validate(paramCookie, paramCookieOrigin);
        return;
      }
      getObsoleteStrict().validate(paramCookie, paramCookieOrigin);
      return;
    }
    getCompat().validate(paramCookie, paramCookieOrigin);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.BestMatchSpec
 * JD-Core Version:    0.6.2
 */