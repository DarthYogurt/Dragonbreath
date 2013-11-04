package my.apache.http.client;

import my.apache.http.conn.routing.HttpRoute;

public abstract interface BackoffManager
{
  public abstract void backOff(HttpRoute paramHttpRoute);

  public abstract void probe(HttpRoute paramHttpRoute);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.BackoffManager
 * JD-Core Version:    0.6.2
 */