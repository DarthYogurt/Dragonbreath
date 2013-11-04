package com.google.inject.spi;

import com.google.inject.TypeLiteral;

public abstract interface TypeListener
{
  public abstract <I> void hear(TypeLiteral<I> paramTypeLiteral, TypeEncounter<I> paramTypeEncounter);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.TypeListener
 * JD-Core Version:    0.6.2
 */