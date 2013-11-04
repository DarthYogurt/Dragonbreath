package com.google.inject.spi;

import com.google.inject.Binding;

public abstract class DefaultBindingTargetVisitor<T, V>
  implements BindingTargetVisitor<T, V>
{
  public V visit(ConstructorBinding<? extends T> paramConstructorBinding)
  {
    return visitOther(paramConstructorBinding);
  }

  public V visit(ConvertedConstantBinding<? extends T> paramConvertedConstantBinding)
  {
    return visitOther(paramConvertedConstantBinding);
  }

  public V visit(ExposedBinding<? extends T> paramExposedBinding)
  {
    return visitOther(paramExposedBinding);
  }

  public V visit(InstanceBinding<? extends T> paramInstanceBinding)
  {
    return visitOther(paramInstanceBinding);
  }

  public V visit(LinkedKeyBinding<? extends T> paramLinkedKeyBinding)
  {
    return visitOther(paramLinkedKeyBinding);
  }

  public V visit(ProviderBinding<? extends T> paramProviderBinding)
  {
    return visitOther(paramProviderBinding);
  }

  public V visit(ProviderInstanceBinding<? extends T> paramProviderInstanceBinding)
  {
    return visitOther(paramProviderInstanceBinding);
  }

  public V visit(ProviderKeyBinding<? extends T> paramProviderKeyBinding)
  {
    return visitOther(paramProviderKeyBinding);
  }

  public V visit(UntargettedBinding<? extends T> paramUntargettedBinding)
  {
    return visitOther(paramUntargettedBinding);
  }

  protected V visitOther(Binding<? extends T> paramBinding)
  {
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.DefaultBindingTargetVisitor
 * JD-Core Version:    0.6.2
 */