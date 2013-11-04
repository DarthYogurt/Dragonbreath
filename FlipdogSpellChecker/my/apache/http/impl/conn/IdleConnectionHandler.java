package my.apache.http.impl.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import my.apache.http.HttpConnection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class IdleConnectionHandler
{
  private final Map<HttpConnection, TimeValues> connectionToTimes = new HashMap();
  private final Log log = LogFactory.getLog(getClass());

  public void add(HttpConnection paramHttpConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    long l = System.currentTimeMillis();
    if (this.log.isDebugEnabled())
      this.log.debug("Adding connection at: " + l);
    this.connectionToTimes.put(paramHttpConnection, new TimeValues(l, paramLong, paramTimeUnit));
  }

  public void closeExpiredConnections()
  {
    long l = System.currentTimeMillis();
    if (this.log.isDebugEnabled())
      this.log.debug("Checking for expired connections, now: " + l);
    Iterator localIterator = this.connectionToTimes.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      HttpConnection localHttpConnection = (HttpConnection)localEntry.getKey();
      TimeValues localTimeValues = (TimeValues)localEntry.getValue();
      if (localTimeValues.timeExpires <= l)
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Closing connection, expired @: " + localTimeValues.timeExpires);
        try
        {
          localHttpConnection.close();
        }
        catch (IOException localIOException)
        {
          this.log.debug("I/O error closing connection", localIOException);
        }
      }
    }
  }

  public void closeIdleConnections(long paramLong)
  {
    long l1 = System.currentTimeMillis() - paramLong;
    if (this.log.isDebugEnabled())
      this.log.debug("Checking for connections, idle timeout: " + l1);
    Iterator localIterator = this.connectionToTimes.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      HttpConnection localHttpConnection = (HttpConnection)localEntry.getKey();
      long l2 = ((TimeValues)localEntry.getValue()).timeAdded;
      if (l2 <= l1)
      {
        if (this.log.isDebugEnabled())
          this.log.debug("Closing idle connection, connection time: " + l2);
        try
        {
          localHttpConnection.close();
        }
        catch (IOException localIOException)
        {
          this.log.debug("I/O error closing connection", localIOException);
        }
      }
    }
  }

  public boolean remove(HttpConnection paramHttpConnection)
  {
    TimeValues localTimeValues = (TimeValues)this.connectionToTimes.remove(paramHttpConnection);
    if (localTimeValues == null)
      this.log.warn("Removing a connection that never existed!");
    while (System.currentTimeMillis() <= localTimeValues.timeExpires)
      return true;
    return false;
  }

  public void removeAll()
  {
    this.connectionToTimes.clear();
  }

  private static class TimeValues
  {
    private final long timeAdded;
    private final long timeExpires;

    TimeValues(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.timeAdded = paramLong1;
      if (paramLong2 > 0L)
      {
        this.timeExpires = (paramLong1 + paramTimeUnit.toMillis(paramLong2));
        return;
      }
      this.timeExpires = 9223372036854775807L;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.IdleConnectionHandler
 * JD-Core Version:    0.6.2
 */