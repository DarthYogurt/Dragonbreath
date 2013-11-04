package com.google.inject;

import com.google.inject.internal.Sets;
import java.util.Set;

final class WeakKeySet
{
  private Set<String> backingSet = Sets.newHashSet();

  public boolean add(Key<?> paramKey)
  {
    return this.backingSet.add(paramKey.toString());
  }

  public boolean contains(Object paramObject)
  {
    return ((paramObject instanceof Key)) && (this.backingSet.contains(paramObject.toString()));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.WeakKeySet
 * JD-Core Version:    0.6.2
 */