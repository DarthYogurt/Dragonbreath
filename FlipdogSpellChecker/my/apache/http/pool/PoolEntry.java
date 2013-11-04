package my.apache.http.pool;

import java.util.concurrent.TimeUnit;
import my.apache.http.annotation.GuardedBy;
import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public abstract class PoolEntry<T, C>
{
  private final C conn;
  private final long created;

  @GuardedBy("this")
  private long expiry;
  private final String id;
  private final T route;
  private volatile Object state;

  @GuardedBy("this")
  private long updated;
  private final long validUnit;

  public PoolEntry(String paramString, T paramT, C paramC)
  {
    this(paramString, paramT, paramC, 0L, TimeUnit.MILLISECONDS);
  }

  public PoolEntry(String paramString, T paramT, C paramC, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramT == null)
      throw new IllegalArgumentException("Route may not be null");
    if (paramC == null)
      throw new IllegalArgumentException("Connection may not be null");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("Time unit may not be null");
    this.id = paramString;
    this.route = paramT;
    this.conn = paramC;
    this.created = System.currentTimeMillis();
    if (paramLong > 0L);
    for (this.validUnit = (this.created + paramTimeUnit.toMillis(paramLong)); ; this.validUnit = 9223372036854775807L)
    {
      this.expiry = this.validUnit;
      return;
    }
  }

  public abstract void close();

  public C getConnection()
  {
    return this.conn;
  }

  public long getCreated()
  {
    return this.created;
  }

  public long getExpiry()
  {
    try
    {
      long l = this.expiry;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getId()
  {
    return this.id;
  }

  public T getRoute()
  {
    return this.route;
  }

  public Object getState()
  {
    return this.state;
  }

  public long getUpdated()
  {
    try
    {
      long l = this.updated;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long getValidUnit()
  {
    return this.validUnit;
  }

  public abstract boolean isClosed();

  public boolean isExpired(long paramLong)
  {
    try
    {
      long l = this.expiry;
      if (paramLong >= l)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public void setState(Object paramObject)
  {
    this.state = paramObject;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id:");
    localStringBuilder.append(this.id);
    localStringBuilder.append("][route:");
    localStringBuilder.append(this.route);
    localStringBuilder.append("][state:");
    localStringBuilder.append(this.state);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  public void updateExpiry(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramTimeUnit == null)
      try
      {
        throw new IllegalArgumentException("Time unit may not be null");
      }
      finally
      {
      }
    this.updated = System.currentTimeMillis();
    if (paramLong > 0L);
    for (long l = this.updated + paramTimeUnit.toMillis(paramLong); ; l = 9223372036854775807L)
    {
      this.expiry = Math.min(l, this.validUnit);
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.PoolEntry
 * JD-Core Version:    0.6.2
 */