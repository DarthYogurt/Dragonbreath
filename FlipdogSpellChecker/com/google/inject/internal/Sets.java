package com.google.inject.internal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class Sets
{
  static int hashCodeImpl(Set<?> paramSet)
  {
    int i = 0;
    Iterator localIterator = paramSet.iterator();
    if (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject != null);
      for (int j = localObject.hashCode(); ; j = 0)
      {
        i += j;
        break;
      }
    }
    return i;
  }

  public static <E> HashSet<E> newHashSet()
  {
    return new HashSet();
  }

  public static <E> LinkedHashSet<E> newLinkedHashSet()
  {
    return new LinkedHashSet();
  }

  public static <E> Set<E> newSetFromMap(Map<E, Boolean> paramMap)
  {
    return new SetFromMap(paramMap);
  }

  private static class SetFromMap<E> extends AbstractSet<E>
    implements Set<E>, Serializable
  {
    static final long serialVersionUID;
    private final Map<E, Boolean> m;
    private transient Set<E> s;

    SetFromMap(Map<E, Boolean> paramMap)
    {
      Preconditions.checkArgument(paramMap.isEmpty(), "Map is non-empty");
      this.m = paramMap;
      this.s = paramMap.keySet();
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      this.s = this.m.keySet();
    }

    public boolean add(E paramE)
    {
      return this.m.put(paramE, Boolean.TRUE) == null;
    }

    public void clear()
    {
      this.m.clear();
    }

    public boolean contains(Object paramObject)
    {
      return this.m.containsKey(paramObject);
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      return this.s.containsAll(paramCollection);
    }

    public boolean equals(@Nullable Object paramObject)
    {
      return (this == paramObject) || (this.s.equals(paramObject));
    }

    public int hashCode()
    {
      return this.s.hashCode();
    }

    public boolean isEmpty()
    {
      return this.m.isEmpty();
    }

    public Iterator<E> iterator()
    {
      return this.s.iterator();
    }

    public boolean remove(Object paramObject)
    {
      return this.m.remove(paramObject) != null;
    }

    public boolean removeAll(Collection<?> paramCollection)
    {
      return this.s.removeAll(paramCollection);
    }

    public boolean retainAll(Collection<?> paramCollection)
    {
      return this.s.retainAll(paramCollection);
    }

    public int size()
    {
      return this.m.size();
    }

    public Object[] toArray()
    {
      return this.s.toArray();
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return this.s.toArray(paramArrayOfT);
    }

    public String toString()
    {
      return this.s.toString();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Sets
 * JD-Core Version:    0.6.2
 */