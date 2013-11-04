package my.apache.http.pool;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.concurrent.FutureCallback;

@ThreadSafe
abstract class PoolEntryFuture<T>
  implements Future<T>
{
  private final FutureCallback<T> callback;
  private volatile boolean cancelled;
  private volatile boolean completed;
  private final Condition condition;
  private final Lock lock;
  private T result;

  PoolEntryFuture(Lock paramLock, FutureCallback<T> paramFutureCallback)
  {
    this.lock = paramLock;
    this.condition = paramLock.newCondition();
    this.callback = paramFutureCallback;
  }

  public boolean await(Date paramDate)
    throws InterruptedException
  {
    this.lock.lock();
    try
    {
      if (this.cancelled)
        throw new InterruptedException("Operation interrupted");
    }
    finally
    {
      this.lock.unlock();
    }
    if (paramDate != null);
    for (boolean bool = this.condition.awaitUntil(paramDate); this.cancelled; bool = true)
    {
      throw new InterruptedException("Operation interrupted");
      this.condition.await();
    }
    this.lock.unlock();
    return bool;
  }

  public boolean cancel(boolean paramBoolean)
  {
    this.lock.lock();
    try
    {
      boolean bool = this.completed;
      if (bool)
        return false;
      this.completed = true;
      this.cancelled = true;
      if (this.callback != null)
        this.callback.cancelled();
      this.condition.signalAll();
      return true;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      Object localObject = get(0L, TimeUnit.MILLISECONDS);
      return localObject;
    }
    catch (TimeoutException localTimeoutException)
    {
      throw new ExecutionException(localTimeoutException);
    }
  }

  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    this.lock.lock();
    try
    {
      if (this.completed)
      {
        Object localObject3 = this.result;
        return localObject3;
      }
      this.result = getPoolEntry(paramLong, paramTimeUnit);
      this.completed = true;
      if (this.callback != null)
        this.callback.completed(this.result);
      Object localObject2 = this.result;
      return localObject2;
    }
    catch (IOException localIOException)
    {
      this.completed = true;
      this.result = null;
      if (this.callback != null)
        this.callback.failed(localIOException);
      throw new ExecutionException(localIOException);
    }
    finally
    {
      this.lock.unlock();
    }
  }

  protected abstract T getPoolEntry(long paramLong, TimeUnit paramTimeUnit)
    throws IOException, InterruptedException, TimeoutException;

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public boolean isDone()
  {
    return this.completed;
  }

  public void wakeup()
  {
    this.lock.lock();
    try
    {
      this.condition.signalAll();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.pool.PoolEntryFuture
 * JD-Core Version:    0.6.2
 */