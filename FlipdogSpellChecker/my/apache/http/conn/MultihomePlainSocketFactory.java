package my.apache.http.conn;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import my.apache.http.annotation.Immutable;
import my.apache.http.conn.scheme.SocketFactory;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;

@Deprecated
@Immutable
public final class MultihomePlainSocketFactory
  implements SocketFactory
{
  private static final MultihomePlainSocketFactory DEFAULT_FACTORY = new MultihomePlainSocketFactory();

  public static MultihomePlainSocketFactory getSocketFactory()
  {
    return DEFAULT_FACTORY;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null.");
    if (paramSocket == null)
      paramSocket = createSocket();
    if ((paramInetAddress != null) || (paramInt2 > 0))
    {
      if (paramInt2 < 0)
        paramInt2 = 0;
      paramSocket.bind(new InetSocketAddress(paramInetAddress, paramInt2));
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(paramString);
    ArrayList localArrayList = new ArrayList(arrayOfInetAddress.length);
    localArrayList.addAll(Arrays.asList(arrayOfInetAddress));
    Collections.shuffle(localArrayList);
    Object localObject = null;
    Iterator localIterator = localArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext());
      while (true)
      {
        if (localObject == null)
          break label224;
        throw localObject;
        InetAddress localInetAddress = (InetAddress)localIterator.next();
        try
        {
          paramSocket.connect(new InetSocketAddress(localInetAddress, paramInt1), i);
        }
        catch (SocketTimeoutException localSocketTimeoutException)
        {
          throw new ConnectTimeoutException("Connect to " + localInetAddress + " timed out");
        }
        catch (IOException localIOException)
        {
          paramSocket = new Socket();
          localObject = localIOException;
        }
      }
    }
    label224: return paramSocket;
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.getClass() != Socket.class)
      throw new IllegalArgumentException("Socket not created by this factory.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.MultihomePlainSocketFactory
 * JD-Core Version:    0.6.2
 */