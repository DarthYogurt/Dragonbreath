package com.google.inject.matcher;

import java.io.Serializable;

public abstract class AbstractMatcher<T>
  implements Matcher<T>
{
  public Matcher<T> and(Matcher<? super T> paramMatcher)
  {
    return new AndMatcher(this, paramMatcher);
  }

  public Matcher<T> or(Matcher<? super T> paramMatcher)
  {
    return new OrMatcher(this, paramMatcher);
  }

  private static class AndMatcher<T> extends AbstractMatcher<T>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Matcher<? super T> a;
    private final Matcher<? super T> b;

    public AndMatcher(Matcher<? super T> paramMatcher1, Matcher<? super T> paramMatcher2)
    {
      this.a = paramMatcher1;
      this.b = paramMatcher2;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof AndMatcher)) && (((AndMatcher)paramObject).a.equals(this.a)) && (((AndMatcher)paramObject).b.equals(this.b));
    }

    public int hashCode()
    {
      return 41 * (this.a.hashCode() ^ this.b.hashCode());
    }

    public boolean matches(T paramT)
    {
      return (this.a.matches(paramT)) && (this.b.matches(paramT));
    }

    public String toString()
    {
      return "and(" + this.a + ", " + this.b + ")";
    }
  }

  private static class OrMatcher<T> extends AbstractMatcher<T>
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Matcher<? super T> a;
    private final Matcher<? super T> b;

    public OrMatcher(Matcher<? super T> paramMatcher1, Matcher<? super T> paramMatcher2)
    {
      this.a = paramMatcher1;
      this.b = paramMatcher2;
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof OrMatcher)) && (((OrMatcher)paramObject).a.equals(this.a)) && (((OrMatcher)paramObject).b.equals(this.b));
    }

    public int hashCode()
    {
      return 37 * (this.a.hashCode() ^ this.b.hashCode());
    }

    public boolean matches(T paramT)
    {
      return (this.a.matches(paramT)) || (this.b.matches(paramT));
    }

    public String toString()
    {
      return "or(" + this.a + ", " + this.b + ")";
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.matcher.AbstractMatcher
 * JD-Core Version:    0.6.2
 */