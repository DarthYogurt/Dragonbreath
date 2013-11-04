package com.google.inject.util;

import com.google.inject.Provider;

public final class Providers
{
  public static <T> Provider<T> of(T paramT)
  {
    return new Provider()
    {
      public T get()
      {
        return this.val$instance;
      }

      public String toString()
      {
        return "of(" + this.val$instance + ")";
      }
    };
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.util.Providers
 * JD-Core Version:    0.6.2
 */