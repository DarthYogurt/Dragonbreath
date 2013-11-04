package com.a.a.b;

import my.apache.http.HeaderElement;
import my.apache.http.HeaderElementIterator;
import my.apache.http.HttpResponse;
import my.apache.http.conn.ConnectionKeepAliveStrategy;
import my.apache.http.message.BasicHeaderElementIterator;
import my.apache.http.protocol.HttpContext;

class e
  implements ConnectionKeepAliveStrategy
{
  public long getKeepAliveDuration(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    long l1 = 20000L;
    BasicHeaderElementIterator localBasicHeaderElementIterator = new BasicHeaderElementIterator(paramHttpResponse.headerIterator("Keep-Alive"));
    while (true)
    {
      if (!localBasicHeaderElementIterator.hasNext())
        return l1;
      HeaderElement localHeaderElement = localBasicHeaderElementIterator.nextElement();
      String str1 = localHeaderElement.getName();
      String str2 = localHeaderElement.getValue();
      if ((str2 != null) && (str1.equalsIgnoreCase("timeout")))
        try
        {
          long l2 = Math.min(l1, 1000L * Long.parseLong(str2));
          l1 = l2;
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.e
 * JD-Core Version:    0.6.2
 */