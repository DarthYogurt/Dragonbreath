package my.apache.http.protocol;

import java.util.Map;
import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class HttpRequestHandlerRegistry
  implements HttpRequestHandlerResolver
{
  private final UriPatternMatcher<HttpRequestHandler> matcher = new UriPatternMatcher();

  public Map<String, HttpRequestHandler> getHandlers()
  {
    return this.matcher.getObjects();
  }

  public HttpRequestHandler lookup(String paramString)
  {
    return (HttpRequestHandler)this.matcher.lookup(paramString);
  }

  public void register(String paramString, HttpRequestHandler paramHttpRequestHandler)
  {
    if (paramString == null)
      throw new IllegalArgumentException("URI request pattern may not be null");
    if (paramHttpRequestHandler == null)
      throw new IllegalArgumentException("Request handler may not be null");
    this.matcher.register(paramString, paramHttpRequestHandler);
  }

  public void setHandlers(Map<String, HttpRequestHandler> paramMap)
  {
    this.matcher.setObjects(paramMap);
  }

  public void unregister(String paramString)
  {
    this.matcher.unregister(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpRequestHandlerRegistry
 * JD-Core Version:    0.6.2
 */