package com.google.inject.matcher;

public abstract interface Matcher<T>
{
  public abstract Matcher<T> and(Matcher<? super T> paramMatcher);

  public abstract boolean matches(T paramT);

  public abstract Matcher<T> or(Matcher<? super T> paramMatcher);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.matcher.Matcher
 * JD-Core Version:    0.6.2
 */