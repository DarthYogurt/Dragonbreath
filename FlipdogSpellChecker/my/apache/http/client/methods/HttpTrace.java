package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpTrace extends HttpRequestBase
{
  public static final String METHOD_NAME = "TRACE";

  public HttpTrace()
  {
  }

  public HttpTrace(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpTrace(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "TRACE";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpTrace
 * JD-Core Version:    0.6.2
 */