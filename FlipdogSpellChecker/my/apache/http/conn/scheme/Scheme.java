package my.apache.http.conn.scheme;

import java.util.Locale;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public final class Scheme
{
  private final int defaultPort;
  private final boolean layered;
  private final String name;
  private final SchemeSocketFactory socketFactory;
  private String stringRep;

  public Scheme(String paramString, int paramInt, SchemeSocketFactory paramSchemeSocketFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Scheme name may not be null");
    if ((paramInt <= 0) || (paramInt > 65535))
      throw new IllegalArgumentException("Port is invalid: " + paramInt);
    if (paramSchemeSocketFactory == null)
      throw new IllegalArgumentException("Socket factory may not be null");
    this.name = paramString.toLowerCase(Locale.ENGLISH);
    this.defaultPort = paramInt;
    if ((paramSchemeSocketFactory instanceof SchemeLayeredSocketFactory))
    {
      this.layered = true;
      this.socketFactory = paramSchemeSocketFactory;
      return;
    }
    if ((paramSchemeSocketFactory instanceof LayeredSchemeSocketFactory))
    {
      this.layered = true;
      this.socketFactory = new SchemeLayeredSocketFactoryAdaptor2((LayeredSchemeSocketFactory)paramSchemeSocketFactory);
      return;
    }
    this.layered = false;
    this.socketFactory = paramSchemeSocketFactory;
  }

  @Deprecated
  public Scheme(String paramString, SocketFactory paramSocketFactory, int paramInt)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Scheme name may not be null");
    if (paramSocketFactory == null)
      throw new IllegalArgumentException("Socket factory may not be null");
    if ((paramInt <= 0) || (paramInt > 65535))
      throw new IllegalArgumentException("Port is invalid: " + paramInt);
    this.name = paramString.toLowerCase(Locale.ENGLISH);
    if ((paramSocketFactory instanceof LayeredSocketFactory))
      this.socketFactory = new SchemeLayeredSocketFactoryAdaptor((LayeredSocketFactory)paramSocketFactory);
    for (this.layered = true; ; this.layered = false)
    {
      this.defaultPort = paramInt;
      return;
      this.socketFactory = new SchemeSocketFactoryAdaptor(paramSocketFactory);
    }
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject);
    Scheme localScheme;
    do
    {
      return true;
      if (!(paramObject instanceof Scheme))
        break;
      localScheme = (Scheme)paramObject;
    }
    while ((this.name.equals(localScheme.name)) && (this.defaultPort == localScheme.defaultPort) && (this.layered == localScheme.layered));
    return false;
    return false;
  }

  public final int getDefaultPort()
  {
    return this.defaultPort;
  }

  public final String getName()
  {
    return this.name;
  }

  public final SchemeSocketFactory getSchemeSocketFactory()
  {
    return this.socketFactory;
  }

  @Deprecated
  public final SocketFactory getSocketFactory()
  {
    if ((this.socketFactory instanceof SchemeSocketFactoryAdaptor))
      return ((SchemeSocketFactoryAdaptor)this.socketFactory).getFactory();
    if (this.layered)
      return new LayeredSocketFactoryAdaptor((LayeredSchemeSocketFactory)this.socketFactory);
    return new SocketFactoryAdaptor(this.socketFactory);
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.defaultPort), this.name), this.layered);
  }

  public final boolean isLayered()
  {
    return this.layered;
  }

  public final int resolvePort(int paramInt)
  {
    if (paramInt <= 0)
      paramInt = this.defaultPort;
    return paramInt;
  }

  public final String toString()
  {
    if (this.stringRep == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.name);
      localStringBuilder.append(':');
      localStringBuilder.append(Integer.toString(this.defaultPort));
      this.stringRep = localStringBuilder.toString();
    }
    return this.stringRep;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.Scheme
 * JD-Core Version:    0.6.2
 */