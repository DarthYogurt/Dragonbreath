package my.apache.http.client.params;

import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;

@Immutable
public class HttpClientParams
{
  public static long getConnectionManagerTimeout(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Long localLong = (Long)paramHttpParams.getParameter("http.conn-manager.timeout");
    if (localLong != null)
      return localLong.longValue();
    return HttpConnectionParams.getConnectionTimeout(paramHttpParams);
  }

  public static String getCookiePolicy(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.cookie-policy");
    if (str == null)
      str = "best-match";
    return str;
  }

  public static boolean isAuthenticating(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-authentication", true);
  }

  public static boolean isRedirecting(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.handle-redirects", true);
  }

  public static void setAuthenticating(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.handle-authentication", paramBoolean);
  }

  public static void setConnectionManagerTimeout(HttpParams paramHttpParams, long paramLong)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setLongParameter("http.conn-manager.timeout", paramLong);
  }

  public static void setCookiePolicy(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.cookie-policy", paramString);
  }

  public static void setRedirecting(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.handle-redirects", paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.params.HttpClientParams
 * JD-Core Version:    0.6.2
 */