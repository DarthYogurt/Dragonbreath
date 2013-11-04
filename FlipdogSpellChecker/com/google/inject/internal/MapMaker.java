package com.google.inject.internal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public final class MapMaker
{
  private static final ValueReference<Object, Object> COMPUTING = new ValueReference()
  {
    public MapMaker.ValueReference<Object, Object> copyFor(MapMaker.ReferenceEntry<Object, Object> paramAnonymousReferenceEntry)
    {
      throw new AssertionError();
    }

    public Object get()
    {
      return null;
    }

    public Object waitForValue()
    {
      throw new AssertionError();
    }
  };
  private final CustomConcurrentHashMap.Builder builder = new CustomConcurrentHashMap.Builder();
  private long expirationNanos = 0L;
  private Strength keyStrength = Strength.STRONG;
  private boolean useCustomMap;
  private Strength valueStrength = Strength.STRONG;

  private static <K, V> ValueReference<K, V> computing()
  {
    return COMPUTING;
  }

  private MapMaker setKeyStrength(Strength paramStrength)
  {
    if (this.keyStrength != Strength.STRONG)
      throw new IllegalStateException("Key strength was already set to " + this.keyStrength + ".");
    this.keyStrength = paramStrength;
    this.useCustomMap = true;
    return this;
  }

  private MapMaker setValueStrength(Strength paramStrength)
  {
    if (this.valueStrength != Strength.STRONG)
      throw new IllegalStateException("Value strength was already set to " + this.valueStrength + ".");
    this.valueStrength = paramStrength;
    this.useCustomMap = true;
    return this;
  }

  public MapMaker concurrencyLevel(int paramInt)
  {
    this.builder.concurrencyLevel(paramInt);
    return this;
  }

  public MapMaker expiration(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.expirationNanos != 0L)
      throw new IllegalStateException("expiration time of " + this.expirationNanos + " ns was already set");
    if (paramLong <= 0L)
      throw new IllegalArgumentException("invalid duration: " + paramLong);
    this.expirationNanos = paramTimeUnit.toNanos(paramLong);
    this.useCustomMap = true;
    return this;
  }

  public MapMaker initialCapacity(int paramInt)
  {
    this.builder.initialCapacity(paramInt);
    return this;
  }

  public MapMaker loadFactor(float paramFloat)
  {
    this.builder.loadFactor(paramFloat);
    return this;
  }

  public <K, V> ConcurrentMap<K, V> makeComputingMap(Function<? super K, ? extends V> paramFunction)
  {
    return new StrategyImpl(this, paramFunction).map;
  }

  public <K, V> ConcurrentMap<K, V> makeMap()
  {
    if (this.useCustomMap)
      return new StrategyImpl(this).map;
    return new ConcurrentHashMap(this.builder.initialCapacity, this.builder.loadFactor, this.builder.concurrencyLevel);
  }

  public MapMaker softKeys()
  {
    return setKeyStrength(Strength.SOFT);
  }

  public MapMaker softValues()
  {
    return setValueStrength(Strength.SOFT);
  }

  public MapMaker weakKeys()
  {
    return setKeyStrength(Strength.WEAK);
  }

  public MapMaker weakValues()
  {
    return setValueStrength(Strength.WEAK);
  }

  private static class ComputationExceptionReference<K, V>
    implements MapMaker.ValueReference<K, V>
  {
    final Throwable t;

    ComputationExceptionReference(Throwable paramThrowable)
    {
      this.t = paramThrowable;
    }

    public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return null;
    }

    public V waitForValue()
    {
      throw new AsynchronousComputationException(this.t);
    }
  }

  private static class LinkedSoftEntry<K, V> extends MapMaker.SoftEntry<K, V>
  {
    final MapMaker.ReferenceEntry<K, V> next;

    LinkedSoftEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      super(paramK, paramInt);
      this.next = paramReferenceEntry;
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return this.next;
    }
  }

  private static class LinkedStrongEntry<K, V> extends MapMaker.StrongEntry<K, V>
  {
    final MapMaker.ReferenceEntry<K, V> next;

    LinkedStrongEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      super(paramK, paramInt);
      this.next = paramReferenceEntry;
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return this.next;
    }
  }

  private static class LinkedWeakEntry<K, V> extends MapMaker.WeakEntry<K, V>
  {
    final MapMaker.ReferenceEntry<K, V> next;

    LinkedWeakEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      super(paramK, paramInt);
      this.next = paramReferenceEntry;
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return this.next;
    }
  }

  private static class NullOutputExceptionReference<K, V>
    implements MapMaker.ValueReference<K, V>
  {
    final String message;

    NullOutputExceptionReference(String paramString)
    {
      this.message = paramString;
    }

    public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return null;
    }

    public V waitForValue()
    {
      throw new NullOutputException(this.message);
    }
  }

  private static class QueueHolder
  {
    static final FinalizableReferenceQueue queue = new FinalizableReferenceQueue();
  }

  private static abstract interface ReferenceEntry<K, V>
  {
    public abstract int getHash();

    public abstract K getKey();

    public abstract ReferenceEntry<K, V> getNext();

    public abstract MapMaker.ValueReference<K, V> getValueReference();

    public abstract void setValueReference(MapMaker.ValueReference<K, V> paramValueReference);

    public abstract void valueReclaimed();
  }

  private static class SoftEntry<K, V> extends FinalizableSoftReference<K>
    implements MapMaker.ReferenceEntry<K, V>
  {
    final int hash;
    final CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> internals;
    volatile MapMaker.ValueReference<K, V> valueReference = MapMaker.access$600();

    SoftEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt)
    {
      super(MapMaker.QueueHolder.queue);
      this.internals = paramInternals;
      this.hash = paramInt;
    }

    public void finalizeReferent()
    {
      this.internals.removeEntry(this);
    }

    public int getHash()
    {
      return this.hash;
    }

    public K getKey()
    {
      return get();
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return null;
    }

    public MapMaker.ValueReference<K, V> getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference(MapMaker.ValueReference<K, V> paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }
  }

  private static class SoftValueReference<K, V> extends FinalizableSoftReference<V>
    implements MapMaker.ValueReference<K, V>
  {
    final MapMaker.ReferenceEntry<K, V> entry;

    SoftValueReference(V paramV, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      super(MapMaker.QueueHolder.queue);
      this.entry = paramReferenceEntry;
    }

    public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return new SoftValueReference(get(), paramReferenceEntry);
    }

    public void finalizeReferent()
    {
      this.entry.valueReclaimed();
    }

    public V waitForValue()
    {
      return get();
    }
  }

  private static class StrategyImpl<K, V>
    implements Serializable, CustomConcurrentHashMap.ComputingStrategy<K, V, MapMaker.ReferenceEntry<K, V>>
  {
    private static final long serialVersionUID;
    final long expirationNanos;
    CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> internals;
    final MapMaker.Strength keyStrength;
    final ConcurrentMap<K, V> map;
    final MapMaker.Strength valueStrength;

    StrategyImpl(MapMaker paramMapMaker)
    {
      this.keyStrength = paramMapMaker.keyStrength;
      this.valueStrength = paramMapMaker.valueStrength;
      this.expirationNanos = paramMapMaker.expirationNanos;
      this.map = paramMapMaker.builder.buildMap(this);
    }

    StrategyImpl(MapMaker paramMapMaker, Function<? super K, ? extends V> paramFunction)
    {
      this.keyStrength = paramMapMaker.keyStrength;
      this.valueStrength = paramMapMaker.valueStrength;
      this.expirationNanos = paramMapMaker.expirationNanos;
      this.map = paramMapMaker.builder.buildComputingMap(this, paramFunction);
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      try
      {
        Fields.keyStrength.set(this, paramObjectInputStream.readObject());
        Fields.valueStrength.set(this, paramObjectInputStream.readObject());
        Fields.expirationNanos.set(this, Long.valueOf(paramObjectInputStream.readLong()));
        Fields.internals.set(this, paramObjectInputStream.readObject());
        Fields.map.set(this, paramObjectInputStream.readObject());
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
    }

    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.writeObject(this.keyStrength);
      paramObjectOutputStream.writeObject(this.valueStrength);
      paramObjectOutputStream.writeLong(this.expirationNanos);
      paramObjectOutputStream.writeObject(this.internals);
      paramObjectOutputStream.writeObject(this.map);
    }

    public V compute(K paramK, MapMaker.ReferenceEntry<K, V> paramReferenceEntry, Function<? super K, ? extends V> paramFunction)
    {
      Object localObject;
      try
      {
        localObject = paramFunction.apply(paramK);
        if (localObject == null)
        {
          String str = paramFunction + " returned null for key " + paramK + ".";
          setValueReference(paramReferenceEntry, new MapMaker.NullOutputExceptionReference(str));
          throw new NullOutputException(str);
        }
      }
      catch (Throwable localThrowable)
      {
        setValueReference(paramReferenceEntry, new MapMaker.ComputationExceptionReference(localThrowable));
        throw new ComputationException(localThrowable);
      }
      setValue(paramReferenceEntry, localObject);
      return localObject;
    }

    public MapMaker.ReferenceEntry<K, V> copyEntry(K paramK, MapMaker.ReferenceEntry<K, V> paramReferenceEntry1, MapMaker.ReferenceEntry<K, V> paramReferenceEntry2)
    {
      MapMaker.ValueReference localValueReference = paramReferenceEntry1.getValueReference();
      if (localValueReference == MapMaker.COMPUTING)
      {
        MapMaker.ReferenceEntry localReferenceEntry2 = newEntry(paramK, paramReferenceEntry1.getHash(), paramReferenceEntry2);
        localReferenceEntry2.setValueReference(new FutureValueReference(paramReferenceEntry1, localReferenceEntry2));
        return localReferenceEntry2;
      }
      MapMaker.ReferenceEntry localReferenceEntry1 = newEntry(paramK, paramReferenceEntry1.getHash(), paramReferenceEntry2);
      localReferenceEntry1.setValueReference(localValueReference.copyFor(localReferenceEntry1));
      return localReferenceEntry1;
    }

    public boolean equalKeys(K paramK, Object paramObject)
    {
      return this.keyStrength.equal(paramK, paramObject);
    }

    public boolean equalValues(V paramV, Object paramObject)
    {
      return this.valueStrength.equal(paramV, paramObject);
    }

    public int getHash(MapMaker.ReferenceEntry paramReferenceEntry)
    {
      return paramReferenceEntry.getHash();
    }

    public K getKey(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return paramReferenceEntry.getKey();
    }

    public MapMaker.ReferenceEntry<K, V> getNext(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return paramReferenceEntry.getNext();
    }

    public V getValue(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return paramReferenceEntry.getValueReference().get();
    }

    public int hashKey(Object paramObject)
    {
      return this.keyStrength.hash(paramObject);
    }

    public MapMaker.ReferenceEntry<K, V> newEntry(K paramK, int paramInt, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this.keyStrength.newEntry(this.internals, paramK, paramInt, paramReferenceEntry);
    }

    void scheduleRemoval(K paramK, V paramV)
    {
      final WeakReference localWeakReference1 = new WeakReference(paramK);
      final WeakReference localWeakReference2 = new WeakReference(paramV);
      ExpirationTimer.instance.schedule(new TimerTask()
      {
        public void run()
        {
          Object localObject = localWeakReference1.get();
          if (localObject != null)
            MapMaker.StrategyImpl.this.map.remove(localObject, localWeakReference2.get());
        }
      }
      , TimeUnit.NANOSECONDS.toMillis(this.expirationNanos));
    }

    public void setInternals(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals)
    {
      this.internals = paramInternals;
    }

    public void setValue(MapMaker.ReferenceEntry<K, V> paramReferenceEntry, V paramV)
    {
      setValueReference(paramReferenceEntry, this.valueStrength.referenceValue(paramReferenceEntry, paramV));
      if (this.expirationNanos > 0L)
        scheduleRemoval(paramReferenceEntry.getKey(), paramV);
    }

    void setValueReference(MapMaker.ReferenceEntry<K, V> paramReferenceEntry, MapMaker.ValueReference<K, V> paramValueReference)
    {
      if (paramReferenceEntry.getValueReference() == MapMaker.COMPUTING);
      for (int i = 1; ; i = 0)
      {
        paramReferenceEntry.setValueReference(paramValueReference);
        if (i != 0)
          try
          {
            paramReferenceEntry.notifyAll();
            return;
          }
          finally
          {
          }
        return;
      }
    }

    public V waitForValue(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
      throws InterruptedException
    {
      MapMaker.ValueReference localValueReference = paramReferenceEntry.getValueReference();
      if (localValueReference == MapMaker.COMPUTING)
        try
        {
          while (true)
          {
            localValueReference = paramReferenceEntry.getValueReference();
            if (localValueReference != MapMaker.COMPUTING)
              break;
            paramReferenceEntry.wait();
          }
        }
        finally
        {
        }
      return localValueReference.waitForValue();
    }

    private static class Fields
    {
      static final Field expirationNanos = findField("expirationNanos");
      static final Field internals = findField("internals");
      static final Field keyStrength = findField("keyStrength");
      static final Field map = findField("map");
      static final Field valueStrength = findField("valueStrength");

      static Field findField(String paramString)
      {
        try
        {
          Field localField = MapMaker.StrategyImpl.class.getDeclaredField(paramString);
          localField.setAccessible(true);
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          throw new AssertionError(localNoSuchFieldException);
        }
      }
    }

    private class FutureValueReference
      implements MapMaker.ValueReference<K, V>
    {
      final MapMaker.ReferenceEntry<K, V> newEntry;
      final MapMaker.ReferenceEntry<K, V> original;

      FutureValueReference(MapMaker.ReferenceEntry<K, V> arg2)
      {
        Object localObject1;
        this.original = localObject1;
        Object localObject2;
        this.newEntry = localObject2;
      }

      public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
      {
        return new FutureValueReference(MapMaker.StrategyImpl.this, this.original, paramReferenceEntry);
      }

      public V get()
      {
        try
        {
          Object localObject2 = this.original.getValueReference().get();
          if (1 == 0)
            removeEntry();
          return localObject2;
        }
        finally
        {
          if (0 == 0)
            removeEntry();
        }
      }

      void removeEntry()
      {
        MapMaker.StrategyImpl.this.internals.removeEntry(this.newEntry);
      }

      public V waitForValue()
        throws InterruptedException
      {
        try
        {
          Object localObject2 = MapMaker.StrategyImpl.this.waitForValue(this.original);
          if (1 == 0)
            removeEntry();
          return localObject2;
        }
        finally
        {
          if (0 == 0)
            removeEntry();
        }
      }
    }
  }

  private static abstract enum Strength
  {
    static
    {
      // Byte code:
      //   0: new 15	com/google/inject/internal/MapMaker$Strength$1
      //   3: dup
      //   4: ldc 16
      //   6: iconst_0
      //   7: invokespecial 20	com/google/inject/internal/MapMaker$Strength$1:<init>	(Ljava/lang/String;I)V
      //   10: putstatic 22	com/google/inject/internal/MapMaker$Strength:WEAK	Lcom/google/inject/internal/MapMaker$Strength;
      //   13: new 24	com/google/inject/internal/MapMaker$Strength$2
      //   16: dup
      //   17: ldc 25
      //   19: iconst_1
      //   20: invokespecial 26	com/google/inject/internal/MapMaker$Strength$2:<init>	(Ljava/lang/String;I)V
      //   23: putstatic 28	com/google/inject/internal/MapMaker$Strength:SOFT	Lcom/google/inject/internal/MapMaker$Strength;
      //   26: new 30	com/google/inject/internal/MapMaker$Strength$3
      //   29: dup
      //   30: ldc 31
      //   32: iconst_2
      //   33: invokespecial 32	com/google/inject/internal/MapMaker$Strength$3:<init>	(Ljava/lang/String;I)V
      //   36: putstatic 34	com/google/inject/internal/MapMaker$Strength:STRONG	Lcom/google/inject/internal/MapMaker$Strength;
      //   39: iconst_3
      //   40: anewarray 2	com/google/inject/internal/MapMaker$Strength
      //   43: astore_0
      //   44: aload_0
      //   45: iconst_0
      //   46: getstatic 22	com/google/inject/internal/MapMaker$Strength:WEAK	Lcom/google/inject/internal/MapMaker$Strength;
      //   49: aastore
      //   50: aload_0
      //   51: iconst_1
      //   52: getstatic 28	com/google/inject/internal/MapMaker$Strength:SOFT	Lcom/google/inject/internal/MapMaker$Strength;
      //   55: aastore
      //   56: aload_0
      //   57: iconst_2
      //   58: getstatic 34	com/google/inject/internal/MapMaker$Strength:STRONG	Lcom/google/inject/internal/MapMaker$Strength;
      //   61: aastore
      //   62: aload_0
      //   63: putstatic 36	com/google/inject/internal/MapMaker$Strength:$VALUES	[Lcom/google/inject/internal/MapMaker$Strength;
      //   66: return
    }

    abstract <K, V> MapMaker.ReferenceEntry<K, V> copyEntry(K paramK, MapMaker.ReferenceEntry<K, V> paramReferenceEntry1, MapMaker.ReferenceEntry<K, V> paramReferenceEntry2);

    abstract boolean equal(Object paramObject1, Object paramObject2);

    abstract int hash(Object paramObject);

    abstract <K, V> MapMaker.ReferenceEntry<K, V> newEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt, MapMaker.ReferenceEntry<K, V> paramReferenceEntry);

    abstract <K, V> MapMaker.ValueReference<K, V> referenceValue(MapMaker.ReferenceEntry<K, V> paramReferenceEntry, V paramV);
  }

  private static class StrongEntry<K, V>
    implements MapMaker.ReferenceEntry<K, V>
  {
    final int hash;
    final CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> internals;
    final K key;
    volatile MapMaker.ValueReference<K, V> valueReference = MapMaker.access$600();

    StrongEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt)
    {
      this.internals = paramInternals;
      this.key = paramK;
      this.hash = paramInt;
    }

    public int getHash()
    {
      return this.hash;
    }

    public K getKey()
    {
      return this.key;
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return null;
    }

    public MapMaker.ValueReference<K, V> getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference(MapMaker.ValueReference<K, V> paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }
  }

  private static class StrongValueReference<K, V>
    implements MapMaker.ValueReference<K, V>
  {
    final V referent;

    StrongValueReference(V paramV)
    {
      this.referent = paramV;
    }

    public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return this.referent;
    }

    public V waitForValue()
    {
      return get();
    }
  }

  private static abstract interface ValueReference<K, V>
  {
    public abstract ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry);

    public abstract V get();

    public abstract V waitForValue()
      throws InterruptedException;
  }

  private static class WeakEntry<K, V> extends FinalizableWeakReference<K>
    implements MapMaker.ReferenceEntry<K, V>
  {
    final int hash;
    final CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> internals;
    volatile MapMaker.ValueReference<K, V> valueReference = MapMaker.access$600();

    WeakEntry(CustomConcurrentHashMap.Internals<K, V, MapMaker.ReferenceEntry<K, V>> paramInternals, K paramK, int paramInt)
    {
      super(MapMaker.QueueHolder.queue);
      this.internals = paramInternals;
      this.hash = paramInt;
    }

    public void finalizeReferent()
    {
      this.internals.removeEntry(this);
    }

    public int getHash()
    {
      return this.hash;
    }

    public K getKey()
    {
      return get();
    }

    public MapMaker.ReferenceEntry<K, V> getNext()
    {
      return null;
    }

    public MapMaker.ValueReference<K, V> getValueReference()
    {
      return this.valueReference;
    }

    public void setValueReference(MapMaker.ValueReference<K, V> paramValueReference)
    {
      this.valueReference = paramValueReference;
    }

    public void valueReclaimed()
    {
      this.internals.removeEntry(this, null);
    }
  }

  private static class WeakValueReference<K, V> extends FinalizableWeakReference<V>
    implements MapMaker.ValueReference<K, V>
  {
    final MapMaker.ReferenceEntry<K, V> entry;

    WeakValueReference(V paramV, MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      super(MapMaker.QueueHolder.queue);
      this.entry = paramReferenceEntry;
    }

    public MapMaker.ValueReference<K, V> copyFor(MapMaker.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return new WeakValueReference(get(), paramReferenceEntry);
    }

    public void finalizeReferent()
    {
      this.entry.valueReclaimed();
    }

    public V waitForValue()
      throws InterruptedException
    {
      return get();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.MapMaker
 * JD-Core Version:    0.6.2
 */