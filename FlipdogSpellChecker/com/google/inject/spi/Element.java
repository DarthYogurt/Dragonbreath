package com.google.inject.spi;

import com.google.inject.Binder;

public abstract interface Element
{
  public abstract <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor);

  public abstract void applyTo(Binder paramBinder);

  public abstract Object getSource();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.Element
 * JD-Core Version:    0.6.2
 */