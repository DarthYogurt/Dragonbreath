package com.a.a.a;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.simple.JSONValue;

public final class b extends Exception
{
  private static final long a = -5744582005002105505L;

  public b(String paramString1, String paramString2, Object paramObject)
  {
  }

  private static String a(Object paramObject)
  {
    if ((paramObject instanceof Map))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Map localMap = (Map)paramObject;
      localStringBuilder.append("{");
      Iterator localIterator = localMap.entrySet().iterator();
      String str = "";
      while (true)
      {
        if (!localIterator.hasNext())
        {
          localStringBuilder.append("}");
          return localStringBuilder.toString();
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localStringBuilder.append(str);
        str = ", ";
        localStringBuilder.append(JSONValue.toJSONString(localEntry.getKey()));
        localStringBuilder.append(" = ");
        localStringBuilder.append("...");
      }
    }
    if ((paramObject instanceof List))
    {
      List localList = (List)paramObject;
      if (localList.isEmpty())
        return "[]";
      if (localList.size() == 1)
        return "[" + a(localList.get(0)) + "]";
      return "[" + a(localList.get(0)) + ", ...] (" + localList.size() + " elements)";
    }
    return JSONValue.toJSONString(paramObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.b
 * JD-Core Version:    0.6.2
 */