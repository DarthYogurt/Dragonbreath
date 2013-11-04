package my.apache.http.protocol;

import java.io.IOException;
import my.apache.http.ConnectionReuseStrategy;
import my.apache.http.HttpEntityEnclosingRequest;
import my.apache.http.HttpException;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseFactory;
import my.apache.http.HttpServerConnection;
import my.apache.http.HttpVersion;
import my.apache.http.MethodNotSupportedException;
import my.apache.http.ProtocolException;
import my.apache.http.RequestLine;
import my.apache.http.StatusLine;
import my.apache.http.UnsupportedHttpVersionException;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.ByteArrayEntity;
import my.apache.http.params.DefaultedHttpParams;
import my.apache.http.params.HttpParams;
import my.apache.http.util.EncodingUtils;
import my.apache.http.util.EntityUtils;

@Immutable
public class HttpService
{
  private volatile ConnectionReuseStrategy connStrategy = null;
  private volatile HttpExpectationVerifier expectationVerifier = null;
  private volatile HttpRequestHandlerResolver handlerResolver = null;
  private volatile HttpParams params = null;
  private volatile HttpProcessor processor = null;
  private volatile HttpResponseFactory responseFactory = null;

  @Deprecated
  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory)
  {
    setHttpProcessor(paramHttpProcessor);
    setConnReuseStrategy(paramConnectionReuseStrategy);
    setResponseFactory(paramHttpResponseFactory);
  }

  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory, HttpRequestHandlerResolver paramHttpRequestHandlerResolver, HttpParams paramHttpParams)
  {
    this(paramHttpProcessor, paramConnectionReuseStrategy, paramHttpResponseFactory, paramHttpRequestHandlerResolver, null, paramHttpParams);
  }

  public HttpService(HttpProcessor paramHttpProcessor, ConnectionReuseStrategy paramConnectionReuseStrategy, HttpResponseFactory paramHttpResponseFactory, HttpRequestHandlerResolver paramHttpRequestHandlerResolver, HttpExpectationVerifier paramHttpExpectationVerifier, HttpParams paramHttpParams)
  {
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null");
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.processor = paramHttpProcessor;
    this.connStrategy = paramConnectionReuseStrategy;
    this.responseFactory = paramHttpResponseFactory;
    this.handlerResolver = paramHttpRequestHandlerResolver;
    this.expectationVerifier = paramHttpExpectationVerifier;
    this.params = paramHttpParams;
  }

  protected void doService(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    HttpRequestHandlerResolver localHttpRequestHandlerResolver = this.handlerResolver;
    HttpRequestHandler localHttpRequestHandler = null;
    if (localHttpRequestHandlerResolver != null)
    {
      String str = paramHttpRequest.getRequestLine().getUri();
      localHttpRequestHandler = this.handlerResolver.lookup(str);
    }
    if (localHttpRequestHandler != null)
    {
      localHttpRequestHandler.handle(paramHttpRequest, paramHttpResponse, paramHttpContext);
      return;
    }
    paramHttpResponse.setStatusCode(501);
  }

  public HttpParams getParams()
  {
    return this.params;
  }

  protected void handleException(HttpException paramHttpException, HttpResponse paramHttpResponse)
  {
    if ((paramHttpException instanceof MethodNotSupportedException))
      paramHttpResponse.setStatusCode(501);
    while (true)
    {
      String str = paramHttpException.getMessage();
      if (str == null)
        str = paramHttpException.toString();
      ByteArrayEntity localByteArrayEntity = new ByteArrayEntity(EncodingUtils.getAsciiBytes(str));
      localByteArrayEntity.setContentType("text/plain; charset=US-ASCII");
      paramHttpResponse.setEntity(localByteArrayEntity);
      return;
      if ((paramHttpException instanceof UnsupportedHttpVersionException))
        paramHttpResponse.setStatusCode(505);
      else if ((paramHttpException instanceof ProtocolException))
        paramHttpResponse.setStatusCode(400);
      else
        paramHttpResponse.setStatusCode(500);
    }
  }

  public void handleRequest(HttpServerConnection paramHttpServerConnection, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    paramHttpContext.setAttribute("http.connection", paramHttpServerConnection);
    try
    {
      localHttpRequest = paramHttpServerConnection.receiveRequestHeader();
      localHttpRequest.setParams(new DefaultedHttpParams(localHttpRequest.getParams(), this.params));
      boolean bool = localHttpRequest instanceof HttpEntityEnclosingRequest;
      localHttpResponse = null;
      if (bool)
      {
        if (!((HttpEntityEnclosingRequest)localHttpRequest).expectContinue())
          break label459;
        localHttpResponse = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_1, 100, paramHttpContext);
        localHttpResponse.setParams(new DefaultedHttpParams(localHttpResponse.getParams(), this.params));
        HttpExpectationVerifier localHttpExpectationVerifier = this.expectationVerifier;
        if (localHttpExpectationVerifier == null);
      }
      try
      {
        this.expectationVerifier.verify(localHttpRequest, localHttpResponse, paramHttpContext);
        if (localHttpResponse.getStatusLine().getStatusCode() < 200)
        {
          paramHttpServerConnection.sendResponseHeader(localHttpResponse);
          paramHttpServerConnection.flush();
          localHttpResponse = null;
          paramHttpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest)localHttpRequest);
        }
        paramHttpContext.setAttribute("http.request", localHttpRequest);
        if (localHttpResponse == null)
        {
          localHttpResponse = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_1, 200, paramHttpContext);
          localHttpResponse.setParams(new DefaultedHttpParams(localHttpResponse.getParams(), this.params));
          this.processor.process(localHttpRequest, paramHttpContext);
          doService(localHttpRequest, localHttpResponse, paramHttpContext);
        }
        if ((localHttpRequest instanceof HttpEntityEnclosingRequest))
          EntityUtils.consume(((HttpEntityEnclosingRequest)localHttpRequest).getEntity());
        paramHttpContext.setAttribute("http.response", localHttpResponse);
        this.processor.process(localHttpResponse, paramHttpContext);
        paramHttpServerConnection.sendResponseHeader(localHttpResponse);
        paramHttpServerConnection.sendResponseEntity(localHttpResponse);
        paramHttpServerConnection.flush();
        if (!this.connStrategy.keepAlive(localHttpResponse, paramHttpContext))
          paramHttpServerConnection.close();
        return;
      }
      catch (HttpException localHttpException2)
      {
        while (true)
        {
          localHttpResponse = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, paramHttpContext);
          localHttpResponse.setParams(new DefaultedHttpParams(localHttpResponse.getParams(), this.params));
          handleException(localHttpException2, localHttpResponse);
        }
      }
    }
    catch (HttpException localHttpException1)
    {
      while (true)
      {
        HttpRequest localHttpRequest;
        HttpResponse localHttpResponse = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, paramHttpContext);
        localHttpResponse.setParams(new DefaultedHttpParams(localHttpResponse.getParams(), this.params));
        handleException(localHttpException1, localHttpResponse);
        continue;
        label459: paramHttpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest)localHttpRequest);
        localHttpResponse = null;
      }
    }
  }

  @Deprecated
  public void setConnReuseStrategy(ConnectionReuseStrategy paramConnectionReuseStrategy)
  {
    if (paramConnectionReuseStrategy == null)
      throw new IllegalArgumentException("Connection reuse strategy may not be null");
    this.connStrategy = paramConnectionReuseStrategy;
  }

  @Deprecated
  public void setExpectationVerifier(HttpExpectationVerifier paramHttpExpectationVerifier)
  {
    this.expectationVerifier = paramHttpExpectationVerifier;
  }

  @Deprecated
  public void setHandlerResolver(HttpRequestHandlerResolver paramHttpRequestHandlerResolver)
  {
    this.handlerResolver = paramHttpRequestHandlerResolver;
  }

  @Deprecated
  public void setHttpProcessor(HttpProcessor paramHttpProcessor)
  {
    if (paramHttpProcessor == null)
      throw new IllegalArgumentException("HTTP processor may not be null");
    this.processor = paramHttpProcessor;
  }

  @Deprecated
  public void setParams(HttpParams paramHttpParams)
  {
    this.params = paramHttpParams;
  }

  @Deprecated
  public void setResponseFactory(HttpResponseFactory paramHttpResponseFactory)
  {
    if (paramHttpResponseFactory == null)
      throw new IllegalArgumentException("Response factory may not be null");
    this.responseFactory = paramHttpResponseFactory;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpService
 * JD-Core Version:    0.6.2
 */