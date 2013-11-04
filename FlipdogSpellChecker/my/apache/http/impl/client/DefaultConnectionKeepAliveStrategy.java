package my.apache.http.impl.client;

import my.apache.http.HeaderElement;
import my.apache.http.HeaderElementIterator;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.ConnectionKeepAliveStrategy;
import my.apache.http.message.BasicHeaderElementIterator;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultConnectionKeepAliveStrategy
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    BasicHeaderElementIterator localBasicHeaderElementIterator = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (true)
    {
      if (!localBasicHeaderElementIterator.hasNext())
        return -1L;
      HeaderElement localHeaderElement = localBasicHeaderElementIterator.nextElement();
      String str1 = localHeaderElement.getName();
      String str2 = localHeaderElement.getValue();
      if ((str2 != null) && (str1.equalsIgnoreCase("timeout")))
        try
        {
          long l = Long.parseLong(str2);
          return l * 1000L;
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
 * JD-Core Version:    0.6.2
 */