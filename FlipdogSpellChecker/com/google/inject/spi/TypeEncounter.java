package com.google.inject.spi;

import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;

public abstract interface TypeEncounter<I>
{
  public abstract void addError(Message paramMessage);

  public abstract void addError(String paramString, Object[] paramArrayOfObject);

  public abstract void addError(Throwable paramThrowable);

  public abstract <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral);

  public abstract <T> MembersInjector<T> getMembersInjector(Class<T> paramClass);

  public abstract <T> Provider<T> getProvider(Key<T> paramKey);

  public abstract <T> Provider<T> getProvider(Class<T> paramClass);

  public abstract void register(MembersInjector<? super I> paramMembersInjector);

  public abstract void register(InjectionListener<? super I> paramInjectionListener);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.TypeEncounter
 * JD-Core Version:    0.6.2
 */