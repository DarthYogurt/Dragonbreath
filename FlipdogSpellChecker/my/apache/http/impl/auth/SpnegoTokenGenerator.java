package my.apache.http.impl.auth;

import java.io.IOException;

@Deprecated
public abstract interface SpnegoTokenGenerator
{
  public abstract byte[] generateSpnegoDERObject(byte[] paramArrayOfByte)
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.auth.SpnegoTokenGenerator
 * JD-Core Version:    0.6.2
 */