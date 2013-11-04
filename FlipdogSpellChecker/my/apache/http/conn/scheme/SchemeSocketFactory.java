package my.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.params.HttpParams;

public abstract interface SchemeSocketFactory
{
  public abstract Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException;

  public abstract Socket createSocket(HttpParams paramHttpParams)
    throws IOException;

  public abstract boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.SchemeSocketFactory
 * JD-Core Version:    0.6.2
 */