package my.apache.http.impl.auth;

import my.apache.http.auth.AuthScheme;
import my.apache.http.auth.AuthSchemeFactory;
import my.apache.http.params.HttpParams;

@Deprecated
public class NegotiateSchemeFactory
  implements AuthSchemeFactory
{
  private final SpnegoTokenGenerator spengoGenerator;
  private final boolean stripPort;

  public NegotiateSchemeFactory()
  {
    this(null, false);
  }

  public NegotiateSchemeFactory(SpnegoTokenGenerator paramSpnegoTokenGenerator)
  {
    this(paramSpnegoTokenGenerator, false);
  }

  public NegotiateSchemeFactory(SpnegoTokenGenerator paramSpnegoTokenGenerator, boolean paramBoolean)
  {
    this.spengoGenerator = paramSpnegoTokenGenerator;
    this.stripPort = paramBoolean;
  }

  public SpnegoTokenGenerator getSpengoGenerator()
  {
    return this.spengoGenerator;
  }

  public boolean isStripPort()
  {
    return this.stripPort;
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new NegotiateScheme(this.spengoGenerator, this.stripPort);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.NegotiateSchemeFactory
 * JD-Core Version:    0.6.2
 */