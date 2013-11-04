package my.apache.http.conn.ssl;

import javax.net.ssl.SSLException;
import my.apache.http.annotation.Immutable;

@Immutable
public class StrictHostnameVerifier extends AbstractVerifier
{
  public final String toString()
  {
    return "STRICT";
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SSLException
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ssl.StrictHostnameVerifier
 * JD-Core Version:    0.6.2
 */