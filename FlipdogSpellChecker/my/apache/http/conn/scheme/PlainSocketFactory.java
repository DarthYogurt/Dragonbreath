package my.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;

@Immutable
public class PlainSocketFactory
  implements SocketFactory, SchemeSocketFactory
{
  private final HostNameResolver nameResolver;

  public PlainSocketFactory()
  {
    this.nameResolver = null;
  }

  @Deprecated
  public PlainSocketFactory(HostNameResolver paramHostNameResolver)
  {
    this.nameResolver = paramHostNameResolver;
  }

  public static PlainSocketFactory getSocketFactory()
  {
    return new PlainSocketFactory();
  }

  @Deprecated
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    InetSocketAddress localInetSocketAddress;
    if (paramInetAddress == null)
    {
      localInetSocketAddress = null;
      if (paramInt2 <= 0);
    }
    else
    {
      if (paramInt2 < 0)
        paramInt2 = 0;
      localInetSocketAddress = new InetSocketAddress(paramInetAddress, paramInt2);
    }
    if (this.nameResolver != null);
    for (InetAddress localInetAddress = this.nameResolver.resolve(paramString); ; localInetAddress = InetAddress.getByName(paramString))
      return connectSocket(paramSocket, new InetSocketAddress(localInetAddress, paramInt1), localInetSocketAddress, paramHttpParams);
  }

  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, ConnectTimeoutException
  {
    if (paramInetSocketAddress1 == null)
      throw new IllegalArgumentException("Remote address may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    Socket localSocket = paramSocket;
    if (localSocket == null)
      localSocket = createSocket();
    if (paramInetSocketAddress2 != null)
    {
      localSocket.setReuseAddress(HttpConnectionParams.getSoReuseaddr(paramHttpParams));
      localSocket.bind(paramInetSocketAddress2);
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    try
    {
      localSocket.setSoTimeout(j);
      localSocket.connect(paramInetSocketAddress1, i);
      return localSocket;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
    }
    throw new ConnectTimeoutException("Connect to " + paramInetSocketAddress1 + " timed out");
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public Socket createSocket(HttpParams paramHttpParams)
  {
    return new Socket();
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.PlainSocketFactory
 * JD-Core Version:    0.6.2
 */