package com.google.inject.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class ImmutableSet<E> extends ImmutableCollection<E>
  implements Set<E>
{
  private static final ImmutableSet<?> EMPTY_IMMUTABLE_SET = new EmptyImmutableSet(null);

  public static <E> Builder<E> builder()
  {
    return new Builder();
  }

  public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> paramIterable)
  {
    if ((paramIterable instanceof ImmutableSet))
      return (ImmutableSet)paramIterable;
    return copyOfInternal(Collections2.toCollection(paramIterable));
  }

  public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> paramIterator)
  {
    return copyOfInternal(Lists.newArrayList(paramIterator));
  }

  private static <E> ImmutableSet<E> copyOfInternal(Collection<? extends E> paramCollection)
  {
    switch (paramCollection.size())
    {
    default:
      return create(paramCollection, paramCollection.size());
    case 0:
      return of();
    case 1:
    }
    return of(paramCollection.iterator().next());
  }

  private static <E> ImmutableSet<E> create(Iterable<? extends E> paramIterable, int paramInt)
  {
    int i = Hashing.chooseTableSize(paramInt);
    Object[] arrayOfObject = new Object[i];
    int j = i - 1;
    ArrayList localArrayList = new ArrayList(paramInt);
    int k = 0;
    Iterator localIterator = paramIterable.iterator();
    if (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      int m = localObject1.hashCode();
      for (int n = Hashing.smear(m); ; n++)
      {
        int i1 = n & j;
        Object localObject2 = arrayOfObject[i1];
        if (localObject2 == null)
        {
          arrayOfObject[i1] = localObject1;
          localArrayList.add(localObject1);
          k += m;
          break;
        }
        if (localObject2.equals(localObject1))
          break;
      }
    }
    if (localArrayList.size() == 1)
      return new SingletonImmutableSet(localArrayList.get(0), k);
    return new RegularImmutableSet(localArrayList.toArray(), k, arrayOfObject, j);
  }

  public static <E> ImmutableSet<E> of()
  {
    return EMPTY_IMMUTABLE_SET;
  }

  public static <E> ImmutableSet<E> of(E paramE)
  {
    return new SingletonImmutableSet(paramE, paramE.hashCode());
  }

  public static <E> ImmutableSet<E> of(E[] paramArrayOfE)
  {
    switch (paramArrayOfE.length)
    {
    default:
      return create(Arrays.asList(paramArrayOfE), paramArrayOfE.length);
    case 0:
      return of();
    case 1:
    }
    return of(paramArrayOfE[0]);
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (((paramObject instanceof ImmutableSet)) && (isHashCodeFast()) && (((ImmutableSet)paramObject).isHashCodeFast()) && (hashCode() != paramObject.hashCode()))
      return false;
    return Collections2.setEquals(this, paramObject);
  }

  public int hashCode()
  {
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      i += localIterator.next().hashCode();
    return i;
  }

  boolean isHashCodeFast()
  {
    return false;
  }

  public abstract UnmodifiableIterator<E> iterator();

  public String toString()
  {
    if (isEmpty())
      return "[]";
    UnmodifiableIterator localUnmodifiableIterator = iterator();
    StringBuilder localStringBuilder = new StringBuilder(16 * size());
    localStringBuilder.append('[').append(localUnmodifiableIterator.next().toString());
    for (int i = 1; i < size(); i++)
      localStringBuilder.append(", ").append(localUnmodifiableIterator.next().toString());
    return ']';
  }

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  static abstract class ArrayImmutableSet<E> extends ImmutableSet<E>
  {
    final Object[] elements;

    ArrayImmutableSet(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      if (paramCollection == this);
      while (true)
      {
        return true;
        if (!(paramCollection instanceof ArrayImmutableSet))
          return super.containsAll(paramCollection);
        if (paramCollection.size() > size())
          return false;
        Object[] arrayOfObject = ((ArrayImmutableSet)paramCollection).elements;
        int i = arrayOfObject.length;
        for (int j = 0; j < i; j++)
          if (!contains(arrayOfObject[j]))
            return false;
      }
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.forArray(this.elements);
    }

    public int size()
    {
      return this.elements.length;
    }

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(this.elements, 0, arrayOfObject, 0, size());
      return arrayOfObject;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      if (paramArrayOfT.length < i)
        paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, i);
      while (true)
      {
        System.arraycopy(this.elements, 0, paramArrayOfT, 0, i);
        return paramArrayOfT;
        if (paramArrayOfT.length > i)
          paramArrayOfT[i] = null;
      }
    }
  }

  public static class Builder<E>
  {
    final ArrayList<E> contents = Lists.newArrayList();

    public Builder<E> add(E paramE)
    {
      Preconditions.checkNotNull(paramE, "element cannot be null");
      this.contents.add(paramE);
      return this;
    }

    public Builder<E> add(E[] paramArrayOfE)
    {
      Preconditions.checkNotNull(paramArrayOfE, "elements cannot be null");
      List localList = Arrays.asList(paramArrayOfE);
      Preconditions.checkContentsNotNull(localList, "elements cannot contain null");
      this.contents.addAll(localList);
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

    public Builder<E> addAll(Iterator<? extends E> paramIterator)
    {
      while (paramIterator.hasNext())
      {
        Object localObject = paramIterator.next();
        Preconditions.checkNotNull(localObject, "element cannot be null");
        this.contents.add(localObject);
      }
      return this;
    }

    public ImmutableSet<E> build()
    {
      return ImmutableSet.copyOf(this.contents);
    }
  }

  private static final class EmptyImmutableSet extends ImmutableSet<Object>
  {
    private static final Object[] EMPTY_ARRAY = new Object[0];

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
      if ((paramObject instanceof Set))
        return ((Set)paramObject).isEmpty();
      return false;
    }

    public final int hashCode()
    {
      return 0;
    }

    public boolean isEmpty()
    {
      return true;
    }

    boolean isHashCodeFast()
    {
      return true;
    }

    public UnmodifiableIterator<Object> iterator()
    {
      return Iterators.emptyIterator();
    }

    public int size()
    {
      return 0;
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

  private static final class RegularImmutableSet<E> extends ImmutableSet.ArrayImmutableSet<E>
  {
    final int hashCode;
    final int mask;
    final Object[] table;

    RegularImmutableSet(Object[] paramArrayOfObject1, int paramInt1, Object[] paramArrayOfObject2, int paramInt2)
    {
      super();
      this.table = paramArrayOfObject2;
      this.mask = paramInt2;
      this.hashCode = paramInt1;
    }

    public boolean contains(Object paramObject)
    {
      if (paramObject == null)
        return false;
      for (int i = Hashing.smear(paramObject.hashCode()); ; i++)
      {
        Object localObject = this.table[(i & this.mask)];
        if (localObject == null)
          break;
        if (localObject.equals(paramObject))
          return true;
      }
    }

    public int hashCode()
    {
      return this.hashCode;
    }

    boolean isHashCodeFast()
    {
      return true;
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
      return ImmutableSet.of(this.elements);
    }
  }

  private static final class SingletonImmutableSet<E> extends ImmutableSet<E>
  {
    final E element;
    final int hashCode;

    SingletonImmutableSet(E paramE, int paramInt)
    {
      this.element = paramE;
      this.hashCode = paramInt;
    }

    public boolean contains(Object paramObject)
    {
      return this.element.equals(paramObject);
    }

    public boolean equals(@Nullable Object paramObject)
    {
      if (paramObject == this);
      Set localSet;
      do
      {
        return true;
        if (!(paramObject instanceof Set))
          break;
        localSet = (Set)paramObject;
      }
      while ((localSet.size() == 1) && (this.element.equals(localSet.iterator().next())));
      return false;
      return false;
    }

    public final int hashCode()
    {
      return this.hashCode;
    }

    public boolean isEmpty()
    {
      return false;
    }

    boolean isHashCodeFast()
    {
      return true;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.singletonIterator(this.element);
    }

    public int size()
    {
      return 1;
    }

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.element;
      return arrayOfObject;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      if (paramArrayOfT.length == 0)
        paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, 1);
      while (true)
      {
        paramArrayOfT[0] = this.element;
        return paramArrayOfT;
        if (paramArrayOfT.length > 1)
          paramArrayOfT[1] = null;
      }
    }

    public String toString()
    {
      String str = this.element.toString();
      return 2 + str.length() + '[' + str + ']';
    }
  }

  static abstract class TransformedImmutableSet<D, E> extends ImmutableSet<E>
  {
    final int hashCode;
    final D[] source;

    TransformedImmutableSet(D[] paramArrayOfD, int paramInt)
    {
      this.source = paramArrayOfD;
      this.hashCode = paramInt;
    }

    public final int hashCode()
    {
      return this.hashCode;
    }

    public boolean isEmpty()
    {
      return false;
    }

    boolean isHashCodeFast()
    {
      return true;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.unmodifiableIterator(new AbstractIterator()
      {
        int index = 0;

        protected E computeNext()
        {
          if (this.index < ImmutableSet.TransformedImmutableSet.this.source.length)
          {
            ImmutableSet.TransformedImmutableSet localTransformedImmutableSet = ImmutableSet.TransformedImmutableSet.this;
            Object[] arrayOfObject = ImmutableSet.TransformedImmutableSet.this.source;
            int i = this.index;
            this.index = (i + 1);
            return localTransformedImmutableSet.transform(arrayOfObject[i]);
          }
          return endOfData();
        }
      });
    }

    public int size()
    {
      return this.source.length;
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
        for (int j = 0; j < this.source.length; j++)
          paramArrayOfT[j] = transform(this.source[j]);
        if (paramArrayOfT.length > i)
          paramArrayOfT[i] = null;
      }
      return paramArrayOfT;
    }

    abstract E transform(D paramD);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ImmutableSet
 * JD-Core Version:    0.6.2
 */