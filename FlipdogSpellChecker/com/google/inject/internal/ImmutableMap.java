package com.google.inject.internal;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public abstract class ImmutableMap<K, V>
  implements ConcurrentMap<K, V>, Serializable
{
  private static final ImmutableMap<?, ?> EMPTY_IMMUTABLE_MAP = new EmptyImmutableMap(null);

  public static <K, V> Builder<K, V> builder()
  {
    return new Builder();
  }

  public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> paramMap)
  {
    if ((paramMap instanceof ImmutableMap))
      return (ImmutableMap)paramMap;
    int i = paramMap.size();
    Map.Entry[] arrayOfEntry;
    int j;
    Iterator localIterator;
    switch (i)
    {
    default:
      arrayOfEntry = new Map.Entry[i];
      j = 0;
      localIterator = paramMap.entrySet().iterator();
    case 0:
    case 1:
    }
    while (localIterator.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator.next();
      int k = j + 1;
      arrayOfEntry[j] = entryOf(localEntry2.getKey(), localEntry2.getValue());
      j = k;
      continue;
      return of();
      Map.Entry localEntry1 = (Map.Entry)Iterables.getOnlyElement(paramMap.entrySet());
      return of(localEntry1.getKey(), localEntry1.getValue());
    }
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  private static <K, V> Map.Entry<K, V> entryOf(K paramK, V paramV)
  {
    return Maps.immutableEntry(Preconditions.checkNotNull(paramK), Preconditions.checkNotNull(paramV));
  }

  public static <K, V> ImmutableMap<K, V> of()
  {
    return EMPTY_IMMUTABLE_MAP;
  }

  public static <K, V> ImmutableMap<K, V> of(K paramK, V paramV)
  {
    return new SingletonImmutableMap(Preconditions.checkNotNull(paramK), Preconditions.checkNotNull(paramV), null);
  }

  public static <K, V> ImmutableMap<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2)
  {
    Map.Entry[] arrayOfEntry = new Map.Entry[2];
    arrayOfEntry[0] = entryOf(paramK1, paramV1);
    arrayOfEntry[1] = entryOf(paramK2, paramV2);
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  public static <K, V> ImmutableMap<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3)
  {
    Map.Entry[] arrayOfEntry = new Map.Entry[3];
    arrayOfEntry[0] = entryOf(paramK1, paramV1);
    arrayOfEntry[1] = entryOf(paramK2, paramV2);
    arrayOfEntry[2] = entryOf(paramK3, paramV3);
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  public static <K, V> ImmutableMap<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4)
  {
    Map.Entry[] arrayOfEntry = new Map.Entry[4];
    arrayOfEntry[0] = entryOf(paramK1, paramV1);
    arrayOfEntry[1] = entryOf(paramK2, paramV2);
    arrayOfEntry[2] = entryOf(paramK3, paramV3);
    arrayOfEntry[3] = entryOf(paramK4, paramV4);
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  public static <K, V> ImmutableMap<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5)
  {
    Map.Entry[] arrayOfEntry = new Map.Entry[5];
    arrayOfEntry[0] = entryOf(paramK1, paramV1);
    arrayOfEntry[1] = entryOf(paramK2, paramV2);
    arrayOfEntry[2] = entryOf(paramK3, paramV3);
    arrayOfEntry[3] = entryOf(paramK4, paramV4);
    arrayOfEntry[4] = entryOf(paramK5, paramV5);
    return new RegularImmutableMap(arrayOfEntry, null);
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  public abstract boolean containsKey(@Nullable Object paramObject);

  public abstract boolean containsValue(@Nullable Object paramObject);

  public abstract ImmutableSet<Map.Entry<K, V>> entrySet();

  public boolean equals(@Nullable Object paramObject)
  {
    if (paramObject == this)
      return true;
    if ((paramObject instanceof Map))
    {
      Map localMap = (Map)paramObject;
      return entrySet().equals(localMap.entrySet());
    }
    return false;
  }

  public abstract V get(@Nullable Object paramObject);

  public int hashCode()
  {
    return entrySet().hashCode();
  }

  public abstract ImmutableSet<K> keySet();

  public final V put(K paramK, V paramV)
  {
    throw new UnsupportedOperationException();
  }

  public final void putAll(Map<? extends K, ? extends V> paramMap)
  {
    throw new UnsupportedOperationException();
  }

  public final V putIfAbsent(K paramK, V paramV)
  {
    throw new UnsupportedOperationException();
  }

  public final V remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean remove(Object paramObject1, Object paramObject2)
  {
    throw new UnsupportedOperationException();
  }

  public final V replace(K paramK, V paramV)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean replace(K paramK, V paramV1, V paramV2)
  {
    throw new UnsupportedOperationException();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(16 * size()).append('{');
    UnmodifiableIterator localUnmodifiableIterator = entrySet().iterator();
    localStringBuilder.append(localUnmodifiableIterator.next());
    while (localUnmodifiableIterator.hasNext())
      localStringBuilder.append(", ").append(localUnmodifiableIterator.next());
    return '}';
  }

  public abstract ImmutableCollection<V> values();

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  public static class Builder<K, V>
  {
    final List<Map.Entry<K, V>> entries = Lists.newArrayList();

    private static <K, V> ImmutableMap<K, V> fromEntryList(List<Map.Entry<K, V>> paramList)
    {
      switch (paramList.size())
      {
      default:
        return new ImmutableMap.RegularImmutableMap((Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]), null);
      case 0:
        return ImmutableMap.of();
      case 1:
      }
      return new ImmutableMap.SingletonImmutableMap((Map.Entry)Iterables.getOnlyElement(paramList), null);
    }

    public ImmutableMap<K, V> build()
    {
      return fromEntryList(this.entries);
    }

    public Builder<K, V> put(K paramK, V paramV)
    {
      this.entries.add(ImmutableMap.entryOf(paramK, paramV));
      return this;
    }

    public Builder<K, V> putAll(Map<? extends K, ? extends V> paramMap)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        put(localEntry.getKey(), localEntry.getValue());
      }
      return this;
    }
  }

  private static final class EmptyImmutableMap extends ImmutableMap<Object, Object>
  {
    public boolean containsKey(Object paramObject)
    {
      return false;
    }

    public boolean containsValue(Object paramObject)
    {
      return false;
    }

    public ImmutableSet<Map.Entry<Object, Object>> entrySet()
    {
      return ImmutableSet.of();
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if ((paramObject instanceof Map))
        return ((Map)paramObject).isEmpty();
      return false;
    }

    public Object get(Object paramObject)
    {
      return null;
    }

    public int hashCode()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public ImmutableSet<Object> keySet()
    {
      return ImmutableSet.of();
    }

    public int size()
    {
      return 0;
    }

    public String toString()
    {
      return "{}";
    }

    public ImmutableCollection<Object> values()
    {
      return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
    }
  }

  private static final class RegularImmutableMap<K, V> extends ImmutableMap<K, V>
  {
    private final transient Map.Entry<K, V>[] entries;
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private final transient int keySetHashCode;
    private final transient int mask;
    private final transient Object[] table;
    private transient ImmutableCollection<V> values;

    private RegularImmutableMap(Map.Entry<?, ?>[] paramArrayOfEntry)
    {
      this.entries = ((Map.Entry[])paramArrayOfEntry);
      int i = Hashing.chooseTableSize(paramArrayOfEntry.length);
      this.table = new Object[i * 2];
      this.mask = (i - 1);
      int j = 0;
      Map.Entry[] arrayOfEntry = this.entries;
      int k = arrayOfEntry.length;
      int m = 0;
      if (m < k)
      {
        Map.Entry localEntry = arrayOfEntry[m];
        Object localObject1 = localEntry.getKey();
        int n = localObject1.hashCode();
        for (int i1 = Hashing.smear(n); ; i1++)
        {
          int i2 = 2 * (i1 & this.mask);
          Object localObject2 = this.table[i2];
          if (localObject2 == null)
          {
            Object localObject3 = localEntry.getValue();
            this.table[i2] = localObject1;
            this.table[(i2 + 1)] = localObject3;
            j += n;
            m++;
            break;
          }
          if (localObject2.equals(localObject1))
            throw new IllegalArgumentException("duplicate key: " + localObject1);
        }
      }
      this.keySetHashCode = j;
    }

    public boolean containsKey(Object paramObject)
    {
      return get(paramObject) != null;
    }

    public boolean containsValue(Object paramObject)
    {
      if (paramObject == null);
      while (true)
      {
        return false;
        Map.Entry[] arrayOfEntry = this.entries;
        int i = arrayOfEntry.length;
        for (int j = 0; j < i; j++)
          if (arrayOfEntry[j].getValue().equals(paramObject))
            return true;
      }
    }

    public ImmutableSet<Map.Entry<K, V>> entrySet()
    {
      Object localObject = this.entrySet;
      if (localObject == null)
      {
        localObject = new EntrySet(this);
        this.entrySet = ((ImmutableSet)localObject);
      }
      return localObject;
    }

    public V get(Object paramObject)
    {
      if (paramObject == null)
        return null;
      for (int i = Hashing.smear(paramObject.hashCode()); ; i++)
      {
        int j = 2 * (i & this.mask);
        Object localObject = this.table[j];
        if (localObject == null)
          break;
        if (localObject.equals(paramObject))
          return this.table[(j + 1)];
      }
    }

    public boolean isEmpty()
    {
      return false;
    }

    public ImmutableSet<K> keySet()
    {
      Object localObject = this.keySet;
      if (localObject == null)
      {
        localObject = new KeySet(this);
        this.keySet = ((ImmutableSet)localObject);
      }
      return localObject;
    }

    public int size()
    {
      return this.entries.length;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(16 * size()).append('{').append(this.entries[0]);
      for (int i = 1; i < this.entries.length; i++)
        localStringBuilder.append(", ").append(this.entries[i].toString());
      return '}';
    }

    public ImmutableCollection<V> values()
    {
      Object localObject = this.values;
      if (localObject == null)
      {
        localObject = new Values(this);
        this.values = ((ImmutableCollection)localObject);
      }
      return localObject;
    }

    private static class EntrySet<K, V> extends ImmutableSet.ArrayImmutableSet<Map.Entry<K, V>>
    {
      final ImmutableMap.RegularImmutableMap<K, V> map;

      EntrySet(ImmutableMap.RegularImmutableMap<K, V> paramRegularImmutableMap)
      {
        super();
        this.map = paramRegularImmutableMap;
      }

      public boolean contains(Object paramObject)
      {
        boolean bool1 = paramObject instanceof Map.Entry;
        boolean bool2 = false;
        if (bool1)
        {
          Map.Entry localEntry = (Map.Entry)paramObject;
          Object localObject = this.map.get(localEntry.getKey());
          bool2 = false;
          if (localObject != null)
          {
            boolean bool3 = localObject.equals(localEntry.getValue());
            bool2 = false;
            if (bool3)
              bool2 = true;
          }
        }
        return bool2;
      }
    }

    private static class KeySet<K, V> extends ImmutableSet.TransformedImmutableSet<Map.Entry<K, V>, K>
    {
      final ImmutableMap.RegularImmutableMap<K, V> map;

      KeySet(ImmutableMap.RegularImmutableMap<K, V> paramRegularImmutableMap)
      {
        super(paramRegularImmutableMap.keySetHashCode);
        this.map = paramRegularImmutableMap;
      }

      public boolean contains(Object paramObject)
      {
        return this.map.containsKey(paramObject);
      }

      K transform(Map.Entry<K, V> paramEntry)
      {
        return paramEntry.getKey();
      }
    }

    private static class Values<V> extends ImmutableCollection<V>
    {
      final ImmutableMap.RegularImmutableMap<?, V> map;

      Values(ImmutableMap.RegularImmutableMap<?, V> paramRegularImmutableMap)
      {
        this.map = paramRegularImmutableMap;
      }

      public boolean contains(Object paramObject)
      {
        return this.map.containsValue(paramObject);
      }

      public boolean isEmpty()
      {
        return false;
      }

      public UnmodifiableIterator<V> iterator()
      {
        return Iterators.unmodifiableIterator(new AbstractIterator()
        {
          int index = 0;

          protected V computeNext()
          {
            if (this.index < ImmutableMap.RegularImmutableMap.Values.this.map.entries.length)
            {
              Map.Entry[] arrayOfEntry = ImmutableMap.RegularImmutableMap.Values.this.map.entries;
              int i = this.index;
              this.index = (i + 1);
              return arrayOfEntry[i].getValue();
            }
            return endOfData();
          }
        });
      }

      public int size()
      {
        return this.map.entries.length;
      }
    }
  }

  private static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    final Object[] keys;
    final Object[] values;

    SerializedForm(ImmutableMap<?, ?> paramImmutableMap)
    {
      this.keys = new Object[paramImmutableMap.size()];
      this.values = new Object[paramImmutableMap.size()];
      int i = 0;
      Iterator localIterator = paramImmutableMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.keys[i] = localEntry.getKey();
        this.values[i] = localEntry.getValue();
        i++;
      }
    }

    Object readResolve()
    {
      ImmutableMap.Builder localBuilder = new ImmutableMap.Builder();
      for (int i = 0; i < this.keys.length; i++)
        localBuilder.put(this.keys[i], this.values[i]);
      return localBuilder.build();
    }
  }

  private static final class SingletonImmutableMap<K, V> extends ImmutableMap<K, V>
  {
    private transient Map.Entry<K, V> entry;
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private final transient K singleKey;
    private final transient V singleValue;
    private transient ImmutableCollection<V> values;

    private SingletonImmutableMap(K paramK, V paramV)
    {
      this.singleKey = paramK;
      this.singleValue = paramV;
    }

    private SingletonImmutableMap(Map.Entry<K, V> paramEntry)
    {
      this.entry = paramEntry;
      this.singleKey = paramEntry.getKey();
      this.singleValue = paramEntry.getValue();
    }

    private Map.Entry<K, V> entry()
    {
      Map.Entry localEntry = this.entry;
      if (localEntry == null)
      {
        localEntry = Maps.immutableEntry(this.singleKey, this.singleValue);
        this.entry = localEntry;
      }
      return localEntry;
    }

    public boolean containsKey(Object paramObject)
    {
      return this.singleKey.equals(paramObject);
    }

    public boolean containsValue(Object paramObject)
    {
      return this.singleValue.equals(paramObject);
    }

    public ImmutableSet<Map.Entry<K, V>> entrySet()
    {
      ImmutableSet localImmutableSet = this.entrySet;
      if (localImmutableSet == null)
      {
        localImmutableSet = ImmutableSet.of(entry());
        this.entrySet = localImmutableSet;
      }
      return localImmutableSet;
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if (paramObject == this);
      Map.Entry localEntry;
      do
      {
        return true;
        if (!(paramObject instanceof Map))
          break;
        Map localMap = (Map)paramObject;
        if (localMap.size() != 1)
          return false;
        localEntry = (Map.Entry)localMap.entrySet().iterator().next();
      }
      while ((this.singleKey.equals(localEntry.getKey())) && (this.singleValue.equals(localEntry.getValue())));
      return false;
      return false;
    }

    public V get(Object paramObject)
    {
      if (this.singleKey.equals(paramObject))
        return this.singleValue;
      return null;
    }

    public int hashCode()
    {
      return this.singleKey.hashCode() ^ this.singleValue.hashCode();
    }

    public boolean isEmpty()
    {
      return false;
    }

    public ImmutableSet<K> keySet()
    {
      ImmutableSet localImmutableSet = this.keySet;
      if (localImmutableSet == null)
      {
        localImmutableSet = ImmutableSet.of(this.singleKey);
        this.keySet = localImmutableSet;
      }
      return localImmutableSet;
    }

    public int size()
    {
      return 1;
    }

    public String toString()
    {
      return '{' + this.singleKey.toString() + '=' + this.singleValue.toString() + '}';
    }

    public ImmutableCollection<V> values()
    {
      Object localObject = this.values;
      if (localObject == null)
      {
        localObject = new Values(this.singleValue);
        this.values = ((ImmutableCollection)localObject);
      }
      return localObject;
    }

    private static class Values<V> extends ImmutableCollection<V>
    {
      final V singleValue;

      Values(V paramV)
      {
        this.singleValue = paramV;
      }

      public boolean contains(Object paramObject)
      {
        return this.singleValue.equals(paramObject);
      }

      public boolean isEmpty()
      {
        return false;
      }

      public UnmodifiableIterator<V> iterator()
      {
        return Iterators.singletonIterator(this.singleValue);
      }

      public int size()
      {
        return 1;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ImmutableMap
 * JD-Core Version:    0.6.2
 */