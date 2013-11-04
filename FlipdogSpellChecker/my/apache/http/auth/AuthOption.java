package my.apache.http.auth;

import my.apache.http.annotation.Immutable;

@Immutable
public final class AuthOption
{
  private final AuthScheme authScheme;
  private final Credentials creds;

  public AuthOption(AuthScheme paramAuthScheme, Credentials paramCredentials)
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Auth scheme may not be null");
    if (paramCredentials == null)
      throw new IllegalArgumentException("User credentials may not be null");
    this.authScheme = paramAuthScheme;
    this.creds = paramCredentials;
  }

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public Credentials getCredentials()
  {
    return this.creds;
  }

  public String toString()
  {
    return this.authScheme.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.AuthOption
 * JD-Core Version:    0.6.2
 */