package my.apache.http.impl.io;

import java.io.IOException;
import my.apache.http.ConnectionClosedException;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.HttpRequestFactory;
import my.apache.http.ParseException;
import my.apache.http.RequestLine;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.message.LineParser;
import my.apache.http.message.ParserCursor;
import my.apache.http.params.HttpParams;
import my.apache.http.util.CharArrayBuffer;

@Deprecated
@NotThreadSafe
public class HttpRequestParser extends AbstractMessageParser<HttpMessage>
{
  private final CharArrayBuffer lineBuf;
  private final HttpRequestFactory requestFactory;

  public HttpRequestParser(SessionInputBuffer paramSessionInputBuffer, LineParser paramLineParser, HttpRequestFactory paramHttpRequestFactory, HttpParams paramHttpParams)
  {
    super(paramSessionInputBuffer, paramLineParser, paramHttpParams);
    if (paramHttpRequestFactory == null)
      throw new IllegalArgumentException("Request factory may not be null");
    this.requestFactory = paramHttpRequestFactory;
    this.lineBuf = new CharArrayBuffer(128);
  }

  protected HttpMessage parseHead(SessionInputBuffer paramSessionInputBuffer)
    throws IOException, HttpException, ParseException
  {
    this.lineBuf.clear();
    if (paramSessionInputBuffer.readLine(this.lineBuf) == -1)
      throw new ConnectionClosedException("Client closed connection");
    ParserCursor localParserCursor = new ParserCursor(0, this.lineBuf.length());
    RequestLine localRequestLine = this.lineParser.parseRequestLine(this.lineBuf, localParserCursor);
    return this.requestFactory.newHttpRequest(localRequestLine);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.HttpRequestParser
 * JD-Core Version:    0.6.2
 */