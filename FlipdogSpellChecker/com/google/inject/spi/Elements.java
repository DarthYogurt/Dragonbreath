package com.google.inject.spi;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Binding;
import com.google.inject.Key;
import com.google.inject.MembersInjector;
import com.google.inject.Module;
import com.google.inject.PrivateBinder;
import com.google.inject.PrivateModule;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Stage;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.AnnotatedElementBuilder;
import com.google.inject.internal.AbstractBindingBuilder;
import com.google.inject.internal.BindingBuilder;
import com.google.inject.internal.ConstantBindingBuilderImpl;
import com.google.inject.internal.Errors;
import com.google.inject.internal.ExposureBuilder;
import com.google.inject.internal.ImmutableList;
import com.google.inject.internal.Lists;
import com.google.inject.internal.Preconditions;
import com.google.inject.internal.PrivateElementsImpl;
import com.google.inject.internal.ProviderMethodsModule;
import com.google.inject.internal.Sets;
import com.google.inject.internal.SourceProvider;
import com.google.inject.matcher.Matcher;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class Elements
{
  private static final BindingTargetVisitor<Object, Object> GET_INSTANCE_VISITOR = new DefaultBindingTargetVisitor()
  {
    public Object visit(InstanceBinding<?> paramAnonymousInstanceBinding)
    {
      return paramAnonymousInstanceBinding.getInstance();
    }

    protected Object visitOther(Binding<?> paramAnonymousBinding)
    {
      throw new IllegalArgumentException();
    }
  };

  public static List<Element> getElements(Stage paramStage, Iterable<? extends Module> paramIterable)
  {
    RecordingBinder localRecordingBinder = new RecordingBinder(paramStage, null);
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
      localRecordingBinder.install((Module)localIterator.next());
    return Collections.unmodifiableList(localRecordingBinder.elements);
  }

  public static List<Element> getElements(Stage paramStage, Module[] paramArrayOfModule)
  {
    return getElements(paramStage, Arrays.asList(paramArrayOfModule));
  }

  public static List<Element> getElements(Iterable<? extends Module> paramIterable)
  {
    return getElements(Stage.DEVELOPMENT, paramIterable);
  }

  public static List<Element> getElements(Module[] paramArrayOfModule)
  {
    return getElements(Stage.DEVELOPMENT, Arrays.asList(paramArrayOfModule));
  }

  static <T> BindingTargetVisitor<T, T> getInstanceVisitor()
  {
    return GET_INSTANCE_VISITOR;
  }

  public static Module getModule(Iterable<? extends Element> paramIterable)
  {
    return new Module()
    {
      public void configure(Binder paramAnonymousBinder)
      {
        Iterator localIterator = this.val$elements.iterator();
        while (localIterator.hasNext())
          ((Element)localIterator.next()).applyTo(paramAnonymousBinder);
      }
    };
  }

  private static class RecordingBinder
    implements Binder, PrivateBinder
  {
    private final List<Element> elements;
    private final Set<Module> modules;
    private final RecordingBinder parent;
    private final PrivateElementsImpl privateElements;
    private final Object source;
    private final SourceProvider sourceProvider;
    private final Stage stage;

    private RecordingBinder(Stage paramStage)
    {
      this.stage = paramStage;
      this.modules = Sets.newHashSet();
      this.elements = Lists.newArrayList();
      this.source = null;
      this.sourceProvider = new SourceProvider().plusSkippedClasses(new Class[] { Elements.class, RecordingBinder.class, AbstractModule.class, ConstantBindingBuilderImpl.class, AbstractBindingBuilder.class, BindingBuilder.class });
      this.parent = null;
      this.privateElements = null;
    }

    private RecordingBinder(RecordingBinder paramRecordingBinder, PrivateElementsImpl paramPrivateElementsImpl)
    {
      this.stage = paramRecordingBinder.stage;
      this.modules = Sets.newHashSet();
      this.elements = paramPrivateElementsImpl.getElementsMutable();
      this.source = paramRecordingBinder.source;
      this.sourceProvider = paramRecordingBinder.sourceProvider;
      this.parent = paramRecordingBinder;
      this.privateElements = paramPrivateElementsImpl;
    }

    private RecordingBinder(RecordingBinder paramRecordingBinder, Object paramObject, SourceProvider paramSourceProvider)
    {
      int j;
      if (paramObject == null)
      {
        j = i;
        if (paramSourceProvider != null)
          break label84;
      }
      while (true)
      {
        Preconditions.checkArgument(i ^ j);
        this.stage = paramRecordingBinder.stage;
        this.modules = paramRecordingBinder.modules;
        this.elements = paramRecordingBinder.elements;
        this.source = paramObject;
        this.sourceProvider = paramSourceProvider;
        this.parent = paramRecordingBinder.parent;
        this.privateElements = paramRecordingBinder.privateElements;
        return;
        j = 0;
        break;
        label84: i = 0;
      }
    }

    private <T> AnnotatedElementBuilder exposeInternal(Key<T> paramKey)
    {
      if (this.privateElements == null)
      {
        addError("Cannot expose %s on a standard binder. Exposed bindings are only applicable to private binders.", new Object[] { paramKey });
        return new AnnotatedElementBuilder()
        {
          public void annotatedWith(Class<? extends Annotation> paramAnonymousClass)
          {
          }

          public void annotatedWith(Annotation paramAnonymousAnnotation)
          {
          }
        };
      }
      ExposureBuilder localExposureBuilder = new ExposureBuilder(this, getSource(), paramKey);
      this.privateElements.addExposureBuilder(localExposureBuilder);
      return localExposureBuilder;
    }

    public void addError(Message paramMessage)
    {
      this.elements.add(paramMessage);
    }

    public void addError(String paramString, Object[] paramArrayOfObject)
    {
      this.elements.add(new Message(getSource(), Errors.format(paramString, paramArrayOfObject)));
    }

    public void addError(Throwable paramThrowable)
    {
      String str = "An exception was caught and reported. Message: " + paramThrowable.getMessage();
      this.elements.add(new Message(ImmutableList.of(getSource()), str, paramThrowable));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Key<T> paramKey)
    {
      return new BindingBuilder(this, this.elements, getSource(), paramKey);
    }

    public <T> AnnotatedBindingBuilder<T> bind(TypeLiteral<T> paramTypeLiteral)
    {
      return bind(Key.get(paramTypeLiteral));
    }

    public <T> AnnotatedBindingBuilder<T> bind(Class<T> paramClass)
    {
      return bind(Key.get(paramClass));
    }

    public AnnotatedConstantBindingBuilder bindConstant()
    {
      return new ConstantBindingBuilderImpl(this, this.elements, getSource());
    }

    public void bindListener(Matcher<? super TypeLiteral<?>> paramMatcher, TypeListener paramTypeListener)
    {
      this.elements.add(new TypeListenerBinding(getSource(), paramTypeListener, paramMatcher));
    }

    public void bindScope(Class<? extends Annotation> paramClass, Scope paramScope)
    {
      this.elements.add(new ScopeBinding(getSource(), paramClass, paramScope));
    }

    public void convertToTypes(Matcher<? super TypeLiteral<?>> paramMatcher, TypeConverter paramTypeConverter)
    {
      this.elements.add(new TypeConverterBinding(getSource(), paramMatcher, paramTypeConverter));
    }

    public Stage currentStage()
    {
      return this.stage;
    }

    public AnnotatedElementBuilder expose(TypeLiteral<?> paramTypeLiteral)
    {
      return exposeInternal(Key.get(paramTypeLiteral));
    }

    public AnnotatedElementBuilder expose(Class<?> paramClass)
    {
      return exposeInternal(Key.get(paramClass));
    }

    public void expose(Key<?> paramKey)
    {
      exposeInternal(paramKey);
    }

    public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
    {
      MembersInjectorLookup localMembersInjectorLookup = new MembersInjectorLookup(getSource(), paramTypeLiteral);
      this.elements.add(localMembersInjectorLookup);
      return localMembersInjectorLookup.getMembersInjector();
    }

    public <T> MembersInjector<T> getMembersInjector(Class<T> paramClass)
    {
      return getMembersInjector(TypeLiteral.get(paramClass));
    }

    public <T> Provider<T> getProvider(Key<T> paramKey)
    {
      ProviderLookup localProviderLookup = new ProviderLookup(getSource(), paramKey);
      this.elements.add(localProviderLookup);
      return localProviderLookup.getProvider();
    }

    public <T> Provider<T> getProvider(Class<T> paramClass)
    {
      return getProvider(Key.get(paramClass));
    }

    protected Object getSource()
    {
      if (this.sourceProvider != null)
        return this.sourceProvider.get();
      return this.source;
    }

    public void install(Module paramModule)
    {
      Object localObject;
      if (this.modules.add(paramModule))
      {
        localObject = this;
        if ((paramModule instanceof PrivateModule))
          localObject = ((PrivateBinder)localObject).newPrivateBinder();
      }
      try
      {
        paramModule.configure((PrivateBinder)localObject);
        ((PrivateBinder)localObject).install(ProviderMethodsModule.forModule(paramModule));
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        while (true)
        {
          Collection localCollection = Errors.getMessagesFromThrowable(localRuntimeException);
          if (!localCollection.isEmpty())
            this.elements.addAll(localCollection);
          else
            addError(localRuntimeException);
        }
      }
    }

    public PrivateBinder newPrivateBinder()
    {
      PrivateElementsImpl localPrivateElementsImpl = new PrivateElementsImpl(getSource());
      this.elements.add(localPrivateElementsImpl);
      return new RecordingBinder(this, localPrivateElementsImpl);
    }

    public <T> void requestInjection(TypeLiteral<T> paramTypeLiteral, T paramT)
    {
      this.elements.add(new InjectionRequest(getSource(), paramTypeLiteral, paramT));
    }

    public void requestInjection(Object paramObject)
    {
      requestInjection(TypeLiteral.get(paramObject.getClass()), paramObject);
    }

    public void requestStaticInjection(Class<?>[] paramArrayOfClass)
    {
      int i = paramArrayOfClass.length;
      for (int j = 0; j < i; j++)
      {
        Class<?> localClass = paramArrayOfClass[j];
        this.elements.add(new StaticInjectionRequest(getSource(), localClass));
      }
    }

    public RecordingBinder skipSources(Class[] paramArrayOfClass)
    {
      if (this.source != null)
        return this;
      return new RecordingBinder(this, null, this.sourceProvider.plusSkippedClasses(paramArrayOfClass));
    }

    public String toString()
    {
      return "Binder";
    }

    public RecordingBinder withSource(Object paramObject)
    {
      return new RecordingBinder(this, paramObject, null);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.Elements
 * JD-Core Version:    0.6.2
 */