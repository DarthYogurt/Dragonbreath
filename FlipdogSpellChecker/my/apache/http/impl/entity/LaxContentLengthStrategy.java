package my.apache.http.impl.entity;

import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.ParseException;
import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.ContentLengthStrategy;
import my.apache.http.params.HttpParams;

@Immutable
public class LaxContentLengthStrategy
  implements ContentLengthStrategy
{
  private final int implicitLen;

  public LaxContentLengthStrategy()
  {
    this(-1);
  }

  public LaxContentLengthStrategy(int paramInt)
  {
    this.implicitLen = paramInt;
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    if (paramHttpMessage == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    boolean bool = paramHttpMessage.getParams().isParameterTrue("http.protocol.strict-transfer-encoding");
    Header localHeader1 = paramHttpMessage.getFirstHeader("Transfer-Encoding");
    long l1;
    if (localHeader1 != null)
    {
      ((HeaderElement[])null);
      HeaderElement[] arrayOfHeaderElement;
      int j;
      while (true)
      {
        int k;
        try
        {
          arrayOfHeaderElement = localHeader1.getElements();
          if (bool)
          {
            k = 0;
            if (k < arrayOfHeaderElement.length);
          }
          else
          {
            j = arrayOfHeaderElement.length;
            if (!"identity".equalsIgnoreCase(localHeader1.getValue()))
              break;
            l1 = -1L;
            return l1;
          }
        }
        catch (ParseException localParseException)
        {
          throw new ProtocolException("Invalid Transfer-Encoding header value: " + localHeader1, localParseException);
        }
        String str = arrayOfHeaderElement[k].getName();
        if ((str != null) && (str.length() > 0) && (!str.equalsIgnoreCase("chunked")) && (!str.equalsIgnoreCase("identity")))
          throw new ProtocolException("Unsupported transfer encoding: " + str);
        k++;
      }
      if ((j > 0) && ("chunked".equalsIgnoreCase(arrayOfHeaderElement[(j - 1)].getName())))
        return -2L;
      if (bool)
        throw new ProtocolException("Chunk-encoding must be the last one applied");
      return -1L;
    }
    if (paramHttpMessage.getFirstHeader("Content-Length") != null)
    {
      l1 = -1L;
      Header[] arrayOfHeader = paramHttpMessage.getHeaders("Content-Length");
      if ((bool) && (arrayOfHeader.length > 1))
        throw new ProtocolException("Multiple content length headers");
      int i = -1 + arrayOfHeader.length;
      while (true)
      {
        if (i < 0);
        while (l1 < 0L)
        {
          return -1L;
          Header localHeader2 = arrayOfHeader[i];
          try
          {
            long l2 = Long.parseLong(localHeader2.getValue());
            l1 = l2;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            if (bool)
              throw new ProtocolException("Invalid content length: " + localHeader2.getValue());
            i--;
          }
        }
      }
    }
    return this.implicitLen;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.entity.LaxContentLengthStrategy
 * JD-Core Version:    0.6.2
 */