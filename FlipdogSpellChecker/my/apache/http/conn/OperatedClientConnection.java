package my.apache.http.conn;

import java.io.IOException;
import java.net.Socket;
import my.apache.http.HttpClientConnection;
import my.apache.http.HttpHost;
import my.apache.http.HttpInetConnection;
import my.apache.http.params.HttpParams;

public abstract interface OperatedClientConnection extends HttpClientConnection, HttpInetConnection
{
  public abstract Socket getSocket();

  public abstract HttpHost getTargetHost();

  public abstract boolean isSecure();

  public abstract void openCompleted(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;

  public abstract void opening(Socket paramSocket, HttpHost paramHttpHost)
    throws IOException;

  public abstract void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.OperatedClientConnection
 * JD-Core Version:    0.6.2
 */