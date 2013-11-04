package com.google.inject.internal;

import java.util.Collection;
import java.util.Set;

public final class Collections2
{
  static boolean setEquals(Set<?> paramSet, @Nullable Object paramObject)
  {
    if (paramObject == paramSet);
    Set localSet;
    do
    {
      return true;
      if (!(paramObject instanceof Set))
        break;
      localSet = (Set)paramObject;
    }
    while ((paramSet.size() == localSet.size()) && (paramSet.containsAll(localSet)));
    return false;
    return false;
  }

  static <E> Collection<E> toCollection(Iterable<E> paramIterable)
  {
    if ((paramIterable instanceof Collection))
      return (Collection)paramIterable;
    return Lists.newArrayList(paramIterable);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Collections2
 * JD-Core Version:    0.6.2
 */