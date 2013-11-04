package my.apache.http;

public class HttpException extends Exception
{
  private static final long serialVersionUID = -5437299376222011036L;

  public HttpException()
  {
  }

  public HttpException(String paramString)
  {
    super(paramString);
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpException
 * JD-Core Version:    0.6.2
 */