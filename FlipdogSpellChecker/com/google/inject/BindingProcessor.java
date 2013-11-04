package com.google.inject;

import com.google.inject.internal.Annotations;
import com.google.inject.internal.BindingImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.internal.ExposedBindingImpl;
import com.google.inject.internal.ImmutableSet;
import com.google.inject.internal.InstanceBindingImpl;
import com.google.inject.internal.InternalFactory;
import com.google.inject.internal.LinkedBindingImpl;
import com.google.inject.internal.LinkedProviderBindingImpl;
import com.google.inject.internal.Lists;
import com.google.inject.internal.ProviderInstanceBindingImpl;
import com.google.inject.internal.ProviderMethod;
import com.google.inject.internal.Scoping;
import com.google.inject.internal.UntargettedBindingImpl;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.ConstructorBinding;
import com.google.inject.spi.ConvertedConstantBinding;
import com.google.inject.spi.ExposedBinding;
import com.google.inject.spi.InstanceBinding;
import com.google.inject.spi.LinkedKeyBinding;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.ProviderBinding;
import com.google.inject.spi.ProviderInstanceBinding;
import com.google.inject.spi.ProviderKeyBinding;
import com.google.inject.spi.UntargettedBinding;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class BindingProcessor extends AbstractProcessor
{
  private static final Set<Class<?>> FORBIDDEN_TYPES = ImmutableSet.of(new Class[] { AbstractModule.class, Binder.class, Binding.class, Injector.class, Key.class, MembersInjector.class, Module.class, Provider.class, Scope.class, TypeLiteral.class });
  private final List<CreationListener> creationListeners = Lists.newArrayList();
  private final Initializer initializer;
  private final List<Runnable> uninitializedBindings = Lists.newArrayList();

  BindingProcessor(Errors paramErrors, Initializer paramInitializer)
  {
    super(paramErrors);
    this.initializer = paramInitializer;
  }

  private <T> void bindExposed(PrivateElements paramPrivateElements, Key<T> paramKey)
  {
    ExposedKeyFactory localExposedKeyFactory = new ExposedKeyFactory(paramKey, paramPrivateElements);
    this.creationListeners.add(localExposedKeyFactory);
    putBinding(new ExposedBindingImpl(this.injector, paramPrivateElements.getExposedSource(paramKey), paramKey, localExposedKeyFactory, paramPrivateElements));
  }

  private boolean isOkayDuplicate(Binding<?> paramBinding, BindingImpl<?> paramBindingImpl)
  {
    boolean bool1 = paramBinding instanceof ExposedBindingImpl;
    boolean bool2 = false;
    if (bool1)
    {
      InjectorImpl localInjectorImpl = (InjectorImpl)((ExposedBindingImpl)paramBinding).getPrivateElements().getInjector();
      Injector localInjector = paramBindingImpl.getInjector();
      bool2 = false;
      if (localInjectorImpl == localInjector)
        bool2 = true;
    }
    return bool2;
  }

  private void putBinding(BindingImpl<?> paramBindingImpl)
  {
    Key localKey = paramBindingImpl.getKey();
    Class localClass = localKey.getRawType();
    if (FORBIDDEN_TYPES.contains(localClass))
    {
      this.errors.cannotBindToGuiceType(localClass.getSimpleName());
      return;
    }
    BindingImpl localBindingImpl = this.injector.state.getExplicitBinding(localKey);
    if ((localBindingImpl != null) && (!isOkayDuplicate(localBindingImpl, paramBindingImpl)))
    {
      this.errors.bindingAlreadySet(localKey, localBindingImpl.getSource());
      return;
    }
    this.injector.state.parent().blacklist(localKey);
    this.injector.state.putBinding(localKey, paramBindingImpl);
  }

  private <T> void validateKey(Object paramObject, Key<T> paramKey)
  {
    Annotations.checkForMisplacedScopeAnnotations(paramKey.getRawType(), paramObject, this.errors);
  }

  public void initializeBindings()
  {
    Iterator localIterator = this.uninitializedBindings.iterator();
    while (localIterator.hasNext())
      ((Runnable)localIterator.next()).run();
  }

  <T> UntargettedBindingImpl<T> invalidBinding(InjectorImpl paramInjectorImpl, Key<T> paramKey, Object paramObject)
  {
    return new UntargettedBindingImpl(paramInjectorImpl, paramKey, paramObject);
  }

  public void runCreationListeners()
  {
    Iterator localIterator = this.creationListeners.iterator();
    while (localIterator.hasNext())
      ((CreationListener)localIterator.next()).notify(this.errors);
  }

  public <T> Boolean visit(Binding<T> paramBinding)
  {
    final Object localObject = paramBinding.getSource();
    if (Void.class.equals(paramBinding.getKey().getRawType()))
    {
      if (((paramBinding instanceof ProviderInstanceBinding)) && ((((ProviderInstanceBinding)paramBinding).getProviderInstance() instanceof ProviderMethod)))
        this.errors.voidProviderMethod();
      while (true)
      {
        return Boolean.valueOf(true);
        this.errors.missingConstantValues();
      }
    }
    final Key localKey = paramBinding.getKey();
    if (localKey.getTypeLiteral().getRawType() == Provider.class)
    {
      this.errors.bindingToProvider();
      return Boolean.valueOf(true);
    }
    validateKey(paramBinding.getSource(), paramBinding.getKey());
    paramBinding.acceptTargetVisitor(new BindingTargetVisitor()
    {
      public Void visit(ConstructorBinding<? extends T> paramAnonymousConstructorBinding)
      {
        throw new IllegalArgumentException("Cannot apply a non-module element");
      }

      public Void visit(ConvertedConstantBinding<? extends T> paramAnonymousConvertedConstantBinding)
      {
        throw new IllegalArgumentException("Cannot apply a non-module element");
      }

      public Void visit(ExposedBinding<? extends T> paramAnonymousExposedBinding)
      {
        throw new IllegalArgumentException("Cannot apply a non-module element");
      }

      public Void visit(InstanceBinding<? extends T> paramAnonymousInstanceBinding)
      {
        Set localSet = paramAnonymousInstanceBinding.getInjectionPoints();
        Object localObject = paramAnonymousInstanceBinding.getInstance();
        ConstantFactory localConstantFactory = new ConstantFactory(BindingProcessor.this.initializer.requestInjection(BindingProcessor.this.injector, localObject, localObject, localSet));
        InternalFactory localInternalFactory = Scopes.scope(localKey, BindingProcessor.this.injector, localConstantFactory, this.val$scoping);
        BindingProcessor.this.putBinding(new InstanceBindingImpl(BindingProcessor.this.injector, localKey, localObject, localInternalFactory, localSet, localObject));
        return null;
      }

      public Void visit(LinkedKeyBinding<? extends T> paramAnonymousLinkedKeyBinding)
      {
        Key localKey = paramAnonymousLinkedKeyBinding.getLinkedKey();
        if (localKey.equals(localKey))
          BindingProcessor.this.errors.recursiveBinding();
        FactoryProxy localFactoryProxy = new FactoryProxy(BindingProcessor.this.injector, localKey, localKey, localObject);
        BindingProcessor.this.creationListeners.add(localFactoryProxy);
        InternalFactory localInternalFactory = Scopes.scope(localKey, BindingProcessor.this.injector, localFactoryProxy, this.val$scoping);
        BindingProcessor.this.putBinding(new LinkedBindingImpl(BindingProcessor.this.injector, localKey, localObject, localInternalFactory, this.val$scoping, localKey));
        return null;
      }

      public Void visit(ProviderBinding<? extends T> paramAnonymousProviderBinding)
      {
        throw new IllegalArgumentException("Cannot apply a non-module element");
      }

      public Void visit(ProviderInstanceBinding<? extends T> paramAnonymousProviderInstanceBinding)
      {
        Provider localProvider = paramAnonymousProviderInstanceBinding.getProviderInstance();
        Set localSet = paramAnonymousProviderInstanceBinding.getInjectionPoints();
        InternalFactoryToProviderAdapter localInternalFactoryToProviderAdapter = new InternalFactoryToProviderAdapter(BindingProcessor.this.initializer.requestInjection(BindingProcessor.this.injector, localProvider, localObject, localSet), localObject);
        InternalFactory localInternalFactory = Scopes.scope(localKey, BindingProcessor.this.injector, localInternalFactoryToProviderAdapter, this.val$scoping);
        BindingProcessor.this.putBinding(new ProviderInstanceBindingImpl(BindingProcessor.this.injector, localKey, localObject, localInternalFactory, this.val$scoping, localProvider, localSet));
        return null;
      }

      public Void visit(ProviderKeyBinding<? extends T> paramAnonymousProviderKeyBinding)
      {
        Key localKey = paramAnonymousProviderKeyBinding.getProviderKey();
        BoundProviderFactory localBoundProviderFactory = new BoundProviderFactory(BindingProcessor.this.injector, localKey, localObject);
        BindingProcessor.this.creationListeners.add(localBoundProviderFactory);
        InternalFactory localInternalFactory = Scopes.scope(localKey, BindingProcessor.this.injector, localBoundProviderFactory, this.val$scoping);
        BindingProcessor.this.putBinding(new LinkedProviderBindingImpl(BindingProcessor.this.injector, localKey, localObject, localInternalFactory, this.val$scoping, localKey));
        return null;
      }

      public Void visit(UntargettedBinding<? extends T> paramAnonymousUntargettedBinding)
      {
        if (localKey.hasAnnotationType())
        {
          BindingProcessor.this.errors.missingImplementation(localKey);
          BindingProcessor.this.putBinding(BindingProcessor.this.invalidBinding(BindingProcessor.this.injector, localKey, localObject));
          return null;
        }
        try
        {
          final BindingImpl localBindingImpl = BindingProcessor.this.injector.createUnitializedBinding(localKey, this.val$scoping, localObject, BindingProcessor.this.errors);
          BindingProcessor.this.putBinding(localBindingImpl);
          BindingProcessor.this.uninitializedBindings.add(new Runnable()
          {
            public void run()
            {
              try
              {
                ((InjectorImpl)localBindingImpl.getInjector()).initializeBinding(localBindingImpl, BindingProcessor.this.errors.withSource(BindingProcessor.1.this.val$source));
                return;
              }
              catch (ErrorsException localErrorsException)
              {
                BindingProcessor.this.errors.merge(localErrorsException.getErrors());
              }
            }
          });
          return null;
        }
        catch (ErrorsException localErrorsException)
        {
          BindingProcessor.this.errors.merge(localErrorsException.getErrors());
          BindingProcessor.this.putBinding(BindingProcessor.this.invalidBinding(BindingProcessor.this.injector, localKey, localObject));
        }
        return null;
      }
    });
    return Boolean.valueOf(true);
  }

  public Boolean visit(PrivateElements paramPrivateElements)
  {
    Iterator localIterator = paramPrivateElements.getExposedKeys().iterator();
    while (localIterator.hasNext())
      bindExposed(paramPrivateElements, (Key)localIterator.next());
    return Boolean.valueOf(false);
  }

  static abstract interface CreationListener
  {
    public abstract void notify(Errors paramErrors);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.BindingProcessor
 * JD-Core Version:    0.6.2
 */