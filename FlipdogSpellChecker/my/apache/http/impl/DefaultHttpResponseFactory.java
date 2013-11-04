package my.apache.http.impl;

import java.util.Locale;
import my.apache.http.HttpResponse;
import my.apache.http.HttpResponseFactory;
import my.apache.http.ProtocolVersion;
import my.apache.http.ReasonPhraseCatalog;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.message.BasicHttpResponse;
import my.apache.http.message.BasicStatusLine;
import my.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpResponseFactory
  implements HttpResponseFactory
{
  protected final ReasonPhraseCatalog reasonCatalog;

  public DefaultHttpResponseFactory()
  {
    this(EnglishReasonPhraseCatalog.INSTANCE);
  }

  public DefaultHttpResponseFactory(ReasonPhraseCatalog paramReasonPhraseCatalog)
  {
    if (paramReasonPhraseCatalog == null)
      throw new IllegalArgumentException("Reason phrase catalog must not be null.");
    this.reasonCatalog = paramReasonPhraseCatalog;
  }

  protected Locale determineLocale(HttpContext paramHttpContext)
  {
    return Locale.getDefault();
  }

  public HttpResponse newHttpResponse(ProtocolVersion paramProtocolVersion, int paramInt, HttpContext paramHttpContext)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("HTTP version may not be null");
    Locale localLocale = determineLocale(paramHttpContext);
    return new BasicHttpResponse(new BasicStatusLine(paramProtocolVersion, paramInt, this.reasonCatalog.getReason(paramInt, localLocale)), this.reasonCatalog, localLocale);
  }

  public HttpResponse newHttpResponse(StatusLine paramStatusLine, HttpContext paramHttpContext)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    Locale localLocale = determineLocale(paramHttpContext);
    return new BasicHttpResponse(paramStatusLine, this.reasonCatalog, localLocale);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.DefaultHttpResponseFactory
 * JD-Core Version:    0.6.2
 */