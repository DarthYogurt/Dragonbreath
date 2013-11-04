package my.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.client.HttpClient;
import my.apache.http.client.ResponseHandler;
import my.apache.http.client.ServiceUnavailableRetryStrategy;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;
import my.apache.http.util.EntityUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ThreadSafe
public class AutoRetryHttpClient
  implements HttpClient
{
  private final HttpClient backend;
  private final Log log = LogFactory.getLog(getClass());
  private final ServiceUnavailableRetryStrategy retryStrategy;

  public AutoRetryHttpClient()
  {
    this(new DefaultHttpClient(), new DefaultServiceUnavailableRetryStrategy());
  }

  public AutoRetryHttpClient(HttpClient paramHttpClient)
  {
    this(paramHttpClient, new DefaultServiceUnavailableRetryStrategy());
  }

  public AutoRetryHttpClient(HttpClient paramHttpClient, ServiceUnavailableRetryStrategy paramServiceUnavailableRetryStrategy)
  {
    if (paramHttpClient == null)
      throw new IllegalArgumentException("HttpClient may not be null");
    if (paramServiceUnavailableRetryStrategy == null)
      throw new IllegalArgumentException("ServiceUnavailableRetryStrategy may not be null");
    this.backend = paramHttpClient;
    this.retryStrategy = paramServiceUnavailableRetryStrategy;
  }

  public AutoRetryHttpClient(ServiceUnavailableRetryStrategy paramServiceUnavailableRetryStrategy)
  {
    this(new DefaultHttpClient(), paramServiceUnavailableRetryStrategy);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException
  {
    return paramResponseHandler.handleResponse(execute(paramHttpHost, paramHttpRequest, paramHttpContext));
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException
  {
    return execute(paramHttpUriRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException
  {
    return paramResponseHandler.handleResponse(execute(paramHttpUriRequest, paramHttpContext));
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException
  {
    return execute(paramHttpHost, paramHttpRequest, null);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException
  {
    int i = 1;
    HttpResponse localHttpResponse;
    while (true)
    {
      localHttpResponse = this.backend.execute(paramHttpHost, paramHttpRequest, paramHttpContext);
      try
      {
        if (this.retryStrategy.retryRequest(localHttpResponse, i, paramHttpContext))
        {
          EntityUtils.consume(localHttpResponse.getEntity());
          long l = this.retryStrategy.getRetryInterval();
          try
          {
            this.log.trace("Wait for " + l);
            Thread.sleep(l);
            i++;
          }
          catch (InterruptedException localInterruptedException)
          {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
          }
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        try
        {
          EntityUtils.consume(localHttpResponse.getEntity());
          throw localRuntimeException;
        }
        catch (IOException localIOException)
        {
          while (true)
            this.log.warn("I/O error consuming response content", localIOException);
        }
      }
    }
    return localHttpResponse;
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return execute(paramHttpUriRequest, null);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    URI localURI = paramHttpUriRequest.getURI();
    return execute(new HttpHost(localURI.getHost(), localURI.getPort(), localURI.getScheme()), paramHttpUriRequest, paramHttpContext);
  }

  public ClientConnectionManager getConnectionManager()
  {
    return this.backend.getConnectionManager();
  }

  public HttpParams getParams()
  {
    return this.backend.getParams();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.AutoRetryHttpClient
 * JD-Core Version:    0.6.2
 */