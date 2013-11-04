package my.apache.http.client.params;

import my.apache.http.annotation.Immutable;

@Immutable
public final class AuthPolicy
{
  public static final String BASIC = "Basic";
  public static final String DIGEST = "Digest";
  public static final String KERBEROS = "Kerberos";
  public static final String NTLM = "NTLM";
  public static final String SPNEGO = "negotiate";
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.params.AuthPolicy
 * JD-Core Version:    0.6.2
 */