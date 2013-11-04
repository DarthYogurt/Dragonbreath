package my.apache.http.params;

public abstract class HttpAbstractParamBean
{
  protected final HttpParams params;

  public HttpAbstractParamBean(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.params = paramHttpParams;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.params.HttpAbstractParamBean
 * JD-Core Version:    0.6.2
 */