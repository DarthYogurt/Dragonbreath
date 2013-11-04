package my.apache.http.entity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;
import my.apache.http.Consts;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpEntity;
import my.apache.http.NameValuePair;
import my.apache.http.ParseException;
import my.apache.http.annotation.Immutable;
import my.apache.http.message.BasicHeaderValueParser;

@Immutable
public final class ContentType
  implements Serializable
{
  public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_JSON = create("application/json", Consts.UTF_8);
  public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", null);
  public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
  public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
  public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
  public static final ContentType DEFAULT_TEXT;
  public static final ContentType MULTIPART_FORM_DATA = create("multipart/form-data", Consts.ISO_8859_1);
  public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
  public static final ContentType TEXT_PLAIN = create("text/plain", Consts.ISO_8859_1);
  public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
  public static final ContentType WILDCARD = create("*/*", null);
  private static final long serialVersionUID = -7768694718232371896L;
  private final Charset charset;
  private final String mimeType;

  static
  {
    DEFAULT_TEXT = TEXT_PLAIN;
  }

  ContentType(String paramString, Charset paramCharset)
  {
    this.mimeType = paramString;
    this.charset = paramCharset;
  }

  public static ContentType create(String paramString)
  {
    return new ContentType(paramString, null);
  }

  public static ContentType create(String paramString1, String paramString2)
    throws UnsupportedCharsetException
  {
    if ((paramString2 != null) && (paramString2.length() > 0));
    for (Charset localCharset = Charset.forName(paramString2); ; localCharset = null)
      return create(paramString1, localCharset);
  }

  public static ContentType create(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      throw new IllegalArgumentException("MIME type may not be null");
    String str = paramString.trim().toLowerCase(Locale.US);
    if (str.length() == 0)
      throw new IllegalArgumentException("MIME type may not be empty");
    if (!valid(str))
      throw new IllegalArgumentException("MIME type may not contain reserved characters");
    return new ContentType(str, paramCharset);
  }

  private static ContentType create(HeaderElement paramHeaderElement)
  {
    String str1 = paramHeaderElement.getName();
    NameValuePair localNameValuePair = paramHeaderElement.getParameterByName("charset");
    String str2 = null;
    if (localNameValuePair != null)
      str2 = localNameValuePair.getValue();
    return create(str1, str2);
  }

  public static ContentType get(HttpEntity paramHttpEntity)
    throws ParseException, UnsupportedCharsetException
  {
    if (paramHttpEntity == null);
    HeaderElement[] arrayOfHeaderElement;
    do
    {
      Header localHeader;
      do
      {
        return null;
        localHeader = paramHttpEntity.getContentType();
      }
      while (localHeader == null);
      arrayOfHeaderElement = localHeader.getElements();
    }
    while (arrayOfHeaderElement.length <= 0);
    return create(arrayOfHeaderElement[0]);
  }

  public static ContentType getOrDefault(HttpEntity paramHttpEntity)
    throws ParseException, UnsupportedCharsetException
  {
    ContentType localContentType = get(paramHttpEntity);
    if (localContentType != null)
      return localContentType;
    return DEFAULT_TEXT;
  }

  public static ContentType parse(String paramString)
    throws ParseException, UnsupportedCharsetException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Content type may not be null");
    HeaderElement[] arrayOfHeaderElement = BasicHeaderValueParser.parseElements(paramString, null);
    if (arrayOfHeaderElement.length > 0)
      return create(arrayOfHeaderElement[0]);
    throw new ParseException("Invalid content type: " + paramString);
  }

  private static boolean valid(String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramString.length())
        return true;
      int j = paramString.charAt(i);
      if ((j == 34) || (j == 44) || (j == 59))
        return false;
    }
  }

  public Charset getCharset()
  {
    return this.charset;
  }

  public String getMimeType()
  {
    return this.mimeType;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.mimeType);
    if (this.charset != null)
    {
      localStringBuilder.append("; charset=");
      localStringBuilder.append(this.charset.name());
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.ContentType
 * JD-Core Version:    0.6.2
 */