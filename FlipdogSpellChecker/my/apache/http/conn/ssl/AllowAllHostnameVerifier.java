package my.apache.http.conn.ssl;

import my.apache.http.annotation.Immutable;

@Immutable
public class AllowAllHostnameVerifier extends AbstractVerifier
{
  public final String toString()
  {
    return "ALLOW_ALL";
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ssl.AllowAllHostnameVerifier
 * JD-Core Version:    0.6.2
 */