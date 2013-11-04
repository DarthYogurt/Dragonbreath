package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.InternalContext;
import com.google.inject.spi.InjectionPoint;

abstract interface SingleMemberInjector
{
  public abstract InjectionPoint getInjectionPoint();

  public abstract void inject(Errors paramErrors, InternalContext paramInternalContext, Object paramObject);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.SingleMemberInjector
 * JD-Core Version:    0.6.2
 */