package my.apache.http.impl.pool;

import java.util.concurrent.atomic.AtomicLong;
import my.apache.http.HttpClientConnection;
import my.apache.http.HttpHost;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.params.HttpParams;
import my.apache.http.pool.AbstractConnPool;
import my.apache.http.pool.ConnFactory;

@ThreadSafe
public class BasicConnPool extends AbstractConnPool<HttpHost, HttpClientConnection, BasicPoolEntry>
{
  private static AtomicLong COUNTER = new AtomicLong();

  public BasicConnPool(HttpParams paramHttpParams)
  {
    super(new BasicConnFactory(paramHttpParams), 2, 20);
  }

  public BasicConnPool(ConnFactory<HttpHost, HttpClientConnection> paramConnFactory)
  {
    super(paramConnFactory, 2, 20);
  }

  protected BasicPoolEntry createEntry(HttpHost paramHttpHost, HttpClientConnection paramHttpClientConnection)
  {
    return new BasicPoolEntry(Long.toString(COUNTER.getAndIncrement()), paramHttpHost, paramHttpClientConnection);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.pool.BasicConnPool
 * JD-Core Version:    0.6.2
 */