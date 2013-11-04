package com.google.inject;

import com.google.inject.internal.Errors;
import com.google.inject.internal.ErrorsException;
import com.google.inject.spi.MembersInjectorLookup;
import com.google.inject.spi.ProviderLookup;

class LookupProcessor extends AbstractProcessor
{
  LookupProcessor(Errors paramErrors)
  {
    super(paramErrors);
  }

  public <T> Boolean visit(MembersInjectorLookup<T> paramMembersInjectorLookup)
  {
    try
    {
      paramMembersInjectorLookup.initializeDelegate(this.injector.membersInjectorStore.get(paramMembersInjectorLookup.getType(), this.errors));
      return Boolean.valueOf(true);
    }
    catch (ErrorsException localErrorsException)
    {
      while (true)
        this.errors.merge(localErrorsException.getErrors());
    }
  }

  public <T> Boolean visit(ProviderLookup<T> paramProviderLookup)
  {
    try
    {
      paramProviderLookup.initializeDelegate(this.injector.getProviderOrThrow(paramProviderLookup.getKey(), this.errors));
      return Boolean.valueOf(true);
    }
    catch (ErrorsException localErrorsException)
    {
      while (true)
        this.errors.merge(localErrorsException.getErrors());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.LookupProcessor
 * JD-Core Version:    0.6.2
 */