package my.apache.http.impl.auth;

import my.apache.http.Header;
import my.apache.http.HttpRequest;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.Credentials;
import my.apache.http.protocol.HttpContext;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

public class SPNegoScheme extends GGSSchemeBase
{
  private static final String SPNEGO_OID = "1.3.6.1.5.5.2";

  public SPNegoScheme()
  {
    super(false);
  }

  public SPNegoScheme(boolean paramBoolean)
  {
    super(paramBoolean);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return super.authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
  }

  protected byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws GSSException
  {
    return generateGSSToken(paramArrayOfByte, new Oid("1.3.6.1.5.5.2"), paramString);
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    return null;
  }

  public String getRealm()
  {
    return null;
  }

  public String getSchemeName()
  {
    return "Negotiate";
  }

  public boolean isConnectionBased()
  {
    return true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.SPNegoScheme
 * JD-Core Version:    0.6.2
 */