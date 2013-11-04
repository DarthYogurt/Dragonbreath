package my.apache.http.client;

import my.apache.http.annotation.Immutable;

@Immutable
public class HttpResponseException extends ClientProtocolException
{
  private static final long serialVersionUID = -7186627969477257933L;
  private final int statusCode;

  public HttpResponseException(int paramInt, String paramString)
  {
    super(paramString);
    this.statusCode = paramInt;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.HttpResponseException
 * JD-Core Version:    0.6.2
 */