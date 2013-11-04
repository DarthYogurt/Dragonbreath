package my.apache.http.impl.conn;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.HttpResponseFactory;
import my.apache.http.NoHttpResponseException;
import my.apache.http.ProtocolException;
import my.apache.http.StatusLine;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.impl.io.AbstractMessageParser;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.message.LineParser;
import my.apache.http.message.ParserCursor;
import my.apache.http.params.HttpParams;
import my.apache.http.util.CharArrayBuffer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ThreadSafe
public class DefaultResponseParser extends AbstractMessageParser<HttpMessage>
{
  private final CharArrayBuffer lineBuf;
  private final Log log = LogFactory.getLog(getClass());
  private final int maxGarbageLines;
  private final HttpResponseFactory responseFactory;

  public DefaultResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
    this.lineBuf = new CharArrayBuffer(128);
    this.maxGarbageLines = getMaxGarbageLines(paramHttpParams);
  }

  protected int getMaxGarbageLines(HttpParams paramHttpParams)
  {
    return paramHttpParams.getIntParameter("http.connection.max-status-line-garbage", 2147483647);
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException
  {
    for (int i = 0; ; i++)
    {
      this.lineBuf.clear();
      int j = paramSessionInputBuffer.readLine(this.lineBuf);
      if ((j == -1) && (i == 0))
        throw new NoHttpResponseException("The target server failed to respond");
      ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
      if (this.lineParser.hasProtocolVersion(this.lineBuf, localParserCursor))
      {
        StatusLine localStatusLine = this.lineParser.parseStatusLine(this.lineBuf, localParserCursor);
        return this.responseFactory.newHttpResponse(localStatusLine, null);
      }
      if ((j == -1) || (i >= this.maxGarbageLines))
        throw new ProtocolException("The server failed to respond with a valid HTTP response");
      if (this.log.isDebugEnabled())
        this.log.debug("Garbage in response: " + this.lineBuf.toString());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.DefaultResponseParser
 * JD-Core Version:    0.6.2
 */