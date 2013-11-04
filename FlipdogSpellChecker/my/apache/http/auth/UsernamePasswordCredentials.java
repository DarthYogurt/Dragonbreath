package my.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public class UsernamePasswordCredentials
  implements Credentials, Serializable
{
  private static final long serialVersionUID = 243343858802739403L;
  private final String password;
  private final BasicUserPrincipal principal;

  public UsernamePasswordCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    if (i >= 0)
    {
      this.principal = new BasicUserPrincipal(paramString.substring(0, i));
      this.password = paramString.substring(i + 1);
      return;
    }
    this.principal = new BasicUserPrincipal(paramString);
    this.password = null;
  }

  public UsernamePasswordCredentials(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Username may not be null");
    this.principal = new BasicUserPrincipal(paramString1);
    this.password = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    UsernamePasswordCredentials localUsernamePasswordCredentials;
    do
    {
      return true;
      if (!(paramObject instanceof UsernamePasswordCredentials))
        break;
      localUsernamePasswordCredentials = (UsernamePasswordCredentials)paramObject;
    }
    while (LangUtils.equals(this.principal, localUsernamePasswordCredentials.principal));
    return false;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserName()
  {
    return this.principal.getName();
  }

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public int hashCode()
  {
    return this.principal.hashCode();
  }

  public String toString()
  {
    return this.principal.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.UsernamePasswordCredentials
 * JD-Core Version:    0.6.2
 */