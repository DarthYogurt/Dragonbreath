package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpPut extends HttpEntityEnclosingRequestBase
{
  public static final String METHOD_NAME = "PUT";

  public HttpPut()
  {
  }

  public HttpPut(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpPut(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "PUT";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpPut
 * JD-Core Version:    0.6.2
 */