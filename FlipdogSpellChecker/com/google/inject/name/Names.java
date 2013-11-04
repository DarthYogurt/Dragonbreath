package com.google.inject.name;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.binder.LinkedBindingBuilder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Names
{
  public static void bindProperties(Binder paramBinder, Map<String, String> paramMap)
  {
    Binder localBinder = paramBinder.skipSources(new Class[] { Names.class });
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str1 = (String)localEntry.getKey();
      String str2 = (String)localEntry.getValue();
      localBinder.bind(Key.get(String.class, new NamedImpl(str1))).toInstance(str2);
    }
  }

  public static void bindProperties(Binder paramBinder, Properties paramProperties)
  {
    Binder localBinder = paramBinder.skipSources(new Class[] { Names.class });
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = paramProperties.getProperty(str1);
      localBinder.bind(Key.get(String.class, new NamedImpl(str1))).toInstance(str2);
    }
  }

  public static Named named(String paramString)
  {
    return new NamedImpl(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.name.Names
 * JD-Core Version:    0.6.2
 */