package my.apache.http.client;

import my.apache.http.HttpHost;
import my.apache.http.auth.AuthScheme;

public abstract interface AuthCache
{
  public abstract void clear();

  public abstract AuthScheme get(HttpHost paramHttpHost);

  public abstract void put(HttpHost paramHttpHost, AuthScheme paramAuthScheme);

  public abstract void remove(HttpHost paramHttpHost);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.AuthCache
 * JD-Core Version:    0.6.2
 */