package my.apache.http.impl.io;

import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.HttpMessageWriter;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.message.BasicLineFormatter;
import my.apache.http.message.LineFormatter;
import my.apache.http.params.HttpParams;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractMessageWriter<T extends HttpMessage>
  implements HttpMessageWriter<T>
{
  protected final CharArrayBuffer lineBuf;
  protected final LineFormatter lineFormatter;
  protected final SessionOutputBuffer sessionBuffer;

  public AbstractMessageWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    if (paramSessionOutputBuffer == null)
      throw new IllegalArgumentException("Session input buffer may not be null");
    this.sessionBuffer = paramSessionOutputBuffer;
    this.lineBuf = new CharArrayBuffer(128);
    if (paramLineFormatter != null);
    while (true)
    {
      this.lineFormatter = paramLineFormatter;
      return;
      paramLineFormatter = BasicLineFormatter.DEFAULT;
    }
  }

  public void write(T paramT)
    throws IOException, HttpException
  {
    if (paramT == null)
      throw new IllegalArgumentException("HTTP message may not be null");
    writeHeadLine(paramT);
    HeaderIterator localHeaderIterator = paramT.headerIterator();
    while (true)
    {
      if (!localHeaderIterator.hasNext())
      {
        this.lineBuf.clear();
        this.sessionBuffer.writeLine(this.lineBuf);
        return;
      }
      Header localHeader = localHeaderIterator.nextHeader();
      this.sessionBuffer.writeLine(this.lineFormatter.formatHeader(this.lineBuf, localHeader));
    }
  }

  protected abstract void writeHeadLine(T paramT)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.AbstractMessageWriter
 * JD-Core Version:    0.6.2
 */