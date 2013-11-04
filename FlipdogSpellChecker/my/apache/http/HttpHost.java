package my.apache.http;

import java.io.Serializable;
import java.util.Locale;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public final class HttpHost
  implements Cloneable, Serializable
{
  public static final String DEFAULT_SCHEME_NAME = "http";
  private static final long serialVersionUID = -7529410654042457626L;
  protected final String hostname;
  protected final String lcHostname;
  protected final int port;
  protected final String schemeName;

  public HttpHost(String paramString)
  {
    this(paramString, -1, null);
  }

  public HttpHost(String paramString, int paramInt)
  {
    this(paramString, paramInt, null);
  }

  public HttpHost(String paramString1, int paramInt, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Host name may not be null");
    this.hostname = paramString1;
    this.lcHostname = paramString1.toLowerCase(Locale.ENGLISH);
    if (paramString2 != null);
    for (this.schemeName = paramString2.toLowerCase(Locale.ENGLISH); ; this.schemeName = "http")
    {
      this.port = paramInt;
      return;
    }
  }

  public HttpHost(HttpHost paramHttpHost)
  {
    this(paramHttpHost.hostname, paramHttpHost.port, paramHttpHost.schemeName);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    HttpHost localHttpHost;
    do
    {
      return true;
      if (!(paramObject instanceof HttpHost))
        break;
      localHttpHost = (HttpHost)paramObject;
    }
    while ((this.lcHostname.equals(localHttpHost.lcHostname)) && (this.port == localHttpHost.port) && (this.schemeName.equals(localHttpHost.schemeName)));
    return false;
    return false;
  }

  public String getHostName()
  {
    return this.hostname;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getSchemeName()
  {
    return this.schemeName;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.lcHostname), this.port), this.schemeName);
  }

  public String toHostString()
  {
    if (this.port != -1)
    {
      StringBuilder localStringBuilder = new StringBuilder(6 + this.hostname.length());
      localStringBuilder.append(this.hostname);
      localStringBuilder.append(":");
      localStringBuilder.append(Integer.toString(this.port));
      return localStringBuilder.toString();
    }
    return this.hostname;
  }

  public String toString()
  {
    return toURI();
  }

  public String toURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.schemeName);
    localStringBuilder.append("://");
    localStringBuilder.append(this.hostname);
    if (this.port != -1)
    {
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(this.port));
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpHost
 * JD-Core Version:    0.6.2
 */