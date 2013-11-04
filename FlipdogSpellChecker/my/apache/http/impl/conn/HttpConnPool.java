package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import my.apache.http.conn.ClientConnectionOperator;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.pool.AbstractConnPool;
import my.apache.http.pool.ConnFactory;
import org.apache.commons.logging.Log;

class HttpConnPool extends AbstractConnPool<HttpRoute, OperatedClientConnection, HttpPoolEntry>
{
  private static AtomicLong COUNTER = new AtomicLong();
  private final Log log;
  private final long timeToLive;
  private final TimeUnit tunit;

  public HttpConnPool(Log paramLog, ClientConnectionOperator paramClientConnectionOperator, int paramInt1, int paramInt2, long paramLong, TimeUnit paramTimeUnit)
  {
    super(new InternalConnFactory(paramClientConnectionOperator), paramInt1, paramInt2);
    this.log = paramLog;
    this.timeToLive = paramLong;
    this.tunit = paramTimeUnit;
  }

  protected HttpPoolEntry createEntry(HttpRoute paramHttpRoute, OperatedClientConnection paramOperatedClientConnection)
  {
    String str = Long.toString(COUNTER.getAndIncrement());
    return new HttpPoolEntry(this.log, str, paramHttpRoute, paramOperatedClientConnection, this.timeToLive, this.tunit);
  }

  static class InternalConnFactory
    implements ConnFactory<HttpRoute, OperatedClientConnection>
  {
    private final ClientConnectionOperator connOperator;

    InternalConnFactory(ClientConnectionOperator paramClientConnectionOperator)
    {
      this.connOperator = paramClientConnectionOperator;
    }

    public OperatedClientConnection create(HttpRoute paramHttpRoute)
      throws IOException
    {
      return this.connOperator.createConnection();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.HttpConnPool
 * JD-Core Version:    0.6.2
 */