package com.google.inject.binder;

public abstract interface ConstantBindingBuilder
{
  public abstract void to(char paramChar);

  public abstract void to(double paramDouble);

  public abstract void to(float paramFloat);

  public abstract void to(int paramInt);

  public abstract void to(long paramLong);

  public abstract void to(Class<?> paramClass);

  public abstract <E extends Enum<E>> void to(E paramE);

  public abstract void to(String paramString);

  public abstract void to(short paramShort);

  public abstract void to(boolean paramBoolean);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.binder.ConstantBindingBuilder
 * JD-Core Version:    0.6.2
 */