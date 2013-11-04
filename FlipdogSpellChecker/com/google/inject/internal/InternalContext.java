package com.google.inject.internal;

import com.google.inject.spi.Dependency;
import java.util.Map;

public final class InternalContext
{
  private Map<Object, ConstructionContext<?>> constructionContexts = Maps.newHashMap();
  private Dependency dependency;

  public <T> ConstructionContext<T> getConstructionContext(Object paramObject)
  {
    ConstructionContext localConstructionContext = (ConstructionContext)this.constructionContexts.get(paramObject);
    if (localConstructionContext == null)
    {
      localConstructionContext = new ConstructionContext();
      this.constructionContexts.put(paramObject, localConstructionContext);
    }
    return localConstructionContext;
  }

  public Dependency getDependency()
  {
    return this.dependency;
  }

  public void setDependency(Dependency paramDependency)
  {
    this.dependency = paramDependency;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.InternalContext
 * JD-Core Version:    0.6.2
 */