package my.apache.http;

import java.net.InetAddress;

public abstract interface HttpInetConnection extends HttpConnection
{
  public abstract InetAddress getLocalAddress();

  public abstract int getLocalPort();

  public abstract InetAddress getRemoteAddress();

  public abstract int getRemotePort();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpInetConnection
 * JD-Core Version:    0.6.2
 */