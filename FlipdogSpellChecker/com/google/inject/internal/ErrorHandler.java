package com.google.inject.internal;

import com.google.inject.spi.Message;

public abstract interface ErrorHandler
{
  public abstract void handle(Message paramMessage);

  public abstract void handle(Object paramObject, Errors paramErrors);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ErrorHandler
 * JD-Core Version:    0.6.2
 */