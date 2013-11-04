package my.apache.http.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HeaderIterator;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class HttpOptions extends HttpRequestBase
{
  public static final String METHOD_NAME = "OPTIONS";

  public HttpOptions()
  {
  }

  public HttpOptions(String paramString)
  {
    setURI(URI.create(paramString));
  }

  public HttpOptions(URI paramURI)
  {
    setURI(paramURI);
  }

  public Set<String> getAllowedMethods(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null)
      throw new IllegalArgumentException("HTTP response may not be null");
    HeaderIterator localHeaderIterator = paramHttpResponse.headerIterator("Allow");
    HashSet localHashSet = new HashSet();
    while (true)
    {
      if (!localHeaderIterator.hasNext())
        return localHashSet;
      HeaderElement[] arrayOfHeaderElement = localHeaderIterator.nextHeader().getElements();
      int i = arrayOfHeaderElement.length;
      for (int j = 0; j < i; j++)
        localHashSet.add(arrayOfHeaderElement[j].getName());
    }
  }

  public String getMethod()
  {
    return "OPTIONS";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpOptions
 * JD-Core Version:    0.6.2
 */