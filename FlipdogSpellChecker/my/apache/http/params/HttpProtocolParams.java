package my.apache.http.params;

import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolVersion;
import my.apache.http.protocol.HTTP;

public final class HttpProtocolParams
  implements CoreProtocolPNames
{
  public static String getContentCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.content-charset");
    if (str == null)
      str = HTTP.DEF_CONTENT_CHARSET.name();
    return str;
  }

  public static String getHttpElementCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    if (str == null)
      str = HTTP.DEF_PROTOCOL_CHARSET.name();
    return str;
  }

  public static CodingErrorAction getMalformedInputAction(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.malformed.input.action");
    if (localObject == null)
      return CodingErrorAction.REPORT;
    return (CodingErrorAction)localObject;
  }

  public static CodingErrorAction getUnmappableInputAction(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.unmappable.input.action");
    if (localObject == null)
      return CodingErrorAction.REPORT;
    return (CodingErrorAction)localObject;
  }

  public static String getUserAgent(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return (String)paramHttpParams.getParameter("http.useragent");
  }

  public static ProtocolVersion getVersion(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Object localObject = paramHttpParams.getParameter("http.protocol.version");
    if (localObject == null)
      return HttpVersion.HTTP_1_1;
    return (ProtocolVersion)localObject;
  }

  public static void setContentCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.content-charset", paramString);
  }

  public static void setHttpElementCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.element-charset", paramString);
  }

  public static void setMalformedInputAction(HttpParams paramHttpParams, CodingErrorAction paramCodingErrorAction)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.malformed.input.action", paramCodingErrorAction);
  }

  public static void setUnmappableInputAction(HttpParams paramHttpParams, CodingErrorAction paramCodingErrorAction)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may no be null");
    paramHttpParams.setParameter("http.unmappable.input.action", paramCodingErrorAction);
  }

  public static void setUseExpectContinue(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.expect-continue", paramBoolean);
  }

  public static void setUserAgent(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.useragent", paramString);
  }

  public static void setVersion(HttpParams paramHttpParams, ProtocolVersion paramProtocolVersion)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.version", paramProtocolVersion);
  }

  public static boolean useExpectContinue(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.expect-continue", false);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.HttpProtocolParams
 * JD-Core Version:    0.6.2
 */