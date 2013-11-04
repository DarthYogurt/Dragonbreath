package com.google.inject.spi;

import com.google.inject.Binding;

public abstract interface ElementVisitor<V>
{
  public abstract <T> V visit(Binding<T> paramBinding);

  public abstract V visit(InjectionRequest paramInjectionRequest);

  public abstract <T> V visit(MembersInjectorLookup<T> paramMembersInjectorLookup);

  public abstract V visit(Message paramMessage);

  public abstract V visit(PrivateElements paramPrivateElements);

  public abstract <T> V visit(ProviderLookup<T> paramProviderLookup);

  public abstract V visit(ScopeBinding paramScopeBinding);

  public abstract V visit(StaticInjectionRequest paramStaticInjectionRequest);

  public abstract V visit(TypeConverterBinding paramTypeConverterBinding);

  public abstract V visit(TypeListenerBinding paramTypeListenerBinding);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.ElementVisitor
 * JD-Core Version:    0.6.2
 */