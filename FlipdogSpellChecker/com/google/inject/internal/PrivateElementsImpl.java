package com.google.inject.internal;

import com.google.inject.Binder;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.PrivateBinder;
import com.google.inject.spi.Element;
import com.google.inject.spi.ElementVisitor;
import com.google.inject.spi.PrivateElements;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class PrivateElementsImpl
  implements PrivateElements
{
  private ImmutableList<Element> elements;
  private List<Element> elementsMutable = Lists.newArrayList();
  private ImmutableMap<Key<?>, Object> exposedKeysToSources;
  private List<ExposureBuilder<?>> exposureBuilders = Lists.newArrayList();
  private Injector injector;
  private final Object source;

  public PrivateElementsImpl(Object paramObject)
  {
    this.source = Preconditions.checkNotNull(paramObject, "source");
  }

  public <T> T acceptVisitor(ElementVisitor<T> paramElementVisitor)
  {
    return paramElementVisitor.visit(this);
  }

  public void addExposureBuilder(ExposureBuilder<?> paramExposureBuilder)
  {
    this.exposureBuilders.add(paramExposureBuilder);
  }

  public void applyTo(Binder paramBinder)
  {
    PrivateBinder localPrivateBinder = paramBinder.withSource(this.source).newPrivateBinder();
    Iterator localIterator1 = getElements().iterator();
    while (localIterator1.hasNext())
      ((Element)localIterator1.next()).applyTo(localPrivateBinder);
    getExposedKeys();
    Iterator localIterator2 = this.exposedKeysToSources.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      localPrivateBinder.withSource(localEntry.getValue()).expose((Key)localEntry.getKey());
    }
  }

  public List<Element> getElements()
  {
    if (this.elements == null)
    {
      this.elements = ImmutableList.copyOf(this.elementsMutable);
      this.elementsMutable = null;
    }
    return this.elements;
  }

  public List<Element> getElementsMutable()
  {
    return this.elementsMutable;
  }

  public Set<Key<?>> getExposedKeys()
  {
    if (this.exposedKeysToSources == null)
    {
      LinkedHashMap localLinkedHashMap = Maps.newLinkedHashMap();
      Iterator localIterator = this.exposureBuilders.iterator();
      while (localIterator.hasNext())
      {
        ExposureBuilder localExposureBuilder = (ExposureBuilder)localIterator.next();
        localLinkedHashMap.put(localExposureBuilder.getKey(), localExposureBuilder.getSource());
      }
      this.exposedKeysToSources = ImmutableMap.copyOf(localLinkedHashMap);
      this.exposureBuilders = null;
    }
    return this.exposedKeysToSources.keySet();
  }

  public Object getExposedSource(Key<?> paramKey)
  {
    getExposedKeys();
    Object localObject = this.exposedKeysToSources.get(paramKey);
    if (localObject != null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "%s not exposed by %s.", new Object[] { paramKey, this });
      return localObject;
    }
  }

  public Injector getInjector()
  {
    return this.injector;
  }

  public Object getSource()
  {
    return this.source;
  }

  public void initInjector(Injector paramInjector)
  {
    if (this.injector == null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool, "injector already initialized");
      this.injector = ((Injector)Preconditions.checkNotNull(paramInjector, "injector"));
      return;
    }
  }

  public String toString()
  {
    return new ToStringBuilder(PrivateElements.class).add("exposedKeys", getExposedKeys()).add("source", getSource()).toString();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.PrivateElementsImpl
 * JD-Core Version:    0.6.2
 */