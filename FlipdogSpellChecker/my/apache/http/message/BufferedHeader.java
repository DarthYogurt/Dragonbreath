package my.apache.http.message;

import java.io.Serializable;
import my.apache.http.FormattedHeader;
import my.apache.http.HeaderElement;
import my.apache.http.ParseException;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BufferedHeader
  implements FormattedHeader, Cloneable, Serializable
{
  private static final long serialVersionUID = -2768352615787625448L;
  private final CharArrayBuffer buffer;
  private final String name;
  private final int valuePos;

  public BufferedHeader(CharArrayBuffer paramCharArrayBuffer)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int i = paramCharArrayBuffer.indexOf(58);
    if (i == -1)
      throw new ParseException("Invalid header: " + paramCharArrayBuffer.toString());
    String str = paramCharArrayBuffer.substringTrimmed(0, i);
    if (str.length() == 0)
      throw new ParseException("Invalid header: " + paramCharArrayBuffer.toString());
    this.buffer = paramCharArrayBuffer;
    this.name = str;
    this.valuePos = (i + 1);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public CharArrayBuffer getBuffer()
  {
    return this.buffer;
  }

  public HeaderElement[] getElements()
    throws ParseException
  {
    ParserCursor localParserCursor = new ParserCursor(0, this.buffer.length());
    localParserCursor.updatePos(this.valuePos);
    return BasicHeaderValueParser.DEFAULT.parseElements(this.buffer, localParserCursor);
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.buffer.substringTrimmed(this.valuePos, this.buffer.length());
  }

  public int getValuePos()
  {
    return this.valuePos;
  }

  public String toString()
  {
    return this.buffer.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BufferedHeader
 * JD-Core Version:    0.6.2
 */