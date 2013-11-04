package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.binder.AnnotatedConstantBindingBuilder;
import com.google.inject.binder.ConstantBindingBuilder;
import com.google.inject.spi.Element;
import java.lang.annotation.Annotation;
import java.util.List;

public final class ConstantBindingBuilderImpl<T> extends AbstractBindingBuilder<T>
  implements AnnotatedConstantBindingBuilder, ConstantBindingBuilder
{
  public ConstantBindingBuilderImpl(Binder paramBinder, List<Element> paramList, Object paramObject)
  {
    super(paramBinder, paramList, paramObject, NULL_KEY);
  }

  private void toConstant(Class<?> paramClass, Object paramObject)
  {
    if (keyTypeIsSet())
    {
      this.binder.addError("Constant value is set more than once.", new Object[0]);
      return;
    }
    BindingImpl localBindingImpl = getBinding();
    Key localKey;
    if (localBindingImpl.getKey().getAnnotation() != null)
      localKey = Key.get(paramClass, localBindingImpl.getKey().getAnnotation());
    while (true)
    {
      if (paramObject == null)
        this.binder.addError("Binding to null instances is not allowed. Use toProvider(Providers.of(null)) if this is your intended behaviour.", new Object[0]);
      setBinding(new InstanceBindingImpl(localBindingImpl.getSource(), localKey, localBindingImpl.getScoping(), ImmutableSet.of(), paramObject));
      return;
      if (localBindingImpl.getKey().getAnnotationType() != null)
        localKey = Key.get(paramClass, localBindingImpl.getKey().getAnnotationType());
      else
        localKey = Key.get(paramClass);
    }
  }

  public ConstantBindingBuilder annotatedWith(Class<? extends Annotation> paramClass)
  {
    annotatedWithInternal(paramClass);
    return this;
  }

  public ConstantBindingBuilder annotatedWith(Annotation paramAnnotation)
  {
    annotatedWithInternal(paramAnnotation);
    return this;
  }

  public void to(char paramChar)
  {
    toConstant(Character.class, Character.valueOf(paramChar));
  }

  public void to(double paramDouble)
  {
    toConstant(Double.class, Double.valueOf(paramDouble));
  }

  public void to(float paramFloat)
  {
    toConstant(Float.class, Float.valueOf(paramFloat));
  }

  public void to(int paramInt)
  {
    toConstant(Integer.class, Integer.valueOf(paramInt));
  }

  public void to(long paramLong)
  {
    toConstant(Long.class, Long.valueOf(paramLong));
  }

  public void to(Class<?> paramClass)
  {
    toConstant(Class.class, paramClass);
  }

  public <E extends Enum<E>> void to(E paramE)
  {
    toConstant(paramE.getDeclaringClass(), paramE);
  }

  public void to(String paramString)
  {
    toConstant(String.class, paramString);
  }

  public void to(short paramShort)
  {
    toConstant(Short.class, Short.valueOf(paramShort));
  }

  public void to(boolean paramBoolean)
  {
    toConstant(Boolean.class, Boolean.valueOf(paramBoolean));
  }

  public String toString()
  {
    return "ConstantBindingBuilder";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ConstantBindingBuilderImpl
 * JD-Core Version:    0.6.2
 */