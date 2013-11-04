package my.apache.http.conn.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class TrustManagerDecorator
  implements X509TrustManager
{
  private final X509TrustManager trustManager;
  private final TrustStrategy trustStrategy;

  TrustManagerDecorator(X509TrustManager paramX509TrustManager, TrustStrategy paramTrustStrategy)
  {
    this.trustManager = paramX509TrustManager;
    this.trustStrategy = paramTrustStrategy;
  }

  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    this.trustManager.checkClientTrusted(paramArrayOfX509Certificate, paramString);
  }

  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    if (!this.trustStrategy.isTrusted(paramArrayOfX509Certificate, paramString))
      this.trustManager.checkServerTrusted(paramArrayOfX509Certificate, paramString);
  }

  public X509Certificate[] getAcceptedIssuers()
  {
    return this.trustManager.getAcceptedIssuers();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ssl.TrustManagerDecorator
 * JD-Core Version:    0.6.2
 */