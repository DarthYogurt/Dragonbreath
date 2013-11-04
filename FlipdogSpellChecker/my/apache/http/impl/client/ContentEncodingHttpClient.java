package my.apache.http.impl.client;

import my.apache.http.annotation.ThreadSafe;
import my.apache.http.client.protocol.RequestAcceptEncoding;
import my.apache.http.client.protocol.ResponseContentEncoding;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.BasicHttpProcessor;

@Deprecated
@ThreadSafe
public class ContentEncodingHttpClient extends DefaultHttpClient
{
  public ContentEncodingHttpClient()
  {
    this(null);
  }

  public ContentEncodingHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }

  public ContentEncodingHttpClient(HttpParams paramHttpParams)
  {
    this(null, paramHttpParams);
  }

  protected BasicHttpProcessor createHttpProcessor()
  {
    BasicHttpProcessor localBasicHttpProcessor = super.createHttpProcessor();
    localBasicHttpProcessor.addRequestInterceptor(new RequestAcceptEncoding());
    localBasicHttpProcessor.addResponseInterceptor(new ResponseContentEncoding());
    return localBasicHttpProcessor;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.ContentEncodingHttpClient
 * JD-Core Version:    0.6.2
 */