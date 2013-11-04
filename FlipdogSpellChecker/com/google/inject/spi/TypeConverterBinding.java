package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.Preconditions;
import com.google.inject.matcher.Matcher;

public final class TypeConverterBinding
  implements Element
{
  private final Object source;
  private final TypeConverter typeConverter;
  private final Matcher<? super TypeLiteral<?>> typeMatcher;

  TypeConverterBinding(Object paramObject, Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.typeMatcher = ((Matcher)Preconditions.checkNotNull(paramMatcher, "typeMatcher"));
    this.typeConverter = ((TypeConverter)Preconditions.checkNotNull(paramTypeConverter, "typeConverter"));
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    paramBinder.withSource(getSource()).convertToTypes(this.typeMatcher, this.typeConverter);
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
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.TypeConverterBinding
 * JD-Core Version:    0.6.2
 */