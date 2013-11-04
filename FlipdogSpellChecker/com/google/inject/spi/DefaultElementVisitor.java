package com.google.inject.spi;

import com.google.inject.Binding;

public abstract class DefaultElementVisitor<V>
  implements ElementVisitor<V>
{
  public <T> V visit(Binding<T> paramBinding)
  {
    return visitOther(paramBinding);
  }

  public V visit(InjectionRequest paramInjectionRequest)
  {
    return visitOther(paramInjectionRequest);
  }

  public <T> V visit(MembersInjectorLookup<T> paramMembersInjectorLookup)
  {
    return visitOther(paramMembersInjectorLookup);
  }

  public V visit(Message paramMessage)
  {
    return visitOther(paramMessage);
  }

  public V visit(PrivateElements paramPrivateElements)
  {
    return visitOther(paramPrivateElements);
  }

  public <T> V visit(ProviderLookup<T> paramProviderLookup)
  {
    return visitOther(paramProviderLookup);
  }

  public V visit(ScopeBinding paramScopeBinding)
  {
    return visitOther(paramScopeBinding);
  }

  public V visit(StaticInjectionRequest paramStaticInjectionRequest)
  {
    return visitOther(paramStaticInjectionRequest);
  }

  public V visit(TypeConverterBinding paramTypeConverterBinding)
  {
    return visitOther(paramTypeConverterBinding);
  }

  public V visit(TypeListenerBinding paramTypeListenerBinding)
  {
    return visitOther(paramTypeListenerBinding);
  }

  protected V visitOther(Element paramElement)
  {
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.DefaultElementVisitor
 * JD-Core Version:    0.6.2
 */