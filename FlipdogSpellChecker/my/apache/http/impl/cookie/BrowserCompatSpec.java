package my.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.message.BasicHeaderElement;
import my.apache.http.message.BasicHeaderValueFormatter;
import my.apache.http.message.BufferedHeader;
import my.apache.http.message.ParserCursor;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BrowserCompatSpec extends CookieSpecBase
{
  private static final String[] DEFAULT_DATE_PATTERNS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" };
  private final String[] datepatterns;

  public BrowserCompatSpec()
  {
    this(null);
  }

  public BrowserCompatSpec(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null);
    for (this.datepatterns = ((String[])paramArrayOfString.clone()); ; this.datepatterns = DEFAULT_DATE_PATTERNS)
    {
      registerAttribHandler("path", new BasicPathHandler());
      registerAttribHandler("domain", new BasicDomainHandler());
      registerAttribHandler("max-age", new BasicMaxAgeHandler());
      registerAttribHandler("secure", new BasicSecureHandler());
      registerAttribHandler("comment", new BasicCommentHandler());
      registerAttribHandler("expires", new BasicExpiresHandler(this.datepatterns));
      registerAttribHandler("version", new BrowserCompatVersionAttributeHandler());
      return;
    }
  }

  public List<Header> formatCookies(List<Cookie> paramList)
  {
    if (paramList == null)
      throw new IllegalArgumentException("List of cookies may not be null");
    if (paramList.isEmpty())
      throw new IllegalArgumentException("List of cookies may not be empty");
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(20 * paramList.size());
    localCharArrayBuffer.append("Cookie");
    localCharArrayBuffer.append(": ");
    int i = 0;
    if (i >= paramList.size())
    {
      ArrayList localArrayList = new ArrayList(1);
      localArrayList.add(new BufferedHeader(localCharArrayBuffer));
      return localArrayList;
    }
    Cookie localCookie = (Cookie)paramList.get(i);
    if (i > 0)
      localCharArrayBuffer.append("; ");
    String str1 = localCookie.getName();
    String str2 = localCookie.getValue();
    if ((localCookie.getVersion() > 0) && ((!str2.startsWith("\"")) || (!str2.endsWith("\""))))
      BasicHeaderValueFormatter.DEFAULT.formatHeaderElement(localCharArrayBuffer, new BasicHeaderElement(str1, str2), false);
    while (true)
    {
      i++;
      break;
      localCharArrayBuffer.append(str1);
      localCharArrayBuffer.append("=");
      if (str2 != null)
        localCharArrayBuffer.append(str2);
    }
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
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    HeaderElement[] arrayOfHeaderElement = paramHeader.getElements();
    int i = 0;
    int j = 0;
    int k = arrayOfHeaderElement.length;
    int m = 0;
    NetscapeDraftHeaderParser localNetscapeDraftHeaderParser;
    CharArrayBuffer localCharArrayBuffer;
    if (m >= k)
      if ((j != 0) || (i == 0))
      {
        localNetscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
        if (!(paramHeader instanceof FormattedHeader))
          break label223;
        localCharArrayBuffer = ((FormattedHeader)paramHeader).getBuffer();
      }
    for (ParserCursor localParserCursor = new ParserCursor(((FormattedHeader)paramHeader).getValuePos(), localCharArrayBuffer.length()); ; localParserCursor = new ParserCursor(0, localCharArrayBuffer.length()))
    {
      arrayOfHeaderElement = new HeaderElement[1];
      arrayOfHeaderElement[0] = localNetscapeDraftHeaderParser.parseHeader(localCharArrayBuffer, localParserCursor);
      return parse(arrayOfHeaderElement, paramCookieOrigin);
      HeaderElement localHeaderElement = arrayOfHeaderElement[m];
      if (localHeaderElement.getParameterByName("version") != null)
        i = 1;
      if (localHeaderElement.getParameterByName("expires") != null)
        j = 1;
      m++;
      break;
      label223: String str = paramHeader.getValue();
      if (str == null)
        throw new MalformedCookieException("Header value is null");
      localCharArrayBuffer = new CharArrayBuffer(str.length());
      localCharArrayBuffer.append(str);
    }
  }

  public String toString()
  {
    return "compatibility";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.BrowserCompatSpec
 * JD-Core Version:    0.6.2
 */