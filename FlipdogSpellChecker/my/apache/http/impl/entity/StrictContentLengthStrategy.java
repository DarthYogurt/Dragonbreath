package my.apache.http.impl.entity;

import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.HttpVersion;
import my.apache.http.ProtocolException;
import my.apache.http.ProtocolVersion;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.ContentLengthStrategy;

@Immutable
public class StrictContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public StrictContentLengthStrategy()
  {
    this(-1);
  }

  public StrictContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    Header localHeader1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    String str2;
    long l;
    if (localHeader1 != null)
    {
      str2 = localHeader1.getValue();
      if ("chunked".equalsIgnoreCase(str2))
      {
        if (paramHttpMessage.getProtocolVersion().lessEquals(HttpVersion.HTTP_1_0))
          throw new ProtocolException("Chunked transfer encoding not allowed for " + paramHttpMessage.getProtocolVersion());
        l = -2L;
      }
    }
    while (true)
    {
      return l;
      if ("identity".equalsIgnoreCase(str2))
        return -1L;
      throw new ProtocolException("Unsupported transfer encoding: " + str2);
      Header localHeader2 = paramHttpMessage.getFirstHeader("Content-Length");
      if (localHeader2 != null)
      {
        String str1 = localHeader2.getValue();
        try
        {
          l = Long.parseLong(str1);
          if (l < 0L)
            throw new ProtocolException("Negative content length: " + str1);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new ProtocolException("Invalid content length: " + str1);
        }
      }
    }
    return this.implicitLen;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.entity.StrictContentLengthStrategy
 * JD-Core Version:    0.6.2
 */