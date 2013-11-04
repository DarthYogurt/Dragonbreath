package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpDelete extends HttpRequestBase
{
  public static final String METHOD_NAME = "DELETE";

  public HttpDelete()
  {
  }

  public HttpDelete(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpDelete(URI paramURI)
  {
    setURI(paramURI);
  }

  public String getMethod()
  {
    return "DELETE";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpDelete
 * JD-Core Version:    0.6.2
 */