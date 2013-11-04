package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.spi.TypeListenerBinding;

class TypeListenerBindingProcessor extends AbstractProcessor
{
  TypeListenerBindingProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public Boolean visit(TypeListenerBinding paramTypeListenerBinding)
  {
    this.injector.state.addTypeListener(paramTypeListenerBinding);
    return Boolean.valueOf(true);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.TypeListenerBindingProcessor
 * JD-Core Version:    0.6.2
 */