package my.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import my.apache.http.HttpHost;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.conn.DnsResolver;
import my.apache.http.conn.HttpHostConnectException;
import my.apache.http.conn.HttpInetSocketAddress;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.scheme.Scheme;
import my.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import my.apache.http.conn.scheme.SchemeRegistry;
import my.apache.http.conn.scheme.SchemeSocketFactory;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@ThreadSafe
public class DefaultClientConnectionOperator
  implements ClientConnectionOperator
{
  protected final DnsResolver dnsResolver;
  private final Log log = LogFactory.getLog(getClass());
  protected final SchemeRegistry schemeRegistry;

  public DefaultClientConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry amy not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.dnsResolver = new SystemDefaultDnsResolver();
  }

  public DefaultClientConnectionOperator(SchemeRegistry paramSchemeRegistry, DnsResolver paramDnsResolver)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    if (paramDnsResolver == null)
      throw new IllegalArgumentException("DNS resolver may not be null");
    this.schemeRegistry = paramSchemeRegistry;
    this.dnsResolver = paramDnsResolver;
  }

  public OperatedClientConnection createConnection()
  {
    return new DefaultClientConnection();
  }

  public void openConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, InetAddress paramInetAddress, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must not be open");
    Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    SchemeSocketFactory localSchemeSocketFactory = localScheme.getSchemeSocketFactory();
    InetAddress[] arrayOfInetAddress = resolveHostname(paramHttpHost.getHostName());
    int i = localScheme.resolvePort(paramHttpHost.getPort());
    int j = 0;
    while (true)
    {
      if (j >= arrayOfInetAddress.length)
        return;
      InetAddress localInetAddress = arrayOfInetAddress[j];
      int k;
      if (j == -1 + arrayOfInetAddress.length)
        k = 1;
      while (true)
      {
        Object localObject = localSchemeSocketFactory.createSocket(paramHttpParams);
        paramOperatedClientConnection.opening((Socket)localObject, paramHttpHost);
        HttpInetSocketAddress localHttpInetSocketAddress = new HttpInetSocketAddress(paramHttpHost, localInetAddress, i);
        InetSocketAddress localInetSocketAddress = null;
        if (paramInetAddress != null)
          localInetSocketAddress = new InetSocketAddress(paramInetAddress, 0);
        if (this.log.isDebugEnabled())
          this.log.debug("Connecting to " + localHttpInetSocketAddress);
        try
        {
          Socket localSocket = localSchemeSocketFactory.connectSocket((Socket)localObject, localHttpInetSocketAddress, localInetSocketAddress, paramHttpParams);
          if (localObject != localSocket)
          {
            localObject = localSocket;
            paramOperatedClientConnection.opening((Socket)localObject, paramHttpHost);
          }
          prepareSocket((Socket)localObject, paramHttpContext, paramHttpParams);
          paramOperatedClientConnection.openCompleted(localSchemeSocketFactory.isSecure((Socket)localObject), paramHttpParams);
          return;
        }
        catch (ConnectException localConnectException)
        {
          if (k != 0)
          {
            throw new HttpHostConnectException(paramHttpHost, localConnectException);
            k = 0;
          }
        }
        catch (ConnectTimeoutException localConnectTimeoutException)
        {
          if (k != 0)
            throw localConnectTimeoutException;
          if (this.log.isDebugEnabled())
            this.log.debug("Connect to " + localHttpInetSocketAddress + " timed out. " + "Connection will be retried using another IP address");
          j++;
        }
      }
    }
  }

  protected void prepareSocket(Socket paramSocket, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    paramSocket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(paramHttpParams));
    paramSocket.setSoTimeout(HttpConnectionParams.getSoTimeout(paramHttpParams));
    int i = HttpConnectionParams.getLinger(paramHttpParams);
    if (i >= 0)
      if (i <= 0)
        break label44;
    label44: for (boolean bool = true; ; bool = false)
    {
      paramSocket.setSoLinger(bool, i);
      return;
    }
  }

  protected InetAddress[] resolveHostname(String paramString)
    throws UnknownHostException
  {
    return this.dnsResolver.resolve(paramString);
  }

  public void updateSecureConnection(OperatedClientConnection paramOperatedClientConnection, HttpHost paramHttpHost, HttpContext paramHttpContext, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramOperatedClientConnection == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Target host may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    if (!paramOperatedClientConnection.isOpen())
      throw new IllegalStateException("Connection must be open");
    Scheme localScheme = this.schemeRegistry.getScheme(paramHttpHost.getSchemeName());
    if (!(localScheme.getSchemeSocketFactory() instanceof SchemeLayeredSocketFactory))
      throw new IllegalArgumentException("Target scheme (" + localScheme.getName() + ") must have layered socket factory.");
    SchemeLayeredSocketFactory localSchemeLayeredSocketFactory = (SchemeLayeredSocketFactory)localScheme.getSchemeSocketFactory();
    try
    {
      Socket localSocket = localSchemeLayeredSocketFactory.createLayeredSocket(paramOperatedClientConnection.getSocket(), paramHttpHost.getHostName(), paramHttpHost.getPort(), paramHttpParams);
      prepareSocket(localSocket, paramHttpContext, paramHttpParams);
      paramOperatedClientConnection.update(localSocket, paramHttpHost, localSchemeLayeredSocketFactory.isSecure(localSocket), paramHttpParams);
      return;
    }
    catch (ConnectException localConnectException)
    {
      throw new HttpHostConnectException(paramHttpHost, localConnectException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.DefaultClientConnectionOperator
 * JD-Core Version:    0.6.2
 */