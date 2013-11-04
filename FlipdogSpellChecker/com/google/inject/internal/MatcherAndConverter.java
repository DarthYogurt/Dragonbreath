package com.google.inject.internal;

import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeConverter;

public final class MatcherAndConverter
{
  private final Object source;
  private final TypeConverter typeConverter;
  private final Matcher<? super TypeLiteral<?>> typeMatcher;

  public MatcherAndConverter(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter, Object paramObject)
  {
    this.typeMatcher = ((Matcher)Preconditions.checkNotNull(paramMatcher, "type matcher"));
    this.typeConverter = ((TypeConverter)Preconditions.checkNotNull(paramTypeConverter, "converter"));
    this.source = paramObject;
  }

  public Object getSource()
  {
    return this.source;
  }

  public TypeConverter getTypeConverter()
  {
    return this.typeConverter;
  }

  public Matcher<? super TypeLiteral<?>> getTypeMatcher()
  {
    return this.typeMatcher;
  }

  public String toString()
  {
    return this.typeConverter + " which matches " + this.typeMatcher + " (bound at " + this.source + ")";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.MatcherAndConverter
 * JD-Core Version:    0.6.2
 */