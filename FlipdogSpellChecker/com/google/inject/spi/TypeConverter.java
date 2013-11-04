package com.google.inject.spi;

import com.google.inject.TypeLiteral;

public abstract interface TypeConverter
{
  public abstract Object convert(String paramString, TypeLiteral<?> paramTypeLiteral);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.TypeConverter
 * JD-Core Version:    0.6.2
 */