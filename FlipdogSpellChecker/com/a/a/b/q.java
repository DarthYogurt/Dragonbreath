package com.a.a.b;

import java.io.IOException;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpEntity;
import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.protocol.HttpContext;

class q
  implements HttpResponseInterceptor
{
  q(k paramk)
  {
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    HeaderElement[] arrayOfHeaderElement;
    int i;
    if (localHttpEntity != null)
    {
      Header localHeader = localHttpEntity.getContentEncoding();
      if (localHeader != null)
      {
        arrayOfHeaderElement = localHeader.getElements();
        i = arrayOfHeaderElement.length;
      }
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      if (arrayOfHeaderElement[j].getName().equalsIgnoreCase("gzip"))
      {
        paramHttpResponse.setEntity(new a(paramHttpResponse.getEntity()));
        return;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.q
 * JD-Core Version:    0.6.2
 */