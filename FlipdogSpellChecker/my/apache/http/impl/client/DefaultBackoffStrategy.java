package my.apache.http.impl.client;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.ConnectionBackoffStrategy;

public class DefaultBackoffStrategy
  implements ConnectionBackoffStrategy
{
  public boolean shouldBackoff(Throwable paramThrowable)
  {
    return ((paramThrowable instanceof SocketTimeoutException)) || ((paramThrowable instanceof ConnectException));
  }

  public boolean shouldBackoff(HttpResponse paramHttpResponse)
  {
    return paramHttpResponse.getStatusLine().getStatusCode() == 503;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultBackoffStrategy
 * JD-Core Version:    0.6.2
 */