package my.apache.http.client.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.entity.StringEntity;

@NotThreadSafe
public class UrlEncodedFormEntity extends StringEntity
{
  public UrlEncodedFormEntity(Iterable<? extends NameValuePair> paramIterable)
  {
    this(paramIterable, null);
  }

  public UrlEncodedFormEntity(Iterable<? extends NameValuePair> paramIterable, Charset paramCharset)
  {
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList)
    throws UnsupportedEncodingException
  {
    this(paramList, null);
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList, String paramString)
    throws UnsupportedEncodingException
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.entity.UrlEncodedFormEntity
 * JD-Core Version:    0.6.2
 */