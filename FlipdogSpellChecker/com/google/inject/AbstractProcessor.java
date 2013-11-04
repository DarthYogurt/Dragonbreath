package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.spi.Element;
import com.google.inject.spi.ElementVisitor;
import com.google.inject.spi.InjectionRequest;
import com.google.inject.spi.MembersInjectorLookup;
import com.google.inject.spi.Message;
import com.google.inject.spi.PrivateElements;
import com.google.inject.spi.ProviderLookup;
import com.google.inject.spi.ScopeBinding;
import com.google.inject.spi.StaticInjectionRequest;
import com.google.inject.spi.TypeConverterBinding;
import com.google.inject.spi.TypeListenerBinding;
import java.util.Iterator;
import java.util.List;

abstract class AbstractProcessor
  implements ElementVisitor<Boolean>
{
  protected Errors errors;
  protected InjectorImpl injector;

  protected AbstractProcessor(Errors paramErrors)
  {
    this.errors = paramErrors;
  }

  public void process(InjectorImpl paramInjectorImpl, List<Element> paramList)
  {
    Errors localErrors = this.errors;
    this.injector = paramInjectorImpl;
    try
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Element localElement = (Element)localIterator.next();
        this.errors = localErrors.withSource(localElement.getSource());
        if (((Boolean)localElement.acceptVisitor(this)).booleanValue())
          localIterator.remove();
      }
    }
    finally
    {
      this.errors = localErrors;
      this.injector = null;
    }
    this.errors = localErrors;
    this.injector = null;
  }

  public void process(Iterable<InjectorShell> paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      InjectorShell localInjectorShell = (InjectorShell)localIterator.next();
      process(localInjectorShell.getInjector(), localInjectorShell.getElements());
    }
  }

  public <T> Boolean visit(Binding<T> paramBinding)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(InjectionRequest paramInjectionRequest)
  {
    return Boolean.valueOf(false);
  }

  public <T> Boolean visit(MembersInjectorLookup<T> paramMembersInjectorLookup)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(Message paramMessage)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(PrivateElements paramPrivateElements)
  {
    return Boolean.valueOf(false);
  }

  public <T> Boolean visit(ProviderLookup<T> paramProviderLookup)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(ScopeBinding paramScopeBinding)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(StaticInjectionRequest paramStaticInjectionRequest)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(TypeConverterBinding paramTypeConverterBinding)
  {
    return Boolean.valueOf(false);
  }

  public Boolean visit(TypeListenerBinding paramTypeListenerBinding)
  {
    return Boolean.valueOf(false);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.AbstractProcessor
 * JD-Core Version:    0.6.2
 */