package my.apache.http.pool;

import java.util.concurrent.Future;
import my.apache.http.concurrent.FutureCallback;

public abstract interface ConnPool<T, E>
{
  public abstract Future<E> lease(T paramT, Object paramObject, FutureCallback<E> paramFutureCallback);

  public abstract void release(E paramE, boolean paramBoolean);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.ConnPool
 * JD-Core Version:    0.6.2
 */