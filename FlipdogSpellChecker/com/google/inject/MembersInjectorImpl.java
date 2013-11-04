package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.ImmutableSet.Builder;
import com.google.inject.internal.InternalContext;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.InjectionPoint;
import java.util.Iterator;

class MembersInjectorImpl<T>
  implements MembersInjector<T>
{
  private final ImmutableList<InjectionListener<? super T>> injectionListeners;
  private final InjectorImpl injector;
  private final ImmutableList<SingleMemberInjector> memberInjectors;
  private final TypeLiteral<T> typeLiteral;
  private final ImmutableList<MembersInjector<? super T>> userMembersInjectors;

  MembersInjectorImpl(InjectorImpl paramInjectorImpl, TypeLiteral<T> paramTypeLiteral, EncounterImpl<T> paramEncounterImpl, ImmutableList<SingleMemberInjector> paramImmutableList)
  {
    this.injector = paramInjectorImpl;
    this.typeLiteral = paramTypeLiteral;
    this.memberInjectors = paramImmutableList;
    this.userMembersInjectors = paramEncounterImpl.getMembersInjectors();
    this.injectionListeners = paramEncounterImpl.getInjectionListeners();
  }

  public ImmutableSet<InjectionPoint> getInjectionPoints()
  {
    ImmutableSet.Builder localBuilder = ImmutableSet.builder();
    Iterator localIterator = this.memberInjectors.iterator();
    while (localIterator.hasNext())
      localBuilder.add(((SingleMemberInjector)localIterator.next()).getInjectionPoint());
    return localBuilder.build();
  }

  public ImmutableList<SingleMemberInjector> getMemberInjectors()
  {
    return this.memberInjectors;
  }

  void injectAndNotify(final T paramT, final Errors paramErrors)
    throws ErrorsException
  {
    if (paramT == null)
      return;
    this.injector.callInContext(new ContextualCallable()
    {
      public Void call(InternalContext paramAnonymousInternalContext)
        throws ErrorsException
      {
        MembersInjectorImpl.this.injectMembers(paramT, paramErrors, paramAnonymousInternalContext);
        return null;
      }
    });
    notifyListeners(paramT, paramErrors);
  }

  public void injectMembers(T paramT)
  {
    Errors localErrors = new Errors(this.typeLiteral);
    try
    {
      injectAndNotify(paramT, localErrors);
      localErrors.throwProvisionExceptionIfErrorsExist();
      return;
    }
    catch (ErrorsException localErrorsException)
    {
      while (true)
        localErrors.merge(localErrorsException.getErrors());
    }
  }

  void injectMembers(T paramT, Errors paramErrors, InternalContext paramInternalContext)
  {
    int i = 0;
    int j = this.memberInjectors.size();
    while (i < j)
    {
      ((SingleMemberInjector)this.memberInjectors.get(i)).inject(paramErrors, paramInternalContext, paramT);
      i++;
    }
    int k = 0;
    int m = this.userMembersInjectors.size();
    while (true)
      if (k < m)
      {
        MembersInjector localMembersInjector = (MembersInjector)this.userMembersInjectors.get(k);
        try
        {
          localMembersInjector.injectMembers(paramT);
          k++;
        }
        catch (RuntimeException localRuntimeException)
        {
          while (true)
            paramErrors.errorInUserInjector(localMembersInjector, this.typeLiteral, localRuntimeException);
        }
      }
  }

  void notifyListeners(T paramT, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    Iterator localIterator = this.injectionListeners.iterator();
    while (localIterator.hasNext())
    {
      InjectionListener localInjectionListener = (InjectionListener)localIterator.next();
      try
      {
        localInjectionListener.afterInjection(paramT);
      }
      catch (RuntimeException localRuntimeException)
      {
        paramErrors.errorNotifyingInjectionListener(localInjectionListener, this.typeLiteral, localRuntimeException);
      }
    }
    paramErrors.throwIfNewErrors(i);
  }

  public String toString()
  {
    return "MembersInjector<" + this.typeLiteral + ">";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.MembersInjectorImpl
 * JD-Core Version:    0.6.2
 */