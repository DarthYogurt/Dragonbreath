package my.apache.http.impl.conn.tsccm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import my.apache.http.conn.routing.HttpRoute;

@Deprecated
public class BasicPoolEntryRef extends WeakReference<BasicPoolEntry>
{
  private final HttpRoute route;

  public BasicPoolEntryRef(BasicPoolEntry paramBasicPoolEntry, ReferenceQueue<Object> paramReferenceQueue)
  {
    super(paramBasicPoolEntry, paramReferenceQueue);
    if (paramBasicPoolEntry == null)
      throw new IllegalArgumentException("Pool entry must not be null.");
    this.route = paramBasicPoolEntry.getPlannedRoute();
  }

  public final HttpRoute getRoute()
  {
    return this.route;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.conn.tsccm.BasicPoolEntryRef
 * JD-Core Version:    0.6.2
 */