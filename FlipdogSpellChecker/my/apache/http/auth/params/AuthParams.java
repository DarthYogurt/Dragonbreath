package my.apache.http.auth.params;

import java.nio.charset.Charset;
import my.apache.http.annotation.Immutable;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HTTP;

@Immutable
public final class AuthParams
{
  public static String getCredentialCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.auth.credential-charset");
    if (str == null)
      str = HTTP.DEF_PROTOCOL_CHARSET.name();
    return str;
  }

  public static void setCredentialCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.auth.credential-charset", paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.params.AuthParams
 * JD-Core Version:    0.6.2
 */