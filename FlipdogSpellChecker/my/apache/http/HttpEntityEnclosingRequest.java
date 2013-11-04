package my.apache.http;

public abstract interface HttpEntityEnclosingRequest extends HttpRequest
{
  public abstract boolean expectContinue();

  public abstract HttpEntity getEntity();

  public abstract void setEntity(HttpEntity paramHttpEntity);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpEntityEnclosingRequest
 * JD-Core Version:    0.6.2
 */