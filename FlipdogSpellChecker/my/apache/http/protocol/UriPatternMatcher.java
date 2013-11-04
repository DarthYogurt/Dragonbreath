package my.apache.http.protocol;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import my.apache.http.annotation.GuardedBy;
import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public class UriPatternMatcher<T>
{

  @GuardedBy("this")
  private final Map<String, T> map = new HashMap();

  public Map<String, T> getObjects()
  {
    try
    {
      Map localMap = this.map;
      return localMap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public T lookup(String paramString)
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Request URI may not be null");
      }
      finally
      {
      }
    int i = paramString.indexOf("?");
    if (i != -1)
      paramString = paramString.substring(0, i);
    Object localObject1 = this.map.get(paramString);
    Object localObject2;
    Iterator localIterator;
    if (localObject1 == null)
    {
      localObject2 = null;
      localIterator = this.map.keySet().iterator();
    }
    while (true)
    {
      boolean bool = localIterator.hasNext();
      if (!bool)
        return localObject1;
      String str = (String)localIterator.next();
      if ((matchUriRequestPattern(str, paramString)) && ((localObject2 == null) || (localObject2.length() < str.length()) || ((localObject2.length() == str.length()) && (str.endsWith("*")))))
      {
        Object localObject3 = this.map.get(str);
        localObject1 = localObject3;
        localObject2 = str;
      }
    }
  }

  protected boolean matchUriRequestPattern(String paramString1, String paramString2)
  {
    if (paramString1.equals("*"));
    while (((paramString1.endsWith("*")) && (paramString2.startsWith(paramString1.substring(0, -1 + paramString1.length())))) || ((paramString1.startsWith("*")) && (paramString2.endsWith(paramString1.substring(1, paramString1.length())))))
      return true;
    return false;
  }

  public void register(String paramString, T paramT)
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("URI request pattern may not be null");
      }
      finally
      {
      }
    this.map.put(paramString, paramT);
  }

  @Deprecated
  public void setHandlers(Map<String, T> paramMap)
  {
    if (paramMap == null)
      try
      {
        throw new IllegalArgumentException("Map of handlers may not be null");
      }
      finally
      {
      }
    this.map.clear();
    this.map.putAll(paramMap);
  }

  public void setObjects(Map<String, T> paramMap)
  {
    if (paramMap == null)
      try
      {
        throw new IllegalArgumentException("Map of handlers may not be null");
      }
      finally
      {
      }
    this.map.clear();
    this.map.putAll(paramMap);
  }

  public void unregister(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      try
      {
        this.map.remove(paramString);
      }
      finally
      {
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.UriPatternMatcher
 * JD-Core Version:    0.6.2
 */