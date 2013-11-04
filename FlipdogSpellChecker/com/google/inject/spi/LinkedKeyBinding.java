package com.google.inject.spi;

import com.google.inject.Binding;
import com.google.inject.Key;

public abstract interface LinkedKeyBinding<T> extends Binding<T>
{
  public abstract Key<? extends T> getLinkedKey();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.LinkedKeyBinding
 * JD-Core Version:    0.6.2
 */