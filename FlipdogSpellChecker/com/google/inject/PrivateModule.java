package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.AnnotatedElementBuilder;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.internal.Preconditions;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeConverter;
import com.google.inject.spi.TypeListener;
import java.lang.annotation.Annotation;

public abstract class PrivateModule
  implements Module
{
  private PrivateBinder binder;

  protected final void addError(Message paramMessage)
  {
    this.binder.addError(paramMessage);
  }

  protected final void addError(String paramString, Object[] paramArrayOfObject)
  {
    this.binder.addError(paramString, paramArrayOfObject);
  }

  protected final void addError(Throwable paramThrowable)
  {
    this.binder.addError(paramThrowable);
  }

  protected final <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> paramTypeLiteral)
  {
    return this.binder.bind(paramTypeLiteral);
  }

  protected final <T> AnnotatedBindingBuilder<T> bind(Class<T> paramClass)
  {
    return this.binder.bind(paramClass);
  }

  protected final <T> LinkedBindingBuilder<T> bind(Key<T> paramKey)
  {
    return this.binder.bind(paramKey);
  }

  protected final AnnotatedConstantBindingBuilder bindConstant()
  {
    return this.binder.bindConstant();
  }

  protected void bindListener(Matcher<? super TypeLiteral<?>> paramMatcher, TypeListener paramTypeListener)
  {
    this.binder.bindListener(paramMatcher, paramTypeListener);
  }

  protected final void bindScope(Class<? extends Annotation> paramClass, Scope paramScope)
  {
    this.binder.bindScope(paramClass, paramScope);
  }

  protected final PrivateBinder binder()
  {
    return this.binder;
  }

  protected abstract void configure();

  public final void configure(Binder paramBinder)
  {
    boolean bool = true;
    try
    {
      if (this.binder == null)
      {
        Preconditions.checkState(bool, "Re-entry is not allowed.");
        this.binder = ((PrivateBinder)paramBinder.skipSources(new Class[] { PrivateModule.class }));
      }
    }
    finally
    {
      try
      {
        configure();
        this.binder = null;
        return;
        bool = false;
      }
      finally
      {
        this.binder = null;
      }
    }
  }

  protected final void convertToTypes(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter)
  {
    this.binder.convertToTypes(paramMatcher, paramTypeConverter);
  }

  protected final Stage currentStage()
  {
    return this.binder.currentStage();
  }

  protected final AnnotatedElementBuilder expose(TypeLiteral<?> paramTypeLiteral)
  {
    return this.binder.expose(paramTypeLiteral);
  }

  protected final AnnotatedElementBuilder expose(Class<?> paramClass)
  {
    return this.binder.expose(paramClass);
  }

  protected final <T> void expose(Key<T> paramKey)
  {
    this.binder.expose(paramKey);
  }

  protected <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
  {
    return this.binder.getMembersInjector(paramTypeLiteral);
  }

  protected <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
  {
    return this.binder.getMembersInjector(paramClass);
  }

  protected final <T> Provider<T> getProvider(Key<T> paramKey)
  {
    return this.binder.getProvider(paramKey);
  }

  protected final <T> Provider<T> getProvider(Class<T> paramClass)
  {
    return this.binder.getProvider(paramClass);
  }

  protected final void install(Module paramModule)
  {
    this.binder.install(paramModule);
  }

  protected final void requestInjection(Object paramObject)
  {
    this.binder.requestInjection(paramObject);
  }

  protected final void requestStaticInjection(Class<?>[] paramArrayOfClass)
  {
    this.binder.requestStaticInjection(paramArrayOfClass);
  }

  protected final void requireBinding(Key<?> paramKey)
  {
    this.binder.getProvider(paramKey);
  }

  protected final void requireBinding(Class<?> paramClass)
  {
    this.binder.getProvider(paramClass);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.PrivateModule
 * JD-Core Version:    0.6.2
 */