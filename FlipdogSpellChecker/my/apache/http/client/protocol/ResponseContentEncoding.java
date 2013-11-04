package my.apache.http.client.protocol;

import java.io.IOException;
import java.util.Locale;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpEntity;
import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.entity.DeflateDecompressingEntity;
import my.apache.http.client.entity.GzipDecompressingEntity;
import my.apache.http.protocol.HttpContext;

@Immutable
public class ResponseContentEncoding
  implements HttpResponseInterceptor
{
  public static final String UNCOMPRESSED = "http.client.response.uncompressed";

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    HeaderElement localHeaderElement;
    String str;
    if ((localHttpEntity != null) && (localHttpEntity.getContentLength() != 0L))
    {
      Header localHeader = localHttpEntity.getContentEncoding();
      if (localHeader != null)
      {
        HeaderElement[] arrayOfHeaderElement = localHeader.getElements();
        if (arrayOfHeaderElement.length != 0)
        {
          localHeaderElement = arrayOfHeaderElement[0];
          str = localHeaderElement.getName().toLowerCase(Locale.US);
          if ((!"gzip".equals(str)) && (!"x-gzip".equals(str)))
            break label127;
          paramHttpResponse.setEntity(new GzipDecompressingEntity(paramHttpResponse.getEntity()));
          if (paramHttpContext != null)
            paramHttpContext.setAttribute("http.client.response.uncompressed", Boolean.valueOf(true));
        }
      }
    }
    label127: 
    do
    {
      do
      {
        return;
        if (!"deflate".equals(str))
          break;
        paramHttpResponse.setEntity(new DeflateDecompressingEntity(paramHttpResponse.getEntity()));
      }
      while (paramHttpContext == null);
      paramHttpContext.setAttribute("http.client.response.uncompressed", Boolean.valueOf(true));
      return;
    }
    while ("identity".equals(str));
    throw new HttpException("Unsupported Content-Coding: " + localHeaderElement.getName());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.protocol.ResponseContentEncoding
 * JD-Core Version:    0.6.2
 */