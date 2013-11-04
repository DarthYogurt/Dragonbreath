package com.google.inject;

import java.lang.reflect.Constructor;

class Reflection
{
  static <T> Constructor<T> invalidConstructor()
  {
    try
    {
      Constructor localConstructor = InvalidConstructor.class.getDeclaredConstructor(new Class[0]);
      return localConstructor;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
  }

  static class InvalidConstructor
  {
    InvalidConstructor()
    {
      throw new AssertionError();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Reflection
 * JD-Core Version:    0.6.2
 */