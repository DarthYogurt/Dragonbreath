package my.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.params.HttpParams;

@Deprecated
public abstract interface SocketFactory
{
  public abstract Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException;

  public abstract Socket createSocket()
    throws IOException;

  public abstract boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.SocketFactory
 * JD-Core Version:    0.6.2
 */