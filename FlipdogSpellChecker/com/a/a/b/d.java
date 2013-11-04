package com.a.a.b;

import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.HttpResponse;
import my.apache.http.HttpVersion;
import my.apache.http.ParseException;
import my.apache.http.ProtocolVersion;
import my.apache.http.StatusLine;
import my.apache.http.TokenIterator;
import my.apache.http.impl.DefaultConnectionReuseStrategy;
import my.apache.http.protocol.HttpContext;

class d extends DefaultConnectionReuseStrategy
{
  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null.");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null.");
    ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
    Header localHeader1 = paramHttpResponse.getFirstHeader("Transfer-Encoding");
    if (localHeader1 != null)
      if ("chunked".equalsIgnoreCase(localHeader1.getValue()))
        break label117;
    while (true)
    {
      return false;
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Length");
      if ((arrayOfHeader != null) && (arrayOfHeader.length == 1))
      {
        Header localHeader2 = arrayOfHeader[0];
        try
        {
          int i = Integer.parseInt(localHeader2.getValue());
          if (i >= 0)
          {
            label117: HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Connection");
            if (!localHeaderIterator.hasNext())
              localHeaderIterator = paramHttpResponse.headerIterator("Proxy-Connection");
            if (localHeaderIterator.hasNext());
            try
            {
              TokenIterator localTokenIterator = createTokenIterator(localHeaderIterator);
              int j = 0;
              while (true)
              {
                if (!localTokenIterator.hasNext())
                {
                  if (j == 0)
                    break label224;
                  return true;
                }
                String str = localTokenIterator.nextToken();
                if ("Close".equalsIgnoreCase(str))
                  break;
                boolean bool2 = "Keep-Alive".equalsIgnoreCase(str);
                if (bool2)
                  j = 1;
              }
              label224: if (localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0));
              for (boolean bool1 = false; ; bool1 = true)
                return bool1;
            }
            catch (ParseException localParseException)
            {
              return false;
            }
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.d
 * JD-Core Version:    0.6.2
 */