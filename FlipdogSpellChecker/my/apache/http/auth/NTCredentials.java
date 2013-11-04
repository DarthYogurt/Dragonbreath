package my.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public class NTCredentials
  implements Credentials, Serializable
{
  private static final long serialVersionUID = -7385699315228907265L;
  private final String password;
  private final NTUserPrincipal principal;
  private final String workstation;

  public NTCredentials(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Username:password string may not be null");
    int i = paramString.indexOf(':');
    String str;
    int j;
    if (i >= 0)
    {
      str = paramString.substring(0, i);
      this.password = paramString.substring(i + 1);
      j = str.indexOf('/');
      if (j < 0)
        break label108;
    }
    label108: for (this.principal = new NTUserPrincipal(str.substring(0, j).toUpperCase(Locale.ENGLISH), str.substring(j + 1)); ; this.principal = new NTUserPrincipal(null, str.substring(j + 1)))
    {
      this.workstation = null;
      return;
      str = paramString;
      this.password = null;
      break;
    }
  }

  public NTCredentials(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("User name may not be null");
    this.principal = new NTUserPrincipal(paramString4, paramString1);
    this.password = paramString2;
    if (paramString3 != null)
    {
      this.workstation = paramString3.toUpperCase(Locale.ENGLISH);
      return;
    }
    this.workstation = null;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    NTCredentials localNTCredentials;
    do
    {
      return true;
      if (!(paramObject instanceof NTCredentials))
        break;
      localNTCredentials = (NTCredentials)paramObject;
    }
    while ((LangUtils.equals(this.principal, localNTCredentials.principal)) && (LangUtils.equals(this.workstation, localNTCredentials.workstation)));
    return false;
  }

  public String getDomain()
  {
    return this.principal.getDomain();
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserName()
  {
    return this.principal.getUsername();
  }

  public Principal getUserPrincipal()
  {
    return this.principal;
  }

  public String getWorkstation()
  {
    return this.workstation;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.principal), this.workstation);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[principal: ");
    localStringBuilder.append(this.principal);
    localStringBuilder.append("][workstation: ");
    localStringBuilder.append(this.workstation);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.NTCredentials
 * JD-Core Version:    0.6.2
 */