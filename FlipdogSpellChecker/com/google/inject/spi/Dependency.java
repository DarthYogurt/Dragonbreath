package com.google.inject.spi;

import com.google.inject.Key;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class Dependency<T>
{
  private final InjectionPoint injectionPoint;
  private final Key<T> key;
  private final boolean nullable;
  private final int parameterIndex;

  Dependency(InjectionPoint paramInjectionPoint, Key<T> paramKey, boolean paramBoolean, int paramInt)
  {
    this.injectionPoint = paramInjectionPoint;
    this.key = paramKey;
    this.nullable = paramBoolean;
    this.parameterIndex = paramInt;
  }

  public static Set<Dependency<?>> forInjectionPoints(Set<InjectionPoint> paramSet)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
      localArrayList.addAll(((InjectionPoint)localIterator.next()).getDependencies());
    return ImmutableSet.copyOf(localArrayList);
  }

  public static <T> Dependency<T> get(Key<T> paramKey)
  {
    return new Dependency(null, paramKey, true, -1);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Dependency;
    boolean bool2 = false;
    if (bool1)
    {
      Dependency localDependency = (Dependency)paramObject;
      boolean bool3 = Objects.equal(this.injectionPoint, localDependency.injectionPoint);
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = Objects.equal(Integer.valueOf(this.parameterIndex), Integer.valueOf(localDependency.parameterIndex));
        bool2 = false;
        if (bool4)
        {
          boolean bool5 = Objects.equal(this.key, localDependency.key);
          bool2 = false;
          if (bool5)
            bool2 = true;
        }
      }
    }
    return bool2;
  }

  public InjectionPoint getInjectionPoint()
  {
    return this.injectionPoint;
  }

  public Key<T> getKey()
  {
    return this.key;
  }

  public int getParameterIndex()
  {
    return this.parameterIndex;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.injectionPoint;
    arrayOfObject[1] = Integer.valueOf(this.parameterIndex);
    arrayOfObject[2] = this.key;
    return Objects.hashCode(arrayOfObject);
  }

  public boolean isNullable()
  {
    return this.nullable;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.key);
    if (this.injectionPoint != null)
    {
      localStringBuilder.append("@").append(this.injectionPoint);
      if (this.parameterIndex != -1)
        localStringBuilder.append("[").append(this.parameterIndex).append("]");
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.Dependency
 * JD-Core Version:    0.6.2
 */