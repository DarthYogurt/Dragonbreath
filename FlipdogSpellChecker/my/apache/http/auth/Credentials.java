package my.apache.http.auth;

import java.security.Principal;

public abstract interface Credentials
{
  public abstract String getPassword();

  public abstract Principal getUserPrincipal();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.Credentials
 * JD-Core Version:    0.6.2
 */