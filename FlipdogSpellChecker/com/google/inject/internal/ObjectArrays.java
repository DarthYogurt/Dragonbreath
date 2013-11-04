package com.google.inject.internal;

import java.lang.reflect.Array;

public final class ObjectArrays
{
  public static <T> T[] newArray(T[] paramArrayOfT, int paramInt)
  {
    return (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ObjectArrays
 * JD-Core Version:    0.6.2
 */