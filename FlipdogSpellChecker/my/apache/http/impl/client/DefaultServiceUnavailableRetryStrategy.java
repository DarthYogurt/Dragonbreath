package my.apache.http.impl.client;

import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.ServiceUnavailableRetryStrategy;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultServiceUnavailableRetryStrategy
  implements ServiceUnavailableRetryStrategy
{
  private final int maxRetries;
  private final long retryInterval;

  public DefaultServiceUnavailableRetryStrategy()
  {
    this(1, 1000);
  }

  public DefaultServiceUnavailableRetryStrategy(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 1)
      throw new IllegalArgumentException("MaxRetries must be greater than 1");
    if (paramInt2 < 1)
      throw new IllegalArgumentException("Retry interval must be greater than 1");
    this.maxRetries = paramInt1;
    this.retryInterval = paramInt2;
  }

  public long getRetryInterval()
  {
    return this.retryInterval;
  }

  public boolean retryRequest(HttpResponse paramHttpResponse, int paramInt, HttpContext paramHttpContext)
  {
    return (paramInt <= this.maxRetries) && (paramHttpResponse.getStatusLine().getStatusCode() == 503);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy
 * JD-Core Version:    0.6.2
 */