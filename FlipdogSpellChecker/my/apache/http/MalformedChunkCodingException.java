package my.apache.http;

import java.io.IOException;

public class MalformedChunkCodingException extends IOException
{
  private static final long serialVersionUID = 2158560246948994524L;

  public MalformedChunkCodingException()
  {
  }

  public MalformedChunkCodingException(String paramString)
  {
    super(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.MalformedChunkCodingException
 * JD-Core Version:    0.6.2
 */