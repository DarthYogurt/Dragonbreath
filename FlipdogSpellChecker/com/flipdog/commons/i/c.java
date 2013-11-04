package com.flipdog.commons.i;

import com.flipdog.commons.diagnostic.Track;
import java.util.HashSet;

public class c
{
  private HashSet<Class<?>> a = new HashSet();

  public void a(Class<?> paramClass)
  {
    try
    {
      this.a.add(paramClass);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b(Class<?> paramClass)
  {
    try
    {
      if (!this.a.contains(paramClass))
        Track.it("Type was not properly registered: " + paramClass.getName(), new String[] { "Ioc" });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.i.c
 * JD-Core Version:    0.6.2
 */