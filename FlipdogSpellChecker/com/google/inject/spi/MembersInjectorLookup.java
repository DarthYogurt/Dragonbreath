package com.google.inject.spi;

import com.google.inject.Binder;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.Preconditions;

public final class MembersInjectorLookup<T>
  implements Element
{
  private MembersInjector<T> delegate;
  private final Object source;
  private final TypeLiteral<T> type;

  public MembersInjectorLookup(Object paramObject, TypeLiteral<T> paramTypeLiteral)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
    this.type = ((TypeLiteral)Preconditions.checkNotNull(paramTypeLiteral, "type"));
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void applyTo(Binder paramBinder)
  {
    initializeDelegate(paramBinder.withSource(getSource()).getMembersInjector(this.type));
  }

  public MembersInjector<T> getDelegate()
  {
    return this.delegate;
  }

  public MembersInjector<T> getMembersInjector()
  {
    return new MembersInjector()
    {
      public void injectMembers(T paramAnonymousT)
      {
        if (MembersInjectorLookup.this.delegate != null);
        for (boolean bool = true; ; bool = false)
        {
          Preconditions.checkState(bool, "This MembersInjector cannot be used until the Injector has been created.");
          MembersInjectorLookup.this.delegate.injectMembers(paramAnonymousT);
          return;
        }
      }

      public String toString()
      {
        return "MembersInjector<" + MembersInjectorLookup.this.type + ">";
      }
    };
  }

  public Object getSource()
  {
    return this.source;
  }

  public TypeLiteral<T> getType()
  {
    return this.type;
  }

  public void initializeDelegate(MembersInjector<T> paramMembersInjector)
  {
    if (this.delegate == null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "delegate already initialized");
      this.delegate = ((MembersInjector)Preconditions.checkNotNull(paramMembersInjector, "delegate"));
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.spi.MembersInjectorLookup
 * JD-Core Version:    0.6.2
 */