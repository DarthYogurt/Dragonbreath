package my.apache.http.message;

import java.io.Serializable;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public class BasicNameValuePair
  implements NameValuePair, Cloneable, Serializable
{
  private static final long serialVersionUID = -6437800749411518984L;
  private final String name;
  private final String value;

  public BasicNameValuePair(String paramString1, String paramString2)
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

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    BasicNameValuePair localBasicNameValuePair;
    do
    {
      return true;
      if (!(paramObject instanceof Serializable))
        break;
      localBasicNameValuePair = (BasicNameValuePair)paramObject;
    }
    while ((this.name.equals(localBasicNameValuePair.name)) && (LangUtils.equals(this.value, localBasicNameValuePair.value)));
    return false;
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
  }

  public String toString()
  {
    if (this.value == null)
      return this.name;
    StringBuilder localStringBuilder = new StringBuilder(1 + this.name.length() + this.value.length());
    localStringBuilder.append(this.name);
    localStringBuilder.append("=");
    localStringBuilder.append(this.value);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicNameValuePair
 * JD-Core Version:    0.6.2
 */