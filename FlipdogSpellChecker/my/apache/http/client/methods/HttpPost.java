package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpPost extends HttpEntityEnclosingRequestBase
{
  public static final String METHOD_NAME = "POST";

  public HttpPost()
  {
  }

  public HttpPost(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpPost(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "POST";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpPost
 * JD-Core Version:    0.6.2
 */