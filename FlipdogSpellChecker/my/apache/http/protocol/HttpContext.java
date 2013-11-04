package my.apache.http.protocol;

public abstract interface HttpContext
{
  public static final String RESERVED_PREFIX = "http.";

  public abstract Object getAttribute(String paramString);

  public abstract Object removeAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpContext
 * JD-Core Version:    0.6.2
 */