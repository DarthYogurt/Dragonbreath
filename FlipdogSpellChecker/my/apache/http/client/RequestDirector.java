package my.apache.http.client;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.protocol.HttpContext;

public abstract interface RequestDirector
{
  public abstract HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.RequestDirector
 * JD-Core Version:    0.6.2
 */