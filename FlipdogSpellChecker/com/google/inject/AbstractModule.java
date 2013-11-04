package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import java.lang.annotation.Annotation;

public abstract class AbstractModule
  implements Module
{
  Binder binder;

  protected void addError(Message paramMessage)
  {
    this.binder.addError(paramMessage);
  }

  protected void addError(String paramString, Object[] paramArrayOfObject)
  {
    this.binder.addError(paramString, paramArrayOfObject);
  }

  protected void addError(Throwable paramThrowable)
  {
    this.binder.addError(paramThrowable);
  }

  protected <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> paramTypeLiteral)
  {
    return this.binder.bind(paramTypeLiteral);
  }

  protected <T> AnnotatedBindingBuilder<T> bind(Class<T> paramClass)
  {
    return this.binder.bind(paramClass);
  }

  protected <T> LinkedBindingBuilder<T> bind(Key<T> paramKey)
  {
    return this.binder.bind(paramKey);
  }

  protected AnnotatedConstantBindingBuilder bindConstant()
  {
    return this.binder.bindConstant();
  }

  protected void bindListener(Matcher<? super TypeLiteral<?>> paramMatcher, TypeListener paramTypeListener)
  {
    this.binder.bindListener(paramMatcher, paramTypeListener);
  }

  protected void bindScope(Class<? extends Annotation> paramClass, Scope paramScope)
  {
    this.binder.bindScope(paramClass, paramScope);
  }

  protected Binder binder()
  {
    return this.binder;
  }

  protected abstract void configure();

  public final void configure(Binder paramBinder)
  {
    try
    {
      if (this.binder == null)
      {
        bool = true;
        Preconditions.checkState(bool, "Re-entry is not allowed.");
        this.binder = ((Binder)Preconditions.checkNotNull(paramBinder, "builder"));
      }
    }
    finally
    {
      try
      {
        configure();
        this.binder = null;
        return;
        boolean bool = false;
      }
      finally
      {
        this.binder = null;
      }
    }
  }

  protected void convertToTypes(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter)
  {
    this.binder.convertToTypes(paramMatcher, paramTypeConverter);
  }

  protected Stage currentStage()
  {
    return this.binder.currentStage();
  }

  protected <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
  {
    return this.binder.getMembersInjector(paramTypeLiteral);
  }

  protected <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
  {
    return this.binder.getMembersInjector(paramClass);
  }

  protected <T> Provider<T> getProvider(Key<T> paramKey)
  {
    return this.binder.getProvider(paramKey);
  }

  protected <T> Provider<T> getProvider(Class<T> paramClass)
  {
    return this.binder.getProvider(paramClass);
  }

  protected void install(Module paramModule)
  {
    this.binder.install(paramModule);
  }

  protected void requestInjection(Object paramObject)
  {
    this.binder.requestInjection(paramObject);
  }

  protected void requestStaticInjection(Class<?>[] paramArrayOfClass)
  {
    this.binder.requestStaticInjection(paramArrayOfClass);
  }

  protected void requireBinding(Key<?> paramKey)
  {
    this.binder.getProvider(paramKey);
  }

  protected void requireBinding(Class<?> paramClass)
  {
    this.binder.getProvider(paramClass);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.AbstractModule
 * JD-Core Version:    0.6.2
 */