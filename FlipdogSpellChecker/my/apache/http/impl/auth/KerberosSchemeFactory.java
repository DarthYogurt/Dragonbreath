package my.apache.http.impl.auth;

import my.apache.http.annotation.Immutable;
import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthSchemeFactory;
import my.apache.http.params.HttpParams;

@Immutable
public class KerberosSchemeFactory
  implements AuthSchemeFactory
{
  private final boolean stripPort;

  public KerberosSchemeFactory()
  {
    this(false);
  }

  public KerberosSchemeFactory(boolean paramBoolean)
  {
    this.stripPort = paramBoolean;
  }

  public boolean isStripPort()
  {
    return this.stripPort;
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new KerberosScheme(this.stripPort);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.KerberosSchemeFactory
 * JD-Core Version:    0.6.2
 */