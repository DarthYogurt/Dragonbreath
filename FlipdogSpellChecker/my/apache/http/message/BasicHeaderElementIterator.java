package my.apache.http.message;

import java.util.NoSuchElementException;
import my.apache.http.FormattedHeader;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HeaderElementIterator;
import my.apache.http.HeaderIterator;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class BasicHeaderElementIterator
  implements HeaderElementIterator
{
  private CharArrayBuffer buffer = null;
  private HeaderElement currentElement = null;
  private ParserCursor cursor = null;
  private final HeaderIterator headerIt;
  private final HeaderValueParser parser;

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator)
  {
    this(paramHeaderIterator, BasicHeaderValueParser.DEFAULT);
  }

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator, HeaderValueParser paramHeaderValueParser)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator may not be null");
    if (paramHeaderValueParser == null)
      throw new IllegalArgumentException("Parser may not be null");
    this.headerIt = paramHeaderIterator;
    this.parser = paramHeaderValueParser;
  }

  private void bufferHeaderValue()
  {
    this.cursor = null;
    this.buffer = null;
    String str;
    do
    {
      if (!this.headerIt.hasNext())
        return;
      Header localHeader = this.headerIt.nextHeader();
      if ((localHeader instanceof FormattedHeader))
      {
        this.buffer = ((FormattedHeader)localHeader).getBuffer();
        this.cursor = new ParserCursor(0, this.buffer.length());
        this.cursor.updatePos(((FormattedHeader)localHeader).getValuePos());
        return;
      }
      str = localHeader.getValue();
    }
    while (str == null);
    this.buffer = new CharArrayBuffer(str.length());
    this.buffer.append(str);
    this.cursor = new ParserCursor(0, this.buffer.length());
  }

  private void parseNextElement()
  {
    do
    {
      if ((!this.headerIt.hasNext()) && (this.cursor == null))
        return;
      if ((this.cursor == null) || (this.cursor.atEnd()))
        bufferHeaderValue();
    }
    while (this.cursor == null);
    HeaderElement localHeaderElement;
    do
    {
      if (this.cursor.atEnd())
      {
        if (!this.cursor.atEnd())
          break;
        this.cursor = null;
        this.buffer = null;
        break;
      }
      localHeaderElement = this.parser.parseHeaderElement(this.buffer, this.cursor);
    }
    while ((localHeaderElement.getName().length() == 0) && (localHeaderElement.getValue() == null));
    this.currentElement = localHeaderElement;
  }

  public boolean hasNext()
  {
    if (this.currentElement == null)
      parseNextElement();
    return this.currentElement != null;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextElement();
  }

  public HeaderElement nextElement()
    throws NoSuchElementException
  {
    if (this.currentElement == null)
      parseNextElement();
    if (this.currentElement == null)
      throw new NoSuchElementException("No more header elements available");
    HeaderElement localHeaderElement = this.currentElement;
    this.currentElement = null;
    return localHeaderElement;
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Remove not supported");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeaderElementIterator
 * JD-Core Version:    0.6.2
 */