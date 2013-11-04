package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpGet extends HttpRequestBase
{
  public static final String METHOD_NAME = "GET";

  public HttpGet()
  {
  }

  public HttpGet(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpGet(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "GET";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpGet
 * JD-Core Version:    0.6.2
 */