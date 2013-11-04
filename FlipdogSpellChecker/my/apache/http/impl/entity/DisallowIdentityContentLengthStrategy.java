package my.apache.http.impl.entity;

import my.apache.http.HttpException;
import my.apache.http.HttpMessage;
import my.apache.http.ProtocolException;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.ContentLengthStrategy;

@Immutable
public class DisallowIdentityContentLengthStrategy
  implements ContentLengthStrategy
{
  private final ContentLengthStrategy contentLengthStrategy;

  public DisallowIdentityContentLengthStrategy(ContentLengthStrategy paramContentLengthStrategy)
  {
    this.contentLengthStrategy = paramContentLengthStrategy;
  }

  public long determineLength(HttpMessage paramHttpMessage)
    throws HttpException
  {
    long l = this.contentLengthStrategy.determineLength(paramHttpMessage);
    if (l == -1L)
      throw new ProtocolException("Identity transfer encoding cannot be used");
    return l;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.entity.DisallowIdentityContentLengthStrategy
 * JD-Core Version:    0.6.2
 */