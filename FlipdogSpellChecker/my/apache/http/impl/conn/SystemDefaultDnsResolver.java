package my.apache.http.impl.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import my.apache.http.conn.DnsResolver;

public class SystemDefaultDnsResolver
  implements DnsResolver
{
  public InetAddress[] resolve(String paramString)
    throws UnknownHostException
  {
    return InetAddress.getAllByName(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.SystemDefaultDnsResolver
 * JD-Core Version:    0.6.2
 */