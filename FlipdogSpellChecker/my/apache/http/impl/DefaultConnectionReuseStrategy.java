package my.apache.http.impl;

import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.HttpResponse;
import my.apache.http.HttpVersion;
import my.apache.http.ParseException;
import my.apache.http.ProtocolVersion;
import my.apache.http.StatusLine;
import my.apache.http.TokenIterator;
import my.apache.http.annotation.Immutable;
import my.apache.http.message.BasicTokenIterator;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultConnectionReuseStrategy
  implements ConnectionReuseStrategy
{
  private boolean canResponseHaveBody(HttpResponse paramHttpResponse)
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    return (i >= 200) && (i != 204) && (i != 304) && (i != 205);
  }

  protected TokenIterator createTokenIterator(HeaderIterator paramHeaderIterator)
  {
    return new BasicTokenIterator(paramHeaderIterator);
  }

  public boolean keepAlive(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null.");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null.");
    ProtocolVersion localProtocolVersion = paramHttpResponse.getStatusLine().getProtocolVersion();
    Header localHeader1 = paramHttpResponse.getFirstHeader("Transfer-Encoding");
    if (localHeader1 != null)
    {
      if (!"chunked".equalsIgnoreCase(localHeader1.getValue()))
        return false;
    }
    else if (canResponseHaveBody(paramHttpResponse))
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Length");
      if (arrayOfHeader.length == 1)
      {
        Header localHeader2 = arrayOfHeader[0];
        try
        {
          int j = Integer.parseInt(localHeader2.getValue());
          if (j >= 0)
            break label128;
          return false;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          return false;
        }
      }
      else
      {
        return false;
      }
    }
    label128: HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Connection");
    if (!localHeaderIterator.hasNext())
      localHeaderIterator = paramHttpResponse.headerIterator("Proxy-Connection");
    if (localHeaderIterator.hasNext())
      try
      {
        TokenIterator localTokenIterator = createTokenIterator(localHeaderIterator);
        int i = 0;
        while (true)
        {
          if (!localTokenIterator.hasNext())
          {
            if (i == 0)
              break;
            return true;
          }
          String str = localTokenIterator.nextToken();
          if ("Close".equalsIgnoreCase(str))
            return false;
          boolean bool = "Keep-Alive".equalsIgnoreCase(str);
          if (bool)
            i = 1;
        }
      }
      catch (ParseException localParseException)
      {
        return false;
      }
    return !localProtocolVersion.lessEquals(HttpVersion.HTTP_1_0);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.DefaultConnectionReuseStrategy
 * JD-Core Version:    0.6.2
 */