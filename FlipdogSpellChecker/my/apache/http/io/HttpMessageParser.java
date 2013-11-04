package my.apache.http.io;

import java.io.IOException;
import my.apache.http.HttpException;
import my.apache.http.HttpMessage;

public abstract interface HttpMessageParser<T extends HttpMessage>
{
  public abstract T parse()
    throws IOException, HttpException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.io.HttpMessageParser
 * JD-Core Version:    0.6.2
 */