package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.ConfigurationException;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.spi.Element;
import com.google.inject.spi.InjectionPoint;
import com.google.inject.spi.Message;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BindingBuilder<T> extends AbstractBindingBuilder<T>
  implements AnnotatedBindingBuilder<T>
{
  public BindingBuilder(Binder paramBinder, List<Element> paramList, Object paramObject, Key<T> paramKey)
  {
    super(paramBinder, paramList, paramObject, paramKey);
  }

  public BindingBuilder<T> annotatedWith(Class<? extends Annotation> paramClass)
  {
    annotatedWithInternal(paramClass);
    return this;
  }

  public BindingBuilder<T> annotatedWith(Annotation paramAnnotation)
  {
    annotatedWithInternal(paramAnnotation);
    return this;
  }

  public BindingBuilder<T> to(Key<? extends T> paramKey)
  {
    Preconditions.checkNotNull(paramKey, "linkedKey");
    checkNotTargetted();
    BindingImpl localBindingImpl = getBinding();
    setBinding(new LinkedBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), paramKey));
    return this;
  }

  public BindingBuilder<T> to(TypeLiteral<? extends T> paramTypeLiteral)
  {
    return to(Key.get(paramTypeLiteral));
  }

  public BindingBuilder<T> to(Class<? extends T> paramClass)
  {
    return to(Key.get(paramClass));
  }

  public void toInstance(T paramT)
  {
    checkNotTargetted();
    if (paramT != null);
    while (true)
    {
      try
      {
        Set localSet = InjectionPoint.forInstanceMethodsAndFields(paramT.getClass());
        localObject = localSet;
        BindingImpl localBindingImpl = getBinding();
        setBinding(new InstanceBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), (Set)localObject, paramT));
        return;
      }
      catch (ConfigurationException localConfigurationException)
      {
        Iterator localIterator = localConfigurationException.getErrorMessages().iterator();
        if (localIterator.hasNext())
        {
          Message localMessage = (Message)localIterator.next();
          this.binder.addError(localMessage);
          continue;
        }
        localObject = (Set)localConfigurationException.getPartialValue();
        continue;
      }
      this.binder.addError("Binding to null instances is not allowed. Use toProvider(Providers.of(null)) if this is your intended behaviour.", new Object[0]);
      Object localObject = ImmutableSet.of();
    }
  }

  public BindingBuilder<T> toProvider(Key<? extends Provider<? extends T>> paramKey)
  {
    Preconditions.checkNotNull(paramKey, "providerKey");
    checkNotTargetted();
    BindingImpl localBindingImpl = getBinding();
    setBinding(new LinkedProviderBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), paramKey));
    return this;
  }

  public BindingBuilder<T> toProvider(Provider<? extends T> paramProvider)
  {
    Preconditions.checkNotNull(paramProvider, "provider");
    checkNotTargetted();
    try
    {
      Set localSet2 = InjectionPoint.forInstanceMethodsAndFields(paramProvider.getClass());
      localSet1 = localSet2;
      BindingImpl localBindingImpl = getBinding();
      setBinding(new ProviderInstanceBindingImpl(localBindingImpl.getSource(), localBindingImpl.getKey(), localBindingImpl.getScoping(), localSet1, paramProvider));
      return this;
    }
    catch (ConfigurationException localConfigurationException)
    {
      while (true)
      {
        Iterator localIterator = localConfigurationException.getErrorMessages().iterator();
        while (localIterator.hasNext())
        {
          Message localMessage = (Message)localIterator.next();
          this.binder.addError(localMessage);
        }
        Set localSet1 = (Set)localConfigurationException.getPartialValue();
      }
    }
  }

  public BindingBuilder<T> toProvider(Class<? extends Provider<? extends T>> paramClass)
  {
    return toProvider(Key.get(paramClass));
  }

  public String toString()
  {
    return "BindingBuilder<" + getBinding().getKey().getTypeLiteral() + ">";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.BindingBuilder
 * JD-Core Version:    0.6.2
 */