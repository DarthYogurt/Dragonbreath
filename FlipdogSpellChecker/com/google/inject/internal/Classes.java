package com.google.inject.internal;

import java.lang.reflect.Modifier;

public class Classes
{
  public static boolean isConcrete(Class<?> paramClass)
  {
    int i = paramClass.getModifiers();
    return (!paramClass.isInterface()) && (!Modifier.isAbstract(i));
  }

  public static boolean isInnerClass(Class<?> paramClass)
  {
    return (!Modifier.isStatic(paramClass.getModifiers())) && (paramClass.getEnclosingClass() != null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Classes
 * JD-Core Version:    0.6.2
 */