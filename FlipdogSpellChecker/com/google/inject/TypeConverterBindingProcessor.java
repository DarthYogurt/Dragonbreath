package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.MatcherAndConverter;
import com.google.inject.internal.SourceProvider;
import com.google.inject.internal.Strings;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeConverterBinding;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

class TypeConverterBindingProcessor extends AbstractProcessor
{
  TypeConverterBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  private <T> void convertToClass(Class<T> paramClass, TypeConverter paramTypeConverter)
  {
    convertToClasses(Matchers.identicalTo(paramClass), paramTypeConverter);
  }

  private void convertToClasses(final Matcher<? super Class<?>> paramMatcher, TypeConverter paramTypeConverter)
  {
    internalConvertToTypes(new AbstractMatcher()
    {
      public boolean matches(TypeLiteral<?> paramAnonymousTypeLiteral)
      {
        Type localType = paramAnonymousTypeLiteral.getType();
        if (!(localType instanceof Class))
          return false;
        Class localClass = (Class)localType;
        return paramMatcher.matches(localClass);
      }

      public String toString()
      {
        return paramMatcher.toString();
      }
    }
    , paramTypeConverter);
  }

  private <T> void convertToPrimitiveType(Class<T> paramClass1, final Class<T> paramClass2)
  {
    try
    {
      convertToClass(paramClass2, new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral<?> paramAnonymousTypeLiteral)
        {
          try
          {
            Object localObject = this.val$parser.invoke(null, new Object[] { paramAnonymousString });
            return localObject;
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException(localInvocationTargetException.getTargetException().getMessage());
          }
        }

        public String toString()
        {
          return "TypeConverter<" + paramClass2.getSimpleName() + ">";
        }
      });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
  }

  private void internalConvertToTypes(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter)
  {
    this.injector.state.addConverter(new MatcherAndConverter(paramMatcher, paramTypeConverter, SourceProvider.UNKNOWN_SOURCE));
  }

  public void prepareBuiltInConverters(InjectorImpl paramInjectorImpl)
  {
    this.injector = paramInjectorImpl;
    try
    {
      convertToPrimitiveType(Integer.TYPE, Integer.class);
      convertToPrimitiveType(Long.TYPE, Long.class);
      convertToPrimitiveType(Boolean.TYPE, Boolean.class);
      convertToPrimitiveType(Byte.TYPE, Byte.class);
      convertToPrimitiveType(Short.TYPE, Short.class);
      convertToPrimitiveType(Float.TYPE, Float.class);
      convertToPrimitiveType(Double.TYPE, Double.class);
      convertToClass(Character.class, new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral<?> paramAnonymousTypeLiteral)
        {
          String str = paramAnonymousString.trim();
          if (str.length() != 1)
            throw new RuntimeException("Length != 1.");
          return Character.valueOf(str.charAt(0));
        }

        public String toString()
        {
          return "TypeConverter<Character>";
        }
      });
      convertToClasses(Matchers.subclassesOf(Enum.class), new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral<?> paramAnonymousTypeLiteral)
        {
          return Enum.valueOf(paramAnonymousTypeLiteral.getRawType(), paramAnonymousString);
        }

        public String toString()
        {
          return "TypeConverter<E extends Enum<E>>";
        }
      });
      internalConvertToTypes(new AbstractMatcher()
      {
        public boolean matches(TypeLiteral<?> paramAnonymousTypeLiteral)
        {
          return paramAnonymousTypeLiteral.getRawType() == Class.class;
        }

        public String toString()
        {
          return "Class<?>";
        }
      }
      , new TypeConverter()
      {
        public Object convert(String paramAnonymousString, TypeLiteral<?> paramAnonymousTypeLiteral)
        {
          try
          {
            Class localClass = Class.forName(paramAnonymousString);
            return localClass;
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            throw new RuntimeException(localClassNotFoundException.getMessage());
          }
        }

        public String toString()
        {
          return "TypeConverter<Class<?>>";
        }
      });
      return;
    }
    finally
    {
      this.injector = null;
    }
  }

  public Boolean visit(TypeConverterBinding paramTypeConverterBinding)
  {
    this.injector.state.addConverter(new MatcherAndConverter(paramTypeConverterBinding.getTypeMatcher(), paramTypeConverterBinding.getTypeConverter(), paramTypeConverterBinding.getSource()));
    return Boolean.valueOf(true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.TypeConverterBindingProcessor
 * JD-Core Version:    0.6.2
 */