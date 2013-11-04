package my.apache.http.impl.client;

import java.nio.charset.Charset;
import my.apache.http.HttpVersion;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.client.protocol.RequestAddCookies;
import my.apache.http.client.protocol.RequestAuthCache;
import my.apache.http.client.protocol.RequestClientConnControl;
import my.apache.http.client.protocol.RequestDefaultHeaders;
import my.apache.http.client.protocol.RequestProxyAuthentication;
import my.apache.http.client.protocol.RequestTargetAuthentication;
import my.apache.http.client.protocol.ResponseProcessCookies;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.params.HttpConnectionParams;
import my.apache.http.params.HttpParams;
import my.apache.http.params.HttpProtocolParams;
import my.apache.http.params.SyncBasicHttpParams;
import my.apache.http.protocol.BasicHttpProcessor;
import my.apache.http.protocol.HTTP;
import my.apache.http.protocol.RequestContent;
import my.apache.http.protocol.RequestExpectContinue;
import my.apache.http.protocol.RequestTargetHost;
import my.apache.http.protocol.RequestUserAgent;
import my.apache.http.util.VersionInfo;

@ThreadSafe
public class DefaultHttpClient extends AbstractHttpClient
{
  public DefaultHttpClient()
  {
    super(null, null);
  }

  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager)
  {
    super(paramClientConnectionManager, null);
  }

  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }

  public DefaultHttpClient(HttpParams paramHttpParams)
  {
    super(null, paramHttpParams);
  }

  public static void setDefaultHttpParams(HttpParams paramHttpParams)
  {
    HttpProtocolParams.setVersion(paramHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(paramHttpParams, HTTP.DEF_CONTENT_CHARSET.name());
    HttpConnectionParams.setTcpNoDelay(paramHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(paramHttpParams, 8192);
    VersionInfo localVersionInfo = VersionInfo.loadVersionInfo("my.apache.http.client", DefaultHttpClient.class.getClassLoader());
    if (localVersionInfo != null);
    for (String str = localVersionInfo.getRelease(); ; str = "UNAVAILABLE")
    {
      HttpProtocolParams.setUserAgent(paramHttpParams, "Apache-HttpClient/" + str + " (java 1.5)");
      return;
    }
  }

  protected HttpParams createHttpParams()
  {
    SyncBasicHttpParams localSyncBasicHttpParams = new SyncBasicHttpParams();
    setDefaultHttpParams(localSyncBasicHttpParams);
    return localSyncBasicHttpParams;
  }

  protected BasicHttpProcessor createHttpProcessor()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    localBasicHttpProcessor.addInterceptor(new RequestDefaultHeaders());
    localBasicHttpProcessor.addInterceptor(new RequestContent());
    localBasicHttpProcessor.addInterceptor(new RequestTargetHost());
    localBasicHttpProcessor.addInterceptor(new RequestClientConnControl());
    localBasicHttpProcessor.addInterceptor(new RequestUserAgent());
    localBasicHttpProcessor.addInterceptor(new RequestExpectContinue());
    localBasicHttpProcessor.addInterceptor(new RequestAddCookies());
    localBasicHttpProcessor.addInterceptor(new ResponseProcessCookies());
    localBasicHttpProcessor.addInterceptor(new RequestAuthCache());
    localBasicHttpProcessor.addInterceptor(new RequestTargetAuthentication());
    localBasicHttpProcessor.addInterceptor(new RequestProxyAuthentication());
    return localBasicHttpProcessor;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DefaultHttpClient
 * JD-Core Version:    0.6.2
 */