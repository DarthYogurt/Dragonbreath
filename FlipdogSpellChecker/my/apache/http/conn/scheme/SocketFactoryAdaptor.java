package my.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.params.BasicHttpParams;
import my.apache.http.params.HttpParams;

@Deprecated
class SocketFactoryAdaptor
  implements SocketFactory
{
  private final SchemeSocketFactory factory;

  SocketFactoryAdaptor(SchemeSocketFactory paramSchemeSocketFactory)
  {
    this.factory = paramSchemeSocketFactory;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    InetSocketAddress localInetSocketAddress1;
    if (paramInetAddress == null)
    {
      localInetSocketAddress1 = null;
      if (paramInt2 <= 0);
    }
    else
    {
      if (paramInt2 < 0)
        paramInt2 = 0;
      localInetSocketAddress1 = new InetSocketAddress(paramInetAddress, paramInt2);
    }
    InetSocketAddress localInetSocketAddress2 = new InetSocketAddress(InetAddress.getByName(paramString), paramInt1);
    return this.factory.connectSocket(paramSocket, localInetSocketAddress2, localInetSocketAddress1, paramHttpParams);
  }

  public Socket createSocket()
    throws IOException
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    return this.factory.createSocket(localBasicHttpParams);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof SocketFactoryAdaptor))
      return this.factory.equals(((SocketFactoryAdaptor)paramObject).factory);
    return this.factory.equals(paramObject);
  }

  public SchemeSocketFactory getFactory()
  {
    return this.factory;
  }

  public int hashCode()
  {
    return this.factory.hashCode();
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    return this.factory.isSecure(paramSocket);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.SocketFactoryAdaptor
 * JD-Core Version:    0.6.2
 */