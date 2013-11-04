package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;

@Immutable
public class DateParseException extends Exception
{
  private static final long serialVersionUID = 4417696455000643370L;

  public DateParseException()
  {
  }

  public DateParseException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.DateParseException
 * JD-Core Version:    0.6.2
 */