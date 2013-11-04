package my.apache.http.impl.client;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import my.apache.http.HttpRequest;
import my.apache.http.RequestLine;
import my.apache.http.annotation.Immutable;

@Immutable
public class StandardHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler
{
  private final Map<String, Boolean> idempotentMethods = new ConcurrentHashMap();

  public StandardHttpRequestRetryHandler()
  {
    this(3, false);
  }

  public StandardHttpRequestRetryHandler(int paramInt, boolean paramBoolean)
  {
    super(paramInt, paramBoolean);
    this.idempotentMethods.put("GET", Boolean.TRUE);
    this.idempotentMethods.put("HEAD", Boolean.TRUE);
    this.idempotentMethods.put("PUT", Boolean.TRUE);
    this.idempotentMethods.put("DELETE", Boolean.TRUE);
    this.idempotentMethods.put("OPTIONS", Boolean.TRUE);
    this.idempotentMethods.put("TRACE", Boolean.TRUE);
  }

  protected boolean handleAsIdempotent(HttpRequest paramHttpRequest)
  {
    String str = paramHttpRequest.getRequestLine().getMethod().toUpperCase(Locale.US);
    Boolean localBoolean = (Boolean)this.idempotentMethods.get(str);
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.StandardHttpRequestRetryHandler
 * JD-Core Version:    0.6.2
 */