package com.google.inject.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

public class StackTraceElements
{
  public static Object forMember(Member paramMember)
  {
    if (paramMember == null)
      return SourceProvider.UNKNOWN_SOURCE;
    Class localClass = paramMember.getDeclaringClass();
    if (MoreTypes.memberType(paramMember) == Constructor.class);
    for (String str = "<init>"; ; str = paramMember.getName())
      return new StackTraceElement(localClass.getName(), str, null, -1);
  }

  public static Object forType(Class<?> paramClass)
  {
    return new StackTraceElement(paramClass.getName(), "class", null, -1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.StackTraceElements
 * JD-Core Version:    0.6.2
 */