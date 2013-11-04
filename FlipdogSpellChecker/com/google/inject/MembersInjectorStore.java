package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.FailableCache;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.Lists;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.TypeListener;
import com.google.inject.spi.TypeListenerBinding;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class MembersInjectorStore
{
  private final FailableCache<TypeLiteral<?>, MembersInjectorImpl<?>> cache = new FailableCache()
  {
    protected MembersInjectorImpl<?> create(TypeLiteral<?> paramAnonymousTypeLiteral, Errors paramAnonymousErrors)
      throws ErrorsException
    {
      return MembersInjectorStore.this.createWithListeners(paramAnonymousTypeLiteral, paramAnonymousErrors);
    }
  };
  private final InjectorImpl injector;
  private final ImmutableList<TypeListenerBinding> typeListenerBindings;

  MembersInjectorStore(InjectorImpl paramInjectorImpl, List<TypeListenerBinding> paramList)
  {
    this.injector = paramInjectorImpl;
    this.typeListenerBindings = ImmutableList.copyOf(paramList);
  }

  private <T> MembersInjectorImpl<T> createWithListeners(TypeLiteral<T> paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    int i = paramErrors.size();
    ImmutableList localImmutableList;
    EncounterImpl localEncounterImpl;
    try
    {
      Set localSet2 = InjectionPoint.forInstanceMethodsAndFields(paramTypeLiteral);
      localSet1 = localSet2;
      localImmutableList = getInjectors(localSet1, paramErrors);
      paramErrors.throwIfNewErrors(i);
      localEncounterImpl = new EncounterImpl(paramErrors, this.injector.lookups);
      Iterator localIterator = this.typeListenerBindings.iterator();
      while (localIterator.hasNext())
      {
        TypeListenerBinding localTypeListenerBinding = (TypeListenerBinding)localIterator.next();
        if (localTypeListenerBinding.getTypeMatcher().matches(paramTypeLiteral))
          try
          {
            localTypeListenerBinding.getListener().hear(paramTypeLiteral, localEncounterImpl);
          }
          catch (RuntimeException localRuntimeException)
          {
            paramErrors.errorNotifyingTypeListener(localTypeListenerBinding, paramTypeLiteral, localRuntimeException);
          }
      }
    }
    catch (ConfigurationException localConfigurationException)
    {
      while (true)
      {
        paramErrors.merge(localConfigurationException.getErrorMessages());
        Set localSet1 = (Set)localConfigurationException.getPartialValue();
      }
      localEncounterImpl.invalidate();
      paramErrors.throwIfNewErrors(i);
    }
    return new MembersInjectorImpl(this.injector, paramTypeLiteral, localEncounterImpl, localImmutableList);
  }

  public <T> MembersInjectorImpl<T> get(TypeLiteral<T> paramTypeLiteral, Errors paramErrors)
    throws ErrorsException
  {
    return (MembersInjectorImpl)this.cache.get(paramTypeLiteral, paramErrors);
  }

  ImmutableList<SingleMemberInjector> getInjectors(Set<InjectionPoint> paramSet, Errors paramErrors)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramSet.iterator();
    while (true)
    {
      InjectionPoint localInjectionPoint;
      if (localIterator.hasNext())
        localInjectionPoint = (InjectionPoint)localIterator.next();
      try
      {
        Errors localErrors;
        if (localInjectionPoint.isOptional())
        {
          localErrors = new Errors(localInjectionPoint);
          label53: if (!(localInjectionPoint.getMember() instanceof Field))
            break label104;
        }
        label104: for (Object localObject = new SingleFieldInjector(this.injector, localInjectionPoint, localErrors); ; localObject = new SingleMethodInjector(this.injector, localInjectionPoint, localErrors))
        {
          localArrayList.add(localObject);
          break;
          localErrors = paramErrors.withSource(localInjectionPoint);
          break label53;
        }
        return ImmutableList.copyOf(localArrayList);
      }
      catch (ErrorsException localErrorsException)
      {
      }
    }
  }

  public boolean hasTypeListeners()
  {
    return !this.typeListenerBindings.isEmpty();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.MembersInjectorStore
 * JD-Core Version:    0.6.2
 */