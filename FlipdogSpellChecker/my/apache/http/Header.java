package my.apache.http;

public abstract interface Header
{
  public abstract HeaderElement[] getElements()
    throws ParseException;

  public abstract String getName();

  public abstract String getValue();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.Header
 * JD-Core Version:    0.6.2
 */