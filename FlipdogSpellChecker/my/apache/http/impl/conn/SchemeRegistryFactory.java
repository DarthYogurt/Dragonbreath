package my.apache.http.impl.conn;

import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.scheme.PlainSocketFactory;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.conn.ssl.SSLSocketFactory;

@ThreadSafe
public final class SchemeRegistryFactory
{
  public static SchemeRegistry createDefault()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    localSchemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
    return localSchemeRegistry;
  }

  public static SchemeRegistry createSystemDefault()
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    localSchemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSystemSocketFactory()));
    return localSchemeRegistry;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.SchemeRegistryFactory
 * JD-Core Version:    0.6.2
 */