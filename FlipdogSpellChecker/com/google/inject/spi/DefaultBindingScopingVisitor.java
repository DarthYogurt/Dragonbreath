package com.google.inject.spi;

import com.google.inject.Scope;
import java.lang.annotation.Annotation;

public class DefaultBindingScopingVisitor<V>
  implements BindingScopingVisitor<V>
{
  public V visitEagerSingleton()
  {
    return visitOther();
  }

  public V visitNoScoping()
  {
    return visitOther();
  }

  protected V visitOther()
  {
    return null;
  }

  public V visitScope(Scope paramScope)
  {
    return visitOther();
  }

  public V visitScopeAnnotation(Class<? extends Annotation> paramClass)
  {
    return visitOther();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.DefaultBindingScopingVisitor
 * JD-Core Version:    0.6.2
 */