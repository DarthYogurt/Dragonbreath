package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.Lists;
import com.google.inject.spi.InjectionRequest;
import com.google.inject.spi.StaticInjectionRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class InjectionRequestProcessor extends AbstractProcessor
{
  private final Initializer initializer;
  private final List<StaticInjection> staticInjections = Lists.newArrayList();

  InjectionRequestProcessor(Errors paramErrors, Initializer paramInitializer)
  {
    super(paramErrors);
    this.initializer = paramInitializer;
  }

  public void injectMembers()
  {
    Iterator localIterator = this.staticInjections.iterator();
    while (localIterator.hasNext())
      ((StaticInjection)localIterator.next()).injectMembers();
  }

  public void validate()
  {
    Iterator localIterator = this.staticInjections.iterator();
    while (localIterator.hasNext())
      ((StaticInjection)localIterator.next()).validate();
  }

  public Boolean visit(InjectionRequest paramInjectionRequest)
  {
    try
    {
      Set localSet2 = paramInjectionRequest.getInjectionPoints();
      localSet1 = localSet2;
      this.initializer.requestInjection(this.injector, paramInjectionRequest.getInstance(), paramInjectionRequest.getSource(), localSet1);
      return Boolean.valueOf(true);
    }
    catch (ConfigurationException localConfigurationException)
    {
      while (true)
      {
        this.errors.merge(localConfigurationException.getErrorMessages());
        Set localSet1 = (Set)localConfigurationException.getPartialValue();
      }
    }
  }

  public Boolean visit(StaticInjectionRequest paramStaticInjectionRequest)
  {
    this.staticInjections.add(new StaticInjection(this.injector, paramStaticInjectionRequest));
    return Boolean.valueOf(true);
  }

  private class StaticInjection
  {
    final InjectorImpl injector;
    ImmutableList<SingleMemberInjector> memberInjectors;
    final StaticInjectionRequest request;
    final Object source;

    public StaticInjection(InjectorImpl paramStaticInjectionRequest, StaticInjectionRequest arg3)
    {
      this.injector = paramStaticInjectionRequest;
      Object localObject;
      this.source = localObject.getSource();
      this.request = localObject;
    }

    void injectMembers()
    {
      try
      {
        this.injector.callInContext(new ContextualCallable()
        {
          public Void call(InternalContext paramAnonymousInternalContext)
          {
            Iterator localIterator = InjectionRequestProcessor.StaticInjection.this.memberInjectors.iterator();
            while (localIterator.hasNext())
              ((SingleMemberInjector)localIterator.next()).inject(InjectionRequestProcessor.this.errors, paramAnonymousInternalContext, null);
            return null;
          }
        });
        return;
      }
      catch (ErrorsException localErrorsException)
      {
      }
      throw new AssertionError();
    }

    void validate()
    {
      Errors localErrors = InjectionRequestProcessor.this.errors.withSource(this.source);
      try
      {
        Set localSet2 = this.request.getInjectionPoints();
        localSet1 = localSet2;
        this.memberInjectors = this.injector.membersInjectorStore.getInjectors(localSet1, localErrors);
        return;
      }
      catch (ConfigurationException localConfigurationException)
      {
        while (true)
        {
          InjectionRequestProcessor.this.errors.merge(localConfigurationException.getErrorMessages());
          Set localSet1 = (Set)localConfigurationException.getPartialValue();
        }
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InjectionRequestProcessor
 * JD-Core Version:    0.6.2
 */