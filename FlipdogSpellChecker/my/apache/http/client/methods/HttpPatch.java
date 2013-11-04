package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpPatch extends HttpEntityEnclosingRequestBase
{
  public static final String METHOD_NAME = "PATCH";

  public HttpPatch()
  {
  }

  public HttpPatch(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpPatch(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "PATCH";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpPatch
 * JD-Core Version:    0.6.2
 */