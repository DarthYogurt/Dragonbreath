package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.Message;
import com.google.inject.spi.TypeEncounter;
import java.util.List;

final class EncounterImpl<T>
  implements TypeEncounter<T>
{
  private final Errors errors;
  private List<InjectionListener<? super T>> injectionListeners;
  private final Lookups lookups;
  private List<MembersInjector<? super T>> membersInjectors;
  private boolean valid = true;

  public EncounterImpl(Errors paramErrors, Lookups paramLookups)
  {
    this.errors = paramErrors;
    this.lookups = paramLookups;
  }

  public void addError(Message paramMessage)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    this.errors.addMessage(paramMessage);
  }

  public void addError(String paramString, Object[] paramArrayOfObject)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    this.errors.addMessage(paramString, paramArrayOfObject);
  }

  public void addError(Throwable paramThrowable)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    Errors localErrors = this.errors;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramThrowable.getMessage();
    localErrors.errorInUserCode(paramThrowable, "An exception was caught and reported. Message: %s", arrayOfObject);
  }

  public ImmutableList<InjectionListener<? super T>> getInjectionListeners()
  {
    if (this.injectionListeners == null)
      return ImmutableList.of();
    return ImmutableList.copyOf(this.injectionListeners);
  }

  public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    return this.lookups.getMembersInjector(paramTypeLiteral);
  }

  public <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
  {
    return getMembersInjector(TypeLiteral.get(paramClass));
  }

  public ImmutableList<MembersInjector<? super T>> getMembersInjectors()
  {
    if (this.membersInjectors == null)
      return ImmutableList.of();
    return ImmutableList.copyOf(this.membersInjectors);
  }

  public <T> Provider<T> getProvider(Key<T> paramKey)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    return this.lookups.getProvider(paramKey);
  }

  public <T> Provider<T> getProvider(Class<T> paramClass)
  {
    return getProvider(Key.get(paramClass));
  }

  public void invalidate()
  {
    this.valid = false;
  }

  public void register(MembersInjector<? super T> paramMembersInjector)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    if (this.membersInjectors == null)
      this.membersInjectors = Lists.newArrayList();
    this.membersInjectors.add(paramMembersInjector);
  }

  public void register(InjectionListener<? super T> paramInjectionListener)
  {
    Preconditions.checkState(this.valid, "Encounters may not be used after hear() returns.");
    if (this.injectionListeners == null)
      this.injectionListeners = Lists.newArrayList();
    this.injectionListeners.add(paramInjectionListener);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.EncounterImpl
 * JD-Core Version:    0.6.2
 */