package my.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.cookie.ClientCookie;
import my.apache.http.cookie.Cookie;
import my.apache.http.cookie.CookieAttributeHandler;
import my.apache.http.cookie.CookieOrigin;
import my.apache.http.cookie.MalformedCookieException;
import my.apache.http.message.BufferedHeader;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class RFC2965Spec extends RFC2109Spec
{
  public RFC2965Spec()
  {
    this(null, false);
  }

  public RFC2965Spec(String[] paramArrayOfString, boolean paramBoolean)
  {
    super(paramArrayOfString, paramBoolean);
    registerAttribHandler("domain", new RFC2965DomainAttributeHandler());
    registerAttribHandler("port", new RFC2965PortAttributeHandler());
    registerAttribHandler("commenturl", new RFC2965CommentUrlAttributeHandler());
    registerAttribHandler("discard", new RFC2965DiscardAttributeHandler());
    registerAttribHandler("version", new RFC2965VersionAttributeHandler());
  }

  private static CookieOrigin adjustEffectiveHost(CookieOrigin paramCookieOrigin)
  {
    String str = paramCookieOrigin.getHost();
    int i = 1;
    for (int j = 0; ; j++)
    {
      if (j >= str.length());
      while (true)
      {
        if (i != 0)
          paramCookieOrigin = new CookieOrigin(str + ".local", paramCookieOrigin.getPort(), paramCookieOrigin.getPath(), paramCookieOrigin.isSecure());
        return paramCookieOrigin;
        int k = str.charAt(j);
        if ((k != 46) && (k != 58))
          break;
        i = 0;
      }
    }
  }

  private List<Cookie> createCookies(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
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
    BasicClientCookie2 localBasicClientCookie2 = new BasicClientCookie2(str1, str2);
    localBasicClientCookie2.setPath(getDefaultPath(paramCookieOrigin));
    localBasicClientCookie2.setDomain(getDefaultDomain(paramCookieOrigin));
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = paramCookieOrigin.getPort();
    localBasicClientCookie2.setPorts(arrayOfInt);
    NameValuePair[] arrayOfNameValuePair = localHeaderElement.getParameters();
    HashMap localHashMap = new HashMap(arrayOfNameValuePair.length);
    int k = -1 + arrayOfNameValuePair.length;
    label152: Iterator localIterator;
    if (k < 0)
      localIterator = localHashMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localArrayList.add(localBasicClientCookie2);
        j++;
        break;
        NameValuePair localNameValuePair1 = arrayOfNameValuePair[k];
        localHashMap.put(localNameValuePair1.getName().toLowerCase(Locale.ENGLISH), localNameValuePair1);
        k--;
        break label152;
      }
      NameValuePair localNameValuePair2 = (NameValuePair)((Map.Entry)localIterator.next()).getValue();
      String str3 = localNameValuePair2.getName().toLowerCase(Locale.ENGLISH);
      localBasicClientCookie2.setAttribute(str3, localNameValuePair2.getValue());
      CookieAttributeHandler localCookieAttributeHandler = findAttribHandler(str3);
      if (localCookieAttributeHandler != null)
        localCookieAttributeHandler.parse(localBasicClientCookie2, localNameValuePair2.getValue());
    }
  }

  protected void formatCookieAsVer(CharArrayBuffer paramCharArrayBuffer, Cookie paramCookie, int paramInt)
  {
    super.formatCookieAsVer(paramCharArrayBuffer, paramCookie, paramInt);
    int[] arrayOfInt;
    int i;
    int j;
    if ((paramCookie instanceof ClientCookie))
    {
      String str = ((ClientCookie)paramCookie).getAttribute("port");
      if (str != null)
      {
        paramCharArrayBuffer.append("; $Port");
        paramCharArrayBuffer.append("=\"");
        if (str.trim().length() > 0)
        {
          arrayOfInt = paramCookie.getPorts();
          if (arrayOfInt != null)
          {
            i = 0;
            j = arrayOfInt.length;
          }
        }
      }
    }
    while (true)
    {
      if (i >= j)
      {
        paramCharArrayBuffer.append("\"");
        return;
      }
      if (i > 0)
        paramCharArrayBuffer.append(",");
      paramCharArrayBuffer.append(Integer.toString(arrayOfInt[i]));
      i++;
    }
  }

  public int getVersion()
  {
    return 1;
  }

  public Header getVersionHeader()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(40);
    localCharArrayBuffer.append("Cookie2");
    localCharArrayBuffer.append(": ");
    localCharArrayBuffer.append("$Version=");
    localCharArrayBuffer.append(Integer.toString(getVersion()));
    return new BufferedHeader(localCharArrayBuffer);
  }

  public boolean match(Cookie paramCookie, CookieOrigin paramCookieOrigin)
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    return super.match(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }

  public List<Cookie> parse(Header paramHeader, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    if (!paramHeader.getName().equalsIgnoreCase("Set-Cookie2"))
      throw new MalformedCookieException("Unrecognized cookie header '" + paramHeader.toString() + "'");
    CookieOrigin localCookieOrigin = adjustEffectiveHost(paramCookieOrigin);
    return createCookies(paramHeader.getElements(), localCookieOrigin);
  }

  protected List<Cookie> parse(HeaderElement[] paramArrayOfHeaderElement, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    return createCookies(paramArrayOfHeaderElement, adjustEffectiveHost(paramCookieOrigin));
  }

  public String toString()
  {
    return "rfc2965";
  }

  public void validate(Cookie paramCookie, CookieOrigin paramCookieOrigin)
    throws MalformedCookieException
  {
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    if (paramCookieOrigin == null)
      throw new IllegalArgumentException("Cookie origin may not be null");
    super.validate(paramCookie, adjustEffectiveHost(paramCookieOrigin));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2965Spec
 * JD-Core Version:    0.6.2
 */