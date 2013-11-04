package com.google.inject.internal;

public class ErrorsException extends Exception
{
  private final Errors errors;

  public ErrorsException(Errors paramErrors)
  {
    this.errors = paramErrors;
  }

  public Errors getErrors()
  {
    return this.errors;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ErrorsException
 * JD-Core Version:    0.6.2
 */