package my.apache.http.client.utils;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.client.HttpClient;
import my.apache.http.conn.ClientConnectionManager;
import my.apache.http.util.EntityUtils;

public class HttpClientUtils
{
  public static void closeQuietly(HttpResponse paramHttpResponse)
  {
    HttpEntity localHttpEntity;
    if (paramHttpResponse != null)
    {
      localHttpEntity = paramHttpResponse.getEntity();
      if (localHttpEntity == null);
    }
    try
    {
      EntityUtils.consume(localHttpEntity);
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(HttpClient paramHttpClient)
  {
    if (paramHttpClient != null)
      paramHttpClient.getConnectionManager().shutdown();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.HttpClientUtils
 * JD-Core Version:    0.6.2
 */