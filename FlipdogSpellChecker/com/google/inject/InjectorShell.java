package com.google.inject;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.PrivateElementsImpl;
import com.google.inject.internal.ProviderInstanceBindingImpl;
import com.google.inject.internal.Scoping;
import com.google.inject.internal.SourceProvider;
import com.google.inject.internal.Stopwatch;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.Element;
import com.google.inject.spi.Elements;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.PrivateElements;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

class InjectorShell
{
  private final List<Element> elements;
  private final InjectorImpl injector;
  private final PrivateElements privateElements;

  private InjectorShell(Builder paramBuilder, List<Element> paramList, InjectorImpl paramInjectorImpl)
  {
    this.privateElements = paramBuilder.privateElements;
    this.elements = paramList;
    this.injector = paramInjectorImpl;
  }

  private static void bindInjector(InjectorImpl paramInjectorImpl)
  {
    Key localKey = Key.get(Injector.class);
    InjectorFactory localInjectorFactory = new InjectorFactory(paramInjectorImpl, null);
    paramInjectorImpl.state.putBinding(localKey, new ProviderInstanceBindingImpl(paramInjectorImpl, localKey, SourceProvider.UNKNOWN_SOURCE, localInjectorFactory, Scoping.UNSCOPED, localInjectorFactory, ImmutableSet.of()));
  }

  private static void bindLogger(InjectorImpl paramInjectorImpl)
  {
    Key localKey = Key.get(Logger.class);
    LoggerFactory localLoggerFactory = new LoggerFactory(null);
    paramInjectorImpl.state.putBinding(localKey, new ProviderInstanceBindingImpl(paramInjectorImpl, localKey, SourceProvider.UNKNOWN_SOURCE, localLoggerFactory, Scoping.UNSCOPED, localLoggerFactory, ImmutableSet.of()));
  }

  List<Element> getElements()
  {
    return this.elements;
  }

  InjectorImpl getInjector()
  {
    return this.injector;
  }

  PrivateElements getPrivateElements()
  {
    return this.privateElements;
  }

  static class Builder
  {
    private final List<Element> elements = Lists.newArrayList();
    private final List<Module> modules = Lists.newArrayList();
    private InjectorImpl parent;
    private PrivateElementsImpl privateElements;
    private Stage stage;
    private State state;

    private State getState()
    {
      if (this.state == null)
        this.state = new InheritingState(State.NONE);
      return this.state;
    }

    void addModules(Iterable<? extends Module> paramIterable)
    {
      Iterator localIterator = paramIterable.iterator();
      while (localIterator.hasNext())
      {
        Module localModule = (Module)localIterator.next();
        this.modules.add(localModule);
      }
    }

    List<InjectorShell> build(Initializer paramInitializer, BindingProcessor paramBindingProcessor, Stopwatch paramStopwatch, Errors paramErrors)
    {
      boolean bool1 = true;
      boolean bool2;
      boolean bool3;
      if (this.stage != null)
      {
        bool2 = bool1;
        Preconditions.checkState(bool2, "Stage not initialized");
        if ((this.privateElements != null) && (this.parent == null))
          break label415;
        bool3 = bool1;
        label39: Preconditions.checkState(bool3, "PrivateElements with no parent");
        if (this.state == null)
          break label421;
      }
      ArrayList localArrayList;
      while (true)
      {
        Preconditions.checkState(bool1, "no state. Did you remember to lock() ?");
        InjectorImpl localInjectorImpl = new InjectorImpl(this.parent, this.state, paramInitializer);
        if (this.privateElements != null)
          this.privateElements.initInjector(localInjectorImpl);
        if (this.parent == null)
        {
          this.modules.add(0, new InjectorShell.RootModule(this.stage, null));
          new TypeConverterBindingProcessor(paramErrors).prepareBuiltInConverters(localInjectorImpl);
        }
        this.elements.addAll(Elements.getElements(this.stage, this.modules));
        paramStopwatch.resetAndLog("Module execution");
        new MessageProcessor(paramErrors).process(localInjectorImpl, this.elements);
        new TypeListenerBindingProcessor(paramErrors).process(localInjectorImpl, this.elements);
        localInjectorImpl.membersInjectorStore = new MembersInjectorStore(localInjectorImpl, localInjectorImpl.state.getTypeListenerBindings());
        paramStopwatch.resetAndLog("TypeListeners creation");
        new ScopeBindingProcessor(paramErrors).process(localInjectorImpl, this.elements);
        paramStopwatch.resetAndLog("Scopes creation");
        new TypeConverterBindingProcessor(paramErrors).process(localInjectorImpl, this.elements);
        paramStopwatch.resetAndLog("Converters creation");
        InjectorShell.bindInjector(localInjectorImpl);
        InjectorShell.bindLogger(localInjectorImpl);
        paramBindingProcessor.process(localInjectorImpl, this.elements);
        paramStopwatch.resetAndLog("Binding creation");
        localArrayList = Lists.newArrayList();
        localArrayList.add(new InjectorShell(this, this.elements, localInjectorImpl, null));
        PrivateElementProcessor localPrivateElementProcessor = new PrivateElementProcessor(paramErrors, this.stage);
        localPrivateElementProcessor.process(localInjectorImpl, this.elements);
        Iterator localIterator = localPrivateElementProcessor.getInjectorShellBuilders().iterator();
        while (localIterator.hasNext())
          localArrayList.addAll(((Builder)localIterator.next()).build(paramInitializer, paramBindingProcessor, paramStopwatch, paramErrors));
        bool2 = false;
        break;
        label415: bool3 = false;
        break label39;
        label421: bool1 = false;
      }
      paramStopwatch.resetAndLog("Private environment creation");
      return localArrayList;
    }

    Object lock()
    {
      return getState().lock();
    }

    Builder parent(InjectorImpl paramInjectorImpl)
    {
      this.parent = paramInjectorImpl;
      this.state = new InheritingState(paramInjectorImpl.state);
      return this;
    }

    Builder privateElements(PrivateElements paramPrivateElements)
    {
      this.privateElements = ((PrivateElementsImpl)paramPrivateElements);
      this.elements.addAll(paramPrivateElements.getElements());
      return this;
    }

    Builder stage(Stage paramStage)
    {
      this.stage = paramStage;
      return this;
    }
  }

  private static class InjectorFactory
    implements InternalFactory<Injector>, Provider<Injector>
  {
    private final Injector injector;

    private InjectorFactory(Injector paramInjector)
    {
      this.injector = paramInjector;
    }

    public Injector get()
    {
      return this.injector;
    }

    public Injector get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
      throws ErrorsException
    {
      return this.injector;
    }

    public String toString()
    {
      return "Provider<Injector>";
    }
  }

  private static class LoggerFactory
    implements InternalFactory<Logger>, Provider<Logger>
  {
    public Logger get()
    {
      return Logger.getAnonymousLogger();
    }

    public Logger get(Errors paramErrors, InternalContext paramInternalContext, Dependency<?> paramDependency)
    {
      InjectionPoint localInjectionPoint = paramDependency.getInjectionPoint();
      if (localInjectionPoint == null)
        return Logger.getAnonymousLogger();
      return Logger.getLogger(localInjectionPoint.getMember().getDeclaringClass().getName());
    }

    public String toString()
    {
      return "Provider<Logger>";
    }
  }

  private static class RootModule
    implements Module
  {
    final Stage stage;

    private RootModule(Stage paramStage)
    {
      this.stage = ((Stage)Preconditions.checkNotNull(paramStage, "stage"));
    }

    public void configure(Binder paramBinder)
    {
      Binder localBinder = paramBinder.withSource(SourceProvider.UNKNOWN_SOURCE);
      localBinder.bind(Stage.class).toInstance(this.stage);
      localBinder.bindScope(Singleton.class, Scopes.SINGLETON);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InjectorShell
 * JD-Core Version:    0.6.2
 */