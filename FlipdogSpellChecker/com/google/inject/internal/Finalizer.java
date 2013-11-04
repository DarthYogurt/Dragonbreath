package com.google.inject.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Finalizer extends Thread
{
  private static final String FINALIZABLE_REFERENCE = "com.google.inject.internal.FinalizableReference";
  private static final Logger logger = Logger.getLogger(Finalizer.class.getName());
  private final WeakReference<Class<?>> finalizableReferenceClassReference;
  private final PhantomReference<Object> frqReference;
  private final ReferenceQueue<Object> queue = new ReferenceQueue();

  private Finalizer(Class<?> paramClass, Object paramObject)
  {
    super(Finalizer.class.getName());
    this.finalizableReferenceClassReference = new WeakReference(paramClass);
    this.frqReference = new PhantomReference(paramObject, this.queue);
    setDaemon(true);
  }

  private void cleanUp(Reference<?> paramReference)
    throws Finalizer.ShutDown
  {
    Method localMethod = getFinalizeReferentMethod();
    while (true)
    {
      paramReference.clear();
      if (paramReference == this.frqReference)
        throw new ShutDown(null);
      try
      {
        localMethod.invoke(paramReference, new Object[0]);
        paramReference = this.queue.poll();
        if (paramReference != null)
          continue;
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          logger.log(Level.SEVERE, "Error cleaning up after reference.", localThrowable);
      }
    }
  }

  private Method getFinalizeReferentMethod()
    throws Finalizer.ShutDown
  {
    Class localClass = (Class)this.finalizableReferenceClassReference.get();
    if (localClass == null)
      throw new ShutDown(null);
    try
    {
      Method localMethod = localClass.getMethod("finalizeReferent", new Class[0]);
      return localMethod;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
  }

  public static ReferenceQueue<Object> startFinalizer(Class<?> paramClass, Object paramObject)
  {
    if (!paramClass.getName().equals("com.google.inject.internal.FinalizableReference"))
      throw new IllegalArgumentException("Expected com.google.inject.internal.FinalizableReference.");
    Finalizer localFinalizer = new Finalizer(paramClass, paramObject);
    localFinalizer.start();
    return localFinalizer.queue;
  }

  public void run()
  {
    try
    {
      while (true)
        label0: cleanUp(this.queue.remove());
    }
    catch (InterruptedException localInterruptedException)
    {
      break label0;
    }
    catch (ShutDown localShutDown)
    {
    }
  }

  private static class ShutDown extends Exception
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Finalizer
 * JD-Core Version:    0.6.2
 */