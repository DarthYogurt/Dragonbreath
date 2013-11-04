package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public final class ImmutableHttpProcessor
  implements HttpProcessor
{
  private final HttpRequestInterceptor[] requestInterceptors;
  private final HttpResponseInterceptor[] responseInterceptors;

  public ImmutableHttpProcessor(HttpRequestInterceptorList paramHttpRequestInterceptorList, HttpResponseInterceptorList paramHttpResponseInterceptorList)
  {
    int m;
    label35: int i;
    if (paramHttpRequestInterceptorList != null)
    {
      int k = paramHttpRequestInterceptorList.getRequestInterceptorCount();
      this.requestInterceptors = new HttpRequestInterceptor[k];
      m = 0;
      if (m >= k)
      {
        if (paramHttpResponseInterceptorList == null)
          break label117;
        i = paramHttpResponseInterceptorList.getResponseInterceptorCount();
        this.responseInterceptors = new HttpResponseInterceptor[i];
      }
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        return;
        this.requestInterceptors[m] = paramHttpRequestInterceptorList.getRequestInterceptor(m);
        m++;
        break;
        this.requestInterceptors = new HttpRequestInterceptor[0];
        break label35;
      }
      this.responseInterceptors[j] = paramHttpResponseInterceptorList.getResponseInterceptor(j);
    }
    label117: this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor)
  {
    this(paramArrayOfHttpRequestInterceptor, null);
  }

  public ImmutableHttpProcessor(HttpRequestInterceptor[] paramArrayOfHttpRequestInterceptor, HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    int m;
    label31: int i;
    if (paramArrayOfHttpRequestInterceptor != null)
    {
      int k = paramArrayOfHttpRequestInterceptor.length;
      this.requestInterceptors = new HttpRequestInterceptor[k];
      m = 0;
      if (m >= k)
      {
        if (paramArrayOfHttpResponseInterceptor == null)
          break label101;
        i = paramArrayOfHttpResponseInterceptor.length;
        this.responseInterceptors = new HttpResponseInterceptor[i];
      }
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        return;
        this.requestInterceptors[m] = paramArrayOfHttpRequestInterceptor[m];
        m++;
        break;
        this.requestInterceptors = new HttpRequestInterceptor[0];
        break label31;
      }
      this.responseInterceptors[j] = paramArrayOfHttpResponseInterceptor[j];
    }
    label101: this.responseInterceptors = new HttpResponseInterceptor[0];
  }

  public ImmutableHttpProcessor(HttpResponseInterceptor[] paramArrayOfHttpResponseInterceptor)
  {
    this(null, paramArrayOfHttpResponseInterceptor);
  }

  public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.requestInterceptors.length)
        return;
      this.requestInterceptors[i].process(paramHttpRequest, paramHttpContext);
    }
  }

  public void process(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.responseInterceptors.length)
        return;
      this.responseInterceptors[i].process(paramHttpResponse, paramHttpContext);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.ImmutableHttpProcessor
 * JD-Core Version:    0.6.2
 */