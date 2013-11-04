package my.apache.http.message;

import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.util.LangUtils;

@NotThreadSafe
public class BasicHeaderElement
  implements HeaderElement, Cloneable
{
  private final String name;
  private final NameValuePair[] parameters;
  private final String value;

  public BasicHeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public BasicHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
    if (paramArrayOfNameValuePair != null)
    {
      this.parameters = paramArrayOfNameValuePair;
      return;
    }
    this.parameters = new NameValuePair[0];
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    BasicHeaderElement localBasicHeaderElement;
    do
    {
      return true;
      if (!(paramObject instanceof Cloneable))
        break;
      localBasicHeaderElement = (BasicHeaderElement)paramObject;
    }
    while ((this.name.equals(localBasicHeaderElement.name)) && (LangUtils.equals(this.value, localBasicHeaderElement.value)) && (LangUtils.equals(this.parameters, localBasicHeaderElement.parameters)));
    return false;
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public NameValuePair getParameter(int paramInt)
  {
    return this.parameters[paramInt];
  }

  public NameValuePair getParameterByName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    for (int i = 0; ; i++)
    {
      if (i >= this.parameters.length)
        return null;
      NameValuePair localNameValuePair = this.parameters[i];
      if (localNameValuePair.getName().equalsIgnoreCase(paramString))
        return localNameValuePair;
    }
  }

  public int getParameterCount()
  {
    return this.parameters.length;
  }

  public NameValuePair[] getParameters()
  {
    return (NameValuePair[])this.parameters.clone();
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int i = LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
    for (int j = 0; ; j++)
    {
      if (j >= this.parameters.length)
        return i;
      i = LangUtils.hashCode(i, this.parameters[j]);
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    if (this.value != null)
    {
      localStringBuilder.append("=");
      localStringBuilder.append(this.value);
    }
    for (int i = 0; ; i++)
    {
      if (i >= this.parameters.length)
        return localStringBuilder.toString();
      localStringBuilder.append("; ");
      localStringBuilder.append(this.parameters[i]);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeaderElement
 * JD-Core Version:    0.6.2
 */