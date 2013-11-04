package com.google.inject.internal;

import java.util.LinkedHashMap;
import java.util.Map;

public class ToStringBuilder
{
  final Map<String, Object> map = new LinkedHashMap();
  final String name;

  public ToStringBuilder(Class paramClass)
  {
    this.name = paramClass.getSimpleName();
  }

  public ToStringBuilder(String paramString)
  {
    this.name = paramString;
  }

  public ToStringBuilder add(String paramString, Object paramObject)
  {
    if (this.map.put(paramString, paramObject) != null)
      throw new RuntimeException("Duplicate names: " + paramString);
    return this;
  }

  public String toString()
  {
    return this.name + this.map.toString().replace('{', '[').replace('}', ']');
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.ToStringBuilder
 * JD-Core Version:    0.6.2
 */