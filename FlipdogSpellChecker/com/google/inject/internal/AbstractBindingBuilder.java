package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Scope;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.Element;
import com.google.inject.spi.InstanceBinding;
import java.lang.annotation.Annotation;
import java.util.List;

public abstract class AbstractBindingBuilder<T>
{
  public static final String ANNOTATION_ALREADY_SPECIFIED = "More than one annotation is specified for this binding.";
  public static final String BINDING_TO_NULL = "Binding to null instances is not allowed. Use toProvider(Providers.of(null)) if this is your intended behaviour.";
  public static final String CONSTANT_VALUE_ALREADY_SET = "Constant value is set more than once.";
  public static final String IMPLEMENTATION_ALREADY_SET = "Implementation is set more than once.";
  protected static final Key<?> NULL_KEY = Key.get(Void.class);
  public static final String SCOPE_ALREADY_SET = "Scope is set more than once.";
  public static final String SINGLE_INSTANCE_AND_SCOPE = "Setting the scope is not permitted when binding to a single instance.";
  protected final Binder binder;
  private BindingImpl<T> binding;
  protected List<Element> elements;
  protected int position;

  public AbstractBindingBuilder(Binder paramBinder, List<Element> paramList, Object paramObject, Key<T> paramKey)
  {
    this.binder = paramBinder;
    this.elements = paramList;
    this.position = paramList.size();
    this.binding = new UntargettedBindingImpl(paramObject, paramKey, Scoping.UNSCOPED);
    paramList.add(this.position, this.binding);
  }

  protected BindingImpl<T> annotatedWithInternal(Class<? extends Annotation> paramClass)
  {
    Preconditions.checkNotNull(paramClass, "annotationType");
    checkNotAnnotated();
    return setBinding(this.binding.withKey(Key.get(this.binding.getKey().getTypeLiteral(), paramClass)));
  }

  protected BindingImpl<T> annotatedWithInternal(Annotation paramAnnotation)
  {
    Preconditions.checkNotNull(paramAnnotation, "annotation");
    checkNotAnnotated();
    return setBinding(this.binding.withKey(Key.get(this.binding.getKey().getTypeLiteral(), paramAnnotation)));
  }

  public void asEagerSingleton()
  {
    checkNotScoped();
    setBinding(getBinding().withScoping(Scoping.EAGER_SINGLETON));
  }

  protected void checkNotAnnotated()
  {
    if (this.binding.getKey().getAnnotationType() != null)
      this.binder.addError("More than one annotation is specified for this binding.", new Object[0]);
  }

  protected void checkNotScoped()
  {
    if ((this.binding instanceof InstanceBinding))
      this.binder.addError("Setting the scope is not permitted when binding to a single instance.", new Object[0]);
    while (!this.binding.getScoping().isExplicitlyScoped())
      return;
    this.binder.addError("Scope is set more than once.", new Object[0]);
  }

  protected void checkNotTargetted()
  {
    if (!(this.binding instanceof UntargettedBindingImpl))
      this.binder.addError("Implementation is set more than once.", new Object[0]);
  }

  protected BindingImpl<T> getBinding()
  {
    return this.binding;
  }

  public void in(Scope paramScope)
  {
    Preconditions.checkNotNull(paramScope, "scope");
    checkNotScoped();
    setBinding(getBinding().withScoping(Scoping.forInstance(paramScope)));
  }

  public void in(Class<? extends Annotation> paramClass)
  {
    Preconditions.checkNotNull(paramClass, "scopeAnnotation");
    checkNotScoped();
    setBinding(getBinding().withScoping(Scoping.forAnnotation(paramClass)));
  }

  protected boolean keyTypeIsSet()
  {
    return !Void.class.equals(this.binding.getKey().getTypeLiteral().getType());
  }

  protected BindingImpl<T> setBinding(BindingImpl<T> paramBindingImpl)
  {
    this.binding = paramBindingImpl;
    this.elements.set(this.position, paramBindingImpl);
    return paramBindingImpl;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.AbstractBindingBuilder
 * JD-Core Version:    0.6.2
 */