package my.apache.http.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public class HeaderGroup
  implements Cloneable, Serializable
{
  private static final long serialVersionUID = 2608834160639271617L;
  private final List<Header> headers = new ArrayList(16);

  public void addHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    this.headers.add(paramHeader);
  }

  public void clear()
  {
    this.headers.clear();
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean containsHeader(String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.headers.size())
        return false;
      if (((Header)this.headers.get(i)).getName().equalsIgnoreCase(paramString))
        return true;
    }
  }

  public HeaderGroup copy()
  {
    HeaderGroup localHeaderGroup = new HeaderGroup();
    localHeaderGroup.headers.addAll(this.headers);
    return localHeaderGroup;
  }

  public Header[] getAllHeaders()
  {
    return (Header[])this.headers.toArray(new Header[this.headers.size()]);
  }

  public Header getCondensedHeader(String paramString)
  {
    Header[] arrayOfHeader = getHeaders(paramString);
    if (arrayOfHeader.length == 0)
      return null;
    if (arrayOfHeader.length == 1)
      return arrayOfHeader[0];
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(128);
    localCharArrayBuffer.append(arrayOfHeader[0].getValue());
    for (int i = 1; ; i++)
    {
      if (i >= arrayOfHeader.length)
        return new BasicHeader(paramString.toLowerCase(Locale.ENGLISH), localCharArrayBuffer.toString());
      localCharArrayBuffer.append(", ");
      localCharArrayBuffer.append(arrayOfHeader[i].getValue());
    }
  }

  public Header getFirstHeader(String paramString)
  {
    for (int i = 0; ; i++)
    {
      Header localHeader;
      if (i >= this.headers.size())
        localHeader = null;
      do
      {
        return localHeader;
        localHeader = (Header)this.headers.get(i);
      }
      while (localHeader.getName().equalsIgnoreCase(paramString));
    }
  }

  public Header[] getHeaders(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= this.headers.size())
        return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
      Header localHeader = (Header)this.headers.get(i);
      if (localHeader.getName().equalsIgnoreCase(paramString))
        localArrayList.add(localHeader);
    }
  }

  public Header getLastHeader(String paramString)
  {
    for (int i = -1 + this.headers.size(); ; i--)
    {
      Header localHeader;
      if (i < 0)
        localHeader = null;
      do
      {
        return localHeader;
        localHeader = (Header)this.headers.get(i);
      }
      while (localHeader.getName().equalsIgnoreCase(paramString));
    }
  }

  public HeaderIterator iterator()
  {
    return new BasicListHeaderIterator(this.headers, null);
  }

  public HeaderIterator iterator(String paramString)
  {
    return new BasicListHeaderIterator(this.headers, paramString);
  }

  public void removeHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    this.headers.remove(paramHeader);
  }

  public void setHeaders(Header[] paramArrayOfHeader)
  {
    clear();
    if (paramArrayOfHeader == null);
    while (true)
    {
      return;
      for (int i = 0; i < paramArrayOfHeader.length; i++)
        this.headers.add(paramArrayOfHeader[i]);
    }
  }

  public String toString()
  {
    return this.headers.toString();
  }

  public void updateHeader(Header paramHeader)
  {
    if (paramHeader == null)
      return;
    for (int i = 0; ; i++)
    {
      if (i >= this.headers.size())
      {
        this.headers.add(paramHeader);
        return;
      }
      if (((Header)this.headers.get(i)).getName().equalsIgnoreCase(paramHeader.getName()))
      {
        this.headers.set(i, paramHeader);
        return;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.HeaderGroup
 * JD-Core Version:    0.6.2
 */