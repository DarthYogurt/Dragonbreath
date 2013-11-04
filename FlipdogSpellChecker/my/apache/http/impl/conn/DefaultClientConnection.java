package my.apache.http.impl.conn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import my.apache.http.Header;
import my.apache.http.HttpException;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseFactory;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.impl.SocketHttpClientConnection;
import my.apache.http.io.HttpMessageParser;
import my.apache.http.io.SessionInputBuffer;
import my.apache.http.io.SessionOutputBuffer;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@NotThreadSafe
public class DefaultClientConnection extends SocketHttpClientConnection
  implements OperatedClientConnection, HttpContext
{
  private final Map<String, Object> attributes = new HashMap();
  private boolean connSecure;
  private final Log headerLog = LogFactory.getLog("my.apache.http.headers");
  private final Log log = LogFactory.getLog(getClass());
  private volatile boolean shutdown;
  private volatile Socket socket;
  private HttpHost targetHost;
  private final Log wireLog = LogFactory.getLog("my.apache.http.wire");

  public void close()
    throws IOException
  {
    try
    {
      super.close();
      if (this.log.isDebugEnabled())
        this.log.debug("Connection " + this + " closed");
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error closing connection", localIOException);
    }
  }

  protected HttpMessageParser<HttpResponse> createResponseParser(SessionInputBuffer paramSessionInputBuffer, HttpResponseFactory paramHttpResponseFactory, HttpParams paramHttpParams)
  {
    return new DefaultHttpResponseParser(paramSessionInputBuffer, null, paramHttpResponseFactory, paramHttpParams);
  }

  protected SessionInputBuffer createSessionInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramInt == -1)
      paramInt = 8192;
    Object localObject = super.createSessionInputBuffer(paramSocket, paramInt, paramHttpParams);
    if (this.wireLog.isDebugEnabled())
      localObject = new LoggingSessionInputBuffer((SessionInputBuffer)localObject, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return localObject;
  }

  protected SessionOutputBuffer createSessionOutputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramInt == -1)
      paramInt = 8192;
    Object localObject = super.createSessionOutputBuffer(paramSocket, paramInt, paramHttpParams);
    if (this.wireLog.isDebugEnabled())
      localObject = new LoggingSessionOutputBuffer((SessionOutputBuffer)localObject, new Wire(this.wireLog), HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    return localObject;
  }

  public Object getAttribute(String paramString)
  {
    return this.attributes.get(paramString);
  }

  public final Socket getSocket()
  {
    return this.socket;
  }

  public final HttpHost getTargetHost()
  {
    return this.targetHost;
  }

  public final boolean isSecure()
  {
    return this.connSecure;
  }

  public void openCompleted(boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertNotOpen();
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    this.connSecure = paramBoolean;
    bind(this.socket, paramHttpParams);
  }

  public void opening(Socket paramSocket, HttpHost paramHttpHost)
    throws IOException
  {
    assertNotOpen();
    this.socket = paramSocket;
    this.targetHost = paramHttpHost;
    if (this.shutdown)
    {
      paramSocket.close();
      throw new InterruptedIOException("Connection already shutdown");
    }
  }

  public HttpResponse receiveResponseHeader()
    throws HttpException, IOException
  {
    HttpResponse localHttpResponse = super.receiveResponseHeader();
    if (this.log.isDebugEnabled())
      this.log.debug("Receiving response: " + localHttpResponse.getStatusLine());
    Header[] arrayOfHeader;
    int i;
    if (this.headerLog.isDebugEnabled())
    {
      this.headerLog.debug("<< " + localHttpResponse.getStatusLine().toString());
      arrayOfHeader = localHttpResponse.getAllHeaders();
      i = arrayOfHeader.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localHttpResponse;
      Header localHeader = arrayOfHeader[j];
      this.headerLog.debug("<< " + localHeader.toString());
    }
  }

  public Object removeAttribute(String paramString)
  {
    return this.attributes.remove(paramString);
  }

  public void sendRequestHeader(HttpRequest paramHttpRequest)
    throws HttpException, IOException
  {
    if (this.log.isDebugEnabled())
      this.log.debug("Sending request: " + paramHttpRequest.getRequestLine());
    super.sendRequestHeader(paramHttpRequest);
    Header[] arrayOfHeader;
    int i;
    if (this.headerLog.isDebugEnabled())
    {
      this.headerLog.debug(">> " + paramHttpRequest.getRequestLine().toString());
      arrayOfHeader = paramHttpRequest.getAllHeaders();
      i = arrayOfHeader.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      Header localHeader = arrayOfHeader[j];
      this.headerLog.debug(">> " + localHeader.toString());
    }
  }

  public void setAttribute(String paramString, Object paramObject)
  {
    this.attributes.put(paramString, paramObject);
  }

  public void shutdown()
    throws IOException
  {
    this.shutdown = true;
    try
    {
      super.shutdown();
      if (this.log.isDebugEnabled())
        this.log.debug("Connection " + this + " shut down");
      Socket localSocket = this.socket;
      if (localSocket != null)
        localSocket.close();
      return;
    }
    catch (IOException localIOException)
    {
      this.log.debug("I/O error shutting down connection", localIOException);
    }
  }

  public void update(Socket paramSocket, HttpHost paramHttpHost, boolean paramBoolean, HttpParams paramHttpParams)
    throws IOException
  {
    assertOpen();
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host must not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters must not be null.");
    if (paramSocket != null)
    {
      this.socket = paramSocket;
      bind(paramSocket, paramHttpParams);
    }
    this.targetHost = paramHttpHost;
    this.connSecure = paramBoolean;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.DefaultClientConnection
 * JD-Core Version:    0.6.2
 */