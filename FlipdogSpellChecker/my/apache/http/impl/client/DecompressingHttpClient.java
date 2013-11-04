package my.apache.http.impl.client;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpRequestInterceptor;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseInterceptor;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.HttpClient;
import my.apache.http.client.ResponseHandler;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.client.protocol.RequestAcceptEncoding;
import my.apache.http.client.protocol.ResponseContentEncoding;
import my.apache.http.client.utils.URIUtils;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.params.HttpParams;
import my.apache.http.protocol.HttpContext;
import my.apache.http.util.EntityUtils;

public class DecompressingHttpClient
  implements HttpClient
{
  private HttpRequestInterceptor acceptEncodingInterceptor;
  private HttpClient backend;
  private HttpResponseInterceptor contentEncodingInterceptor;

  public DecompressingHttpClient(HttpClient paramHttpClient)
  {
    this(paramHttpClient, new RequestAcceptEncoding(), new ResponseContentEncoding());
  }

  DecompressingHttpClient(HttpClient paramHttpClient, HttpRequestInterceptor paramHttpRequestInterceptor, HttpResponseInterceptor paramHttpResponseInterceptor)
  {
    this.backend = paramHttpClient;
    this.acceptEncodingInterceptor = paramHttpRequestInterceptor;
    this.contentEncodingInterceptor = paramHttpResponseInterceptor;
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, paramResponseHandler, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    HttpResponse localHttpResponse = execute(paramHttpHost, paramHttpRequest, paramHttpContext);
    try
    {
      Object localObject2 = paramResponseHandler.handleResponse(localHttpResponse);
      HttpEntity localHttpEntity2;
      return localObject2;
    }
    finally
    {
      HttpEntity localHttpEntity1 = localHttpResponse.getEntity();
      if (localHttpEntity1 != null)
        EntityUtils.consume(localHttpEntity1);
    }
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException, ClientProtocolException
  {
    return execute(paramHttpHost, paramHttpRequest, null);
  }

  // ERROR //
  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    // Byte code:
    //   0: aload_3
    //   1: ifnonnull +11 -> 12
    //   4: new 77	my/apache/http/protocol/BasicHttpContext
    //   7: dup
    //   8: invokespecial 78	my/apache/http/protocol/BasicHttpContext:<init>	()V
    //   11: astore_3
    //   12: aload_2
    //   13: instanceof 80
    //   16: ifeq +102 -> 118
    //   19: new 82	my/apache/http/impl/client/EntityEnclosingRequestWrapper
    //   22: dup
    //   23: aload_2
    //   24: checkcast 80	my/apache/http/HttpEntityEnclosingRequest
    //   27: invokespecial 85	my/apache/http/impl/client/EntityEnclosingRequestWrapper:<init>	(Lmy/apache/http/HttpEntityEnclosingRequest;)V
    //   30: astore 5
    //   32: aload_0
    //   33: getfield 30	my/apache/http/impl/client/DecompressingHttpClient:acceptEncodingInterceptor	Lmy/apache/http/HttpRequestInterceptor;
    //   36: aload 5
    //   38: aload_3
    //   39: invokeinterface 91 3 0
    //   44: aload_0
    //   45: getfield 28	my/apache/http/impl/client/DecompressingHttpClient:backend	Lmy/apache/http/client/HttpClient;
    //   48: aload_1
    //   49: aload 5
    //   51: aload_3
    //   52: invokeinterface 92 4 0
    //   57: astore 6
    //   59: aload_0
    //   60: getfield 32	my/apache/http/impl/client/DecompressingHttpClient:contentEncodingInterceptor	Lmy/apache/http/HttpResponseInterceptor;
    //   63: aload 6
    //   65: aload_3
    //   66: invokeinterface 97 3 0
    //   71: getstatic 103	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   74: aload_3
    //   75: ldc 105
    //   77: invokeinterface 111 2 0
    //   82: invokevirtual 115	java/lang/Boolean:equals	(Ljava/lang/Object;)Z
    //   85: ifeq +30 -> 115
    //   88: aload 6
    //   90: ldc 117
    //   92: invokeinterface 121 2 0
    //   97: aload 6
    //   99: ldc 123
    //   101: invokeinterface 121 2 0
    //   106: aload 6
    //   108: ldc 125
    //   110: invokeinterface 121 2 0
    //   115: aload 6
    //   117: areturn
    //   118: new 127	my/apache/http/impl/client/RequestWrapper
    //   121: dup
    //   122: aload_2
    //   123: invokespecial 130	my/apache/http/impl/client/RequestWrapper:<init>	(Lmy/apache/http/HttpRequest;)V
    //   126: astore 5
    //   128: goto -96 -> 32
    //   131: astore 9
    //   133: aload 6
    //   135: invokeinterface 56 1 0
    //   140: invokestatic 62	my/apache/http/util/EntityUtils:consume	(Lmy/apache/http/HttpEntity;)V
    //   143: aload 9
    //   145: athrow
    //   146: astore 4
    //   148: new 38	my/apache/http/client/ClientProtocolException
    //   151: dup
    //   152: aload 4
    //   154: invokespecial 133	my/apache/http/client/ClientProtocolException:<init>	(Ljava/lang/Throwable;)V
    //   157: athrow
    //   158: astore 8
    //   160: aload 6
    //   162: invokeinterface 56 1 0
    //   167: invokestatic 62	my/apache/http/util/EntityUtils:consume	(Lmy/apache/http/HttpEntity;)V
    //   170: aload 8
    //   172: athrow
    //   173: astore 7
    //   175: aload 6
    //   177: invokeinterface 56 1 0
    //   182: invokestatic 62	my/apache/http/util/EntityUtils:consume	(Lmy/apache/http/HttpEntity;)V
    //   185: aload 7
    //   187: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   59	115	131	my/apache/http/HttpException
    //   4	12	146	my/apache/http/HttpException
    //   12	32	146	my/apache/http/HttpException
    //   32	59	146	my/apache/http/HttpException
    //   118	128	146	my/apache/http/HttpException
    //   133	146	146	my/apache/http/HttpException
    //   160	173	146	my/apache/http/HttpException
    //   175	188	146	my/apache/http/HttpException
    //   59	115	158	java/io/IOException
    //   59	115	173	java/lang/RuntimeException
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, null);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return execute(getHttpHost(paramHttpUriRequest), paramHttpUriRequest, paramHttpContext);
  }

  public ClientConnectionManager getConnectionManager()
  {
    return this.backend.getConnectionManager();
  }

  HttpHost getHttpHost(HttpUriRequest paramHttpUriRequest)
  {
    return URIUtils.extractHost(paramHttpUriRequest.getURI());
  }

  public HttpParams getParams()
  {
    return this.backend.getParams();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.DecompressingHttpClient
 * JD-Core Version:    0.6.2
 */