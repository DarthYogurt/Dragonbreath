package my.apache.http.impl.client;

import my.apache.http.HttpException;
import my.apache.http.HttpResponse;
import my.apache.http.annotation.Immutable;

@Immutable
public class TunnelRefusedException extends HttpException
{
  private static final long serialVersionUID = -8646722842745617323L;
  private final HttpResponse response;

  public TunnelRefusedException(String paramString, HttpResponse paramHttpResponse)
  {
    super(paramString);
    this.response = paramHttpResponse;
  }

  public HttpResponse getResponse()
  {
    return this.response;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.TunnelRefusedException
 * JD-Core Version:    0.6.2
 */