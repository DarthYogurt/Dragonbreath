package com.flipdog.commons.i;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.ScopedBindingBuilder;

public abstract class d extends AbstractModule
{
  private c a;

  public d(c paramc)
  {
    this.a = paramc;
  }

  protected <T> void a(Class<T> paramClass)
  {
    bind(paramClass);
    this.a.a(paramClass);
  }

  protected <T> void a(Class<T> paramClass, Provider<T> paramProvider)
  {
    bind(paramClass).toProvider(paramProvider).in(Scopes.SINGLETON);
    this.a.a(paramClass);
  }

  protected <T, T2 extends T> void a(Class<T> paramClass, Class<T2> paramClass1)
  {
    bind(paramClass).to(paramClass1);
    this.a.a(paramClass);
  }

  protected <T, T2 extends T> void a(Class<T> paramClass, T2 paramT2)
  {
    bind(paramClass).toInstance(paramT2);
    this.a.a(paramClass);
  }

  protected <T> void b(Class<T> paramClass)
  {
    bind(paramClass).in(Scopes.SINGLETON);
    this.a.a(paramClass);
  }

  protected <T, T2 extends T> void b(Class<T> paramClass, Class<T2> paramClass1)
  {
    bind(paramClass).to(paramClass1).in(Scopes.SINGLETON);
    this.a.a(paramClass);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.i.d
 * JD-Core Version:    0.6.2
 */