package my.apache.http.impl;

import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.Immutable;
import my.apache.http.protocol.HttpContext;

@Immutable
public class NoConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.NoConnectionReuseStrategy
 * JD-Core Version:    0.6.2
 */