package com.google.inject;

import com.google.inject.spi.BindingScopingVisitor;
import com.google.inject.spi.BindingTargetVisitor;
import com.google.inject.spi.Element;

public abstract interface Binding<T> extends Element
{
  public abstract <V> V acceptScopingVisitor(BindingScopingVisitor<V> paramBindingScopingVisitor);

  public abstract <V> V acceptTargetVisitor(BindingTargetVisitor<? super T, V> paramBindingTargetVisitor);

  public abstract Key<T> getKey();

  public abstract Provider<T> getProvider();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.Binding
 * JD-Core Version:    0.6.2
 */