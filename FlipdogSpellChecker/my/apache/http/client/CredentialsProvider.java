package my.apache.http.client;

import my.apache.http.auth.AuthScope;
import my.apache.http.auth.Credentials;

public abstract interface CredentialsProvider
{
  public abstract void clear();

  public abstract Credentials getCredentials(AuthScope paramAuthScope);

  public abstract void setCredentials(AuthScope paramAuthScope, Credentials paramCredentials);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.CredentialsProvider
 * JD-Core Version:    0.6.2
 */