package my.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import my.apache.http.HttpInetConnection;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.impl.io.SocketInputBuffer;
import my.apache.http.impl.io.SocketOutputBuffer;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class SocketHttpClientConnection extends AbstractHttpClientConnection
  implements HttpInetConnection
{
  private volatile boolean open;
  private volatile Socket socket = null;

  private static void formatAddress(StringBuilder paramStringBuilder, SocketAddress paramSocketAddress)
  {
    if ((paramSocketAddress instanceof InetSocketAddress))
    {
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramSocketAddress;
      if (localInetSocketAddress.getAddress() != null);
      for (Object localObject = localInetSocketAddress.getAddress().getHostAddress(); ; localObject = localInetSocketAddress.getAddress())
      {
        paramStringBuilder.append(localObject).append(':').append(localInetSocketAddress.getPort());
        return;
      }
    }
    paramStringBuilder.append(paramSocketAddress);
  }

  protected void assertNotOpen()
  {
    if (this.open)
      throw new IllegalStateException("Connection is already open");
  }

  protected void assertOpen()
  {
    if (!this.open)
      throw new IllegalStateException("Connection is not open");
  }

  protected void bind(Socket paramSocket, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.socket = paramSocket;
    int i = HttpConnectionParams.getSocketBufferSize(paramHttpParams);
    init(createSessionInputBuffer(paramSocket, i, paramHttpParams), createSessionOutputBuffer(paramSocket, i, paramHttpParams), paramHttpParams);
    this.open = true;
  }

  // ERROR //
  public void close()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 50	my/apache/http/impl/SocketHttpClientConnection:open	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: iconst_0
    //   10: putfield 50	my/apache/http/impl/SocketHttpClientConnection:open	Z
    //   13: aload_0
    //   14: getfield 17	my/apache/http/impl/SocketHttpClientConnection:socket	Ljava/net/Socket;
    //   17: astore_1
    //   18: aload_0
    //   19: invokevirtual 95	my/apache/http/impl/SocketHttpClientConnection:doFlush	()V
    //   22: aload_1
    //   23: invokevirtual 100	java/net/Socket:shutdownOutput	()V
    //   26: aload_1
    //   27: invokevirtual 103	java/net/Socket:shutdownInput	()V
    //   30: aload_1
    //   31: invokevirtual 105	java/net/Socket:close	()V
    //   34: return
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 105	java/net/Socket:close	()V
    //   40: aload_2
    //   41: athrow
    //   42: astore 4
    //   44: goto -18 -> 26
    //   47: astore 5
    //   49: goto -19 -> 30
    //   52: astore_3
    //   53: goto -23 -> 30
    //
    // Exception table:
    //   from	to	target	type
    //   18	22	35	finally
    //   22	26	35	finally
    //   26	30	35	finally
    //   22	26	42	java/io/IOException
    //   26	30	47	java/io/IOException
    //   22	26	52	java/lang/UnsupportedOperationException
    //   26	30	52	java/lang/UnsupportedOperationException
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketInputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    return new SocketOutputBuffer(paramSocket, paramInt, paramHttpParams);
  }

  public InetAddress getLocalAddress()
  {
    if (this.socket != null)
      return this.socket.getLocalAddress();
    return null;
  }

  public int getLocalPort()
  {
    if (this.socket != null)
      return this.socket.getLocalPort();
    return -1;
  }

  public InetAddress getRemoteAddress()
  {
    if (this.socket != null)
      return this.socket.getInetAddress();
    return null;
  }

  public int getRemotePort()
  {
    if (this.socket != null)
      return this.socket.getPort();
    return -1;
  }

  protected Socket getSocket()
  {
    return this.socket;
  }

  public int getSocketTimeout()
  {
    int i = -1;
    if (this.socket != null);
    try
    {
      int j = this.socket.getSoTimeout();
      i = j;
      return i;
    }
    catch (SocketException localSocketException)
    {
    }
    return i;
  }

  public boolean isOpen()
  {
    return this.open;
  }

  public void setSocketTimeout(int paramInt)
  {
    assertOpen();
    if (this.socket != null);
    try
    {
      this.socket.setSoTimeout(paramInt);
      return;
    }
    catch (SocketException localSocketException)
    {
    }
  }

  public void shutdown()
    throws IOException
  {
    this.open = false;
    Socket localSocket = this.socket;
    if (localSocket != null)
      localSocket.close();
  }

  public String toString()
  {
    if (this.socket != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      SocketAddress localSocketAddress1 = this.socket.getRemoteSocketAddress();
      SocketAddress localSocketAddress2 = this.socket.getLocalSocketAddress();
      if ((localSocketAddress1 != null) && (localSocketAddress2 != null))
      {
        formatAddress(localStringBuilder, localSocketAddress2);
        localStringBuilder.append("<->");
        formatAddress(localStringBuilder, localSocketAddress1);
      }
      return localStringBuilder.toString();
    }
    return super.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.SocketHttpClientConnection
 * JD-Core Version:    0.6.2
 */