package my.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import my.apache.http.Header;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.ClientCookie;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.CookiePathComparator;
import my.apache.http.cookie.CookieRestrictionViolationException;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.message.BufferedHeader;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class RFC2109Spec extends CookieSpecBase
{
  private static final String[] DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy" };
  private static final CookiePathComparator PATH_COMPARATOR = new CookiePathComparator();
  private final String[] datepatterns;
  private final boolean oneHeader;

  public RFC2109Spec()
  {
    this(null, false);
  }

  public RFC2109Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramArrayOfString != null);
    for (this.datepatterns = ((String[])paramArrayOfString.clone()); ; this.datepatterns = DATE_PATTERNS)
    {
      this.oneHeader = paramBoolean;
      registerAttribHandler("version", new RFC2109VersionHandler());
      registerAttribHandler("path", new BasicPathHandler());
      registerAttribHandler("domain", new RFC2109DomainHandler());
      registerAttribHandler("max-age", new BasicMaxAgeHandler());
      registerAttribHandler("secure", new BasicSecureHandler());
      registerAttribHandler("comment", new BasicCommentHandler());
      registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
      return;
    }
  }

  private List<Header> doFormatManyHeaders(List<Cookie> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Cookie localCookie = (Cookie)localIterator.next();
      int i = localCookie.getVersion();
      CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
      localCharArrayBuffer.append("Cookie: ");
      localCharArrayBuffer.append("$Version=");
      localCharArrayBuffer.append(Integer.toString(i));
      localCharArrayBuffer.append("; ");
      formatCookieAsVer(localCharArrayBuffer, localCookie, i);
      localArrayList.add(new BufferedHeader(localCharArrayBuffer));
    }
  }

  private List<Header> doFormatOneHeader(List<Cookie> paramList)
  {
    int i = 2147483647;
    Iterator localIterator1 = paramList.iterator();
    CharArrayBuffer localCharArrayBuffer;
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      localCharArrayBuffer = new CharArrayBuffer(40 * paramList.size());
      localCharArrayBuffer.append("Cookie");
      localCharArrayBuffer.append(": ");
      localCharArrayBuffer.append("$Version=");
      localCharArrayBuffer.append(Integer.toString(i));
      localIterator2 = paramList.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        ArrayList localArrayList = new ArrayList(1);
        localArrayList.add(new BufferedHeader(localCharArrayBuffer));
        return localArrayList;
        Cookie localCookie2 = (Cookie)localIterator1.next();
        if (localCookie2.getVersion() >= i)
          break;
        i = localCookie2.getVersion();
        break;
      }
      Cookie localCookie1 = (Cookie)localIterator2.next();
      localCharArrayBuffer.append("; ");
      formatCookieAsVer(localCharArrayBuffer, localCookie1, i);
    }
  }

  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    formatParamAsVer(paramCharArrayBuffer, paramCookie.getName(), paramCookie.getValue(), paramInt);
    if ((paramCookie.getPath() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("path")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Path", paramCookie.getPath(), paramInt);
    }
    if ((paramCookie.getDomain() != null) && ((paramCookie instanceof ClientCookie)) && (((ClientCookie)paramCookie).containsAttribute("domain")))
    {
      paramCharArrayBuffer.append("; ");
      formatParamAsVer(paramCharArrayBuffer, "$Domain", paramCookie.getDomain(), paramInt);
    }
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    if (paramList.size() > 1)
    {
      ArrayList localArrayList = new ArrayList(paramList);
      Collections.sort(localArrayList, PATH_COMPARATOR);
      paramList = localArrayList;
    }
    if (this.oneHeader)
      return doFormatOneHeader(paramList);
    return doFormatManyHeaders(paramList);
  }

  protected void formatParamAsVer(CharArrayBuffer paramCharArrayBuffer, String paramString1, String paramString2, int paramInt)
  {
    paramCharArrayBuffer.append(paramString1);
    paramCharArrayBuffer.append("=");
    if (paramString2 != null)
    {
      if (paramInt > 0)
      {
        paramCharArrayBuffer.append('"');
        paramCharArrayBuffer.append(paramString2);
        paramCharArrayBuffer.append('"');
      }
    }
    else
      return;
    paramCharArrayBuffer.append(paramString2);
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    return null;
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    return parse(paramHeader.getElements(), paramCookieOrigin);
  }

  public String toString()
  {
    return "rfc2109";
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    String str = paramCookie.getName();
    if (str.indexOf(' ') != -1)
      throw new CookieRestrictionViolationException("Cookie name may not contain blanks");
    if (str.startsWith("$"))
      throw new CookieRestrictionViolationException("Cookie name may not start with $");
    super.validate(paramCookie, paramCookieOrigin);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2109Spec
 * JD-Core Version:    0.6.2
 */