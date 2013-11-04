package com.google.inject.internal;

import java.util.Map;

public abstract class FailableCache<K, V>
{
  private final Map<K, Object> delegate = new MapMaker().makeComputingMap(new Function()
  {
    public Object apply(@Nullable K paramAnonymousK)
    {
      Errors localErrors = new Errors();
      Object localObject1;
      try
      {
        Object localObject2 = FailableCache.this.create(paramAnonymousK, localErrors);
        localObject1 = localObject2;
        if (localErrors.hasErrors())
          return localErrors;
      }
      catch (ErrorsException localErrorsException)
      {
        while (true)
        {
          localErrors.merge(localErrorsException.getErrors());
          localObject1 = null;
        }
      }
      return localObject1;
    }
  });

  protected abstract V create(K paramK, Errors paramErrors)
    throws ErrorsException;

  public V get(K paramK, Errors paramErrors)
    throws ErrorsException
  {
    Object localObject = this.delegate.get(paramK);
    if ((localObject instanceof Errors))
    {
      paramErrors.merge((Errors)localObject);
      throw paramErrors.toException();
    }
    return localObject;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.FailableCache
 * JD-Core Version:    0.6.2
 */