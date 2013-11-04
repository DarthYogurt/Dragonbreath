package my.apache.http.entity;

import my.apache.http.HttpException;
import my.apache.http.HttpMessage;

public abstract interface ContentLengthStrategy
{
  public static final int CHUNKED = -2;
  public static final int IDENTITY = -1;

  public abstract long determineLength(HttpMessage paramHttpMessage)
    throws HttpException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.ContentLengthStrategy
 * JD-Core Version:    0.6.2
 */