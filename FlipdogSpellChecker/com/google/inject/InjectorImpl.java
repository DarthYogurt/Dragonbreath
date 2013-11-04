package com.google.inject;

import com.google.inject.internal.Annotations;
import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Classes;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.InstanceBindingImpl;
import com.google.inject.internal.InternalContext;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.LinkedBindingImpl;
import com.google.inject.internal.LinkedProviderBindingImpl;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Maps;
import com.google.inject.internal.MatcherAndConverter;
import com.google.inject.internal.Nullable;
import com.google.inject.internal.Scoping;
import com.google.inject.internal.SourceProvider;
import com.google.inject.internal.ToStringBuilder;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ConvertedConstantBinding;
import com.google.inject.spi.Dependency;
import com.google.inject.spi.ProviderBinding;
import com.google.inject.spi.ProviderKeyBinding;
import com.google.inject.spi.TypeConverter;
import com.google.inject.util.Providers;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class InjectorImpl
  implements Injector, Lookups
{
  final BindingsMultimap bindingsMultimap = new BindingsMultimap(null);
  final ConstructorInjectorStore constructors = new ConstructorInjectorStore(this);
  final Initializer initializer;
  final Map<Key<?>, BindingImpl<?>> jitBindings = Maps.newHashMap();
  final ThreadLocal<Object[]> localContext;
  Lookups lookups = new DeferredLookups(this);
  MembersInjectorStore membersInjectorStore;
  final InjectorImpl parent;
  final State state;

  InjectorImpl(@Nullable InjectorImpl paramInjectorImpl, State paramState, Initializer paramInitializer)
  {
    this.parent = paramInjectorImpl;
    this.state = paramState;
    this.initializer = paramInitializer;
    if (paramInjectorImpl != null)
    {
      this.localContext = paramInjectorImpl.localContext;
      return;
    }
    this.localContext = new ThreadLocal()
    {
      protected Object[] initialValue()
      {
        return new Object[1];
      }
    };
  }

  private <T> BindingImpl<T> convertConstantStringBinding(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Key localKey = paramKey.ofType(String.class);
    BindingImpl localBindingImpl = this.state.getExplicitBinding(localKey);
    if ((localBindingImpl == null) || (!localBindingImpl.isConstant()))
      return null;
    String str = (String)localBindingImpl.getProvider().get();
    Object localObject1 = localBindingImpl.getSource();
    TypeLiteral localTypeLiteral = paramKey.getTypeLiteral();
    MatcherAndConverter localMatcherAndConverter = this.state.getConverter(str, localTypeLiteral, paramErrors, localObject1);
    if (localMatcherAndConverter == null)
      return null;
    Object localObject2;
    try
    {
      localObject2 = localMatcherAndConverter.getTypeConverter().convert(str, localTypeLiteral);
      if (localObject2 == null)
        throw paramErrors.converterReturnedNull(str, localObject1, localTypeLiteral, localMatcherAndConverter).toException();
    }
    catch (ErrorsException localErrorsException)
    {
      throw localErrorsException;
      if (!localTypeLiteral.getRawType().isInstance(localObject2))
        throw paramErrors.conversionTypeError(str, localObject1, localTypeLiteral, localMatcherAndConverter, localObject2).toException();
    }
    catch (RuntimeException localRuntimeException)
    {
      throw paramErrors.conversionError(str, localObject1, localTypeLiteral, localMatcherAndConverter, localRuntimeException).toException();
    }
    ConvertedConstantBindingImpl localConvertedConstantBindingImpl = new ConvertedConstantBindingImpl(this, paramKey, localObject2, localBindingImpl);
    return localConvertedConstantBindingImpl;
  }

  private <T> BindingImpl<T> createJustInTimeBindingRecursive(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    if (this.parent != null)
      try
      {
        BindingImpl localBindingImpl2 = this.parent.createJustInTimeBindingRecursive(paramKey, new Errors());
        return localBindingImpl2;
      }
      catch (ErrorsException localErrorsException)
      {
      }
    if (this.state.isBlacklisted(paramKey))
      throw paramErrors.childBindingAlreadySet(paramKey).toException();
    BindingImpl localBindingImpl1 = createJustInTimeBinding(paramKey, paramErrors);
    this.state.parent().blacklist(paramKey);
    this.jitBindings.put(paramKey, localBindingImpl1);
    return localBindingImpl1;
  }

  private <T> BindingImpl<MembersInjector<T>> createMembersInjectorBinding(Key<MembersInjector<T>> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType = paramKey.getTypeLiteral().getType();
    if (!(localType instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawMembersInjector().toException();
    TypeLiteral localTypeLiteral = TypeLiteral.get(((ParameterizedType)localType).getActualTypeArguments()[0]);
    MembersInjectorImpl localMembersInjectorImpl = this.membersInjectorStore.get(localTypeLiteral, paramErrors);
    ConstantFactory localConstantFactory = new ConstantFactory(Initializables.of(localMembersInjectorImpl));
    return new InstanceBindingImpl(this, paramKey, SourceProvider.UNKNOWN_SOURCE, localConstantFactory, ImmutableSet.of(), localMembersInjectorImpl);
  }

  private <T> BindingImpl<Provider<T>> createProviderBinding(Key<Provider<T>> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType = paramKey.getTypeLiteral().getType();
    if (!(localType instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawProvider().toException();
    return new ProviderBindingImpl(this, paramKey, getBindingOrThrow(paramKey.ofType(((ParameterizedType)localType).getActualTypeArguments()[0]), paramErrors));
  }

  private <T> BindingImpl<TypeLiteral<T>> createTypeLiteralBinding(Key<TypeLiteral<T>> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Type localType1 = paramKey.getTypeLiteral().getType();
    if (!(localType1 instanceof ParameterizedType))
      throw paramErrors.cannotInjectRawTypeLiteral().toException();
    Type localType2 = ((ParameterizedType)localType1).getActualTypeArguments()[0];
    if ((!(localType2 instanceof Class)) && (!(localType2 instanceof GenericArrayType)) && (!(localType2 instanceof ParameterizedType)))
      throw paramErrors.cannotInjectTypeLiteralOf(localType2).toException();
    TypeLiteral localTypeLiteral = TypeLiteral.get(localType2);
    ConstantFactory localConstantFactory = new ConstantFactory(Initializables.of(localTypeLiteral));
    return new InstanceBindingImpl(this, paramKey, SourceProvider.UNKNOWN_SOURCE, localConstantFactory, ImmutableSet.of(), localTypeLiteral);
  }

  private <T> BindingImpl<T> getJustInTimeBinding(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    Object localObject1 = this.state.lock();
    InjectorImpl localInjectorImpl = this;
    while (true)
    {
      if (localInjectorImpl != null);
      try
      {
        BindingImpl localBindingImpl2 = (BindingImpl)localInjectorImpl.jitBindings.get(paramKey);
        if (localBindingImpl2 != null)
          return localBindingImpl2;
        localInjectorImpl = localInjectorImpl.parent;
        continue;
        BindingImpl localBindingImpl1 = createJustInTimeBindingRecursive(paramKey, paramErrors);
        return localBindingImpl1;
      }
      finally
      {
      }
    }
  }

  static boolean isMembersInjector(Key<?> paramKey)
  {
    return (paramKey.getTypeLiteral().getRawType().equals(MembersInjector.class)) && (!paramKey.hasAnnotationType());
  }

  static boolean isProvider(Key<?> paramKey)
  {
    return paramKey.getTypeLiteral().getRawType().equals(Provider.class);
  }

  <T> T callInContext(ContextualCallable<T> paramContextualCallable)
    throws ErrorsException
  {
    Object[] arrayOfObject = (Object[])this.localContext.get();
    if (arrayOfObject[0] == null)
    {
      arrayOfObject[0] = new InternalContext();
      try
      {
        Object localObject2 = paramContextualCallable.call((InternalContext)arrayOfObject[0]);
        return localObject2;
      }
      finally
      {
        arrayOfObject[0] = null;
      }
    }
    return paramContextualCallable.call((InternalContext)arrayOfObject[0]);
  }

  public Injector createChildInjector(Iterable<? extends Module> paramIterable)
  {
    return new InjectorBuilder().parentInjector(this).addModules(paramIterable).build();
  }

  public Injector createChildInjector(Module[] paramArrayOfModule)
  {
    return createChildInjector(ImmutableList.of(paramArrayOfModule));
  }

  <T> BindingImpl<T> createImplementedByBinding(Key<T> paramKey, Scoping paramScoping, ImplementedBy paramImplementedBy, Errors paramErrors)
    throws ErrorsException
  {
    Class localClass1 = paramKey.getTypeLiteral().getRawType();
    Class localClass2 = paramImplementedBy.value();
    if (localClass2 == localClass1)
      throw paramErrors.recursiveImplementationType().toException();
    if (!localClass1.isAssignableFrom(localClass2))
      throw paramErrors.notASubtype(localClass2, localClass1).toException();
    final Key localKey = Key.get(localClass2);
    return new LinkedBindingImpl(this, paramKey, localClass1, Scopes.scope(paramKey, this, new InternalFactory()
    {
      public T get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency<?> paramAnonymousDependency)
        throws ErrorsException
      {
        return this.val$targetBinding.getInternalFactory().get(paramAnonymousErrors.withSource(localKey), paramAnonymousInternalContext, paramAnonymousDependency);
      }
    }
    , paramScoping), paramScoping, localKey);
  }

  <T> BindingImpl<T> createJustInTimeBinding(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    if (this.state.isBlacklisted(paramKey))
      throw paramErrors.childBindingAlreadySet(paramKey).toException();
    if (isProvider(paramKey))
      return createProviderBinding(paramKey, paramErrors);
    if (isMembersInjector(paramKey))
      return createMembersInjectorBinding(paramKey, paramErrors);
    BindingImpl localBindingImpl1 = convertConstantStringBinding(paramKey, paramErrors);
    if (localBindingImpl1 != null)
      return localBindingImpl1;
    if (paramKey.hasAnnotationType())
    {
      if (paramKey.hasAttributes())
        try
        {
          Errors localErrors = new Errors();
          BindingImpl localBindingImpl3 = getBindingOrThrow(paramKey.withoutAttributes(), localErrors);
          return localBindingImpl3;
        }
        catch (ErrorsException localErrorsException)
        {
        }
      throw paramErrors.missingImplementation(paramKey).toException();
    }
    Class localClass = paramKey.getTypeLiteral().getRawType();
    BindingImpl localBindingImpl2 = createUnitializedBinding(paramKey, Scoping.UNSCOPED, localClass, paramErrors);
    initializeBinding(localBindingImpl2, paramErrors);
    return localBindingImpl2;
  }

  <T> SingleParameterInjector<T> createParameterInjector(Dependency<T> paramDependency, Errors paramErrors)
    throws ErrorsException
  {
    return new SingleParameterInjector(paramDependency, getInternalFactory(paramDependency.getKey(), paramErrors));
  }

  <T> BindingImpl<T> createProvidedByBinding(Key<T> paramKey, Scoping paramScoping, ProvidedBy paramProvidedBy, Errors paramErrors)
    throws ErrorsException
  {
    final Class localClass1 = paramKey.getTypeLiteral().getRawType();
    final Class localClass2 = paramProvidedBy.value();
    if (localClass2 == localClass1)
      throw paramErrors.recursiveProviderType().toException();
    final Key localKey = Key.get(localClass2);
    return new LinkedProviderBindingImpl(this, paramKey, localClass1, Scopes.scope(paramKey, this, new InternalFactory()
    {
      public T get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency)
        throws ErrorsException
      {
        Errors localErrors = paramAnonymousErrors.withSource(localKey);
        Provider localProvider = (Provider)this.val$providerBinding.getInternalFactory().get(localErrors, paramAnonymousInternalContext, paramAnonymousDependency);
        Object localObject;
        try
        {
          localObject = localProvider.get();
          if ((localObject != null) && (!localClass1.isInstance(localObject)))
            throw localErrors.subtypeNotProvided(localClass2, localClass1).toException();
        }
        catch (RuntimeException localRuntimeException)
        {
          throw localErrors.errorInProvider(localRuntimeException).toException();
        }
        return localObject;
      }
    }
    , paramScoping), paramScoping, localKey);
  }

  <T> BindingImpl<T> createUnitializedBinding(Key<T> paramKey, Scoping paramScoping, Object paramObject, Errors paramErrors)
    throws ErrorsException
  {
    Class localClass1 = paramKey.getTypeLiteral().getRawType();
    if ((localClass1.isArray()) || (localClass1.isEnum()))
      throw paramErrors.missingImplementation(paramKey).toException();
    if (localClass1 == TypeLiteral.class)
      return createTypeLiteralBinding(paramKey, paramErrors);
    ImplementedBy localImplementedBy = (ImplementedBy)localClass1.getAnnotation(ImplementedBy.class);
    if (localImplementedBy != null)
    {
      Annotations.checkForMisplacedScopeAnnotations(localClass1, paramObject, paramErrors);
      return createImplementedByBinding(paramKey, paramScoping, localImplementedBy, paramErrors);
    }
    ProvidedBy localProvidedBy = (ProvidedBy)localClass1.getAnnotation(ProvidedBy.class);
    if (localProvidedBy != null)
    {
      Annotations.checkForMisplacedScopeAnnotations(localClass1, paramObject, paramErrors);
      return createProvidedByBinding(paramKey, paramScoping, localProvidedBy, paramErrors);
    }
    if (Modifier.isAbstract(localClass1.getModifiers()))
      throw paramErrors.missingImplementation(paramKey).toException();
    if (Classes.isInnerClass(localClass1))
      throw paramErrors.cannotInjectInnerClass(localClass1).toException();
    if (!paramScoping.isExplicitlyScoped())
    {
      Class localClass2 = Annotations.findScopeAnnotation(paramErrors, localClass1);
      if (localClass2 != null)
        paramScoping = Scopes.makeInjectable(Scoping.forAnnotation(localClass2), this, paramErrors.withSource(localClass1));
    }
    return ConstructorBindingImpl.create(this, paramKey, paramObject, paramScoping);
  }

  public <T> List<Binding<T>> findBindingsByType(TypeLiteral<T> paramTypeLiteral)
  {
    return this.bindingsMultimap.getAll(paramTypeLiteral);
  }

  public <T> Binding<T> getBinding(Class<T> paramClass)
  {
    return getBinding(Key.get(paramClass));
  }

  public <T> BindingImpl<T> getBinding(Key<T> paramKey)
  {
    Errors localErrors = new Errors(paramKey);
    try
    {
      BindingImpl localBindingImpl = getBindingOrThrow(paramKey, localErrors);
      localErrors.throwConfigurationExceptionIfErrorsExist();
      return localBindingImpl;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public <T> BindingImpl<T> getBindingOrThrow(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    BindingImpl localBindingImpl = this.state.getExplicitBinding(paramKey);
    if (localBindingImpl != null)
      return localBindingImpl;
    return getJustInTimeBinding(paramKey, paramErrors);
  }

  public Map<Key<?>, Binding<?>> getBindings()
  {
    return this.state.getExplicitBindingsThisLevel();
  }

  public <T> T getInstance(Key<T> paramKey)
  {
    return getProvider(paramKey).get();
  }

  public <T> T getInstance(Class<T> paramClass)
  {
    return getProvider(paramClass).get();
  }

  <T> InternalFactory<? extends T> getInternalFactory(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    return getBindingOrThrow(paramKey, paramErrors).getInternalFactory();
  }

  public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
  {
    Errors localErrors = new Errors(paramTypeLiteral);
    try
    {
      MembersInjectorImpl localMembersInjectorImpl = this.membersInjectorStore.get(paramTypeLiteral, localErrors);
      return localMembersInjectorImpl;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
  {
    return getMembersInjector(TypeLiteral.get(paramClass));
  }

  SingleParameterInjector<?>[] getParametersInjectors(List<Dependency<?>> paramList, Errors paramErrors)
    throws ErrorsException
  {
    if (paramList.isEmpty())
      return null;
    int i = paramErrors.size();
    SingleParameterInjector[] arrayOfSingleParameterInjector = new SingleParameterInjector[paramList.size()];
    int j = 0;
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      Dependency localDependency;
      int k;
      if (localIterator.hasNext())
      {
        localDependency = (Dependency)localIterator.next();
        k = j + 1;
      }
      try
      {
        arrayOfSingleParameterInjector[j] = createParameterInjector(localDependency, paramErrors.withSource(localDependency));
        label83: j = k;
        continue;
        paramErrors.throwIfNewErrors(i);
        return arrayOfSingleParameterInjector;
      }
      catch (ErrorsException localErrorsException)
      {
        break label83;
      }
    }
  }

  public Injector getParent()
  {
    return this.parent;
  }

  public <T> Provider<T> getProvider(Key<T> paramKey)
  {
    Errors localErrors = new Errors(paramKey);
    try
    {
      Provider localProvider = getProviderOrThrow(paramKey, localErrors);
      localErrors.throwIfNewErrors(0);
      return localProvider;
    }
    catch (ErrorsException localErrorsException)
    {
      throw new ConfigurationException(localErrors.merge(localErrorsException.getErrors()).getMessages());
    }
  }

  public <T> Provider<T> getProvider(Class<T> paramClass)
  {
    return getProvider(Key.get(paramClass));
  }

  <T> Provider<T> getProviderOrThrow(Key<T> paramKey, Errors paramErrors)
    throws ErrorsException
  {
    final InternalFactory localInternalFactory = getInternalFactory(paramKey, paramErrors);
    return new Provider()
    {
      public T get()
      {
        final Errors localErrors = new Errors(this.val$dependency);
        try
        {
          Object localObject = InjectorImpl.this.callInContext(new ContextualCallable()
          {
            public T call(InternalContext paramAnonymous2InternalContext)
              throws ErrorsException
            {
              paramAnonymous2InternalContext.setDependency(InjectorImpl.4.this.val$dependency);
              try
              {
                Object localObject2 = InjectorImpl.4.this.val$factory.get(localErrors, paramAnonymous2InternalContext, InjectorImpl.4.this.val$dependency);
                return localObject2;
              }
              finally
              {
                paramAnonymous2InternalContext.setDependency(null);
              }
            }
          });
          localErrors.throwIfNewErrors(0);
          return localObject;
        }
        catch (ErrorsException localErrorsException)
        {
          throw new ProvisionException(localErrors.merge(localErrorsException.getErrors()).getMessages());
        }
      }

      public String toString()
      {
        return localInternalFactory.toString();
      }
    };
  }

  void index()
  {
    Iterator localIterator = this.state.getExplicitBindingsThisLevel().values().iterator();
    while (localIterator.hasNext())
      index((Binding)localIterator.next());
  }

  <T> void index(Binding<T> paramBinding)
  {
    this.bindingsMultimap.put(paramBinding.getKey().getTypeLiteral(), paramBinding);
  }

  <T> void initializeBinding(BindingImpl<T> paramBindingImpl, Errors paramErrors)
    throws ErrorsException
  {
    Key localKey;
    if ((paramBindingImpl instanceof ConstructorBindingImpl))
    {
      localKey = paramBindingImpl.getKey();
      this.jitBindings.put(localKey, paramBindingImpl);
    }
    try
    {
      ((ConstructorBindingImpl)paramBindingImpl).initialize(this, paramErrors);
      if (1 == 0)
        this.jitBindings.remove(localKey);
      return;
    }
    finally
    {
      if (0 == 0)
        this.jitBindings.remove(localKey);
    }
  }

  public void injectMembers(Object paramObject)
  {
    getMembersInjector(paramObject.getClass()).injectMembers(paramObject);
  }

  public String toString()
  {
    return new ToStringBuilder(Lookups.class).add("bindings", this.state.getExplicitBindingsThisLevel().values()).toString();
  }

  private static class BindingsMultimap
  {
    final Map<TypeLiteral<?>, List<Binding<?>>> multimap = Maps.newHashMap();

    <T> List<Binding<T>> getAll(TypeLiteral<T> paramTypeLiteral)
    {
      if ((List)this.multimap.get(paramTypeLiteral) != null)
        return Collections.unmodifiableList((List)this.multimap.get(paramTypeLiteral));
      return ImmutableList.of();
    }

    <T> void put(TypeLiteral<T> paramTypeLiteral, Binding<T> paramBinding)
    {
      Object localObject = (List)this.multimap.get(paramTypeLiteral);
      if (localObject == null)
      {
        localObject = Lists.newArrayList();
        this.multimap.put(paramTypeLiteral, localObject);
      }
      ((List)localObject).add(paramBinding);
    }
  }

  private static class ConvertedConstantBindingImpl<T> extends BindingImpl<T>
    implements ConvertedConstantBinding<T>
  {
    final Binding<String> originalBinding;
    final Provider<T> provider;
    final T value;

    ConvertedConstantBindingImpl(Injector paramInjector, Key<T> paramKey, T paramT, Binding<String> paramBinding)
    {
      super(paramKey, paramBinding.getSource(), new ConstantFactory(Initializables.of(paramT)), Scoping.UNSCOPED);
      this.value = paramT;
      this.provider = Providers.of(paramT);
      this.originalBinding = paramBinding;
    }

    public <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor)
    {
      return paramBindingTargetVisitor.visit(this);
    }

    public void applyTo(Binder paramBinder)
    {
      throw new UnsupportedOperationException("This element represents a synthetic binding.");
    }

    public Set<Dependency<?>> getDependencies()
    {
      return ImmutableSet.of(Dependency.get(getSourceKey()));
    }

    public Provider<T> getProvider()
    {
      return this.provider;
    }

    public Key<String> getSourceKey()
    {
      return this.originalBinding.getKey();
    }

    public T getValue()
    {
      return this.value;
    }

    public String toString()
    {
      return new ToStringBuilder(ConvertedConstantBinding.class).add("key", getKey()).add("sourceKey", getSourceKey()).add("value", this.value).toString();
    }
  }

  static abstract interface MethodInvoker
  {
    public abstract Object invoke(Object paramObject, Object[] paramArrayOfObject)
      throws IllegalAccessException, InvocationTargetException;
  }

  static class ProviderBindingImpl<T> extends BindingImpl<Provider<T>>
    implements ProviderBinding<Provider<T>>
  {
    final BindingImpl<T> providedBinding;

    ProviderBindingImpl(InjectorImpl paramInjectorImpl, Key<Provider<T>> paramKey, Binding<T> paramBinding)
    {
      super(paramKey, paramBinding.getSource(), createInternalFactory(paramBinding), Scoping.UNSCOPED);
      this.providedBinding = ((BindingImpl)paramBinding);
    }

    static <T> InternalFactory<Provider<T>> createInternalFactory(Binding<T> paramBinding)
    {
      return new InternalFactory()
      {
        public Provider<T> get(Errors paramAnonymousErrors, InternalContext paramAnonymousInternalContext, Dependency paramAnonymousDependency)
        {
          return this.val$provider;
        }
      };
    }

    public <V> V acceptTargetVisitor(BindingTargetVisitor<? super Provider<T>, V> paramBindingTargetVisitor)
    {
      return paramBindingTargetVisitor.visit(this);
    }

    public void applyTo(Binder paramBinder)
    {
      throw new UnsupportedOperationException("This element represents a synthetic binding.");
    }

    public Key<? extends T> getProvidedKey()
    {
      return this.providedBinding.getKey();
    }

    public String toString()
    {
      return new ToStringBuilder(ProviderKeyBinding.class).add("key", getKey()).add("providedKey", getProvidedKey()).toString();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.InjectorImpl
 * JD-Core Version:    0.6.2
 */