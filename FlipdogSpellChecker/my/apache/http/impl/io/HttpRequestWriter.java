package my.apache.http.impl.io;

import java.io.IOException;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.message.LineFormatter;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class HttpRequestWriter extends AbstractMessageWriter<HttpRequest>
{
  public HttpRequestWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }

  protected void writeHeadLine(HttpRequest paramHttpRequest)
    throws IOException
  {
    this.lineFormatter.formatRequestLine(this.lineBuf, paramHttpRequest.getRequestLine());
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.HttpRequestWriter
 * JD-Core Version:    0.6.2
 */