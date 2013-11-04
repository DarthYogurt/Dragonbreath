package my.apache.http.client;

import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;

@Immutable
public class NonRepeatableRequestException extends ProtocolException
{
  private static final long serialVersionUID = 82685265288806048L;

  public NonRepeatableRequestException()
  {
  }

  public NonRepeatableRequestException(String paramString)
  {
    super(paramString);
  }

  public NonRepeatableRequestException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.NonRepeatableRequestException
 * JD-Core Version:    0.6.2
 */