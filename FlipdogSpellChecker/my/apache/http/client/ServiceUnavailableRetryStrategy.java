package my.apache.http.client;

import my.apache.http.HttpResponse;
import my.apache.http.protocol.HttpContext;

public abstract interface ServiceUnavailableRetryStrategy
{
  public abstract long getRetryInterval();

  public abstract boolean retryRequest(HttpResponse paramHttpResponse, int paramInt, HttpContext paramHttpContext);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.ServiceUnavailableRetryStrategy
 * JD-Core Version:    0.6.2
 */