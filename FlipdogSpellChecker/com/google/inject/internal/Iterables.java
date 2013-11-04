package com.google.inject.internal;

import java.util.Arrays;
import java.util.Iterator;

public final class Iterables
{
  public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> paramIterable)
  {
    return new IterableWithToString()
    {
      public Iterator<T> iterator()
      {
        return Iterators.concat(this.val$iterators.iterator());
      }
    };
  }

  public static <T> Iterable<T> concat(Iterable<? extends T> paramIterable1, Iterable<? extends T> paramIterable2)
  {
    Preconditions.checkNotNull(paramIterable1);
    Preconditions.checkNotNull(paramIterable2);
    return concat(Arrays.asList(new Iterable[] { paramIterable1, paramIterable2 }));
  }

  public static <T> T getOnlyElement(Iterable<T> paramIterable)
  {
    return Iterators.getOnlyElement(paramIterable.iterator());
  }

  public static String toString(Iterable<?> paramIterable)
  {
    return Iterators.toString(paramIterable.iterator());
  }

  public static <F, T> Iterable<T> transform(Iterable<F> paramIterable, final Function<? super F, ? extends T> paramFunction)
  {
    Preconditions.checkNotNull(paramIterable);
    Preconditions.checkNotNull(paramFunction);
    return new IterableWithToString()
    {
      public Iterator<T> iterator()
      {
        return Iterators.transform(this.val$fromIterable.iterator(), paramFunction);
      }
    };
  }

  static abstract class IterableWithToString<E>
    implements Iterable<E>
  {
    public String toString()
    {
      return Iterables.toString(this);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Iterables
 * JD-Core Version:    0.6.2
 */