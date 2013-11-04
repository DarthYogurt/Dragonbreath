package com.google.inject.internal;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public final class Iterators
{
  static final Iterator<Object> EMPTY_ITERATOR = new UnmodifiableIterator()
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
  private static final ListIterator<Object> EMPTY_LIST_ITERATOR = new ListIterator()
  {
    public void add(Object paramAnonymousObject)
    {
      throw new UnsupportedOperationException();
    }

    public boolean hasNext()
    {
      return false;
    }

    public boolean hasPrevious()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }

    public int nextIndex()
    {
      return 0;
    }

    public Object previous()
    {
      throw new NoSuchElementException();
    }

    public int previousIndex()
    {
      return -1;
    }

    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    public void set(Object paramAnonymousObject)
    {
      throw new UnsupportedOperationException();
    }
  };

  public static <T> Enumeration<T> asEnumeration(Iterator<T> paramIterator)
  {
    Preconditions.checkNotNull(paramIterator);
    return new Enumeration()
    {
      public boolean hasMoreElements()
      {
        return this.val$iterator.hasNext();
      }

      public T nextElement()
      {
        return this.val$iterator.next();
      }
    };
  }

  public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> paramIterator)
  {
    Preconditions.checkNotNull(paramIterator);
    return new Iterator()
    {
      Iterator<? extends T> current = Iterators.emptyIterator();
      Iterator<? extends T> removeFrom;

      public boolean hasNext()
      {
        while ((!this.current.hasNext()) && (this.val$inputs.hasNext()))
          this.current = ((Iterator)this.val$inputs.next());
        return this.current.hasNext();
      }

      public T next()
      {
        if (!hasNext())
          throw new NoSuchElementException();
        this.removeFrom = this.current;
        return this.current.next();
      }

      public void remove()
      {
        if (this.removeFrom != null);
        for (boolean bool = true; ; bool = false)
        {
          Preconditions.checkState(bool, "no calls to next() since last call to remove()");
          this.removeFrom.remove();
          this.removeFrom = null;
          return;
        }
      }
    };
  }

  public static <T> UnmodifiableIterator<T> emptyIterator()
  {
    return (UnmodifiableIterator)EMPTY_ITERATOR;
  }

  public static <T> ListIterator<T> emptyListIterator()
  {
    return EMPTY_LIST_ITERATOR;
  }

  public static <T> UnmodifiableIterator<T> forArray(T[] paramArrayOfT)
  {
    return new UnmodifiableIterator()
    {
      int i = 0;
      final int length = this.val$array.length;

      public boolean hasNext()
      {
        return this.i < this.length;
      }

      public T next()
      {
        try
        {
          Object localObject = this.val$array[this.i];
          this.i = (1 + this.i);
          return localObject;
        }
        catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
        {
        }
        throw new NoSuchElementException();
      }
    };
  }

  public static <T> UnmodifiableIterator<T> forArray(final T[] paramArrayOfT, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 0);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool);
      final int i = paramInt1 + paramInt2;
      Preconditions.checkPositionIndexes(paramInt1, i, paramArrayOfT.length);
      return new UnmodifiableIterator()
      {
        int i = this.val$offset;

        public boolean hasNext()
        {
          return this.i < i;
        }

        public T next()
        {
          if (!hasNext())
            throw new NoSuchElementException();
          Object[] arrayOfObject = paramArrayOfT;
          int j = this.i;
          this.i = (j + 1);
          return arrayOfObject[j];
        }
      };
    }
  }

  public static <T> T getOnlyElement(Iterator<T> paramIterator)
  {
    Object localObject = paramIterator.next();
    if (!paramIterator.hasNext())
      return localObject;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("expected one element but was: <" + localObject);
    for (int i = 0; (i < 4) && (paramIterator.hasNext()); i++)
      localStringBuilder.append(", " + paramIterator.next());
    if (paramIterator.hasNext())
      localStringBuilder.append(", ...");
    localStringBuilder.append(">");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public static <T> UnmodifiableIterator<T> singletonIterator(@Nullable T paramT)
  {
    return new UnmodifiableIterator()
    {
      boolean done;

      public boolean hasNext()
      {
        return !this.done;
      }

      public T next()
      {
        if (this.done)
          throw new NoSuchElementException();
        this.done = true;
        return this.val$value;
      }
    };
  }

  public static String toString(Iterator<?> paramIterator)
  {
    if (!paramIterator.hasNext())
      return "[]";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(paramIterator.next());
    while (paramIterator.hasNext())
      localStringBuilder.append(", ").append(paramIterator.next());
    return ']';
  }

  public static <F, T> Iterator<T> transform(Iterator<F> paramIterator, final Function<? super F, ? extends T> paramFunction)
  {
    Preconditions.checkNotNull(paramIterator);
    Preconditions.checkNotNull(paramFunction);
    return new Iterator()
    {
      public boolean hasNext()
      {
        return this.val$fromIterator.hasNext();
      }

      public T next()
      {
        Object localObject = this.val$fromIterator.next();
        return paramFunction.apply(localObject);
      }

      public void remove()
      {
        this.val$fromIterator.remove();
      }
    };
  }

  public static <T> UnmodifiableIterator<T> unmodifiableIterator(Iterator<T> paramIterator)
  {
    Preconditions.checkNotNull(paramIterator);
    return new UnmodifiableIterator()
    {
      public boolean hasNext()
      {
        return this.val$iterator.hasNext();
      }

      public T next()
      {
        return this.val$iterator.next();
      }
    };
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Iterators
 * JD-Core Version:    0.6.2
 */