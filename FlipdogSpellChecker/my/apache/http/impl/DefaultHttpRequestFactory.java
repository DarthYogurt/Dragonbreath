package my.apache.http.impl;

import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestFactory;
import my.apache.http.MethodNotSupportedException;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.message.BasicHttpEntityEnclosingRequest;
import my.apache.http.message.BasicHttpRequest;

@Immutable
public class DefaultHttpRequestFactory
  implements HttpRequestFactory
{
  private static final String[] RFC2616_COMMON_METHODS = { "GET" };
  private static final String[] RFC2616_ENTITY_ENC_METHODS = { "POST", "PUT" };
  private static final String[] RFC2616_SPECIAL_METHODS = { "HEAD", "OPTIONS", "DELETE", "TRACE", "CONNECT" };

  private static boolean isOneOf(String[] paramArrayOfString, String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfString.length)
        return false;
      if (paramArrayOfString[i].equalsIgnoreCase(paramString))
        return true;
    }
  }

  public HttpRequest newHttpRequest(String paramString1, String paramString2)
    throws MethodNotSupportedException
  {
    if (isOneOf(RFC2616_COMMON_METHODS, paramString1))
      return new BasicHttpRequest(paramString1, paramString2);
    if (isOneOf(RFC2616_ENTITY_ENC_METHODS, paramString1))
      return new BasicHttpEntityEnclosingRequest(paramString1, paramString2);
    if (isOneOf(RFC2616_SPECIAL_METHODS, paramString1))
      return new BasicHttpRequest(paramString1, paramString2);
    throw new MethodNotSupportedException(paramString1 + " method not supported");
  }

  public HttpRequest newHttpRequest(RequestLine paramRequestLine)
    throws MethodNotSupportedException
  {
    if (paramRequestLine == null)
      throw new IllegalArgumentException("Request line may not be null");
    String str = paramRequestLine.getMethod();
    if (isOneOf(RFC2616_COMMON_METHODS, str))
      return new BasicHttpRequest(paramRequestLine);
    if (isOneOf(RFC2616_ENTITY_ENC_METHODS, str))
      return new BasicHttpEntityEnclosingRequest(paramRequestLine);
    if (isOneOf(RFC2616_SPECIAL_METHODS, str))
      return new BasicHttpRequest(paramRequestLine);
    throw new MethodNotSupportedException(str + " method not supported");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.DefaultHttpRequestFactory
 * JD-Core Version:    0.6.2
 */