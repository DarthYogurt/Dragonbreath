package my.apache.http.impl.pool;

import java.io.IOException;
import my.apache.http.HttpClientConnection;
import my.apache.http.HttpHost;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.pool.PoolEntry;

@ThreadSafe
public class BasicPoolEntry extends PoolEntry<HttpHost, HttpClientConnection>
{
  public BasicPoolEntry(String paramString, HttpHost paramHttpHost, HttpClientConnection paramHttpClientConnection)
  {
    super(paramString, paramHttpHost, paramHttpClientConnection);
  }

  public void close()
  {
    try
    {
      ((HttpClientConnection)getConnection()).close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public boolean isClosed()
  {
    return !((HttpClientConnection)getConnection()).isOpen();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.pool.BasicPoolEntry
 * JD-Core Version:    0.6.2
 */