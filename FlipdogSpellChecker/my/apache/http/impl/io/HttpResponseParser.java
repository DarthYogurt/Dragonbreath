package my.apache.http.impl.io;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.HttpResponseFactory;
import my.apache.http.NoHttpResponseException;
import my.apache.http.ParseException;
import my.apache.http.StatusLine;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.message.LineParser;
import my.apache.http.message.ParserCursor;
import my.apache.http.params.HttpParams;
import my.apache.http.util.CharArrayBuffer;

@Deprecated
@NotThreadSafe
public class HttpResponseParser extends AbstractMessageParser<HttpMessage>
{
  private final CharArrayBuffer lineBuf;
  private final HttpResponseFactory responseFactory;

  public HttpResponseParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException
  {
    this.lineBuf.clear();
    if (paramSessionInputBuffer.readLine(this.lineBuf) == -1)
      throw new NoHttpResponseException("The target server failed to respond");
    ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
    StatusLine localStatusLine = this.lineParser.parseStatusLine(this.lineBuf, localParserCursor);
    return this.responseFactory.newHttpResponse(localStatusLine, null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.HttpResponseParser
 * JD-Core Version:    0.6.2
 */