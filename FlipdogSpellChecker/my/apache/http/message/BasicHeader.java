package my.apache.http.message;

import java.io.Serializable;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.ParseException;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicHeader
  implements Header, Cloneable, Serializable
{
  private static final long serialVersionUID = -5427236326487562174L;
  private final String name;
  private final String value;

  public BasicHeader(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public HeaderElement[] getElements()
    throws ParseException
  {
    if (this.value != null)
      return BasicHeaderValueParser.parseElements(this.value, null);
    return new HeaderElement[0];
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public String toString()
  {
    return BasicLineFormatter.DEFAULT.formatHeader(null, this).toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeader
 * JD-Core Version:    0.6.2
 */