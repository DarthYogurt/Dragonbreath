package my.apache.http.impl.auth;

import my.apache.http.Header;
import my.apache.http.HttpRequest;
import my.apache.http.auth.AuthenticationException;
import my.apache.http.auth.Credentials;
import my.apache.http.protocol.HttpContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class NegotiateScheme extends GGSSchemeBase
{
  private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";
  private static final String SPNEGO_OID = "1.3.6.1.5.5.2";
  private final Log log = LogFactory.getLog(getClass());
  private final SpnegoTokenGenerator spengoGenerator;

  public NegotiateScheme()
  {
    this(null, false);
  }

  public NegotiateScheme(SpnegoTokenGenerator paramSpnegoTokenGenerator)
  {
    this(paramSpnegoTokenGenerator, false);
  }

  public NegotiateScheme(SpnegoTokenGenerator paramSpnegoTokenGenerator, boolean paramBoolean)
  {
    super(paramBoolean);
    this.spengoGenerator = paramSpnegoTokenGenerator;
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest, null);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return super.authenticate(paramCredentials, paramHttpRequest, paramHttpContext);
  }

  // ERROR //
  protected byte[] generateToken(byte[] paramArrayOfByte, String paramString)
    throws org.ietf.jgss.GSSException
  {
    // Byte code:
    //   0: new 57	org/ietf/jgss/Oid
    //   3: dup
    //   4: ldc 12
    //   6: invokespecial 60	org/ietf/jgss/Oid:<init>	(Ljava/lang/String;)V
    //   9: astore_3
    //   10: aload_1
    //   11: astore 4
    //   13: iconst_0
    //   14: istore 5
    //   16: aload_0
    //   17: aload 4
    //   19: aload_3
    //   20: aload_2
    //   21: invokevirtual 64	my/apache/http/impl/auth/NegotiateScheme:generateGSSToken	([BLorg/ietf/jgss/Oid;Ljava/lang/String;)[B
    //   24: astore 9
    //   26: aload 9
    //   28: astore 4
    //   30: iload 5
    //   32: ifeq +61 -> 93
    //   35: aload_0
    //   36: getfield 39	my/apache/http/impl/auth/NegotiateScheme:log	Lorg/apache/commons/logging/Log;
    //   39: ldc 66
    //   41: invokeinterface 72 2 0
    //   46: aload_0
    //   47: aload 4
    //   49: new 57	org/ietf/jgss/Oid
    //   52: dup
    //   53: ldc 9
    //   55: invokespecial 60	org/ietf/jgss/Oid:<init>	(Ljava/lang/String;)V
    //   58: aload_2
    //   59: invokevirtual 64	my/apache/http/impl/auth/NegotiateScheme:generateGSSToken	([BLorg/ietf/jgss/Oid;Ljava/lang/String;)[B
    //   62: astore 4
    //   64: aload 4
    //   66: ifnull +27 -> 93
    //   69: aload_0
    //   70: getfield 41	my/apache/http/impl/auth/NegotiateScheme:spengoGenerator	Lmy/apache/http/impl/auth/SpnegoTokenGenerator;
    //   73: ifnull +20 -> 93
    //   76: aload_0
    //   77: getfield 41	my/apache/http/impl/auth/NegotiateScheme:spengoGenerator	Lmy/apache/http/impl/auth/SpnegoTokenGenerator;
    //   80: aload 4
    //   82: invokeinterface 78 2 0
    //   87: astore 8
    //   89: aload 8
    //   91: astore 4
    //   93: aload 4
    //   95: areturn
    //   96: astore 6
    //   98: aload 6
    //   100: invokevirtual 82	org/ietf/jgss/GSSException:getMajor	()I
    //   103: iconst_2
    //   104: if_icmpne +20 -> 124
    //   107: aload_0
    //   108: getfield 39	my/apache/http/impl/auth/NegotiateScheme:log	Lorg/apache/commons/logging/Log;
    //   111: ldc 84
    //   113: invokeinterface 72 2 0
    //   118: iconst_1
    //   119: istore 5
    //   121: goto -91 -> 30
    //   124: aload 6
    //   126: athrow
    //   127: astore 7
    //   129: aload_0
    //   130: getfield 39	my/apache/http/impl/auth/NegotiateScheme:log	Lorg/apache/commons/logging/Log;
    //   133: aload 7
    //   135: invokevirtual 88	java/io/IOException:getMessage	()Ljava/lang/String;
    //   138: aload 7
    //   140: invokeinterface 92 3 0
    //   145: aload 4
    //   147: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   16	26	96	org/ietf/jgss/GSSException
    //   76	89	127	java/io/IOException
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
 * Qualified Name:     my.apache.http.impl.auth.NegotiateScheme
 * JD-Core Version:    0.6.2
 */