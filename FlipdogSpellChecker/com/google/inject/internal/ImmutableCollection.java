package com.google.inject.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ImmutableCollection<E>
  implements Collection<E>, Serializable
{
  private static final Object[] EMPTY_ARRAY = new Object[0];
  static final ImmutableCollection<Object> EMPTY_IMMUTABLE_COLLECTION = new EmptyImmutableCollection(null);
  private static final UnmodifiableIterator<Object> EMPTY_ITERATOR = new UnmodifiableIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }
  };

  public final boolean add(E paramE)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean addAll(Collection<? extends E> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  public boolean contains(@Nullable Object paramObject)
  {
    if (paramObject == null);
    Iterator localIterator;
    do
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = iterator();
      }
    while (!localIterator.next().equals(paramObject));
    return true;
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      if (!contains(localIterator.next()))
        return false;
    return true;
  }

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public abstract UnmodifiableIterator<E> iterator();

  public final boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public Object[] toArray()
  {
    return toArray(new Object[size()]);
  }

  public <T> T[] toArray(T[] paramArrayOfT)
  {
    int i = size();
    if (paramArrayOfT.length < i)
      paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, i);
    while (true)
    {
      int j = 0;
      Iterator localIterator = iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        int k = j + 1;
        paramArrayOfT[j] = localObject;
        j = k;
      }
      if (paramArrayOfT.length > i)
        paramArrayOfT[i] = null;
    }
    return paramArrayOfT;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(16 * size());
    localStringBuilder.append('[');
    UnmodifiableIterator localUnmodifiableIterator = iterator();
    if (localUnmodifiableIterator.hasNext())
      localStringBuilder.append(localUnmodifiableIterator.next());
    while (localUnmodifiableIterator.hasNext())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append(localUnmodifiableIterator.next());
    }
    return ']';
  }

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  private static class ArrayImmutableCollection<E> extends ImmutableCollection<E>
  {
    private final E[] elements;

    ArrayImmutableCollection(E[] paramArrayOfE)
    {
      this.elements = paramArrayOfE;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return new UnmodifiableIterator()
      {
        int i = 0;

        public boolean hasNext()
        {
          return this.i < ImmutableCollection.ArrayImmutableCollection.this.elements.length;
        }

        public E next()
        {
          if (!hasNext())
            throw new NoSuchElementException();
          Object[] arrayOfObject = ImmutableCollection.ArrayImmutableCollection.this.elements;
          int j = this.i;
          this.i = (j + 1);
          return arrayOfObject[j];
        }
      };
    }

    public int size()
    {
      return this.elements.length;
    }
  }

  private static class EmptyImmutableCollection extends ImmutableCollection<Object>
  {
    public boolean contains(@Nullable Object paramObject)
    {
      return false;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public UnmodifiableIterator<Object> iterator()
    {
      return ImmutableCollection.EMPTY_ITERATOR;
    }

    public int size()
    {
      return 0;
    }

    public Object[] toArray()
    {
      return ImmutableCollection.EMPTY_ARRAY;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      if (paramArrayOfT.length > 0)
        paramArrayOfT[0] = null;
      return paramArrayOfT;
    }
  }

  private static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    final Object[] elements;

    SerializedForm(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    Object readResolve()
    {
      if (this.elements.length == 0)
        return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
      return new ImmutableCollection.ArrayImmutableCollection((Object[])this.elements.clone());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ImmutableCollection
 * JD-Core Version:    0.6.2
 */