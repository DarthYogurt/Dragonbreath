package com.google.inject;

import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.Iterables;
import com.google.inject.internal.Scoping;
import com.google.inject.internal.Stopwatch;
import com.google.inject.spi.Dependency;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class InjectorBuilder
{
  private final BindingProcessor bindingProcesor = new BindingProcessor(this.errors, this.initializer);
  private final Errors errors = new Errors();
  private final Initializer initializer = new Initializer();
  private final InjectionRequestProcessor injectionRequestProcessor = new InjectionRequestProcessor(this.errors, this.initializer);
  private final InjectorShell.Builder shellBuilder = new InjectorShell.Builder();
  private List<InjectorShell> shells;
  private Stage stage;
  private final Stopwatch stopwatch = new Stopwatch();

  private void initializeStatically()
  {
    this.bindingProcesor.initializeBindings();
    this.stopwatch.resetAndLog("Binding initialization");
    Iterator localIterator1 = this.shells.iterator();
    while (localIterator1.hasNext())
      ((InjectorShell)localIterator1.next()).getInjector().index();
    this.stopwatch.resetAndLog("Binding indexing");
    this.injectionRequestProcessor.process(this.shells);
    this.stopwatch.resetAndLog("Collecting injection requests");
    this.bindingProcesor.runCreationListeners();
    this.stopwatch.resetAndLog("Binding validation");
    this.injectionRequestProcessor.validate();
    this.stopwatch.resetAndLog("Static validation");
    this.initializer.validateOustandingInjections(this.errors);
    this.stopwatch.resetAndLog("Instance member validation");
    new LookupProcessor(this.errors).process(this.shells);
    Iterator localIterator2 = this.shells.iterator();
    while (localIterator2.hasNext())
      ((DeferredLookups)((InjectorShell)localIterator2.next()).getInjector().lookups).initialize(this.errors);
    this.stopwatch.resetAndLog("Provider verification");
    Iterator localIterator3 = this.shells.iterator();
    while (localIterator3.hasNext())
    {
      InjectorShell localInjectorShell = (InjectorShell)localIterator3.next();
      if (!localInjectorShell.getElements().isEmpty())
        throw new AssertionError("Failed to execute " + localInjectorShell.getElements());
    }
    this.errors.throwCreationExceptionIfErrorsExist();
  }

  private void injectDynamically()
  {
    this.injectionRequestProcessor.injectMembers();
    this.stopwatch.resetAndLog("Static member injection");
    this.initializer.injectAll(this.errors);
    this.stopwatch.resetAndLog("Instance injection");
    this.errors.throwCreationExceptionIfErrorsExist();
    Iterator localIterator = this.shells.iterator();
    while (localIterator.hasNext())
      loadEagerSingletons(((InjectorShell)localIterator.next()).getInjector(), this.stage, this.errors);
    this.stopwatch.resetAndLog("Preloading singletons");
    this.errors.throwCreationExceptionIfErrorsExist();
  }

  private Injector primaryInjector()
  {
    return ((InjectorShell)this.shells.get(0)).getInjector();
  }

  InjectorBuilder addModules(Iterable<? extends Module> paramIterable)
  {
    this.shellBuilder.addModules(paramIterable);
    return this;
  }

  Injector build()
  {
    if (this.shellBuilder == null)
      throw new AssertionError("Already built, builders are not reusable.");
    synchronized (this.shellBuilder.lock())
    {
      this.shells = this.shellBuilder.build(this.initializer, this.bindingProcesor, this.stopwatch, this.errors);
      this.stopwatch.resetAndLog("Injector construction");
      initializeStatically();
      if (this.stage == Stage.TOOL)
        return new ToolStageInjector(primaryInjector());
    }
    injectDynamically();
    return primaryInjector();
  }

  public void loadEagerSingletons(InjectorImpl paramInjectorImpl, Stage paramStage, final Errors paramErrors)
  {
    Iterator localIterator = ImmutableSet.copyOf(Iterables.concat(paramInjectorImpl.state.getExplicitBindingsThisLevel().values(), paramInjectorImpl.jitBindings.values())).iterator();
    while (localIterator.hasNext())
    {
      final BindingImpl localBindingImpl = (BindingImpl)localIterator.next();
      if (localBindingImpl.getScoping().isEagerSingleton(paramStage))
        try
        {
          paramInjectorImpl.callInContext(new ContextualCallable()
          {
            Dependency<?> dependency = Dependency.get(localBindingImpl.getKey());

            public Void call(InternalContext paramAnonymousInternalContext)
            {
              paramAnonymousInternalContext.setDependency(this.dependency);
              Errors localErrors = paramErrors.withSource(this.dependency);
              try
              {
                localBindingImpl.getInternalFactory().get(localErrors, paramAnonymousInternalContext, this.dependency);
                return null;
              }
              catch (ErrorsException localErrorsException)
              {
                localErrors.merge(localErrorsException.getErrors());
                return null;
              }
              finally
              {
                paramAnonymousInternalContext.setDependency(null);
              }
            }
          });
        }
        catch (ErrorsException localErrorsException)
        {
          throw new AssertionError();
        }
    }
  }

  InjectorBuilder parentInjector(InjectorImpl paramInjectorImpl)
  {
    this.shellBuilder.parent(paramInjectorImpl);
    return stage((Stage)paramInjectorImpl.getInstance(Stage.class));
  }

  InjectorBuilder stage(Stage paramStage)
  {
    this.shellBuilder.stage(paramStage);
    this.stage = paramStage;
    return this;
  }

  static class ToolStageInjector
    implements Injector
  {
    private final Injector delegateInjector;

    ToolStageInjector(Injector paramInjector)
    {
      this.delegateInjector = paramInjector;
    }

    public Injector createChildInjector(Iterable<? extends Module> paramIterable)
    {
      return this.delegateInjector.createChildInjector(paramIterable);
    }

    public Injector createChildInjector(Module[] paramArrayOfModule)
    {
      return this.delegateInjector.createChildInjector(paramArrayOfModule);
    }

    public <T> List<Binding<T>> findBindingsByType(TypeLiteral<T> paramTypeLiteral)
    {
      return this.delegateInjector.findBindingsByType(paramTypeLiteral);
    }

    public <T> Binding<T> getBinding(Key<T> paramKey)
    {
      return this.delegateInjector.getBinding(paramKey);
    }

    public <T> Binding<T> getBinding(Class<T> paramClass)
    {
      return this.delegateInjector.getBinding(paramClass);
    }

    public Map<Key<?>, Binding<?>> getBindings()
    {
      return this.delegateInjector.getBindings();
    }

    public <T> T getInstance(Key<T> paramKey)
    {
      throw new UnsupportedOperationException("Injector.getInstance(Key<T>) is not supported in Stage.TOOL");
    }

    public <T> T getInstance(Class<T> paramClass)
    {
      throw new UnsupportedOperationException("Injector.getInstance(Class<T>) is not supported in Stage.TOOL");
    }

    public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
    {
      throw new UnsupportedOperationException("Injector.getMembersInjector(TypeLiteral<T>) is not supported in Stage.TOOL");
    }

    public <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
    {
      throw new UnsupportedOperationException("Injector.getMembersInjector(Class<T>) is not supported in Stage.TOOL");
    }

    public Injector getParent()
    {
      return this.delegateInjector.getParent();
    }

    public <T> Provider<T> getProvider(Key<T> paramKey)
    {
      throw new UnsupportedOperationException("Injector.getProvider(Key<T>) is not supported in Stage.TOOL");
    }

    public <T> Provider<T> getProvider(Class<T> paramClass)
    {
      throw new UnsupportedOperationException("Injector.getProvider(Class<T>) is not supported in Stage.TOOL");
    }

    public void injectMembers(Object paramObject)
    {
      throw new UnsupportedOperationException("Injector.injectMembers(Object) is not supported in Stage.TOOL");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InjectorBuilder
 * JD-Core Version:    0.6.2
 */