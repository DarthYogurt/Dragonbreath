package my.apache.http.protocol;

import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;

public abstract interface HttpExpectationVerifier
{
  public abstract void verify(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpExpectationVerifier
 * JD-Core Version:    0.6.2
 */