package my.apache.http.pool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
abstract class RouteSpecificPool<T, C, E extends PoolEntry<T, C>>
{
  private final LinkedList<E> available;
  private final Set<E> leased;
  private final LinkedList<PoolEntryFuture<E>> pending;
  private final T route;

  RouteSpecificPool(T paramT)
  {
    this.route = paramT;
    this.leased = new HashSet();
    this.available = new LinkedList();
    this.pending = new LinkedList();
  }

  public E add(C paramC)
  {
    PoolEntry localPoolEntry = createEntry(paramC);
    this.leased.add(localPoolEntry);
    return localPoolEntry;
  }

  protected abstract E createEntry(C paramC);

  public void free(E paramE, boolean paramBoolean)
  {
    if (paramE == null)
      throw new IllegalArgumentException("Pool entry may not be null");
    if (!this.leased.remove(paramE))
      throw new IllegalStateException("Entry " + paramE + " has not been leased from this pool");
    if (paramBoolean)
      this.available.addFirst(paramE);
  }

  public int getAllocatedCount()
  {
    return this.available.size() + this.leased.size();
  }

  public int getAvailableCount()
  {
    return this.available.size();
  }

  public E getFree(Object paramObject)
  {
    Iterator localIterator2;
    Iterator localIterator1;
    if (!this.available.isEmpty())
    {
      if (paramObject != null)
      {
        localIterator2 = this.available.iterator();
        if (localIterator2.hasNext())
          break label52;
      }
      localIterator1 = this.available.iterator();
    }
    label52: PoolEntry localPoolEntry1;
    do
    {
      if (!localIterator1.hasNext())
      {
        return null;
        PoolEntry localPoolEntry2 = (PoolEntry)localIterator2.next();
        if (!paramObject.equals(localPoolEntry2.getState()))
          break;
        localIterator2.remove();
        this.leased.add(localPoolEntry2);
        return localPoolEntry2;
      }
      localPoolEntry1 = (PoolEntry)localIterator1.next();
    }
    while (localPoolEntry1.getState() != null);
    localIterator1.remove();
    this.leased.add(localPoolEntry1);
    return localPoolEntry1;
  }

  public E getLastUsed()
  {
    if (!this.available.isEmpty())
      return (PoolEntry)this.available.getLast();
    return null;
  }

  public int getLeasedCount()
  {
    return this.leased.size();
  }

  public int getPendingCount()
  {
    return this.pending.size();
  }

  public final T getRoute()
  {
    return this.route;
  }

  public PoolEntryFuture<E> nextPending()
  {
    return (PoolEntryFuture)this.pending.poll();
  }

  public void queue(PoolEntryFuture<E> paramPoolEntryFuture)
  {
    if (paramPoolEntryFuture == null)
      return;
    this.pending.add(paramPoolEntryFuture);
  }

  public boolean remove(E paramE)
  {
    if (paramE == null)
      throw new IllegalArgumentException("Pool entry may not be null");
    return (this.available.remove(paramE)) || (this.leased.remove(paramE));
  }

  public void shutdown()
  {
    Iterator localIterator1 = this.pending.iterator();
    Iterator localIterator2;
    label32: Iterator localIterator3;
    if (!localIterator1.hasNext())
    {
      this.pending.clear();
      localIterator2 = this.available.iterator();
      if (localIterator2.hasNext())
        break label96;
      this.available.clear();
      localIterator3 = this.leased.iterator();
    }
    while (true)
    {
      if (!localIterator3.hasNext())
      {
        this.leased.clear();
        return;
        ((PoolEntryFuture)localIterator1.next()).cancel(true);
        break;
        label96: ((PoolEntry)localIterator2.next()).close();
        break label32;
      }
      ((PoolEntry)localIterator3.next()).close();
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[route: ");
    localStringBuilder.append(this.route);
    localStringBuilder.append("][leased: ");
    localStringBuilder.append(this.leased.size());
    localStringBuilder.append("][available: ");
    localStringBuilder.append(this.available.size());
    localStringBuilder.append("][pending: ");
    localStringBuilder.append(this.pending.size());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  public void unqueue(PoolEntryFuture<E> paramPoolEntryFuture)
  {
    if (paramPoolEntryFuture == null)
      return;
    this.pending.remove(paramPoolEntryFuture);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.RouteSpecificPool
 * JD-Core Version:    0.6.2
 */