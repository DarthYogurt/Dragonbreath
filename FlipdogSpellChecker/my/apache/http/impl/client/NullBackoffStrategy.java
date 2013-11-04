package my.apache.http.impl.client;

import my.apache.http.HttpResponse;
import my.apache.http.client.ConnectionBackoffStrategy;

public class NullBackoffStrategy
  implements ConnectionBackoffStrategy
{
  public boolean shouldBackoff(Throwable paramThrowable)
  {
    return false;
  }

  public boolean shouldBackoff(HttpResponse paramHttpResponse)
  {
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.NullBackoffStrategy
 * JD-Core Version:    0.6.2
 */