package com.a.a.c;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class c
{
  public String a;
  public String b;
  public Map<String, Object> c;

  public c(Map<String, Object> paramMap)
  {
    this.c = paramMap;
    Object localObject1 = paramMap.get("error");
    if ((localObject1 instanceof String))
      this.a = ((String)localObject1);
    while (true)
    {
      Object localObject3 = paramMap.get("user_error");
      if ((localObject3 instanceof String))
        this.b = ((String)localObject3);
      return;
      if ((localObject1 instanceof Map))
      {
        Iterator localIterator = ((Map)localObject1).values().iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if ((localObject2 instanceof String))
            this.a = ((String)localObject2);
        }
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.c.c
 * JD-Core Version:    0.6.2
 */