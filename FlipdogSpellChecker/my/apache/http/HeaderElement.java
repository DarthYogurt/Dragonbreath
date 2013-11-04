package my.apache.http;

public abstract interface HeaderElement
{
  public abstract String getName();

  public abstract NameValuePair getParameter(int paramInt);

  public abstract NameValuePair getParameterByName(String paramString);

  public abstract int getParameterCount();

  public abstract NameValuePair[] getParameters();

  public abstract String getValue();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HeaderElement
 * JD-Core Version:    0.6.2
 */