package my.apache.http.impl.client;

import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.annotation.Immutable;
import my.apache.http.client.HttpResponseException;
import my.apache.http.client.ResponseHandler;
import my.apache.http.util.EntityUtils;

@Immutable
public class BasicResponseHandler
  implements ResponseHandler<String>
{
  public String handleResponse(HttpResponse paramHttpResponse)
    throws HttpResponseException, IOException
  {
    StatusLine localStatusLine = paramHttpResponse.getStatusLine();
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localStatusLine.getStatusCode() >= 300)
    {
      EntityUtils.consume(localHttpEntity);
      throw new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase());
    }
    if (localHttpEntity == null)
      return null;
    return EntityUtils.toString(localHttpEntity);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.BasicResponseHandler
 * JD-Core Version:    0.6.2
 */