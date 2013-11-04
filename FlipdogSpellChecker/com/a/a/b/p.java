package com.a.a.b;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.protocol.HttpContext;

class p
  implements HttpRequestInterceptor
{
  p(k paramk)
  {
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    if (!paramHttpRequest.containsHeader("Accept-Encoding"))
      paramHttpRequest.addHeader("Accept-Encoding", "gzip");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.p
 * JD-Core Version:    0.6.2
 */