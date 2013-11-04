package my.apache.http.conn;

import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.params.HttpParams;

public abstract interface ClientConnectionManagerFactory
{
  public abstract ClientConnectionManager newInstance(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ClientConnectionManagerFactory
 * JD-Core Version:    0.6.2
 */