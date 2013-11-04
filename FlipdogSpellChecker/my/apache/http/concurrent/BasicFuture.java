package my.apache.http.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicFuture<T>
  implements Future<T>, Cancellable
{
  private final FutureCallback<T> callback;
  private volatile boolean cancelled;
  private volatile boolean completed;
  private volatile Exception ex;
  private volatile T result;

  public BasicFuture(FutureCallback<T> paramFutureCallback)
  {
    this.callback = paramFutureCallback;
  }

  private T getResult()
    throws ExecutionException
  {
    if (this.ex != null)
      throw new ExecutionException(this.ex);
    return this.result;
  }

  public boolean cancel()
  {
    return cancel(true);
  }

  public boolean cancel(boolean paramBoolean)
  {
    try
    {
      if (this.completed)
        return false;
      this.completed = true;
      this.cancelled = true;
      notifyAll();
      if (this.callback != null)
      {
        this.callback.cancelled();
        return true;
      }
    }
    finally
    {
    }
    return true;
  }

  public boolean completed(T paramT)
  {
    try
    {
      if (this.completed)
        return false;
      this.completed = true;
      this.result = paramT;
      notifyAll();
      if (this.callback != null)
      {
        this.callback.completed(paramT);
        return true;
      }
    }
    finally
    {
    }
    return true;
  }

  public boolean failed(Exception paramException)
  {
    try
    {
      if (this.completed)
        return false;
      this.completed = true;
      this.ex = paramException;
      notifyAll();
      if (this.callback != null)
      {
        this.callback.failed(paramException);
        return true;
      }
    }
    finally
    {
    }
    return true;
  }

  public T get()
    throws InterruptedException, ExecutionException
  {
    try
    {
      while (true)
      {
        if (this.completed)
        {
          Object localObject2 = getResult();
          return localObject2;
        }
        wait();
      }
    }
    finally
    {
    }
  }

  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    long l1;
    long l2;
    long l3;
    Object localObject2;
    try
    {
      l1 = paramTimeUnit.toMillis(paramLong);
      if (l1 <= 0L);
      for (l2 = 0L; ; l2 = System.currentTimeMillis())
      {
        l3 = l1;
        if (!this.completed)
          break;
        Object localObject3 = getResult();
        localObject2 = localObject3;
        return localObject2;
      }
      if (l3 <= 0L)
        throw new TimeoutException();
    }
    finally
    {
    }
    do
    {
      wait(l3);
      if (this.completed)
      {
        localObject2 = getResult();
        break;
      }
      l3 = l1 - (System.currentTimeMillis() - l2);
    }
    while (l3 > 0L);
    throw new TimeoutException();
  }

  public boolean isCancelled()
  {
    return this.cancelled;
  }

  public boolean isDone()
  {
    return this.completed;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.concurrent.BasicFuture
 * JD-Core Version:    0.6.2
 */