package my.apache.http;

import java.io.IOException;

public abstract interface HttpServerConnection extends HttpConnection
{
  public abstract void flush()
    throws IOException;

  public abstract void receiveRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException;

  public abstract HttpRequest receiveRequestHeader()
    throws HttpException, IOException;

  public abstract void sendResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException;

  public abstract void sendResponseHeader(HttpResponse paramHttpResponse)
    throws HttpException, IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpServerConnection
 * JD-Core Version:    0.6.2
 */