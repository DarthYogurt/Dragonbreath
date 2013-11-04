package my.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;
import my.apache.http.annotation.Immutable;
import my.apache.http.util.LangUtils;

@Immutable
public class NTUserPrincipal
  implements Principal, Serializable
{
  private static final long serialVersionUID = -6870169797924406894L;
  private final String domain;
  private final String ntname;
  private final String username;

  public NTUserPrincipal(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      throw new IllegalArgumentException("User name may not be null");
    this.username = paramString2;
    if (paramString1 != null);
    for (this.domain = paramString1.toUpperCase(Locale.ENGLISH); (this.domain != null) && (this.domain.length() > 0); this.domain = null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.domain);
      localStringBuilder.append('/');
      localStringBuilder.append(this.username);
      this.ntname = localStringBuilder.toString();
      return;
    }
    this.ntname = this.username;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    NTUserPrincipal localNTUserPrincipal;
    do
    {
      return true;
      if (!(paramObject instanceof NTUserPrincipal))
        break;
      localNTUserPrincipal = (NTUserPrincipal)paramObject;
    }
    while ((LangUtils.equals(this.username, localNTUserPrincipal.username)) && (LangUtils.equals(this.domain, localNTUserPrincipal.domain)));
    return false;
  }

  public String getDomain()
  {
    return this.domain;
  }

  public String getName()
  {
    return this.ntname;
  }

  public String getUsername()
  {
    return this.username;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.username), this.domain);
  }

  public String toString()
  {
    return this.ntname;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.NTUserPrincipal
 * JD-Core Version:    0.6.2
 */