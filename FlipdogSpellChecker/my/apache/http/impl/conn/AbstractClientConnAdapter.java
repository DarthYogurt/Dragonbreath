package my.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import my.apache.http.HttpConnectionMetrics;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.conn.ManagedClientConnection;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.protocol.HttpContext;

@Deprecated
@NotThreadSafe
public abstract class AbstractClientConnAdapter
  implements ManagedClientConnection, HttpContext
{
  private final ClientConnectionManager connManager;
  private volatile long duration;
  private volatile boolean markedReusable;
  private volatile boolean released;
  private volatile OperatedClientConnection wrappedConnection;

  protected AbstractClientConnAdapter(ClientConnectionManager paramClientConnectionManager, OperatedClientConnection paramOperatedClientConnection)
  {
    this.connManager = paramClientConnectionManager;
    this.wrappedConnection = paramOperatedClientConnection;
    this.markedReusable = false;
    this.released = false;
    this.duration = 9223372036854775807L;
  }

  // ERROR //
  public void abortConnection()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 32	my/apache/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield 32	my/apache/http/impl/conn/AbstractClientConnAdapter:released	Z
    //   19: aload_0
    //   20: invokevirtual 42	my/apache/http/impl/conn/AbstractClientConnAdapter:unmarkReusable	()V
    //   23: aload_0
    //   24: invokevirtual 45	my/apache/http/impl/conn/AbstractClientConnAdapter:shutdown	()V
    //   27: aload_0
    //   28: getfield 26	my/apache/http/impl/conn/AbstractClientConnAdapter:connManager	Lmy/apache/http/conn/ClientConnectionManager;
    //   31: aload_0
    //   32: aload_0
    //   33: getfield 36	my/apache/http/impl/conn/AbstractClientConnAdapter:duration	J
    //   36: getstatic 51	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   39: invokeinterface 57 5 0
    //   44: goto -33 -> 11
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    //   52: astore_3
    //   53: goto -26 -> 27
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	47	finally
    //   14	23	47	finally
    //   23	27	47	finally
    //   27	44	47	finally
    //   23	27	52	java/io/IOException
  }

  protected final void assertNotAborted()
    throws InterruptedIOException
  {
    if (isReleased())
      throw new InterruptedIOException("Connection has been shut down");
  }

  protected final void assertValid(OperatedClientConnection paramOperatedClientConnection)
    throws ConnectionShutdownException
  {
    if ((isReleased()) || (paramOperatedClientConnection == null))
      throw new ConnectionShutdownException();
  }

  protected void detach()
  {
    try
    {
      this.wrappedConnection = null;
      this.duration = 9223372036854775807L;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void flush()
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.flush();
  }

  public Object getAttribute(String paramString)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if ((localOperatedClientConnection instanceof HttpContext))
      return ((HttpContext)localOperatedClientConnection).getAttribute(paramString);
    return null;
  }

  public InetAddress getLocalAddress()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getLocalAddress();
  }

  public int getLocalPort()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getLocalPort();
  }

  protected ClientConnectionManager getManager()
  {
    return this.connManager;
  }

  public HttpConnectionMetrics getMetrics()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getMetrics();
  }

  public InetAddress getRemoteAddress()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemoteAddress();
  }

  public int getRemotePort()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getRemotePort();
  }

  public SSLSession getSSLSession()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if (!isOpen());
    Socket localSocket;
    do
    {
      return null;
      localSocket = localOperatedClientConnection.getSocket();
    }
    while (!(localSocket instanceof SSLSocket));
    return ((SSLSocket)localSocket).getSession();
  }

  public int getSocketTimeout()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.getSocketTimeout();
  }

  protected OperatedClientConnection getWrappedConnection()
  {
    return this.wrappedConnection;
  }

  public boolean isMarkedReusable()
  {
    return this.markedReusable;
  }

  public boolean isOpen()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    if (localOperatedClientConnection == null)
      return false;
    return localOperatedClientConnection.isOpen();
  }

  protected boolean isReleased()
  {
    return this.released;
  }

  public boolean isResponseAvailable(int paramInt)
    throws IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.isResponseAvailable(paramInt);
  }

  public boolean isSecure()
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    return localOperatedClientConnection.isSecure();
  }

  public boolean isStale()
  {
    if (isReleased());
    OperatedClientConnection localOperatedClientConnection;
    do
    {
      return true;
      localOperatedClientConnection = getWrappedConnection();
    }
    while (localOperatedClientConnection == null);
    return localOperatedClientConnection.isStale();
  }

  public void markReusable()
  {
    this.markedReusable = true;
  }

  public void receiveResponseEntity(HttpResponse paramHttpResponse)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.receiveResponseEntity(paramHttpResponse);
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    return localOperatedClientConnection.receiveResponseHeader();
  }

  public void releaseConnection()
  {
    try
    {
      boolean bool = this.released;
      if (bool);
      while (true)
      {
        return;
        this.released = true;
        this.connManager.releaseConnection(this, this.duration, TimeUnit.MILLISECONDS);
      }
    }
    finally
    {
    }
  }

  public Object removeAttribute(String paramString)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if ((localOperatedClientConnection instanceof HttpContext))
      return ((HttpContext)localOperatedClientConnection).removeAttribute(paramString);
    return null;
  }

  public void sendRequestEntity(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestEntity(paramHttpEntityEnclosingRequest);
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    unmarkReusable();
    localOperatedClientConnection.sendRequestHeader(paramHttpRequest);
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    if ((localOperatedClientConnection instanceof HttpContext))
      ((HttpContext)localOperatedClientConnection).setAttribute(paramString, paramObject);
  }

  public void setIdleDuration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong > 0L)
    {
      this.duration = paramTimeUnit.toMillis(paramLong);
      return;
    }
    this.duration = -1L;
  }

  public void setSocketTimeout(int paramInt)
  {
    OperatedClientConnection localOperatedClientConnection = getWrappedConnection();
    assertValid(localOperatedClientConnection);
    localOperatedClientConnection.setSocketTimeout(paramInt);
  }

  public void unmarkReusable()
  {
    this.markedReusable = false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.AbstractClientConnAdapter
 * JD-Core Version:    0.6.2
 */