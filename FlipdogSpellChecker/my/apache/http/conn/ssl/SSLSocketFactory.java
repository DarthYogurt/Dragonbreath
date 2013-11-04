package my.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import my.apache.http.HttpHost;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.conn.ConnectTimeoutException;
import my.apache.http.conn.HttpInetSocketAddress;
import my.apache.http.conn.scheme.HostNameResolver;
import my.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import my.apache.http.conn.scheme.LayeredSocketFactory;
import my.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import my.apache.http.params.HttpParams;

@ThreadSafe
public class SSLSocketFactory
  implements SchemeLayeredSocketFactory, LayeredSchemeSocketFactory, LayeredSocketFactory
{
  public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
  public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
  public static final String SSL = "SSL";
  public static final String SSLV2 = "SSLv2";
  public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
  public static final String TLS = "TLS";
  private volatile X509HostnameVerifier hostnameVerifier;
  private final HostNameResolver nameResolver;
  private final javax.net.ssl.SSLSocketFactory socketfactory;

  @Deprecated
  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, HostNameResolver paramHostNameResolver)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramHostNameResolver);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, paramTrustStrategy), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(KeyStore paramKeyStore)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, paramKeyStore, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore, String paramString)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore, paramString, null, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore1, String paramString, KeyStore paramKeyStore2)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore1, paramString, paramKeyStore2, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(SSLContext paramSSLContext)
  {
    this(paramSSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  @Deprecated
  public SSLSocketFactory(SSLContext paramSSLContext, HostNameResolver paramHostNameResolver)
  {
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    this.nameResolver = paramHostNameResolver;
  }

  public SSLSocketFactory(SSLContext paramSSLContext, X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramSSLContext == null)
      throw new IllegalArgumentException("SSL context may not be null");
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public SSLSocketFactory(javax.net.ssl.SSLSocketFactory paramSSLSocketFactory, X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramSSLSocketFactory == null)
      throw new IllegalArgumentException("SSL socket factory may not be null");
    this.socketfactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, paramX509HostnameVerifier);
  }

  private static SSLContext createSSLContext(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException
  {
    if (paramString1 == null)
      paramString1 = "TLS";
    KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    char[] arrayOfChar;
    KeyManager[] arrayOfKeyManager;
    TrustManager[] arrayOfTrustManager;
    if (paramString2 != null)
    {
      arrayOfChar = paramString2.toCharArray();
      localKeyManagerFactory.init(paramKeyStore1, arrayOfChar);
      arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
      TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      localTrustManagerFactory.init(paramKeyStore2);
      arrayOfTrustManager = localTrustManagerFactory.getTrustManagers();
      if ((arrayOfTrustManager == null) || (paramTrustStrategy == null));
    }
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfTrustManager.length)
      {
        SSLContext localSSLContext = SSLContext.getInstance(paramString1);
        localSSLContext.init(arrayOfKeyManager, arrayOfTrustManager, paramSecureRandom);
        return localSSLContext;
        arrayOfChar = null;
        break;
      }
      TrustManager localTrustManager = arrayOfTrustManager[i];
      if ((localTrustManager instanceof X509TrustManager))
        arrayOfTrustManager[i] = new TrustManagerDecorator((X509TrustManager)localTrustManager, paramTrustStrategy);
    }
  }

  public static SSLSocketFactory getSocketFactory()
    throws SSLInitializationException
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, null, null);
      SSLSocketFactory localSSLSocketFactory = new SSLSocketFactory(localSSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
      return localSSLSocketFactory;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SSLInitializationException(localNoSuchAlgorithmException.getMessage(), localNoSuchAlgorithmException);
    }
    catch (KeyManagementException localKeyManagementException)
    {
      throw new SSLInitializationException(localKeyManagementException.getMessage(), localKeyManagementException);
    }
  }

  public static SSLSocketFactory getSystemSocketFactory()
    throws SSLInitializationException
  {
    return new SSLSocketFactory((javax.net.ssl.SSLSocketFactory)javax.net.ssl.SSLSocketFactory.getDefault(), BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
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
      return connectSocket(paramSocket, new HttpInetSocketAddress(new HttpHost(paramString, paramInt1), localInetAddress, paramInt1), localInetSocketAddress, paramHttpParams);
  }

  // ERROR //
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +13 -> 14
    //   4: new 90	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 211
    //   10: invokespecial 95	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload 4
    //   16: ifnonnull +13 -> 29
    //   19: new 90	java/lang/IllegalArgumentException
    //   22: dup
    //   23: ldc 213
    //   25: invokespecial 95	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   28: athrow
    //   29: aload_1
    //   30: ifnull +112 -> 142
    //   33: aload_1
    //   34: astore 5
    //   36: aload_3
    //   37: ifnull +19 -> 56
    //   40: aload 5
    //   42: aload 4
    //   44: invokestatic 219	my/apache/http/params/HttpConnectionParams:getSoReuseaddr	(Lmy/apache/http/params/HttpParams;)Z
    //   47: invokevirtual 225	java/net/Socket:setReuseAddress	(Z)V
    //   50: aload 5
    //   52: aload_3
    //   53: invokevirtual 229	java/net/Socket:bind	(Ljava/net/SocketAddress;)V
    //   56: aload 4
    //   58: invokestatic 233	my/apache/http/params/HttpConnectionParams:getConnectionTimeout	(Lmy/apache/http/params/HttpParams;)I
    //   61: istore 6
    //   63: aload 4
    //   65: invokestatic 236	my/apache/http/params/HttpConnectionParams:getSoTimeout	(Lmy/apache/http/params/HttpParams;)I
    //   68: istore 7
    //   70: aload 5
    //   72: iload 7
    //   74: invokevirtual 240	java/net/Socket:setSoTimeout	(I)V
    //   77: aload 5
    //   79: aload_2
    //   80: iload 6
    //   82: invokevirtual 244	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   85: aload_2
    //   86: instanceof 189
    //   89: ifeq +98 -> 187
    //   92: aload_2
    //   93: checkcast 189	my/apache/http/conn/HttpInetSocketAddress
    //   96: invokevirtual 248	my/apache/http/conn/HttpInetSocketAddress:getHttpHost	()Lmy/apache/http/HttpHost;
    //   99: invokevirtual 251	my/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   102: astore 9
    //   104: aload 5
    //   106: instanceof 253
    //   109: ifeq +87 -> 196
    //   112: aload 5
    //   114: checkcast 253	javax/net/ssl/SSLSocket
    //   117: astore 11
    //   119: aload_0
    //   120: getfield 86	my/apache/http/conn/ssl/SSLSocketFactory:hostnameVerifier	Lmy/apache/http/conn/ssl/X509HostnameVerifier;
    //   123: ifnull +16 -> 139
    //   126: aload_0
    //   127: getfield 86	my/apache/http/conn/ssl/SSLSocketFactory:hostnameVerifier	Lmy/apache/http/conn/ssl/X509HostnameVerifier;
    //   130: aload 9
    //   132: aload 11
    //   134: invokeinterface 259 3 0
    //   139: aload 11
    //   141: areturn
    //   142: aload_0
    //   143: getfield 84	my/apache/http/conn/ssl/SSLSocketFactory:socketfactory	Ljavax/net/ssl/SSLSocketFactory;
    //   146: invokevirtual 263	javax/net/ssl/SSLSocketFactory:createSocket	()Ljava/net/Socket;
    //   149: astore 5
    //   151: goto -115 -> 36
    //   154: astore 8
    //   156: new 176	my/apache/http/conn/ConnectTimeoutException
    //   159: dup
    //   160: new 265	java/lang/StringBuilder
    //   163: dup
    //   164: ldc_w 267
    //   167: invokespecial 268	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   170: aload_2
    //   171: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   174: ldc_w 274
    //   177: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokespecial 281	my/apache/http/conn/ConnectTimeoutException:<init>	(Ljava/lang/String;)V
    //   186: athrow
    //   187: aload_2
    //   188: invokevirtual 282	java/net/InetSocketAddress:getHostName	()Ljava/lang/String;
    //   191: astore 9
    //   193: goto -89 -> 104
    //   196: aload_2
    //   197: invokevirtual 286	java/net/InetSocketAddress:getPort	()I
    //   200: istore 10
    //   202: aload_0
    //   203: getfield 84	my/apache/http/conn/ssl/SSLSocketFactory:socketfactory	Ljavax/net/ssl/SSLSocketFactory;
    //   206: aload 5
    //   208: aload 9
    //   210: iload 10
    //   212: iconst_1
    //   213: invokevirtual 289	javax/net/ssl/SSLSocketFactory:createSocket	(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
    //   216: checkcast 253	javax/net/ssl/SSLSocket
    //   219: astore 11
    //   221: aload_0
    //   222: aload 11
    //   224: invokevirtual 293	my/apache/http/conn/ssl/SSLSocketFactory:prepareSocket	(Ljavax/net/ssl/SSLSocket;)V
    //   227: goto -108 -> 119
    //   230: astore 12
    //   232: aload 11
    //   234: invokevirtual 296	javax/net/ssl/SSLSocket:close	()V
    //   237: aload 12
    //   239: athrow
    //   240: astore 13
    //   242: goto -5 -> 237
    //
    // Exception table:
    //   from	to	target	type
    //   70	85	154	java/net/SocketTimeoutException
    //   126	139	230	java/io/IOException
    //   232	237	240	java/lang/Exception
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, true);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  @Deprecated
  public Socket createSocket()
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket();
    prepareSocket(localSSLSocket);
    return localSSLSocket;
  }

  @Deprecated
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, paramBoolean);
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket();
    prepareSocket(localSSLSocket);
    return localSSLSocket;
  }

  public X509HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (!(paramSocket instanceof SSLSocket))
      throw new IllegalArgumentException("Socket not created by this factory");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed");
    return true;
  }

  protected void prepareSocket(SSLSocket paramSSLSocket)
    throws IOException
  {
  }

  @Deprecated
  public void setHostnameVerifier(X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramX509HostnameVerifier == null)
      throw new IllegalArgumentException("Hostname verifier may not be null");
    this.hostnameVerifier = paramX509HostnameVerifier;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ssl.SSLSocketFactory
 * JD-Core Version:    0.6.2
 */