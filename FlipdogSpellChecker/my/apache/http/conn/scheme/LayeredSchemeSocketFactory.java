package my.apache.http.conn.scheme;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@Deprecated
public abstract interface LayeredSchemeSocketFactory extends SchemeSocketFactory
{
  public abstract Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.LayeredSchemeSocketFactory
 * JD-Core Version:    0.6.2
 */