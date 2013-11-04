package my.apache.http.impl.client;

import my.apache.http.annotation.Immutable;

@Immutable
public class ProxyAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public ProxyAuthenticationStrategy()
  {
    super(407, "Proxy-Authenticate", "http.auth.proxy-scheme-pref");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.ProxyAuthenticationStrategy
 * JD-Core Version:    0.6.2
 */