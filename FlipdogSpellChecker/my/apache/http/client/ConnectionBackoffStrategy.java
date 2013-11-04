package my.apache.http.client;

import my.apache.http.HttpResponse;

public abstract interface ConnectionBackoffStrategy
{
  public abstract boolean shouldBackoff(Throwable paramThrowable);

  public abstract boolean shouldBackoff(HttpResponse paramHttpResponse);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.ConnectionBackoffStrategy
 * JD-Core Version:    0.6.2
 */