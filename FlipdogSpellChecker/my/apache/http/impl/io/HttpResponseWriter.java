package my.apache.http.impl.io;

import java.io.IOException;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.message.LineFormatter;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class HttpResponseWriter extends AbstractMessageWriter<HttpResponse>
{
  public HttpResponseWriter(SessionOutputBuffer paramSessionOutputBuffer, LineFormatter paramLineFormatter, HttpParams paramHttpParams)
  {
    super(paramSessionOutputBuffer, paramLineFormatter, paramHttpParams);
  }

  protected void writeHeadLine(HttpResponse paramHttpResponse)
    throws IOException
  {
    this.lineFormatter.formatStatusLine(this.lineBuf, paramHttpResponse.getStatusLine());
    this.sessionBuffer.writeLine(this.lineBuf);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.io.HttpResponseWriter
 * JD-Core Version:    0.6.2
 */