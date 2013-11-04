package com.google.inject.internal;

import java.lang.annotation.Annotation;

public class Nullability
{
  public static boolean allowsNull(Annotation[] paramArrayOfAnnotation)
  {
    int i = paramArrayOfAnnotation.length;
    for (int j = 0; j < i; j++)
      if ("Nullable".equals(paramArrayOfAnnotation[j].annotationType().getSimpleName()))
        return true;
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Nullability
 * JD-Core Version:    0.6.2
 */