package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.Lists;
import com.google.inject.spi.Element;
import com.google.inject.spi.MembersInjectorLookup;
import com.google.inject.spi.ProviderLookup;
import java.util.List;

class DeferredLookups
  implements Lookups
{
  private final InjectorImpl injector;
  private final List<Element> lookups = Lists.newArrayList();

  public DeferredLookups(InjectorImpl paramInjectorImpl)
  {
    this.injector = paramInjectorImpl;
  }

  public <T> MembersInjector<T> getMembersInjector(TypeLiteral<T> paramTypeLiteral)
  {
    MembersInjectorLookup localMembersInjectorLookup = new MembersInjectorLookup(paramTypeLiteral, paramTypeLiteral);
    this.lookups.add(localMembersInjectorLookup);
    return localMembersInjectorLookup.getMembersInjector();
  }

  public <T> Provider<T> getProvider(Key<T> paramKey)
  {
    ProviderLookup localProviderLookup = new ProviderLookup(paramKey, paramKey);
    this.lookups.add(localProviderLookup);
    return localProviderLookup.getProvider();
  }

  public void initialize(Errors paramErrors)
  {
    this.injector.lookups = this.injector;
    new LookupProcessor(paramErrors).process(this.injector, this.lookups);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.DeferredLookups
 * JD-Core Version:    0.6.2
 */