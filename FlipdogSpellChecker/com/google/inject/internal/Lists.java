package com.google.inject.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public final class Lists
{
  static int computeArrayListCapacity(int paramInt)
  {
    if (paramInt >= 0);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool);
      return (int)Math.min(5L + paramInt + paramInt / 10, 2147483647L);
    }
  }

  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList();
  }

  public static <E> ArrayList<E> newArrayList(Iterable<? extends E> paramIterable)
  {
    if ((paramIterable instanceof Collection))
      return new ArrayList((Collection)paramIterable);
    return newArrayList(paramIterable.iterator());
  }

  public static <E> ArrayList<E> newArrayList(@Nullable E paramE, E[] paramArrayOfE)
  {
    ArrayList localArrayList = new ArrayList(1 + paramArrayOfE.length);
    localArrayList.add(paramE);
    int i = paramArrayOfE.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(paramArrayOfE[j]);
    return localArrayList;
  }

  public static <E> ArrayList<E> newArrayList(Iterator<? extends E> paramIterator)
  {
    ArrayList localArrayList = newArrayList();
    while (paramIterator.hasNext())
      localArrayList.add(paramIterator.next());
    return localArrayList;
  }

  public static <E> ArrayList<E> newArrayList(E[] paramArrayOfE)
  {
    ArrayList localArrayList = new ArrayList(computeArrayListCapacity(paramArrayOfE.length));
    Collections.addAll(localArrayList, paramArrayOfE);
    return localArrayList;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Lists
 * JD-Core Version:    0.6.2
 */