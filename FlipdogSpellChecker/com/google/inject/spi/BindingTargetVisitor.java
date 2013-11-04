package com.google.inject.spi;

public abstract interface BindingTargetVisitor<T, V>
{
  public abstract V visit(ConstructorBinding<? extends T> paramConstructorBinding);

  public abstract V visit(ConvertedConstantBinding<? extends T> paramConvertedConstantBinding);

  public abstract V visit(ExposedBinding<? extends T> paramExposedBinding);

  public abstract V visit(InstanceBinding<? extends T> paramInstanceBinding);

  public abstract V visit(LinkedKeyBinding<? extends T> paramLinkedKeyBinding);

  public abstract V visit(ProviderBinding<? extends T> paramProviderBinding);

  public abstract V visit(ProviderInstanceBinding<? extends T> paramProviderInstanceBinding);

  public abstract V visit(ProviderKeyBinding<? extends T> paramProviderKeyBinding);

  public abstract V visit(UntargettedBinding<? extends T> paramUntargettedBinding);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.BindingTargetVisitor
 * JD-Core Version:    0.6.2
 */