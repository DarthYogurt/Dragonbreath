package my.apache.http.impl.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.ParseException;
import my.apache.http.ProtocolException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.HttpMessageParser;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.message.BasicLineParser;
import my.apache.http.message.LineParser;
import my.apache.http.params.HttpParams;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractMessageParser<T extends HttpMessage>
  implements HttpMessageParser<T>
{
  private static final int HEADERS = 1;
  private static final int HEAD_LINE;
  private final List<CharArrayBuffer> headerLines;
  protected final LineParser lineParser;
  private final int maxHeaderCount;
  private final int maxLineLen;
  private T message;
  private final SessionInputBuffer sessionBuffer;
  private int state;

  public AbstractMessageParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpParams paramHttpParams)
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.sessionBuffer = paramSessionInputBuffer;
    this.maxHeaderCount = paramHttpParams.getIntParameter("http.connection.max-header-count", -1);
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    if (paramLineParser != null);
    while (true)
    {
      this.lineParser = paramLineParser;
      this.headerLines = new ArrayList();
      this.state = 0;
      return;
      paramLineParser = BasicLineParser.DEFAULT;
    }
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser)
    throws HttpException, IOException
  {
    if (paramLineParser == null)
      paramLineParser = BasicLineParser.DEFAULT;
    return parseHeaders(paramSessionInputBuffer, paramInt1, paramInt2, paramLineParser, new ArrayList());
  }

  public static Header[] parseHeaders(SessionInputBuffer paramSessionInputBuffer, int paramInt1, int paramInt2, LineParser paramLineParser, List<CharArrayBuffer> paramList)
    throws HttpException, IOException
  {
    if (paramSessionInputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    if (paramLineParser == null)
      throw new IllegalArgumentException("Line parser may not be null");
    if (paramList == null)
      throw new IllegalArgumentException("Header line list may not be null");
    CharArrayBuffer localCharArrayBuffer1 = null;
    CharArrayBuffer localCharArrayBuffer2 = null;
    label65: Header[] arrayOfHeader;
    int i;
    if (localCharArrayBuffer1 == null)
    {
      localCharArrayBuffer1 = new CharArrayBuffer(64);
      if ((paramSessionInputBuffer.readLine(localCharArrayBuffer1) != -1) && (localCharArrayBuffer1.length() >= 1))
        break label124;
      arrayOfHeader = new Header[paramList.size()];
      i = 0;
    }
    while (true)
    {
      if (i >= paramList.size())
      {
        return arrayOfHeader;
        localCharArrayBuffer1.clear();
        break label65;
        label124: if (((localCharArrayBuffer1.charAt(0) == ' ') || (localCharArrayBuffer1.charAt(0) == '\t')) && (localCharArrayBuffer2 != null))
        {
          for (int j = 0; ; j++)
          {
            if (j >= localCharArrayBuffer1.length());
            int k;
            do
            {
              if ((paramInt2 <= 0) || (1 + localCharArrayBuffer2.length() + localCharArrayBuffer1.length() - j <= paramInt2))
                break;
              throw new IOException("Maximum line length limit exceeded");
              k = localCharArrayBuffer1.charAt(j);
            }
            while ((k != 32) && (k != 9));
          }
          localCharArrayBuffer2.append(' ');
          localCharArrayBuffer2.append(localCharArrayBuffer1, j, localCharArrayBuffer1.length() - j);
        }
        while ((paramInt1 > 0) && (paramList.size() >= paramInt1))
        {
          throw new IOException("Maximum header count exceeded");
          paramList.add(localCharArrayBuffer1);
          localCharArrayBuffer2 = localCharArrayBuffer1;
          localCharArrayBuffer1 = null;
        }
        break;
      }
      CharArrayBuffer localCharArrayBuffer3 = (CharArrayBuffer)paramList.get(i);
      try
      {
        arrayOfHeader[i] = paramLineParser.parseHeader(localCharArrayBuffer3);
        i++;
      }
      catch (ParseException localParseException)
      {
        throw new ProtocolException(localParseException.getMessage());
      }
    }
  }

  public T parse()
    throws IOException, HttpException
  {
    switch (this.state)
    {
    default:
      throw new IllegalStateException("Inconsistent parser state");
    case 0:
    case 1:
    }
    try
    {
      this.message = parseHead(this.sessionBuffer);
      this.state = 1;
      Header[] arrayOfHeader = parseHeaders(this.sessionBuffer, this.maxHeaderCount, this.maxLineLen, this.lineParser, this.headerLines);
      this.message.setHeaders(arrayOfHeader);
      HttpMessage localHttpMessage = this.message;
      this.message = null;
      this.headerLines.clear();
      this.state = 0;
      return localHttpMessage;
    }
    catch (ParseException localParseException)
    {
      throw new ProtocolException(localParseException.getMessage(), localParseException);
    }
  }

  protected abstract T parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.AbstractMessageParser
 * JD-Core Version:    0.6.2
 */