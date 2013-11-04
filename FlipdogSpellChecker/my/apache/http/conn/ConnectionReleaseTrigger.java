package my.apache.http.conn;

import java.io.IOException;

public abstract interface ConnectionReleaseTrigger
{
  public abstract void abortConnection()
    throws IOException;

  public abstract void releaseConnection()
    throws IOException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.ConnectionReleaseTrigger
 * JD-Core Version:    0.6.2
 */