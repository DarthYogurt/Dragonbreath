package my.apache.http;

public abstract interface StatusLine
{
  public abstract ProtocolVersion getProtocolVersion();

  public abstract String getReasonPhrase();

  public abstract int getStatusCode();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.StatusLine
 * JD-Core Version:    0.6.2
 */