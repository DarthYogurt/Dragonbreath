package com.google.inject;

import com.google.inject.internal.Annotations;
import com.google.inject.internal.Errors;
import com.google.inject.internal.Preconditions;
import com.google.inject.spi.ScopeBinding;

class ScopeBindingProcessor extends AbstractProcessor
{
  ScopeBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(ScopeBinding paramScopeBinding)
  {
    Scope localScope1 = paramScopeBinding.getScope();
    Class localClass = paramScopeBinding.getAnnotationType();
    if (!Annotations.isScopeAnnotation(localClass))
      this.errors.withSource(localClass).missingScopeAnnotation();
    if (!Annotations.isRetainedAtRuntime(localClass))
      this.errors.withSource(localClass).missingRuntimeRetention(paramScopeBinding.getSource());
    Scope localScope2 = this.injector.state.getScope((Class)Preconditions.checkNotNull(localClass, "annotation type"));
    if (localScope2 != null)
      this.errors.duplicateScopes(localScope2, localClass, localScope1);
    while (true)
    {
      return Boolean.valueOf(true);
      this.injector.state.putAnnotation(localClass, (Scope)Preconditions.checkNotNull(localScope1, "scope"));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.ScopeBindingProcessor
 * JD-Core Version:    0.6.2
 */