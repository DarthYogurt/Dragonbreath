package my.apache.http.message;

import my.apache.http.Header;
import my.apache.http.ParseException;
import my.apache.http.ProtocolVersion;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.util.CharArrayBuffer;

public abstract interface LineParser
{
  public abstract boolean hasProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor);

  public abstract Header parseHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException;

  public abstract ProtocolVersion parseProtocolVersion(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;

  public abstract RequestLine parseRequestLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;

  public abstract StatusLine parseStatusLine(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.LineParser
 * JD-Core Version:    0.6.2
 */