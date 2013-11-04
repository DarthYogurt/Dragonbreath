package my.apache.http.impl.conn.tsccm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import my.apache.http.conn.OperatedClientConnection;
import my.apache.http.conn.params.ConnPerRoute;
import my.apache.http.conn.routing.HttpRoute;
import my.apache.http.util.LangUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Deprecated
public class RouteSpecificPool
{
  protected final ConnPerRoute connPerRoute;
  protected final LinkedList<BasicPoolEntry> freeEntries;
  private final Log log = LogFactory.getLog(getClass());
  protected final int maxEntries;
  protected int numEntries;
  protected final HttpRoute route;
  protected final Queue<WaitingThread> waitingThreads;

  public RouteSpecificPool(HttpRoute paramHttpRoute, int paramInt)
  {
    this.route = paramHttpRoute;
    this.maxEntries = paramInt;
    this.connPerRoute = new ConnPerRoute()
    {
      public int getMaxForRoute(HttpRoute paramAnonymousHttpRoute)
      {
        return RouteSpecificPool.this.maxEntries;
      }
    };
    this.freeEntries = new LinkedList();
    this.waitingThreads = new LinkedList();
    this.numEntries = 0;
  }

  public RouteSpecificPool(HttpRoute paramHttpRoute, ConnPerRoute paramConnPerRoute)
  {
    this.route = paramHttpRoute;
    this.connPerRoute = paramConnPerRoute;
    this.maxEntries = paramConnPerRoute.getMaxForRoute(paramHttpRoute);
    this.freeEntries = new LinkedList();
    this.waitingThreads = new LinkedList();
    this.numEntries = 0;
  }

  public BasicPoolEntry allocEntry(Object paramObject)
  {
    ListIterator localListIterator;
    if (!this.freeEntries.isEmpty())
      localListIterator = this.freeEntries.listIterator(this.freeEntries.size());
    while (true)
    {
      BasicPoolEntry localBasicPoolEntry1;
      OperatedClientConnection localOperatedClientConnection;
      if (!localListIterator.hasPrevious())
      {
        if ((getCapacity() == 0) && (!this.freeEntries.isEmpty()))
        {
          localBasicPoolEntry1 = (BasicPoolEntry)this.freeEntries.remove();
          localBasicPoolEntry1.shutdownEntry();
          localOperatedClientConnection = localBasicPoolEntry1.getConnection();
        }
      }
      else
        try
        {
          localOperatedClientConnection.close();
          return localBasicPoolEntry1;
          BasicPoolEntry localBasicPoolEntry2 = (BasicPoolEntry)localListIterator.previous();
          if ((localBasicPoolEntry2.getState() == null) || (LangUtils.equals(paramObject, localBasicPoolEntry2.getState())))
          {
            localListIterator.remove();
            return localBasicPoolEntry2;
          }
        }
        catch (IOException localIOException)
        {
          this.log.debug("I/O error closing connection", localIOException);
          return localBasicPoolEntry1;
        }
    }
    return null;
  }

  public void createdEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    if (!this.route.equals(paramBasicPoolEntry.getPlannedRoute()))
      throw new IllegalArgumentException("Entry not planned for this pool.\npool: " + this.route + "\nplan: " + paramBasicPoolEntry.getPlannedRoute());
    this.numEntries = (1 + this.numEntries);
  }

  public boolean deleteEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    boolean bool = this.freeEntries.remove(paramBasicPoolEntry);
    if (bool)
      this.numEntries = (-1 + this.numEntries);
    return bool;
  }

  public void dropEntry()
  {
    if (this.numEntries < 1)
      throw new IllegalStateException("There is no entry that could be dropped.");
    this.numEntries = (-1 + this.numEntries);
  }

  public void freeEntry(BasicPoolEntry paramBasicPoolEntry)
  {
    if (this.numEntries < 1)
      throw new IllegalStateException("No entry created for this pool. " + this.route);
    if (this.numEntries <= this.freeEntries.size())
      throw new IllegalStateException("No entry allocated from this pool. " + this.route);
    this.freeEntries.add(paramBasicPoolEntry);
  }

  public int getCapacity()
  {
    return this.connPerRoute.getMaxForRoute(this.route) - this.numEntries;
  }

  public final int getEntryCount()
  {
    return this.numEntries;
  }

  public final int getMaxEntries()
  {
    return this.maxEntries;
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }

  public boolean hasThread()
  {
    return !this.waitingThreads.isEmpty();
  }

  public boolean isUnused()
  {
    return (this.numEntries < 1) && (this.waitingThreads.isEmpty());
  }

  public WaitingThread nextThread()
  {
    return (WaitingThread)this.waitingThreads.peek();
  }

  public void queueThread(WaitingThread paramWaitingThread)
  {
    if (paramWaitingThread == null)
      throw new IllegalArgumentException("Waiting thread must not be null.");
    this.waitingThreads.add(paramWaitingThread);
  }

  public void removeThread(WaitingThread paramWaitingThread)
  {
    if (paramWaitingThread == null)
      return;
    this.waitingThreads.remove(paramWaitingThread);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.RouteSpecificPool
 * JD-Core Version:    0.6.2
 */