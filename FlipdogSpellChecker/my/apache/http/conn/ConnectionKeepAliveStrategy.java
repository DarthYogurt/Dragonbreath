package my.apache.http.conn;

import my.apache.http.HttpResponse;
import my.apache.http.protocol.HttpContext;

public abstract interface ConnectionKeepAliveStrategy
{
  public abstract long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ConnectionKeepAliveStrategy
 * JD-Core Version:    0.6.2
 */