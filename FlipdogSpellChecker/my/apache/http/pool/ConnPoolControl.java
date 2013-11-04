package my.apache.http.pool;

public abstract interface ConnPoolControl<T>
{
  public abstract int getDefaultMaxPerRoute();

  public abstract int getMaxPerRoute(T paramT);

  public abstract int getMaxTotal();

  public abstract PoolStats getStats(T paramT);

  public abstract PoolStats getTotalStats();

  public abstract void setDefaultMaxPerRoute(int paramInt);

  public abstract void setMaxPerRoute(T paramT, int paramInt);

  public abstract void setMaxTotal(int paramInt);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.ConnPoolControl
 * JD-Core Version:    0.6.2
 */