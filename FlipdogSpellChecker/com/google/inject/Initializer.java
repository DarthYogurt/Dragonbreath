package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Maps;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.InjectionPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

class Initializer
{
  private final Thread creatingThread = Thread.currentThread();
  private final Map<Object, InjectableReference<?>> pendingInjection = Maps.newIdentityHashMap();
  private final CountDownLatch ready = new CountDownLatch(1);

  void injectAll(Errors paramErrors)
  {
    Iterator localIterator = Lists.newArrayList(this.pendingInjection.values()).iterator();
    while (localIterator.hasNext())
    {
      InjectableReference localInjectableReference = (InjectableReference)localIterator.next();
      try
      {
        localInjectableReference.get(paramErrors);
      }
      catch (ErrorsException localErrorsException)
      {
        paramErrors.merge(localErrorsException.getErrors());
      }
    }
    if (!this.pendingInjection.isEmpty())
      throw new AssertionError("Failed to satisfy " + this.pendingInjection);
    this.ready.countDown();
  }

  public <T> Initializable<T> requestInjection(InjectorImpl paramInjectorImpl, T paramT, Object paramObject, Set<InjectionPoint> paramSet)
  {
    Preconditions.checkNotNull(paramObject);
    if ((paramT == null) || ((paramSet.isEmpty()) && (!paramInjectorImpl.membersInjectorStore.hasTypeListeners())))
      return Initializables.of(paramT);
    InjectableReference localInjectableReference = new InjectableReference(paramInjectorImpl, paramT, paramObject);
    this.pendingInjection.put(paramT, localInjectableReference);
    return localInjectableReference;
  }

  void validateOustandingInjections(Errors paramErrors)
  {
    Iterator localIterator = this.pendingInjection.values().iterator();
    while (localIterator.hasNext())
    {
      InjectableReference localInjectableReference = (InjectableReference)localIterator.next();
      try
      {
        localInjectableReference.validate(paramErrors);
      }
      catch (ErrorsException localErrorsException)
      {
        paramErrors.merge(localErrorsException.getErrors());
      }
    }
  }

  private class InjectableReference<T>
    implements Initializable<T>
  {
    private final InjectorImpl injector;
    private final T instance;
    private MembersInjectorImpl<T> membersInjector;
    private final Object source;

    public InjectableReference(T paramObject, Object arg3)
    {
      this.injector = paramObject;
      Object localObject1;
      this.instance = Preconditions.checkNotNull(localObject1, "instance");
      Object localObject2;
      this.source = Preconditions.checkNotNull(localObject2, "source");
    }

    public T get(Errors paramErrors)
      throws ErrorsException
    {
      if (Initializer.this.ready.getCount() == 0L)
        return this.instance;
      if (Thread.currentThread() != Initializer.this.creatingThread)
        try
        {
          Initializer.this.ready.await();
          Object localObject = this.instance;
          return localObject;
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new RuntimeException(localInterruptedException);
        }
      if (Initializer.this.pendingInjection.remove(this.instance) != null)
        this.membersInjector.injectAndNotify(this.instance, paramErrors.withSource(this.source));
      return this.instance;
    }

    public String toString()
    {
      return this.instance.toString();
    }

    public void validate(Errors paramErrors)
      throws ErrorsException
    {
      TypeLiteral localTypeLiteral = TypeLiteral.get(this.instance.getClass());
      this.membersInjector = this.injector.membersInjectorStore.get(localTypeLiteral, paramErrors.withSource(this.source));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Initializer
 * JD-Core Version:    0.6.2
 */