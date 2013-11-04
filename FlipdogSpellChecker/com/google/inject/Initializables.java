package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;

class Initializables
{
  static <T> Initializable<T> of(T paramT)
  {
    return new Initializable()
    {
      public T get(Errors paramAnonymousErrors)
        throws ErrorsException
      {
        return this.val$instance;
      }

      public String toString()
      {
        return String.valueOf(this.val$instance);
      }
    };
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Initializables
 * JD-Core Version:    0.6.2
 */