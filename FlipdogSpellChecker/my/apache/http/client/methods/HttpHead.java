package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpHead extends HttpRequestBase
{
  public static final String METHOD_NAME = "HEAD";

  public HttpHead()
  {
  }

  public HttpHead(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpHead(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "HEAD";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpHead
 * JD-Core Version:    0.6.2
 */