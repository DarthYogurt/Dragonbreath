package com.google.inject.internal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public abstract class ImmutableList<E> extends ImmutableCollection<E>
  implements List<E>, RandomAccess
{
  private static final ImmutableList<?> EMPTY_IMMUTABLE_LIST = new EmptyImmutableList(null);

  public static <E> Builder<E> builder()
  {
    return new Builder();
  }

  private static Object[] copyIntoArray(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    int i = paramArrayOfObject.length;
    int j = 0;
    int m;
    for (int k = 0; j < i; k = m)
    {
      Object localObject = paramArrayOfObject[j];
      if (localObject == null)
        throw new NullPointerException("at index " + k);
      m = k + 1;
      arrayOfObject[k] = localObject;
      j++;
    }
    return arrayOfObject;
  }

  public static <E> ImmutableList<E> copyOf(Iterable<? extends E> paramIterable)
  {
    if ((paramIterable instanceof ImmutableList))
      return (ImmutableList)paramIterable;
    if ((paramIterable instanceof Collection))
      return copyOfInternal((Collection)paramIterable);
    return copyOfInternal(Lists.newArrayList(paramIterable));
  }

  public static <E> ImmutableList<E> copyOf(Iterator<? extends E> paramIterator)
  {
    return copyOfInternal(Lists.newArrayList(paramIterator));
  }

  private static Object[] copyOf(Object[] paramArrayOfObject, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, Math.min(paramArrayOfObject.length, paramInt));
    return arrayOfObject;
  }

  private static <E> ImmutableList<E> copyOfInternal(ArrayList<? extends E> paramArrayList)
  {
    if (paramArrayList.isEmpty())
      return of();
    return new RegularImmutableList(nullChecked(paramArrayList.toArray()), null);
  }

  private static <E> ImmutableList<E> copyOfInternal(Collection<? extends E> paramCollection)
  {
    int i = paramCollection.size();
    if (i == 0)
      return of();
    return createFromIterable(paramCollection, i);
  }

  private static <E> ImmutableList<E> createFromIterable(Iterable<?> paramIterable, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    int i = 0;
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (i == paramInt)
      {
        paramInt = 3 * (1 + paramInt / 2);
        arrayOfObject = copyOf(arrayOfObject, paramInt);
      }
      if (localObject == null)
        throw new NullPointerException("at index " + i);
      int j = i + 1;
      arrayOfObject[i] = localObject;
      i = j;
    }
    if (i == 0)
      return of();
    if (i != paramInt)
      arrayOfObject = copyOf(arrayOfObject, i);
    return new RegularImmutableList(arrayOfObject, 0, i, null);
  }

  private static Object[] nullChecked(Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = paramArrayOfObject.length;
    while (i < j)
    {
      if (paramArrayOfObject[i] == null)
        throw new NullPointerException("at index " + i);
      i++;
    }
    return paramArrayOfObject;
  }

  public static <E> ImmutableList<E> of()
  {
    return EMPTY_IMMUTABLE_LIST;
  }

  public static <E> ImmutableList<E> of(E paramE)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramE }), null);
  }

  public static <E> ImmutableList<E> of(E paramE1, E paramE2)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramE1, paramE2 }), null);
  }

  public static <E> ImmutableList<E> of(E paramE1, E paramE2, E paramE3)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramE1, paramE2, paramE3 }), null);
  }

  public static <E> ImmutableList<E> of(E paramE1, E paramE2, E paramE3, E paramE4)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramE1, paramE2, paramE3, paramE4 }), null);
  }

  public static <E> ImmutableList<E> of(E paramE1, E paramE2, E paramE3, E paramE4, E paramE5)
  {
    return new RegularImmutableList(copyIntoArray(new Object[] { paramE1, paramE2, paramE3, paramE4, paramE5 }), null);
  }

  public static <E> ImmutableList<E> of(E[] paramArrayOfE)
  {
    if (paramArrayOfE.length == 0)
      return of();
    return new RegularImmutableList(copyIntoArray(paramArrayOfE), null);
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws InvalidObjectException
  {
    throw new InvalidObjectException("Use SerializedForm");
  }

  public final void add(int paramInt, E paramE)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean addAll(int paramInt, Collection<? extends E> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public abstract int indexOf(@Nullable Object paramObject);

  public abstract UnmodifiableIterator<E> iterator();

  public abstract int lastIndexOf(@Nullable Object paramObject);

  public final E remove(int paramInt)
  {
    throw new UnsupportedOperationException();
  }

  public final E set(int paramInt, E paramE)
  {
    throw new UnsupportedOperationException();
  }

  public abstract ImmutableList<E> subList(int paramInt1, int paramInt2);

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  public static class Builder<E>
  {
    private final ArrayList<E> contents = Lists.newArrayList();

    public Builder<E> add(E paramE)
    {
      Preconditions.checkNotNull(paramE, "element cannot be null");
      this.contents.add(paramE);
      return this;
    }

    public Builder<E> addAll(Iterable<? extends E> paramIterable)
    {
      if ((paramIterable instanceof Collection))
      {
        Collection localCollection = (Collection)paramIterable;
        this.contents.ensureCapacity(this.contents.size() + localCollection.size());
      }
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        Preconditions.checkNotNull(localObject, "elements contains a null");
        this.contents.add(localObject);
      }
      return this;
    }

    public ImmutableList<E> build()
    {
      return ImmutableList.copyOf(this.contents);
    }
  }

  private static final class EmptyImmutableList extends ImmutableList<Object>
  {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    private EmptyImmutableList()
    {
      super();
    }

    public boolean contains(Object paramObject)
    {
      return false;
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      return paramCollection.isEmpty();
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if ((paramObject instanceof List))
        return ((List)paramObject).isEmpty();
      return false;
    }

    public Object get(int paramInt)
    {
      Preconditions.checkElementIndex(paramInt, 0);
      throw new AssertionError("unreachable");
    }

    public int hashCode()
    {
      return 1;
    }

    public int indexOf(Object paramObject)
    {
      return -1;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public UnmodifiableIterator<Object> iterator()
    {
      return Iterators.emptyIterator();
    }

    public int lastIndexOf(Object paramObject)
    {
      return -1;
    }

    public ListIterator<Object> listIterator()
    {
      return Iterators.emptyListIterator();
    }

    public ListIterator<Object> listIterator(int paramInt)
    {
      Preconditions.checkPositionIndex(paramInt, 0);
      return Iterators.emptyListIterator();
    }

    public int size()
    {
      return 0;
    }

    public ImmutableList<Object> subList(int paramInt1, int paramInt2)
    {
      Preconditions.checkPositionIndexes(paramInt1, paramInt2, 0);
      return this;
    }

    public Object[] toArray()
    {
      return EMPTY_ARRAY;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      if (paramArrayOfT.length > 0)
        paramArrayOfT[0] = null;
      return paramArrayOfT;
    }

    public String toString()
    {
      return "[]";
    }
  }

  private static final class RegularImmutableList<E> extends ImmutableList<E>
  {
    private final Object[] array;
    private final int offset;
    private final int size;

    private RegularImmutableList(Object[] paramArrayOfObject)
    {
      this(paramArrayOfObject, 0, paramArrayOfObject.length);
    }

    private RegularImmutableList(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
    {
      super();
      this.offset = paramInt1;
      this.size = paramInt2;
      this.array = paramArrayOfObject;
    }

    public boolean contains(Object paramObject)
    {
      return indexOf(paramObject) != -1;
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if (paramObject == this);
      while (true)
      {
        return true;
        if (!(paramObject instanceof List))
          return false;
        List localList = (List)paramObject;
        if (size() != localList.size())
          return false;
        int i = this.offset;
        if ((paramObject instanceof RegularImmutableList))
        {
          RegularImmutableList localRegularImmutableList = (RegularImmutableList)paramObject;
          int k = localRegularImmutableList.offset;
          while (k < localRegularImmutableList.offset + localRegularImmutableList.size)
          {
            Object[] arrayOfObject2 = this.array;
            int m = i + 1;
            if (!arrayOfObject2[i].equals(localRegularImmutableList.array[k]))
              return false;
            k++;
            i = m;
          }
        }
        else
        {
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            Object localObject = localIterator.next();
            Object[] arrayOfObject1 = this.array;
            int j = i + 1;
            if (!arrayOfObject1[i].equals(localObject))
              return false;
            i = j;
          }
        }
      }
    }

    public E get(int paramInt)
    {
      Preconditions.checkElementIndex(paramInt, this.size);
      return this.array[(paramInt + this.offset)];
    }

    public int hashCode()
    {
      int i = 1;
      for (int j = this.offset; j < this.offset + this.size; j++)
        i = i * 31 + this.array[j].hashCode();
      return i;
    }

    public int indexOf(Object paramObject)
    {
      if (paramObject != null)
        for (int i = this.offset; i < this.offset + this.size; i++)
          if (this.array[i].equals(paramObject))
            return i - this.offset;
      return -1;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.forArray(this.array, this.offset, this.size);
    }

    public int lastIndexOf(Object paramObject)
    {
      if (paramObject != null)
        for (int i = -1 + (this.offset + this.size); i >= this.offset; i--)
          if (this.array[i].equals(paramObject))
            return i - this.offset;
      return -1;
    }

    public ListIterator<E> listIterator()
    {
      return listIterator(0);
    }

    public ListIterator<E> listIterator(final int paramInt)
    {
      Preconditions.checkPositionIndex(paramInt, this.size);
      return new ListIterator()
      {
        int index = paramInt;

        public void add(E paramAnonymousE)
        {
          throw new UnsupportedOperationException();
        }

        public boolean hasNext()
        {
          return this.index < ImmutableList.RegularImmutableList.this.size;
        }

        public boolean hasPrevious()
        {
          return this.index > 0;
        }

        public E next()
        {
          try
          {
            Object localObject = ImmutableList.RegularImmutableList.this.get(this.index);
            this.index = (1 + this.index);
            return localObject;
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
          }
          throw new NoSuchElementException();
        }

        public int nextIndex()
        {
          return this.index;
        }

        public E previous()
        {
          try
          {
            Object localObject = ImmutableList.RegularImmutableList.this.get(-1 + this.index);
            this.index = (-1 + this.index);
            return localObject;
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
          }
          throw new NoSuchElementException();
        }

        public int previousIndex()
        {
          return -1 + this.index;
        }

        public void remove()
        {
          throw new UnsupportedOperationException();
        }

        public void set(E paramAnonymousE)
        {
          throw new UnsupportedOperationException();
        }
      };
    }

    public int size()
    {
      return this.size;
    }

    public ImmutableList<E> subList(int paramInt1, int paramInt2)
    {
      Preconditions.checkPositionIndexes(paramInt1, paramInt2, this.size);
      if (paramInt1 == paramInt2)
        return ImmutableList.of();
      return new RegularImmutableList(this.array, paramInt1 + this.offset, paramInt2 - paramInt1);
    }

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(this.array, this.offset, arrayOfObject, 0, this.size);
      return arrayOfObject;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      if (paramArrayOfT.length < this.size)
        paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, this.size);
      while (true)
      {
        System.arraycopy(this.array, this.offset, paramArrayOfT, 0, this.size);
        return paramArrayOfT;
        if (paramArrayOfT.length > this.size)
          paramArrayOfT[this.size] = null;
      }
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(16 * size());
      localStringBuilder.append('[').append(this.array[this.offset]);
      for (int i = 1 + this.offset; i < this.offset + this.size; i++)
        localStringBuilder.append(", ").append(this.array[i]);
      return ']';
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
      return ImmutableList.of(this.elements);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ImmutableList
 * JD-Core Version:    0.6.2
 */