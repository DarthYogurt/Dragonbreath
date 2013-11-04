package my.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpRequest;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.HttpRequestRetryHandler;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpRequestRetryHandler
  implements HttpRequestRetryHandler
{
  private final boolean requestSentRetryEnabled;
  private final int retryCount;

  public DefaultHttpRequestRetryHandler()
  {
    this(3, false);
  }

  public DefaultHttpRequestRetryHandler(int paramInt, boolean paramBoolean)
  {
    this.retryCount = paramInt;
    this.requestSentRetryEnabled = paramBoolean;
  }

  public int getRetryCount()
  {
    return this.retryCount;
  }

  protected boolean handleAsIdempotent(HttpRequest paramHttpRequest)
  {
    return !(paramHttpRequest instanceof HttpEntityEnclosingRequest);
  }

  public boolean isRequestSentRetryEnabled()
  {
    return this.requestSentRetryEnabled;
  }

  protected boolean requestIsAborted(HttpRequest paramHttpRequest)
  {
    HttpRequest localHttpRequest = paramHttpRequest;
    if ((paramHttpRequest instanceof RequestWrapper))
      localHttpRequest = ((RequestWrapper)paramHttpRequest).getOriginal();
    return ((localHttpRequest instanceof HttpUriRequest)) && (((HttpUriRequest)localHttpRequest).isAborted());
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    if (paramIOException == null)
      throw new IllegalArgumentException("Exception parameter may not be null");
    if (paramHttpContext == null)
      throw new IllegalArgumentException("HTTP context may not be null");
    if (paramInt > this.retryCount);
    while (true)
    {
      return false;
      if ((!(paramIOException instanceof InterruptedIOException)) && (!(paramIOException instanceof UnknownHostException)) && (!(paramIOException instanceof ConnectException)) && (!(paramIOException instanceof SSLException)))
      {
        HttpRequest localHttpRequest = (HttpRequest)paramHttpContext.getAttribute("http.request");
        if (!requestIsAborted(localHttpRequest))
        {
          if (handleAsIdempotent(localHttpRequest))
            return true;
          Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
          if ((localBoolean != null) && (localBoolean.booleanValue()));
          for (int i = 1; (i == 0) || (this.requestSentRetryEnabled); i = 0)
            return true;
        }
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultHttpRequestRetryHandler
 * JD-Core Version:    0.6.2
 */