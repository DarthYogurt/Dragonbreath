package my.apache.http.impl.client;

import my.apache.http.annotation.Immutable;

@Immutable
public class TargetAuthenticationStrategy extends AuthenticationStrategyImpl
{
  public TargetAuthenticationStrategy()
  {
    super(401, "WWW-Authenticate", "http.auth.target-scheme-pref");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.TargetAuthenticationStrategy
 * JD-Core Version:    0.6.2
 */