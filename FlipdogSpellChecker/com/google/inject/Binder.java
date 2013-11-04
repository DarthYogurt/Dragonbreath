package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import java.lang.annotation.Annotation;

public abstract interface Binder
{
  public abstract void addError(Message paramMessage);

  public abstract void addError(String paramString, Object[] paramArrayOfObject);

  public abstract void addError(Throwable paramThrowable);

  public abstract <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> AnnotatedBindingBuilder<T> bind(Class<T> paramClass);

  public abstract <T> LinkedBindingBuilder<T> bind(Key<T> paramKey);

  public abstract AnnotatedConstantBindingBuilder bindConstant();

  public abstract void bindListener(Matcher<? super TypeLiteral<?>> paramMatcher, TypeListener paramTypeListener);

  public abstract void bindScope(Class<? extends Annotation> paramClass, Scope paramScope);

  public abstract void convertToTypes(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter);

  public abstract Stage currentStage();

  public abstract <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> MembersInjector<T> getMembersInjector(Class<T> paramClass);

  public abstract <T> Provider<T> getProvider(Key<T> paramKey);

  public abstract <T> Provider<T> getProvider(Class<T> paramClass);

  public abstract void install(Module paramModule);

  public abstract PrivateBinder newPrivateBinder();

  public abstract <T> void requestInjection(TypeLiteral<T> paramTypeLiteral, T paramT);

  public abstract void requestInjection(Object paramObject);

  public abstract void requestStaticInjection(Class<?>[] paramArrayOfClass);

  public abstract Binder skipSources(Class[] paramArrayOfClass);

  public abstract Binder withSource(Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Binder
 * JD-Core Version:    0.6.2
 */